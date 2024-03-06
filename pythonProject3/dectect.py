import os
import cv2.dnn
import numpy as np
import uuid
from ultralytics.utils import ASSETS, yaml_load
from ultralytics.utils.checks import check_yaml

CLASSES = yaml_load(check_yaml("weight/mydataset.yaml"))["names"]
colors = np.random.uniform(0, 255, size=(len(CLASSES), 3))


def draw_bounding_box(img, class_id, confidence, x, y, x_plus_w, y_plus_h):

    label = f"{CLASSES[class_id]} ({confidence:.2f})"
    color = colors[class_id]
    cv2.rectangle(img, (x, y), (x_plus_w, y_plus_h), color, 2)
    cv2.putText(img, label, (x - 10, y - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.5, color, 2)


def crop_and_save_objects(original_image, bounding_boxes, detections, result_folder="image"):
    #khai bai bien luu duong dan anh
    image_url = []
    # Create image folder if it doesn't exist
    os.makedirs(result_folder, exist_ok=True)

    for i, (x, y, w, h) in enumerate(bounding_boxes):
        class_name = detections[i]["class_name"]
        confidence = detections[i]["confidence"]

        # Convert coordinates to integers
        x, y, w, h = int(x), int(y), int(w), int(h)

        cropped_object = original_image[y:y+h, x:x+w]
        random_uuid = str(uuid.uuid4())

        name = f"{random_uuid}_{i+1}.png"
        image = {"url": f"http://127.0.0.1:5000/image/{name}", "className" : class_name}
        # Save the cropped object to the image folder
        result_path = os.path.join(result_folder, name)
        image_url.append(image)
        cv2.imwrite(result_path, cropped_object)
        print(f"Saved cropped object: {result_path}")
    return image_url

def detect_image(input_image):
    model: cv2.dnn.Net = cv2.dnn.readNetFromONNX("weight/final.onnx")

    # Read the input image
    original_image: np.ndarray = cv2.imread(input_image)
    before_image: np.ndarray = cv2.imread(input_image)
    [height, width, _] = original_image.shape

    # Prepare a square image for inference
    length = max((height, width))
    image = np.zeros((length, length, 3), np.uint8)
    image[0:height, 0:width] = original_image

    # Calculate scale factor
    scale = length / 640

    # Preprocess the image and prepare blob for model
    blob = cv2.dnn.blobFromImage(image, scalefactor=1 / 255, size=(640, 640), swapRB=True)
    model.setInput(blob)

    # Perform inference
    outputs = model.forward()

    # Prepare output array
    outputs = np.array([cv2.transpose(outputs[0])])
    rows = outputs.shape[1]

    class_names = set()


    for i in range(rows):
        classes_scores = outputs[0][i][4:]
        (minScore, maxScore, minClassLoc, (x, maxClassIndex)) = cv2.minMaxLoc(classes_scores)
        if maxScore >= 0.50:
            class_names.add(CLASSES[maxClassIndex])

    return list(class_names)
def main(input_image):

    model: cv2.dnn.Net = cv2.dnn.readNetFromONNX("weight/final.onnx")

    # Read the input image
    original_image: np.ndarray = cv2.imread(input_image)
    before_image: np.ndarray = cv2.imread(input_image)
    [height, width, _] = original_image.shape

    # Prepare a square image for inference
    length = max((height, width))
    image = np.zeros((length, length, 3), np.uint8)
    image[0:height, 0:width] = original_image

    # Calculate scale factor
    scale = length / 640

    # Preprocess the image and prepare blob for model
    blob = cv2.dnn.blobFromImage(image, scalefactor=1 / 255, size=(640, 640), swapRB=True)
    model.setInput(blob)

    # Perform inference
    outputs = model.forward()

    # Prepare output array
    outputs = np.array([cv2.transpose(outputs[0])])
    rows = outputs.shape[1]

    boxes = []
    scores = []
    class_ids = []

    # Iterate through output to collect bounding boxes, confidence scores, and class IDs
    for i in range(rows):
        classes_scores = outputs[0][i][4:]
        (minScore, maxScore, minClassLoc, (x, maxClassIndex)) = cv2.minMaxLoc(classes_scores)
        if maxScore >= 0.50:
            box = [
                outputs[0][i][0] - (0.5 * outputs[0][i][2]),
                outputs[0][i][1] - (0.5 * outputs[0][i][3]),
                outputs[0][i][2],
                outputs[0][i][3],
            ]
            boxes.append(box)
            scores.append(maxScore)
            class_ids.append(maxClassIndex)

    # Apply NMS (Non-maximum suppression)
    result_boxes = cv2.dnn.NMSBoxes(boxes, scores, 0.25, 0.45, 0.5)

    detections = []
    number = 0;
    # Iterate through NMS results to draw bounding boxes and labels
    for i in range(len(result_boxes)):
        index = result_boxes[i]
        box = boxes[index]
        detection = {
            "class_id": class_ids[index],
            "class_name": CLASSES[class_ids[index]],
            "confidence": scores[index],
            "box": box,
            "scale": scale,
        }
        detections.append(detection)
        draw_bounding_box(
            original_image,
            class_ids[index],
            scores[index],
            round(box[0] * scale),
            round(box[1] * scale),
            round((box[0] + box[2]) * scale),
            round((box[1] + box[3]) * scale),
        )
        number +=1;

    # Save the image with bounding boxes
    result_image_path = os.path.join("image", "predicted_image_with_boxes.png")
    cv2.imwrite(result_image_path, original_image)
    print(f"Saved predicted image with bounding boxes: {result_image_path}")

    # Crop and save objects based on bounding boxes
    bounding_boxes = []
    for i, index in enumerate(result_boxes):
        box = boxes[index]
        x = round(box[0] * scale)
        y = round(box[1] * scale)
        w = round(box[2] * scale)
        h = round(box[3] * scale)
        bounding_boxes.append((x, y, w, h))

    original_image: np.ndarray = cv2.imread(input_image)
    image_url = crop_and_save_objects(original_image, bounding_boxes, detections)
    print(image_url)
    return image_url
if __name__ == '__main__':
        main("uploads/799_1.jpeg");
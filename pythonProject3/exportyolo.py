from ultralytics import YOLO

# Load a model

model = YOLO("weight/final.pt")  # load a pretrained model (recommended for training)

path = model.export(format="onnx")  # export the model to ONNX format
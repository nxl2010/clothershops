import cv2
import numpy as np
import matplotlib.pyplot as plt

def show_image(image):
    plt.imshow(image)
    plt.axis('off')  # Tắt trục tọa độ
    plt.show()
def remove_alpha_channel(image, replacement_color=(255, 255, 255)):
    # Kiểm tra xem ảnh có kênh alpha không
    if image.shape[2] == 4:
        # Tách kênh BGR và alpha
        bgr = image[:, :, :3]
        alpha = image[:, :, 3]

        # Tạo mask cho các pixel trong suốt
        transparent_mask = alpha == 0

        # Thiết lập các pixel trong suốt thành màu thay thế
        bgr[transparent_mask] = replacement_color

        # Loại bỏ kênh alpha
        return bgr
    else:
        return image

def get_primary_colors(image_path):
    # Đọc ảnh và loại bỏ kênh alpha nếu có
    img = cv2.imread(image_path, cv2.IMREAD_UNCHANGED)
    bgr_image = remove_alpha_channel(img)
    show_image(bgr_image)
    # Chuyển đổi ảnh sang không gian màu HSV
    hsv_image = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
    show_image(hsv_image)
    # Xác định các ngưỡng màu cho các màu cơ bản
    color_ranges = {
        "red": ([0, 100, 100], [10, 255, 255]),
        "orange": ([10, 100, 100], [20, 255, 255]),
        "yellow": ([20, 100, 100], [30, 255, 255]),
        "green": ([30, 100, 100], [60, 255, 255]),
        "blue": ([90, 100, 100], [120, 255, 255]),
        "purple": ([120, 100, 100], [150, 255, 255]),
        "pink": ([150, 100, 100], [180, 255, 255]),
        "white": ([0, 0, 200], [180, 30, 255]),
        "black": ([0, 0, 0], [180, 255, 30]),
        "gray": ([0, 0, 30], [180, 30, 200]),
    }

    # Tạo mask và đếm số lượng pixel cho từng màu
    color_pixel_counts = {}
    for color_name, (lower, upper) in color_ranges.items():
        mask = cv2.inRange(hsv_image, np.array(lower), np.array(upper))
        pixel_count = np.count_nonzero(mask)
        color_pixel_counts[color_name] = pixel_count

    # Sắp xếp các màu theo số lượng pixel giảm dần
    sorted_colors = sorted(color_pixel_counts.items(), key=lambda x: x[1], reverse=True)

    # Trả về hai màu chính có số lượng pixel lớn nhất
    primary_colors = sorted_colors[:2]
    return primary_colors

# Sử dụng hàm để lấy thông tin về hai màu chính từ ảnh
print(get_primary_colors('799_3.jpeg'))

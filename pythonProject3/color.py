import cv2
import numpy as np
from PIL import Image
def getColors(image_path):

    # Đọc ảnh và chuyển đổi sang không gian màu HSV
    image = cv2.imread(image_path, cv2.IMREAD_UNCHANGED)

    # Chuyển đổi ảnh sang không gian màu HSV
    hsv_image = cv2.cvtColor(image, cv2.COLOR_RGB2HSV)
    # Xác định các ngưỡng màu cho các màu sắc khác nhau
    color_ranges = {
        "red": ([0, 50, 50], [10, 255, 255]),
        "pink": ([150, 50, 50], [180, 255, 255]),
        "white": ([0, 0, 200], [180, 30, 255]),
        "yellow": ([20, 50, 50], [40, 255, 255]),
        "green": ([40, 50, 50], [80, 255, 255]),
        "black": ([0, 0, 0], [180, 255, 30]),
        "gray": ([0, 0, 100], [180, 30, 200])
    }

    # Tạo mask cho mỗi màu sắc và đếm số lượng pixel
    color_pixel_counts = {}
    for color_name, (lower, upper) in color_ranges.items():
        mask = cv2.inRange(hsv_image, np.array(lower), np.array(upper))
        pixel_count = np.count_nonzero(mask)
        total_pixels = hsv_image.shape[0] * hsv_image.shape[1]
        color_percentage = (pixel_count / total_pixels) * 100
        color_pixel_counts[color_name] = color_percentage

    # Lọc ra các màu có tỷ lệ lớn hơn ngưỡng được chỉ định
    top_colors = [(color, percentage) for color, percentage in color_pixel_counts.items() if percentage > 1]

    # Sắp xếp các màu theo tỷ lệ giảm dần
    sorted_colors = sorted(top_colors, key=lambda x: x[1], reverse=True)
    colors = sorted_colors[:2]
    print(colors)
    # Trả về hai màu có tỷ lệ lớn nhất
    return colors

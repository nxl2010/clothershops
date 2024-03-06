import cv2
import numpy as np
import matplotlib.pyplot as plt

def show_image(image):
    plt.imshow(image)
    plt.axis('off')  # Tắt trục tọa độ
    plt.show()
def convert_bgra_to_hsv_with_alpha(image_path):
    # Đọc ảnh BGRA
    img = cv2.imread(image_path, cv2.IMREAD_UNCHANGED)

    # Tách kênh alpha
    alpha_channel = img[:, :, 3]

    # Chuyển đổi các kênh BGR sang HSV
    bgr_image = img[:, :, :3]  # Lấy 3 kênh BGR
    hsv_image = cv2.cvtColor(bgr_image, cv2.COLOR_BGR2HSV)

    # Hợp nhất kênh alpha với ảnh HSV
    hsv_with_alpha = cv2.merge((hsv_image, alpha_channel))

    return hsv_with_alpha

# Sử dụng hàm để chuyển đổi ảnh BGRA sang HSV với kênh alpha

def getColors(image_path):
    # Đọc ảnh và chuyển đổi sang không gian màu HSV
    hsv_with_alpha = convert_bgra_to_hsv_with_alpha(image_path)
    show_image(hsv_with_alpha)
    # Xác định các ngưỡng màu cho các màu sắc khác nhau
    color_ranges = {
        "red": ([0, 50, 50, 0], [10, 255, 255, 255]),
        "pink": ([150, 50, 50, 0], [180, 255, 255, 255]),
        "white": ([0, 0, 200, 0], [180, 30, 255, 255]),
        "yellow": ([20, 50, 50, 0], [40, 255, 255, 255]),
        "green": ([40, 50, 50, 0], [80, 255, 255, 255]),
        "black": ([0, 0, 0, 0], [180, 255, 30, 255]),
        "gray": ([0, 0, 100, 0], [180, 30, 200, 255])
    }

    # Tạo mask cho mỗi màu sắc và đếm số lượng pixel
    color_pixel_counts = {}
    for color_name, (lower, upper) in color_ranges.items():
        mask = cv2.inRange(hsv_with_alpha, np.array(lower), np.array(upper))
        pixel_count = np.count_nonzero(mask)
        total_pixels = hsv_with_alpha.shape[0] * hsv_with_alpha.shape[1]
        color_percentage = (pixel_count / total_pixels) * 100
        color_pixel_counts[color_name] = color_percentage

    # Lọc ra các màu có tỷ lệ lớn hơn ngưỡng được chỉ định
    top_colors = [(color, percentage) for color, percentage in color_pixel_counts.items() if percentage > 1]

    # Sắp xếp các màu theo tỷ lệ giảm dần
    sorted_colors = sorted(top_colors, key=lambda x: x[1], reverse=True)
    colors = sorted_colors[:2]

    # Trả về hai màu có tỷ lệ lớn nhất
    return colors

# Sử dụng hàm để lấy thông tin về hai màu phổ biến nhất trong ảnh
print(getColors('image/6edde2eb-d648-4957-a598-58a845558bd3.png'))

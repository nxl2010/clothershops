import rembg
from PIL import Image
import os
import uuid
import numpy as np
def remove_background(input_image_path):
    output_directory = 'image/'

    # Tạo thư mục lưu ảnh đã xóa nền nếu nó không tồn tại
    os.makedirs(output_directory, exist_ok=True)

    # Đọc ảnh
    image = Image.open(input_image_path)

    # Chuyển đổi ảnh sang định dạng RGB nếu cần thiết
    image = image.convert("RGB")

    # Sử dụng Rembg để loại bỏ nền
    image_array = np.array(image)

    # Xóa nền với màu trắng
    image_no_bg = rembg.remove(image_array, alpha_matte=True)

    # Tạo ảnh từ mảng numpy đã loại bỏ nền
    image_no_bg = Image.fromarray(image_no_bg)

    # Tạo tên cho ảnh dựa trên giá trị UUID
    output_image_name = str(uuid.uuid4()) + '.png'

    # Đường dẫn đầy đủ cho ảnh đã xóa nền
    output_image_path = os.path.join(output_directory, output_image_name)

    # Lưu ảnh đã loại bỏ nền
    image_no_bg.save(output_image_path)
    print(output_image_path)
    # Trả về đường dẫn của ảnh đã lưu
    return output_image_path

remove_background('image/470defd4-e994-40c3-a1a0-d7ddbc795772_1.png')
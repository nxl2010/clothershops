from flask import Flask, request, jsonify, send_file
from flask_cors import CORS
import os
from dectect import main, detect_image
from werkzeug.utils import secure_filename
from deletefile import deleteFile
from color import getColors
from removebackground import remove_background
app = Flask(__name__)
CORS(app, CORS_ENABLED=False)
UPLOAD_FOLDER = 'uploads'  # Thư mục để lưu trữ ảnh tải lên

# Thiết lập thư mục upload
if not os.path.exists(UPLOAD_FOLDER):
    os.makedirs(UPLOAD_FOLDER)


@app.route('/color', methods=['POST'])
def upload_file():
    if 'file' not in request.files:
        return jsonify({'Không có file'})

    file = request.files['file']
    # Lưu ảnh vào thư mục uploads
    filename = secure_filename(file.filename)
    file_path = os.path.join(UPLOAD_FOLDER, filename)
    file.save(file_path)

    # Gọi hàm color để xử lý màu
    colors = getColors(file_path)


    os.remove(file_path)

    return jsonify({'color': colors})
@app.route('/dectect', methods=['POST'])
def dectect_color():
    if 'file' not in request.files:
        return jsonify({'Không có file'})

    file = request.files['file']
    # Lưu ảnh vào thư mục uploads
    filename = secure_filename(file.filename)
    file_path = os.path.join(UPLOAD_FOLDER, filename)
    file.save(file_path)

    # Gọi hàm main để xử lý ảnh
    dectect = main(file_path)


    os.remove(file_path)

    return jsonify({'filename': dectect})

#Lấy ảnh
@app.route('/image/<filename>')
def get_image(filename):
    image_path = os.path.join('image', filename)
    return send_file(image_path, mimetype='image/png')
@app.route('/removebg')
def removebg():
    if 'file' not in request.files:
        return jsonify({'Không có file'})
    file = request.files['file']
    # Lưu ảnh vào thư mục uploads
    filename = secure_filename(file.filename)
    file_path = os.path.join(UPLOAD_FOLDER, filename)
    file.save(file_path)

    # Gọi hàm main để xử lý ảnh
    url = remove_background(file_path)

    os.remove(file_path)
    return jsonify({'url': url})
#Xóa file
@app.route('/deltefile')
def deltefile():
    deleteFile()
@app.route('/postproduct', methods=['POST'])
def post_product():
    if 'file' not in request.files:
        return jsonify({'Không có file'})

    file = request.files['file']
    # Lưu ảnh vào thư mục uploads
    filename = secure_filename(file.filename)
    file_path = os.path.join(UPLOAD_FOLDER, filename)
    file.save(file_path)

    # Gọi hàm main để xử lý ảnh
    dectect = detect_image(file_path)
    url = remove_background(file_path)


    os.remove(file_path)

    return jsonify({'category': dectect, 'url' : url })

if __name__ == '__main__':
    app.static_folder = 'image'
    app.run(debug=True)

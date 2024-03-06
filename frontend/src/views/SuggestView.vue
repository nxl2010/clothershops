<template>
    <div class=" mx-2 mt-4 py-8 px-4 bg-gradient-to-r from-blue-500 to-purple-500 text-white rounded-md">
        <h1 class="text-3xl font-bold mb-2">Chào mừng đến với ứng dụng gợi ý sản phẩm</h1>
        <p class="text-lg">Hãy chọn từ trang phục trong cửa hàng của chúng tôi hoặc đăng tải hình ảnh của bạn lên</p>
    </div>
    <div class="flex mb-32">
        <div class="w-1/2 py-4 px-2">
            <div class="mb-8 bg-white p-6 rounded shadow-md">
                <h2 class="text-2xl font-bold mb-4">Sử dụng sản phẩm cửa hàng</h2>
                <div v-if="categories.length === 0">
                    <p>Chưa có sản phẩm để gợi ý.</p>
                </div>
                <div v-else>
                    <div v-for="category in categories" :key="category.name" class="mb-4">
                        <h3 class="text-lg font-bold mb-2">{{ category.name }}</h3>
                        <div class="flex items-center space-x-4">
                            <div v-for="product in category.products" :key="product.name">
                                <img :src="product.image" :alt="product.name" class="w-16 h-16 object-cover rounded">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="w-1/2 py-4 px-2">
            <div class="mb-8 bg-white p-6 rounded shadow-md">
                <div v-if="showForm">
                    <h2 class="text-2xl font-bold mb-4">Upload ảnh</h2>
                    <p>Bạn có thể đăng ảnh chứa trang phục của bạn dưới đây.</p>
                    <p>- Đảm bảo chất lượng ảnh tốt để có kết quả tốt nhất.</p>
                    <p>- Hình ảnh sản phẩm không bị chèn lên nhau</p>
                    <form @submit.prevent="uploadImage" enctype="multipart/form-data">
                        <label for="image" class="block text-sm font-medium text-gray-700 mb-2">Chọn ảnh</label>
                        <input type="file" id="image" name="image" accept="image/*" @change="handleFileChange"
                            class="w-full py-2 px-3 border rounded focus:outline-none focus:ring focus:border-blue-300 mb-4">

                        <button type="submit"
                            class="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600 focus:outline-none focus:ring focus:border-blue-300">
                            Đăng ảnh
                        </button>
                      
                    </form>
                    <button
                            class="ml-2 bg-green-500 text-white py-2 px-4 rounded hover:bg-blue-600 focus:outline-none focus:ring focus:border-blue-300">
                            <i class="fa-solid fa-camera"></i>
                        </button>
                </div>


                <!-- Hiển thị loading khi đang xử lý -->
                <div v-if="loading" class="flex items-center justify-center h-48">
                    <div role="status" class="text-center">
                        <svg aria-hidden="true"
                            class="inline w-16 h-16 text-gray-200 animate-spin dark:text-gray-600 fill-blue-600 mb-4"
                            viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path
                                d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z"
                                fill="currentColor" />
                            <path
                                d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z"
                                fill="currentFill" />
                        </svg>
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>
                <!-- Hiển thị kết quả sau khi xử lý xong -->
                <div v-if="results.length !=0">
                    <div class="text-center">
                        <h2 class="text-3xl font-bold mb-4 text-blue-500">Kết quả</h2>
                        <p class="text-lg mb-2">Hệ thống đã phát hiện {{ results.length }} sản phẩm trong ảnh của bạn</p>
                        <p class="mb-4">Vui lòng chọn một ảnh để nhận các gợi ý</p>

                        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
                            <div v-for="result in results" :key="result.url" class="bg-white p-4 rounded-md shadow-md">
                                <img :src="result.url" alt="Result Image" class="w-full h-32 object-cover rounded-md mb-2">
                                <button @click="selectImage(result.className, result.url)"
                                    class="bg-blue-500 text-white py-1 px-2 rounded-full hover:bg-blue-600 focus:outline-none focus:ring focus:border-blue-300">
                                    Chọn ảnh
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>


    </div>
</template>
  
<script setup>
import { ref, onMounted, watch } from 'vue';

import { useRouter } from 'vue-router';
import router from '../router';
import axios from 'axios';
const selectedFile = ref(null);
const handleFileChange = (event) => {
    selectedFile.value = event.target.files[0];
};
const showForm = ref(true);
const loading = ref(false);
const result = ref(null);
const results = ref([]);
const uploadImage = async () => {
    // Hiển thị loading
    loading.value = true;
    showForm.value = false;

    try {
        const formData = new FormData();
        formData.append('file', selectedFile.value);

        const response = await axios.post('http://localhost:5000/dectect', formData);
        const imageLinks = response.data.filename.map(item => item.url);
        results.value = response.data.filename;
        await new Promise((resolve) => setTimeout(resolve, 2000));

        loading.value = false;
        result.value = imageLinks;
    } catch (error) {
        console.error('Error uploading image:', error);
       
    }
};

const selectImage = (classname, url) => {
    const name = encodeURIComponent(classname); 
    localStorage.setItem('selectedUrl', url);
    router.push(`/choesesuggest?name=${name}`);
};
const categories = ref([

]);


</script>
  
<style>
/* Các kiểu CSS tùy chỉnh có thể được thêm ở đây nếu cần */
</style>
  
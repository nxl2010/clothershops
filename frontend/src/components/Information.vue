<template>
    <div class="grid grid-cols-1 lg:grid-cols-4 gap-2">
        <!-- Cột chiếm 3/4 trang -->
        <div class="col-span-3 lg:col-span-3 py-4">
            <div class="bg-white p-8 rounded-lg shadow-md">
                <h1 class="text-2xl font-semibold mb-6 text-center">Thông tin cá nhân</h1>
                <div class="grid grid-cols-1 gap-6">
                    <div class="flex items-center">
                        <span class="text-sm font-medium text-gray-700 w-24">Tên đăng nhập:</span>
                        <p class="text-gray-900 font-semibold">{{ myUser.userName }}</p>
                    </div>
                    <div class="flex items-center">
                        <span class="text-sm font-medium text-gray-700 w-24">Họ và tên:</span>
                        <input class="text-gray-900 font-semibold bg-gray-100 p-2 border border-gray-300 rounded"
                          v-model="myUser.fullName" />
                    </div>
                    <div class="flex items-center">
                        <span class="text-sm font-medium text-gray-700 w-24">Email:</span>
                        <input class="text-gray-900 font-semibold bg-gray-100 p-2 border border-gray-300 rounded"
                            v-model="myUser.email" />
                    </div>
                    <div class="flex items-center">
                        <span class="text-sm font-medium text-gray-700 w-24">Số điện thoại:</span>
                        <input class="text-gray-900 font-semibold bg-gray-100 p-2 border border-gray-300 rounded"
                            v-model="myUser.phoneNumber" />
                    </div>
                </div>
                <div class="flex justify-evenly">
                    <button @click="editMyUser()" class="mt-4 h-9 w-16 bg-orange-500 text-white rounded-sm ">Lưu</button>

                </div>
            </div>
         

        </div>

        <div class="col-span-3 lg:col-span-1 py-4">
            <div class="bg-white p-8 rounded-lg min-h-full flex flex-col items-center">
                <img src="" alt=""
                    class="rounded-full w-32 h-32 mx-auto mb-4">
                <button class="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700">
                    Chọn ảnh
                </button>
            </div>
        </div>

    </div>
</template>
<script setup>
import { ref, onMounted } from 'vue';
import apiRequest from '../service/api.js';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

const myUser = ref([]);
    const editMyUser = async () => {
        try {
        const response = await apiRequest('PUT', 'http://localhost:8080/api/v2/user/myuser', {
        // Truyền thông tin cập nhật từ biến myUser
        userName: myUser.value.userName,
        fullName: myUser.value.fullName,
        email: myUser.value.email,
        phoneNumber: myUser.value.phoneNumber,
        // Thêm các trường khác cần cập nhật
        });
        toast.success("Bạn đã cập nhật thành công");

    } catch (error) {
        console.error('Lỗi khi cập nhật thông tin người dùng:', error);
    }
    }
const fetchInformation  = async () => {
  try {
    const response = await apiRequest('GET', 'http://localhost:8080/api/v2/user/myuser');
    myUser.value = response.data;
  } catch (error) {
    console.error('Error fetching categories:', error);
  }
};

onMounted(() => {
  fetchInformation();
});
</script>
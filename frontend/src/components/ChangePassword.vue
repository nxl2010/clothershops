<template>
  <div class="min-h-fit flex items-center justify-center">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
      <h1 class="text-2xl font-semibold mb-6">Đổi mật khẩu</h1>
      <form @submit.prevent="updatePassword">
        <div class="mb-4">
          <label  class="block text-sm font-medium text-gray-700">Mật khẩu cũ</label>
          <input type="password" v-model="oldPassword" class="mt-1 p-2 w-full border rounded focus:ring focus:ring-blue-200" />
          <span v-if="error" class="text-red-500 text-sm">{{ error }}</span>
        </div>
        <div class="mb-4">
          <label class="block text-sm font-medium text-gray-700">Mật khẩu mới</label>
          <input type="password" v-model="newPassword" class="mt-1 p-2 w-full border rounded focus:ring focus:ring-blue-200" />
          <!-- <span v-if="passwordNotStrong" class="text-red-500 text-sm">Mật khẩu chưa đủ mạnh</span> -->
        </div>
        <div class="mb-6">
          <label class="block text-sm font-medium text-gray-700">Xác nhận mật khẩu mới</label>
          <input type="password" v-model="confirmPassword" class="mt-1 p-2 w-full border rounded focus:ring focus:ring-blue-200" />
        </div>
        <button type="submit" class="bg-blue-500 text-white rounded p-2 w-full hover:bg-blue-600">Đổi mật khẩu</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import {ref, onMounted} from "vue";
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import apiRequest from '../service/api';
import router from "../router";


const oldPassword = ref("");
const newPassword = ref("");
const error = ref(null);

const updatePassword = async () => {
    try {
    const response = await apiRequest('PUT', 'http://localhost:8080/api/v2/user/changepassword', {
      // Truyền thông tin cập nhật từ biến myUser
      oldPassword:  oldPassword.value,
      newPassword: newPassword.value
    });
    toast.success("Bạn đã cập nhật thành công");
    router.push('/logout');
  } catch (err) {
        if (err.response) {
            //Kiểm tra lỗi từ sever
            error.value = err.response.data; 
        } else if (err.request) {
            error.value = "No response received from the server.";
            
        } else {
            error.value = "An error occurred while making the request.";
        }
   
    }
}


</script>

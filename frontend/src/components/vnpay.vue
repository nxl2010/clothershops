<template>
  <div class="min-h-screen bg-gray-100 flex flex-col justify-center items-center">
    <div class="bg-white p-8 rounded-lg shadow-lg">
      <h1 class="text-2xl font-semibold mb-4">{{ paymentStatus }}</h1>
      <div class="flex justify-between">
        <router-link to="/" class="px-4 py-2 bg-gray-300 rounded-lg text-white">Trang chủ</router-link>
      </div>
    </div>
  </div>
  
</template>
  
<script setup>
import { ref } from 'vue';
import apiRequest from '../service/api';

const paymentStatus = ref('');
const putPayment = async (code) => {
  try {
    const response = await apiRequest('PUT', `http://localhost:8080/api/v2/order/pay/${code}?isPaid=true`);
    console.log('Cập nhật thanh toán', response.data);
  } catch (error) {
    console.error('Lỗi thanh toán:', error);
  }
};
// Lấy tham số từ URL
const params = new URLSearchParams(window.location.search);
// Kiểm tra tham số vnp_ResponseCode để xác định kết quả thanh toán
const responseCode = params.get('vnp_ResponseCode');
const code = params.get('vnp_OrderInfo');
if (responseCode === '00') {
  paymentStatus.value = 'Thanh toán thành công';
  putPayment(code);
  console.log(code);
} else {
  paymentStatus.value = 'Thanh toán thất bại';
}
</script>
  
<style>
/* Thêm các tùy chỉnh CSS của bạn tại đây nếu cần */
</style>
  
<template>
    <div class="container mx-auto mt-8">
        <h1 class="text-2xl font-bold mb-4">Thông tin giao hàng</h1>

        <!-- Form nhập liệu -->
        <form @submit.prevent="addPaymennt" class="mb-4">
            <div class="mb-4">
                <label for="customerName" class="block text-sm font-bold mb-2">Tên khách hàng</label>
                <input type="text" id="customerName" v-model="newShippingInfo.name" class="border rounded-md p-2 w-full"
                    required>
            </div>

            <div class="mb-4">
                <label for="phoneNumber" class="block text-sm font-bold mb-2">Số điện thoại</label>
                <input type="text" id="phoneNumber" v-model="newShippingInfo.sdt" class="border rounded-md p-2 w-full"
                    required>
            </div>

            <div class="mb-4">
                <label for="address" class="block text-sm font-bold mb-2">Địa chỉ</label>
                <input type="text" id="address" v-model="newShippingInfo.address" class="border rounded-md p-2 w-full"
                    required>
            </div>

            <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded">Thêm thông
                tin giao hàng</button>
        </form>

        <!-- Danh sách thông tin giao hàng -->
        <ul class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            <!-- Các thông tin giao hàng đã có -->
            <li v-for="(info, index) in shippingInfos" :key="index" class="bg-white p-4 rounded shadow-md">
                <h2 class="text-lg font-bold mb-2">{{ info.name }}</h2>
                <p>Số điện thoại: {{ info.sdt }}</p>
                <p>Địa chỉ: {{ info.address }}</p>
            </li>
        </ul>
    </div>
</template>
  
<script setup>
import { ref, onMounted } from 'vue';
import apiRequest from '../service/api';
// Dữ liệu các thông tin giao hàng
const shippingInfos = ref([

]);
const fetchAddress = async () => {
    try {
        const response = await apiRequest('GET', 'http://localhost:8080/api/v2/shipinfor');
        shippingInfos.value = response.data;
    } catch (error) {
        console.error('Lỗi tải:', error);
    }
};

const addPaymennt = async () => {

    try {
        const response = await apiRequest('POST', 'http://localhost:8080/api/v2/shipinfor', {
            "name": newShippingInfo.value.name,
            "address": newShippingInfo.value.address,
            "sdt": newShippingInfo.value.sdt
        });
        if (response.status === 200) {
            fetchAddress();
        } else {
            successMessageVisible.value = true;
        }

    } catch (error) {
    console.error('Error adding category:', error);
}

    // Xóa danh sách sản phẩm được chọn sau khi thanh toán
};
// Dữ liệu mới để thêm thông tin giao hàng
const newShippingInfo = ref({
    name: '',
    sdt: '',
    address: ''
});
onMounted(() => {
   fetchAddress();
});
// Hàm thêm thông tin giao hàng

</script>
  
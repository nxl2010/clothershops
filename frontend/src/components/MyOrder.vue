<!-- OrdersPage.vue -->
<template>
    <div>
        <nav class="bg-blue-500 p-4 text-white">
            <ul class="flex">
                <li v-for="(statusItem, index) in orderStatus" :key="index" class="mr-6">
                    <router-link :to="{ name: 'orders', query: { status: statusItem.value } }" class="hover:underline">
                        {{ statusItem.label }}
                    </router-link>
                </li>
            </ul>
        </nav>
        <div class="overflow-x-auto mt-3">
            <div class="overflow-x-auto">
                <table v-if="orders.length > 0" class="min-w-full border border-gray-300">
                    <thead>
                        <tr class="bg-gray-100">
                            <th class="py-2 px-4 border-b">Tên</th>
                            <th class="py-2 px-4 border-b">Ngày đặt hàng</th>
                            <th class="py-2 px-4 border-b">Tổng đơn</th>
                            <th class="py-2 px-4 border-b">Tình trạng thanh toán</th>
                            <th class="py-2 px-4 border-b">Tình trạng đơn hàng</th>
                            <th class="py-2 px-4 border-b">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(order, index) in orders" :key="index" class="border-b">
                            <td class="py-2 px-4">{{ order.name }}</td>
                            <td class="py-2 px-4">{{ order.time }}</td>
                            <td class="py-2 px-4">{{ order.totalAmount }}</td>
                            <td class="py-2 px-4">{{ order.orderStatus }}</td>
                            <td class="py-2 px-4">{{ order.orderShip }}</td>
                            <td class="py-2 px-4">
                                <button @click="viewOrderDetails(order)"
                                    class="bg-blue-500 text-white px-4 py-2 rounded">Xem chi tiết</button>
                                <button @click="payForOrder(order)" class="bg-green-500 text-white px-4 py-2 rounded">Thanh
                                    toán</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div v-else class="text-center py-8">
                    <p>Không có đơn hàng nào.</p>
                </div>
            </div>



        </div>
    </div>
</template>
<script setup>
import { ref, onMounted, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import apiRequest from '../service/api.js';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

const orderStatus = ref([
    { label: 'Chờ xử lý', value: 'CONFIRMING' },
    { label: 'Đang giao hàng', value: 'DELIVERED' },
    { label: 'Hoàn thành', value: 'completed' },
    { label: 'Đã hủy', value: 'CANCELLED' },
]);

const orders = ref([]);
const $route = useRoute();

// Hàm lấy dữ liệu từ API
const fetchInformation = async () => {
    try {
        const status = $route.query.status || 'DELIVERED'; // Lấy giá trị từ query param hoặc mặc định là 'DELIVERED'
        const response = await apiRequest('GET', `http://localhost:8080/api/v2/order/shipstatus?status=${status}`);
        orders.value = response.data;
    } catch (error) {
        console.error('Error fetching categories:', error);
        orders.value = [];
    }
};

// Gọi hàm fetchInformation khi component được mounted hoặc khi query param thay đổi
onMounted(fetchInformation);
watchEffect(() => {
    fetchInformation();
});
</script>


  
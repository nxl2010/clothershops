<template>
    <div>
        <div class="flex mb-4">
            <button @click="showRecommendations" :class="{ 'bg-blue-500 text-white': activeTab === 'recommendations' }"
                class="mr-4 px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300">
                Có thể bạn thích
            </button>
            <button @click="showBestSellers" :class="{ 'bg-blue-500 text-white': activeTab === 'bestSellers' }"
                class="px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300">
                Sản phẩm bán chạy
            </button>
        </div>

        <!-- Nội dung -->
        <div v-show="activeTab === 'recommendations'">
            <!-- Nội dung Có thể bạn thích -->
            <div class="grid grid-cols-2 md:grid-cols-4 gap-4">
                <!-- Lặp qua danh sách sản phẩm đề xuất -->
                <div v-for="product in recommendedProducts" :key="product.id" class="bg-white p-4 shadow-md rounded-md">
                    <!-- Ảnh sản phẩm -->
                    <img :src="product.image" alt="Product Image" class="w-full h-40 object-cover rounded-md mb-2">

                    <!-- Tên sản phẩm -->
                    <p class="text-sm font-medium text-gray-800 mb-1">{{ product.name }}</p>

                    <!-- Giá sản phẩm -->
                    <p class="text-sm font-bold text-blue-500">{{ product.price }}</p>
                </div>
            </div>
        </div>
        <div v-show="activeTab === 'bestSellers'">
            <!-- Nội dung Sản phẩm bán chạy -->
            <div class="grid grid-cols-2 md:grid-cols-4 gap-4">
                <!-- Lặp qua danh sách sản phẩm đề xuất -->
                <div v-for="product in recommendedProducts" :key="product.id" class="bg-white p-4 shadow-md rounded-md">
                    <!-- Ảnh sản phẩm -->
                    <img :src="product.image" alt="Product Image" class="w-full h-40 object-cover rounded-md mb-2">

                    <!-- Tên sản phẩm -->
                    <p class="text-sm font-medium text-gray-800 mb-1">{{ product.name }}</p>

                    <!-- Giá sản phẩm -->
                    <p class="text-sm font-bold text-blue-500">{{ product.price }}</p>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script setup>
import { ref } from 'vue';

const activeTab = ref('recommendations');

const showRecommendations = () => {
    activeTab.value = 'recommendations';
};
const recommendedProducts = ref([
    {
        id: 1,
        name: 'Sản phẩm 1',
        price: '$19.99',
        image: 'https://pos.nvncdn.com/a36e05-151378/ps/20231213_xZq6sn8vzU.jpeg',
    },
    {
        id: 2,
        name: 'Sản phẩm 2',
        price: '$29.99',
        image: 'https://pos.nvncdn.com/a36e05-151378/ps/20231127_mSjc2QbAOv.jpeg',
    },
    {
        id: 3,
        name: 'Sản phẩm 3',
        price: '$39.99',
        image: 'https://pos.nvncdn.com/a36e05-151378/ps/20231223_sNjEk58hBN.jpeg',
    },
    {
        id: 4,
        name: 'Sản phẩm 4',
        price: '$49.99',
        image: 'https://pos.nvncdn.com/a36e05-151378/ps/20231016_W7iTJKNNLw.jpeg',
    },
]);
const showBestSellers = () => {
    activeTab.value = 'bestSellers';
};
</script>
  
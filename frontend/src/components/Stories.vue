<template>
    <div class="max-w-7xl mt-8 border-t-2 border-b-2 pb-7 pt-2">
        <div class="flex items-center mb-2">
            <div class="p-2 flex justify-between items-center ml-3 ">
                <div class="flex">
                    <i class="fa-solid fa-mars p-1 text-3xl" style="color: #16e3e0;"></i>
                    <h2 class="text-4xl font-normal ml-1">Áo</h2>
                </div>
            </div>

            <!-- Thanh nav -->
            <div class="flex">
                <button class="text-gray-600 hover:text-gray-800 px-3 py-2 focus:outline-none text-xl">Áo sơ mi</button>
                <button class="text-gray-600 hover:text-gray-800 px-3 py-2 focus:outline-none text-xl">Áo thun</button>
                <button class="text-gray-600 hover:text-gray-800 px-3 py-2 focus:outline-none text-xl">Giày</button>
            </div>
        </div>

        <div class="grid grid-cols-2 lg:grid-cols-4 gap-5 px-5">
            <div v-for="(item, index) in data" :key="index" class="col-span-1 border-slate-200 border-2 rounded-xl">
                <div class="bg-white h-80 rounded-xl overflow-hidden px-2 py-2">
                    <img class="h-full w-full object-cover rounded-xl" :src="item.image" alt="">
                </div>
                <p class="font-medium text-center mt-2">{{ item.title }}</p>
                <p class="text-center">{{ item.price }} đ</p>
                <div class="flex justify-center mt-2 py-2">
                    <button @click="handleBuyNow" class="bg-blue-500 text-white px-3 py-1 rounded-md mx-2">Mua
                        Nhanh</button>
                        <router-link to="/product">
                            <button @click="handleViewDetails" class="bg-gray-500 text-white px-3 py-1 rounded-md mx-2">Xem Chi
                        Tiết</button>
                        </router-link>
                    
                </div>
            </div>
        </div>



    </div>
</template>
  
<script setup>
import { ref } from 'vue';
const data = [
    {
        image: 'https://pos.nvncdn.com/a36e05-151378/ps/20231213_iAl9k6EuKP.jpeg',
        title: 'Áo sơ mi Bamboo NX25',
        price: '199000'
    },
    {
        image: 'https://pos.nvncdn.com/a36e05-151378/ps/20231213_xZq6sn8vzU.jpeg',
        title: 'Áo sơ mi Bamboo NX12',
        price: '299000'
    },
    {
        image: 'https://pos.nvncdn.com/a36e05-151378/ps/20240111_PJhfgzwRKJ.jpeg',
        title: 'Áo sơ mi màu đỏ NX21',
        price: '199000'
    },
    {
        image: 'https://pos.nvncdn.com/a36e05-151378/ps/20231127_mSjc2QbAOv.jpeg',
        title: 'Áo sơ mi màu bơ NX32',
        price: '299000'
    },
    {
        image: 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/20211127_Han_Hyo-joo_%ED%95%9C%ED%9A%A8%EC%A3%BC_BH_Entertainment_%285%29.jpg/800px-20211127_Han_Hyo-joo_%ED%95%9C%ED%9A%A8%EC%A3%BC_BH_Entertainment_%285%29.jpg',
        title: 'Hello, my friend'
    }
];
</script>
  
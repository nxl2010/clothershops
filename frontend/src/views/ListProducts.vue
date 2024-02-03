<template>
  <div>
    <!-- Bộ loojc  -->
    <div class="bg-gray-200 p-3 rounded-md mb-4">
      <label class="mr-4 text-2xl"> QUẦN</label>
      <label for="typeFilter" class="mr-2">Loại quần:</label>
      <select id="typeFilter" v-model="selectedType" class="p-2 border border-gray-300 rounded-md">
        <option value="">Tất cả</option>
        <option value="shirt">Áo sơ mi</option>
        <option value="tshirt">Áo thun</option>
      </select>

      <label for="priceFilter" class="mx-4">Giá tiền:</label>
      <select id="priceFilter" v-model="selectedPriceRange" class="p-2 border border-gray-300 rounded-md">
        <option value="">Tất cả</option>
        <option value="under200000">bé hơn 200000</option>
        <option value="200000-500000">200000 - 500000</option>
      </select>

      <label for="colorFilter" class="mx-4">Màu sắc:</label>
      <select id="colorFilter" v-model="selectedcolorRange" class="p-2 border border-gray-300 rounded-md">
        <option value="">Tất cả</option>
        <option value="red">Đỏ</option>
        <option value="yellow">X</option>
      </select>

      <label for="sortFilter" class="mx-4">Sắp xếp theo:</label>
      <select id="sortFilter" v-model="selectedSort" class="p-2 border border-gray-300 rounded-md">
        <option value="desc">Giá giảm dần</option>
        <option value="asc">Giá tăng dần</option>
      </select>
    </div>

    <!-- Hiển thị sản phẩm  -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-5 px-5">
      <div v-for="(item, index) in data" :key="index" class="col-span-1 border-slate-200 border-2 rounded-xl">
        <div class="bg-white h-80 rounded-xl overflow-hidden px-2 py-2">
          <img class="h-full w-full object-cover rounded-xl" :src="item.image" alt="">
        </div>
        <p class="font-medium text-center mt-2">{{ item.title }}</p>
        <p class="text-center">{{ item.price }}</p>
        <div class="flex justify-center mt-2 py-2">
          <button @click="handleBuyNow" class="bg-blue-500 text-white px-3 py-1 rounded-md mx-2">Mua Nhanh</button>
          <button @click="handleViewDetails" class="bg-gray-500 text-white px-3 py-1 rounded-md mx-2">Xem Chi
            Tiết</button>
        </div>
      </div>
    </div>

    <!-- Phân trang -->
    <div class=" flex justify-center my-4">
      <nav aria-label="Page navigation example">
        <ul class="flex items-center -space-x-px h-8 text-sm">
          <li>
            <a href="#"
              class="flex items-center justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
              <span class="sr-only">Previous</span>
              <svg class="w-2.5 h-2.5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                viewBox="0 0 6 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="M5 1 1 5l4 4" />
              </svg>
            </a>
          </li>
          <!-- Add your pagination logic here -->
          <!-- For example, you can dynamically generate the page numbers based on the total number of pages -->
          <li v-for="pageNumber in totalPages" :key="pageNumber">
            <a href="#"
              class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">{{
                pageNumber }}</a>
          </li>
          <li>
            <a href="#"
              class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
              <span class="sr-only">Next</span>
              <svg class="w-2.5 h-2.5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                viewBox="0 0 6 10">
                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="m1 9 4-4-4-4" />
              </svg>
            </a>
          </li>
        </ul>
      </nav>
    </div>

  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';

const category = ref('');
const products = ref([]);
const selectedType = ref('');
const selectedPriceRange = ref('');
const selectedcolorRange = ref('');
const selectedSort = ref('desc');

const data = [
  {
    image: 'https://pos.nvncdn.com/a36e05-151378/ps/20240127_y1TtzegIWT.jpeg',
    title: 'Quần âu nam đen',
    price: '179000'
  },
  {
    image: 'https://pos.nvncdn.com/a36e05-151378/ps/20230807_Rmu4zpn3HQ.webp',
    title: 'Quần short nam đen',
    price: '199000'
  },
  {
    image: 'https://pos.nvncdn.com/a36e05-151378/ps/20230830_9bHZqmf83L.jpeg',
    title: 'Quần âu nam ghi',
    price: '249000'
  },
  {
    image: 'https://pos.nvncdn.com/a36e05-151378/ps/20230703_cgqILawrLU.jpeg',
    title: 'Quần jean nam trẻ trung',
    price: '299000'
  },
 
];

// Simulating data fetch for testing
onMounted(() => {
  category.value = 'Test Category';
  products.value = [
    { id: 1, name: 'Product 1', type: 'shirt', price: 150000 },
    { id: 2, name: 'Product 2', type: 'tshirt', price: 250000 },
    { id: 3, name: 'Product 3', type: 'shirt', price: 180000 },
  ];
});

// Pagination logic
const totalPages = ref(5); // Set the total number of pages based on your data

</script>

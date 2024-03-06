<template>
    <div class="max-w-7xl mt-8 border-t-2 border-b-2 pb-7 pt-2">
        <div class="flex items-center mb-2 ml-4">
            <div class="p-2 flex justify-between items-center ml-3 ">
                <div class="flex">
                    <h2 class="text-4xl font-normal ml-1">{{ category }}</h2>
                </div>
            </div>

            <!-- Thanh nav -->

        </div>

        <div class="grid grid-cols-2 lg:grid-cols-4 gap-5 px-5">
            <div v-for="(product, index) in products" :key="index" class="col-span-1 border-slate-200 border-2 rounded-xl">
                <div class="bg-white h-80 rounded-xl overflow-hidden px-2 py-2">
                    <img class="h-full w-full object-cover rounded-xl" :src="product.url" alt="">
                </div>
                <p class="font-medium text-center mt-2">{{ product.name }}</p>
                <p class="text-center">{{ product.price }} đ</p>
                <div class="flex justify-center mt-2 py-2">
                    <button @click="handleBuyNow(product.code)" class="bg-blue-500 text-white px-3 py-1 rounded-md mx-2">Mua
                        Nhanh</button>

                    <button @click="handleViewDetails(product.code)"
                        class="bg-gray-500 text-white px-3 py-1 rounded-md mx-2">Xem Chi
                        Tiết</button>

                </div>
            </div>
        </div>



    </div>
</template>
  
<script setup>
import { ref, onMounted, defineProps } from 'vue';
import apiRequest from '../service/api.js';
import { toast } from 'vue3-toastify';
import { useRouter } from 'vue-router';

const router = useRouter();
import 'vue3-toastify/dist/index.css';
const props = defineProps(['codeCategory']);
const category = ref('');
const fetchCategories = async(code) =>{
    try {
        const response = await apiRequest('GET', `http://localhost:8080/api/v2/category/${code}`);
       category.value = response.data;
    } catch (error) {
        console.error('Error fetching categories:', error);
    }
}
console.log(props.codeCategory);
const products = ref([
]);
const fetchProducts = async (categoryId) => {
    try {
        const response = await apiRequest('GET', `http://localhost:8080/api/v2/product/category/${categoryId}?size=4`);
        products.value = response.data.content;
    } catch (error) {
        console.error('Error fetching categories:', error);
    }
};

const handleViewDetails = (products) => {
    console.log(products);
    router.push(`/product/${products}`);
};

onMounted(() => {
    fetchProducts(props.codeCategory);
    fetchCategories(props.codeCategory);
});
</script>
  
<template>
    <div class="background-container">
        <div class="flex h-full items-center justify-center">
          
            <ul class="flex space-x-7 h-96 justify-center mx-2">
                <li v-for="category in  categorys " :key="category.id">
                    <router-link :to="`/about/${ category.entry }`" style="text-decoration: none;">
                        <div class="bg-white h-full w-72 flex flex-col items-center rounded-xl p-4">
                            <div class="mt-4 flex items-center justify-center mb-4">
                                <img class="h-28 w-28 rounded-full" :src=" category.image " alt="">
                            </div>
                            <div class="rounded-xl" :style="{ backgroundColor: category.color }">
                                <h3 class="mx-2 text-gray-200 font-medium">{{ category.entry }}</h3>
                            </div>
                            <h3 class="text-lg font-semibold">{{ category.name }}</h3>
                            <p class=" mt-2 text-gray-400 text-center">{{ category.decribe }}</p>
                        </div>
                    </router-link>
                </li>
            </ul>



        </div>
    </div>
    
    <stories></stories>
    <stories></stories>

</template>
<script setup>

import { ref, onMounted } from 'vue';
import stories from '../components/Stories.vue'

import axios from 'axios';
const categorys = ref([]);
// Function to fetch quotes from API
const fetchCategory = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/v3/category'); // Adjust the URL as needed
        categorys.value = response.data;
    } catch (error) {
        console.error('Error fetching quotes:', error);
    }
};

// Fetch quotes when the component is mounted
onMounted(() => {
    fetchCategory();
});
</script>
<style>
.background-container {
    z-index: 1;
    width: 100%;
    height: 80vh;
    background-image: url('https://pos.nvncdn.com/a36e05-151378/bn/20240103_oopNFzjh.gif');
    background-size: cover;
    background-position: center;
}

</style>

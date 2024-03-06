<template>
    <div class="background-container">
    </div>

    <div v-for="category in categorys" :key="category.id">
        <stories :codeCategory="category.code"></stories>
    </div>
</template>
<script setup>

import { ref, onMounted } from 'vue';
import stories from '../components/Stories.vue'

import apiRequest from '../service/api';
const categorys = ref([]);

const fetchCategory = async () => {
    try {
        const response = await apiRequest('GET', 'http://localhost:8080/api/v2/category');
        categorys.value = response.data;
    } catch (error) {
        console.error('Error fetching categories:', error);
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
    background-image: url('D:\DATN\frontend\src\assets\image\416137794_662729392731038_5347396210539475085_n.jpg');
    background-size: cover;
    background-position: center;
}
</style>

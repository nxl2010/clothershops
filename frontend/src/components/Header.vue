<template>
    <div class="container mx-auto flex justify-between items-center">
        <a href="http://localhost:5173/" class="text-2xl font-semibold beautiful-text">NXLZero</a>
        <nav class="hidden md:block">
            <ul class="flex space-x-12">
                <li><a href="http://localhost:5173/" class="hover:text-gray-300 font-medium text-sm">Trang chủ</a></li>
                <li><a href="http://localhost:5173/about/Cooking" class="hover:text-gray-300 font-medium text-sm">Sản phẩm</a></li>
                <li><a href="http://localhost:5173/about/Hust" class="hover:text-gray-300 font-medium text-sm">Thử đồ</a></li>
                <li><a href="http://localhost:5173/about/Code" class="hover:text-gray-300 font-medium text-sm">Khuyến mãi</a></li>
                <!-- <li><a href="http://localhost:5173/about/Life" class="hover:text-gray-300 font-medium text-sm">Life</a></li> -->
            </ul>
        </nav>
        <nav class="hidden md:flex">
            <ul class="flex space-x-4">
                <router-link to="/search">
                    <i class="fa-solid fa-magnifying-glass p-1 text-lg"></i>
                </router-link>
                <router-link to="/carts"> 
                    <i class="fa-solid fa-cart-shopping p-1 text-lg"></i>
                </router-link>
               
                <i class="fa-solid fa-user p-1 text-lg" @click="showModal" ></i>
            </ul>
        </nav>
        <nav class="md:hidden" :class="{ 'block': isMenuOpen, 'hidden': !isMenuOpen }">
            <ul class="flex flex-col space-y-2 text-center">
                <li><a href="http://localhost:5173/about/" class="hover:text-gray-300 font-medium text-sm">Home</a></li>
                <li><a href="http://localhost:5173/about/Cooking" class="hover:text-gray-300 font-medium text-sm">Cook</a></li>
                <li><a href="http://localhost:5173/about/Hust" class="hover:text-gray-300 font-medium text-sm">Hust</a></li>
                <li><a href="http://localhost:5173/about/Code" class="hover:text-gray-300 font-medium text-sm">Code</a></li>
                <li><a href="http://localhost:5173/about/Life" class="hover:text-gray-300 font-medium text-sm">Life</a></li>
            </ul>
        </nav>

        <div class="md:hidden">
            <i class="fas fa-bars text-lg cursor-pointer" @click="toggleMenu"></i>
        </div>
        <div v-if="isModalOpen" class="fixed top-0 left-0 w-full h-full flex items-center justify-center bg-opacity-50 bg-gray-800">
            <login @someEvent="closeNotification"></login>
        </div>
    </div>
   
</template>
  
<script setup>
import login from '../components/alert/login.vue'
import { ref } from 'vue';
import router from '../router';
const isMenuOpen = ref(false);
const isModalOpen = ref(false);
function showModal(){
    if(1){
        router.push("/myuser/information");
    }else{
        isModalOpen.value = !isModalOpen.value;
    }
   
}
function closeNotification(){
    isModalOpen.value = false;
}
function toggleMenu() {
    isMenuOpen.value = !isMenuOpen.value;
}
</script>
<style scoped>
.beautiful-text {
  font-size: 20px;
    font-weight: 700;
    letter-spacing: 3px;
  color: #333; /* Initial color */
  text-decoration: none;
  transition: color 0.3s ease-in-out; /* Add smooth transition */
}

.beautiful-text:hover {
  color: #21cfcf; /* Color on hover */
  transform: scale(1.05); /* Add a slight scale effect on hover */
}
</style>
  
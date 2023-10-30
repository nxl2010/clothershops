<template>
  <div class="bg-white p-4 rounded-md text-center relative">
    <i class="fa-solid fa-xmark absolute top-2 right-2 text-red-500 text-2xl"  @click="$emit('someEvent')"></i>
    <h2 class="text-2xl font-bold mb-4">Bạn chưa đăng nhập vào web</h2>
    <button
      @click="login"
      class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-2"
    >
      Đăng nhập
    </button>
    <button
      @click="register"
      class="bg-green-500 hover-bg-green-700 text-white font-bold py-2 px-4 rounded"
    >
      Đăng ký
    </button>
  </div>
</template>

<script setup>
import router from '../../router';

const login = () => {
    router.push('/login');
};

const register = () => {
    router.push('/signup');
};
</script>


<template>
  <div class="min-h-screen flex items-center justify-center bg-gradient-to-r from-blue-500 to-green-500">
    <div class="bg-white rounded-lg shadow-lg p-6 w-80 text-center">
      <h1 class="text-3xl font-semibold text-gray-800 mb-4">Thông báo</h1>
      <p class="text-gray-700">{{ message }}</p>
      <button
        @click="redirectToHome"
        class="mt-6 px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-700 focus:outline-none"
      >
        Trở về Trang Chủ
      </button>
    </div>
  </div>
</template>

  
  <script>
  export default {
    data() {
      return {
        message: "BYE BYE",
      };
    },
    async mounted() {
      localStorage.removeItem("token");
      await this.delay(1000); 
      this.$router.push("/login");
    },
    methods: {
      delay(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
      },
    },
  };
  </script>
  
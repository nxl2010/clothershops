

<template>
  <div class="grid grid-cols-1 lg:grid-cols-4 ">
    <!-- Cột chiếm 3/4 trang -->
    <div class="col-span-3 lg:col-span-1 py-4 pl-4 pr-1">
      <Slidebar></Slidebar>

    


    </div>

    <!-- Cột chiếm 1/4 trang là sidebar-->
    <div class="hidden lg:block lg:col-span-3 py-4 pr-4">
      <div class="bg-gray-100 py-3 px-4 rounded-lg shadow-md">
          <router-view></router-view>
      </div>
    </div>
  </div>

</template>
<script setup>

import Slidebar from '../components/Slidebar.vue';
import { useRoute } from 'vue-router';
import ChangePassword from '../components/ChangePassword.vue';
import Information from '../components/Information.vue';

const route = useRoute();
const path = route.params.id;
</script>

<style></style>

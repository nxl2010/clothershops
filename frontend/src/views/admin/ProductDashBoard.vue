<!-- NavbarLayout.vue -->
<template>
    <div>
        <nav class="rounded-lg p-4 sm:block hidden">
            <ul class="flex">
                <li v-for="link in navLinks" :key="link.to" class="px-1">
                    <router-link :to="link.to" exact-active-class="active" class="px-2 py-2 rounded-xl bg-slate-300 hover:bg-gray-300">
                        {{ link.label }}
                    </router-link>
                </li>
            </ul>
        </nav>

        <router-view></router-view>
    </div>
</template>
  
<script setup>
import {ref} from 'vue';
const navLinks = ref([
    { to: '/admin/product/supplier' ,label: 'Nhà cung cấp'},
    { to: '/admin/product/category', label: 'Danh mục sản phẩm'},
    { to: '/admin/product/allproduct', label: 'Danh sách sản phẩm'},
    { to: '/admin/product/product-reviews', label: 'Đánh giá'},
    { to: '/admin/product/import', label: 'Nhập kho'},
    
    // Thêm các liên kết khác nếu cần
]);

const active = 'active';
</script>
  
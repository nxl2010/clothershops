<!-- NavbarLayout.vue -->
<template>
    <div>
        <!-- Navbar -->
        <nav>
            <ul>
                <li><router-link to="/product">Danh sách sản phẩm</router-link></li>
                <li><router-link to="/product/create">Thêm sản phẩm</router-link></li>
                <!-- Thêm các liên kết khác nếu cần -->
            </ul>
        </nav>

        <!-- Hiển thị component con -->
        <router-view></router-view>
    </div>
</template>
  
<script>
export default {
    // Các phần khác của layout (CSS, Script, ...)
};
</script>
  
<style scoped>
/* CSS cho NavbarLayout */
</style>
  
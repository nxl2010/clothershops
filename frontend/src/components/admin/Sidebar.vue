<template>
<div id="docs-sidebar" >
  <!-- Header Section -->
    <div class="p-2.5 mt-1 flex items-center rounded-md bg-blue-500">
      <i class="bi bi-app-indicator px-2 py-1 bg-blue-700 rounded-md text-white"></i>
      <h1 class="text-[15px] ml-3 text-xl font-bold text-white">NXLZero</h1>
      <i class="bi bi-x ml-20 cursor-pointer lg:hidden text-white" @click="openBar"></i>
    </div>
    <hr class="my-2 text-gray-600">

    <!-- Navigation Section -->
    <div>
      <div v-for="(item, index) in navigationItems" :key="index"
        class="p-2.5 mt-2 flex items-center rounded-md px-4 duration-300 cursor-pointer"
        :class="{ 'hover:bg-blue-500': index !== 0 }">
        <i :class="item.iconClass + ' text-gray-600'"></i>
        <span class="text-[15px] ml-4 text-gray-800">{{ item.label }}</span>
      </div>
      <hr class="my-4 text-gray-600">

      <!-- User Submenu -->
      <div class="p-2.5 mt-2 flex items-center rounded-md px-4 duration-300 cursor-pointer hover:bg-blue-500">
        <i class="fa-solid fa-users text-gray-600"></i>
        <div class="flex justify-between w-full items-center" @click="toggleSubMenu('user')">
          <span class="text-[15px] ml-4 text-gray-800">User</span>
          <span class="text-sm rotate-180" id="arrow">
            <i class="bi bi-chevron-down text-gray-600"></i>
          </span>
        </div>
      </div>
      <div class="leading-7 text-left text-sm font-thin mt-2 w-4/5 mx-auto" v-show="isUserOpen">

        <div v-for="(submenu, index) in userSubmenus" :key="index">
          <router-link :to="submenu.routerLink">
            <div class="cursor-pointer p-1 hover:bg-gray-200 rounded-md mt-1">
              {{ submenu.label }}
            </div>
          </router-link>
        </div>
      </div>


      <!-- Orders Submenu -->
      <div class="p-2.5 mt-2 flex items-center rounded-md px-4 duration-300 cursor-pointer hover:bg-blue-500">
        <i class="fas fa-file-alt text-gray-600"></i>
        <div class="flex justify-between w-full items-center" @click="toggleSubMenu('orders')">
          <span class="text-[15px] ml-4 text-gray-800">Đơn hàng</span>
          <span class="text-sm rotate-180" id="arrow">
            <i class="bi bi-chevron-down text-gray-600"></i>
          </span>
        </div>
      </div>
      <div class="leading-7 text-left text-sm font-thin mt-2 w-4/5 mx-auto" v-show="isOrdersOpen">
        <div v-for="(submenu, index) in ordersSubmenus" :key="index">
          <router-link :to="submenu.routerLink">
            <div class="cursor-pointer p-1 hover:bg-gray-200 rounded-md mt-1">
              {{ submenu.label }}
            </div>
          </router-link>
        </div>
      </div>


      <!-- Warehouse Submenu -->
      <div class="p-2.5 mt-2 flex items-center rounded-md px-4 duration-300 cursor-pointer hover:bg-blue-500">
        <i class="fas fa-warehouse text-gray-600"></i>
        <div class="flex justify-between w-full items-center" @click="toggleSubMenu('warehouse')">
          <span class="text-[15px] ml-4 text-gray-800">Kho hàng</span>
          <span class="text-sm rotate-180" id="arrow">
            <i class="bi bi-chevron-down text-gray-600"></i>
          </span>
        </div>
      </div>
      <div class="leading-7 text-left text-sm font-thin mt-2 w-4/5 mx-auto" v-show="isWarehouseOpen">

        <div v-for="(submenu, index) in warehouseSubmenus" :key="index">
          <router-link :to="submenu.routerLink">
            <div class="cursor-pointer p-1 hover:bg-gray-200 rounded-md mt-1">
              {{ submenu.label }}
            </div>
          </router-link>
        </div>

      </div>


      <!-- Logout Section -->
      <div class="p-2.5 mt-3 flex items-center rounded-md px-4 duration-300 cursor-pointer hover:bg-blue-500">
        <i class="bi bi-box-arrow-in-right text-gray-600"></i>
        <span class="text-[15px] ml-4 text-gray-800">Logout</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchText: '',
      isUserOpen: false,
      isOrdersOpen: false,
      isWarehouseOpen: false,
      navigationItems: [
        { label: 'Trang chủ', iconClass: 'fa-solid fa-house' }
      ],
      userSubmenus: [
        { label: 'Tất cả người dùng', routerLink: '/admin/user' },
        { label: 'Phân quyền', routerLink: '/admin/user/role' }

      ],
      ordersSubmenus: [
        { label: 'Tất cả đơn hàng', routerLink: '/admin/allproducts' }
      ],
      warehouseSubmenus: [
        { label: 'Nhà cung cấp', routerLink: '/admin/supplier' },
        { label: 'Nhập kho', routerLink: '/admin/enter-inventory' },
        { label: 'Danh sách sản phẩm', routerLink: '/admin/products' }
      ]


    };
  },
  methods: {
    openBar() {
      // Implement your open bar logic here
    },
    handleSearch() {
      // Implement your search logic here
    },
    toggleSubMenu(submenu) {
      if (submenu === 'user') {
        this.isUserOpen = !this.isUserOpen;
        this.isOrdersOpen = false;
        this.isWarehouseOpen = false;
      } else if (submenu === 'orders') {
        this.isOrdersOpen = !this.isOrdersOpen;
        this.isUserOpen = false;
        this.isWarehouseOpen = false;
      } else if (submenu === 'warehouse') {
        this.isWarehouseOpen = !this.isWarehouseOpen;
        this.isUserOpen = false;
        this.isOrdersOpen = false;
      }
    },
  },
};
</script>

<style scoped>
/* Add your CSS styles here */
</style>

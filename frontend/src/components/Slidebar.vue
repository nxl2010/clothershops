<template>
    <div>
        <aside id="logo-sidebar"
            class="top-18 left-0 z-40 w-64 h-screen transition-transform -translate-x-full sm:translate-x-0"
            aria-label="Sidebar">
            <div class="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800">
                <a href="https://flowbite.com/" class="flex items-center mb-5">
                    <img src="https://flowbite.com/docs/images/logo.svg" class="h-6 mr-3 sm:h-7" alt="Flowbite Logo" />
                    <span class="self-center text-xl font-semibold whitespace-nowrap dark:text-white">Nguyễn Xuân Lâm</span>
                </a>
                <ul class="space-y-2 font-medium">
                    <li>

                        <a href="#"
                            class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-200 dark:hover-bg-gray-700 group">
                            <span class="ml-3">Dashboard</span>
                        </a>
                    </li>


                    <li>
                        <router-link to="/myuser/information">
                            <div
                                class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-200 dark:hover-bg-gray-700 group">
                                <span class="ml-3 whitespace-nowrap">Thông tin cá nhân</span>
                            </div>
                        </router-link>

                    </li>
                    <li>
                        <router-link to="/myuser/changepassword">
                            <div
                                class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-200 dark:hover-bg-gray-700 group">
                                <span class="ml-3 whitespace-nowrap">Đổi mật khẩu</span>
                            </div>
                        </router-link>
                    </li>
                    <li>
                        <router-link to="/myuser/information">
                            <div
                                class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-200 dark:hover-bg-gray-700 group">
                                <span class="ml-3 whitespace-nowrap">Đơn hàng</span>
                            </div>
                        </router-link>
                    </li>
                    <li>
                        <router-link to="/myuser/information">
                            <div
                                class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-200 dark:hover-bg-gray-700 group">
                                <span class="ml-3 whitespace-nowrap">Khuyến mãi</span>
                            </div>
                        </router-link>
                    </li>
                    <li>
                        <a href="#"
                            class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-red-300 dark:hover-bg-gray-700 group">
                            <router-link to="/logout">
                                <span class="ml-3 whitespace-nowrap" >Logout</span>
                            </router-link>
                        
                        </a>
                    </li>
                </ul>
            </div>
        </aside>

    </div></template>
<script setup>


// Gọi hàm initializeData để khởi động dữ liệu khi component được mounted
</script>

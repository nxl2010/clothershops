<template>
    <div id="sidebar-mini"
        class="hs-overlay hs-overlay-open:translate-x-0 -translate-x-full transition-all duration-300 transform hidden fixed top-0 start-0 bottom-0 z-[60] w-20 bg-white border-e border-gray-200 lg:block lg:translate-x-0 lg:end-auto lg:bottom-0 [&::-webkit-scrollbar]:w-2 [&::-webkit-scrollbar-thumb]:rounded-full [&::-webkit-scrollbar-track]:bg-gray-100 [&::-webkit-scrollbar-thumb]:bg-gray-300 dark:[&::-webkit-scrollbar-track]:bg-slate-700 dark:[&::-webkit-scrollbar-thumb]:bg-slate-500 dark:bg-gray-800 dark:border-gray-700">
        <div class="flex flex-col justify-center items-center gap-y-2 py-2">
            <div class="mb-3">
                <div class="flex justify-end p-2">
                    <button type="button"
                        class="p-2 inline-flex justify-center items-center gap-x-2 rounded-lg border border-gray-200 bg-white text-gray-800 shadow-sm hover:bg-gray-50 disabled:opacity-50 disabled:pointer-events-none dark:bg-transparent dark:border-gray-700 dark:text-white dark:hover:bg-white/10 dark:focus:outline-none dark:focus:ring-1 dark:focus:ring-gray-600"
                        data-hs-overlay="#sidebar-mini" aria-controls="sidebar-mini" aria-label="Toggle navigation"
                        @click="toggleHello">
                        <span class="sr-only">Toggle Navigation</span>
                        <svg class="flex-shrink-0 w-4 h-4" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                            <path fill-rule="evenodd"
                                d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z" />
                        </svg>
                    </button>
                </div>
            </div>

            <div v-for="menuItem in menuItems" :key="menuItem.icon" class="hs-tooltip inline-block [--placement:right]">
                <router-link :to="menuItem.to"
                    class="hs-tooltip-toggle w-[2.375rem] h-[2.375rem] inline-flex justify-center items-center gap-x-2 text-sm font-semibold rounded-full border border-transparent text-gray-500 hover:bg-gray-100 disabled:opacity-50 disabled:pointer-events-none dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:outline-none dark:focus:ring-1 dark:focus:ring-gray-600">
                    <i :class="'fa-solid fa-' + menuItem.icon"></i>
                </router-link>
            </div>


        </div>
    </div>
</template>
<script setup>
import { ref, defineProps } from 'vue';
const menuItems = ref([
  { icon: 'home', to: '/admin' },
  { icon: 'user', to: '/admin/user' },
  { icon: 'truck-fast', to: '/' },
  { icon: 'warehouse', to: '/admin/product' },
  { icon: 'comment-dots', to: '/admin/message'},
  { icon: 'right-from-bracket', to: '/logout' },

]);
const { isHello } = defineProps(['isHello']);
const emit = defineEmits();

const toggleHello = () => {
    // Gửi sự kiện lên component cha khi button được click
    emit('toggle-hello');
};
</script>
 
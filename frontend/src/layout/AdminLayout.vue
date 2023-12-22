<template>
  <div>
    <MiniSidebar :isHello="isHello" @toggle-hello="toggleHello"></MiniSidebar>
    <transition name="fade">
      <div v-if="isHello" @click="closeSidebar" class="overlay"></div>
    </transition>
    <transition-group name="sidebar" tag="div">
      <SideBar v-if="isHello" key="sidebar" class="hs-overlay fixed top-0 start-0 bottom-0 z-[60] w-64 bg-white border-e border-gray-200 pt-7 pb-10 overflow-y-auto lg:block lg:translate-x-0 lg:end-auto lg:bottom-0 [&::-webkit-scrollbar]:w-2 [&::-webkit-scrollbar-thumb]:rounded-full [&::-webkit-scrollbar-track]:bg-gray-100 [&::-webkit-scrollbar-thumb]:bg-gray-300 dark:[&::-webkit-scrollbar-track]:bg-slate-700 dark:[&::-webkit-scrollbar-thumb]:bg-slate-500 dark:bg-gray-800 dark:border-gray-700">
        <!-- ... Sidebar content ... -->
      </SideBar>
    </transition-group>
    <main class="sm:ml-14 px-4">
      <Header></Header>
      <slot></slot>
    </main>
  </div>
</template>

<script setup>
import MiniSidebar from '../components/admin/MiniSidebar.vue';
import Header from '../components/admin/Header.vue';
import SideBar from '../components/admin/Sidebar.vue';
import { ref } from 'vue';

const isHello = ref(false);

const toggleHello = () => {
  isHello.value = !isHello.value;
};

const closeSidebar = () => {
  isHello.value = false;
};
</script>

<style scoped>
.z-10 {
  z-index: 10;
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 9;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}

.sidebar-enter-active, .sidebar-leave-active {
  transition: transform 0.5s;
}
.sidebar-enter, .sidebar-leave-to {
  transform: translateX(-100%);
}
.sidebar-enter-to, .sidebar-leave {
  transform: translateX(0);
}
</style>

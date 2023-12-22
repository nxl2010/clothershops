<template>
    <div class="container mx-auto p-4">
        <h1 class="text-2xl font-bold mb-4">Danh mục sản phẩm</h1>
        <!-- Create Form -->

        <h2 class="text-xl font-semibold mb-2">Thêm danh mục mới</h2>

        <form @submit.prevent="addCategory" class="mb-4">
            <div class="flex space-x-2">
                <input v-model="newCategory.name" type="text" class="w-1/3 px-2 py-1 border rounded-lg" placeholder="Name"
                    required>
                <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white px-3 py-2 rounded-lg">Add</button>

            </div>

        </form>

        <div class="overflow-x-auto">
            <table class="min-w-full">
                <thead>
                    <tr>
                        <th class="px-2 py-3 bg-gray-100 text-left">ID</th>
                        <th class="px-2 py-3 bg-gray-100 text-left">Name</th>
                        <th class="px-2 py-3 bg-gray-100 text-left">Code</th>
                        <th class="px-2 py-3 bg-gray-100 text-left">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="category in categories" :key="category.id" class="border-b hover:bg-gray-100">
                        <td class="px-2 py-4">{{ category.id }}</td>
                        <td class="px-2 py-4">{{ category.name }}</td>
                        <td class="px-2 py-4">{{ category.code }}</td>
                        <td class="px-1 py-4 space-x-2">
                            <button class="bg-green-500 hover:bg-green-600 text-white px-3 py-2 rounded-lg">Edit</button>
                            <button class="bg-red-500 hover:bg-red-600 text-white px-3 py-2 rounded-lg">Delete</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>
<script setup>
import { ref, onMounted } from 'vue';
import apiRequest from '../../service/api.js';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

const showCreateForm = ref(false);
const categories = ref([]);
function toggleCreateForm() {
    showCreateForm.value = !showCreateForm.value;
}

const fetchCategories = async () => {
    try {
        const response = await apiRequest('GET', 'http://localhost:8080/api/v2/category');
        categories.value = response.data;
    } catch (error) {
        console.error('Error fetching categories:', error);
    }
};
const newCategory = ref([]);

const addCategory = async () => {
   try {
        const response = await apiRequest('POST', 'http://localhost:8080/api/v2/category', {
            name: newCategory.value.name
        });
        // Kiểm tra mã trạng thái HTTP để xác định xem yêu cầu đã thành công hay không
        toast.success("Bạn đã cập nhật thành công");
        fetchCategories();
    } catch (error) {
        console.error('Error adding category:', error);
    }
};

onMounted(() => {
    fetchCategories();
});
</script>
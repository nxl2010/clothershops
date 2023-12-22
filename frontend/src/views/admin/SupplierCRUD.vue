<template>
    <div class="container mx-auto p-4">
        <h1 class="text-2xl font-bold mb-4">Supplier CRUD</h1>
        <div class="flex items-center justify-between mb-4">
            <button @click="toggleCreateForm"
                class="px-4 py-2 bg-blue-500 hover:bg-blue-600 text-white rounded-md focus:outline-none">
                {{ showCreateForm ? 'Hide Form' : 'Add New Item' }}
            </button>

            <div class="flex items-center">
                <input type="text" class="px-3 py-2 border rounded-md focus:outline-none" placeholder="Search">
                <button class="ml-2 px-4 py-2 bg-gray-300 hover:bg-gray-400 text-gray-700 rounded-md focus:outline-none"
                    type="button">Search</button>
            </div>
        </div>



        <!-- Create Form -->
        <div v-if="showCreateForm"
            class="fixed top-0 left-0 w-full h-full flex items-center justify-center bg-opacity-50 bg-gray-800">
            <div class="bg-white w-3/4 max-w-2xl p-4 rounded-lg shadow-lg">
                <div class="flex justify-end">
                    <button @click="toggleCreateForm"
                        class="text-gray-600 hover:text-gray-800 text-xl leading-none">&times;</button>
                </div>
                <h2 class="text-xl font-semibold mb-2">Create Supplier</h2>

                <form @submit.prevent="addSupplier" class="mb-4">
                    <div class="grid grid-cols-2 gap-4">
                        <div class="flex flex-col">
                            <label for="name" class="text-gray-600">Name</label>
                            <input v-model="newSupplier.name" type="text" id="name"
                                class="w-full px-4 py-2 border rounded-lg" placeholder="Name" required>
                        </div>

                        <div class="flex flex-col">
                            <label for="email" class="text-gray-600">Email</label>
                            <input v-model="newSupplier.email" type="email" id="email"
                                class="w-full px-4 py-2 border rounded-lg" placeholder="Email" required>
                        </div>
                    </div>



                    <div class="mt-2 ml-2">
                        <label for="address" class="text-gray-600">Address</label>
                        <AddressVietNam @submitAddress="handleAddressSubmit" />
                    </div>

                    <div v-if="newSupplier.address" class="flex flex-col mt-4">

                        <input v-model="newSupplier.address" type="text" id="address"
                            class="w-full px-4 py-2 border rounded-lg" placeholder="Address" required>
                    </div>
                    <div class="flex flex-col mt-4">
                        <label for="note" class="text-gray-600">Note</label>
                        <input v-model="newSupplier.note" type="text" id="note" class="w-full px-4 py-2 border rounded-lg"
                            placeholder="Note">
                    </div>

                    <div class="flex justify-end mt-4">
                        <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-lg">Add
                            Supplier</button>
                    </div>
                </form>
            </div>

        </div>

        <div class="overflow-x-auto">
            <table class="w-full text-left text-gray-500 dark:text-gray-400">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                    <tr>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">ID</th>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Tên nhà cung cấp</th>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Địa chỉ</th>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Email</th>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Trạng thái</th>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Ghi chú</th>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(supplier, index) in suppliers" :key="index" class="border-b hover:bg-gray-100">
                        <td class="px-2 py-4">{{ supplier.id }}</td>
                        <td class="px-2 py-4">
                            <div class=" w-max">
                                {{ supplier.name }}
                            </div>
                        </td>
                        <td class="px-2 py-4">
                            <div class=" w-max">
                                {{ supplier.address }}
                            </div>
                        </td>
                        <td class="px-2 py-4">{{ supplier.email }}</td>
                        <td class="px-2 py-4 text-center">
                            <span class="inline-block w-6 h-6 rounded-full"
                                :class="{ 'bg-green-500': supplier.status, 'bg-red-500': !supplier.status }">

                            </span>
                        </td>


                        <td class="px-2 py-4">{{ supplier.note }}</td>
                        <td class="px-1 py-4 space-x-2">
                            <div class=" w-max ">
                                <button
                                    class=" mr-1 bg-green-500 hover:bg-green-600 text-white px-3 py-2 rounded-lg">Edit</button>
                                <button class="bg-red-500 hover:bg-red-600 text-white px-3 py-2 rounded-lg">Delete</button>

                            </div>
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
const showCreateForm = ref(false);
const suppliers = ref([]);
function toggleCreateForm() {
    showCreateForm.value = !showCreateForm.value;
}

const fetchCategories = async () => {
    try {
        const response = await apiRequest('GET', 'http://localhost:8080/api/v2/supplier');
        suppliers.value = response.data;
    } catch (error) {
        console.error('Error fetching categories:', error);
    }
};

const newSupplier = ref({
    name: '',
    address: '',
    email: '',
    note: '',
});
import AddressVietNam from '../../components/AddressVietNam.vue';

const handleAddressSubmit = (addressData) => {
    // Gửi dữ liệu địa chỉ tới backend
    newSupplier.value.address = addressData.address;

    // Thực hiện axios.post hoặc các bước xử lý khác tại đây
};
const addSupplier = async () => {
    // Kiểm tra xem tên và hình ảnh có được cung cấp không

    try {
        const response = await apiRequest('POST', 'http://localhost:8080/api/v2/supplier', newSupplier.value);
        // Kiểm tra mã trạng thái HTTP để xác định xem yêu cầu đã thành công hay không
        if (response.status === 201) {
            console.log('Supplier added:', response.data);

            // Đặt lại giá trị của newCategory về trạng thái mặc định sau khi thêm
            newSupplier.value = {
                name: '',
                address: '',
                email: '',
                note: '',
            };

        } else {
            console.error('Failed to add category. Status:', response.status);
        }
        fetchCategories();
    } catch (error) {
        console.error('Error adding category:', error);
    }
};

onMounted(() => {
    fetchCategories();
});
</script>
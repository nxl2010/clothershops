<template>
    <div class="container mx-auto p-4">
        <h1 class="text-2xl font-bold mb-4">product CRUD</h1>
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
            class="fixed top-0 left-0 w-full h-full flex items-center justify-center bg-opacity-50 bg-gray-800 overflow-y-auto">
            <div class="bg-white w-full sm:w-3/4 md:w-2/3 lg:w-1/2 xl:w-3/4 p-4 rounded-lg shadow-lg max-h-screen overflow-y-auto">
                <div>
                <button @click="toggleCreateForm"
                    class="text-gray-600 hover:text-gray-800 text-xl leading-none">&times;</button>
            </div>
            
            <PreviewProduct></PreviewProduct>
            </div>
          

        </div>

        <div class="overflow-x-auto">
            <table class="w-full text-left text-gray-500 dark:text-gray-400">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                    <tr>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Mã</th>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Tên sản phẩm </th>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Danh mục </th>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Nhà cung cấp</th>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Giá cả</th>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Số lượng</th>
                        <th scope="col" class="px-2 py-3 bg-gray-100 text-left">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(product, index) in products" :key="index" class="border-b hover:bg-gray-100">
                        <td class="px-2 py-4">{{ product.code }}</td>
                        <td class="px-2 py-4">
                            <div class=" w-max">
                                {{ product.name }}
                            </div>
                        </td>
                        <td class="px-2 py-4">
                            <div class=" w-max">
                                {{ product.category }}
                            </div>
                        </td>
                        <td class="px-2 py-4">{{ product.supplier }}</td>
                        <td class="px-2 py-4">
                            {{ product.price }}
                        </td>


                        <td class="px-2 py-4">{{ product.quantity }}</td>
                        <td class="px-1 py-4 space-x-2">
                            <div class=" w-max ">
                                <button class="mr-1 bg-slate-500 hover:bg-slate-600 text-white px-3 py-2 rounded-lg">Chi
                                    tiết</button>
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
import AddProduct from '../../components/admin/Product/AddProduct.vue';
import PreviewProduct from '../../components/admin/Product/PreviewProduct.vue';
import { ref, onMounted } from 'vue';
import apiRequest from '../../service/api.js';
const showCreateForm = ref(false);
const products = ref([]);
function toggleCreateForm() {
    showCreateForm.value = !showCreateForm.value;
}

const fetchCategories = async () => {
    try {
        const response = await apiRequest('GET', 'http://localhost:8080/api/v2/product');
        products.value = response.data;
    } catch (error) {
        console.error('Error fetching categories:', error);
    }
};

const newproduct = ref({
    name: '',
    address: '',
    email: '',
    note: '',
});
import AddressVietNam from '../../components/AddressVietNam.vue';

const handleAddressSubmit = (addressData) => {
    // Gửi dữ liệu địa chỉ tới backend
    newproduct.value.address = addressData.address;

    // Thực hiện axios.post hoặc các bước xử lý khác tại đây
};
const addproduct = async () => {
    // Kiểm tra xem tên và hình ảnh có được cung cấp không

    try {
        const response = await apiRequest('POST', 'http://localhost:8080/api/v2/product', newproduct.value);
        // Kiểm tra mã trạng thái HTTP để xác định xem yêu cầu đã thành công hay không
        if (response.status === 201) {
            console.log('product added:', response.data);

            // Đặt lại giá trị của newCategory về trạng thái mặc định sau khi thêm
            newproduct.value = {
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
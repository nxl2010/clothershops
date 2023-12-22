<template>
    <div class="container mx-auto p-4">
        <h1 class="text-2xl font-bold mb-4">CRUD Example</h1>
        <button @click="toggleCreateForm" class="mb-2 bg-blue-500 hover:bg-blue-600 text-white px-3 py-2 rounded-lg">
            {{ showCreateForm ? 'Hide Form' : 'Add New Item' }}
        </button>
        <!-- Create Form -->
        <div v-if="showCreateForm"
            class="fixed top-0 left-0 w-full h-full flex items-center justify-center bg-opacity-50 bg-gray-800 overflow-y-auto">
            <!-- Step 2 -->
            <div v-if="showStep2"
                class="bg-white w-full sm:w-3/4 md:w-2/3 lg:w-1/2 xl:w-3/4 p-4 rounded-lg shadow-lg max-h-screen overflow-y-auto">
                <h2 class="text-2xl font-semibold mb-4">Bước 2: Xem danh sách sản phẩm</h2>

                <!-- Display information entered in Step 1 -->
                <div class="flex mb-4">
                    <div class="w-1/4">
                        <label class="block text-sm font-medium text-black-600 mb-1">Người giao hàng:</label>
                        <div>{{ supplier.deliveryPerson }}</div>
                    </div>
                    <div class="w-1/4">
                        <label class="block text-sm font-medium text-black-600 mb-1">Nhà cung cấp:</label>
                        <div>{{ supplier.name }}</div>
                    </div>
                    <div class="w-1/4">
                        <label class="block text-sm font-medium text-black-600 mb-1">Người làm đơn:</label>
                        <div>{{ supplier.manager }}</div>
                    </div>
                    <div class="w-1/4">
                        <label class="block text-sm font-semibold text-black-600 mb-1">Ngày xuất đơn:</label>
                        <div>{{ supplier.orderDate }}</div>
                    </div>
                </div>

                <!-- ... (display other information from Step 1 as needed) ... -->

                <!-- Display the list of products -->
                <!-- Display the list of products -->
                <table class="table-auto">
                    <thead>
                        <tr>
                            <th class="px-4 py-2">Tên sản phẩm</th>
                            <th class="px-4 py-2">Số lượng</th>
                            <th class="px-4 py-2">Giá</th>
                            <th class="px-4 py-2">Tổng tiền</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(product, index) in products" :key="index">
                            <td class="border px-4 py-2">{{ product.name }}</td>
                            <td class="border px-4 py-2">{{ product.quantity }}</td>
                            <td class="border px-4 py-2">{{ product.price }}</td>
                            <td class="border px-4 py-2">{{ product.total }}</td>
                        </tr>
                        <!-- Add a row for displaying the total amount -->
                        <tr>
                            <td colspan="3" class="border px-4 py-2 font-semibold text-right">Tổng cộng:</td>
                            <td class="border px-4 py-2 font-semibold">{{ totalAmount }}</td>
                        </tr>
                        <tr>
                            <td colspan="3" class="border px-4 py-2 font-semibold text-right">Bằng chữ:</td>
                            <td class="border px-4 py-2 font-semibold">{{ formatTotalWords }}</td>
                        </tr>
                    </tbody>
                </table>
                <div class="flex mb-4">
                    <!-- ... (existing code) ... -->
                    <div class="w-1/4">
                        <label class="block text-sm font-medium text-black-600 mb-1">Trạng thái thanh toán:</label>
                        <div>{{ supplier.pay }}</div>
                    </div>
                </div>
                <!-- Button to go back to Step 1 -->
                <div class="flex justify-between mt-4">
                    <button @click="prevStep" class="bg-gray-500 text-white p-2 rounded">Quay lại Bước 1</button>
                    <div>
                        <button @click="saveAsPdf" class="bg-blue-500 mr-2 text-white p-2 rounded">Lưu PDF</button>
                        <button @click="printInvoice" class="bg-green-500 text-white p-2 rounded">In hóa đơn</button>
                    </div>

                </div>
            </div>

            <div v-if="!showStep2"
                class="bg-white w-full sm:w-3/4 md:w-2/3 lg:w-1/2 xl:w-3/4 p-4 rounded-lg shadow-lg max-h-screen overflow-y-auto ">

                <div class="flex justify-end">
                    <button @click="toggleCreateForm"
                        class="text-gray-600 hover:text-gray-800 text-xl leading-none">x</button>
                </div>
                <div class="container mx-auto p-4 ">
                    <h2 class="text-2xl font-semibold mb-4">Bước 1: Nhập sản phẩm mới</h2>
                    <div class="border-x p-3 border-y">
                        <div class="flex flex-col lg:flex-row gap-6">
                            <div class="w-full lg:w-1/2">
                                <label for="deliveryPerson" class="block text-sm font-medium text-gray-600 mb-1">Người giao
                                    hàng:</label>
                                <input type="text" v-model="supplier.deliveryPerson" id="deliveryPerson"
                                    class="w-full p-2 border rounded focus:outline-none focus:ring focus:border-blue-300 transition-all"
                                    required />
                            </div>
                            <div class="w-full lg:w-1/2">
                                <label for="supplier" class="block text-sm font-medium text-gray-600 mb-1">Nhà cung
                                    cấp:</label>
                                <select v-model="supplier.name" id="supplier"
                                    class="w-full p-2 border rounded focus:outline-none focus:ring focus:border-blue-300 transition-all"
                                    required>
                                    <!-- Use v-for to dynamically generate options -->
                                    <option v-for="supplierOption in supplierOptions" :value="supplierOption.name"
                                        :key="supplierOption.code">
                                        {{ supplierOption.name }}
                                    </option>
                                </select>
                            </div>
                        </div>


                        <div class="overflow-x-auto">
                            <table class="min-w-full table-auto border mt-4">
                                <thead>
                                    <tr>
                                        <th class="px-4 py-2">Tên sản phẩm</th>
                                        <th class="px-4 py-2">Số lượng</th>
                                        <th class="px-4 py-2">Giá</th>
                                        <th class="px-4 py-2">Tổng tiền</th>
                                        <th class="px-4 py-2">Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="(product, index) in products" :key="index">
                                        <td class="border px-4 py-2">{{ product.name }}</td>
                                        <td class="border px-4 py-2">{{ product.quantity }}</td>
                                        <td class="border px-4 py-2">{{ product.price }}</td>
                                        <td class="border px-4 py-2">{{ product.total }}</td>
                                        <td class="border px-4 py-2">
                                            <button @click="removeProduct(index)" class="text-red-500">Xóa</button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>


                        <div class="flex flex-col sm:flex-row justify-between items-center mt-4 mx-2">
                            <div class="mb-4 sm:mb-0">
                                <label for="supplier" class="block text-sm font-medium text-gray-600 mb-1">Trạng
                                    thái</label>
                                <select v-model="supplier.pay" id="supplier"
                                    class="w-full p-2 border rounded focus:outline-none focus:ring focus:border-blue-300 transition-all"
                                    required placeholder="Chưa thanh toán">
                                    <!-- Danh sách các nhà cung cấp -->
                                    <option value="Đã Thanh toán">Đã thanh toán</option>
                                    <option value="Chưa thanh toán">Chưa thanh toán</option>
                                </select>
                            </div>

                            <div>
                                <label class="text-sm font-medium text-gray-600">Tổng giá tiền:</label>
                                <div class="text-lg font-semibold">{{ totalAmount }}</div>
                            </div>
                        </div>
                    </div>

                    <!-- Form nhập thông tin sản phẩm mới -->

                    <form @submit.prevent="addProduct" class="border p-3 mt-4">
                        <div class="grid grid-cols-2 gap-4 mb-2">
                            <div>
                                <label for="productName" class="block text-sm font-medium text-gray-600">Tên sản
                                    phẩm:</label>
                                <input type="text" v-model="newProduct.name" id="productName"
                                    class="mt-1 p-2 border rounded w-full" required />
                            </div>

                            <div>
                                <label for="quantity" class="block text-sm font-medium text-gray-600">Số lượng:</label>
                                <div class="grid grid-cols-4 gap-1">
                                    <div v-for="(sizeQuantity, index) in sizeQuantities" :key="index">
                                        <label :for="`size_${index}`">{{ sizeQuantity.size }}</label>
                                        <input type="number" v-model="sizeQuantity.quantity" :id="`size_${index}`"
                                            class="mt-1 p-2 border rounded w-full" required pattern="[0-9]+" />
                                    </div>
                                </div>
                            </div>

                            <div>
                                <label for="price" class="block text-sm font-medium text-gray-600">Giá:</label>
                                <input type="number" v-model="newProduct.price" id="price"
                                    class="mt-1 p-2 border rounded w-full" required pattern="[0-9]+" />
                            </div>

                            <div>
                                <label for="total" class="block text-sm font-medium text-gray-600">Tổng tiền:</label>
                                <input type="number" v-model="newProduct.total" id="total"
                                    class="mt-1 p-2 border rounded w-full" readonly />
                            </div>
                        </div>
                        <div class="text-gray-600 mt-2">Giá đã nhập: {{ formatPriceInWords }}</div>
                        <div class="flex justify-end">
                            <button type="submit" class="mt-3 bg-blue-500 text-white p-2 rounded">Thêm sản phẩm</button>
                        </div>
                    </form>
                    <!-- Nút chuyển sang bước tiếp theo -->
                    <div class="flex justify-end p-3">
                        <button @click="nextStep" class="mt-3 bg-green-500 text-white p-2 rounded">Tiếp theo</button>
                    </div>
                </div>
            </div>
        </div>
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
import { ref, onMounted, computed, watch, watchEffect } from 'vue';
import apiRequest from '../../service/api.js';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

const showCreateForm = ref(false);
const categories = ref([]);
const sizeQuantities = ref([
    { size: "S", quantity: 0 },
    { size: "M", quantity: 0 },
    { size: "L", quantity: 0 },
    // Thêm các size khác nếu cần
]);
function toggleCreateForm() {
    showCreateForm.value = !showCreateForm.value;
}
const nowDay = Date.now();

const dateObject = new Date(nowDay);
const supplierOptions = ref([]);
const year = dateObject.getFullYear();
const month = (dateObject.getMonth() + 1).toString().padStart(2, '0');
const day = dateObject.getDate().toString().padStart(2, '0');
const hour = dateObject.getHours().toString().padStart(2, '0');
const minute = dateObject.getMinutes().toString().padStart(2, '0');

const formattedDateTime = `${year}-${month}-${day} ${hour}:${minute}`;
const supplier = ref({
    name: '',
    deliveryPerson: '',
    pay: 'Chưa thanh toán',
    orderDate: formattedDateTime,
    manager: 'Nguyễn Xuân Lâm'
});
const fetchSupplier = async () => {
    try {
        const response = await apiRequest('GET', 'http://localhost:8080/api/v2/supplier');
        supplierOptions.value = response.data;
    } catch (error) {
        console.error('Error fetching categories:', error);
    }
};
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
const formatTotalWords = computed(() => {
    if (totalAmount.value !== null && !isNaN(totalAmount.value)) {
        const amountInWords = convertToWords(totalAmount.value);
        return `${amountInWords} đồng`;
    }
    return '';
});
const formatPriceInWords = computed(() => {
    if (newProduct.value.price !== null && !isNaN(newProduct.value.price)) {
        const amountInWords = convertToWords(newProduct.value.price);
        return `${amountInWords} đồng`;
    }
    return '';
});

// Helper function to convert a number to words (Vietnamese)
function convertToWords(number) {
    const units = ['', 'một', 'hai', 'ba', 'bốn', 'năm', 'sáu', 'bảy', 'tám', 'chín'];
    const tens = ['', 'mười', 'hai mươi', 'ba mươi', 'bốn mươi', 'năm mươi', 'sáu mươi', 'bảy mươi', 'tám mươi', 'chín mươi'];

    if (number < 10) {
        return units[number];
    } else if (number < 100) {
        const unitDigit = number % 10;
        return `${tens[Math.floor(number / 10)]} ${units[unitDigit]}`;
    } else if (number < 1000) {
        const remainingTens = number % 100;
        return `${units[Math.floor(number / 100)]} trăm ${convertToWords(remainingTens)}`;
    } else if (number < 1000000) {
        const remainingHundreds = number % 1000;
        return `${convertToWords(Math.floor(number / 1000))} nghìn ${convertToWords(remainingHundreds)}`;
    } else if (number < 1000000000) {
        const remainingThousands = number % 1000000;
        return `${convertToWords(Math.floor(number / 1000000))} triệu ${convertToWords(remainingThousands)}`;
    } else if (number < 1000000000000) {
        const remainingMillions = number % 1000000000;
        return `${convertToWords(Math.floor(number / 1000000000))} tỷ ${convertToWords(remainingMillions)}`;
    } else {
        // Handle other cases
        return 'Số quá lớn để hiển thị';
    }
}

const showStep2 = ref(false);  // Thêm biến để điều khiển hiển thị Bước 2

// ... (Các hàm và biến khác không thay đổi) ...

const nextStep = () => {
    // Chuyển từ Bước 1 sang Bước 2
    showStep2.value = true;

};

const prevStep = () => {
    // Chuyển từ Bước 2 về Bước 1
    showStep2.value = false;
};
const newProduct = ref({
    name: '',
    price: null,
    quantity: 0,
    total: 0,
});
const products = ref([]);

const addProduct = () => {
    // Thêm sản phẩm mới vào danh sách
    products.value.push({
        name: newProduct.value.name,
        price: newProduct.value.price,
        quantity: newProduct.value.quantity,
        total: newProduct.value.total,
    });

    // Xóa thông tin của sản phẩm mới để chuẩn bị cho sản phẩm tiếp theo
    newProduct.value = {
        name: '',
        quantity: 0,
        total: 0,
    };
};
const removeProduct = (index) => {
    products.value.splice(index, 1);
};

const totalAmount = ref(0);

// Tính tổng giá tiền khi danh sách sản phẩm thay đổi
watchEffect(() => {
    totalAmount.value = products.value.reduce((total, product) => total + product.total, 0);
});

// ... (Phần code khác không thay đổi) ...

// Sử dụng watchEffect để tự động tính total khi quantity hoặc price thay đổi
watchEffect(() => {
    newProduct.value.total = newProduct.value.quantity * newProduct.value.price;
});
onMounted(() => {
    fetchCategories();
    fetchSupplier();
});
</script>
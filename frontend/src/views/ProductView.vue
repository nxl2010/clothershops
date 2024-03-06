<template>
    <div class="container mx-auto p-8 px-14">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-20 bg-white rounded-xl p-3">
            <div class="grid grid-cols-1 md:grid-cols-8 gap-2">
                <div class="md:col-span-1">
                   
                </div>
                <div class="md:col-span-7">
                    <img :src="products.url" alt="Product Image"
                        class="w-full h-auto rounded-lg shadow-lg" />
                </div>
            </div>

            <!-- Phần hiển thị hình ảnh -->

            <!-- Phần thông tin sản phẩm -->
            <div>
                <h1 class="text-2xl font-bold mb-2">{{ products.name }}</h1>
                <p class="text-gray-600 mb-2">Mã sản phẩm: NX12</p>
                <p class="text-gray-600 mb-2">Số lượng còn hàng: {{ products.totalQuantity }}</p>
                <hr />
                <p class="text-gray-700 text-3xl mt-4 mb-2">Giá: {{ products.price }} đ</p>

                <!-- Chọn size -->
                <div class="mb-2">
                    <label for="size" class="block text-sm font-medium text-gray-600">Chọn size:</label>
                    <div class="flex items-center space-x-2">
                        <template v-for="(size, index) in products.sizes" :key="index">
                            <input type="radio" :id="`size-${index}`" name="size" :value="size" class="hidden"
                                :checked="isSelectedSize(size)" @change="selectSize(size)" />
                            <label :for="`size-${index}`" class="radio-label cursor-pointer">
                                <input type="checkbox" :checked="isSelectedSize(size)" class="hidden" />
                                <span :class="{ 'bg-blue-500': isSelectedSize(size) }" class="radio-button"></span>
                                {{ size }}
                            </label>
                        </template>
                    </div>
                </div>


                <!-- Chọn số lượng -->
                <div class="mb-2">
                    <label for="quantity" class="block text-sm font-medium text-gray-600">Số lượng:</label>
                    <div class="flex space-x-2 items-center">
                        <button type="button" class="border border-gray-300 rounded-md px-2" @click="decrementQuantity">
                            -
                        </button>
                        <input type="number" id="quantity" name="quantity" v-model="quantity"
                            class="border border-gray-300 rounded-md p-2" />
                        <button type="button" class="border border-gray-300 rounded-md px-2" @click="incrementQuantity">
                            +
                        </button>
                    </div>
                </div>

                <!-- Nút thêm vào giỏ hàng và Mua ngay -->
                <div class="flex space-x-4">
                    <button @click="addToCart"
                        class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:ring focus:border-blue-300">
                        Thêm vào giỏ hàng
                    </button>

                    <button @click="addToBuy"
                        class="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-600 focus:outline-none focus:ring focus:border-green-300">
                        Mua ngay
                    </button>
                </div>
            </div>
        </div>
        <div class="mt-8  p-3 rounded-xl bg-white">
            <!-- Thanh điều hướng -->
            <div class="flex mb-4">
                <button @click="showDetails" :class="{ 'bg-blue-500 text-white': activeTab === 'details' }"
                    class="mr-4 px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300">
                    Chi tiết sản phẩm
                </button>
                <button @click="showReviews" :class="{ 'bg-blue-500 text-white': activeTab === 'reviews' }"
                    class="px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300">
                    Đánh giá
                </button>
            </div>

            <!-- Nội dung -->
            <div v-show="activeTab === 'details'">
                <!-- Nội dung Chi tiết sản phẩm -->
                <p>Thông tin chi tiết sản phẩm sẽ ở đây...</p>
            </div>
            <div v-show="activeTab === 'reviews'">
                <!-- Nội dung Đánh giá -->
                <p>Đánh giá sản phẩm sẽ ở đây...</p>
            </div>
        </div>
        <div class="mt-8  p-3 rounded-xl bg-white">
            <ProductRecomen></ProductRecomen>
        </div>

    </div>
</template>

<script setup>
import { ref, onMounted, watchEffect } from 'vue';
import ProductRecomen from '../components/ProductRecomen.vue';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import apiRequest from '../service/api';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const productCode = route.params.code;
const activeTab = ref('details');

const showDetails = () => {
    activeTab.value = 'details';
};

const showReviews = () => {
    activeTab.value = 'reviews';
};

const products = ref([]);
const quantity = ref(1);
const selectedSize = ref(null);

const fetchProducts = async (codeProduct) => {
    try {
        console.log(codeProduct);
        const response = await apiRequest('GET', `http://localhost:8080/api/v2/product/detailproduct/${codeProduct}`);
        products.value = response.data;
    } catch (error) {
        console.error('Error fetching categories:', error);
    }
};

const isSelectedSize = (size) => {
    return selectedSize.value === size;
};

const selectSize = (size) => {
    selectedSize.value = size;
};
const decrementQuantity = () => {
    if (quantity.value > 1) {
        quantity.value -= 1;
    }
};

const incrementQuantity = () => {
    quantity.value += 1;
};

//thêm giỏ hàng
const username = ref("username");
const fetchUsername = async () => {
    try {
        const response = await apiRequest('GET', 'http://localhost:8080/api/v2/user/getusername');
        username.value = response.data;
    } catch (error) {
        console.error('Error fetching categories:', error);
    }
};
const getProductToCart = async (username) => {

    try {
        const response = await apiRequest('POST', `http://localhost:8080/api/v2/cart?username=${username}`, {
            "productCode": productCode,
            "size": selectedSize.value,
            "quantity": quantity.value
        });

        if (response.status === 200) {
            console.log('product added:', response.data);

            toast.success(`Thêm vào giỏ hàng thành công - Size: ${selectedSize.value}, Số lượng: ${quantity.value}`);
        } else {
            console.error('Failed to add category. Status:', response.status);
        }
    } catch (error) {
        console.error('Error adding category:', error);
    }

    // Thực hiện thêm vào giỏ hàng và hiển thị thông báo thành công
    // Sử dụng toast.success để hiển thị thông báo thành công

};
const addToCart = () => {
    if (!selectedSize.value) {
        toast.error('Vui lòng chọn kích thước.');
        return;
    }

    if (quantity.value <= 0) {
        toast.error('Vui lòng chọn số lượng hợp lệ.');
        return;
    }
    getProductToCart(username.value);
}
const addToBuy = () => {
    if (!selectedSize.value) {
        toast.error('Vui lòng chọn kích thước.');
        return;
    }

    if (quantity.value <= 0) {
        toast.error('Vui lòng chọn số lượng hợp lệ.');
        return;
    }
    const existingItems = [];

    // Thêm một mục mới vào mảng
    existingItems.push({
        "name": products.value.name,
        "image": products.value.url,
        "productCode": productCode,
        "size": selectedSize.value,
        "quantity": quantity.value,
        "selected": true,
        "price": products.value.price
    });

    // Lưu lại mảng đã cập nhật vào localStorage
    localStorage.setItem('selectedItems', JSON.stringify(existingItems));
    // Chuyển hướng đến trang thanh toán
    router.push('/payment');
}
onMounted(() => {
    fetchUsername();
    fetchProducts(productCode);
});
</script>
<style scoped>
.radio-label {
    @apply cursor-pointer border border-gray-300 rounded-md p-1 text-sm flex items-center;
}

.radio-button {
    @apply w-4 h-4 mr-1 border border-gray-300 rounded-full;
}
</style>
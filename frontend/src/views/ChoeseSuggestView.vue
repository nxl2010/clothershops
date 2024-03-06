<template>
    <div class="flex mb-32">
        <div class="w-1/3 py-4 px-2">
            <div class="mb-8 bg-white p-6 rounded shadow-md">
                <div class="flex">
                    <h2 class="text-2xl font-bold mb-4">Gợi ý sản phẩm</h2>
                    <i class="fa-solid fa-rotate-right p-2" @click="reload()"></i>
                </div>

                <div v-if="categories.length === 0">
                    <p>Chưa có sản phẩm để gợi ý.</p>
                </div>
                <div v-else>

                    <div v-for="(category, categoryIndex) in categories" :key="category.category" class="mb-4">
                        <div class="flex">
                            <h3 class="text-lg font-bold mb-2">{{ category.category }}</h3>

                        </div>
                        <div class="flex items-center space-x-4">
                            <div v-for="(product, productIndex) in category.products" :key="product.name">
                                <div :class="{ 'border-blue-500 border-2': isSelected(product) }"
                                    @click="addToCart(productIndex, categoryIndex), addImage(product.url, category.category)"
                                    class="product-image-container">
                                    <img :src="product.url" :alt="product.name"
                                        class="w-28 h-28 object-cover rounded cursor-pointer">
                                </div>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>
        <div class="w-1/3 py-4 px-2">
            <div class=" bg-white py-2 rounded shadow-md">
                <div class=" bg-white px-6 pt-3 ">
                    <h2 class="text-2xl font-bold mb-4">Hình ảnh tương quan</h2>

                </div>
                <div class="flex flex-wrap">
                    <!-- Khung hiển thị cho Áo, Quần, Giày -->
                    <div class="w-full md:w-3/5 pb-4 flex flex-col items-center">

                        <div class="flex-2 mb-2">
                            <div class="h-full">
                                <div>
                                    <img :src="aoImage" alt="Result Image" class=" w-40 h-36 object-cover rounded">
                                </div>
                            </div>
                        </div>


                        <div class="flex-3 mb-2">
                            <div class=" h-full">
                                <div>
                                    <img :src="quanGiayImage" alt="Result Image" class="w-40 h-48 object-contain rounded">
                                </div>
                            </div>
                        </div>

                        <div class="flex-1">
                            <div class="h-full">
                                <div>
                                    <img :src="giayImage" alt="Result Image" class="w-40 h-40 object-contain rounded">
                                </div>
                            </div>
                        </div>
                    </div>



                    <!-- Khung hiển thị cho Túi, Đồng hồ -->
                    <div class="w-full md:w-2/5 py-4 flex justify-center flex-col">



                        <div class="mb-2">
                            <img :src="dongHoImage" alt="Result Image" class=" w-20 h-20 object-cover rounded">
                        </div>

                        <div class="mb-2">
                            <img :src="giayNItImage" alt="Result Image" class=" w-20 h-20 object-cover rounded">
                        </div>
                        <div>
                            <img :src="tuiImage" alt="Result Image" class=" w-20 h-20 object-cover rounded">
                        </div>


                    </div>

                </div>

            </div>
        </div>





        <!-- Phần 3: Tính giá tiền -->
        <div class="w-1/3 py-4 px-2">

            <div class="mb-8 bg-white p-4 rounded shadow-md">
                <h2 class="text-2xl font-bold mb-4">Giỏ hàng của bạn</h2>
                <div v-if="cartItems.length === 0">
                    <p>Giỏ hàng của bạn đang trống.</p>
                </div>
                <div v-else>
                    <div v-for="(item, index) in cartItems" :key="index" class="flex mb-4 border-2 p-2 rounded-xl">
                        <input type="checkbox" v-model="item.selected" @change="updateTotals" class="mr-4">
                        <img :src="item.image" alt="Product Image" class="w-16 h-16 object-cover rounded-md mr-4">
                        <div class="w-1/2">
                            <p class="text-sm font-medium text-gray-800">{{ item.name }}</p>
                            <select v-model="item.size" class="block w-full mt-1">
                                <option v-for="sizeOption in sizeOptions" :key="sizeOption">{{ sizeOption }}</option>
                            </select>
                            <p class="text-sm font-bold text-blue-500">{{ item.price }}</p>
                        </div>
                        <div class="w-1/2 flex items-center justify-end">
                            <span v-if="!item.selected">{{ item.quantity }}</span>
                            <button @click="decrementQuantity(item)" class="text-gray-500 mr-2"
                                v-if="item.selected">-</button>
                            <span class="mr-2" v-if="item.selected">{{ item.quantity }}</span>
                            <button @click="incrementQuantity(item)" class="text-gray-500 mr-4"
                                v-if="item.selected">+</button>
                            <button @click="removeFromCart(item)" class="text-red-500">Xóa</button>
                        </div>
                    </div>
                    <div class="py-2">
                        <div class="flex justify-between">
                            <p class="text-2xl font-bold text-blue-500">Tổng thanh toán:</p>
                            <p class="text-2xl font-bold">{{ selectedTotalPrice }}</p>
                        </div>
                        <button class="mt-4 p-2 bg-blue-500 rounded text-white" @click="checkout">Thanh Toán</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script setup>
import { ref, onMounted, watch } from 'vue';
import ProductRecomen from '../components/ProductRecomen.vue';
import { useRouter, useRoute } from 'vue-router';
import apiRequest from '../service/api';
const route = useRoute();
const name = route.query.name;
const cartItems = ref([

    // Có thể thêm các mục khác vào đây nếu cần
]);


const addCategories = async (code) => {
    try {
        const response = await apiRequest('POST', `http://localhost:8080/api/v2/suggestclothers/suggest?categoryCode=${code}`);
        categories.value = response.data;

        if (response.status === 200) {
            console.log("ok");
        } else {
            console.error('Lỗi:', response.status);
        }
    } catch (error) {
        console.error('Lỗi:', error);
    }

    // Xóa danh sách sản phẩm được chọn sau khi thanh toán
};
const router = useRouter();
const sizeOptions = ["S", "M", "L", "XL"]; // Danh sách kích thước chung

const addToCart = (productIndex, categoryIndex) => {
    const selectedProduct = categories.value[categoryIndex].products[productIndex];
    const existingItem = cartItems.value.find(item => item.name === selectedProduct.name);

    if (existingItem) {
        existingItem.quantity++;
    } else {
        cartItems.value.push({
            name: selectedProduct.name,
            image: selectedProduct.url,
            price: selectedProduct.price,
            quantity: 1,
            code: selectedProduct.code,
            selected: true
        });
        console.log(cartItems.value);
    }

    // Cập nhật tổng số lượng và tổng giá tiền
    updateTotals();
};


const aoImage = ref('https://via.placeholder.com/160');
const quanGiayImage = ref('https://via.placeholder.com/160');
const giayImage = ref('https://via.placeholder.com/160');
const dongHoImage = ref('https://via.placeholder.com/80');
const giayNItImage = ref('https://via.placeholder.com/80');
const tuiImage = ref('https://via.placeholder.com/80');
const addImage = (url, name) => {
    if (name == "Áo sơ mi" || name == "Áo ngắn tay") {
        aoImage.value = url;
    }
    if (name == "Quần dài" || name == "Quần ngắn") {
        quanGiayImage.value = url;
    }
    if (name == "Thắt lưng") {
        giayNItImage.value = url;
    }
    if (name == "Giày") {
        giayImage.value = url;
    }
    if (name == "Đồng hồ") {
        dongHoImage.value = url;
    }
}
const addNewImage = (url, name) => {
    if (name == "nw2B7" || name == "k2N86") {
        aoImage.value = url;
    }
    if (name == "WvCy3" || name == "Ygpwp") {
        quanGiayImage.value = url;
    }
    if (name == "LZKsx") {
        giayNItImage.value = url;
    }
    if (name == "HHoxL") {
        giayImage.value = url;
    }
    if (name == "BMpgD") {
        dongHoImage.value = url;
    }
}
const categories = ref([

]);
const updateTotals = () => {
  selectedItems.value = cartItems.value.filter(item => item.selected);
  selectedTotalItems.value = selectedItems.value.reduce((total, item) => total + item.quantity, 0);
  selectedTotalPrice.value = selectedItems.value.reduce((total, item) => total + item.quantity * item.price, 0);
};

const removeFromCart = (index) => {
    cartItems.value.splice(index, 1);
    updateTotals();
};

const incrementQuantity = (item) => {
    item.quantity += 1;
    updateTotals();
};

const decrementQuantity = (item) => {
    if (item.quantity > 1) {
        item.quantity -= 1;
        updateTotals();
    }
};
const selectedProduct = ref(null);

const selectProduct = (product) => {
    if (isSelected(product)) {
        selectedProduct.value = null;
    } else {
        selectedProduct.value = product;
    }
}

const isSelected = (product) => {
    return selectedProduct.value === product;
}
const selectedItems = ref([]);
const selectedTotalPrice = ref(0);
const selectedTotalItems = ref(0);

const reload = () => {
    addCategories(name);
}
const checkout = () => {
    // Lưu danh sách sản phẩm được chọn vào localStorage
    localStorage.setItem('selectedItems', JSON.stringify(selectedItems.value));

    // Chuyển hướng đến trang thanh toán
    router.push('/payment');
};
watch(cartItems, () => {
    updateTotals();
});
onMounted(() => {
    const url = localStorage.getItem('selectedUrl');

    addCategories(name);
    addNewImage(url,name);
    updateTotals();
});
</script>
  
<style>
/* Các kiểu CSS tùy chỉnh có thể được thêm ở đây nếu cần */
</style>
  
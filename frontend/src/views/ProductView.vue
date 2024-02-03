<template>
    <div class="container mx-auto p-8 px-14">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-20 bg-white rounded-xl p-3">
            <div class="grid grid-cols-1 md:grid-cols-8 gap-2">
                <div class="md:col-span-1">
                    <div class="md:col-span-1">
                        <img src="https://pos.nvncdn.com/a36e05-151378/ps/20231213_iAl9k6EuKP.jpeg" alt="Thumbnail"
                            class="w-full h-auto mb-2 rounded-lg shadow-lg" />
                        <img src="https://pos.nvncdn.com/a36e05-151378/ps/20231213_ut0YOGicZV.jpeg" alt="Thumbnail"
                            class="w-full h-auto mb-2 rounded-lg shadow-lg" />
                        <img src="https://pos.nvncdn.com/a36e05-151378/ps/20231213_MIYfKj2HUR.jpeg" alt="Thumbnail"
                            class="w-full h-auto mb-2 rounded-lg shadow-lg" />
                        <!-- Thêm các ảnh khác nếu cần -->
                    </div>
                </div>
                <div class="md:col-span-7">
                    <img src="https://pos.nvncdn.com/a36e05-151378/ps/20231213_iAl9k6EuKP.jpeg" alt="Product Image"
                        class="w-full h-auto rounded-lg shadow-lg" />
                </div>
            </div>

            <!-- Phần hiển thị hình ảnh -->

            <!-- Phần thông tin sản phẩm -->
            <div>
                <h1 class="text-2xl font-bold mb-2">Áo Sơ Mi Bamboo NX25</h1>
                <p class="text-gray-600 mb-2">Mã sản phẩm: NX12</p>
                <p class="text-gray-600 mb-2">Số lượng còn hàng: 290</p>
                <hr />
                <p class="text-gray-700 text-3xl mt-4 mb-2">Giá: 199000</p>

                <!-- Chọn size -->
                <div class="mb-2">
    <label for="size" class="block text-sm font-medium text-gray-600">Chọn size:</label>
    <div class="flex items-center space-x-2">
      <input
        type="radio"
        id="size-s"
        name="size"
        value="S"
        class="hidden"
        :checked="isSelectedSize('S')"
        @change="selectSize('S')"
      />
      <label for="size-s" class="radio-label cursor-pointer">
        <input type="checkbox" :checked="isSelectedSize('S')" class="hidden" />
        <span :class="{ 'bg-blue-500': isSelectedSize('S') }" class="radio-button"></span>
        S
      </label>

      <input
        type="radio"
        id="size-m"
        name="size"
        value="M"
        class="hidden"
        :checked="isSelectedSize('M')"
        @change="selectSize('M')"
      />
      <label for="size-m" class="radio-label cursor-pointer">
        <input type="checkbox" :checked="isSelectedSize('M')" class="hidden" />
        <span :class="{ 'bg-blue-500': isSelectedSize('M') }" class="radio-button"></span>
        M
      </label>

      <input
        type="radio"
        id="size-l"
        name="size"
        value="L"
        class="hidden"
        :checked="isSelectedSize('L')"
        @change="selectSize('L')"
      />
      <label for="size-l" class="radio-label cursor-pointer">
        <input type="checkbox" :checked="isSelectedSize('L')" class="hidden" />
        <span :class="{ 'bg-blue-500': isSelectedSize('L') }" class="radio-button"></span>
        L
      </label>

      <input
        type="radio"
        id="size-xl"
        name="size"
        value="XL"
        class="hidden"
        :checked="isSelectedSize('XL')"
        @change="selectSize('XL')"
      />
      <label for="size-xl" class="radio-label cursor-pointer">
        <input type="checkbox" :checked="isSelectedSize('XL')" class="hidden" />
        <span :class="{ 'bg-blue-500': isSelectedSize('XL') }" class="radio-button"></span>
        XL
      </label>
    </div>
  </div>


                <!-- Chọn số lượng -->
                <div class="mb-2">
                    <label for="quantity" class="block text-sm font-medium text-gray-600">Số lượng:</label>
                    <div class="flex space-x-2 items-center">
                        <button type="button" class="border border-gray-300 rounded-md px-2" @click="decrementQuantity">
                            -
                        </button>
                        <input type="number" id="quantity" name="quantity" value="1"
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

                    <button
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
import { ref } from 'vue';
import ProductRecomen from '../components/ProductRecomen.vue';
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

const activeTab = ref('details');
const quantity = ref(1);

const selectedSize = ref(null);

// Thêm hàm isSelectedSize vào setup
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

const showDetails = () => {
    activeTab.value = 'details';
};

const showReviews = () => {
    activeTab.value = 'reviews';
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

    // Thực hiện thêm vào giỏ hàng và hiển thị thông báo thành công
    // Sử dụng toast.success để hiển thị thông báo thành công
    toast.success(`Thêm vào giỏ hàng thành công - Size: ${selectedSize.value}, Số lượng: ${quantity.value}`);
};
</script>
<style scoped>
.radio-label {
  @apply cursor-pointer border border-gray-300 rounded-md p-1 text-sm flex items-center;
}

.radio-button {
  @apply w-4 h-4 mr-1 border border-gray-300 rounded-full;
}
</style>
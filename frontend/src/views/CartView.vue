<template>
  <div class="container mx-auto p-8 rounded-xl px-14">
    <div class="flex bg-white p-3">
      <!-- Sản phẩm trong giỏ -->
      <div class="w-2/3 pr-8">
        <h2 class="text-2xl font-bold mb-4">Giỏ hàng của bạn</h2>

        <!-- Hiển thị danh sách sản phẩm trong giỏ hàng -->
        <div v-if="cartItems.length === 0">
          <p>Giỏ hàng của bạn đang trống.</p>
        </div>
        <div v-else>
          <div v-for="(item, index) in cartItems" :key="index" class="flex mb-4 border-2 p-2 rounded-xl">
            <input type="checkbox" v-model="item.selected" @change="updateTotals" class="mr-4">
            <img :src="item.url" alt="Product Image" class="w-16 h-16 object-cover rounded-md mr-4">
            <div class="w-1/2">
              <p class="text-sm font-medium text-gray-800">{{ item.productName }}</p>
              <p class="text-sm font-medium text-gray-800">Size: {{ item.size }}</p>
              <p class="text-sm font-bold text-blue-500">{{ item.price }}</p>
            </div>
            <div class="w-1/2 flex items-center justify-end">
              <span v-if="!item.selected">{{ item.quantity }}</span>
              <button @click="decrementQuantity(index)" class="text-gray-500 mr-2" v-if="item.selected">-</button>
              <span class="mr-2" v-if="item.selected">{{ item.quantity }}</span>
              <button @click="incrementQuantity(index)" class="text-gray-500 mr-4" v-if="item.selected">+</button>
              <button @click="removeFromCart(item.id)" class="text-red-500">Xóa</button>
            </div>
          </div>

        </div>
      </div>

      <!-- Tóm tắt đơn hàng-->
      <div class="w-1/3 bg-slate-200 p-4 rounded-xl">
        <h2 class="text-2xl font-bold mb-4">Tóm tắt đơn hàng</h2>
        <div>
          <p class=" text-lg font-medium text-gray-800">Tổng số sản phẩm: {{ selectedTotalItems }}</p>
          <p class="text-lg font-bold text-blue-500">Tổng giá: {{ selectedTotalPrice }}</p>
        </div>
        <button @click="checkout"
          class="mt-4 bg-blue-500  text-white px-6 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:ring focus:border-blue-300">
          Thanh toán
        </button>
      </div>
    </div>
    <div class="mt-8  p-3 rounded-xl bg-white">
      <ProductRecomen></ProductRecomen>
    </div>
  </div>
</template>
  
<script setup>
import { ref, onMounted, watch } from 'vue';
import ProductRecomen from '../components/ProductRecomen.vue';
import { useRouter } from 'vue-router';
import apiRequest from '../service/api';
const router = useRouter();
const cartItems = ref([
  // Thêm các sản phẩm khác nếu cần
]);
const fetchCart = async () => {
  try {
    const response = await apiRequest('GET', 'http://localhost:8080/api/v2/cart');
    cartItems.value = response.data;
    cartItems.value.selected = false;
  } catch (error) {
    console.error('Lỗi tải:', error);
  }
};

const updateTotals = () => {
  selectedItems.value = cartItems.value.filter(item => item.selected);
  selectedTotalItems.value = selectedItems.value.reduce((total, item) => total + item.quantity, 0);
  selectedTotalPrice.value = selectedItems.value.reduce((total, item) => total + item.quantity * item.price, 0);
};
const removeFromCart = async (itemId) => {
    if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này khỏi giỏ hàng không?")) {
        try {
            const response = await apiRequest('DELETE', `http://localhost:8080/api/v2/cart/${itemId}`);
            if (response.status === 200) {
                fetchCart();
                updateTotals();
            } else {
                console.error('Lỗi', response.status);
            }
        } catch (error) {
            console.error('Lỗi', error);
        }
    }
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


const selectedItems = ref([]);
const selectedTotalPrice = ref(0);
const selectedTotalItems = ref(0);
onMounted(() => {
  updateTotals();
});
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
  fetchCart();
});
</script>
  
<style>
/* Các kiểu CSS tùy chỉnh có thể được thêm ở đây nếu cần */
</style>
  
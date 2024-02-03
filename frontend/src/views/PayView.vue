<template>
    <div v-if="!successMessageVisible" class="container mx-auto p-8 rounded-xl px-14">
        <div class="bg-white p-6 rounded-xl flex">
            <!-- Phần thông tin khách hàng -->
            <div class="w-2/3 pr-8">
                <h2 class="text-2xl font-bold mb-4">Thông tin khách hàng</h2>
                <div class="w-2/3 pr-8">
                    <div class="mb-4 flex space-x-4">
                        <div class="flex-1">
                            <input v-model="customerName" type="text" id="customerName"
                                class="w-full p-2 border rounded-md">
                        </div>
                        <div class="flex-1">
                            <input v-model="phoneNumber" type="tel" id="phoneNumber" class="w-full p-2 border rounded-md">
                        </div>
                    </div>

                    <!-- Hàng dưới (Địa chỉ và Nút thay đổi) -->
                    <div class="mb-4 flex space-x-4">
                        <div class="flex-1">
                            <input v-model="shippingAddress" type="text" id="shippingAddress"
                                class="w-full p-2 border rounded-md">
                        </div>


                    </div>

                    <!-- Thêm trường chọn phương thức thanh toán và nhập voucher giảm giá -->
                    <!-- ... -->
                </div>


                <!-- Thêm trường chọn phương thức thanh toán -->
                <!-- Thêm trường chọn phương thức thanh toán -->
                <div class="mb-4">
                    <label for="paymentMethod" class="text-sm font-medium text-gray-800">Phương thức thanh toán:</label>
                    <select v-model="paymentMethod" id="paymentMethod" class="w-full p-2 border rounded-md">
                        <option value="creditCard">Thẻ tín dụng</option>
                        <option value="paypal">PayPal</option>
                        <option value="momo">Thanh toán Momo</option>
                        <option value="cashOnDelivery">Thanh toán khi nhận hàng</option>
                        <!-- Thêm các phương thức thanh toán khác nếu cần -->
                    </select>
                </div>

                <!-- Thêm phần hiển thị mã QR Momo và nút xác nhận thanh toán -->
                <div v-if="paymentMethod === 'momo'" class="mb-4">
                    <label class="text-sm font-medium text-gray-800">Mã QR Momo:</label>
                    <!-- Đặt một địa chỉ id cho div chứa mã QR để có thể thay đổi nội dung từ Vue -->
                    <img src='https://img.vietqr.io/image/vietinbank-113366668888-compact.jpg' />
                    <button @click="confirmMomoPayment"
                        class="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-600 focus:outline-none focus:ring focus:border-green-300">
                        Xác nhận thanh toán Momo
                    </button>
                </div>



                <!-- Thêm trường nhập voucher giảm giá -->
                <div class="mb-4">
                    <label for="discountCode" class="text-sm font-medium text-gray-800">Mã giảm giá:</label>
                    <input v-model="discountCode" type="text" id="discountCode" class="w-full p-2 border rounded-md">
                </div>


            </div>

            <!-- Phần hiển thị thông tin đơn hàng -->
            <div class="w-1/3 bg-slate-100 p-3 rounded-xl">
                <h2 class="text-2xl font-bold mb-4">Tóm tắt đơn hàng</h2>
                <div v-if="selectedItems.length === 0">
                    <p>Bạn chưa chọn sản phẩm nào để thanh toán.</p>
                </div>
                <div v-else>
                    <div v-for="item in selectedItems" :key="item.id" class="flex mb-1 border-2 p-1 rounded-xl">
                        <img :src="item.image" alt="Product Image" class="w-12 h-12 object-cover rounded-md mr-4">
                        <div class="w-1/2">
                            <p class="text-sm font-medium text-gray-800">{{ item.name }}</p>
                            <p class="text-sm font-bold text-blue-500">{{ item.price }}</p>
                        </div>
                        <div class="w-1/2 flex items-center justify-end">
                            <span v-if="!item.selected">{{ item.quantity }}</span>
                            <button @click="decrementQuantity(item)" class="text-gray-500 mr-2"
                                v-if="item.selected">-</button>
                            <span class="mr-2" v-if="item.selected">{{ item.quantity }}</span>
                            <button @click="incrementQuantity(item)" class="text-gray-500 mr-4"
                                v-if="item.selected">+</button>
                            <button @click="removeFromCart(item.id)" class="text-red-500">Xóa</button>
                        </div>
                    </div>


                </div>
                <div>
                    <div class="flex justify-between my-2">
                        <p class="text-sm font-medium text-gray-800">Tổng giá sản phẩm: </p>
                        <p class="text-sm font-medium text-right">{{ selectedTotalPrice }}</p>
                    </div>
                    <div>
                        <div class="flex justify-between mb-2">
                            <p class="text-sm font-medium text-gray-800">Mã giảm giá:</p>
                            <p class="text-sm font-medium text-right">{{ discountCode || 'Không có' }}</p>
                        </div>
                        <div class="flex justify-between mb-4">
                            <p class="text-sm font-medium text-gray-800">Chi phí vận chuyển:</p>
                            <p class="text-sm font-medium text-right">{{ shippingFee || 'Miễn phí' }}</p>
                        </div>
                        <div class="flex justify-between">
                            <p class="text-2xl font-bold text-blue-500">Tổng thanh toán:</p>
                            <p class="text-2xl font-bold">{{ grandTotal }}</p>
                        </div>
                    </div>
                </div>

                <div class="mt-4">
                    <!-- Thêm trường nhập voucher giảm giá và phương thức thanh toán nếu cần -->
                    <!-- ... -->
                    <button @click="confirmPaymennt"
                        class="mt-4 bg-blue-500 float-right text-white px-4 py-2 rounded-md hover:bg-blue-600 focus:outline-none focus:ring focus:border-blue-300">
                        Hoàn tất đơn hàng
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div v-if="successMessageVisible" class="text-green-600 mt-4 p-4 bg-green-100 rounded">
      <p class="mb-2">Giao dịch thành công! Cảm ơn bạn đã mua hàng.</p>
      <router-link to="/">
        <button  class="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600">
        Quay về trang chủ
      </button>
      </router-link>
      
    </div>
</template>
  
<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRouter } from 'vue-router';
import AddressVietNam from '../components/AddressVietNam.vue';
const router = useRouter();
const successMessageVisible = ref(false);

const confirmPaymennt = () => {
    // Thực hiện xác nhận thanh toán (có thể thêm logic xử lý thanh toán tại đây)

    // Xóa danh sách sản phẩm được chọn sau khi thanh toán
    localStorage.removeItem('selectedItems');

    // Hiện thông báo giao dịch thành công và ẩn các phần tử khác
    successMessageVisible.value = true;
};
const customerName = ref('Nguyễn Xuân Lâm');
const phoneNumber = ref('0339193576');
const shippingAddress = ref('19/44, ngõ Đỗ Thuận, phường Cầu Dền, Hà Nội');
// Thêm các biến cho địa chỉ, phương thức thanh toán, và voucher giảm giá nếu cần
// ...
const paymentMethod = ref('');
const selectedItems = ref([]);
const selectedTotalItems = ref(0);
const selectedTotalPrice = ref(0);
const updateTotals = () => {
    selectedTotalItems.value = selectedItems.value.reduce((total, item) => total + item.quantity, 0);
    selectedTotalPrice.value = selectedItems.value.reduce((total, item) => total + item.quantity * parseFloat(item.price.replace('$', '')), 0).toFixed(2);
};

const removeFromCart = (itemId) => {
    selectedItems.value = selectedItems.value.filter(item => item.id !== itemId);
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

const discountCode = ref('DISCOUNT123'); // Replace with your actual discount code
const shippingFee = ref(10); // Replace with your actual shipping fee

const grandTotal = computed(() => {
    const totalWithoutDiscount = selectedItems.value.reduce((total, item) => total + item.quantity * parseFloat(item.price.replace('$', '')), 0);
    const totalWithDiscount = applyDiscount(totalWithoutDiscount);
    return (totalWithDiscount + shippingFee.value).toFixed(2);
});

const applyDiscount = (total) => {
    // Add your discount logic here
    // For example, subtract 10% if the discount code is applied
    return discountCode.value ? total * 0.9 : total;
};
const confirmPayment = () => {
    // Thực hiện xác nhận thanh toán (có thể thêm logic xử lý thanh toán tại đây)

    // Xóa danh sách sản phẩm được chọn sau khi thanh toán
    localStorage.removeItem('selectedItems');

    // Chuyển hướng về trang chủ sau khi thanh toán
    const router = useRouter();
    router.push('/');
};

onMounted(() => {
    // Lấy danh sách sản phẩm được chọn từ giỏ hàng
    selectedItems.value = JSON.parse(localStorage.getItem('selectedItems')) || [];
    updateTotals();
});
</script>
  
<style>
/* Các kiểu CSS tùy chỉnh có thể được thêm ở đây nếu cần */
</style>
  
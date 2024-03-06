<template>
    <div v-if="!successMessageVisible" class="container mx-auto p-8 rounded-xl px-14">
        <div class="bg-white p-6 rounded-xl flex">
            <!-- Phần thông tin khách hàng -->
            <div class="w-2/3 pr-8">
                <h2 class="text-2xl font-bold mb-4">Thông tin khách hàng</h2>
                <div class="w-2/3 pr-8">
                    <div class="mb-4 flex space-x-4">
                        <div class="flex-1">
                            <p>{{ shipInformation.name }}</p>
                        </div>
                        <div class="flex-1">
                            <p>{{ shipInformation.sdt }}</p>
                        </div>
                    </div>

                    <!-- Hàng dưới (Địa chỉ và Nút thay đổi) -->
                    <div class="mb-4 flex space-x-4">
                        <div class="flex-1">
                            <p>{{ shipInformation.address }}</p>
                        </div>
                    </div>
                </div>



                <div class="mb-4">
                    <label for="paymentMethod" class="text-sm font-medium text-gray-800">Phương thức thanh toán:</label>
                    <select v-model="paymentMethod" id="paymentMethod" class="w-full p-2 border rounded-md">
                        <option value="true">Thanh toán VNPay</option>
                        <option value="false">Thanh toán khi nhận hàng</option>
                        <!-- Thêm các phương thức thanh toán khác nếu cần -->
                    </select>
                </div>


               


            </div>

            <!-- Phần hiển thị thông tin đơn hàng -->
            <div class="w-1/3 bg-slate-100 p-3 rounded-xl">
                <h2 class="text-2xl font-bold mb-4">Tóm tắt đơn hàng</h2>
                <div v-if="selectedItems.length === 0">
                    <p>Bạn chưa chọn sản phẩm nào để thanh toán.</p>
                </div>
                <div v-else>
                    <div v-for="(item, index) in selectedItems" :key="index" class="flex mb-1 border-2 p-1 rounded-xl">
                        <img :src="item.image" alt="Product Image" class="w-12 h-12 object-cover rounded-md mr-4">
                        <div class="w-1/2">
                            <p class="text-sm font-medium text-gray-800">{{ item.name }}</p>
                            <p class="text-sm font-bold text-blue-500">{{ item.price }}</p>
                        </div>
                        <div class="w-1/2 flex items-center justify-end">

                            <button @click="decrementQuantity(item)" class="text-gray-500 mr-2">-</button>
                            <span class="mr-2">{{ item.quantity }}</span>
                            <button @click="incrementQuantity(item)" class="text-gray-500 mr-4">+</button>
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
            <button class="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600">
                Quay về trang chủ
            </button>
        </router-link>

    </div>
</template>
  
<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRouter } from 'vue-router';
import apiRequest from '../service/api';
import { toast } from 'vue3-toastify';

import 'vue3-toastify/dist/index.css';
const router = useRouter();
const successMessageVisible = ref(false);
const shipInformation = ref({});
const paymentMethod = ref(null);
const isPaymentMethodSelected = computed(() => {
    return paymentMethod.value === 'true' ? true : false;
});
const fetchShipInfor = async () => {
    try {
        const response = await apiRequest('GET', 'http://localhost:8080/api/v2/shipinfor/last');
        shipInformation.value = response.data;
    } catch (error) {
        console.error('Lỗi tải', error);
    }
};
const confirmPaymennt = () => {
    if (!paymentMethod.value) {
        toast.error('Vui lòng chọn phương thức thanh toán');
        return;
    }
   addPaymennt();
}

const addPaymennt = async () => {
   
    try {
        confirmChanges(); 
        console.log(editedItems.value);
        console.log(paymentMethod.value);
        const response = await apiRequest('POST', 'http://localhost:8080/api/v2/order', {
            "shipCode": shipInformation.value.code,
            "paymentMethod": paymentMethod.value,
            "isPaid": false,
            "discount": 0.1,
            "totalAmount": grandTotal.value,
            "orderdetailsDTOS": editedItems.value
        });
        console.log(response.data);
        
        if (response.status === 200) {
            localStorage.removeItem('selectedItems');
          
            if(isPaymentMethodSelected.value){
                createPayment(grandTotal.value, "Đơn hàng", response.data)
            }else{
                successMessageVisible.value = true;
            }
        } else {
            console.error('Failed to add category. Status:', response.status);
        }
    } catch (error) {
        console.error('Error adding category:', error);
    }

    // Xóa danh sách sản phẩm được chọn sau khi thanh toán
};
const createPayment = async (amount, orderInfo, code) => {
    try {
        const response = await apiRequest('POST', `http://localhost:8080/api/v2/payment?amount=${amount}&orderInfo=${orderInfo}123&code=${code}`);            
        if (response.status === 200) {
            window.location.href = response.data;
        } else {
            console.error('Failed to add category. Status:', response.status);
        }
    } catch (error) {
        console.error('Error adding category:', error);
    }

}



const selectedItems = ref([]);
const selectedTotalItems = ref(0);
const selectedTotalPrice = ref(0);
const updateTotals = () => {
    selectedTotalItems.value = selectedItems.value.reduce((total, item) => total + item.quantity, 0);
    selectedTotalPrice.value = selectedItems.value.reduce((total, item) => total + item.quantity * item.price, 0);
};



const removeFromCart = (itemId) => {
    if (confirm("Bạn có muốn xóa không")) {
        selectedItems.value = selectedItems.value.filter(item => item.id !== itemId);
        localStorage.removeItem('selectedItems');
        updateTotals();
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
const editedItems = ref([]);
const confirmChanges = () => {
    editedItems.value = selectedItems.value.map(item => ({
        productCode: item.code,
        size: item.size,
        quantity: item.quantity,
        price: item.price
    }));
    console.log('Dữ liệu chỉnh sửa để gửi về API:', editedItems.value);
};
const shippingFee = ref(10);

const grandTotal = computed(() => {
    const total = selectedItems.value.reduce((total, item) => total + item.quantity * item.price, 0);
   
    const totalIncludingShipping = total + shippingFee.value;
    return Math.floor(totalIncludingShipping);
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
    fetchShipInfor();
});
</script>
  
<style>
/* Các kiểu CSS tùy chỉnh có thể được thêm ở đây nếu cần */
</style>
  
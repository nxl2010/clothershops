<template>
    <div class="grid grid-cols-1 lg:grid-cols-4 gap-2">
        <!-- Cột chiếm 3/4 trang -->
        <div class="col-span-3 lg:col-span-3 py-4">
            <div class="bg-white p-8 rounded-lg">
                <h1 class="text-2xl font-semibold mb-6">Thông tin cá nhân</h1>
                <div class="grid grid-cols-1 gap-6">
                    <div>
                        <label class="block text-sm font-medium text-gray-700">Họ và tên:</label>
                        <p class="text-gray-900 font-semibold mt-2">Nguyễn Xuân Lâm</p>
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700">Email:</label>
                        <p class="text-gray-900 font-semibold mt-2">lam@example.com</p>
                    </div>
                    <div>
                        <label class="block text-sm font-medium text-gray-700">Số điện thoại:</label>
                        <p class="text-gray-900 font-semibold mt-2">123-456-7890</p>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-span-3 lg:col-span-1 py-4">
            <div class="bg-white p-8 rounded-lg min-h-full flex flex-col items-center">
                <img src="https://media.vov.vn/sites/default/files/styles/large/public/2023-06/1_21.jpeg.jpg" alt=""
                    class="rounded-full w-32 h-32 mx-auto mb-4">
                <button class="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700">
                    Chọn ảnh
                </button>
            </div>
        </div>

    </div>
</template>
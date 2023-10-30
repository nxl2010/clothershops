<template>
    <div class="min-h-fit flex items-center justify-center">
      <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
        <h1 class="text-2xl font-semibold mb-6">Đổi mật khẩu</h1>
        <form @submit.prevent="submitForm">
          <div class="mb-4">
            <label for="current-password" class="block text-sm font-medium text-gray-700">Mật khẩu cũ</label>
            <input type="password" id="current-password" name="current-password" v-model="currentPassword" class="mt-1 p-2 w-full border rounded focus:ring focus:ring-blue-200" />
            <span v-if="invalidCurrentPassword" class="text-red-500 text-sm">Sai mật khẩu cũ</span>
          </div>
          <div class="mb-4">
            <label for="password" class="block text-sm font-medium text-gray-700">Mật khẩu mới</label>
            <input type="password" id="password" name="password" v-model="newPassword" class="mt-1 p-2 w-full border rounded focus:ring focus:ring-blue-200" />
            <span v-if="passwordNotStrong" class="text-red-500 text-sm">Mật khẩu chưa đủ mạnh</span>
          </div>
          <div class="mb-6">
            <label for="confirm-password" class="block text-sm font-medium text-gray-700">Xác nhận mật khẩu mới</label>
            <input type="password" id="confirm-password" name="confirm-password" v-model="confirmPassword" class="mt-1 p-2 w-full border rounded focus:ring focus:ring-blue-200" />
          </div>
          <button type="submit" class="bg-blue-500 text-white rounded p-2 w-full hover:bg-blue-600">Đổi mật khẩu</button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        currentPassword: '',
        newPassword: '',
        confirmPassword: '',
        invalidCurrentPassword: false,
        passwordNotStrong: false,
      };
    },
    methods: {
      submitForm() {
        // Kiểm tra mật khẩu cũ và tính mạnh của mật khẩu mới ở đây
        if (this.currentPasswordIsInvalid) {
          this.invalidCurrentPassword = true;
        } else {
          this.invalidCurrentPassword = false;
        }
  
        if (this.newPasswordIsWeak) {
          this.passwordNotStrong = true;
        } else {
          this.passwordNotStrong = false;
        }
  
        if (!this.invalidCurrentPassword && !this.passwordNotStrong) {
          // Gửi biểu mẫu nếu tất cả hợp lệ
          // Thực hiện tương ứng với việc thay đổi mật khẩu
          this.changePassword();
        }
      },
      changePassword() {
        // Đặt logic thay đổi mật khẩu ở đây
      },
    },
  };
  </script>
  
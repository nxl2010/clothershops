<!-- ParentComponent.vue -->
<template>
    <div>
      <AddressVietNam @submitAddress="handleAddressSubmit" />
    </div>
  </template>
  
  <script>
  import AddressVietNam from '../../components/AddressVietNam.vue';
  
  export default {
    components: {
      AddressVietNam,
    },
    methods: {
      handleAddressSubmit(addressData) {
        // Gửi dữ liệu địa chỉ tới backend
        console.log('Data to be sent to backend:', addressData);
  
        // Thực hiện axios.post hoặc các bước xử lý khác tại đây
      },
    },
  };
  </script>
    
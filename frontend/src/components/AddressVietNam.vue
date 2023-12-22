<template>
    <div>
      <select v-model="selectedProvince" @change="getDistricts">
        <option value="">-- Chọn tỉnh thành --</option>
        <option v-for="province in provinces" :key="province.code" :value="province.code">{{ province.name }}</option>
      </select>
  
      <select v-model="selectedDistrict" @change="getWards">
        <option value="">-- Chọn quận, huyện --</option>
        <option v-for="district in districts" :key="district.code" :value="district.code">{{ district.name }}</option>
      </select>
  
      <select v-model="selectedWard" @change="submitAddress">
        <option value="">-- Chọn xã --</option>
        <option v-for="ward in wards" :key="ward.code" :value="ward.code">{{ ward.name }}</option>
      </select>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        provinces: [],
        selectedProvince: '',
        districts: [],
        selectedDistrict: '',
        wards: [],
        selectedWard: '',
      };
    },
    mounted() {
      this.fetchProvinces();
    },
    methods: {
      async fetchProvinces() {
        try {
          const response = await axios.get('https://provinces.open-api.vn/api/p');
          this.provinces = response.data;
        } catch (error) {
          console.error('Lỗi khi gọi API:', error);
        }
      },
      async getDistricts() {
        try {
          if (!this.selectedProvince) {
            // Nếu chưa chọn tỉnh nào, reset dữ liệu quận/huyện và xã
            this.districts = [];
            this.selectedDistrict = '';
            this.wards = [];
            this.selectedWard = '';
            return;
          }
  
          const response = await axios.get(`https://provinces.open-api.vn/api/p/${this.selectedProvince}?depth=2`);
          this.districts = response.data.districts;
          this.selectedDistrict = '';
          this.wards = [];
          this.selectedWard = '';
        } catch (error) {
          console.error('Lỗi khi gọi API:', error);
        }
      },
      async getWards() {
        try {
          if (!this.selectedDistrict) {
            // Nếu chưa chọn quận/huyện nào, reset dữ liệu xã
            this.wards = [];
            this.selectedWard = '';
            return;
          }
  
          const response = await axios.get(`https://provinces.open-api.vn/api/d/${this.selectedDistrict}?depth=2`);
          this.wards = response.data.wards;
          this.selectedWard = '';
        } catch (error) {
          console.error('Lỗi khi gọi API:', error);
        }
      },
      submitAddress() {
        // Kiểm tra xem đã chọn đủ thông tin chưa
        if (this.selectedProvince && this.selectedDistrict && this.selectedWard) {
          // Tìm tên tỉnh tương ứng với mã tỉnh được chọn
          const selectedProvinceObject = this.provinces.find(province => province.code === this.selectedProvince);
          const provinceName = selectedProvinceObject ? selectedProvinceObject.name : '';
  
          // Tìm tên huyện tương ứng với mã huyện được chọn
          const selectedDistrictObject = this.districts.find(district => district.code === this.selectedDistrict);
          const districtName = selectedDistrictObject ? selectedDistrictObject.name : '';
  
          // Tìm tên xã tương ứng với mã xã được chọn
          const selectedWardObject = this.wards.find(ward => ward.code === this.selectedWard);
          const wardName = selectedWardObject ? selectedWardObject.name : '';
  
          // Gọi phương thức từ component cha hoặc gửi sự kiện về component cha
          this.$emit('submitAddress', {
            address: `${wardName}, ${districtName}, ${provinceName}`,
          });
        }
      },
    },
  };
  </script>
  
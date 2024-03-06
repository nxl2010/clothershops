<template>
    <div>
      <input type="file" ref="fileInput" @change="handleFileInputChange">
      <button @click="uploadImage" :disabled="!selectedFile">Upload Image to S3</button>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import axios from 'axios';
  
  const selectedFile = ref(null);
  
  const handleFileInputChange = (event) => {
    selectedFile.value = event.target.files[0];
  };
  
  const uploadImage = async () => {
    if (!selectedFile.value) {
      console.error('No file selected');
      return;
    }
  
    const file = selectedFile.value;
  
    // Thay thế bằng presigned URL bạn đã nhận được từ backend của bạn
    const presignedUrl = 'https://datn22.s3.us-east-2.amazonaws.com/f691313a-b3b4-4537-9286-31277bf5f52f.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240206T172734Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Credential=AKIAQTMSX5TM4WMTWTQ7%2F20240206%2Fus-east-2%2Fs3%2Faws4_request&X-Amz-Signature=f23650a6d4d033981fbbf12db1d12b3884ab5406b05d10412c4805f4de35b5b4';
  
    try {
      // Sử dụng axios để PUT file lên S3
      await axios.put(presignedUrl, file, {
        headers: {
          'Content-Type': file.type,
        },
      });
  
      console.log('Upload successful');
    } catch (error) {
      console.error('Error uploading file:', error);
    }
  };
  </script>
  
// api.js
import axios from "axios";
const apiRequest = (method, url, data = null) => {
  const tokenJson = localStorage.getItem('token');
  const token = JSON.parse(tokenJson).jwt;
  return axios({
    method: method,
    url: url,
    data: data,
    headers: {
      Authorization: `Bearer ${token}`, // Đặt token vào tiêu đề Authorization
    },
  });
};

export default apiRequest;

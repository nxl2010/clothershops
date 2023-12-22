import { ref } from "vue";
import axios from "axios";
import router from "../router/index.js";

const error = ref(null);
const isPending = ref(false);

import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
async function signup(username, email, fullname, password) {
    error.value = null;
    try {
        const response = await axios.post('http://localhost:8080/api/v1/auth/register', {
            userName: username,
            password: password,
            fullName: fullname,
            email: email
        });

        toast.success("Bạn đăng ký thành công");
        setTimeout(() => {
            router.push('/login');
        }, 2000);
        //Chuyển đến trang đăng nhập
       
    } catch (err) {
        if (err.response) {
            //Kiểm tra lỗi từ sever
            error.value = err.response.data; 
        } else if (err.request) {
            error.value = "No response received from the server.";
        } else {
            error.value = "An error occurred while making the request.";
        }
   
    }
   
}


  export function userSignUp() {
    return {error, isPending, signup};
  }
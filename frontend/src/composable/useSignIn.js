import { ref } from "vue";
import axios from "axios";
import router from "../router/index.js";

const error = ref(null);
const isPending = ref(false);

async function signin(username, password) {
    error.value = null;
    try {
        const response = await axios.post('http://localhost:8080/api/v1/auth/authenticate', {
            userName: username,
            password: password,
        });

        const jwt = response.data.token;
        const now = Date.now();
        const time = now + 1000 * 60  * 24;
        const timestamp = Math.floor(time / 1000);
       
        const token = {
            jwt: jwt,
            exp: timestamp,
        };

        localStorage.setItem('token', JSON.stringify(token));
        router.push('/');
    } catch (err) {
        if (err.response) {
            //Kiểm tra lỗi từ sever
            error.value = err.response.data; 
        } else if (err.request) {
            error.value = "Máy chủ không phản hồi";
        } else {
            error.value = "Lỗi thực thi";
        }
   
    }
}


  export function userSignIn() {
    return {error, isPending, signin};
  }
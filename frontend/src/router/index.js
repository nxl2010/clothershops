import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import HomeLayout from '../layout/HomeLayout.vue'
import LoginLayout from '../layout/LoginLayout.vue'
import SignUp from '../views/SignUp.vue'
import LoginVue from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import AdminLayout from '../layout/AdminLayout.vue'
import Dashboard from '../views/admin/Dashboard.vue'
import Search from '../views/Search.vue'
import NotFound from '../views/NotFound.vue'
import Product from '../views/admin/ProductCRUD.vue'
import SupplierCRUD from '../views/admin/SupplierCRUD.vue'
import ProductDashBoard from '../views/admin/ProductDashBoard.vue'
import ProductView from '../views/ProductView.vue'
import CartView from '../views/CartView.vue'
import PayView from '../views/PayView.vue'
import ListCategory from '../views/ListCategory.vue'
import ListProducts from '../views/ListProducts.vue'
import SuggestView from '../views/SuggestView.vue'

//Auth Guards 
const isAuthenticated = () => {
  const tokenJson = localStorage.getItem('token');

  // Kiểm tra xem tokenJson có tồn tại không
  if (tokenJson) {
    const tokenData = JSON.parse(tokenJson);
    const currentTime = Math.floor(Date.now() / 1000);
    console.log(currentTime);

    // Kiểm tra xem token có hợp lệ và chưa hết hạn không
    if (tokenData && tokenData.exp && currentTime < tokenData.exp) {
      console.log(tokenData.exp);
      // Người dùng đã xác thực
      return true;
    } else {
      // Token hết hạn, người dùng không được xác thực
      return false;
    }
  } else {
    // Nếu tokenJson là giá trị falsy (null hoặc undefined), người dùng không được xác thực
    return false;
  }
};

// Sử dụng hàm isAuthenticated








const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      meta: {
        requiresAuth: true,
        layout: HomeLayout
      },
      component: HomeView,

    },
    {
      path: '/carts',
      name: 'carts',
      meta:{
        layout: HomeLayout
      },
      component: CartView
    },
    {
      path: '/payment',
      name: 'pay',
      meta:{
        layout: HomeLayout
      },
      component: PayView
    },
    {
      path: '/uploadimage',
      name: 'upload',
      meta:{
        layout: HomeLayout
      },
      component: () => import('../components/uploadimage.vue')
    },
    {
      path: '/vnpay',
      name: 'vnpay',
      meta:{
        layout: HomeLayout
      },
      component: () => import('../components/vnpay.vue')
    },
    {
      path: '/product/:code',
      name: 'detailproduct',
      meta:{
        layout: HomeLayout
      },
      component: ProductView
    },
    {
      path: '/products/:category',
      name: 'ListProducts',
      meta: {
        layout: HomeLayout
      },
      component: ListProducts
    },
    {
      path: '/suggest',
      name: 'Suggest',
      meta: {
        layout: HomeLayout
      },
      component: SuggestView
    },
    {
      path: '/choesesuggest',
      name: 'ChoeseSuggest',
      meta: {
        layout: HomeLayout
      },
      component:  () => import('../views/ChoeseSuggestView.vue'),
    },

    {
      path: '/myuser',
      name: 'myuser',
      meta: {
        layout: HomeLayout
      },

      component: () => import('../views/AboutView.vue'),
      children: [
        {
          path: 'information',
          meta: {
            layout: HomeLayout
          },
          component: () => import('../components/Information.vue')
        },
        {
          path: 'shipinformation',
          meta: {
            layout: HomeLayout
          },
          component: () => import('../components/ShipInformation.vue')
        },
        {
          path: 'changepassword',
          meta: {
            layout: HomeLayout
          },
          component: () => import('../components/ChangePassword.vue')
        },
        {
          path: 'orders',
          name: 'orders',
          meta: {
            layout: HomeLayout
          },
          component: () => import('../components/MyOrder.vue')
        }

      ]
    },
    // ADMIN ROUTER
    {
      path: '/admin',
      name: 'dashboard',
      meta: {
        layout: AdminLayout,
        requiresAuth: true,
      },
      component: Dashboard
    },
    {
      path: '/admin/user',
      name: 'user',
      meta: {
        layout: AdminLayout
      },
      component: Dashboard
    },
  
    {
      path: '/admin/product',
      name: 'product',
      meta: {
        layout: AdminLayout
      },
      component: ProductDashBoard,
      children:[
        {
          path: 'supplier',
          name: 'supplier',
          meta: {
            layout: AdminLayout
          },
          component: SupplierCRUD
        },
        {
          path: 'allproduct',
          name: 'allproduct',
          component:  () => import('../views/admin/ProductCRUD.vue')
        },
        {
          path: 'category',
          name: 'category',
          component:  () => import('../views/admin/CategoryCRUD.vue')
        },
        {
          path: 'import',
          name: 'import',
          component:  () => import('../views/admin/ImportDetailsCRUD.vue')
        }
      ]
    },
  {
    path: '/search',
    name: 'search',
    meta: {
      layout: HomeLayout
    },
    component: Search
  },
  {
    path: '/login',
    name: 'login',
    meta: {

      layout: LoginLayout
    },
    component: LoginVue,
    beforeEnter: (to, from, next) => {
      // Nếu người dùng đã đăng nhập, tự động chuyển hướng đến trang home
      if (isAuthenticated()) {
        next('/');
      } else {
        next();
      }
    },


  },
  {
    path: "/logout", // Địa chỉ URL cho trang đăng xuất
    name: "Logout",
    meta: {
      layout: LoginLayout
    },
    component: Logout,
  },
  {
    path: '/signup',
    name: 'signup',
    meta: {
      layout: LoginLayout,
    },
    component: SignUp,
    beforeEnter: (to, from, next) => {
      // Nếu người dùng đã đăng nhập, tự động chuyển hướng đến trang home
      if (isAuthenticated()) {
        next('/');
      } else {
        next();
      }
    },
  },
  {
    path: '/notfound',
    name: 'NotFound',
    meta: {
      layout: LoginLayout
    },
    component: NotFound
  }
    // {
    //   path:'/product',
    //   name: 'product',
    //   meta: {
    //     layout: AdminLayout
    //   },
    //   component: Product
    // }
  ]
})
router.beforeEach((to, from, next) => {
  // Kiểm tra meta.requiresAuth để xác định xem route yêu cầu xác thực hay không
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // Nếu yêu cầu xác thực, kiểm tra xem người dùng đã đăng nhập chưa
    if (!true) {
      // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
      next('/login')
    } else {
      // Nếu đã đăng nhập, cho phép chuyển hướng
      next()
    }
  } else {
    // Nếu route không yêu cầu xác thực, cho phép chuyển hướng
    next()
  }
})
// router.beforeEach((to, from, next) => {
//   if (to.matched.length === 0) {
//     // Không có route khớp với đường dẫn, chuyển hướng đến trang Not Found
//     next({ path: '/notfound' })
//   } else {
//     // Có route khớp, tiếp tục điều hướng bình thường
//     next()
//   }
// })

export default router

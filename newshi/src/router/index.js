import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Article from "../views/Article.vue";
import Main from "../views/Main.vue";
import MyPage from "../views/MyPage.vue";
import MyInfo from "../components/mypage/MyInfo";

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: "/article",
    name: "Article",
    component: Article,
  },
  {
    path: "/main",
    name: "Main",
    component: Main,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
    children: [
      {
        path: "/",
        name: "MyInfo",
        component: MyInfo
      }
    ]
  }
];

const router = new VueRouter({

  mode: "history",
  routes

});

export default router;

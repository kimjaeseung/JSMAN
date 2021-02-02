import Vue from 'vue';
import VueRouter from 'vue-router';
import Article from '../views/Article.vue';
import Main from '../views/Main.vue';
import MyPage from '../views/MyPage.vue';
import FindPw from '../views/FindPw.vue';
import ChangePw from '../views/ChangePw.vue';
import MyInfo from '../components/mypage/MyInfo';
import Channel from "../views/Channel.vue";
import Scrab from "../components/channel/Scrab.vue";
import ScrabNews from "../components/channel/ScrabNews.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: '/article/:newsNo',
    name: 'Article',
    component: Article,
    props: true,
  },
  {
    path: '/findpw',
    name: 'FindPw',
    component: FindPw,
  },
  {
    path: '/changepw/:id',
    name: 'ChangePw',
    component: ChangePw,
    props: true,
  },
  {
    path: '/',
    name: 'Main',
    component: Main,
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: MyPage,
    children: [
      {
        path: "/",
        name: "MyInfo",
        component: MyInfo
      }
    ]
  },
  {
    path: "/channel/:id",
    name: "Channel",
    component: Channel,
    children: [
      {
        path: "",
        name: "Scrab",
        component: Scrab,
      },
      {
        path: ":scrab_no",
        name: "ScrabNews",
        component: ScrabNews,
      }
    ]
  },
  
];

const router = new VueRouter({
  mode: 'history',
  routes,
});

export default router;

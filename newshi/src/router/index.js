import Vue from 'vue';
import VueRouter from 'vue-router';
import Article from '../views/Article.vue';
import Main from '../views/Main.vue';
import MyPage from '../views/MyPage.vue';
import FindPw from '../views/FindPw.vue';
import ChangePw from '../views/ChangePw.vue';
import Feedback from '../views/Feedback.vue';
import MyInfo from '../components/mypage/MyInfo';
import Channel from '../views/Channel.vue';
import Scrap from '../components/channel/Scrap.vue';
import ScrapNews from '../components/channel/ScrapNews.vue';
import ToScrap from '../components/channel/ToScrap.vue';
import ModifyMyinfo from '../components/mypage/ModifyMyinfo.vue';
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
    path: '/feedback',
    name: 'Feedback',
    component: Feedback,
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: MyPage,
    children: [
      {
        path: '/',
        name: 'MyInfo',
        component: MyInfo,
      },
      {
        path: 'modify',
        name: 'ModifyMyinfo',
        component: ModifyMyinfo,
      },
    ],
  },
  {
    path: '/channel/:id',
    name: 'Channel',
    component: Channel,
    children: [
      {
        path: '',
        name: 'ToScrap',
        component: ToScrap,
      },
      {
        path: 'main',
        name: 'Scrap',
        component: Scrap,
        props: true,
      },
      {
        path: ':scrap_no',
        name: 'ScrapNews',
        component: ScrapNews,
      },
      {
        path: 'modify/:scrap_no',
        name: 'ModifyScrap',
      },
    ],
  },
];

const router = new VueRouter({
  mode: 'history',
  routes,
});

export default router;

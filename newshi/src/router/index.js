import Vue from 'vue';
import VueRouter from 'vue-router';
import Article from '../views/Article.vue';
import Main from '../views/Main.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/article',
    name: 'Article',
    component: Article,
  },
  {
    path: '/',
    name: 'Main',
    component: Main,
  },
];

const router = new VueRouter({
  mode: 'history',
  routes,
});

export default router;

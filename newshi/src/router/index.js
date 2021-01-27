import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Main from "../views/Main.vue";
import Article from "../views/Article.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/article/:newsNo",
    name: "Article",
    component: Article,
    props: true,
  },
  {
    path: "/main",
    name: "Main",
    component: Main,
  }
];

const router = new VueRouter({
  mode: "history",
  routes
});

export default router;

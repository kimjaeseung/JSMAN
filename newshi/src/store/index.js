import Vue from 'vue';
import Vuex from 'vuex';
import googleUser from '@/store/googleUser';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    googleUser,
  },
});

export default store;

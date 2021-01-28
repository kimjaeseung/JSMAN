import Vue from 'vue';
import Vuex from 'vuex';
import { getInfo } from '@/api/user.js';
Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    userProfile: {},
    tags: {},
    loggedIn: false,
  },
  getters: {
    userProfile: ({ userProfile }) => userProfile,
    tags: ({ tags }) => tags,
    loggedIn: ({ loggedIn }) => loggedIn,
  },
  mutations: {
    SET_USER(state, userProfile) {
      state.userProfile = userProfile;
    },
    SET_TAGS(state, tags) {
      state.tags = tags;
    },
    SET_LOGGED(state, loggedIn) {
      state.loggedIn = loggedIn;
    },
  },
  actions: {
    getUserInfo() {
      if (localStorage['access-token'] && localStorage['access-token'] !== '') {
        getInfo(
          localStorage.id,
          (response) => {
            if (response.data.message === 'success') {
              let user = response.data['userInfo'];
              let tags = response.data['tags'];
              this.SET_USER(user);
              this.SET_TAGS(tags);
            } else {
              this.isLoginError = true;
            }
          },
          (error) => {
            console.error(error);
            alert('아이디 또는 비밀번호가 일치하지 않습니다.');
          }
        );
      }
    },
  },
  modules: {},
});

export default store;

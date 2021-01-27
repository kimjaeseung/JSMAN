import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import vuetify from './plugins/vuetify';
import { ValidationProvider, extend } from 'vee-validate';

// Add a rule.
extend('secret', {
  validate: (value) => value === 'example',
  message: 'This is not the magic word',
});

// Register it globally
Vue.component('ValidationProvider', ValidationProvider);
// import { ValidationProvider } from 'vee-validate/dist/vee-validate.full.esm';
// import { ValidationObserver } from 'vee-validate';

// extend('password', {
//   getMessage: (field) => `${field} should include lower-case,
//   numeric digit, special chracter($@$!%*#?&).`,
//   validate: (value) => {
//     return /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(value);
//   },
// });

// Vue.component('ValidationProvider', ValidationProvider);
// Vue.component('ValidationObserver', ValidationObserver);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount('#app');

<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" width="500" transition="dialog-top-transition">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="primary" dark v-bind="attrs" v-on="on" icon>
          <v-icon>mdi-account-outline</v-icon>
        </v-btn>
      </template>
      <v-card v-if="isLogin">
        <v-toolbar dark color="primary">
          <v-btn icon dark @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-toolbar-title> 로그인</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn dark text @click="isLogin = !isLogin">
              회원가입
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>

        <v-form class="pa-6" @submit.prevent="onSubmit">
          <ValidationProvider
            name="id"
            rules="required|email"
            v-slot="{ errors }"
          >
            <v-text-field
              v-model="id"
              :error-messages="errors"
              label="E-mail"
              required
            ></v-text-field>
          </ValidationProvider>

          <ValidationProvider
            name="password"
            rules="required|password"
            v-slot="{ errors }"
          >
            <v-text-field
              v-model="password"
              type="password"
              :error-messages="errors"
              label="비밀번호"
              required
            ></v-text-field>
            <br />
          </ValidationProvider>
          <v-btn :disabled="!isValid" color="primary" @click="onSubmit"
            >로그인</v-btn
          >
        </v-form>
      </v-card>
      <v-card v-else>
        <v-toolbar dark color="primary">
          <v-btn icon dark @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-toolbar-title> 회원가입</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn dark text @click="isLogin = !isLogin">
              로그인
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <v-form class="pa-6" @submit.prevent="onJoin">
          <ValidationProvider
            name="id"
            rules="required|email"
            v-slot="{ errors }"
          >
            <v-text-field
              v-model="id"
              :error-messages="errors"
              label="E-mail"
              required
            ></v-text-field>
          </ValidationProvider>

          <ValidationProvider
            name="password"
            rules="required|password"
            v-slot="{ errors }"
          >
            <v-text-field
              v-model="password"
              type="password"
              :error-messages="errors"
              label="비밀번호"
              required
            ></v-text-field>
          </ValidationProvider>

          <ValidationProvider
            name="passwordConfirm"
            rules="required|password|passwordConfirm:@password"
            v-slot="{ errors }"
          >
            <v-text-field
              v-model="passwordConfirm"
              type="password"
              :error-messages="errors"
              label="비밀번호 확인"
              required
            ></v-text-field>
          </ValidationProvider>
          <v-btn :disabled="!isValid" color="primary" @click="onJoin"
            >회원가입</v-btn
          >
        </v-form>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { login } from '@/api/user.js';
import { ValidationProvider } from 'vee-validate';
import { extend } from 'vee-validate';
import * as rules from 'vee-validate/dist/rules';

Object.keys(rules).forEach((rule) => {
  extend(rule, rules[rule]);
});
extend('password', {
  message:
    'password should include lower-case, numeric digit, special chracter($@$!%*#?&).',
  validate: (value) => {
    return /^.*(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$@$!%*#?&]).*$/.test(value);
  },
});
extend('passwordConfirm', {
  params: ['target'],
  validate(value, { target }) {
    return value === target;
  },
  message: 'Password confirmation does not match',
});
export default {
  components: {
    ValidationProvider,
  },

  data: () => ({
    dialog: null,
    id: '',
    password: '',
    passwordConfirm: '',
    isValid: false,
    isLogin: true,
  }),
  watch: {
    id: function() {
      this.checkForm();
    },
    password: function() {
      this.checkForm();
    },
  },
  methods: {
    onSubmit() {
      if (!this.isValid)
        return alert('내용을 다시 한번 확인해주시길 바랍니다.');
      let { id, password } = this;
      let info = {
        id,
        password,
      };
      localStorage.setItem('access-token', '');
      login(
        info,
        (response) => {
          if (response.data.message === 'success') {
            let token = response.data['access-token'];
            localStorage.setItem('access-token', token);
            localStorage.setItem('id', info.id);
            this.$router.push('/');
          } else {
            this.isLoginError = true;
          }
        },
        (error) => {
          console.error(error);
          alert('아이디 또는 비밀번호가 일치하지 않습니다.');
        }
      );
    },
    checkForm() {
      if (this.id == '' || this.password == '') this.isValid = false;
      else this.isValid = this.id && this.password;
    },
    onJoin() {},
  },
};
</script>

<style></style>

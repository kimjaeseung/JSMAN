<template>
  <v-container>
    <h2>비밀번호 변경</h2>
    <h3>비밀번호를 입력하시면 비밀번호가 변경됩니다</h3>
    <v-form class="pa-6" @submit.prevent="changePw">
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

      <v-btn color="orange lighten-4" @click="changePw">비밀번호 변경</v-btn>
    </v-form>
  </v-container>
</template>

<script>
import { changePassword } from '@/api/user.js';
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
  data() {
    return {
      id: '',
      password: '',
      passwordConfirm: '',
    };
  },
  created: function() {
    this.id = this.$route.params.id;
    console.log(this.id);
  },
  methods: {
    changePw() {
      let { id, password } = this;
      let info = {
        id,
        password,
      };
      changePassword(
        info,
        (response) => {
          if (response.data.message === 'success') {
            alert('비밀번호 변경에 성공했습니다.');
            this.$router.push('/');
          } else {
            alert('비밀번호 변경에 실패했습니다. 다시한번 진행해주세요');
            this.password = '';
            this.passwordConfirm = '';
          }
        },
        (error) => {
          console.error(error);
          alert('비밀번호 변경 중 오류가 났습니다.');
        }
      );
    },
  },
};
</script>

<style></style>

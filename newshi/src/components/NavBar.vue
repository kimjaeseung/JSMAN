<template>
  <v-app>
    <v-app-bar flat app style="border-bottom:1px solid">
      <v-app-bar-nav-icon @click="openNavbar()"></v-app-bar-nav-icon>
      <v-spacer />
      <v-img contain src="@/assets/logo.png" style="height: 50%"></v-img>
      <v-spacer />
      <v-icon @click="search_drawer = !search_drawer">mdi-magnify</v-icon>
      <v-dialog
        v-model="dialog"
        width="500"
        :fullscreen="isFull"
        transition="dialog-top-transition"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn color="black" dark v-bind="attrs" v-on="on" icon>
            <v-icon>mdi-account-outline</v-icon>
          </v-btn>
        </template>
        <v-card v-if="isLogin">
          <v-toolbar
            color="orange lighten-4
"
          >
            <v-btn icon @click="dialog = false">
              <v-icon>mdi-close</v-icon>
            </v-btn>
            <v-toolbar-title> 로그인</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
              <v-btn text @click="isLogin = !isLogin">
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
                autocapitalize="off"
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
            <v-btn
              :disabled="!isValid"
              color="orange lighten-4
"
              @click="onSubmit"
              >로그인</v-btn
            >
          </v-form>
        </v-card>
        <v-card v-else>
          <v-toolbar
            color="orange lighten-4
"
          >
            <v-btn icon @click="dialog = false">
              <v-icon>mdi-close</v-icon>
            </v-btn>
            <v-toolbar-title> 회원가입</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
              <v-btn text @click="isLogin = !isLogin">
                로그인
              </v-btn>
            </v-toolbar-items>
          </v-toolbar>
          <v-form class="pa-6" @submit.prevent="onJoin">
            <ValidationProvider
              name="joinId"
              rules="required|email"
              v-slot="{ errors }"
            >
              <v-text-field
                v-model="formData.id"
                :error-messages="errors"
                label="E-mail"
                required
                autocapitalize="off"
              ></v-text-field>
              <v-btn v-show="!isDupEmailCheck" @click="emailCheck()"
                >Email 인증</v-btn
              >
            </ValidationProvider>

            <v-text-field
              v-model="validNum"
              required
              v-show="isDupEmailCheck"
              placeholder="인증번호를 입력해주세요"
            ></v-text-field>
            <v-btn v-show="isDupEmailCheck" @click="emailValidCheck()"
              >인증번호 확인</v-btn
            >
            <v-btn v-show="isDupEmailCheck" @click="resendEmail()"
              >재발송</v-btn
            >

            <ValidationProvider
              name="joinName"
              rules="required|min:2"
              v-slot="{ errors }"
            >
              <v-text-field
                v-model="formData.name"
                :error-messages="errors"
                label="name"
                required
                autocapitalize="off"
              ></v-text-field>
              <v-btn v-show="!isDupNameCheck" @click="nameCheck()"
                >중복검사</v-btn
              >
              <v-btn disabled v-show="isDupNameCheck">검사완료</v-btn>
            </ValidationProvider>

            <ValidationProvider
              name="joinPassword"
              rules="required|password"
              v-slot="{ errors }"
            >
              <v-text-field
                v-model="formData.password"
                type="password"
                :error-messages="errors"
                label="비밀번호"
                required
              ></v-text-field>
            </ValidationProvider>

            <ValidationProvider
              name="joinPasswordConfirm"
              rules="required|password|passwordConfirm:@joinPassword"
              v-slot="{ errors }"
            >
              <v-text-field
                v-model="formData.passwordConfirm"
                type="password"
                :error-messages="errors"
                label="비밀번호 확인"
                required
              ></v-text-field>
            </ValidationProvider>
            <span><h4>관심 목록</h4></span>
            <v-row justify="space-around">
              <v-col cols="10" sm="10" md="8">
                <v-sheet outlined width="330">
                  <v-chip-group
                    v-model="formData.tags"
                    multiple
                    active-class="yellow --text"
                  >
                    <v-chip border large v-for="tag in tags" :key="tag">
                      {{ tag }}
                    </v-chip>
                  </v-chip-group>
                </v-sheet>
              </v-col>
            </v-row>
            <br />
            <v-btn :disabled="!isValid" color="orange lighten-4" @click="onJoin"
              >회원가입</v-btn
            >
          </v-form>
        </v-card>
      </v-dialog>
    </v-app-bar>

    <v-navigation-drawer
      right
      bottom
      v-model="search_drawer"
      absolute
      temporary
    >
      <v-autocomplete
        :search-input.sync="search_word"
        :items="autocomp_value"
        filled
      ></v-autocomplete>
      <br />{{ search_word }}
    </v-navigation-drawer>

    <v-navigation-drawer v-model="menu_drawer" absolute temporary>
      <v-list>
        <v-list-item-group>
          <v-list-item>
            <v-list-item-avatar>
              <v-img :src="member.thumbnail_path"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>{{ member.name }}</v-list-item-title>
              <v-list-item-subtitle>{{ member.id }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-divider />

          <v-list-item v-for="(menu, index) in menus" :key="index">
            <v-list-item-icon>
              <v-icon>mdi-{{ menu.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-title>
              {{ menu.title }}
            </v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </v-app>
</template>

<script>
import {
  login,
  join,
  emailTest,
  emailValidTest,
  nameTest,
} from '@/api/user.js';
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
      menu_drawer: false,
      search_drawer: false,
      member: {},
      autocomp_value: [],
      search_word: '',
      menus: [
        {
          icon: 'newspaper-variant-multiple-outline',
          title: '내가 댓글 남긴 기사',
        },
        { icon: 'newspaper-plus', title: '언론사 선택하기' },
        { icon: 'brightness-6', title: '다크모드' },
        { icon: 'email-open-outline', title: '피드백 보내기' },
        { icon: 'comment-processing-outline', title: '댓글 운영 정책' },
        { icon: 'home', title: '홈페이지 바로가기' },
        { icon: 'information-outline', title: '버전 정보' },
      ],
      mounted_flag: false,
      dialog: null,
      id: '',
      password: '',
      formData: {
        id: '',
        name: '',
        password: '',
        passwordConfirm: '',
        tags: [],
        isValid: false,
      },
      isValid: false,
      isLogin: true,
      isDupEmailCheck: false,
      isEmailValid: false,
      isDupNameCheck: false,
      validNum: '',
      tags: [
        '속보',
        '정치',
        '경제',
        '사회',
        '생활/문화',
        '세계/국제',
        'IT/과학',
        '오피니언',
      ],
    };
  },
  computed: {
    isFull() {
      let check = false;
      switch (this.$vuetify.breakpoint.name) {
        case 'xs':
          check = true;
          break;
        case 'sm':
          check = true;
          break;
        case 'md':
          check = false;
          break;
        case 'lg':
          check = false;
          break;
        case 'xl':
          check = false;
          break;
      }
      return check;
    },
  },
  methods: {
    openNavbar: function() {
      // axios request: id로 이름과 썸네일을 불러오도록 한다.
      //일단은 그냥 받아온것처럼 함
      this.member = {
        name: '김재성',
        id: 'kimjea23@naver.com',
        thumbnail_path: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
      };
      this.menu_drawer = true;
    },
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
      if (this.isLogin) {
        if (this.id == '' || this.password == '') this.isValid = false;
        else this.isValid = this.id && this.password;
      } else {
        this.formData.isValid =
          this.formData.id &&
          this.formData.name &&
          this.formData.password &&
          this.formData.passwordConfirm;
      }
    },
    emailCheck() {
      //   if (!validator.validate('joinId')) {
      //     this.emailValid = false;
      //     alert('입력하신 아이디는 이메일 형식이 아닙니다.');
      //     return;
      //   }
      emailTest(
        this.formData.id,
        (response) => {
          if (response.data.message === 'success') {
            this.isDupEmailCheck = true;
            alert(
              '입력하신 아이디로 인증메일을 발송하였습니다. 인증번호를 입력해주세요.'
            );
          } else {
            this.isDupEmailCheck = false;
            alert(
              '입력하신 아이디는 이미 등록된 아이디입니다. 다시 한번 확인해주시기 바랍니다.'
            );
          }
        },
        (error) => {
          console.error(error);
          alert('아이디를 다시 한번 확인해주세요.');
        }
      );
    },
    emailValidCheck() {
      emailValidTest(
        this.validNum,
        (response) => {
          if (response.data.message === 'success') {
            this.isEmailValid = true;
            alert('이메일 인증이 완료되었습니다.');
          } else {
            this.isDupEmailCheck = false;
            this.isEmailValid = false;
            alert('이메일 인증에 실패했습니다. ');
          }
        },
        (error) => {
          console.error(error);
          alert('이메일 인증에 오류가 발생했습니다.');
        }
      );
    },
    resendEmail() {
      emailTest(
        this.formData.id,
        (response) => {
          if (response.data.message === 'success') {
            this.isDupEmailCheck = true;
            alert(
              '입력하신 아이디로 재발송하였습니다. 인증번호를 입력해주세요.'
            );
          } else {
            this.isDupEmailCheck = false;
            alert('재발송에 실패했습니다. 다시 한번 확인해주시기 바랍니다.');
          }
        },
        (error) => {
          console.error(error);
          alert('재발송 중 에러가 발생했습니다. 죄송합니다.');
        }
      );
    },
    nameCheck() {
      //   if (!validator.validate('joinId')) {
      //     this.emailValid = false;
      //     alert('입력하신 이름은 잘못된 형식입니다.');
      //     return;
      //   }
      nameTest(
        this.formData.name,
        (response) => {
          if (response.data.message === 'success') {
            this.isDupNameCheck = true;
            alert('사용가능한 이름입니다.');
          } else {
            this.isDupNameCheck = false;
            alert('입력하신 이름은 이미 등록된 이름입니다.');
          }
        },
        (error) => {
          console.error(error);
          alert('이름을 다시 한번 확인해주세요.');
        }
      );
    },
    onJoin() {
      if (!this.formData.isValid) {
        alert('모든 정보를 입력해주시기 바랍니다.');
        return;
      } else if (!this.isDupEmailCheck) {
        alert('이메일 인증을 해야 합니다.');
        return;
      } else if (!this.isEmailValid) {
        alert('이메일 인증번호를 검사해야 합니다.');
        return;
      } else if (!this.isDupNameCheck) {
        alert('이름 중복 검사를 해야합니다.');
        return;
      }
      let info = {
        id: this.formData.id,
        name: this.formData.name,
        password: this.formData.password,
        tags: this.formData.tags,
      };

      join(
        info,
        (response) => {
          if (response.data.message === 'success') {
            alert('회원가입에 성공했습니다.');
            let token = response.data['access-token'];
            localStorage.setItem('access-token', token);
            localStorage.setItem('id', info.id);
            this.$router.push('/');
          } else {
            this.isDupEmailCheck = false;
            this.isEmailValid = false;
            this.isDupNameCheck = false;
            alert('회원가입에 실패했습니다. 다시 한번 확인해주세요.');
          }
        },
        (error) => {
          console.error(error);
          alert('회원가입 중 오류가 발생했습니다.');
        }
      );
    },
  },
  watch: {
    search_word: function() {
      console.log('검색어 변경');

      if (this.search_word == undefined) return;

      if (this.search_word == '') this.autocomp_value = [];
      else if (this.search_word.charAt(0) == '#') {
        // 태그에 접근하는 axios
        this.autocomp_value = ['#경제', '#시사', '#IT', '#감성'];
      } else {
        // 큐레이터에 접근하는 axios
        this.autocomp_value = ['사람1', '사람2', '사람3', '감재성'];
      }
    },
    id: function() {
      this.checkForm();
    },
    password: function() {
      this.checkForm();
    },
    formData: function() {
      this.checkForm();
    },
  },

  created() {},
};
</script>

<style></style>

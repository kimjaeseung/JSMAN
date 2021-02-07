<template>
  <div>
    <v-app-bar flat app style="border-bottom:1px solid">
      <v-app-bar-nav-icon
        @click="menu_drawer = !menu_drawer"
      ></v-app-bar-nav-icon>
      <v-spacer />
      <a href="/">
      <div v-if="this.switchTheme == 'true'">
        <v-img contain src="@/assets/logo_darkmode.png"></v-img>
      </div>
      <div v-else>
        <v-img contain src="@/assets/logo_lightmode.png"></v-img>
      </div>
      </a>
      <v-spacer />
      <v-icon @click="search_drawer = !search_drawer">mdi-magnify</v-icon>
      <v-dialog
        v-model="dialog"
        width="500"
        height="500"
        v-if="!logged"
        :fullscreen="isFull"
        transition="dialog-top-transition"
      >
        <template v-slot:activator="{ on: dialog, attrs }">
          <v-tooltip bottom>
            <template v-slot:activator="{ on: tooltip }">
              <v-btn
                v-bind="attrs"
                v-on="{ ...tooltip, ...dialog }"
                icon
              >
                <v-icon>mdi-account</v-icon>
              </v-btn>
            </template>
            <span>로그인</span>
          </v-tooltip>
        </template>
        <Login
          v-if="isLogin"
          @closeDialog="closeDialog"
          @changeJoin="changeJoin"
        ></Login>
        <Join
          v-else
          :isKakao="isKakao"
          :user="info"
          @closeDialog="closeDialog"
          @changeLogin="changeLogin"
          @changeKakao="changeKakao"
        ></Join>
      </v-dialog>
      <v-menu open-on-hover offset-y v-else>
        <template v-slot:activator="{ on, attrs }">
          <v-btn color="black" dark v-bind="attrs" v-on="on" icon>
            <v-icon>mdi-account</v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item>
            <v-list-item-title
              ><v-btn @click="myPage">마이페이지</v-btn></v-list-item-title
            >
          </v-list-item>
          <v-list-item>
            <v-list-item-title
              ><v-btn @click="logout">로그아웃</v-btn></v-list-item-title
            >
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
    <v-navigation-drawer right bottom v-model="search_drawer" fixed temporary>
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
          <v-list-item v-if="logged">
            <v-list-item-avatar>
              <v-img :src="member.thumbnail_path"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>{{ member.name }}</v-list-item-title>
              <v-list-item-subtitle>{{ member.id }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item v-else>
            <v-list-item-avatar>
              <v-icon>mdi-account-outline</v-icon>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>로그인이 필요합니다</v-list-item-title>
              <v-list-item-subtitle>로그인 해주세요</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-divider />
          <v-list-item v-for="(menu, index) in menus" :key="index">
            <v-list-item-icon>
              <v-icon>mdi-{{ menu.icon }}</v-icon>
            </v-list-item-icon>
            <router-link :to="menu.router">
              <v-list-item-title>
                {{ menu.title }}
              </v-list-item-title>
            </router-link>
            <v-switch
              v-if="menu.title === '다크모드'"
              v-model="switchTheme"
              value="true"
              @click="changeTheme()"
              inset
              dense
              color="orange"
              class="mx-3"
            ></v-switch>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>
<script>
import Login from '@/components/Login.vue';
import Join from '@/components/Join.vue';
import { mapActions } from 'vuex';

const localThemeMode = localStorage.getItem('themeMode');

export default {
  components: {
    Login,
    Join,
  },
  data() {
    return {
      menu_drawer: false,
      search_drawer: false,
      member: {
        name: '김재성',
        id: 'kimjea23@naver.com',
        thumbnail_path: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
      },
      autocomp_value: [],
      search_word: '',
      menus: [
        {
          icon: 'newspaper-variant-multiple-outline',
          title: '나중에 볼 기사',
          router: '/save'
        },
        { icon: 'newspaper-plus', title: '언론사 선택하기', router: '/press' },
        { icon: 'brightness-6', title: '다크모드', router: '' },
        { icon: 'email-open-outline', title: '피드백 보내기', router: '/feedback' },
        { icon: 'comment-processing-outline', title: '댓글 운영 정책', router: '/commentpolicy' },
        { icon: 'home', title: '홈페이지 바로가기', router: '/home' },
        { icon: 'information-outline', title: '버전 정보', router: '/version' },
      ],
      mounted_flag: false,
      dialog: null,
      isLogin: true,
      isKakao: false,
      info: {},
      logged: true,
      switchTheme: '',
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
    ...mapActions(['logout']),
    closeDialog() {
      this.dialog = !this.dialog;
      this.isLogin = true;
    },
    changeJoin() {
      this.isLogin = false;
    },
    changeLogin() {
      this.isLogin = true;
    },
    changeKakao: function(info) {
      this.info = info;
      this.isLogin = false;
      this.isKakao = true;
    },
    myPage() {
      this.$router.push('/mypage');
    },
    changeTheme() {
        // true일 때 darkmode, false일 때 lightmode
        this.$store.dispatch('getThemeMode', this.switchTheme)
        this.$vuetify.theme.dark = this.switchTheme
    }
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
  },
  created() {
    this.logged = this.$store.getters.loggedIn;
    this.member = this.$store.getters.userProfile;
    this.switchTheme = localThemeMode;
    localThemeMode.toString() == 'true' ? this.$vuetify.theme.dark = true: this.$vuetify.theme.dark = false; // 시작하자마자 다크테마인지 아닌지 체크
    console.log(this.logged);
    console.log(this.member);
  },
  
};
</script>

<style>
#switch {
  display: inline;
}
.theme--dark.v-app-bar.v-toolbar.v-sheet {
  background-color: #1E1E1E !important;
}
.theme--dark.v-navigation-drawer{
  background-color: #252525 !important;
}
.theme--light.v-app-bar.v-toolbar.v-sheet {
  background-color: #ffffff !important;
}
.v-application a {
  text-decoration: none;
  color: inherit !important;
}
.v-list-item__icon {
  margin-left: 0px !important;
}
</style>

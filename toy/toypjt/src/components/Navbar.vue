<template>
  <div>
    <v-navigation-drawer v-model="drawer" app absolute temporary hide-overlay>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="title">
            <v-icon large @click="drawer = !drawer">
              mdi-window-close
            </v-icon>
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list>
        <v-list-group
          v-for="item in items"
          :key="item.title"
          v-model="item.active"
          no-action
        >
          <template v-slot:activator>
            <v-list-item-content>
              <v-list-item-title v-text="item.title"></v-list-item-title>
            </v-list-item-content>
          </template>

          <v-list-item v-for="child in item.items" :key="child.title">
            <v-list-item-content>
              <v-list-item-title
                ><a href="#" style="color: black;text-decoration:none">{{
                  child.title
                }}</a></v-list-item-title
              >
            </v-list-item-content>
          </v-list-item>
        </v-list-group>
      </v-list>
      <v-btn class="ma-6" outlined min-height="32" min-width="190"
        >멤버십가입</v-btn
      >
    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title class="ps-16">
        <v-btn depressed @click="home()">Book Journalism</v-btn>
      </v-toolbar-title>
      <v-spacer></v-spacer>

      <v-btn outlined max-height="23" max-width="96">
        멤버십가입
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-newspaper-variant</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-menu open-on-hover offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn color="black" dark v-bind="attrs" v-on="on" icon>
            <v-icon>mdi-account-outline</v-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item>
            <v-list-item-title
              ><a href="#" style="color: black;text-decoration:none"
                >로그인</a
              ></v-list-item-title
            >
          </v-list-item>
          <v-list-item>
            <v-list-item-title
              ><a href="#" style="color: black;text-decoration:none"
                >회원가입</a
              ></v-list-item-title
            >
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  data: () => ({
    items: [
      {
        items: [
          { title: '전체', to: '/' },
          { title: '세계', to: '/' },
          { title: '테크', to: '/' },
          { title: '컬처', to: '/' },
          { title: '경제', to: '/' },
          { title: '정치', to: '/' },
          { title: '사회', to: '/' },
          { title: '워크', to: '/' },
          { title: '지구', to: '/' },
        ],
        title: '책',
      },
      {
        active: true,
        items: [
          { title: '전체', to: '/' },
          { title: '정치', to: '/' },
          { title: '경제', to: '/' },
          { title: '사회', to: '/' },
        ],
        title: '뉴스',
      },
      {
        title: '모임',
        to: '/',
      },
      {
        title: '오디오',
        to: '/',
      },
      {
        items: [
          { title: '전체', to: '/' },
          { title: '정치', to: '/' },
          { title: '경제', to: '/' },
          { title: '사회', to: '/' },
        ],
        title: '토론',
      },
      {
        items: [
          { title: '젊은 혁신가 인터뷰', to: '/' },
          { title: '뉴스 브리핑', to: '/' },
        ],
        title: '뉴스레터',
      },
      {
        items: [
          { title: '브랜드 소개', to: '/' },
          { title: '팟캐스트', to: '/' },
          { title: '블로그', to: '/' },
          { title: '기업 고객', to: '/' },
          { title: '문의', to: '/' },
        ],
        title: '더 보기',
      },
    ],
    drawer: null,
  }),
  methods: {
    home() {
      this.$router.push('/').catch((error) => {
        if (error.name === 'NavigationDuplicated') {
          location.reload();
        }
      });
    },
  },
};
</script>

<style></style>

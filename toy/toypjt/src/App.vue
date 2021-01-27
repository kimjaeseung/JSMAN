<template>
  <v-app id="inspire">
    <router-view></router-view>
  </v-app>
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
      this.$router.push('/');
    },
  },
};
</script>

<template>
  <v-container>
    <v-row class="justify-center align-center" no-gutters>
      <v-banner two-line>
        <template v-slot:actions>
          {{ url }}
          <v-btn text color="deep-purple accent-4" @click="copyUrl">
            <v-icon>
              mdi-content-copy
            </v-icon>
            복사하기
          </v-btn>
          <v-btn text color="deep-purple accent-4" @click="kakaoCopy">
            <v-img src="@/assets/images/kakaolink_btn_small.png"></v-img>
            카카오톡 공유
          </v-btn>
        </template>
      </v-banner>
    </v-row>
    <v-row class="align-end" no-gutters>
      <v-btn
        ><a :href="url" style="color: black; text-decoration: none"
          >해당 포스트로 이동</a
        ></v-btn
      >
      <v-btn @click="goMain">메인페이지로 이동</v-btn>
    </v-row>
  </v-container>
</template>

<script>
export default {
  props: {
    postNo: Number,
  },
  data() {
    return {
      url: '',
      id: '',
    };
  },
  methods: {
    copyUrl() {
      this.$copyText(this.url);
      alert('주소를 복사했습니다.');
    },
    kakaoCopy() {
      let desc = prompt('설명을 작성해주세요.', `${this.id} 포스트입니다.`);
      window.Kakao.Link.sendDefault({
        objectType: 'feed',
        content: {
          title: this.postName,
          description: desc,
          imageUrl: 'https://newha.s3.us-east-2.amazonaws.com/logo.jpg',
          link: {
            webUrl: this.url,
            mobileWebUrl: this.url,
          },
        },
        buttons: [
          {
            title: '해당 포스트로 이동',
            link: {
              webUrl: this.url,
            },
          },
        ],
      });
    },
    gomain() {
      this.$router.push('/');
    },
  },
  created() {
    this.id = localStorage['id'];
    this.url = `http://localhost:8081/${this.id}/${this.postNo}`;
    // this.url = `http://i4a307.p.ssafy.io/${id}/${postNo}`;
  },
};
</script>

<style></style>

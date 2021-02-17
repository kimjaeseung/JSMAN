<template>
  <v-container>
    <v-row class="justify-center align-center" no-gutters>
      <v-banner two-line>
        <template v-slot:actions>
          <v-row>
            {{ url }}
          </v-row>
          <v-row>
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
          </v-row>
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
import { API_BASE_URL } from '@/config';

export default {
  props: {
    postNo: String,
    postName: String,
    tags: String,
  },
  data() {
    return {
      url: '',
      id: '',
    };
  },
  methods: {
    copyUrl() {
      this.$copyText(this.url).then(
        function(e) {
          alert('복사 되었습니다.');
          console.log(e);
        },
        function(e) {
          alert('복사에 실패했습니다.');
          console.log(e);
        }
      );
    },
    kakaoCopy() {
      // let desc = prompt('설명을 작성해주세요.', `${this.id} 포스트입니다.`);
      // console.log(desc);
      window.Kakao.Link.sendDefault({
        objectType: 'feed',
        content: {
          title: this.postName,
          description: this.tags,
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
    goMain() {
      this.$router.push('/');
    },
  },
  created() {
    this.id = localStorage['id'];
    this.url = API_BASE_URL + `${this.id}/${this.postNo}`;
    // this.url = `http://i4a307.p.ssafy.io/${this.id}/${this.postNo}`;
  },
};
</script>

<style></style>

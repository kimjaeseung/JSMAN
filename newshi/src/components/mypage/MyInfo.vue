<template>
    <v-container>
      <div class="d-flex justify-center">
      <v-avatar size="150px">
        <v-img :src="member.thumbnail_path"></v-img>
      </v-avatar>
      </div>
      <div class="d-flex justify-center mt-3" ><h2>{{member.name}}</h2></div>
      <div class="d-flex justify-center">{{member.id}}</div>
      
      <v-container >
        <v-row class="d-flex justify-center mt-10">
          <v-btn v-for="(hashtag, index) in hashtags" :key="index" text style="font-size:150%" color="#646464">{{hashtag}}</v-btn>
          <!-- <v-btn text style="font-size:150%" :color="hashtagcolors[index]">{{hashtag}}</v-btn> -->
          <br v-if="index % 3 == 2"/>
        </v-row>
      </v-container>

      <v-card class="mt-10">
        <v-list style="text-align: left">
            <v-subheader>팔로워</v-subheader>
            <v-list-item v-for="(follower, index) in followers" :key="index">
              <v-list-item-avatar>
                <img :src="follower.thumbnail_path">
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title>{{follower.name}}</v-list-item-title>
                <v-list-item-subtitle>{{follower.id}}</v-list-item-subtitle>
              </v-list-item-content>
              <v-list-item-action><v-btn class="error">삭제</v-btn></v-list-item-action>
            </v-list-item>
        </v-list>
      </v-card>
    </v-container>
</template>

<script>
export default {
  data() {
    return {
      member: {},
      hashtags: [],
      followers: []
    }
  },
  created() {
    // 내 정보 불러오는 axios(임시)
    this.member = {
        name: '김재성',
        id: 'kimjea23@naver.com',
        thumbnail_path: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
    };

    // 내 hashtags 불러오는 axios(임시)
    this.hashtags = ['#속보', '#정치', '#경제', '#사회', '#문화'];
    // this.hashtagcolors = ['blue', 'red', 'green', 'black', '#6464CD'];

    // 팔로워 불러오는 axios(임시)
    this.followers = [
        { thumbnail_path: 'https://cdn.vuetifyjs.com/images/lists/1.jpg', id: 'abcde@naver.com', name: '김싸피' },
        { thumbnail_path: 'https://cdn.vuetifyjs.com/images/lists/2.jpg', id: 'fgdsgsdfg@naver.com', name: '이죄송' },
        { thumbnail_path: 'https://cdn.vuetifyjs.com/images/lists/3.jpg', id: 'asdfkl@naver.com', name: '최바보' },
      ];
  },
}
</script>

<style>

</style>
<template>
    <v-container>
      <v-card>
        <v-row>
          <v-col>
            <v-card-actions class="d-flex justify-end">
            <v-btn class="mr-3"> 비밀번호 수정 </v-btn>
            </v-card-actions>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <div class="d-flex justify-center">
              <v-dialog v-model="dialog" width="500">
                <template v-slot:activator="{ on, attrs }">
                  <v-avatar size="150px" v-bind="attrs" v-on="on">
                    <v-img :src="member.thumbnail_path"></v-img>
                    <!-- <v-img :src="`${member.thumbnail_path}`" aspect-ratio="1"></v-img> -->
                  </v-avatar>
                </template>
              <v-card-title>파일 업로드</v-card-title>
              <v-file-input v-model="file"></v-file-input>
              <v-btn @click="fileUpload">전송하기!</v-btn>
              </v-dialog>
      </div>
      <div class="d-flex justify-center mt-3" >
        <h2>{{member.name}}</h2>
      </div>
      <div class="d-flex justify-center">{{member.id}}</div>
      <v-container class="ma-5">
        <v-row>
          <v-col></v-col>
          <v-col class="d-flex justify-center" cols="8">
            <div>
              <v-btn v-for="(hashtag, index) in hashtags" :key="index" text style="font-size:150%" color="#646464">{{hashtag}}</v-btn>
            </div>
          </v-col>
          <v-col></v-col>
        </v-row>
      </v-container>
        </v-col>
      </v-row>
      <v-container>
      <v-row>
        <v-col>
      <v-card class="mt-10">
        <v-list style="text-align: left">
            <v-subheader>구독중인 큐레이터</v-subheader>
            <v-list-item v-for="(follower, index) in followers" :key="index">
              <v-list-item-avatar>
                <v-img :src="follower.thumbnail_path"></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title>{{follower.name}}</v-list-item-title>
                <v-list-item-subtitle>{{follower.id}}</v-list-item-subtitle>
              </v-list-item-content>
              <v-list-item-action><v-btn class="error">삭제</v-btn></v-list-item-action>
            </v-list-item>
        </v-list>
      </v-card>
        </v-col>
      </v-row>
      </v-container>
      </v-card>
    </v-container>
    
</template>

<script>
import axios from 'axios';
export default {
  methods: {
    fileUpload() {
      console.log(this.file);
      var frm = new FormData();
      frm.append("file", this.file);
      frm.append("id", 'kimjea23@naver.com');
      axios.post('http://localhost/upload', frm, { headers: { 'Content-Type': 'multipart/form-data' } }) 
      .then((response) => { 
        // 응답 처리 
        console.log(response)
      }) .catch((error) => { 
        // 예외 처리 
        console.log(error);
      })
      }
  },
  data() {
    return {
      member: {},
      hashtags: [],
      followers: [],
      file: [],
      dialog: false,
    }
  },
  created() {
    // 내 정보 불러오는 axios(임시)
    // this.member.thumbnail_path = "http://images.khan.co.kr/article/2021/02/08/l_2021020802000444700074261.jpg";
    // this.member.name = '로딩중';  
    // this.member.id = '기달';
    // axios.get('http://localhost/sidebarUser/' + 'kimjea23@naver.com'
    // ).then((response) => {
    //   console.log(response);
    //   this.member.name = response.data.name;
    //   // this.member.thumbnail_path = '../../../../../../tmp/' + response.data.thumbnail_path;
    //   this.member.id = 'kimjea23@naver.com';
    //   this.member.thumbnail_path = "http://images.khan.co.kr/article/2021/02/08/l_2021020802000444700074261.jpg";
    //   console.log(this.member.thumbnail_path);
    //   console.log(this.member.name)
    //   this.$forceUpdate();
    // })
    console.log("경로>>>>>" + require('@/assets/images/default_avatar.png'));
    this.member = {
        name: '김재성',
        id: 'kimjea23@naver.com',
        thumbnail_path: require('@/assets/images/default_avatar.png'),
    };

    // 내 hashtags 불러오는 axios(임시)
    this.hashtags = ['#속보', '#정치', '#경제', '#사회', '#문화'];
    // this.hashtagcolors = ['blue', 'red', 'green', 'black', '#6464CD'];

    // 큐레이터 불러오는 axios
    axios.get('http://localhost:8080/subscribe', 
      { params: { id: this.member.id } },
    ).then((response) => { 
        // 응답 처리 
        var followers = response.data;
        console.log(followers);
        followers.forEach(function(follower) {
          if(follower.thumbnail_path == null) {
            follower.thumbnail_path = require('@/assets/images/default_avatar.png');
          }
        })
        this.followers = followers;
      }) .catch((error) => { 
        // 예외 처리 
        console.log(error);
      })

    // this.followers = [
    //     { thumbnail_path: require('@/assets/images/default_avatar.png'), id: 'abcde@naver.com', name: '김싸피' },
    //     { thumbnail_path: 'https://cdn.vuetifyjs.com/images/lists/2.jpg', id: 'fgdsgsdfg@naver.com', name: '이죄송' },
    //     { thumbnail_path: 'https://cdn.vuetifyjs.com/images/lists/3.jpg', id: 'asdfkl@naver.com', name: '최바보' },
    //   ];
  },
}
</script>

<style>

</style>
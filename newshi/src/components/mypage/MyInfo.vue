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
              <v-list-item-avatar @click="toChannel(follower.id)" style="cursor: pointer">
                <v-img :src="follower.thumbnail_path"></v-img>
              </v-list-item-avatar>
              <v-list-item-content @click="toChannel(follower.id)" style="cursor: pointer">
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
import { mapActions } from 'vuex';

export default {
  computed: {
    getMember() {
      return this.$store.state.userProfile;
    },
  },
  watch: {
    getMember: function(val) {
      this.member = val;
    },
    member: function() {
      this.getFollowers();
      this.getTagList();
    }
  },
  methods: {
    ...mapActions(['logout', 'getUserInfo']),
    toChannel(curator) {
      console.log(curator + "채널로");
      this.$router.push('channel/' + curator);
    },
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
    },
    getFollowers() {
      axios.get('http://localhost:8080/subscribe', 
      { params: { id: this.member.id } },
    ).then((response) => { 
        // 응답 처리 
        var followers = response.data;
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
    },
    getTagList() {
      axios.get('http://localhost:8080/tagList', 
      { params: { id: this.member.id } },
    ).then((response) => { 
      var hashtags = response.data;
      hashtags.forEach((hashtag) => {
        this.hashtags.push('#' + hashtag['name'])
      })
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
    this.member = this.$store.getters.userProfile;
    console.log(this.member);
    if (this.member === null || this.member.id == undefined) {
      this.getUserInfo();
    }
    // 내 hashtags 불러오는 axios(임시)
    // this.hashtags = ['#속보', '#정치', '#경제', '#사회', '#문화'];
    
  },
}
</script>

<style>

</style>
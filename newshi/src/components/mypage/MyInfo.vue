<template>
    <v-container>
      <v-card>
        <v-row>
          <v-col>
            <v-card-actions class="d-flex justify-start">
              <v-dialog v-model="dialog2" width="unset">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn v-bind="attrs" v-on="on">해쉬태그 수정</v-btn>
                </template>
              <v-card >
                <v-card-title>해쉬태그 수정</v-card-title>
                <v-card-actions>
                <v-chip-group
                  v-model="tags"
                  active-class="yellow --text"
                  column
                  multiple
                >
                  <v-chip border large v-for="tag in tagName" :key="tag">
                    {{ tag }}
                  </v-chip>
                </v-chip-group>
                </v-card-actions>
                <v-card-actions>
                  <v-btn @click="modifyTags">수정</v-btn><v-btn @click="dialog2=flase">취소</v-btn>
                </v-card-actions>
              </v-card>
              </v-dialog>
            </v-card-actions>
          </v-col>
          <v-col>
            <v-card-actions class="d-flex justify-end">
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="primary"
                    dark
                    v-bind="attrs"
                    v-on="on"
                  >
                    회원정보 수정
                  </v-btn>
                </template>
                <v-list>
                  <v-list-item>
                    <v-list-item-title @click="toModify"> 비밀번호 수정 </v-list-item-title>
                  </v-list-item>
                  <v-list-item>
                    <v-list-item-title @click="userDelete"> 회원탈퇴 </v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            <!-- <v-btn class="mr-3" @click="toModify"> 비밀번호 수정 </v-btn> -->
            </v-card-actions>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <div class="d-flex justify-center">
              <v-dialog v-model="dialog">
                <template v-slot:activator="{ on, attrs }">
                  <v-avatar size="150px" v-bind="attrs" v-on="on">
                    <v-img :src="member.thumbnail_path"></v-img>
                  </v-avatar>
                </template>
              <v-card>
                <v-card-title>파일 업로드</v-card-title>
                <v-card-actions>
                <v-file-input v-model="file"></v-file-input>
                </v-card-actions>
                <v-card-actions>
                <v-btn @click="fileUpload">전송하기!</v-btn>
                </v-card-actions>
              </v-card>
              </v-dialog>
      </div>
      <div class="d-flex justify-center mt-3">
        <h2 @click="toChannel(member.id)" style="cursor: pointer">{{member.name}}</h2>
      </div>
      <div class="d-flex justify-center">
        <p @click="toChannel(member.id)" style="cursor: pointer">{{member.id}}</p></div>
      
      <v-container class="mt-8">
        <v-row>
          <v-col></v-col>
          <v-col class="d-flex justify-center" cols="8">
            <div>
              <v-btn v-for="(hashtag, index) in hashtags" :key="index" text style="font-size:125%" color="#646464" @click="toSearch(hashtag)">#{{hashtag}}</v-btn>
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
      <v-card class="mt-8">
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
              <v-list-item-action><v-btn class="error" @click="unfollow(follower.id)">삭제</v-btn></v-list-item-action>
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
import { uploadImage } from '@/api/board.js';

const tag_dict = {
        '속보': 0,
        '정치': 1,
        '경제': 2,
        '사회': 3,
        '생활/문화': 4,
        '세계/국제': 5,
        'IT/과학': 6,
        '오피니언': 7,
        0: '속보',
        1: '정치',
        2: '경제',
        3: '사회',
        4: '생활/문화',
        5: '세계/국제',
        6: 'IT/과학',
        7: '오피니언',
}

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
    userDelete() {
      axios.delete('http://localhost:8080/delete', {params: { id: this.member.id }})
      .then(() => {
        this.logout();
        this.$router.go(this.$router.currentRoute);
        // this.$router.push('/');
      })
    },
    toModify() {
      this.$router.push('mypage/modify');
    },
    toSearch(tag) {
      tag = tag.replace('/', '%2F');
      this.$router.push('/search/hashtag/' + tag);
    },
    unfollow(id) {
      var frm = new FormData();
      frm.append("id", this.member.id);
      frm.append("id2", id);
      console.log(id);
      axios.post('http://localhost:8080/subscdelete', frm, { headers: { 'Content-Type': 'multipart/form-data' }})
      .then(() => {
        this.$router.go(this.$router.currentRoute);
      })
    },
    modifyTags(){
      var params = new URLSearchParams();
      params.append("id", this.member.id);
      this.tags.forEach(function(tag) {
        params.append("list",tag_dict[tag]);
      });
      console.log(this.member.id);
      axios.get('http://localhost:8080/tagListUpdate', 
      { params: params },
    ).then(() => { 
      this.$router.go(this.$router.currentRoute);
      }) .catch((error) => { 
        // 예외 처리 
        console.log(error);
      })
    },
    toChannel(curator) {
      console.log(curator + "채널로");
      this.$router.push('channel/' + curator);
    },
    fileUpload() {
      console.log(this.file);
      let file = this.file;
      const fileName = file.name;
      uploadImage(
        file,
        (response) => {
          if (response.data.message === 'success') {
            this.imageSrc =
              'https://newha.s3.us-east-2.amazonaws.com/' + fileName;
            console.log(this.imageSrc);
            var frm = new FormData();
            frm.append("id", this.member.id);
            frm.append("thumbnail_path", this.imageSrc);
            axios.post('http://localhost:8080/upload', frm, { headers: { 'Content-Type': 'multipart/form-data' }})
            .then(() => {
              this.$router.go(this.$router.currentRoute);
            })
          } else {
            alert('큐레이터의 데이터를 받아오는데 실패했습니다.');
          }
        },
        (error) => {
          console.error(error);
          alert('큐레이터의 데이터를 받아오는 중 에러가 발생했습니다.');
        }
      );
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
        this.hashtags.push(hashtag['name'])
        this.tags.push(tag_dict[hashtag['name']]);
      })
      })
      
      console.log(this.hashtags);
    }
  },
  data() {
    return {
      member: {},
      hashtags: [],
      followers: [],
      file: [],
      dialog: false,
      dialog2: false,
      tagName: [
        '속보',
        '정치',
        '경제',
        '사회',
        '생활/문화',
        '세계/국제',
        'IT/과학',
        '오피니언',
      ],
      tags:[],
    }
  },
  created() {
    if(this.$store.getters.userProfile.id != undefined) {
      this.member = this.$store.getters.userProfile;
    }
    // 내 hashtags 불러오는 axios(임시)
    // this.hashtags = ['#속보', '#정치', '#경제', '#사회', '#문화'];
    
  },
}
</script>

<style>

</style>
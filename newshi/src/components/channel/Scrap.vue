<template>
  <v-container>
      <!-- <v-row no-gutters>현재 스크랩 수: {{scraps.length}}</v-row> -->
    <v-row justify="center">
        <v-col v-for="(scrap, index) in scraps" :key="index" cols="auto">
            <v-card width='150' height='150'>
            <v-img :src="scrap.scrap_thumbnail" aspect-ratio="1">
                <div fill-height style="width:100%; height:100%;" class="d-flex justify-center align-center" else>
                  <v-container >
                    <v-row>
                      <v-col class="d-flex justify-center align-center">
                         <v-btn @click="showNews(scrap.postNo)">보러가기</v-btn>
                      </v-col>
                    </v-row>
                    <v-row v-if="isMyPage==true">
                      <v-col class="d-flex justify-center align-center">
                        <v-dialog :retain-focus="false" width="500" v-model="dialog">
                          <template v-slot:activator="{ on, attrs }">
                            <v-btn v-bind="attrs" v-on="on" @click="modifyBtn(scrap.name, scrap.postNo)">
                             수정하기
                            </v-btn>
                          </template>
                          <v-card>
                            <input type="text" v-model="modify_title" style="font-size:30px; width:100%; text-align:center">
                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn color="primary" @click="modify()">
                                수정
                              </v-btn>
                              <v-btn color="primary" @click="refreshPage">
                                취소
                              </v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                         <!-- <v-btn :disabled="hover == false">수정하기</v-btn> -->
                      </v-col>
                    </v-row>
                  </v-container>
                </div>
            </v-img>
            <div class="ellipsis">{{scrap.name}}</div>
            </v-card>
        </v-col>
    </v-row>
    </v-container>
</template>

<script>
import axios from 'axios';

export default {
  computed: {
    getMember() {
      return this.$store.state.userProfile;
    },
  },
  watch: {
    getMember: function(val) {
      this.member = val;
      if(this.member.id == this.$route.params.id){
        this.isMyPage = true;
      }
    },
    member: function() {
      if(this.member.id == this.$route.params.id){
        this.isMyPage = true;
        console.log("isMyPage");
      }
    }
  },
    props: {
      propsIsMyPage: {
        type: Boolean
      }
    },
    data() {
        return {
          isMyPage: Boolean,
          scraps: [],
          dialog: false,
          flag: false,
          member: {},
          modify_title: '',
          modify_postNo: 0,
        }
    },
    methods: {
      showNews(scrap_no) {
        this.$router.push('./' + this.$route.params.id + '/' + scrap_no);
      },
      modifyBtn(name, postNo) {
        this.modify_title = name;
        this.modify_postNo = postNo;
        this.dialog=true;
      },
      modify() {
        console.log(this.modify_postNo);
        axios.put('http://localhost:8080/article/post', { id: this.$route.params.id, name: this.modify_title, postNo: this.modify_postNo})
        .then(() => {
          this.$router.go(this.$router.currentRoute);
        });
      },
      refreshPage() {
        this.dialog=false;
      },
      getPostList() {
        axios.get('http://localhost:8080/article/post', 
        { params: { id: this.$route.params.id } },
      ).then((response) => { 
        let scraps = response.data;
        for(let i = 0; i < scraps.length; i++) {
          setTimeout(() => {
            axios.get('http://localhost:8080/article/scraplist', 
            { params: { postNo: scraps[i].postNo } },
          ).then((response) => {
            scraps[i].scrap_thumbnail = response.data[0].new_image[0];
            this.scraps.push(scraps[i]);
          });
          }, i * 10);
        }
        });
      }
    },
    created() {
      if(this.$store.getters.userProfile.id != undefined) {
      this.member = this.$store.getters.userProfile;
    }
      this.getPostList();
  },
}
</script>

<style>
    .scrap_dark{
    background-color:black;
    opacity: 0.4;
    width: 100%;
    height: 100%;
 }
 .ellipsis{
        width:100%;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        text-align: center;
    } 
</style>
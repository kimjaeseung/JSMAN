<template>
  <div>
      <v-app-bar flat app style="border-bottom:1px solid">
        <v-app-bar-nav-icon @click="openNavbar()"></v-app-bar-nav-icon>
        <v-spacer/>
        <v-img contain src="@/assets/logo.png" style="height: 50%"></v-img>
        <v-spacer/>
        <v-icon @click="search_drawer = true">mdi-magnify</v-icon>
      </v-app-bar>
      
      <v-navigation-drawer bottom v-model="search_drawer" absolute temporary >
          <v-autocomplete :search-input.sync="search_word" :items="autocomp_value" filled></v-autocomplete>
          <br>{{search_word}}
      </v-navigation-drawer>

      <v-navigation-drawer v-model="menu_drawer" absolute temporary>
        <v-list>
            <v-list-item-group>
                <v-list-item>
                    <v-list-item-avatar>
                        <v-img :src="member.thumbnail_path"></v-img>
                    </v-list-item-avatar>
                    <v-list-item-content>
                        <v-list-item-title>{{member.name}}</v-list-item-title>
                        <v-list-item-subtitle>{{member.id}}</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item> <v-divider/>

                <v-list-item v-for="(menu, index) in menus" :key="index">
                    <v-list-item-icon>
                        <v-icon>mdi-{{menu.icon}}</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                    {{menu.title}}
                   </v-list-item-title>
                </v-list-item>
                
            </v-list-item-group>
        </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
export default {
    data() {
        return {
            menu_drawer: false,
            search_drawer: false,
            member: {},
            autocomp_value: [],
            search_word: '',
            menus: [
                { icon: 'newspaper-variant-multiple-outline', title: '내가 댓글 남긴 기사' },
                { icon: 'newspaper-plus', title: '언론사 선택하기' },
                { icon: 'brightness-6', title: '다크모드' },
                { icon: 'email-open-outline', title: '피드백 보내기' },
                { icon: 'comment-processing-outline', title: '댓글 운영 정책' },
                { icon: 'home', title: '홈페이지 바로가기' },
                { icon: 'information-outline', title: '버전 정보' },
            ],
            mounted_flag: false,
        }
    },

    methods: {
        openNavbar: function() {
            // axios request: id로 이름과 썸네일을 불러오도록 한다. 
            //일단은 그냥 받아온것처럼 함
            this.member = {
                name: '김재성',
                id: 'kimjea23@naver.com',
                thumbnail_path: 'https://cdn.vuetifyjs.com/images/lists/1.jpg'
            }
            this.menu_drawer = true;

        },
    },
    watch: {
        search_word: function() {
            console.log("검색어 변경");
            
            if(this.search_word == undefined) return;
            
            if(this.search_word == '') this.autocomp_value = [];
            else if(this.search_word.charAt(0) == '#') {
                // 태그에 접근하는 axios
                this.autocomp_value = ['#경제', '#시사', '#IT', '#감성'];
            } else {
                // 큐레이터에 접근하는 axios
                this.autocomp_value = ['사람1', '사람2', '사람3', '감재성'];
            }
        }
        
    },

    created() {
        
    },
}
</script>

<style>

</style>
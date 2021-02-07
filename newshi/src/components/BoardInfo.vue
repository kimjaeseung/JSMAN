<template>  
  <v-hover v-slot="{ hover }">
    <v-card>
      <v-card-title>
        <v-list two-line>
          <template>
            <v-list-item-avatar>
              <v-img :src="member.thumbnail_path" :alt="member.name">
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title><a style="color: black;text-decoration:none" @click="goChannel">{{member.id}}</a> <a style="color: black;text-decoration:none" @click="boardDetail">업로드 : {{board.date}}</a></v-list-item-title>
              <v-list-item-subtitle>{{board.title}}</v-list-item-subtitle>
            </v-list-item-content>
            <v-divider></v-divider>
            <v-list-item-action v-show="hover">
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    icon
                    v-show="isMyPage"
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon>mdi-dots-vertical</v-icon>
                  </v-btn>
                </template>
                <v-list>
                  <v-list-item>
                    <v-list-item-title><v-btn text @click="modifyBoard">수정</v-btn></v-list-item-title>
                    <v-list-item-title><v-btn text @click="deleteBoard">삭제</v-btn></v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </v-list-item-action>
          </template>
        </v-list>
      </v-card-title>
      <v-card-text>
        {{content.front}}
      </v-card-text>
      <a v-show="!isDetail" @click="isDetail = !isDetail">자세히 보기</a>
      <v-card-text v-if="isDetail">
        {{content.behind}}
      </v-card-text>
      <a v-show="isDetail" @click="isDetail = !isDetail">간략히 보기</a>
      <v-card-actions>
        <v-btn @click="boardDetail">
          <v-btn icon @click="boardDetail"><v-icon>mdi-comment-text</v-icon></v-btn>
          {{board.commentCnt}}
        </v-btn>
    </v-card>
  </v-hover>
</template>

<script>
export default {
  props: {
    member : Object,
    board : Object,
    num: Number,
  },
  data() {
    return {
      isDetail: false,
      content: {
        front: '',
        behind: '',
      },
      isMyPage: false,
    };
  },
  methods: {
    goChannel() {
       this.$router.push({name: 'Channel', params: {id: this.member.id} });
    },
    boardDetail() {
       this.$router.push({name: 'BoardDetail', params: {board: this.board, member: this.member, num: this.num} });
    },
    modifyBoard() {
      this.$router.push({name: 'AddBoard', params: {board: this.board} });
    },
    deleteBoard() {
      this.$emit('delBoard',this.num);
    }
  },
  created() {
    let locP = this.board.content.indexOf('</p>'); //</p>
    let locH = this.board.content.indexOf('</H');//</H
    if((locP !== -1 && locH !== -1 && locP < locH) || (locH === -1 && locP !== -1)) {
      this.content.front = this.board.content.substring(0, locP+4);
      this.content.behind = this.board.content.substring(locP+4, this.board.content.length);
    } else {
      this.content.front = this.board.content.substring(0, locH+5);
      this.content.behind = this.board.content.substring(locH+5, this.board.content.length);
    }
    if(this.member.id === localStorage['id']) {
      this.isMyPage = true;
    }
  }
};
</script>

<style></style>

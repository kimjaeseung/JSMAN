<template>
  <v-row> 
      <v-hover v-slot="{ hover }">
    <v-card>
      <v-card-title>
        <v-list two-line>
          <template>
            <v-list-item-avatar>
              <v-img :src="member.thumbnail_path" :alt="member.name">
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title><a style="color: black;text-decoration:none" @click="goChannel">{{member.id}}</a> <a style="color: black;text-decoration:none" @click="boardDetail">업로드 : {{comment.date}}</a></v-list-item-title>
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
                  <v-list-item v-if="!isModify">
                    <v-list-item-title><v-btn text @click="isModify = !isModify">수정</v-btn></v-list-item-title>
                    <v-list-item-title><v-btn text @click="deleteComment">삭제</v-btn></v-list-item-title>
                  </v-list-item>
                  <v-list-item v-else>
                    <v-list-item-title><v-btn text @click="cancel">취소</v-btn></v-list-item-title>
                    <v-list-item-title><v-btn text @click="deleteComment">삭제</v-btn></v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </v-list-item-action>
          </template>
        </v-list>
      </v-card-title>
      <v-card-text>
          <v-row v-if="!isModify">
              {{comment.content}}
          </v-row>
          <v-row v-else>
            <v-form>
                <v-text-field
                    v-model="alterComment"
                    placeholder="댓글 수정..."
                    ref="comment"
                    @focus="isFocus = true"
                    @blur="!isFocus"
                >
                </v-text-field>
                <v-row v-show="isFocus">
                    <v-btn @click="cancel">취소</v-btn>
                    <v-btn @click="modifyComment">수정</v-btn>
                </v-row>
            </v-form>
          </v-row>
      </v-card-text>
    </v-card>
  </v-hover>
  </v-row>
</template>

<script>
export default {
    props: {
      comment = Object,
      num = Number,
    },
    data() {
    return {
      isMyComment: false,
      isModify: false,
      isFocus: false,
      alterComment : '',
      member: {},
    };
  },
   methods: {
    deleteComment() {
      this.$emit('delComment', num);
    },
    cancel() {
      this.alterComment = this.comment.content;
      this.isFocus = false;
      this.$refs.comment.blur();
      this.isModify = false;
    },
    modifyComment() {
      let comm = this.comment;
      comm.content = this.alterComment;
      boardCommentUpdate(
        comm,
        (response) => {
          if (response.data.message === 'success') {
              this.$emit('updateComment', comm, this.num);
          } else {
            alert('댓글 수정이 실패하였습니다.');
          }
        },
        (error) => {
          console.error(error);
          alert('해당 게시물의 댓글을 수정하는 중 에러가 발생했습니다.');
        }
      );
      this.cancel();
    },
  },
  created() {
      //userNo를 통해 유저정보를 받아와야합니다.
      this.alterComment = this.comment.content;
  }
}
</script>

<style></style>

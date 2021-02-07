<template>
  <v-container>
    <v-form>
      <v-text-field
        v-model="newComment"
        placeholder="댓글 추가..."
        ref="comment"
        @focus="isFocus = true"
        @blur="!isFocus"
      >
        <v-avatar size="100px" slot="prepend">
          <v-img :src="member.thumbnail_path"></v-img>
        </v-avatar>
      </v-text-field>
      <v-row v-show="isFocus">
        <v-btn @click="cancel">취소</v-btn>
        <v-btn @click="addComment">작성</v-btn>
      </v-row>
    </v-form>
    <v-divider></v-divider>
    <BoardCommentDeatil
      v-for="(comment, index) in commentList"
      :key="index"
      :comment="comment"
      :num="index"
      @delComment="removeComment"
      @updateComment="modifyComment"
    ></BoardCommentDeatil>
  </v-container>
</template>

<script>
import { boardCommentList, boardCommentInsert } from '@/api/board.js';
import BoardCommentDetail from '@/components/BoardCommentDeatil.vue';

export default {
  components: {
    boardCommentList,
    boardCommentInsert,
    BoardCommentDetail,
  },
  props: {
    boardPostNo: Number,
  },
  data() {
    return {
      commentList: [],
      newComment: '',
      isFocus: false,
    };
  },
  methods: {
    removeComment(index) {
      let comment = this.commentList.splice(index, 1);
      boardDelete(
        comment.commentNo,
        (response) => {
          if (response.data.message === 'success') {
            alert('해당 댓글 삭제에 성공했습니다.');
          } else {
            alert('해당 댓글 삭제에 실패했습니다.');
          }
        },
        (error) => {
          console.error(error);
          alert('게시물의 댓글 삭제 중 에러가 발생했습니다.');
        }
      );
      console.log(this.commentList);
      console.log(index);
    },
    cancel() {
      this.newComment = '';
      this.isFocus = false;
      this.$refs.comment.blur();
    },
    addComment() {
      let comm = {
        boardPostNo: this.boardPostNo,
        id: localStorage['id'],
        content: this.newComment,
      };
      boardCommentInsert(
        comm,
        (response) => {
          if (response.data.message === 'success') {
            let newComm = {
              BoardPostNo: response.data['BoardPostNo'],
              CommentNo: response.data['CommentNo'],
              Content: response.data['Content'],
              Date: response.data['Date'],
              UserNo: response.data['UserNo'],
            };
            this.commentList.unshift(newComm);
          } else {
            alert('댓글 등록이 실패하였습니다.');
          }
        },
        (error) => {
          console.error(error);
          alert('해당 게시물의 댓글을 등록하는 중 에러가 발생했습니다.');
        }
      );
      this.cancel();
    },
    modifyComment(comm, index) {
      this.commentList.splice(index, 1, comm);
    },
  },
  created() {
    boardCommentList(
      this.boardPostNo,
      (response) => {
        if (response.data.message === 'success') {
          this.commentList = response.data;
        } else {
          alert('댓글을 가져오는데 실패하였습니다.');
        }
      },
      (error) => {
        console.error(error);
        alert('해당 게시물의 댓글을 가져오는 중 에러가 발생했습니다.');
      }
    );
  },
};
</script>

<style></style>

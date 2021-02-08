import { createInstance } from './index.js';

const instance = createInstance();
// const config = {
//   headers: { "access-token": localStorage.getItem("access-token") }
// };

function boardList(id, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .get('/boardList', {
      params: {
        id: id,
      },
    })
    .then(success)
    .catch(fail);
}

function boardInsert(board, images, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .get('/boardInsert', board, {
      params: {
        id: localStorage.id,
      }
    })
    .then(success)
    .catch(fail);
}

function boardDelete(boardno, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .delete('/boardDelete', {
      params: {
        boardPostNo = boardno,
      }
    })
    .then(success)
    .catch(fail);
}

function boardCommentList(boardPostNo, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .get('/boardDetail/${boardPostNo}', {
    })
    .then(success)
    .catch(fail);
}

function boardCommentInsert(comment, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .post('/boardCommentInsert', {
      params: {
        boardPostNo = comment.boardPostNo,
        id = comment.id,
        content = comment.content,
      }
    })
    .then(success)
    .catch(fail);
}

function boardCommentUpdate(comment, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .put('/boardCommentUpdate', comment)
    .then(success)
    .catch(fail);
}

function boardCommentDelete(commentno, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .delete('/boardCommentDelete', {
      params: {
        commentNo = commentno,
      }
    })
    .then(success)
    .catch(fail);
}



export { boardList, boardInsert, boardDelete, boardCommentList, boardCommentDelete, boardCommentInsert, boardCommentUpdate };

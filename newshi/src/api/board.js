import { createInstance, createFileInstance } from './index.js';

const instance = createInstance();
// const config = {
//   headers: { "access-token": localStorage.getItem("access-token") }
// };

const fileInstance = createFileInstance();

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

function uploadImage(image, success, fail) {
  fileInstance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );
  const form = new FormData();
  form.append('file', image);
  console.log(form);
  fileInstance
    .post('/uploadFile', form)
    .then(success)
    .catch(fail);
}

function boardInsert(board, id, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );
  // const list = new FormData();
  // list.append('board', board);
  // list.append('id', id);
  // console.log(list.board);
  // console.log(list.id);
  const list = [
    {
      title: board.title,
      content: board.content,
    },
    {
      id: id,
    },
  ];
  console.log(list);
  // [
  //   {
  //     "title": "새로운 큐레이터",
  //     "content": "<p>안녕하세요 반가워요</p>"
  //   },
  //   {
  //     "id": "chunawoos@hanmail.net"
  //   }
  // ]
  instance
    .post('/boardInsert', list)
    .then(success)
    .catch(fail);
}

function boardUpdate(b, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  console.log(b);

  instance
    .put('/boardUpdate', b)
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
        boardPostNo: boardno,
      },
    })
    .then(success)
    .catch(fail);
}

function boardCommentList(boardPostNo, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  instance
    .get('/boardCommentList', {
      params: {
        boardPostNo: boardPostNo,
      },
    })
    .then(success)
    .catch(fail);
}

function boardCommentInsert(comment, success, fail) {
  instance.defaults.headers['access-token'] = window.localStorage.getItem(
    'access-token'
  );

  console.log(comment);

  instance
    .post('/boardCommentInsert', {
      params: {
        boardPostNo: comment.boardPostNo,
        id: comment.id,
        content: comment.content,
      },
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
        commentNo: commentno,
      },
    })
    .then(success)
    .catch(fail);
}

export {
  boardList,
  boardInsert,
  boardUpdate,
  boardDelete,
  boardCommentList,
  boardCommentDelete,
  boardCommentInsert,
  boardCommentUpdate,
  uploadImage,
};

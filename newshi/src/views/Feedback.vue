<template>
  <div class="container">
    <h3>뉴하팀에 피드백 메일 보내기</h3>
    <v-form @submit.prevent="submit">
      <v-text-field
        v-model="email"
        :rules="emailRules"
        label="답장 받을 이메일"
        required
      ></v-text-field>

      <v-text-field
        v-model="title"
        label="제목"
        required
        autocapitalize="off"
      ></v-text-field>

      <v-textarea
        v-model="content"
        type="text"
        label="본문"
        required
      ></v-textarea>
      <br />
      <v-btn
        :disabled="!valid"
        color="#ff9800"
        @click="submit"
        >전송하기</v-btn
      >
    </v-form> 
  </div>
</template>

<script>
import axios from 'axios';
const API_FROM_AWS_API_GATEWAY = 'https://1asikq5ii4.execute-api.ap-northeast-2.amazonaws.com/default/newshifeedback';

export default {
  name: 'Feedback',
  methods: {
    isValid: function () {
      if (this.title == '' || this.content == '') {
        this.valid = false
      } else {
        this.valid = true
      }
    },
    submit: function () {
      axios.post(API_FROM_AWS_API_GATEWAY, JSON.stringify({
        email: this.email,
        title: this.title,
        content: this.content,
        }
      ))
      .then((res) => {
        console.log(res)
        // this.mailingInProgress = false
        // this.responseText = 'Email sent succeeded!'
        // this.mailSent = true
      })
      .catch((e) => {
        console.log(e)
        // this.mailingInProgress = false
        // this.responseText = e + '. Please try later.'
        // this.mailSent = false
      })
    },
    // mailTo () {
    //   if (this.checkInputValidity() && !this.mailingInProgress && !this.mailSent) {
    //     this.mailingInProgress = true
    //     axios.post('YOUR_API_ADDRESS.amazonaws.com/seesoMailer', JSON.stringify(this.inquiryInput))
    //       .then(() => {
    //         this.mailingInProgress = false
    //         this.responseText = 'Email sent succeeded!'
    //         this.mailSent = true
    //       })
    //       .catch((e) => {
    //         this.mailingInProgress = false
    //         this.responseText = e + '. Please try later.'
    //         this.mailSent = false
    //       })
    //   }
    // }
  },
  data: function () {
    return {
      email: '',
      title: '',
      content: '',
      valid: false,
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid',
      ],
    }
  },
  watch: {
    title: function () {
      this.isValid();
    },
    content: function () {
      this.isValid();
    },
  }
}
</script>
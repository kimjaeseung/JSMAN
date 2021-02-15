<template>
  <v-container>
  <h2>나중에 볼 기사</h2>
    <v-list>
      <template v-for="(newsInfo, i) in newsItems">
        <v-list-item
          :key="newsInfo+i"
          @click="move(newsInfo)"
        >
          <v-list-item-avatar rounded >
            <v-img :src="newsInfo.image_path" centered></v-img>
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title v-html="newsInfo.title"></v-list-item-title>
            <v-list-item-subtitle v-html="newsInfo.company"></v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-list>
  </v-container>
</template>

<script>
import axios from 'axios';
const API_URL = 'http://localhost:8080';
const id = localStorage.getItem('id')

export default {
  name: 'Save',
  data() {
    return {
      newsItems: []
    }
  },
  methods: {
    getSaveList: function () {
      axios.get(`${API_URL}`+'/article/savelist'+`?id=${id}`)
      .then((res)=> {
        this.newsItems = res.data;
      })
      .catch((err) => {
        console.log(err)
      })
    },
    move(newsInfo){
      this.$router.push({name: 'Article', params: {newsNo: newsInfo.newsNo, newsInfo2: newsInfo} })
    },
  },
  created: function() {
    this.getSaveList();
  }
}
</script>

<style>

</style>
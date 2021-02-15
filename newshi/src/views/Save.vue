<template>
  <v-container>
  <h1>Save</h1>
    <!-- 
      <v-list>
      <template v-for="(newsInfo, i) in newsItem.newsLists">
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
    -->
  </v-container>
</template>

<script>
import axios from 'axios';
const API_URL = 'http://localhost:8080';
// const id = localStorage.getItem('id')

export default {
  name: 'Save',
  data() {
    return {

    }
  },
  methods: {
    getSaveList: function () {
      axios.get(API_URL)
      .then((res) => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
    }
  },
  created: function() {
    this.getSaveList();
  }
}
</script>

<style>

</style>
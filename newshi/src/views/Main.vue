<template>
  <v-container>
    <div v-if="this.isLoggedIn == true">
      <NewsList 
        :news-infos="newsInfos"
      />
    </div>
    <div v-else>
      <v-list>
        <template v-for="(newsInfo, i) in basicNews">
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
    </div>
  </v-container>
</template>

<script>
import NewsList from '../components/NewsList.vue';
import axios from 'axios';
const API_URL = 'http://localhost:8080';
const id = localStorage.getItem('id');

export default {
  name: "Main",
  components: { 
    NewsList,
  },
  methods: {
    getData: function () {
      axios.get(`${API_URL}`+'/subscribe'+`?id=${id}`)
      .then((res)=>{
        let subscribes = res.data;
        subscribes.forEach(element => {
          let subscriberId = element.id;
          let newsInfo = {
            name: element.name,
            avatar: element.thumbnail_path,
            newsLists: [],
          }
          axios.get(`${API_URL}`+'/article/curatorscrap'+`?id=${subscriberId}`)
          .then((res)=> {
            newsInfo.newsLists = res.data;
            this.newsInfos.push(newsInfo)
          })
          .catch((err) => {
            console.log(err)
          })
        });
      })
      .catch((err) => {
        console.log(err)
      })
    },
    isLogged: function () {
      if (
        localStorage.getItem('access-token') === null ||
        localStorage.getItem('access-token') === '' ||
        localStorage['access-token'] === undefined
      ) {
        this.isLoggedIn = false;
        axios.get(`${API_URL}`+'/article')
        .then((res)=> {
          this.basicNews = res.data;
          // console.log(this.basicNews)
        })
        .catch((err) => {
          console.log(err)
        })
      } else {
        this.isLoggedIn = true;
      }
    },
    move(newsInfo){
      this.$router.push({name: 'Article', params: {newsNo: newsInfo.newsNo, newsInfo2: newsInfo} })
    },
  },
  data: function () {
    return {
      tab: null,
      newsInfos: [],
      isLoggedIn: '',
      basicNews: [],
      // cancelFooter: cancel,
    }
  },
  created: function () {
    this.isLogged();
    this.isLoggedIn ? this.getData(): this.isLoggedIn;
  }
}
</script>

<style>
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
}
</style>
<template>
  <div>
  <NewsList 
    :news-infos="newsInfos"
  />
  </div>
  
</template>

<script>
import NewsList from '../components/NewsList.vue';
import axios from 'axios';
const API_URL = 'http://localhost:8080';
const id = localStorage.getItem('id')

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
    }
  },
  data: function () {
    return {
        tab: null,
        newsInfos: [],
      }
  },
  created: function () {
    this.getData();
  }
}
</script>

<style>

</style>
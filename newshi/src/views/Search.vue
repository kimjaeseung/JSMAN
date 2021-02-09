<template>
  <div>
      <v-container>
          <v-row>
              <v-col>
                  <v-autocomplete solo @change="(event) => search(event)" 
                      :search-input.sync="search_word" 
                      :items="autocomp_value">
                  </v-autocomplete>
              </v-col>
          </v-row>
          <v-row>
              <v-col><router-view :key="$route.fullPath"></router-view></v-col>
          </v-row>
      </v-container>
  </div>
</template>

<script>
export default {
    data() {
        return {
            search_word: '',
            autocomp_value: [],
            curator_search_flag: false,
        }
    }, 
    methods: {
        search(event) {
            if(event.charAt(0) == '#') {
                this.$router.push('/search/hashtag/' + event.substring(1));
            } else this.$router.push('/search/curator/' + event);
        }
    },
    watch: {
    search_word: function() {
        if(this.search_word == undefined) return;
        else if (this.search_word == '') {
            this.autocomp_value = [];
            this.curator_search_flag = false;
        }
        else if (this.search_word.length == 1 && this.search_word.charAt(0) == '#') {
            console.log('해시태그들 axios')
            this.autocomp_value = ['#경제', '#시사', '#IT', '#감성'];
            this.curator_search_flag = false;
        } else if(this.search_word.length == 1 && !this.curator_search_flag){
            // 큐레이터에 접근하는 axios
            console.log("사람 검색 axios");
            this.autocomp_value = ['사람1', '사람2', '사람3', '감재성'];
            this.curator_search_flag = true;
        }
        },
    },
}
</script>

<style>

</style>
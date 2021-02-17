<template>
  
</template>

<script>
export default {
    created() {
      
      this.$router.push({name: 'Article', params: {newsNo: newsInfo.newsNo, newsInfo2: newsInfo} })
    },
}
</script>

<style>

</style>
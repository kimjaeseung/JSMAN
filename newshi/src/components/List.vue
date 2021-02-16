<template>
  
</template>

<script>

export default {
  name: 'List',
  props: [
    'basicNews'
  ],
  data() {
    return {
      newsItem: this.basicNews
    }
  },
  created: function () {
    console.log(this.basicNews)
  }
}
</script>

<style>

</style>
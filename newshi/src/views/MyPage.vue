<template>
  <div>
      <router-view></router-view>
  </div>
</template>

<script>
export default {
  created() {
    console.log(this.$route.params.id);
  },
}
</script>

<style>

</style>
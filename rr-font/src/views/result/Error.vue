<template>
 <p>del</p>
</template>

<script>
  import Result from './Result'

  export default {
    name: "Error",
    components: {
      Result
    },
    data () {
      return {
        title: '提交失败',
        description: '请核对并修改以下信息后，再重新提交。'
      }
    }
  }
</script>

<style scoped>

</style>
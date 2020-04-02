<template>
<van-button @click="click">sssss</van-button>
</template>

<script>
  import { Toast } from 'vant';
  export default {
    name: 'capture',
    components: {  },
    methods: {
      open (link) {
        this.$electron.shell.openExternal(link)
      },
      click(){
        Toast.success("success")
      }
    },
    data(){
      return {
      }
    }
  }
</script>

<style>

</style>

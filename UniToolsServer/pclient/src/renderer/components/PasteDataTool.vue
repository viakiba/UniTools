<template>
  <div id="wrapper">
    <titleHead />
    <van-tabbar route >
      <van-tabbar-item icon="label" replace  to="/label" >
        数据
      </van-tabbar-item>
      <van-tabbar-item icon="setting" replace  to="/setting">
        设置
      </van-tabbar-item>
    </van-tabbar>

  </div>

</template>

<script>
  import store from "../store";
  import titleHead from '../title/title.vue';
  const {ipcRenderer} = require('electron')

  export default {
    name: 'LandingPage',
    components: { titleHead },
    methods: {
      open (link) {
        this.$electron.shell.openExternal(link)
      },
    },
    data(){
      return {
        
      }
    }
  }
</script>

<style>
</style>

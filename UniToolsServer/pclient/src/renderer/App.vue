<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'pclient',
    components:{
    }
  }
</script>

<style type="text-css" >
@import url('https://fonts.googleapis.com/css?family=Source+Sans+Pro');
body { font-family: 'Source Sans Pro', sans-serif; }
.scroll-wrapper{ overflow: auto; }
::-webkit-scrollbar{ width:0px; }
/* ::-webkit-scrollbar-track-piece{ background-color: $white-color; } */
/* ::-webkit-scrollbar-thumb{ background-color: rgba(0,0,0,.6); border-radius: 3px } */
</style>
<template>
  <div class="titleicon" ></div>
</template>

<script>
    const {ipcRenderer: ipc} = require('electron');
    const style = {
        close: {
            background: 'url("static/pic/close.png") no-repeat center',
            left: '20px'
        }
    };
    export default {
        name: 'Titleicon',
        computed: {
            
        },
        methods: {
            
        }
    }
</script>
    
<style>
.titleicon {
    background:url("/static/pic/lover.png") no-repeat center; 
    height: 20px;
    width: 20px;
    -webkit-app-region: no-drag;
}
</style>
<template>
  <div 
    class="titlebtn" 
    v-on:click="click">
    </div>
</template>

<script>
    const {ipcRenderer: ipc} = require('electron');
    export default {
        name: 'Titlebtn',
        props: ['type'],
        computed: {
        },
        methods: {
            click: function () {
                ipc.send(this.type);
            }
        }
    }
</script>
    
<style>
.titlebtn {
    background: url("/static/pic/close.png") no-repeat center;
    height: 20px;
    -webkit-app-region: no-drag;
}
</style>
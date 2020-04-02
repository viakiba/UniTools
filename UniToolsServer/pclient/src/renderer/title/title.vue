<template>
    <div id="mytitle">
        <van-row justify="center" gutter="8px">
            <van-col span="2"> <Titleicon /></van-col>
            <van-col span="6" style=""> PasteCloud</van-col>
            <van-col offset="14" span="2"><Titlebtn type="close" /></van-col>
        </van-row>
        <van-divider :style="{ color: '#666',background:'#666', margin: '3px' }"/>
    </div>
</template>

<script>
    import Titlebtn from './Titlebtn.vue';
    import Titleicon from './Titleicon.vue';
    import Titlename from './Titlename.vue';
    export default {
        name: 'Mytitle',
        components: {
            Titlebtn,
            Titleicon,
            Titlename,
        }
    }
</script>
    
<style>
#mytitle {
    height: 20px;
    width: 100%;
    -webkit-app-region: drag;
    position: fixed;
    top: 0;
    left: 0;
    display: block;
    z-index:9999;
    background-color: white;
}
</style>
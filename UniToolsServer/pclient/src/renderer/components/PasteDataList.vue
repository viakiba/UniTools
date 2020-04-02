<template>
  <div id="wrapper">
    <titleHead/>
    <div v-show="activeLaber" id="pasteDataList" style="margin-top:30px">
      <van-list v-model="loading" :finished="finished" @load="onLoad">
        <van-cell v-for="(item, index ) in list" :key="index">
              <van-row>
                <van-col span="2">
                 {{ index }}
                </van-col>
                <van-col span="10">
                  <div v-bind:title="item.content" class="van-ellipsis"> {{ item.content }} </div>
                </van-col>
                <van-col span="2">
                  <div class="van-ellipsis">  </div>
                </van-col>
                <van-col span="8">
                  <div class="van-ellipsis"> {{ item.date }} </div>
                </van-col>
                <van-col span="2"  @click = clickTest>
                  <van-image
                    width="20"
                    height="20"
                    src="/static/pic/copy.png"
                  />
                  <p hidden> {{ item.content }} </p>
                </van-col>
              </van-row>
        </van-cell>
      </van-list>
    </div>

    <div v-show="activeSetting" id="setting" style="margin-top:30px">
      <van-cell-group>
        <van-field
          v-model="value1"
          clearable
          label="账号"
          placeholder="请输入邮箱"
        />
        <van-field
          v-model="value1"
          clearable
          label="密码"
          placeholder="请输入密码"
        />
        <van-divider >配置</van-divider>
        <van-field
          v-model="value1"
          label="密钥"
          placeholder="请输入密钥值"
        />
        <van-field
          v-model="value1"
          clearable
          label="地址"
          placeholder="请输入服务器地址"
        />
        <!-- <van-divider>快捷键</van-divider> -->
        <van-field
          v-model="value1"
          clearable
          label="打开面板"
          readonly
          placeholder="Alt+S"
        />
        <van-field
          v-model="value1"
          clearable
          label="上传云端"
          readonly
          placeholder="Alt+D"
        />
        <van-field
          v-model="value1"
          clearable
          label="记录本地"
          readonly
          placeholder="Alt+Z"
        />
        <van-field
          v-model="value1"
          clearable
          label="复制数据"
          readonly
          placeholder="Alt+X"
        />
      </van-cell-group>
    </div>

    <van-tabbar id='pasteData' inactive-color='#7d7e80' active-color='#7d7e80' @change="onChange" >
      <van-tabbar-item name = "label" icon="label">
        数据
      </van-tabbar-item>
      <van-tabbar-item name = "setting" icon="setting">
        设置      
      </van-tabbar-item>
    </van-tabbar>

  </div>

</template>

<script>
  import store from "../store";
  import titleHead from '../title/title.vue';
  import { Toast } from 'vant';

  const {ipcRenderer} = require('electron')
  const remote=require('electron').remote
  const axios = require('axios');

  export default {
    name: 'LandingPage',
    components: { titleHead },
    methods: {
      open (link) {
        this.$electron.shell.openExternal(link)
      },
      onLoad() {
        // 异步更新数据
        setInterval(() => {
            var localData = ipcRenderer.sendSync('localPasteData','data')
            if(localData == 1){
              var localPasteData = remote.getGlobal('sharedObject').localPasteData
              console.info("本地更新")
              this.list = [];
              for (let index = 0; index < localPasteData.length; index++) {
                const element = localPasteData[index];
                this.list.unshift(element);
              }
              store.setStoreFunction("localPasteData",localData);
            }
             this.loading = false;
            // 数据全部加载完成
            this.finished = true;
          }, 500);
      },
      clickTest(event){
        console.log(event.currentTarget.textContent)
        Toast('复制成功');
        var localData = ipcRenderer.sendSync('pasteData',event.currentTarget.textContent)
      },
      onChange(index) {
        if('label' == index){
          this.activeLaber = true;
          this.activeSetting = false;
        }
        if('setting' == index){
          this.activeLaber = false;
          this.activeSetting = true;
        }
      },
      onClickHttp(){
        axios.get("http://yapi.demo.qunar.com/mock/58679/pasteCloud/paste/list")
          .then(function (response) {
            // handle success
            console.log(response);
          })
          .catch(function (error) {
            // handle error
            console.log(error);
          })
      }
    },
    data(){
      return {
        activeLaber:true,
        activeSetting:false,
        list: [],
        loading: false,
        finished: false,
        value1: ''
      }
    }
  }
</script>

<style>

</style>

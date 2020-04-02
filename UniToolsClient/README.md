# Paste记录器

## 技术

```txt
使用 electron-vue脚手架进行搭建 并单独升级electron至7.x.x
使用ioHook进行键盘按键的hook监听 （iohook的版本与node及electron版本有关）
使用lndb进行数据持久化
使用ipc进行主进程与渲染进程通信
使用vant进行组件渲染
自定义了窗口 关闭了系统自带的窗口
hook了ctrl+c 快捷键 记录到了粘贴板
使用路由思路进行了新建窗口 可以使用 Alt+S 快捷键体验
使用 Alt+D 可以唤起主面板
自定义了托盘菜单 并进行事件注册

```


## 运行

```shell
yarn 下载module
yarn run dev : 运行
yarn run build:win32：打包
```

## 其他

pr / star / issure 交流学习

## 感谢

```html
脚手架： 
    https://simulatedgreg.gitbooks.io/electron-vue/content/cn/

托盘开发：
    http://bbs.itying.com/topic/5c21ced8d5488a17e894a7e6

electron文档： 
    https://electronjs.org/docs

vant组件：
    https://youzan.github.io/vant/#/zh-CN/tabbar

全局变量：
    https://segmentfault.com/a/1190000008420925

electron-fix：https://github.com/pangxieju/electron-fix/blob/master/README.md （throw new Error('Electron failed to install correctly, please delete node_modules/electron and try installing again）

新建窗口：
    https://www.jianshu.com/p/98df105042c0 
    https://molunerfinn.com/electron-vue-2/

electron渲染进程报错(require is not defined )：
    https://segmentfault.com/q/1010000019066276  ( nodeIntegration: false)

IOHOOK : 
    https://github.com/wilix-team/iohook/blob/master/docs/usage.md

自定义标题栏：
    https://segmentfault.com/a/1190000011765025

键控代码 
    https://minecraft-zh.gamepedia.com/index.php?title=%E9%94%AE%E6%8E%A7%E4%BB%A3%E7%A0%81&variant=zh-sg

图标库： 
    https://www.iconfont.cn/search/index?searchType=icon&q=close

electron粘贴板： 
    http://blog.yancoder.com/2017/03/31/JS%E8%8E%B7%E5%8F%96%E5%89%AA%E5%88%87%E6%9D%BF%E4%BF%A1%E6%81%AF/
```
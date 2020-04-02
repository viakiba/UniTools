import { app, BrowserWindow } from 'electron'

const path = require('path');
const electron = require('electron');
const ioHook = require('iohook');
const menu = electron.Menu;
const ipcMain = electron.ipcMain;
const tray = electron.Tray;
const clipboard = electron.clipboard;
const globalShortcut = electron.globalShortcut;
const  screen = electron.screen;
const  os = electron.os;

var flag = 1

global.sharedObject = {
  localPasteData: []
}

if (process.env.NODE_ENV !== 'development') {
  global.__static = path.join(__dirname, '/static').replace(/\\/g, '\\\\')
}

let mainWindow
let miniWindow 

const winURL = process.env.NODE_ENV === 'development'
  ? `http://localhost:9080`
  : `file://${__dirname}/index.html`

function createWindow () {
  menu.setApplicationMenu(null)

  /**
   * Initial window options
   */
  mainWindow = new BrowserWindow({
    height: 520,
    width: 360,
    // 隐藏系统工具栏
    frame: false,
    // 禁止窗口大小调整
    resizable:false,
    //禁掉同源策略 跨域
    webPreferences: {
      webSecurity: false,
      nodeIntegration: true
    },
  })

  mainWindow.loadURL(winURL)
  
  if (process.env.NODE_ENV === "development") {
    mainWindow.webContents.on("did-frame-finish-load", () => {
      mainWindow.webContents.once("devtools-opened", () => {
        mainWindow.focus();
      });
      mainWindow.webContents.openDevTools();
    });
  }
  mainWindow.on('closed', () => {
    mainWindow = null
  })

  globalShortcut.register('Alt+S', testNewRouter);

  globalShortcut.register('Alt+D', () => {
    if(!mainWindow.isFocused()){
      mainWindow.show();
    }
  })

  // var appIcon = new tray('/static/pic/lover.png').replace(/\\/g, '\\\\');
  var appIcon = new tray(path.join(__dirname,'icon/lover.png').replace(/\\/g, '\\\\'));
  const menuMini = menu.buildFromTemplate( [
    {
        label: '粘贴云端',
        click: function () {
          console.log("1=------------")
        }
    },
    {
        label: '云端拷贝',
        click: function () {
          console.log("2=------------")
        }
    },
    {
      label: '记录至本地',
      click: copyFun
    },
    {
        label: '退出',
        click: function () {
            // BrowserWindow.getFocusedWindow().webContents().send('close-main-window');
             app.quit();
        
        }
    }
  ])
  
  appIcon.setToolTip('PasteCloud');
  appIcon.setContextMenu(menuMini);
  
  appIcon.on('click',()=>{    
    if(!mainWindow.isFocused()){
      mainWindow.show();
    }
  })
}

app.on('ready', createWindow)

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', () => {
  if (mainWindow === null) {
    createWindow()
  }
})

ipcMain.on('pasteData', (event, arg)=> {
  console.log(arg);
  clipboard.writeText(arg," [ 'text/plain', 'text/html' ]");   
  event.returnValue = '';
});

ipcMain.on('localPasteData', e=> {
    if(flag == 1){
      e.returnValue = flag;
      flag = 0;
    }
    e.returnValue = flag;
});
ipcMain.on('close', e=> {
  console.info(mainWindow.isFocused());
  if(!mainWindow.isFocused()){
    mainWindow=null;
  }else{
      e.preventDefault();  /*阻止应用退出*/
      mainWindow.hide(); /*隐藏当前窗口*/
  }
});

Date.prototype.Format = function (fmt) {
	var o = {
		"M+": this.getMonth() + 1, // 月份
		"d+": this.getDate(), // 日
		"h+": this.getHours(), // 小时
		"m+": this.getMinutes(), // 分
		"s+": this.getSeconds(), // 秒
		"q+": Math.floor((this.getMonth() + 3) / 3), // 季度
		"S": this.getMilliseconds() // 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

const testNewRouter = (e,args) => {
  menu.setApplicationMenu(null) // 关闭子窗口菜单栏
  const modalPath = process.env.NODE_ENV === 'development'
    ? 'http://localhost:9080/#miniWindow'
    : `file://${__dirname}/#miniWindow`
// 使用hash对子页面跳转，这是vue的路由思想
  miniWindow = new BrowserWindow({
    width: 600,
    height: 400,
    webPreferences: {
      webSecurity: false,
      nodeIntegration: true
    },
    parent: mainWindow // mainWindow是主窗口
  })
 
  console.log(modalPath)
  miniWindow.loadURL(modalPath)
  miniWindow.openDevTools()
  miniWindow.on('closed', () => {
    miniWindow = null
  })
}

ioHook.on("keypress", event => { 
  // console.log(event)
  // console.log("rawcode"+event.rawcode)
  // console.log(event.rawcode==67)
  if(event.rawcode == 67){
    // console.log( clipboard.readText() );
  }
  if(event.altKey & !event.ctrlKey){
    if(event.keyChar == 115){

    }
  }
  if(!event.altKey & event.ctrlKey){
    if(event.keyChar == 99){
      // console.log( clipboard.readText() );
    }
  }


}  
); 
const id = ioHook.registerShortcut([29, 46], (keys) => {
},(keys) => {
  copyFun();
console.log('Shortcut called with keys:',  clipboard.readText() )
});
ioHook.start();


const copyFun = function copy (event) {
  var types = clipboard.availableFormats()
  console.log(types)
  for (const type in types) {
    if (types[type] == 'text/plain') {
      var object = new Object();
      object.content = clipboard.readText();
      object.time = new Date();
      object.date = new Date().Format("MM-dd hh:mm:ss");
      object.status = 1;// 本地
      object.type = 1;// 文字
      global.sharedObject.localPasteData.push(object);
      //设置保存的数量
      if(global.sharedObject.localPasteData.length > 50 ){
        global.sharedObject.localPasteData.shift();
      }
      flag = 1;
      console.info(JSON.stringify(global.sharedObject.localPasteData) );//转json
    }
    if (types[type] == 'image/png') {
      
    }
  }
}
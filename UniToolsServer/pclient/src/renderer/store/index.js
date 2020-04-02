const {remote} = require('electron');
const userDataPath = remote.app.getPath('userData');

const LNDB = require('lndb')
const db = new LNDB(userDataPath)

console.debug(userDataPath)
// 初始类型
const pg = db.init('.')

// 写入数据
function setStoreFunction(storeType,value){
    localStorage.setItem('storedData1', "dasdsa")
　　pg.set(storeType, value)
}

function readStoreFunction(storeType,value){
　　pg.set(storeType, value)
}


export default {setStoreFunction,readStoreFunction}

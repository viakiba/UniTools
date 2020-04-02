import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode:'hash',
  routes: [
    {
      path: '/',
      name: 'landing-page',
      component: require('@/components/PasteDataList').default,
      beforeEnter: (to, from, next) => {
        console.log('PasteDataList>>>>>>>>>>>>>>>>1111111111')
        next()
      }
    },
    {
      path: '/landing',
      name: 'landing',
      component: require('@/components/PasteDataList').default,
      beforeEnter: (to, from, next) => {
        console.log('PasteDataList>>>>>>>>>>>>>>>>1111111111')
        next()
      }
    },
    {
      path: '/miniWindow',
      name: 'miniWindow',
      component: require('@/components/miniWindow').default,
    },
    {
      path: '/capture',
      name: 'capture',
      component: require('@/components/capture').default,
    }
  ]
})

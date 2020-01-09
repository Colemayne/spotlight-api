import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import Create from '@/views/Create'
import Documentation from '@/views/Documentation'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/create',
      name: 'Create',
      component: Create
    },
    {
      path: '/documentation',
      name: 'Documentation',
      component: Documentation
    }
  ]
})

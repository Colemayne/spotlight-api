import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import Create from '@/views/create/Create'
import Application from '@/views/create/Application'
import Endpoints from '@/views/create/Endpoints'
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
      path: '/teams',
      name: 'Create',
      component: Create
    },
    {
      path: '/edit/:team',
      name: 'chooseApplication',
      component: Application,
    },
    {
      path: '/endpoints/:app',
      name: 'chooseEndpoint',
      component: Endpoints,
    },
    {
      path: '/documentation',
      name: 'Documentation',
      component: Documentation
    }
  ]
})

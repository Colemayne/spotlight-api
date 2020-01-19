import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import Create from '@/views/create/Create'
import Application from '@/views/create/Application'
import Endpoints from '@/views/create/Endpoints'
import EndpointVersion from '@/views/create/EndpointVersion'
import Documentation from '@/views/document/Documentation'
import DocumentationApps from '@/views/document/DocumentationApps'
import DocumentationView from '@/views/document/DocumentationView'
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
      path: '/edit',
      name: 'Create',
      component: Create
    },
    {
      path: '/edit/:team',
      name: 'chooseApplication',
      component: Application,
    },
    {
      path: '/edit/:team/:app',
      name: 'chooseEndpoint',
      component: Endpoints,
    },
    {
      path: '/versions/:endpoint',
      name: 'chooseVersion',
      component: EndpointVersion,
    },
    {
      path: '/documentation',
      name: 'Documentation',
      component: Documentation
    },
    {
      path: '/documentation/:team',
      name: 'DocumentationApps',
      component: DocumentationApps
    },
    {
      path: '/documentation/:team/:app',
      name: 'DocumentationView',
      component: DocumentationView
    }
  ]
})

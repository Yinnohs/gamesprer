import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import NotFoundView from '@/views/NotFoundView.vue'
import LoginView from '@/views/LoginView.vue'
import FindGamesView from '@/views/FindGamesView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      meta:{
        isAuth: false
      }
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
      meta:{
        isAuth: false
      }
    },
    {
      path: '/find',
      name: 'find',
      component: FindGamesView,
      meta:{
        isAuth: true
      }
    },
    {
      path: '/:catchAll(.*)*',
      name: '404',
      component: NotFoundView,
      meta:{
        isAuth: false
      }
    },
  ],
})

router.beforeEach((to, from, next)=>{
  if(to.matched.some(record => record.meta.isAuth)){
    const user = JSON.parse(localStorage.getItem('user')!)
    if(!user){
      next('/login')
    }
  }
  next()
})

export default router

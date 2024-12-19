import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path: '/login',
      name: 'login',
      component: () => import('../views/Login.vue'),
    },
    {
      path: '/',
      component: () => import('../views/Layout.vue'),
      children: [
        {
          path: '/passenger',
          component: () => import('../views/layout/Passenger.vue')
        },
        {
          path: 'welcome',
          component: () => import('../views/layout/WelcomePage.vue')
        },
        {
          path: '/batch/',
          children: [{
            path: 'job',
            component: () => import('../views/batch/Job.vue')
          }]
        }
      ]
    },
    {
      path: '',
      redirect: '/welcome '
    }
  ],
})

export default router

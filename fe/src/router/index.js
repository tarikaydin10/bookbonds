import { createRouter, createWebHistory } from 'vue-router'
import { useAuth } from '@/composables/useAuth'
import ClubDetailsView from '@/views/ClubDetailsView.vue'
const { token } = useAuth()

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/LoginView.vue'),
    beforeEnter: (to, from, next) => {
      if (token.value) {
        next({ name: 'Home' }) // Redirect to Home if already logged in
      } else {
        next()
      }
    }
  },
  
  {
    path: '/about',
    name: 'About',
    component: () => import('@/views/AboutView.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/',
    name: 'Clubs',
    component: () => import('@/views/ClubsView.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/clubs/:id',
    name: 'ClubDetails',
    component: () => import('@/views/ClubDetailsView.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/clubs/:clubId/journeys/:journeyId',
    name: 'JourneyDetails',
    component: () => import('@/views/JourneyDetailsView.vue'),
    meta: { requiresAuth: true }
  },


  // Weitere Routen hier hinzufügen
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth && !token.value) {
    next({ name: 'Login' })
  } else {
    next()
  }
})

export default router

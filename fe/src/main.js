import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import { useAuth } from '@/composables/useAuth'
import './assets/tailwind.css'

// Erstelle die App-Instanz
const app = createApp(App)

// Verwende Pinia und den Router
app.use(createPinia())
app.use(router)

// Fetch user (automatisches Login)
const { fetchUser } = useAuth()

fetchUser().finally(() => {
  // Die App wird erst dann gemountet, wenn der Fetch-User-Vorgang abgeschlossen ist
  app.mount('#app')
})

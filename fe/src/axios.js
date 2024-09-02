import axios from 'axios'
import { useAuth } from '@/composables/useAuth'

const { token, logoutUser } = useAuth()

axios.interceptors.request.use(config => {
  if (token.value) {
    config.headers.Authorization = `Bearer ${token.value}`
  }
  return config
})

axios.interceptors.response.use(
  response => response,
  error => {
    if (error.response.status === 401) {
      logoutUser()
    }
    return Promise.reject(error)
  }
)

export default axios

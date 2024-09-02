import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'



const token = ref(localStorage.getItem('token') || null)
const user = ref(null)

export function useAuth() {
  const apiUrl = import.meta.env.VITE_API_BASE_URL;
  const router = useRouter();


  const loginUser = async (username, password) => {
    try {
      const response = await axios.post(apiUrl +'/auth/signin', { username, password })
      token.value = response.data.token
      localStorage.setItem('token', token.value)
      await fetchUser()
      console.log(user)
      router.push('/')
    } catch (error) {
      throw new Error('Login failed')
    }
  }

  const logoutUser = () => {
    token.value = null
    user.value = null
    localStorage.removeItem('token')
    router.push('/login')
  }

  const fetchUser = async () => {
    if (!token.value) return

    try {
      const response = await axios.get(apiUrl + '/user', {
        headers: {
          Authorization: `Bearer ${token.value}`
        }
      })
      user.value = response.data
    } catch (error) {
      logoutUser()
    }
  }

  const getUserData = () => {
    return user.value
  }

  const isAuthenticated = () => {
    console.log(!!token.value)
    return !!token.value
  }


  return {
    token,
    user,
    loginUser,
    logoutUser,
    fetchUser,
    getUserData,
    isAuthenticated
  }
}

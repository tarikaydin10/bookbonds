
<script setup>
import { ref } from 'vue'
import { useAuth } from '@/composables/useAuth'
import IconLogo1 from '@/components/icons/IconLogo1.vue'
const username = ref('')
const password = ref('')
const error = ref('')

const { loginUser } = useAuth()

const login = async () => {
  error.value = ''
  try {
    await loginUser(username.value, password.value)
  } catch (err) {
    error.value = 'Login fehlgeschlagen'
  }
}
</script>


<template>
  <div class="flex flex-col items-center justify-center min-h-screen font-body space-y-20">
    <div class="flex space-x-12 items-center">
      <IconLogo1 :width="'160px'" :strokeColor="'#000'" :strokeWidth="18" />
      <h1 class="font-heading text-h1" :style="{
        fontSize: '4em'
      }">BookBonds</h1>

    </div>
    <div class="flex flex-col items-center gap-y-4 ">

      <p class="bg-red-400 px-8 py-1 rounded-xl font-accent text-red-900" v-if="error">{{ error }}</p>

      <form @submit.prevent="login" class="p-8 bg-primary-500 rounded-lg shadow-lg w-full max-w-md">

        <input v-model="username" placeholder="Username" class="w-full p-3 mb-4 border rounded" />
        <input v-model="password" type="password" placeholder="Passwort" class="w-full p-3 mb-4 border rounded" />
        <button class="bg-white-50 py-2 px-4 rounded-full font-accent hover:bg-primary-400 mt-2"
          type="submit">Login</button>
      </form>
    </div>
  </div>
</template>

<script setup>


import { RouterLink, RouterView } from 'vue-router'
import SideNavigation from '@/components/SideNavigation.vue'
import BottomNavigation from '@/components/BottomNavigation.vue'

import { useAuth } from '@/composables/useAuth'
const auth = useAuth()
const { isAuthenticated } = useAuth()
const user = auth.user;

</script>

<template>
  <div class="flex min-h-screen">
    <div v-if="isAuthenticated()">
      <!-- Sidebar -->
      <header class="hidden md:flex">
        <SideNavigation />
      </header>

      <header class="flex md:hidden">
        <BottomNavigation />
      </header>

    </div>
    <!-- Main Content Area -->
    <main class="flex-1">
      <RouterView />
    </main>
  </div>
</template>

<style scoped></style>

<template>
    <!-- Mobile Navigation (Bottom Navbar) -->
    <nav
        class="md:hidden fixed bottom-0 inset-x-0 bg-primary-50 text-white shadow-t flex justify-around py-2 z-50 shadow-md font-body">
        <router-link to="/" class="flex flex-col items-center" active-class="text-primary-300">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m0 0l-7 7-7-7m14 0v10a1 1 0 01-1 1h-3" />
            </svg>
            <span class="text-xs">Clubs</span>
        </router-link>
        <router-link to="/journeys" class="flex flex-col items-center" active-class="text-primary-300">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M9 20l-5.447-2.724A2 2 0 013 15.382V8.618a2 2 0 011.553-1.894L9 4m6 16l5.447-2.724A2 2 0 0021 15.382V8.618a2 2 0 00-1.553-1.894L15 4m-6 16V4m6 16V4" />
            </svg>
            <span class="text-xs">Reisen</span>
        </router-link>
        <router-link to="/profile" class="flex flex-col items-center" active-class="text-primary-300">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M5.121 19.121A4 4 0 017 17h10a4 4 0 011.879.5M9 11a4 4 0 100-8 4 4 0 000 8zm12 10a9 9 0 10-18 0h18z" />
            </svg>
            <span class="text-xs">Profil</span>
        </router-link>
    </nav>
</template>
  
<script setup>
import { ref } from 'vue';
import { useAuth } from '@/composables/useAuth';
const auth = useAuth();
const user = auth.user;
</script>

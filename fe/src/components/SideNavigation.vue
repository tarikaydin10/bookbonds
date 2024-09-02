<template>
    <!-- Desktop Navigation (Left Sidebar) -->
    <nav class="fixed w-64 font-body inset-y-0 left-0">
        <div class="flex flex-col bg-primary-100 py-8 px-4 h-full">
            <!-- Logo und Navigation -->
            <div class="flex flex-col w-full">
                <div class="flex items-center bg-primary-100">
                    <!-- Logo -->
                    <router-link to="/" class="flex flex-row items-center gap-x-4 border-b border-black-300 pb-6">
                        <IconLogo1 :width="'60px'" :strokeColor="'#000'" :strokeWidth="18" />
                        <h1 class="font-heading text-big">BookBonds</h1>
                    </router-link>
                </div>
                <div class="flex-1 flex flex-col mt-4 py-4">
                    <router-link to="/" class="py-4 rounded-2xl px-6 hover:bg-primary-200" active-class="bg-primary-300">
                        Clubs
                    </router-link>
                    <hr class="border-1 border-black-300" />
                    <router-link to="/profile" class="py-4 px-6 rounded-2xl hover:bg-primary-200"
                        active-class="bg-primary-300">
                        Profil
                    </router-link>
                </div>
            </div>

            <!-- User Section -->
            <div class="w-full mt-auto p-4">
                <h2 v-if="user">Hallo {{ user.username }} </h2>
                <button v-if="user" @click="auth.logoutUser" class="text-white">Logout</button>
                <router-link v-else to="/login" class="text-white">Login</router-link>
            </div>
        </div>
    </nav>
</template>
  
<script setup>
import { ref } from 'vue';
import { useAuth } from '@/composables/useAuth';

import IconLogo1 from '@/components/icons/IconLogo1.vue';

const auth = useAuth();
const user = auth.user;
</script>

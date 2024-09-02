<template>
    <div>
        <button @click="goToClubs"
            class="mb-8 font-body mt-2 flex items-center space-x-2 text-gray-600 hover:text-gray-800 focus:outline-none">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor"
                class="w-5 h-5">
                <path stroke-linecap="round" stroke-linejoin="round" d="M15 19l-7-7 7-7" />
            </svg>
            <span class="text-lg">Zurück zu Clubs</span>
        </button>

        <!-- Club Details -->
        <div class="mb-6 px-8 py-6 bg-primary-500 text-white-100 rounded-3xl  " :style="{
            backgroundColor: currentTheme?.bgColor,
            textColor: currentTheme?.textColor
        }">
            <h2 class="font-heading font-h3 text-h1 font-bold">{{ club.title }}</h2>

            <p class="mt-2 mb-5 font-body text-p text-white-300">{{ club.description }}</p>
            <hr class="border-t border-solid rounded-full mb-3" :style="{
                borderColor: shadeColor(currentTheme.textColor, -70)
            }" />
            <div class="mb-6">
                <ClubMemberOverview :members="club.members" />
            </div>
            <p class="text-small font-body font-light text-white-300">Erstellt am: {{ new
                Date(club.createdAt).toLocaleDateString() }}</p>

        </div>
    </div>
</template>

<script setup>


const props = defineProps({
    club: Object,
    currentTheme: Object,
});

import { useColor } from '@/composables/useColor.js'
const { shadeColor } = useColor();

import { useRouter } from 'vue-router';
const router = useRouter();


const goToClubs = () => {
    router.push({ name: 'Clubs' });

}


</script>
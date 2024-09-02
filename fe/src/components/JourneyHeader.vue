<template>
  <div class=" flex items-center gap-x-4 px-8 py-2 bg-primary-500 rounded-3xl mb-12 " :style="{
    backgroundColor: clubTheme?.bgColor,
    color: clubTheme?.textColor
  }">
    <svg @click="gotToClubDetail()" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="2"
      stroke="currentColor" class="w-5 h-5">
      <path stroke-linecap="round" stroke-linejoin="round" d="M15 19l-7-7 7-7" />
    </svg>
    <h2 class="font-heading font-accent text-h3">{{ club?.title }}</h2>

  </div>

  <div class="mb-4">
    <h1 class="text-center font-detail text-h1 font-h1 text-primary-800 mt-6 mb-8">{{ journey.title }}</h1>
    <p class="text-center font-body  text-bigger text-gray-800">{{ journey.description }}</p>
    <p class="text-center font-light text-gray-500 text-smaller py-2 font-detail">Gestartet am: {{
      formatDate(journey.createdAt) }} Uhr</p>
  </div>
</template>
  
<script setup>
import { DeprecationTypes } from 'vue';
import { onMounted } from 'vue';
import { ref, defineComponent } from 'vue';

import { useMoments } from '@/composables/useMoments';

import { useRouter } from 'vue-router';
const router = useRouter();

const { formatDate } = useMoments();

const props = defineProps({
  club: Object,
  journey: Object,
  clubTheme: Object,

});



const gotToClubDetail = () => {
  router.push({ name: 'ClubDetails', params: { id: props.club.clubId } });

}


</script>
  

  
<template>
  <ul class="grid grid-cols-1 md:grid-cols-2 gap-y-12 place-items-center">
    <li v-for="journey in journeys" :key="journey.journeyId" @click="$emit('openJourneyDetails', journey.journeyId)"
      class="shadow-lg rounded-3xl bg-white-50 w-64 bg-white  hover:shadow-xl transition cursor-pointer">
      <!-- Fortschrittsbalken und Motivationsnachricht -->

      <div class="px-4 pt-2 pb-4 bg-primary-100 rounded-t-3xl">

        <ProgressBar class="h-2 bg-gray-200 shadow-inner rounded-lg " :progress="journey.progress"
          :primaryColor="'primary-500'" />
        <p class="text-primary-700 font-body text-small font-accent mt-3">
          <span class="font-h2 text-smaller"> {{ journey.progress.toFixed(2) }}% </span> komplett: {{
            getProgressMessage(journey.progress) }}
        </p>
      </div>

      <div class="px-4 pt-4 pb-6 flex flex-col items-center content-center">

        <h3 class="text-center font-detail text-big font-h3 text-primary-800 mb-4 line-clamp-2">{{ journey.title }}</h3>
        <div class="flex items-center">

          <img class="w-20 h-full shadow-md" src="@/assets/images/example-cover.jpeg" />
          <!-- Titel und Buchinformationen -->
          <div class="ml-6">
            <p class="text-p font-detail font-accent text-gray-500">{{ journey.book.title }}</p>
            <p class="text-small font-lighter font-body text-gray-500 -mt-1">{{ journey.book.author }}</p>

            <div class="mt-2 text-small font-detail text-gray-500">
              <p>Kapitel: {{ journey.numberOfChapters }}</p>
              <p>Seiten: {{ journey.book.numberOfPages }}</p>
            </div>
          </div>
        </div>
      </div>
    </li>
  </ul>
</template>

<script setup>
import { ref } from 'vue'

import { useMessages } from '@/composables/useMessages'

import ProgressBar from '@/components/Molecules/ProgressBar.vue'

const { getProgressMessage } = useMessages();
const props = defineProps({
  journeys: Array,
});


</script>

<style scoped>
.bg-primary-100 {
  background-color: #f3e8ff;
}

.bg-primary-500 {
  background-color: #9f7aea;
}

.text-primary-700 {
  color: #6b46c1;
}

.shadow-lg {
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
}
</style>

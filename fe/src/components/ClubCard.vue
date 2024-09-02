<template>
  <div
    class="club-card transition duration-500 shadow-lg max-w-40 w-40 h-40 px-6 py-4 flex flex-col rounded-3xl cursor-pointer hover:bg-secondary-600 hover:ease-in-out duration-200 transform transition-transform"
    :style="{
      backgroundColor: currentTheme.bgColor,
      textColor: currentTheme.textColor
    }" @click="$emit('openClub', club.clubId)">
    <h3 class="font-heading font-accent text-h3 leading-7 text-white-100 pb-1 line-clamp-2">{{ club.title }}</h3>
    <p class="font-body font-lighter text-white-300 flex-grow line-clamp-2"> {{ club.description }}</p>

    <div class="flex w-full space-x-4">
      <!-- Members Section -->
      <div class="flex items-center space-x-2">
        <IconMember :color="'#ddd'" class="w-4 h-4" />
        <p class="font-body text-big font-accent text-white-200">{{ club?.members?.length }}</p>
      </div>

      <!-- Journeys Section -->
      <div class="flex items-center space-x-2">
        <IconJourney :color="'#ddd'" class="w-4 h-4" />
        <p class="font-body text-big font-accent text-white-200">{{ club?.journeys?.length }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import IconJourney from '@/components/icons/IconJourney.vue';
import IconMember from '@/components/icons/IconMember.vue';
import { useTheme } from '../composables/useTheme';

const { getTheme } = useTheme();

const props = defineProps({
  club: Object,
});

const currentTheme = ref(getTheme(props.club.theme));
</script>

<style scoped>
.club-card:hover {
  transform: scale(1.125);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
  /* Verstärkter Schatten beim Hovern */

  /* Vergrößerung um 5% beim Hovern */
}
</style>

<template>
  <div class="clubs-list max-w-xl grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-y-8 gap-x-8 justify-items-center">
    <ClubCard 
      v-for="club in clubs" 
      :key="club.clubId" 
      :club="club" 
      @openClub="openClub"
    />

    <SecondaryButton class="max-w-40 w-40 h-20 font-heading" @click="openClubInput" >Club erstellen</SecondaryButton>

  </div>
</template>

<script setup>
import ClubCard from './ClubCard.vue';
import SecondaryButton from '@/components/Molecules/SecondaryButton.vue'

const props = defineProps({
  clubs: Array,

});

const emit = defineEmits(['openClub', 'openInput']);

const openClubInput = () => {
  emit('openInput');
};

const openClub = (clubId) => {
  emit('openClub', clubId);
};
</script>

<style scoped>
/* Keine weiteren Anpassungen notwendig */
</style>

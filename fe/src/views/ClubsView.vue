<template>
  <div class="bg-gray-100 min-h-screen p-6">
    <div class="max-w-4xl mx-auto">
      <div class="flex justify-between items-end p-4  mb-16">
        <h2 class="font-heading font-h2 text-h1">Meine Buchclubs</h2>

      </div>
      <div class="flex justify-start">    
 
         <ClubList :clubs="clubs" @openClub="openClub" @openInput="openClubInput" />

      </div>
      
      <ClubInputForm 
        v-if="isClubInputVisible" 
        @close="closeClubInput" 
        @create="createClub"
        @navigate="navigateToClub"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import ClubList from '@/components/ClubList.vue';

import PrimaryButton from '@/components/Molecules/PrimaryButton.vue'
import SecondaryButton from '@/components/Molecules/SecondaryButton.vue'

import ClubInputForm from '@/components/ClubInputForm.vue';
import { useClubs } from '@/composables/useClubs';
import { useRouter } from 'vue-router';

const { clubs, loadClubs, createNewClub } = useClubs();
const router = useRouter();
const isClubInputVisible = ref(false);

const openClub = (clubId) => {
  router.push({ name: 'ClubDetails', params: { id: clubId } });
};

const openClubInput = () => {
  isClubInputVisible.value = true;
};

const closeClubInput = () => {
  isClubInputVisible.value = false;
};

const createClub = async (formData) => {
  const createdClub = await createNewClub(formData);
  console.log('Created Club:', createdClub.clubId); // Verifizieren, dass das Objekt korrekt erstellt wurde
  if(createdClub && createdClub.clubId){
    closeClubInput();
    navigateToClub(createdClub.clubId);
  }
};

const navigateToClub = (clubId) => {
  router.push({ name: 'ClubDetails', params: { id: clubId } });
};

onMounted(() => {
  loadClubs();
});
</script>

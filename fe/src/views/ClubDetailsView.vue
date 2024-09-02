<template>
  <div class="bg-gray-100 min-h-screen">


    <div v-if="club" class="max-w-4xl mx-auto p-6">
      <ClubHeader :club="club" :currentTheme="currentTheme" />

      <!-- Journeys Section -->
      <div class="pt-8">
        <div class="flex justify-between items-center mb-16 px-4">
          <h3 class="font-detail  font-h3 text-h3 text-gray-800">Journeys</h3>

          <SecondaryButton class="font-detail font-lighter" @click="openJourneyInput">Journey starten</SecondaryButton>

        </div>
        <JourneyList :journeys="club.journeys" @openJourneyDetails="openJourneyDetails" />

      </div>

      <!-- JourneyInputForm integriert das Buch-Input -->
      <JourneyInputForm v-if="isJourneyInputVisible" :journeyData="newJourney" @close="closeJourneyInput"
        @createJourney="startJourney" />
    </div>

    <div v-else class="text-center p-6 text-gray-500">
      <p>Loading...</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();

import { useJourneys } from '@/composables/useJourneys';
import { useClubs } from '@/composables/useClubs';
import ClubMemberList from '@/components/ClubMemberList.vue';
import PrimaryButton from '@/components/Molecules/PrimaryButton.vue';
import SecondaryButton from '@/components/Molecules/SecondaryButton.vue';
import ClubHeader from '@/components/ClubHeader.vue'

import JourneyList from '@/components/JourneyList.vue';

import JourneyInputForm from '@/components/JourneyInputForm.vue';
import ClubMemberOverview from '@/components/ClubMemberOverview.vue';
import { useTheme } from '../composables/useTheme';


const { getTheme } = useTheme();


const clubId = router.currentRoute.value.params.id;
const { getClubById } = useClubs();

const club = ref(null);
const isJourneyInputVisible = ref(false);

const newJourney = ref({
  title: '',
  description: '',
  book: null,
  numberOfChapters: 0,
});
const currentTheme = ref(null)


const openJourneyDetails = (journeyId) => {
  router.push({ name: 'JourneyDetails', params: { clubId, journeyId } });
};

const openJourneyInput = () => {
  isJourneyInputVisible.value = true;
};

const closeJourneyInput = () => {
  isJourneyInputVisible.value = false;
};


const startJourney = async (journeyData) => {
  try {
    journeyData.bookId = newJourney.value.book.isbn13;
    journeyData.clubId = clubId;

    const response = await createJourney(journeyData);
    console.log('Journey erfolgreich erstellt:', response);

    // Club-Daten nach dem Erstellen der Journey erneut abrufen
    const updatedClub = await getClubById(clubId);
    if (updatedClub) {
      club.value = updatedClub;
    }
    if (club.value) {
      club.value.journeys.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
    }

    // Journey-Input zurücksetzen
    newJourney.value = {
      title: '',
      description: '',
      book: null,
      numberOfChapters: 0,
    };

    closeJourneyInput();
    openJourneyDetails(response.journeyId)
  } catch (error) {
    console.error('Fehler beim Erstellen der Journey:', error);
  }
};

onMounted(async () => {
  const foundClub = await getClubById(clubId);
  if (foundClub) {
    currentTheme.value = getTheme(foundClub.theme);

    foundClub.members.sort((a, b) => {
      if (a.role === 'HOST' && b.role !== 'HOST') {
        return -1; // a kommt vor b
      } else if (a.role !== 'HOST' && b.role === 'HOST') {
        return 1; // b kommt vor a
      } else {
        return 0; // a und b bleiben in der ursprünglichen Reihenfolge
      }
    });

    club.value = foundClub;
  }

});


</script>

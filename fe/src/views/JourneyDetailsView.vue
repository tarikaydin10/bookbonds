<template>
  <div v-if="journey" class="max-w-4xl mx-auto p-6 rounded-3xl">

    <JourneyHeader class="mb-14" :club="club" :journey="journey" :clubTheme="clubTheme" />

    <div class="bg-primary-100 px-8 py-12 rounded-2xl shadow-md">

      <!-- Buchinformationen -->

      <JourneyBookDetails :journey="journey" />

      <JourneyProgressOverview :journey="journey" />

      <div class=" mb-2 max-w-2xl flex mt-12 font-heading text-gray-800 text-h2">

        <h3 class="font-body font-accent text-big">Diskussion</h3>

      </div>

      <JourneyChapterList :loadJourneyData="loadJourneyData" :chapters="journey.chapters" :toggleRead="toggleRead"
        :toggleFavorite="toggleFavorite" :isFavorite="isFavorite" :isRead="isRead" :getReactions="getReactions"
        :getSortedComments="getSortedComments" />

    </div>

  </div>

  <div v-else class="p-6 text-gray-500 text-center">Loading...</div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
const clubId = router.currentRoute.value.params.clubId;


import { useJourneys } from '@/composables/useJourneys';
import { useAuth } from '@/composables/useAuth';
import { useClubs } from '@/composables/useClubs';

import JourneyProgressOverview from '@/components/JourneyProgressOverview.vue';

import JourneyHeader from '@/components/JourneyHeader.vue';

import JourneyBookDetails from '@/components/JourneyBookDetails.vue'

import JourneyChapterList from '@/components/JourneyChapterList.vue'



import { useTheme } from '@/composables/useTheme';

import { useColor } from '@/composables/useColor'

const { shadeColor } = useColor();

const { getTheme } = useTheme();


const journeyId = router.currentRoute.value.params.journeyId;
const { getJourneyById, addCommentToChapter, reactToChapter } = useJourneys();
const { getUserData } = useAuth();
const { getClubById } = useClubs();
const journey = ref(null);

const currentUser = ref(getUserData());
const currentUserId = ref(currentUser.value.user_id);

const club = ref(null)

const clubTheme = ref(null)

const loadJourneyData = async () => {
  const foundJourney = await getJourneyById(journeyId);
  if (foundJourney) {
    journey.value = foundJourney;
    journey.value.chapters.sort((a, b) => a.chapterNumber - b.chapterNumber);
    foundJourney.chapters.forEach(chapter => {
    });
  }
};
onMounted(async () => {
  await loadJourneyData();
  await loadClubData();
  clubTheme.value = getTheme(club.value.theme)
  console.log(clubTheme.value)
  // Watcher initialisieren, um den Startwert zu berücksichtigen
});

const loadClubData = async () => {
  const foundClub = await getClubById(clubId);
  if (foundClub) {
    club.value = foundClub
  }

};

const getSortedComments = (chapterId) => {
  const chapter = getChapterById(chapterId);

  if (!chapter) {
    console.error(`Chapter with ID ${chapterId} not found`);
    return [];
  }
  const sortedComments = chapter.comments.slice().sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));

  // Sortiere die Antworten jedes Hauptkommentars
  sortedComments.forEach(comment => {
    if (comment.replies && comment.replies.length > 0) {
      comment.replies.sort((a, b) => new Date(a.createdAt) - new Date(b.createdAt));
    }
  });

  return sortedComments;
}

const isRead = (chapterId) => {
  const chapter = getChapterById(chapterId)
  return chapter.reactions.some(reaction => reaction.user.user_id === currentUserId.value && reaction.read);
};

const isFavorite = (chapterId) => {
  const chapter = getChapterById(chapterId)

  return chapter.reactions.some(reaction => reaction.user.user_id === currentUserId.value && reaction.favorited);
};

const getReactions = (chapterId, type) => {
  const chapter = getChapterById(chapterId)
  if (type === 'read') {
    return chapter.reactions.filter(reaction => reaction.read);
  } else if (type === 'favorite') {
    return chapter.reactions.filter(reaction => reaction.favorited);
  }
  return [];
};

const toggleRead = async (chapterId) => {
  const chapter = getChapterById(chapterId)

  await reactToChapter(chapter.chapterId, 'read');
  await loadJourneyData();
};

const toggleFavorite = async (chapterId) => {
  const chapter = getChapterById(chapterId)

  await reactToChapter(chapter.chapterId, 'favorite');
  await loadJourneyData();
};

const getChapterById = (chapterId) => {
  const chapter = journey.value.chapters.find(chapter => chapter.chapterId === chapterId);
  return chapter;
};

</script>

<style scoped></style>

<template>
    <div class="font-detail h-full flex flex-col">
        <div v-if="chapters.length === 0" class="text-gray-500">
            <p>Es wurden noch keine Kapitel erstellt.</p>
            <button class="mt-2 py-2 px-4 bg-white-500 text-white rounded-full hover:bg-blue-700">
                Kapitel setzen
            </button>
        </div>

        <ChapterList :chapters="chapters" :isRead="isRead" :selectChapter="selectChapter" :toggleRead="toggleRead"
            :toggleFavorite="toggleFavorite" :getHeartColor="getHeartColor" :getHeartStrokeColor="getHeartStrokeColor" />

        <Transition name="fade">
            <div v-if="selectedChapterId" class="fixed inset-0 bg-gray-800 opacity-50 z-40"></div>
        </Transition>

        <Transition name="slide-up">
            <ChapterDiscussion v-if="selectedChapterId" :loadJourneyData="loadJourneyData"
                :chapter="getChapterById(selectedChapterId)" :closeChapterDetailOverlay="closeChapterDetailOverlay"
                :isRead="isRead" :toggleRead="toggleRead" :getReactions="getReactions"
                :getSortedComments="getSortedComments" />
        </Transition>
    </div>
</template>

<script setup>
import { ref } from 'vue';

import { useRouter } from 'vue-router';

import ChapterList from '@/components/ChapterList.vue'

import ChapterDiscussion from '@/components/ChapterDiscussion.vue'

const props = defineProps({
    chapters: Array,
    isRead: Function,
    isFavorite: Function,
    toggleRead: Function,
    toggleFavorite: Function,
    getReactions: Function,
    getSortedComments: Function,
    loadJourneyData: Function
});

const router = useRouter();

const clubId = router.currentRoute.value.params.clubId;


const getChapterById = (chapterId) => {
    const chapter = props.chapters.find(chapter => chapter.chapterId === chapterId);
    return chapter;
}

const getHeartColor = (chapterId) => {
    return isFavorite(chapterId) ? '#ea4d8e' : 'none';
};
const getHeartStrokeColor = (chapterId) => {
    return isFavorite(chapterId) ? '#ea4d8e' : '#777';
}


//null setzen und binding oben anpassen
const selectedChapterId = ref(null);

const selectChapter = (chapterId) => {
    selectedChapterId.value = chapterId;
};



const getSortedComments = (chapterId) => {
    return props.getSortedComments(chapterId);
};

const isRead = (chapterId) => {
    return props.isRead(chapterId);
};

const isFavorite = (chapterId) => {
    return props.isFavorite(chapterId);
};

const toggleRead = (chapterId) => {
    return props.toggleRead(chapterId);
};

const toggleFavorite = (chapterId) => {
    return props.toggleFavorite(chapterId);
};

const getReactions = (chapterId, type) => {
    return props.getReactions(chapterId, type);
};

const loadJourneyData = () => {
    props.loadJourneyData();
}

const closeChapterDetailOverlay = () => {
    selectedChapterId.value = null;
};
</script>

<style scoped>
.slide-up-enter-active,
.slide-up-leave-active {
    transition: transform 0.75s ease-in-out;
}

.slide-up-enter-from {
    transform: translateY(100%);
}

.slide-up-leave-to {
    transform: translateY(100%);
}


.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
    opacity: 0;
}
</style>

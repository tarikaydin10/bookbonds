<template>
    <div class="fixed inset-0 z-50 flex items-end justify-center">
        <div class="absolute  inset-0" @click="closeChapterDetailOverlay"></div>
        <div class="relative bg-white-200 pb-4  rounded-t-lg shadow-md w-full h-[90vh] max-w-2xl flex flex-col">
            <div class="p-4 text-gray-700 flex-shrink-0 px-12 pt-8">
                <button class="font-body border border-black-300 px-2 py-1 rounded-md mb-4"
                    @click="closeChapterDetailOverlay">X</button>
                <div class="flex justify-between mb-4">
                    <h4 class="text-big font-accent text-gray-600">{{ chapter.title }}
                    </h4>
                    <div class="relative items-center flex content-center">
                        <div class="absolute top-0 right-0">
                            <Transition name="readButton">
                                <button v-if="!isRead(chapter.chapterId)" @click.stop="toggleRead(chapter.chapterId)"
                                    class="rounded-2xl font-body text-smaller font-accent px-3 py-1.5 text-white-100 bg-gradient-to-r from-purple-500 via-purple-600 to-purple-700 hover:bg-gradient-to-br text-center">
                                    Gelesen
                                </button>
                            </Transition>
                            <Transition name="readButton">
                                <button v-if="isRead(chapter.chapterId)" @click.stop="toggleRead(chapter.chapterId)"
                                    class="absolute top-0 right-0 rounded-2xl font-body text-small font-accent px-3 py-1 text-gray-600 bg-gradient-to-r from-green-100 via-green-200 to-green-300 hover:bg-gradient-to-br text-center">
                                    <span class="flex gap-x-1.5 items-center">
                                        <IconCheck :color="'#777'" class="w-3 h-3" />
                                        Gelesen
                                    </span>
                                </button>
                            </Transition>
                        </div>
                    </div>
                </div>
                <div class="mb-4">
                    <h5 class="font-semibold text-gray-700">Gelesen von:</h5>
                    <ul class="text-gray-500">
                        <li v-for="reaction in getReactions(chapter.chapterId, 'read')" :key="reaction.reactionId"
                            class="flex items-center mb-2">
                            <img :src="reaction.user.avatarUrl" alt="Avatar" class="w-6 h-6 rounded-full mr-2">
                            {{ reaction.user.username }}
                        </li>
                    </ul>
                    <h5 class="font-semibold text-gray-700 mt-4">Favorisiert von:</h5>
                    <ul class="text-gray-500">
                        <li v-for="reaction in getReactions(chapter.chapterId, 'favorite')" :key="reaction.reactionId"
                            class="flex items-center mb-2">
                            <img :src="reaction.user.avatarUrl" alt="Avatar" class="w-6 h-6 rounded-full mr-2">
                            {{ reaction.user.username }}
                        </li>
                    </ul>
                </div>
            </div>

            <!-- Kommentare Bereich -->
            <div class="flex-1 overflow-y-auto p-4">
                <ul class="">
                    <li v-for="comment in getSortedComments(chapter.chapterId)" :key="comment.commentId"
                        class="p-4 bg-white rounded-lg ">

                        <div class="flex items-start">
                            <img :src="comment.user.avatarUrl" alt="Avatar" class="w-8 h-8 rounded-full mr-3">
                            <div class="w-full">
                                <div class="flex justify-between">
                                    <div class="flex gap-x-2">
                                        <p class="text-gray-800 font-accent text-smaller font-body">{{
                                            comment.user.username }}</p>
                                        <p class="text-gray-400 font-body text-smaller">{{
                                            timeAgo(comment.createdAt) }}</p>
                                    </div>
                                    <div v-if="comment.user.user_id === currentUser.user_id">
                                        <p class="font-body cursor-pointer" @click="removeComment(comment.commentId)">X</p>
                                    </div>
                                </div>
                                <p class="text-gray-800 font-detail">{{ comment.content }}</p>
                                <button @click="setReplyParent(comment.commentId)"
                                    class="py-1 px-3 text-smaller text-blue-600">
                                    Antworten
                                </button>
                                <ul class="text-gray-500 ml-4">
                                    <li v-for="reply in comment.replies" :key="reply.commentId" class="mt-2">
                                        <div class="flex items-start mb-2">
                                            <img :src="reply.user.avatarUrl" alt="Avatar" class="w-8 h-8 rounded-full mr-3">
                                            <div class="w-full">
                                                <div class="flex justify-between">
                                                    <div class="flex gap-x-2 items-center">
                                                        <p class="text-gray-800 font-accent font-body text-smaller">
                                                            {{ reply.user.username }}</p>
                                                        <p class="text-gray-400 font-body text-smaller">{{
                                                            timeAgo(reply.createdAt) }}</p>
                                                    </div>
                                                    <p v-if="reply.user.user_id === currentUser.user_id"
                                                        class="font-body cursor-pointer"
                                                        @click="removeComment(reply.commentId)">X</p>
                                                </div>
                                                <p class="text-gray-800">{{ reply.content }}</p>
                                                <button @click="setReplyParent(reply.commentId)"
                                                    class="py-1 px-3 text-smaller">
                                                    Antworten
                                                </button>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <hr class="border-black-300 mt-8 mx-auto" style="border-width: 0.1px; width: 80%;" />

                    </li>
                </ul>
            </div>

            <!-- Eingabebereich -->
            <div class="p-4 bg-gray-100 border-t flex-shrink-0">
                <hr class="mb-4" />
                <div v-if="replyParentId">
                    <p @click="setReplyParent(null)" class="cursor-pointer text-red-500">X</p>
                    <p>{{ 'Antwort auf: ' + replyParentId }}</p>
                </div>
                <div class="flex gap-x-4 items-center">
                    <textarea v-model="newComments[chapter.chapterId]" placeholder="Schreibe einen Kommentar..."
                        class="w-full p-4 border rounded-lg resize-none focus:outline-none focus:ring-2 focus:ring-blue-500 transition"></textarea>
                    <button @click="addComment(chapter.chapterId)"
                        class="px-4 shadow-md h-12 bg-primary-500 flex items-center rounded-full hover:bg-blue-700 transition duration-300">
                        <IconArrowUp :strokeColor="'#FFF'" class="w-5" />
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup>
const props = defineProps({
    chapter: Object,
    closeChapterDetailOverlay: Function,
    isRead: Function,
    toggleRead: Function,
    getReactions: Function,
    getSortedComments: Function,
    loadJourneyData: Function
});
import IconCheck from './icons/IconCheck.vue';
import IconArrowUp from '@/components/icons/IconArrowUp.vue'


import { ref, onMounted } from 'vue';

import { useJourneys } from '@/composables/useJourneys.js'
const { getJourneyById, addCommentToChapter, reactToChapter, deleteComment } = useJourneys();

import { useRouter } from 'vue-router';
const router = useRouter();
const clubId = router.currentRoute.value.params.clubId;

import { useAuth } from '@/composables/useAuth';
const auth = useAuth();
const currentUser = auth.user;

const newComments = ref({});
const replyParentId = ref(null);  // Ref für die Parent-ID, wenn ein Kommentar eine Antwort ist

onMounted(() => {
    console.log(props.chapter)
})


function timeAgo(date) {
    const now = new Date();
    const seconds = Math.floor((now - new Date(date)) / 1000);

    const intervals = {
        year: 31536000,
        month: 2592000,
        week: 604800,
        day: 86400,
        hour: 3600,
        minute: 60,
    };

    if (seconds >= intervals.year) {
        const years = Math.floor(seconds / intervals.year);
        return `${years} Jahr${years !== 1 ? 'en' : ''}`;
    } else if (seconds >= intervals.month) {
        const months = Math.floor(seconds / intervals.month);
        return `${months} Monat${months !== 1 ? 'en' : ''}`;
    } else if (seconds >= intervals.week) {
        const weeks = Math.floor(seconds / intervals.week);
        return `${weeks} Woche${weeks !== 1 ? 'n' : ''}`;
    } else if (seconds >= intervals.day) {
        const days = Math.floor(seconds / intervals.day);
        return `${days} Tag${days !== 1 ? 'en' : ''}`;
    } else if (seconds >= intervals.hour) {
        const hours = Math.floor(seconds / intervals.hour);
        return `${hours} Stunde${hours !== 1 ? 'n' : ''}`;
    } else if (seconds >= intervals.minute) {
        const minutes = Math.floor(seconds / intervals.minute);
        return `${minutes} Minute${minutes !== 1 ? 'n' : ''}`;
    } else {
        return `${seconds} Sekunden`;
    }
}

const setReplyParent = (commentId) => {
    replyParentId.value = commentId;
};

const removeComment = async (commentId) => {
    await deleteComment(commentId);
    loadJourneyData();
};

const loadJourneyData = () => {
    props.loadJourneyData();
}


const addComment = async (chapterId) => {
    const content = newComments.value[chapterId];
    if (content.trim()) {
        const addedComment = await addCommentToChapter(clubId, chapterId, content, replyParentId.value); // Parent ID wird übergeben
        if (addedComment) {
            await loadJourneyData();
            newComments.value[chapterId] = '';
            replyParentId.value = null; // Zurücksetzen der Parent-ID
        }
    }
};
</script>
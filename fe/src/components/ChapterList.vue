<template>
    <transition-group name="chapter" tag="ul">

        <li v-for="chapter in props.chapters" :key="chapter.chapterId" :class="[
            'mb-8 mx-auto max-w-xl py-6 px-6 bg-gradient-to-br rounded-2xl shadow-lg cursor-pointer transition duration-300 ease-in-out ',
            {
                'from-white-100 via-white-200 to-secondary-100 border border-3 border-primary-200 hover:from-secondary-100 hover:to-white-100': chapter.chapterProgress > 0,
                'from-gray-200 to-gray-300': chapter.chapterProgress === 0,
                '': isRead(chapter.chapterId)
            }
        ]" @click="selectChapter(chapter.chapterId)">

            <div class="flex justify-between mb-1">
                <h4 class="text-bigger font-h3 text-gray-700 mb-2">
                    {{ chapter.title }}
                </h4>
                <div class="relative items-center flex content-center">
                    <div class="absolute top-0 right-0">

                        <!-- Transition für den ersten Button -->
                        <Transition name="readButton">
                            <button v-if="!isRead(chapter.chapterId)" @click.stop="toggleRead(chapter?.chapterId)"
                                class="rounded-2xl font-body text-smaller font-accent px-3 py-1.5 text-white-100 bg-gradient-to-r from-purple-500 via-purple-600 to-purple-700 hover:bg-gradient-to-br text-center">
                                Gelesen
                            </button>
                        </Transition>

                        <!-- Transition für den zweiten Button -->
                        <Transition name="readButton">
                            <button v-if="isRead(chapter.chapterId)" @click.stop="toggleRead(chapter?.chapterId)"
                                class="absolute top-0 right-0 rounded-2xl font-body text-small font-accent px-3 py-1 text-gray-600 bg-gradient-to-r from-green-200 via-green-300 to-green-400 text-center">
                                <span class="flex gap-x-1.5 items-center">
                                    <IconCheck :color="'#777'" class="w-3 h-3" />
                                    Gelesen
                                </span>
                            </button>
                        </Transition>
                    </div>
                </div>
            </div>

            <p class="text-gray-600 font-body">Gelesen: {{ chapter.chapterProgress }}%</p>

            <!-- Progress Bar -->
            <ProgressBar class="h-2 bg-gray-200 shadow-inner rounded-lg mt-1 " :progress="chapter.chapterProgress"
                :primaryColor="'primary-500'" />

            <div v-if="isRead(chapter.chapterId)"
                class="flex gap-x-6 font-detail text-white-500 text-bigger font-h3 items-center mt-5 ml-1">
                <div class="flex items-center gap-x-2">
                    <IconHeart @click.stop="toggleFavorite(chapter.chapterId)" :color="getHeartColor(chapter.chapterId)"
                        :stroke="getHeartStrokeColor(chapter.chapterId)" class="w-5.5 h-5" />
                    <p>{{ getLikesCount(chapter) }}</p>
                </div>
                <div class="flex items-center gap-x-2">
                    <IconComment :color="'#777'" class="w-5 h-5" />
                    <p>{{ getCommentCount(chapter) }}</p>
                </div>
            </div>

        </li>
    </transition-group>
</template>
<script setup>
const props = defineProps({
    chapters: Array,
    isRead: Function,
    selectChapter: Function,
    toggleRead: Function,
    toggleFavorite: Function,
    getHeartColor: Function,
    getHeartStrokeColor: Function,
});
import { useChapters } from '../composables/useChapters';
import IconHeart from './icons/IconHeart.vue';
import ProgressBar from './Molecules/ProgressBar.vue';
import IconCheck from './icons/IconCheck.vue';

import IconComment from './icons/IconComment.vue';
const { getLikesCount, getCommentCount } = useChapters();

</script>

<style scoped>
.readButton-enter-active,
.readButton-leave-active {
    transition: opacity .5s ease-in;
}

.readButton-enter-from,
.readButton-leave-to {
    opacity: 0;
    transition: ease-in;
}
</style>
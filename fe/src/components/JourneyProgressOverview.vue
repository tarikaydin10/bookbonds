<template>
    <div class="p-8 h-40 mb-8">

        <ProgressBar class="h-2 bg-gray-200 shadow-inner rounded-lg " :progress="journey.progress"
            :primaryColor="'primary-500'" />
        <p class="text-primary-700 text-bigger font-body mt-3">
            <span class=" text-bigger font-accent mr-2">{{ journey.progress.toFixed(2) }}% </span> {{
                getProgressMessage(journey.progress) }}
        </p>

    </div>
</template>

<script setup>
const props = defineProps({
    journey: Object,
});

import { useMessages } from '@/composables/useMessages';
const { getProgressMessage } = useMessages();

import ProgressBar from '../components/Molecules/ProgressBar.vue';



</script>
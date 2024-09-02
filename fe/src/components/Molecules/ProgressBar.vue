<template>
    <div class="overflow-hidden">
        <div class="h-full shadow-inner rounded-lg transition-width duration-3500 ease-out" :class="'bg-' + primaryColor"
            :style="{ width: `${progress}%` }">
        </div>
    </div>
</template>

<script setup>
import { useMessages } from '@/composables/useMessages';

const props = defineProps({
    progress: Number,
    primaryColor: String,
});
</script>
<style scoped></style>
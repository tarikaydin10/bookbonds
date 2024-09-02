<template>
    <div class=" px-8 mb-8 flex flex-col md:flex-row gap-x-20">
        <div class="flex flex-col flex-1 mb-4 md:mb-0">
            <p class="text-h2 font-body font-h3 text-gray-700">
                {{ journey.book.title }}
            </p>
            <p class="font-accent text-p -mt-1 mb-2 font-body text-gray-500">
                {{ journey.book.author }} <span class="font-p">({{ new Date(journey.book.publishDate).getFullYear()
                }})</span>
            </p>
            <p class="mb-4 text-small font-accent font-body text-gray-600">
                {{ journey.book.description }}
            </p>
            <p class="text-small font-body text-gray-600">{{ journey.book.numberOfPages }} Seiten</p>
            <p class="text-bigger font-body text-gray-600">{{ journey.numberOfChapters }} Kapitel</p>
        </div>
        <div class="max-w-44 flex-1 flex justify-center items-center">
            <img :src="'/src/assets/images/example-cover.jpeg'" alt="Buchcover"
                class="w-full h-auto max-w-xs md:max-w-sm lg:max-w-md shadow-lg rounded-lg">
        </div>
    </div>
</template>

<script setup>
const props = defineProps({
    journey: Object,

});

</script>
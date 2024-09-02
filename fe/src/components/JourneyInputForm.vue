<template>
  <div class="fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-end z-50">
    <div class="bg-primary-100 p-10 rounded-t-lg shadow-lg w-full h-4/5 max-w-xl mx-auto transform transition-transform duration-300 ease-in-out">
      <h3 class="font-detail font-h3 text-h2 text-white mb-12">Journey starten</h3>
      
      <div class="flex flex-col space-y-8 font-body">
        <!-- Journey-Titel -->
        <div class="relative mb-4">
          <label
            :class="{'text-primary-500 -top-4 ': journeyData.title || isFocused.title, 'text-black-800 -top-2.5 ': !journeyData.title && !isFocused.title }"
            class="absolute left-3 bg-primary-100 px-1 transition-all rounded px-2 py-1"
          >
            Journey-Titel
          </label>
          <input
            type="text"
            v-model="journeyData.title"
            @focus="isFocused.title = true"
            @blur="isFocused.title = false"
            required
            :class="{'border-primary-500' :journeyData.title || isFocused.title, 'border-black-500': !journeyData.title && !isFocused.title }"
            class="pt-5 w-full p-3 font-detail border border-2 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-600 bg-primary-100 font-accent"
          />
        </div>

        <!-- Journey-Beschreibung -->
        <div class="relative mb-4">
          <label
            :class="{'text-primary-500 -top-4 ': journeyData.description || isFocused.description, 'text-black-800 -top-2.5 ': !journeyData.description && !isFocused.description }"
            class="absolute left-3 bg-primary-100 px-1 transition-all rounded px-2 py-1"
          >
            Journey-Beschreibung
          </label>
          <textarea
            v-model="journeyData.description"
            @focus="isFocused.description = true"
            @blur="isFocused.description = false"
            :class="{'border-primary-500' :journeyData.description || isFocused.description, 'border-black-500': !journeyData.description && !isFocused.description }"
            class="pt-5 w-full p-3 border border-2 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-600 bg-primary-100 font-accent"
          ></textarea>
        </div>
        
        <!-- Buchauswahl -->
        <button
          @click="isBookInputVisible = true"
          class="bg-indigo-500 text-white px-4 py-2 mb-4 rounded-lg hover:bg-indigo-600 focus:outline-none focus:ring-2 focus:ring-indigo-400"
        >
          Buch auswählen
        </button>
        <p v-if="journeyData.book" class="text-gray-700 mb-4">Ausgewähltes Buch: {{ journeyData.book.title }}</p>

        <!-- Anzahl der Kapitel -->
        <div class="relative mb-4">
          <label
            :class="{'text-primary-500 -top-4 ': journeyData.numberOfChapters || isFocused.numberOfChapters, 'text-black-800 -top-2.5 ': !journeyData.numberOfChapters && !isFocused.numberOfChapters }"
            class="absolute left-3 font-accent bg-primary-100 px-1 transition-all rounded px-2 py-1"
          >
            Anzahl der Kapitel
          </label>
          <input
            type="number"
            v-model.number="journeyData.numberOfChapters"
            @focus="isFocused.numberOfChapters = true"
            @blur="isFocused.numberOfChapters = false"
            min="0"
            :class="{'border-primary-500' :journeyData.numberOfChapters || isFocused.numberOfChapters, 'border-black-500': !journeyData.numberOfChapters && !isFocused.numberOfChapters }"
            class="pt-5 w-full p-3 border border-2 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-600 bg-primary-100 font-accent"
          />
        </div>

        <!-- Buttons -->
        <div class="flex justify-end space-x-4">
          <button
            @click="createJourney"
            class="bg-green-500 text-white px-4 py-2 rounded-lg hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-green-400"
          >
            Jetzt starten
          </button>
          <button
            @click="$emit('close')"
            class="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-400"
          >
            Abbrechen
          </button>
        </div>
      </div>
    </div>

    <!-- JourneyBookInput wird nur angezeigt, wenn isBookInputVisible true ist -->
    <JourneyBookInput v-if="isBookInputVisible" @close="closeBookInput" @select-book="setBook" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import JourneyBookInput from '@/components/JourneyBookInput.vue';

const props = defineProps({
  journeyData: Object,
});
const emit = defineEmits(['createJourney', 'close']);

const isBookInputVisible = ref(false);
const isFocused = ref({
  title: false,
  description: false,
  numberOfChapters: false,
});

const createJourney = () => {
  emit('createJourney', props.journeyData);
};

const setBook = (book) => {
  props.journeyData.book = book;
  isBookInputVisible.value = false;
};

const closeBookInput = () => {
  isBookInputVisible.value = false;
};
</script>

<style scoped>
.journey-input-overlay {
  transform: translateY(100%); /* Start position at the bottom */
  animation: slide-up 0.3s forwards ease-in-out;
}

@keyframes slide-up {
  from {
    transform: translateY(100%);
  }
  to {
    transform: translateY(0);
  }
}
</style>

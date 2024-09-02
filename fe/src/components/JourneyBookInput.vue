<template>
  <div class="fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center z-50">
    <div class="bg-white p-6 rounded-lg shadow-lg max-w-md w-full">
      <h3 class="text-2xl font-semibold text-gray-800 mb-4">Buch auswählen</h3>
      <div class="relative mb-4">
        <label
          :class="{ 'text-blue-500': query || isFocused.query, 'text-gray-500': !query && !isFocused.query }"
          class="absolute left-3 -top-2.5 bg-white px-1 transition-all"
        >
          Buchtitel oder Autor eingeben
        </label>
        <input
          type="text"
          v-model="query"
          @focus="isFocused.query = true"
          @blur="isFocused.query = false"
          class="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
      </div>
      <div class="flex justify-between mb-4">
        <button
          @click="searchBook"
          class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400"
        >
          Suche
        </button>
        <button
          @click="closeBookInput"
          class="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-400"
        >
          Abbrechen
        </button>
      </div>

      <div v-if="searchResults.length > 0" class="space-y-2">
        <ul>
          <li
            v-for="book in searchResults"
            :key="book.isbn13"
            @click="selectBook(book)"
            class="p-3 border border-gray-300 rounded-lg hover:bg-gray-100 cursor-pointer"
          >
            <p class="font-semibold text-gray-800">{{ book.title }}</p>
            <p class="text-gray-600">von {{ book.author }}</p>
            <p class="text-gray-500 text-sm">ISBN: {{ book.isbn13 }}</p>
          </li>
        </ul>
      </div>
      <div v-else>
        <p v-if="hasSearched" class="text-gray-600">Keine Ergebnisse gefunden.</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useBooks } from '@/composables/useBooks';


const emit = defineEmits(['select-book', 'close']);

const query = ref('');
const searchResults = ref([]);
const hasSearched = ref(false);
const isFocused = ref({
  query: false,
});

const { searchBooks } = useBooks();

const searchBook = async () => {
  if (query.value) {
    const results = await searchBooks(query.value);
    searchResults.value = results;
    hasSearched.value = true;
  }
};

const selectBook = (selBook) => {
  emit('select-book', selBook);
  closeBookInput();
};

const closeBookInput = () => {
  emit('close');
};
</script>

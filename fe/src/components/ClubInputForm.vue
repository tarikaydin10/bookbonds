<template>
  <div class="fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-end z-50 transition-all ease-in-out"
    :style="{
      backgroundColor: selectedTheme.bgColor + 'DD',
    }">

    <div
      class="p-10 rounded-t-lg bg-primary-100 shadow-lg w-full h-4/5 max-w-xl mx-auto transform transition-all ease-in-out"
      :style="{
        backgroundColor: selectedTheme.textColor
      }">
      <h3 class="font-body font-h3 text-h2 mb-10">Neuen Club erstellen</h3>


      <div class="flex flex-col space-y-8 font-body">
        <!-- Club-Titel -->
        <div class="relative mb-4">
          <label
            :class="{ 'text-primary-500 -top-4 ': newClub.title || isFocused.title, 'text-black-800 -top-2.5 ': !newClub.title && !isFocused.title }"
            :style="{
              backgroundColor: selectedTheme.textColor
            }" class="absolute left-3 font-accent px-1  bg-primary-100 transition-all ease-in-out rounded px-2 py-1">
            Club-Titel
          </label>
          <input type="text" v-model="newClub.title" @focus="isFocused.title = true" @blur="isFocused.title = false"
            placeholder="Cooler Club" required
            :class="{ 'border-primary-500': newClub.title || isFocused.title, 'border-black-500': !newClub.title && !isFocused.title }"
            :style="{
              backgroundColor: selectedTheme.textColor
            }"
            class="transition-all bg-primary-100 ease-in-out pt-5 w-full p-3 border border-2 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-600 font-accent" />
        </div>

        <!-- Club-Beschreibung -->
        <div class="relative mb-4">
          <label
            :class="{ 'text-primary-500 -top-4 ': newClub.description || isFocused.description, 'text-black-800 -top-2.5 ': !newClub.description && !isFocused.descriptionr }"
            class="absolute left-3 font-accent px-1 bg-primary-100  transition-all ease-in-out rounded px-2 py-1" :style="{
              backgroundColor: selectedTheme.textColor
            }">
            Club-Beschreibung
          </label>
          <textarea v-model="newClub.description" @focus="isFocused.description = true"
            @blur="isFocused.description = false" placeholder="Eine Gruppe ... Leseratten"
            :class="{ 'border-primary-500': newClub.description || isFocused.description, 'border-black-500': !newClub.description && !isFocused.description }"
            class="transition-all ease-in-out bg-primary-100 pt-5 w-full p-3 border border-2 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-600 font-accent"
            :style="{
              backgroundColor: selectedTheme.textColor
            }"></textarea>
        </div>


        <!-- Club-Bild hochladen -->
        <div class="relative mb-4">
          <label
            :class="{ 'text-primary-500 -top-4 ': newClub.image || isFocused.image, 'text-black-800 -top-2.5 ': !newClub.image && !isFocused.image }"
            class="absolute left-3 bg-primary-100 font-accent px-1  transition-all rounded px-2 py-1" :style="{
              backgroundColor: selectedTheme.textColor
            }">
            Club-Bild hochladen
          </label>
          <input type="file" @focus="isFocused.image = true" @blur="isFocused.image = false" @change="handleFileUpload"
            :class="{ 'border-primary-500': newClub.image || isFocused.image, 'border-black-500': !newClub.image && !isFocused.image }"
            class="pt-5 w-full p-3 border border-2 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary-600 font-accent" />
        </div>

        <!-- Hintergrundbild für den Club (als Radio-Buttons) -->
        <div class="relative space-y-4">
          <label class="font-accent text-bigger px-1">
            Wähle ein Theme
          </label>
          <div class="flex flex-wrap  justify-center gap-x-3 gap-y-3 ">
            <div v-for="theme in themes" :key="theme.value" class="flex items-center w-2/5 min-w-1/3">
              <input type="radio" :id="theme.value" :value="theme.value" v-model="newClub.theme" class="hidden">
              <label :for="theme.value" :style="{
                backgroundImage: `url(${theme.bgSmall})`,
                backgroundSize: '',
                backgroundPosition: 'center',
                color: theme.textColor // Textfarbe aus dem theme.textColor-Wert
              }"
                class="font-h3 text-big w-full h-32 rounded border-2 cursor-pointer flex items-center justify-center text-center"
                :class="{ 'border-primary-600': newClub.theme === theme.value, 'border-gray-300': newClub.theme !== theme.value }">
                {{ theme.value.charAt(0).toUpperCase() + theme.value.slice(1) }}
              </label>
            </div>
          </div>
        </div>
      </div>
      <hr class="transition-all border border-gray-400 rounded-2xl mt-8 mb-8" />
      <!-- Buttons -->
      <div class="flex justify-end space-x-4">
        <button @click="createClub" class="bg-gray-300 text-bigger  px-6 py-3 rounded-2xl font-body font-accent shadow-md"
          :style="{
            backgroundColor: selectedTheme.bgColor,
            color: selectedTheme.textColor
          }">
          Erstellen
        </button>
        <button @click="$emit('close')" class="text-gray-600 border border-gray-500 px-4 py-2 rounded-2xl">
          Abbrechen
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useTheme } from '@/composables/useTheme';
const emit = defineEmits(['create', 'close', 'navigate']);


const { themes, selectedTheme, setTheme } = useTheme();


const newClub = ref({
  title: '',
  description: '',
  image: null,
  theme: selectedTheme.value, // Default Hintergrundfarbe
});


watch(
  () => newClub.value.theme,
  (newTheme) => {
    const theme = themes.value.find(t => t.value === newTheme);
    if (theme) {
      selectedTheme.value = theme;
    }
  }
);
const isFocused = ref({
  title: false,
  description: false,
  image: false,
  backgroundColor: false,
});

const handleFileUpload = (event) => {
  const file = event.target.files[0];
  newClub.value.image = file;
};

const createClub = async () => {
  if (newClub.value.title.trim()) {
    const formData = new FormData();
    formData.append('title', newClub.value.title);
    formData.append('description', newClub.value.description);
    formData.append('theme', newClub.value.theme);
    if (newClub.value.image) {
      formData.append('image', newClub.value.image);
    }

    await emit('create', formData);
  }
};



</script>

<style scoped>
.journey-input-overlay {
  transform: translateY(100%);
  /* Start position at the bottom */
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

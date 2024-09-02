// src/composables/useTheme.js
import { ref, watch } from 'vue';
import royalBgSm from '@/assets/images/club-backgrounds/royal_828.webp';
import classicsBgSm from '@/assets/images/club-backgrounds/classics_828.webp';
import evergreenBgSm from '@/assets/images/club-backgrounds/evergreen_828.webp';
import midnightBgSm from '@/assets/images/club-backgrounds/midnight_828.webp';

export function useTheme() {
  const themes = ref([

    { value: 'royal', bgSmall: royalBgSm, textColor: '#A5B4FC', bgColor: '#6366F1' },
    { value: 'classics', bgSmall: classicsBgSm, textColor: '#F7E1D7', bgColor: '#800020' },
    { value: 'evergreen', bgSmall: evergreenBgSm, textColor: '#F7E1D7', bgColor: '#2A9D8F' },
    { value: 'midnight', bgSmall: midnightBgSm, textColor: '#A5B4FC', bgColor: '#1A1A72' },
  ]);

  const selectedTheme = ref({
    value: 'default',
    bgSmall: null,
    textColor:'DDD',
    bgColor:'888'
  }); // Standardmäßig das erste Theme auswählen

  const setTheme = (newTheme) => {
    const theme = themes.value.find(t => t.value === newTheme);
    if (theme) {
      selectedTheme.value = theme;
    }
  };

  const getTheme = (value) => {
   const theme = themes.value.find(t => t.value === value);
    if (theme) {
      return theme;
    }
  }

  return {
    themes,
    selectedTheme,
    setTheme,
    getTheme
  };
}

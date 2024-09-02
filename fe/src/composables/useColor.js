﻿// src/composables/useTheme.js
import { ref, watch } from 'vue';
import royalBgSm from '@/assets/images/club-backgrounds/royal_828.webp';
import classicsBgSm from '@/assets/images/club-backgrounds/classics_828.webp';
import evergreenBgSm from '@/assets/images/club-backgrounds/evergreen_828.webp';
import midnightBgSm from '@/assets/images/club-backgrounds/midnight_828.webp';

export function useColor() {
  function shadeColor(col, amt) {

    var usePound = false;
  
    if (col[0] == "#") {
      col = col.slice(1);
      usePound = true;
    }
  
    var num = parseInt(col, 16);
  
    var r = (num >> 16) + amt;
  
    if (r > 255) r = 255;
    else if (r < 0) r = 0;
  
    var b = ((num >> 8) & 0x00FF) + amt;
  
    if (b > 255) b = 255;
    else if (b < 0) b = 0;
  
    var g = (num & 0x0000FF) + amt;
  
    if (g > 255) g = 255;
    else if (g < 0) g = 0;
  
    return (usePound ? "#" : "") + (g | (b << 8) | (r << 16)).toString(16);
  }

  return {
    shadeColor,
  };
}

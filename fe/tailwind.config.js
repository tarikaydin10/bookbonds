/** @type {import('tailwindcss').Config} */

module.exports = {
  purge: [],
  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
   darkMode: false, // or 'media' or 'class'
   theme: {
    extend: {
      spacing: {
        '5.5': '1.35rem',
      },
      transitionDuration: {
        '3500': '3500ms',
      },
      transitionProperty: {
        'width': 'width'
    },
      backgroundImage: {
        'cotton': "url('/src/assets/images/Cotton.webp')",
        'mosaic': "url('/src/assets/images/Mosaic.webp')",
        'speckles': "url('/src/assets/images/Speckles.webp')",

      },
      colors: {
        yellow: {
          500: '#FFD57E'
        },
        primary: {
          50: '#EEF2FF',
          100: '#E0E7FF',
          200: '#C7D2FE',
          300: '#A5B4FC',
          400: '#818CF8',
          500: '#6366F1',
          600: '#4F46E5',
          700: '#4338CA',
          800: '#3730A3',
          900: '#312E81',
        },
        secondary: {
          50: '#F5F3F0',
          100: '#E7E3DE',
          200: '#CAC1B6',
          300: '#ADA193',
          400: '#8E7E6D',
          500: '#4C443C',
          600: '#3E3730',
          700: '#312B25',
          800: '#27221D',
          900: '#1F1A17',
        },
        black: {
          50: '#F9FAFB',
          100: '#F3F4F6',
          200: '#E5E7EB',
          300: '#D1D5DB',
          400: '#9CA3AF',
          500: '#6B7280',
          600: '#4B5563',
          700: '#374151',
          800: '#1F2937',
          900: '#111827',
        },
        white: {
          50: '#FFFFFF',
          100: '#F5F5F5',
          200: '#E5E5E5',
          300: '#D4D4D4',
          400: '#A3A3A3',
          500: '#737373',
          600: '#525252',
          700: '#404040',
          800: '#262626',
          900: '#171717',
        },
      },
    },
    fontFamily: {
      heading: ['Playfair Display', 'serif'],
      body: ['Raleway', 'sans-serif'],
      detail: ['Lora', 'serif'],
    },
    fontSize: {
      // Definiere hier deine globalen Schriftgrößen
      'h1': '2.5rem',
      'h2': '2rem',
      'h3': '1.75rem',
      'big': '1.5rem',
      'bigger': '1.25rem',
      'p': '1rem',
      'smaller':'0.9rem',
      'small': '0.75rem',
    },
    fontWeight: {
      'h1': '900',
      'h2': '800',
      'h3': '700',
      'p': '400',
      'accent': '600',
      'lighter': '300',
      'light': '200'
    },
    container: {
      padding: "2rem",
      center: true,
    },
    screens: {
      sm: "640px",
      md: "768px",
    },
  },
   variants: {
     extend: {},
   },
   plugins: [
    require('@tailwindcss/line-clamp'),
   ],
 }


<template>
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 47" fill="none" :width="widthAttr" :height="heightAttr"
    overflow="visible">
    <path :stroke="strokeColor" :fill="color"
      d="M14.4661 3C8.13355 3 3 8.08221 3 14.3514C3 19.4126 5.00579 31.4238 24.7569 43.5677C25.4722 44.0076 26.3922 44.0076 27.1075 43.5677C46.8585 31.4238 48.8644 19.4126 48.8644 14.3514C48.8644 8.08221 43.7308 3 37.3983 3C31.0658 3 25.9322 9.87966 25.9322 9.87966C25.9322 9.87966 20.7986 3 14.4661 3Z"
      stroke-width="4.58644" stroke-linecap="round" stroke-linejoin="round" class="stroke-current-color" />
  </svg>
</template>

<script>
export default {
  name: 'Vector',

  props: {
    width: {
      type: String,
      required: false
    },

    heightAttr: {
      type: String,
      required: false
    },

    size: {
      type: String,
      required: false
    },
    color: {
    },
    strokeColor: {

    }
  },

  computed: {
    widthAttr() {
      if (this.height) return undefined;
      if (this.size) return this.size;
      return this.width || "52px";
    },

    heightAttr() {
      if (this.width) return undefined;
      if (this.size) return this.size;
      return this.height || "47px";
    }
  }
}
</script>

<template>
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52" overflow="visible">
    <path :stroke="color" fill="none"
      d="M25.9322 48.8644C38.5972 48.8644 48.8644 38.5972 48.8644 25.9322C48.8644 13.2671 38.5972 3 25.9322 3C13.2671 3 3 13.2671 3 25.9322C3 32.039 5.3871 37.5884 9.27911 41.6981L4.66673 46.9622C4.01729 47.7033 4.54366 48.8644 5.52915 48.8644H25.9322Z"
      stroke-width="4.58644" stroke-linecap="round" stroke-linejoin="round" class="stroke-current-color" />
  </svg>
</template>

<script>
export default {
  name: 'IconCommentCircleAltChatMessage',

  props: {
    width: {
      type: String,
      required: false
    },

    height: {
      type: String,
      required: false
    },

    size: {
      type: String,
      required: false
    },
    color: {
      type: String
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
      return this.height || "52px";
    }
  }
}
</script>

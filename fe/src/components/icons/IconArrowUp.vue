<template>
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 54 56" fill="none" :width="widthAttr" :height="heightAttr"
    overflow="visible">
    <path d="M26.7911 52.559V3.38428M26.7911 3.38428L50.395 26.9882M26.7911 3.38428L3.18726 26.9882"
      stroke-width="5.90097" stroke-linecap="round" stroke-linejoin="round" :stroke="strokeColor" />
  </svg>
</template>

<script>
export default {
  name: 'IconArrowUp',

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
    strokeColor: {
      type: String
    }
  },

  computed: {
    widthAttr() {
      if (this.height) return undefined;
      if (this.size) return this.size;
      return this.width || "54px";
    },

    heightAttr() {
      if (this.width) return undefined;
      if (this.size) return this.size;
      return this.height || "56px";
    }
  }
}
</script>

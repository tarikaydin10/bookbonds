<template>
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 23 18" :fill="color" :width="widthAttr" :height="heightAttr"
    overflow="visible">
    <path
      d="M7.62377 16.856L0.364335 9.59655C-0.0717985 9.16041 -0.0717985 8.45327 0.364335 8.0171L1.94374 6.43765C2.37987 6.00147 3.08706 6.00147 3.52319 6.43765L8.41349 11.3279L18.888 0.853467C19.3241 0.417334 20.0313 0.417334 20.4674 0.853467L22.0468 2.43292C22.483 2.86905 22.483 3.57619 22.0468 4.01237L9.20322 16.856C8.76704 17.2922 8.0599 17.2922 7.62377 16.856Z"
      class="fill-current-color" />
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

    height: {
      type: String,
      required: false
    },

    size: {
      type: String,
      required: false
    },
    color: {
      type: String,
      required: false,
      default: "#BCBCD0"
    }
  },

  computed: {
    widthAttr() {
      if (this.height) return undefined;
      if (this.size) return this.size;
      return this.width || "23px";
    },

    heightAttr() {
      if (this.width) return undefined;
      if (this.size) return this.size;
      return this.height || "18px";
    }
  }
}
</script>

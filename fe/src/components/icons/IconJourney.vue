<template>
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 12 14" 
  
  :width="widthAttr" :height="heightAttr" overflow="visible">
    <path :fill="color" d="M1.71429 0.142822C1.59429 0.142822 1.49143 0.159965 1.38857 0.194251C0.72 0.331394 0.188571 0.862822 0.0514286 1.53139C0 1.63425 0 1.73711 0 1.85711V11.2857C0 12.7085 1.14857 13.8571 2.57143 13.8571H12V12.1428H2.57143C2.09143 12.1428 1.71429 11.7657 1.71429 11.2857C1.71429 10.8057 2.09143 10.4285 2.57143 10.4285H12V0.999965C12 0.519965 11.6229 0.142822 11.1429 0.142822H10.2857V5.28568L8.57143 3.57139L6.85714 5.28568V0.142822H1.71429Z" fill="#BCBCD0" /></svg>
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
        return this.width || "12px";
      },

      heightAttr() {
        if (this.width) return undefined;
        if (this.size) return this.size;
        return this.height || "14px";
      }
    }
  }
</script>

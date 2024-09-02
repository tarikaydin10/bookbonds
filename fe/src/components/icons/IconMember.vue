<template>
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" 
  :width="widthAttr" :height="heightAttr" overflow="visible">
    <path d="M5 8C6.45078 8 7.625 6.82578 7.625 5.375C7.625 3.92422 6.45078 2.75 5 2.75C3.54922 2.75 2.375 3.92422 2.375 5.375C2.375 6.82578 3.54922 8 5 8ZM6.8 8.75H6.60547C6.11797 8.98438 5.57656 9.125 5 9.125C4.42344 9.125 3.88437 8.98438 3.39453 8.75H3.2C1.70937 8.75 0.5 9.95938 0.5 11.45V12.125C0.5 12.7461 1.00391 13.25 1.625 13.25H8.375C8.99609 13.25 9.5 12.7461 9.5 12.125V11.45C9.5 9.95938 8.29062 8.75 6.8 8.75ZM11.75 8C12.9922 8 14 6.99219 14 5.75C14 4.50781 12.9922 3.5 11.75 3.5C10.5078 3.5 9.5 4.50781 9.5 5.75C9.5 6.99219 10.5078 8 11.75 8ZM12.875 8.75H12.7859C12.4602 8.8625 12.1156 8.9375 11.75 8.9375C11.3844 8.9375 11.0398 8.8625 10.7141 8.75H10.625C10.1469 8.75 9.70625 8.88828 9.31953 9.11094C9.89141 9.72734 10.25 10.5453 10.25 11.45V12.35C10.25 12.4016 10.2383 12.4508 10.2359 12.5H14.375C14.9961 12.5 15.5 11.9961 15.5 11.375C15.5 9.92422 14.3258 8.75 12.875 8.75Z"
     :fill="color" /></svg>
</template>

<script>
  export default {
    name: 'Frame38',

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
        return this.width || "16px";
      },

      heightAttr() {
        if (this.width) return undefined;
        if (this.size) return this.size;
        return this.height || "16px";
      }
    }
  }
</script>

<template>
    <button
      @click="onClick"
      class="font-h3 py-2 px-3 border border-solid border-primary-500 rounded-3xl shadow text-primary-500 hover:bg-primary-500 hover:text-white-200 hover:ease-in-out duration-300"
    >
      <slot />
    </button>
  </template>
  
  <script setup>
  

  
  const emit = defineEmits(['click']);
  
  const onClick = (event) => {
    emit('click', event);
  };
  </script>
  
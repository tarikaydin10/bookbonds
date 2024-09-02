<template>
    <button
      @click="onClick"
      class="py-2 px-3 rounded-lg rounded-3xl shadow-md bg-primary-500 hover:bg-indigo-700 text-white-200 text-p font-accent font-body hover:ease-in-out duration-300"
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
  
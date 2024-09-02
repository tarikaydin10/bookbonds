<template>
  <p class="text-white-300 font-body  text-bigger mb-2">Teilnehmer</p>
  <ul>
    <li v-for="member in members" :key="member.memberId"
      class=" font-body py-1 px-3 inline-flex items-center  hover:bg-white-600 transition rounded-full">

      <p class="bg-white-500 rounded-full px-2 border-2 border-solid mr-1.5"
        :class="{ 'border-blue-800 shadow': member.role === 'HOST', 'border-white-500': member.role === 'MEMBER' }">U
      </p>
      <p class="text-white-200 font-body font-lighter drop-shadow-xl text-white-300">{{ member.user.username
      }}</p>
      <p v-if="member.role === 'HOST'" class="ml-1 text-small font-body font-light text-primary-300">Host
      </p>
    </li>
  </ul>
</template>

<script setup>
import { defineComponent } from 'vue';


const props = defineProps({
  members: Array,

});




</script>

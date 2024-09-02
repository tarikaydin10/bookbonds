<template>
  <ul class="divide-y divide-gray-200">
    <li v-for="member in members" :key="member.memberId"
      class="p-4 flex bg-gray-500 justify-between items-center bg-gray-100 hover:bg-gray-50 transition rounded-xl">
      <p class="text-lg font-semibold text-gray-800">{{ member.user.username }}</p>
      <div class="text-sm font-medium text-gray-600">{{ member.role }}</div>
    </li>
  </ul>
</template>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
  props: {
    members: {
      type: Array,
      required: true,
    },
  },
});
</script>

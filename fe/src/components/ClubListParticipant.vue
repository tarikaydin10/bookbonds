<script setup>
import { onMounted } from 'vue'
import { useClubs } from '@/composables/useClubs'

const { clubs, loading, error, loadClubs } = useClubs()

onMounted(() => {
  loadClubs()
})
</script>

<template>
    <div>
      <h2>Meine Clubs</h2>
      <p v-if="loading">Lade Clubs...</p>
      <ul v-else>
        <li v-for="club in clubs" :key="club.club_id">
          <h3>{{ club.title }}</h3>
          <p>Host: {{ club.host.username }}</p>
          <p>Buch: {{ club.book.title }} von {{ club.book.authors.join(', ') }}</p>
          <p>Teilnehmer: {{ club.participants.length }}</p>
        </li>
      </ul>
      <p v-if="error">{{ error }}</p>
    </div>
  </template>
  
 
  
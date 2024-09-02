<script setup>
import { onMounted } from 'vue'
import { useClubs } from '@/composables/useClubs'

const { clubs, loading, error, loadClubs } = useClubs()

onMounted(loadClubs)
</script>

<template>
    <div>
      <h2>Hosted</h2>
     </div>
  </template>
  
 
  
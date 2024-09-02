import { ref } from 'vue'
import axios from 'axios'

export function useClubs() {
  const clubs = ref([])
  const loading = ref(false)
  const error = ref('')

  const apiUrl = import.meta.env.VITE_API_BASE_URL;



  const getClubById = async (clubId) => {
    try {
      const token = localStorage.getItem('token')
      const response = await axios.get(apiUrl + '/clubs/' + clubId, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })

      console.log(response.data)
      return response.data
    } catch (error) {
      throw new Error('Fehler beim Laden des Clubs.')
    }
  }



  const loadClubs = async () => {
    loading.value = true
    error.value = ''
    try {
      const token = localStorage.getItem('token')
      const response = await axios.get(apiUrl + '/clubs', {
        headers: {
          'Content-Type': 'application/json',

          Authorization: `Bearer ${token}`
        }
      })
      clubs.value = response.data
      console.log(response.data)
    } catch (err) {
      console.error('Fehler beim Laden der Clubs:', err)
      error.value = 'Es gab ein Problem beim Laden der Clubs.'
    } finally {
      loading.value = false
    }
  }

  const createNewClub = async (formData) => {
    try {
      const token = localStorage.getItem('token')
      const response = await axios.post(apiUrl + '/clubs', formData, {
        headers: {
          'Authorization': `Bearer ${token}`
                },
      });
    clubs.value.push(response.data);
    console.log(response.data)
    return response.data;
    } catch (error) {
      console.error('Error creating club:', error);
    }
  };



  return {
    clubs,
    loading,
    error,
    loadClubs,
    getClubById,
    createNewClub,
  }
}

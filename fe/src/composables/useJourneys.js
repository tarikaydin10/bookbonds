import { ref } from 'vue';
import axios from 'axios';

export function useJourneys() {
  const journeys = ref([]);
  const journey = ref(null);

  const apiUrl = import.meta.env.VITE_API_BASE_URL;


 

  const getJourneyById = async (journeyId) => {
    try {
        const token = localStorage.getItem('token')

      const response = await axios.get(apiUrl +`/journeys/${journeyId}`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      journey.value = response.data;
      return journey.value;
    } catch (error) {
      console.error('Fehler beim Laden der Journey:', error);
      return null;
    }
  };

  const createJourney = async (journeyData) => {
    try {
      const response = await axios.post(apiUrl+'/journeys', journeyData, {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
        },
      });
      return response.data;
    } catch (error) {
      console.error("Error creating journey:", error);
      throw error;
    }
  };


 

  const addCommentToChapter = async (clubId, chapterId, content, parentCommentId) => {
    console.log(parentCommentId)

    try {
      const token = localStorage.getItem('token')
      const response = await axios.post(
        apiUrl + `/comments/club/${clubId}/chapter/${chapterId}`,
        { content, parentCommentId } , 
        { headers: {
          'Content-Type': 'application/json', // Setze den Content-Type Header auf 'application/json'
        Authorization: `Bearer ${token}`
      }
     });
      return response.data;
    } catch (error) {
      console.error('Error adding comment:', error);
      return null;
    }
  };

  const deleteComment = async (commentId) => {
    console.log(commentId)
    try {
      
      const token = localStorage.getItem('token')

      // Sende die DELETE-Anfrage an die API
      const response = await axios.delete(apiUrl +`/comments/${commentId}`, {
          headers: {
              Authorization: `Bearer ${token}`
          }
      });
  
      // Wenn die Anfrage erfolgreich war, entferne den Kommentar aus dem lokalen State
      
  } catch (error) {
      console.error('Fehler beim Löschen des Kommentars:', error);
  }
  
  }
  const reactToChapter = async (chapterId, reactionType) => {
    try {
      const token = localStorage.getItem('token');
      const response = await axios.post(
        apiUrl + `/chapters/${chapterId}/react`,
        { type: reactionType },
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );
      return response.data;
    } catch (error) {
      console.error('Error reacting to chapter:', error);
      return null;
    }
  };

  return {
    journeys,
    journey,
    getJourneyById,
    createJourney,
    addCommentToChapter,
    reactToChapter,
    deleteComment
  };
}

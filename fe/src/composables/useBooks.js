import axios from 'axios';

export function useBooks() {

  const apiUrl = import.meta.env.VITE_API_BASE_URL;


  const searchBooks = async (query) => {
    try {

      const token = localStorage.getItem('token')

      const response = await axios.get(apiUrl + '/books/search', {
        headers: {
          Authorization: `Bearer ${token}`
        },
        params: { query },
      });
      return response.data;
    } catch (error) {
      console.error('Fehler bei der Buchsuche:', error);
      return [];
    }
  };

  return {
    searchBooks,
  };
}

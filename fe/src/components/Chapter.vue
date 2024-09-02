<template>
    <div>
      <h4>{{ chapter.title }}</h4>
      <p>Gelesen: <input type="checkbox" v-model="isRead" @change="toggleRead" /></p>
      <p>Favorisiert: <input type="checkbox" v-model="isFavorited" @change="toggleFavorited" /></p>
  
      <div>
        <h5>Kommentare</h5>
        <ul>
          <li v-for="comment in comments" :key="comment.commentId">{{ comment.content }} - {{ comment.user.username }}</li>
        </ul>
        <textarea v-model="newComment" placeholder="Kommentar hinzufügen..."></textarea>
        <button @click="addComment">Kommentieren</button>
      </div>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from 'vue';
  import { useChapters } from '@/composables/useChapters';
  
  export default {
    props: ['chapter'],
    setup(props) {
      const { getCommentsByChapter, addComment, addReaction } = useChapters();
      const comments = ref([]);
      const isRead = ref(false);
      const isFavorited = ref(false);
      const newComment = ref('');
  
      onMounted(async () => {
        comments.value = await getCommentsByChapter(props.chapter.chapterId);
      });
  
      const toggleRead = () => {
        addReaction(props.chapter.chapterId, isRead.value, isFavorited.value);
      };
  
      const toggleFavorited = () => {
        addReaction(props.chapter.chapterId, isRead.value, isFavorited.value);
      };
  
      const addComment = async () => {
        if (newComment.value.trim()) {
          const comment = await addComment(props.chapter.chapterId, newComment.value);
          comments.value.push(comment);
          newComment.value = '';
        }
      };
  
      return {
        comments,
        isRead,
        isFavorited,
        newComment,
        addComment,
        toggleRead,
        toggleFavorited,
      };
    },
  };
  </script>
  
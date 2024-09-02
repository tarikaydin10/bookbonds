<template>
    <div class="text-sm text-gray-600">
        <div class="mb-2">
            <p>
                Gelesen: {{ isRead(chapter) ? 'Ja' : 'Nein' }} | Favorisiert: {{ isFavorite(chapter) ? 'Ja' :
                    'Nein'
                }}
            </p>
            <button @click="toggleRead(chapter)" class="py-1 px-3 mr-2 bg-green-500 text-white rounded hover:bg-green-700">
                Als gelesen markieren
            </button>
            <button @click="toggleFavorite(chapter)" class="py-1 px-3 bg-yellow-500 text-white rounded hover:bg-yellow-700">
                Favorisieren
            </button>
        </div>

        <div class="mb-4">
            <h5 class="font-semibold text-gray-700">Gelesen von:</h5>
            <ul class="text-gray-500">
                <li v-for="reaction in getReactions(chapter, 'read')" :key="reaction.reactionId">
                    {{ reaction.user.username }}
                </li>
            </ul>
            <h5 class="font-semibold text-gray-700 mt-2">Favorisiert von:</h5>
            <ul class="text-gray-500">
                <li v-for="reaction in getReactions(chapter, 'favorite')" :key="reaction.reactionId">
                    {{ reaction.user.username }}
                </li>
            </ul>
        </div>

        <div>
            <h5 class="font-semibold text-gray-700">Kommentare</h5>
            <ul>
                <li v-for="comment in getSortedComments(chapter.comments)" :key="comment.commentId"
                    class="mb-2 p-2 bg-white rounded shadow">
                    <p class="text-gray-800"> <span>{{ comment.user.username }}:</span> {{ comment.content }}</p>
                    <p class="text-gray-600">Reaktionen:</p>
                    <ul class="text-gray-500 ml-4">
                        <li v-for="reaction in comment.reactions" :key="reaction.reactionId">
                            {{ reaction.user.username }}: {{ reaction.type }}
                        </li>
                    </ul>
                </li>
            </ul>
            <div class="mt-4">
                <textarea v-model="newComments[chapter.chapterId]" placeholder="Schreibe einen Kommentar..."
                    class="w-full p-2 border rounded resize-none"></textarea>
                <button @click="addComment(chapter.chapterId)"
                    class="mt-2 py-2 px-4 bg-blue-500 text-white rounded hover:bg-blue-700">
                    Kommentar hinzufügen
                </button>
            </div>
        </div>
    </div>
</template>
  
<script setup>

const props = defineProps({
    chapter: Object,
    newComments: Object,
});

const emit = defineEmits(['toggleRead', 'toggleFavorite', 'isFavorite', 'isRead', 'getReactions', 'addComment', 'close']);



const getSortedComments = (comments) => {
    return comments.slice().sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
};

const isRead = (chapter) => {
    return emit('isRead', chapter)
};

const isFavorite = (chapter) => {
    return emit('isFavorite', chapter)
};

const toggleRead = (chapter) => {
    console.log("Im Read!")
    return emit('toggleRead', chapter)
};
const toggleFavorite = (chapter) => {
    return emit('toggleFavorite', chapter)
};
const getReactions = (chapter, type) => {
    return emit('getReactions', chapter, type)
};
const addComment = (chapterId) => {
    return emit('addComment', chapterId)
};
</script>
  
<style scoped></style>
  
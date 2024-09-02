
export function useChapters() {

  const getLikesCount = (chapter) => {
    if (!chapter.reactions || !Array.isArray(chapter.reactions)) {
      return 0; // Falls keine Reaktionen vorhanden sind, gib 0 zurück
    }
    // Zähle die Reaktionen, die als favorisiert markiert sind
    const likes = chapter.reactions.filter(reaction => reaction.favorited).length;
    return likes;
  };


  const getCommentCount = (chapter) => {  
    return chapter.comments ? chapter.comments.length : 0;
  };

  const getLikingUsers = (chapter) => {
    if (!chapter.reactions || !Array.isArray(chapter.reactions)) {
      return [];
    }
  
    return chapter.reactions
      .filter(reaction => reaction.favorited)
      .map(reaction => reaction.user);
  };
  

  return {
getLikesCount,
getCommentCount,
getLikingUsers
  }
}

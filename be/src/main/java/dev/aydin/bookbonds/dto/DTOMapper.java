package dev.aydin.bookbonds.dto;

import dev.aydin.bookbonds.entities.*;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public class DTOMapper {

    public ClubDTO convertToClubDTO(Club club) {
        ClubDTO clubDTO = new ClubDTO();
        clubDTO.setClubId(club.getClubId());
        clubDTO.setTitle(club.getTitle());
        clubDTO.setDescription(club.getDescription());
        clubDTO.setTheme(club.getTheme());
        clubDTO.setCreatedAt(club.getCreatedAt());
        clubDTO.setUpdatedAt(club.getUpdatedAt());

        clubDTO.setMembers(club.getMembers().stream()
                .map(this::convertToMemberForClubDTO)
                .collect(Collectors.toSet()));

        clubDTO.setJourneys(club.getJourneys().stream()
                .map(this::convertToJourneyFlatDTO)
                .collect(Collectors.toSet()));


        return clubDTO;
    }

    public JourneyFlatDTO convertToJourneyFlatDTO(Journey journey) {
        JourneyFlatDTO journeyDTO = new JourneyFlatDTO();
        journeyDTO.setJourneyId(journey.getJourneyId());
        journeyDTO.setTitle(journey.getTitle());
        journeyDTO.setDescription(journey.getDescription());
        journeyDTO.setBook(journey.getBook());

        journeyDTO.setChapters(journey.getChapters().stream()
                .map(this::convertToChapterForJourneyDTO)
                .collect(Collectors.toSet()));

        journeyDTO.setNumberOfChapters(journey.getNumberOfChapters());
        journeyDTO.setCreatedAt(journey.getCreatedAt());

        journeyDTO.setProgress(journey.getProgress());
        return journeyDTO;
    }

    public ChapterForJourneyDTO convertToChapterForJourneyDTO(Chapter chapter) {
        ChapterForJourneyDTO chapterDTO = new ChapterForJourneyDTO();
        chapterDTO.setChapterId(chapter.getChapterId());
        chapterDTO.setTitle(chapter.getTitle());
        chapterDTO.setChapterNumber(chapter.getChapterNumber());
        chapterDTO.setComments(chapter.getComments().stream()
                .sorted(Comparator.comparing(Comment::getCreatedAt))
                .map(this::convertToCommentForChapterDTO)
                .collect(Collectors.toSet()));

        chapterDTO.setReactions(chapter.getReactions().stream()
                .map(this::convertToUserReactionDTO)
                .collect(Collectors.toSet()));

        chapterDTO.setChapterProgress(chapter.getChapterProgress());
        chapterDTO.setClubId(chapter.getClubId());
        return chapterDTO;
    }

    public UserReactionDTO convertToUserReactionDTO(UserReaction userReaction) {
        UserReactionDTO userReactionDto = new UserReactionDTO();
        userReactionDto.setReactionId(userReaction.getReactionId());

        User user = userReaction.getUser();
        userReactionDto.setUser(new UserForMemberDTO(user.getUser_id(), user.getUsername()));

        userReactionDto.setRead(userReaction.isRead());
        userReactionDto.setFavorited(userReaction.isFavorited());
        return userReactionDto;
    }

    public CommentForChapterDTO convertToCommentForChapterDTO(Comment comment) {
        CommentForChapterDTO commentDto = new CommentForChapterDTO();
        commentDto.setCommentId(comment.getCommentId());

        User user = comment.getUser();
        commentDto.setUser(new UserForMemberDTO(user.getUser_id(), user.getUsername()));

if(comment.getChapter() != null) commentDto.setChapterId(comment.getChapter().getChapterId());
        commentDto.setContent(comment.getContent());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setReplies(comment.getReplies().stream()
                .map(this::convertToCommentForChapterDTO)
                .collect(Collectors.toSet()));
        return commentDto;
    }

    public MemberForClubDTO convertToMemberForClubDTO(ClubMember member) {
        MemberForClubDTO memberDTO = new MemberForClubDTO();
        memberDTO.setMemberId(member.getMemberId());
        memberDTO.setUser(new UserForMemberDTO(member.getUser().getUser_id(), member.getUser().getUsername()));
        memberDTO.setRole(member.getRole());
        return memberDTO;
    }

    public UserFlatDTO convertToUserFlatDTO(User user) {
        UserFlatDTO userDTO = new UserFlatDTO();
        userDTO.setUser_id(user.getUser_id());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setMemberships(user.getMemberships().stream()
                .map(this::convertToMEmberForUserDTO)
                .collect(Collectors.toSet()));

        return userDTO;
    }

    private MemberForUserDTO convertToMEmberForUserDTO(ClubMember member) {
        MemberForUserDTO memberDTO = new MemberForUserDTO();
        memberDTO.setMemberId(member.getMemberId());
        memberDTO.setClub(new ClubForMemberDTO(member.getClub().getClubId(), member.getClub().getTitle(), member.getClub().getDescription(), member.getClub().getCreatedAt(), member.getClub().getUpdatedAt()));
        member.setRole(member.getRole());
        return memberDTO;
    }
}

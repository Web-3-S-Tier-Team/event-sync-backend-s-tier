package hei.school.even_sync_backend.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


public class Question {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String contenu;
    @Getter
    @Setter
    private String nom;
    private List<User> vote;
    @Setter
    @Getter
    private String sessionId;
    @Setter
    @Getter
    private LocalDateTime createdAt;
    public Question(String id, String contenu, String nom, List<User> vote, String sessionId, LocalDateTime createdAt) {
        this.id = id;
        this.contenu = contenu;
        this.nom = nom;
        this.vote = vote;
        this.sessionId = sessionId;
        this.createdAt = createdAt;
    }
    
    public Question() {
    }

    // Dans Question.java
    @Getter
    private int upvotes; // Remplacez List<User> par int

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }
    public void setUpvotes(List<User> vote) {
        this.vote = vote;
    }


}
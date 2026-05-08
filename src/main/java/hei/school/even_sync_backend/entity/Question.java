package hei.school.even_sync_backend.entity;

import java.time.LocalDateTime;
import java.util.List;


public class Question {
    private String id;
    private String contenu;
    private String nom;
    private List<User> vote;
    private String sessionId;
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

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public List<User> getUpvotes() {
        return vote;
    }
    public void setUpvotes(List<User> vote) {
        this.vote = vote;
    }
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
}
package hei.school.even_sync_backend.entity;

import java.time.LocalDateTime;

public class Question {
    private String id;
    private String contenu;
    private String nom;
    private int upvotes;
    private String sessionId;
    private LocalDateTime createdAt;
    public Question(String id, String contenu, String nom, int upvotes, String sessionId, LocalDateTime createdAt) {
        this.id = id;
        this.contenu = contenu;
        this.nom = nom;
        this.upvotes = upvotes;
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
    public int getUpvotes() {
        return upvotes;
    }
    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
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
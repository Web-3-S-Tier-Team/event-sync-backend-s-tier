package hei.school.even_sync_backend.dto;

public class QuestionDTO {
    private String nom;
    private String contenu;
    private int upvotes;
    public QuestionDTO(String nom, String contenu, int upvotes) {
        this.nom = nom;
        this.contenu = contenu;
        this.upvotes = upvotes;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    public int getUpvotes() {
        return upvotes;
    }
    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }
    public QuestionDTO() {
    }
    
}

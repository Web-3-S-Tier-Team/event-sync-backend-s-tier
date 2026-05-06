package hei.school.even_sync_backend.model;

public class Speaker {
    private Long id;
    private String fullName;
    private String photoUrl;
    private String biography;

    public Speaker() {}

    public Speaker(Long id, String fullName, String photoUrl, String biography) {
        this.id = id;
        this.fullName = fullName;
        this.photoUrl = photoUrl;
        this.biography = biography;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }
}

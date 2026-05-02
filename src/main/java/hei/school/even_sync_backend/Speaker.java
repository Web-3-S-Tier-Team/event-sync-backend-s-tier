public class Speaker {

    private Long id;
    private String fullName;
    private String photoUrl;
    private String bio;
    private String externalLinks;

    public Speaker() {}

    public Speaker(Long id, String fullName, String photoUrl, String bio, String externalLinks) {
        this.id = id;
        this.fullName = fullName;
        this.photoUrl = photoUrl;
        this.bio = bio;
        this.externalLinks = externalLinks;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getBio() {
        return bio;
    }

    public String getExternalLinks() {
        return externalLinks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setExternalLinks(String externalLinks) {
        this.externalLinks = externalLinks;
    }
}

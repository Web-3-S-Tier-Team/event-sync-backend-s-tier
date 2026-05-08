package hei.school.even_sync_backend.model;

import java.time.LocalDateTime;

public class Question {
    private Long id;
    private String content;
    private String authorName;
    private int upvoteCount;
    private LocalDateTime createdAt;

    public Question() {}

    public Question(Long id, String content, String authorName) {
        this.id = id;
        this.content = content;
        this.authorName = authorName;
        this.upvoteCount = 0;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public int getUpvoteCount() { return upvoteCount; }
    public void setUpvoteCount(int upvoteCount) { this.upvoteCount = upvoteCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
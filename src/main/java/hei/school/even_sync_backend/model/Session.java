import java.time.LocalDateTime;
import java.util.List;

public class Session {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Long roomId;
    private List<Long> speakerIds;

    public Session() {}

    public Session(Long id, String title, String description,
                   LocalDateTime startTime, LocalDateTime endTime,
                   Long roomId, List<Long> speakerIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.speakerIds = speakerIds;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Long getRoomId() {
        return roomId;
    }

    public List<Long> getSpeakerIds() {
        return speakerIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public void setSpeakerIds(List<Long> speakerIds) {
        this.speakerIds = speakerIds;
    }
}

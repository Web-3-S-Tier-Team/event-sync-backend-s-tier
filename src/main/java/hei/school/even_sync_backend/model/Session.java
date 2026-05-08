package hei.school.even_sync_backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Long roomId;
    private List<Long> speakerIds;
    private int capacity;
    private boolean isLive;
    private Event event;
    private Room room;
    private List<Speaker> speakers = new ArrayList<>();
    private List<Question> questions = new ArrayList<>();

    public Session() {}

    public Session(Long id, String title, String description,
                   LocalDateTime startTime, LocalDateTime endTime,
                   Long roomId, List<Long> speakerIds
                   LocalDateTime startTime, LocalDateTime endTime, int capacity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.speakerIds = speakerIds;
    }

    public Session(Long id, String title, String description,
                   LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public Session(Long roomId, List<Long> speakerIds
                   LocalDateTime startTime, LocalDateTime endTime, int capacity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.speakerIds = speakerIds;
        this.capacity = capacity;
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
        this.capacity = capacity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public boolean isLive() { return isLive; }
    public void setLive(boolean live) { isLive = live; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public List<Speaker> getSpeakers() { return speakers; }
    public void setSpeakers(List<Speaker> speakers) { this.speakers = speakers; }
    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }

    public String getLiveStatus() {
        return isLive ? "LIVE" : "SCHEDULED";
    }
}

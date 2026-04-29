package hei.school.even_sync_backend.service;

import hei.school.even_sync_backend.model.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LiveSessionDetector {

    public void updateLiveStatus(Session session) {
        if (session == null || session.getStartTime() == null || session.getEndTime() == null) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        boolean isLive = now.isAfter(session.getStartTime()) && now.isBefore(session.getEndTime());
        session.setLive(isLive);
    }

    public void updateLiveStatus(List<Session> sessions) {
        sessions.forEach(this::updateLiveStatus);
    }
}
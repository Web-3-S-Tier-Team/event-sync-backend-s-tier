package hei.school.even_sync_backend.service;

import hei.school.even_sync_backend.model.Session;
import hei.school.even_sync_backend.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final LiveSessionDetector liveSessionDetector;

    public SessionService(SessionRepository sessionRepository, LiveSessionDetector liveSessionDetector) {
        this.sessionRepository = sessionRepository;
        this.liveSessionDetector = liveSessionDetector;
    }

    public List<Session> getSessionsByEventId(Long eventId) {
        List<Session> sessions = sessionRepository.findByEventId(eventId);
        liveSessionDetector.updateLiveStatus(sessions);
        return sessions.stream()
                .sorted(Comparator.comparing(Session::getStartTime))
                .collect(Collectors.toList());
    }

    public List<Session> getLiveSessionsByEventId(Long eventId) {
        return getSessionsByEventId(eventId).stream()
                .filter(Session::isLive)
                .collect(Collectors.toList());
    }

    public Session getSessionDetail(Long eventId, Long sessionId) {

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new RuntimeException("Session not found: " + sessionId));

        if (!session.getEvent().getId().equals(eventId)) {
            throw new RuntimeException("Session does not belong to event: " + eventId);
        }

        liveSessionDetector.updateLiveStatus(session);

        return session;
    }
}
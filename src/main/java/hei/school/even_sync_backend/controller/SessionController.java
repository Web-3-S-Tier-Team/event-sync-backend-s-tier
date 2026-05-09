package hei.school.even_sync_backend.controller;

import hei.school.even_sync_backend.model.Session;
import hei.school.even_sync_backend.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    // GET /events/{eventId}/sessions
    @GetMapping("/events/{eventId}/sessions")
    public List<Session> listSessions(@PathVariable Long eventId) {
        return sessionService.getSessionsByEventId(eventId);
    }

    // GET /events/{eventId}/sessions/live
    @GetMapping("/events/{eventId}/sessions/live")
    public List<Session> listLiveSessions(@PathVariable Long eventId) {
        return sessionService.getLiveSessionsByEventId(eventId);
    }

    // GET /events/{eventId}/sessions/{sessionId}
    @GetMapping("/events/{eventId}/sessions/{sessionId}")
    public Session getSessionDetail(@PathVariable Long eventId, @PathVariable Long sessionId) {
        return sessionService.getSessionDetail(eventId, sessionId);
    }
}
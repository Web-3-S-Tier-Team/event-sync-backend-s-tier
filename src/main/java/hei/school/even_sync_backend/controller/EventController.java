// hei.school.even_sync_backend/controller/EventController.java
package hei.school.even_sync_backend.controller;

import hei.school.even_sync_backend.model.Event;
import hei.school.even_sync_backend.model.Session;
import hei.school.even_sync_backend.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // GET /events
    @GetMapping
    public List<Event> listEvents() {
        return eventService.getAllEvents();
    }

    // GET /events/{id}
    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventService.getEventById(id)
                .orElseThrow(() -> new RuntimeException("Event not found: " + id));
    }

    // GET /events/ongoing
    @GetMapping("/ongoing")
    public List<Event> listOngoingEvents() {
        return eventService.getOngoingEvents();
    }

    // GET /events/live-sessions
    @GetMapping("/live-sessions")
    public List<Session> listLiveSessions() {
        return eventService.getAllLiveSessions();
    }
}
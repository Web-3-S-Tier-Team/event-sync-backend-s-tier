package hei.school.even_sync_backend.service;

import hei.school.even_sync_backend.model.Event;
import hei.school.even_sync_backend.model.Session;
import hei.school.even_sync_backend.repository.EventRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final LiveSessionDetector liveSessionDetector;

    public EventService(EventRepository eventRepository, LiveSessionDetector liveSessionDetector) {
        this.eventRepository = eventRepository;
        this.liveSessionDetector = liveSessionDetector;
    }

    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        events.forEach(this::refreshSessionsLiveStatus);
        return events;
    }

    public Optional<Event> getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        event.ifPresent(this::refreshSessionsLiveStatus);
        return event;
    }

    public List<Event> getOngoingEvents() {
        return eventRepository.findOngoingEvents();
    }

    public List<Session> getAllLiveSessions() {
        return getAllEvents().stream()
                .flatMap(e -> e.getSessions().stream())
                .filter(Session::isLive)
                .collect(Collectors.toList());
    }

    private void refreshSessionsLiveStatus(Event event) {
        if (event.getSessions() != null) {
            liveSessionDetector.updateLiveStatus(event.getSessions());
        }
    }
}
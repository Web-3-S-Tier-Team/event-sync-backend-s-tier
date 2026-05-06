package hei.school.even_sync_backend.repository;

import hei.school.even_sync_backend.model.Event;
import java.util.List;
import java.util.Optional;

public interface EventRepository {
    List<Event> findAll();
    Optional<Event> findById(Long id);
    List<Event> findOngoingEvents();
    Event save(Event event);
    void deleteById(Long id);
}
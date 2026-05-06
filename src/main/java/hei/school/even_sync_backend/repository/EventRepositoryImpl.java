package hei.school.even_sync_backend.repository;

import hei.school.even_sync_backend.model.Event;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private final Map<Long, Event> store = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Event> findOngoingEvents() {
        LocalDateTime now = LocalDateTime.now();
        return store.values().stream()
                .filter(e -> e.getStartDate().isBefore(now) && e.getEndDate().isAfter(now))
                .collect(Collectors.toList());
    }

    @Override
    public Event save(Event event) {
        if (event.getId() == null) {
            event.setId(currentId++);
        }
        store.put(event.getId(), event);
        return event;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
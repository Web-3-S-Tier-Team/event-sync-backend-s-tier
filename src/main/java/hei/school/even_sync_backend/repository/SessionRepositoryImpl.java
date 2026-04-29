package hei.school.even_sync_backend.repository;

import hei.school.even_sync_backend.model.Session;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class SessionRepositoryImpl implements SessionRepository {

    private final Map<Long, Session> store = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public List<Session> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Session> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Session> findByEventId(Long eventId) {
        return store.values().stream()
                .filter(s -> s.getEvent() != null && s.getEvent().getId().equals(eventId))
                .collect(Collectors.toList());
    }

    @Override
    public Session save(Session session) {
        if (session.getId() == null) {
            session.setId(currentId++);
        }
        store.put(session.getId(), session);
        return session;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
package hei.school.even_sync_backend.repository;

import hei.school.even_sync_backend.model.Session;
import java.util.List;
import java.util.Optional;

public interface SessionRepository {
    List<Session> findAll();
    Optional<Session> findById(Long id);
    List<Session> findByEventId(Long eventId);
    Session save(Session session);
    void deleteById(Long id);
}
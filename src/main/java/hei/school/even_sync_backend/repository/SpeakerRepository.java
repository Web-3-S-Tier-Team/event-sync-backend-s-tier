package hei.school.even_sync_backend.repository;

import hei.school.even_sync_backend.model.Speaker;

import java.util.List;
import java.util.Optional;

public interface SpeakerRepository {
    List<Speaker> findAll();

    Optional<Speaker> findById(Long id);

    Speaker save(Speaker speaker);

    void deleteById(Long id);
}


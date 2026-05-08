package hei.school.even_sync_backend.repository;

import hei.school.even_sync_backend.model.Speaker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class SpeakerRepositoryImpl implements SpeakerRepository {

    private final Map<Long, Speaker> store = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public List<Speaker> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Speaker> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Speaker save(Speaker speaker) {
        if (speaker.getId() == null) {
            speaker.setId(currentId++);
        }
        store.put(speaker.getId(), speaker);
        return speaker;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}


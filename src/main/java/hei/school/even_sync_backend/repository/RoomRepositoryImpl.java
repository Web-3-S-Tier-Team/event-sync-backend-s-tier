package hei.school.even_sync_backend.repository;

import hei.school.even_sync_backend.model.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    private final Map<Long, Room> store = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Room> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Room save(Room room) {
        if (room.getId() == null) {
            room.setId(currentId++);
        }
        store.put(room.getId(), room);
        return room;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}


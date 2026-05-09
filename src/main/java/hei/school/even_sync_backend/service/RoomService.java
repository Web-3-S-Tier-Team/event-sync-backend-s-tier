package hei.school.even_sync_backend.service;

import hei.school.even_sync_backend.model.Room;
import hei.school.even_sync_backend.model.Session;
import hei.school.even_sync_backend.repository.RoomRepository;
import hei.school.even_sync_backend.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final SessionRepository sessionRepository;
    private final LiveSessionDetector liveSessionDetector;

    public RoomService(RoomRepository roomRepository, SessionRepository sessionRepository, LiveSessionDetector liveSessionDetector) {
        this.roomRepository = roomRepository;
        this.sessionRepository = sessionRepository;
        this.liveSessionDetector = liveSessionDetector;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Session> getSessionsByRoom(Long roomId) {
        List<Session> sessions = sessionRepository.findByRoomId(roomId);
        liveSessionDetector.updateLiveStatus(sessions);
        return sessions.stream()
                .sorted(Comparator.comparing(Session::getStartTime))
                .collect(Collectors.toList());
    }
}

package hei.school.even_sync_backend.service;

import hei.school.even_sync_backend.model.Session;
import hei.school.even_sync_backend.model.Speaker;
import hei.school.even_sync_backend.repository.SpeakerRepository;
import hei.school.even_sync_backend.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpeakerService {

    private final SpeakerRepository speakerRepository;
    private final SessionRepository sessionRepository;
    private final LiveSessionDetector liveSessionDetector;

    public SpeakerService(SpeakerRepository speakerRepository, SessionRepository sessionRepository, LiveSessionDetector liveSessionDetector) {
        this.speakerRepository = speakerRepository;
        this.sessionRepository = sessionRepository;
        this.liveSessionDetector = liveSessionDetector;
    }

    public List<Speaker> getAllSpeakers() {
        return speakerRepository.findAll();
    }

    public Optional<Speaker> getSpeakerById(Long id) {
        Optional<Speaker> speaker = speakerRepository.findById(id);
        speaker.ifPresent(this::enrichSpeakerWithSessions);
        return speaker;
    }

    public List<Session> getSpeakerSessions(Long speakerId) {
        List<Session> sessions = sessionRepository.findAll().stream()
                .filter(s -> s.getSpeakers() != null && 
                            s.getSpeakers().stream().anyMatch(sp -> sp.getId().equals(speakerId)))
                .collect(Collectors.toList());
        liveSessionDetector.updateLiveStatus(sessions);
        return sessions;
    }

    private void enrichSpeakerWithSessions(Speaker speaker) {
        List<Session> sessions = getSpeakerSessions(speaker.getId());
        speaker.setSessions(sessions);
    }
}

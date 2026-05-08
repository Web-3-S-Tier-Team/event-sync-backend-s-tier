package hei.school.even_sync_backend.service;

import hei.school.even_sync_backend.model.Speaker;
import hei.school.even_sync_backend.repository.SpeakerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpeakerService {

    private final SpeakerRepository speakerRepository;

    public SpeakerService(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    public List<Speaker> getAllSpeakers() {
        return speakerRepository.findAll();
    }

    public Optional<Speaker> getSpeakerById(Long id) {
        return speakerRepository.findById(id);
    }
}


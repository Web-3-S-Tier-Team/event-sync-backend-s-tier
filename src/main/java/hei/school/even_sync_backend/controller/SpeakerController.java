package hei.school.even_sync_backend.controller;

import hei.school.even_sync_backend.model.Speaker;
import hei.school.even_sync_backend.service.SpeakerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/speakers")
public class SpeakerController {

    private final SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping
    public List<Speaker> listSpeakers() {
        return speakerService.getAllSpeakers();
    }

    @GetMapping("/{id}")
    public Speaker getSpeaker(@PathVariable Long id) {
        return speakerService.getSpeakerById(id)
                .orElseThrow(() -> new RuntimeException("Speaker not found: " + id));
    }
}


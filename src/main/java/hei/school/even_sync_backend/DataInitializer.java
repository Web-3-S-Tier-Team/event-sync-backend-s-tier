
package hei.school.even_sync_backend;

import hei.school.even_sync_backend.model.*;
import hei.school.even_sync_backend.repository.EventRepository;
import hei.school.even_sync_backend.repository.SessionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EventRepository eventRepository;
    private final SessionRepository sessionRepository;

    public DataInitializer(EventRepository eventRepository, SessionRepository sessionRepository) {
        this.eventRepository = eventRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void run(String... args) {
        Event event = new Event(
                null,
                "Tech Summit 2024",
                "Conférence technology annuelle",
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(2),
                "Paris Convention Center"
        );

        Room room = new Room(1L, "Salle A");
        Speaker speaker = new Speaker(1L, "Marie Dupont", "/photo.jpg", "Experte Java");

        // Session LIVE
        Session liveSession = new Session(
                null,
                "Introduction à Java",
                "Les bases du langage Java",
                LocalDateTime.now().minusMinutes(30),
                LocalDateTime.now().plusMinutes(60),
                100
        );
        liveSession.setRoom(room);
        liveSession.getSpeakers().add(speaker);
        liveSession.setEvent(event);

        // Session à venir
        Session upcomingSession = new Session(
                null,
                "Spring Boot Avancé",
                "Framework Spring Boot",
                LocalDateTime.now().plusHours(2),
                LocalDateTime.now().plusHours(4),
                50
        );
        upcomingSession.setRoom(room);
        upcomingSession.getSpeakers().add(speaker);
        upcomingSession.setEvent(event);

        // Session terminée
        Session endedSession = new Session(
                null,
                "Keynote d'ouverture",
                "Mot de bienvenue",
                LocalDateTime.now().minusHours(3),
                LocalDateTime.now().minusHours(2),
                200
        );
        endedSession.setRoom(room);
        endedSession.getSpeakers().add(speaker);
        endedSession.setEvent(event);

        event.getSessions().add(liveSession);
        event.getSessions().add(upcomingSession);
        event.getSessions().add(endedSession);

        eventRepository.save(event);
        sessionRepository.save(liveSession);
        sessionRepository.save(upcomingSession);
        sessionRepository.save(endedSession);

        System.out.println("✅ Données de test prêtes !");
    }
}
package hei.school.even_sync_backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hei.school.even_sync_backend.dto.QuestionDTO;
import hei.school.even_sync_backend.entity.Question;
import hei.school.even_sync_backend.exception.BadRequestException;
import hei.school.even_sync_backend.exception.NotFoundException;
import hei.school.even_sync_backend.exception.UnauthorizedException;
import hei.school.even_sync_backend.repository.QuestionRepository;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final SessionService sessionService;
    private final LiveSessionDetector liveSessionDetector;

    public QuestionService(QuestionRepository questionRepository, SessionService sessionService, LiveSessionDetector liveSessionDetector) {
        this.questionRepository = questionRepository;
        this.sessionService = sessionService;
        this.liveSessionDetector = liveSessionDetector;
    }

    public List<QuestionDTO> findQuestion(String idSession) {
        try {
            if (idSession == null || idSession.isEmpty()) {
                throw new BadRequestException("The session id must be defined");
            }

            List<Question> listOfQuestions = questionRepository.getBySession(idSession);

            if (listOfQuestions.isEmpty()) {
                throw new NotFoundException("No question in session: " + idSession + " has been found");
            }
            return mapToDTO(listOfQuestions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<QuestionDTO> createQuestion(String idSession, String questionContent, String userName) {
        try {
            if (idSession == null || idSession.isEmpty()) {
                throw new BadRequestException("The session id must be defined");
            }
            if (questionContent == null || questionContent.isEmpty()) {
                throw new BadRequestException("The question content cannot be null or empty");
            }
            if (userName == null || userName.isEmpty()) {
                userName = "Anonymous";
            }

            questionRepository.createQuestion(idSession, questionContent, userName);

            List<Question> listOfQuestions = questionRepository.getBySession(idSession);
            if (listOfQuestions.isEmpty()) {
                throw new NotFoundException("No question in session: " + idSession + " has been found");
            }
            return mapToDTO(listOfQuestions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void upvoteQuestion(String questionId) {
        try {
            if (questionId == null || questionId.isEmpty()) {
                throw new BadRequestException("The question id must be defined");
            }
            questionRepository.upvote(questionId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteQuestion(String questionId) {
        try {
            if (questionId == null || questionId.isEmpty()) {
                throw new BadRequestException("The question id must be defined");
            }
            questionRepository.deleteQuestion(questionId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<QuestionDTO> mapToDTO(List<Question> listOfQuestions) {
        return (List<QuestionDTO>) listOfQuestions.stream()
                .map(q -> new QuestionDTO(
                        q.getNom(),
                        q.getContenu(),
                        q.getUpvotes() // Supprimez le .size() et le commentaire ici
                ));
    }
}

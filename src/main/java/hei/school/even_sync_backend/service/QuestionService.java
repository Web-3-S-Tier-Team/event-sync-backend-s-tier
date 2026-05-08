package hei.school.even_sync_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import hei.school.even_sync_backend.dto.QuestionDTO;
import hei.school.even_sync_backend.entity.Question;
import hei.school.even_sync_backend.entity.User;
import hei.school.even_sync_backend.exception.NotFoundException;
import hei.school.even_sync_backend.exception.UnauthorizedException;
import hei.school.even_sync_backend.repository.QuestionRepository;

@Service
public class QuestionService {
    QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<QuestionDTO> findQuestion (String sessionId){
        try {
            if (sessionId==null || sessionId=="") {
                throw new BadRequestException("The session id must be defined");
            }
            
            List<Question> listOfQuestions = questionRepository.getBySession(sessionId);

            if (listOfQuestions.isEmpty()) {
                throw new NotFoundException("No question in session:"+sessionId+" has been found");
            }
            return mapToDTO(listOfQuestions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<QuestionDTO> createQuestion (String sessionId,String questionContener,String userName){
        try {
            if (sessionId==null || sessionId=="") {
                throw new BadRequestException("The session id must be defined");
            }
            if (questionContener==""||questionContener==null) {
                throw new BadRequestException("The question can not be null");
            }
            if (userName==null||userName=="") {
                throw new UnauthorizedException("Your user name must be defined");
            }
            questionRepository.createQuestion(sessionId,questionContener,userName);
            
            List<Question> listOfQuestions =  questionRepository.getBySession(sessionId);
            if (listOfQuestions.isEmpty()) {
                throw new NotFoundException("No question in session:"+sessionId+" has been found");
            }
            return mapToDTO(listOfQuestions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<QuestionDTO> addVoteToQuestion (String sessionId,String questionId, String userId, String userName){
        try {
            if (questionRepository.getUserInVoteQuestion(questionId).contains(new User(userId,userName))) {
                questionRepository.upvote(sessionId, questionId);
            }else{
                questionRepository.downvote(sessionId, questionId);
            }
            return mapToDTO( questionRepository.getBySession(sessionId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<QuestionDTO> deleteQuestion (String sessionId,String questionId, String adminKey){
        try {
            if (adminKey==System.getenv("adminKey")) {
                throw new UnauthorizedException("No access to this methode");
            }
            questionRepository.delete(sessionId,questionId);
            return mapToDTO(questionRepository.getBySession(questionId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<QuestionDTO> mapToDTO (List<Question> listOfQuestions) {
        return listOfQuestions.stream().map(q->new QuestionDTO(
                q.getNom(),
                q.getContenu(),
                q.getUpvotes().size()))
            .collect(Collectors.toList());
    }
}

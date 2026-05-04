package hei.school.even_sync_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import hei.school.even_sync_backend.dto.QuestionDTO;
import hei.school.even_sync_backend.entity.Question;
import hei.school.even_sync_backend.exception.NotFoundException;
import hei.school.even_sync_backend.exception.UnauthorizedException;
import hei.school.even_sync_backend.repository.QuestionRepository;

@Service
public class QuestionService {
    QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<QuestionDTO> findQuestion (String idSession){
        try {
            if (idSession==null || idSession=="") {
                throw new BadRequestException("The session id must be defined");
            }
            
            List<Question> listOfQuestions = questionRepository.getBySession(idSession);

            if (listOfQuestions.isEmpty()) {
                throw new NotFoundException("No question in session:"+idSession+" has been found");
            }
            return mapToDTO(listOfQuestions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<QuestionDTO> createQuestion (String idSession,String questionContener,String userName){
        try {
            if (idSession==null || idSession=="") {
                throw new BadRequestException("The session id must be defined");
            }
            if (questionContener==""||questionContener==null) {
                throw new BadRequestException("The question can not be null");
            }
            if (userName==null||userName=="") {
                throw new UnauthorizedException("Your user name must be defined");
            }
            questionRepository.createQuestion(idSession,questionContener,userName);
            
            List<Question> listOfQuestions =  questionRepository.getBySession(idSession);
            if (listOfQuestions.isEmpty()) {
                throw new NotFoundException("No question in session:"+idSession+" has been found");
            }
            return mapToDTO(listOfQuestions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<QuestionDTO> addVoteToQuestion (String idSession){
        throw new RuntimeException("methode not implemented");
    }

    public List<QuestionDTO> deleteQuestion (String idSession){
        throw new RuntimeException("methode not implemented");
    }

    private List<QuestionDTO> mapToDTO (List<Question> listOfQuestions) {
        return listOfQuestions.stream().map(q->new QuestionDTO(
                q.getNom(),
                q.getContenu(),
                q.getUpvotes()))
            .collect(Collectors.toList());
    }
}

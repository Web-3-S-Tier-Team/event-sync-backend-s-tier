package hei.school.even_sync_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import hei.school.even_sync_backend.dto.QuestionDTO;
import hei.school.even_sync_backend.exception.BadRequestException;
import hei.school.even_sync_backend.exception.NotFoundException;
import hei.school.even_sync_backend.exception.UnauthorizedException;
import hei.school.even_sync_backend.service.QuestionService;

@Controller
public class QuestionController {
    QuestionService questionService;

    public QuestionController (QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("session/{idSession}/questions")
    public ResponseEntity<?> getQuestions (@PathVariable String idSession) {
        try {
            List<QuestionDTO> getQuestionInASession = questionService.findQuestion(idSession);
            return ResponseEntity.status(HttpStatus.OK)
            .body(getQuestionInASession);
            
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e.getMessage());
        }
    }

    @PostMapping("session/{idSession}/questions")
    public ResponseEntity<?> postQuestions (@PathVariable String idSession,@RequestBody String questionContainer, @RequestHeader String userName) {
        try {
            List<QuestionDTO> getQuestionInASession = questionService.createQuestion(idSession,questionContainer,userName);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(getQuestionInASession);
            
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(e.getMessage());
        } catch (UnauthorizedException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e.getMessage());
        }
    }

    @PutMapping("session/{sessionId}/questions/{questionId}/addVote")
    public ResponseEntity<?> putQuestionsAddVote (@PathVariable String sessionId,@PathVariable String questionId,@RequestHeader String userId, @RequestHeader String userName ) {
        try {  
        List<QuestionDTO> getQuestionInASession = questionService.upvoteQuestion(sessionId, questionId, userId, userName);            
        return ResponseEntity.status(HttpStatus.OK)
            .body(getQuestionInASession);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e.getMessage());
        }
    }

    @DeleteMapping("session/{sessionId}/questions/{questionId}")
    public ResponseEntity<?> deleteQuestions (@PathVariable String sessionId ,@PathVariable String questionId ,@RequestHeader String admin_key ) {
        try {
            List<QuestionDTO> getQuestionInASession = questionService.deleteQuestion(sessionId, questionId, admin_key);
            return ResponseEntity.status(HttpStatus.OK)
            .body(getQuestionInASession);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e.getMessage());
        }
    }
}

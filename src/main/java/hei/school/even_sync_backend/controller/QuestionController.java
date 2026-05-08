package hei.school.even_sync_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import hei.school.even_sync_backend.dto.QuestionDTO;
import hei.school.even_sync_backend.exception.BadRequestException;
import hei.school.even_sync_backend.exception.ForbiddenRequestException;
import hei.school.even_sync_backend.exception.NotFoundException;
import hei.school.even_sync_backend.exception.UnauthorizedException;
import hei.school.even_sync_backend.service.QuestionService;

@RestController 
public class QuestionController {
    QuestionService questionService;

    public QuestionController (QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("session/{sessionId}/questions")
    public ResponseEntity<?> getQuestions (@PathVariable String sessionId) {
        try {
            List<QuestionDTO> getQuestionInASession = questionService.findQuestion(sessionId);
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

    @PostMapping("event/{eventId}/session/{sessionId}/questions")
    public ResponseEntity<?> postQuestions (@PathVariable String sessionId,@PathVariable Long eventId ,@RequestBody String questionContainer, @RequestHeader String userName) {
        try {
            List<QuestionDTO> getQuestionInASession = questionService.createQuestion(eventId,sessionId,questionContainer,userName);
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
        } catch (ForbiddenRequestException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e.getMessage());
        }
    }

    @PutMapping("session/{sessionId}/questions/{questionId}/addVote")
    public ResponseEntity<?> putQuestionsAddVote (@PathVariable String sessionId,@PathVariable String questionId,@RequestHeader String userId, @RequestHeader String userName ) {
        try {
            List<QuestionDTO> getQuestionInASession = questionService.addVoteToQuestion(sessionId, questionId, userId, userName);
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

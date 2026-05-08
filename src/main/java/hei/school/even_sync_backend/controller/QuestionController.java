package hei.school.even_sync_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hei.school.even_sync_backend.dto.QuestionDTO;
import hei.school.even_sync_backend.exception.BadRequestException;
import hei.school.even_sync_backend.exception.NotFoundException;
import hei.school.even_sync_backend.exception.UnauthorizedException;
import hei.school.even_sync_backend.service.QuestionService;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("session/{idSession}/questions")
    public ResponseEntity<?> getQuestions(@PathVariable String idSession) {
        try {
            List<QuestionDTO> questionsInSession = questionService.findQuestion(idSession);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(questionsInSession);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("session/{idSession}/questions")
    public ResponseEntity<?> postQuestion(@PathVariable String idSession, 
                                          @RequestBody String questionContent, 
                                          @RequestHeader(required = false) String userName) {
        try {
            List<QuestionDTO> questionsInSession = questionService.createQuestion(idSession, questionContent, userName);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(questionsInSession);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("session/{idSession}/questions/{questionId}/upvote")
    public ResponseEntity<?> upvoteQuestion(@PathVariable String idSession, 
                                             @PathVariable String questionId) {
        try {
            questionService.upvoteQuestion(questionId);
            List<QuestionDTO> questionsInSession = questionService.findQuestion(idSession);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(questionsInSession);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("session/{idSession}/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable String idSession, 
                                             @PathVariable String questionId) {
        try {
            questionService.deleteQuestion(questionId);
            List<QuestionDTO> questionsInSession = questionService.findQuestion(idSession);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(questionsInSession);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}

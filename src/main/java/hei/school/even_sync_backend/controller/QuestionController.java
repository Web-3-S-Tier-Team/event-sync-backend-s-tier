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

import hei.school.even_sync_backend.dto.QuestionDTO;
import hei.school.even_sync_backend.exception.BadRequestException;
import hei.school.even_sync_backend.exception.NotFoundException;
import hei.school.even_sync_backend.service.QuestionService;

@Controller
public class QuestionController {
    QuestionService questionService;

    public QuestionController (QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("session/{id}/questions")
    public ResponseEntity<?> getQuestions (@PathVariable String id) {
        try {
            List<QuestionDTO> getQuestionInASession = questionService.findQuestion(id);
            return ResponseEntity.status(HttpStatus.OK)
            .body(getQuestionInASession);
            
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(e.getMessage());
        } 
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e.getMessage());
        }
    }

    @PostMapping("session/{id}/questions")
    public ResponseEntity<?> postQuestions (@PathVariable String idSession) {
        try {
            List<QuestionDTO> getQuestionInASession = questionService.findQuestion(idSession);
            return ResponseEntity.status(HttpStatus.OK)
            .body(getQuestionInASession);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e.getMessage());
        }
    }

    @PutMapping("session/{id}/questions")
    public ResponseEntity<?> putQuestions (@PathVariable String idSession) {
        try {
            List<QuestionDTO> getQuestionInASession = questionService.findQuestion(idSession);
            return ResponseEntity.status(HttpStatus.OK)
            .body(getQuestionInASession);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e.getMessage());
        }
    }

    @DeleteMapping("session/{id}/questions")
    public ResponseEntity<?> deleteQuestions (@PathVariable String idSession) {
        try {
            List<QuestionDTO> getQuestionInASession = questionService.findQuestion(idSession);
            return ResponseEntity.status(HttpStatus.OK)
            .body(getQuestionInASession);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(e.getMessage());
        }
    }
}

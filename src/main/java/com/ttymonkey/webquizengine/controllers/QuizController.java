package com.ttymonkey.webquizengine.controllers;

import com.ttymonkey.webquizengine.models.dto.QuizAnswerRequest;
import com.ttymonkey.webquizengine.models.dto.QuizCreationRequest;
import com.ttymonkey.webquizengine.models.dto.QuizAnswerResponse;
import com.ttymonkey.webquizengine.models.errors.QuizNotFound;
import com.ttymonkey.webquizengine.services.quiz.QuizAnswerVerificationService;
import com.ttymonkey.webquizengine.services.quiz.QuizzesCompletedService;
import com.ttymonkey.webquizengine.services.quiz.QuizzesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/quizzes", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuizController {
    private final QuizAnswerVerificationService answerVerificationService;
    private final QuizzesService quizzesService;

    private final QuizzesCompletedService quizzesCompletedService;

    @Autowired
    public QuizController(
        QuizAnswerVerificationService answerVerificationService,
        QuizzesService quizzesService,
        QuizzesCompletedService quizzesCompletedService
    ) {
        this.answerVerificationService = answerVerificationService;
        this.quizzesService = quizzesService;
        this.quizzesCompletedService = quizzesCompletedService;
    }

    @GetMapping
    public ResponseEntity<?> getAllQuizzes(@RequestParam(defaultValue = "0") int page) {
        System.out.println("Get all!");
        var response = quizzesService.getAll(page);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getQuizById(@PathVariable long id) {
        try {
            var response = quizzesService.getById(id);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (QuizNotFound ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createNewQuiz(@AuthenticationPrincipal UserDetails details, @Valid @RequestBody QuizCreationRequest newQuiz) {
        var response = quizzesService.create(newQuiz, details.getUsername());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteQuiz(@AuthenticationPrincipal UserDetails details, @PathVariable long id) {
        try {
            if (!quizzesService.isAuthor(id, details.getUsername())) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            quizzesService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (QuizNotFound ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{id}/solve")
    public ResponseEntity<?> submitQuizAnswer(
        @AuthenticationPrincipal UserDetails details,
        @PathVariable int id,
        @Valid @RequestBody QuizAnswerRequest quizAnswer
    ) {
        try {
            var answers = quizAnswer.getAnswer();
            var result = answerVerificationService.verifyAnswer(id, answers);

            if (result) {
                quizzesCompletedService.setQuizCompletedByUser(id, details.getUsername());
            }

            var response = new QuizAnswerResponse(
                result,
                result ? "Congratulations, you're right!" : "Wrong answer! Please, try again."
            );

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (QuizNotFound ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("completed")
    public ResponseEntity<?> getQuizzesCompleted(
        @AuthenticationPrincipal UserDetails details,
        @RequestParam(defaultValue = "0") int page
    ) {
        var response = quizzesCompletedService.findAllByUsername(details.getUsername(), page);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

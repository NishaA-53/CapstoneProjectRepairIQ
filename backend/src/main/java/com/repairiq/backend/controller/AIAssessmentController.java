package com.repairiq.backend.controller;

import com.repairiq.backend.model.AIAssessment;
import com.repairiq.backend.repository.AIAssessmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai-assessments")
@CrossOrigin(origins = "*")
public class AIAssessmentController {

    private final AIAssessmentRepository repository;

    public AIAssessmentController(AIAssessmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<AIAssessment> getAllAssessments() {
        return repository.findAll();
    }

    @PostMapping
    public AIAssessment saveAssessment(@RequestBody AIAssessment assessment) {
        return repository.save(assessment);
    }
}
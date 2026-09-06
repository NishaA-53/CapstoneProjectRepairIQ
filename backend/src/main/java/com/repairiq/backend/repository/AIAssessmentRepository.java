package com.repairiq.backend.repository;

import com.repairiq.backend.model.AIAssessment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AIAssessmentRepository extends MongoRepository<AIAssessment, String> {
}
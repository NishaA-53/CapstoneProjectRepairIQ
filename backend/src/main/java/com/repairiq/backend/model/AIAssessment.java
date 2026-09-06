package com.repairiq.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ai_assessments")
public class AIAssessment {

    @Id
    private String id;

    private String productName;
    private int productAge;
    private String problemDescription;
    private double estimatedRepairCost;
    private int repairabilityScore;
    private String rating;
    private String recommendation;
    private String explanation;

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductAge() {
        return productAge;
    }

    public void setProductAge(int productAge) {
        this.productAge = productAge;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public double getEstimatedRepairCost() {
        return estimatedRepairCost;
    }

    public void setEstimatedRepairCost(double estimatedRepairCost) {
        this.estimatedRepairCost = estimatedRepairCost;
    }

    public int getRepairabilityScore() {
        return repairabilityScore;
    }

    public void setRepairabilityScore(int repairabilityScore) {
        this.repairabilityScore = repairabilityScore;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
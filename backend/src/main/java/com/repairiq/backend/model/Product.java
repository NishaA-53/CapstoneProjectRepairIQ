package com.repairiq.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private int age;
    private int spareParts;
    private int disassembly;
    private int repairCost;
    private int repairabilityScore;

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(int spareParts) {
        this.spareParts = spareParts;
    }

    public int getDisassembly() {
        return disassembly;
    }

    public void setDisassembly(int disassembly) {
        this.disassembly = disassembly;
    }

    public int getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(int repairCost) {
        this.repairCost = repairCost;
    }

    public int getRepairabilityScore() {
        return repairabilityScore;
    }

    public void setRepairabilityScore(int repairabilityScore) {
        this.repairabilityScore = repairabilityScore;
    }
}
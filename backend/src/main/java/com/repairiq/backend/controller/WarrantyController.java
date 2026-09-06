package com.repairiq.backend.controller;

import com.repairiq.backend.model.Warranty;
import com.repairiq.backend.repository.WarrantyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warranty")
@CrossOrigin(origins = "*")
public class WarrantyController {

    private final WarrantyRepository repository;

    public WarrantyController(WarrantyRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Warranty> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Warranty create(@RequestBody Warranty warranty) {
        return repository.save(warranty);
    }
}
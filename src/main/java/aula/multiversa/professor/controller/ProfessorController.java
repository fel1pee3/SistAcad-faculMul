package aula.multiversa.professor.controller;

import aula.multiversa.professor.model.ProfessorModel;
import aula.multiversa.professor.service.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProfessorModel> create(@RequestBody ProfessorModel professor) {
        return ResponseEntity.ok(professorService.save(professor));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorModel>> findAll() {
        return ResponseEntity.ok(professorService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<ProfessorModel>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfessorModel> update(@PathVariable Long id, @RequestBody ProfessorModel professor) {
        return ResponseEntity.ok(professorService.update(id, professor));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        professorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

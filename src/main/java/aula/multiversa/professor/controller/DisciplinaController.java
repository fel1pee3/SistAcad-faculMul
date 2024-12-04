package aula.multiversa.professor.controller;

import aula.multiversa.professor.model.DisciplinaModel;
import aula.multiversa.professor.service.DisciplinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping("/create")
    public ResponseEntity<DisciplinaModel> create(@RequestBody DisciplinaModel disciplina) {
        return ResponseEntity.ok(disciplinaService.save(disciplina));
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaModel>> findAll() {
        return ResponseEntity.ok(disciplinaService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<DisciplinaModel>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(disciplinaService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DisciplinaModel> update(@PathVariable Long id, @RequestBody DisciplinaModel disciplina) {
        return ResponseEntity.ok(disciplinaService.update(id, disciplina));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        disciplinaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addProf/{professorId}/toDisc/{disciplinaId}")
    public ResponseEntity<Void> assignToDiscipline(@PathVariable Long professorId, @PathVariable Long disciplinaId) {
        disciplinaService.addProfessorToDisciplina(professorId, disciplinaId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/removeProf/{disciplinaId}")
    public ResponseEntity<Void> removeFromDiscipline(@PathVariable Long disciplinaId) {
        disciplinaService.removeProfessorFromDisciplina(disciplinaId);
        return ResponseEntity.noContent().build();
    }

}
package aula.multiversa.professor.controller;

import aula.multiversa.professor.model.AlunoModel;
import aula.multiversa.professor.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/create")
    public ResponseEntity<AlunoModel> create(@RequestBody AlunoModel aluno) {
        return ResponseEntity.ok(alunoService.save(aluno));
    }

    @GetMapping
    public ResponseEntity<List<AlunoModel>> findAll() {
        return ResponseEntity.ok(alunoService.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<AlunoModel>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.findById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AlunoModel> update(@PathVariable Long id, @RequestBody AlunoModel aluno) {
        return ResponseEntity.ok(alunoService.update(id, aluno));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{alunoId}/addDisciplina/{disciplinaId}")
    public ResponseEntity<Void> addToDiscipline(@PathVariable Long alunoId, @PathVariable Long disciplinaId) {
        alunoService.addAlunoToDisciplina(alunoId, disciplinaId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{alunoId}/removeDisciplina/{disciplinaId}")
    public ResponseEntity<Void> removeFromDiscipline(@PathVariable Long alunoId, @PathVariable Long disciplinaId) {
        alunoService.removeAlunoFromDisciplina(alunoId, disciplinaId);
        return ResponseEntity.noContent().build();
    }
}

package aula.multiversa.professor.service;

import aula.multiversa.professor.model.DisciplinaModel;
import aula.multiversa.professor.model.ProfessorModel;
import aula.multiversa.professor.repository.DisciplinaRepository;
import aula.multiversa.professor.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    // Método para salvar ou atualizar uma disciplina
    public DisciplinaModel save(DisciplinaModel disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public DisciplinaModel update(Long disciplinaId, DisciplinaModel disciplina) {
        disciplinaRepository.findById(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        return disciplinaRepository.save(disciplina);
    }

        // Método para buscar uma disciplina por ID
    public Optional<DisciplinaModel> findById(Long id) {
        return disciplinaRepository.findById(id);
    }

    // Método para buscar todas as disciplinas
    public List<DisciplinaModel> findAll() {
        return disciplinaRepository.findAll();
    }

    // Método para excluir uma disciplina por ID
    public void deleteById(Long id) {
        disciplinaRepository.deleteById(id);
    }

    // Associar um Professor a uma Disciplina
    public DisciplinaModel addProfessorToDisciplina(Long disciplinaId, Long professorId) {
        DisciplinaModel disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        ProfessorModel professor = professorRepository.findById(professorId).orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        disciplina.setProfessor(professor);
        return disciplinaRepository.save(disciplina);
    }

    // Remover um Professor de uma Disciplina
    public DisciplinaModel removeProfessorFromDisciplina(Long disciplinaId) {
        DisciplinaModel disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        disciplina.setProfessor(null);
        return disciplinaRepository.save(disciplina);
    }
}

package aula.multiversa.professor.service;

import aula.multiversa.professor.model.AlunoModel;
import aula.multiversa.professor.model.ProfessorModel;
import aula.multiversa.professor.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    // Método para salvar um professor
    public ProfessorModel save(ProfessorModel professor) {
        return professorRepository.save(professor);
    }

    public ProfessorModel update(Long professorId, ProfessorModel professor) {
        professorRepository.findById(professorId).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        return professorRepository.save(professor);
    }

    // Método para buscar um professor por ID
    public Optional<ProfessorModel> findById(Long id) {
        return professorRepository.findById(id);
    }

    // Método para buscar todos os professores
    public List<ProfessorModel> findAll() {
        return professorRepository.findAll();
    }

    // Método para excluir um professor por ID
    public void deleteById(Long id) {
        professorRepository.deleteById(id);
    }
}

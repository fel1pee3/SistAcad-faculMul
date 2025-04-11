package aula.multiversa.professor.service;

import aula.multiversa.professor.exception.EmailInvalidoException;
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
        // Recupera o professor do banco de dados
        ProfessorModel professorExistente = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        // Atualiza os campos do professor existente com os dados do novo objeto
        professorExistente.setNome(professor.getNome());
        professorExistente.setEmail(professor.getEmail());
        professorExistente.setDisciplinas(professor.getDisciplinas());

        // Salva o professor atualizado no banco de dados
        return professorRepository.save(professorExistente);
    }

    // Método para buscar um professor por ID
    public Optional<ProfessorModel> findById(Long id) {
        return Optional.ofNullable(professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNaoEncontradoException("Professor não encontrado com id: " + id)));
    }

    // Método para buscar todos os professores
    public List<ProfessorModel> findAll() {
        return professorRepository.findAll();
    }

    // Método para excluir um professor por ID
    public void deleteById(Long id) {
        professorRepository.deleteById(id);
    }

    //METODO PARA Validar e lançar exceção no email
    public ProfessorModel cadastrarProfessor(ProfessorModel professor) {
        if (!professor.getEmail().contains("@")) {
            throw new EmailInvalidoException("Email inválido.");
        }
        return professorRepository.save(professor);
    }

    //Metodo pra lança uma exceçao no Id
    public class ProfessorNaoEncontradoException extends RuntimeException {
        public ProfessorNaoEncontradoException(String mensagem) {
            super(mensagem);
        }
    }

}

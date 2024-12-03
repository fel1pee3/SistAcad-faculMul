package aula.multiversa.professor.service;

import aula.multiversa.professor.model.AlunoModel;
import aula.multiversa.professor.model.DisciplinaModel;
import aula.multiversa.professor.repository.AlunoRepository;
import aula.multiversa.professor.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    // Método para salvar ou atualizar um aluno
    public AlunoModel save(AlunoModel aluno) {
        return alunoRepository.save(aluno);
    }

    // Método para buscar um aluno por ID
    public Optional<AlunoModel> findById(Long id) {
        return alunoRepository.findById(id);
    }

    // Método para buscar todos os alunos
    public List<AlunoModel> findAll() {
        return alunoRepository.findAll();
    }

    // Método para excluir um aluno por ID
    public void deleteById(Long id) {
        alunoRepository.deleteById(id);
    }

    // Adicionar Aluno em uma Disciplina
    public AlunoModel addAlunoToDisciplina(Long alunoId, Long disciplinaId) {
        AlunoModel aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        DisciplinaModel disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        aluno.getDisciplinas().add(disciplina);
        return alunoRepository.save(aluno);
    }

    // Remover Aluno de uma Disciplina
    public AlunoModel removeAlunoFromDisciplina(Long alunoId, Long disciplinaId) {
        AlunoModel aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        DisciplinaModel disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        aluno.getDisciplinas().remove(disciplina);
        return alunoRepository.save(aluno);
    }


}

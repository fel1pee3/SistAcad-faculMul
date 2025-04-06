package Model;

import aula.multiversa.professor.model.AlunoModel;
import aula.multiversa.professor.model.DisciplinaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisciplinaModelTest {

    private DisciplinaModel disciplina;
    private AlunoModel aluno;

    @BeforeEach
    void setUp() {
        disciplina = new DisciplinaModel();
        disciplina.setNome("Matemática");

        aluno = new AlunoModel();
        aluno.setNome("João");
        aluno.setEmail("joao@gmail.com");
    }

    @Test
    void testAdicionarAluno() {
        disciplina.adicionarAluno(aluno);

        assertNotNull(disciplina.getAlunos());
        assertTrue(disciplina.getAlunos().contains(aluno));
    }

    

}

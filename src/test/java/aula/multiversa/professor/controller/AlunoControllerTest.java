package aula.multiversa.professor.controller;

import aula.multiversa.professor.model.AlunoModel;
import aula.multiversa.professor.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class AlunoControllerTest {

    private final AlunoService alunoService = Mockito.mock(AlunoService.class);
    private final AlunoController alunoController = new AlunoController(alunoService);

    @Test
    public void testTC001_CriarAlunoComDadosValidos() {
        AlunoModel aluno = new AlunoModel();
        aluno.setNome("João");
        aluno.setIdade(20);
        aluno.setCurso("Engenharia");

        Mockito.when(alunoService.save(any(AlunoModel.class))).thenReturn(aluno);

        ResponseEntity<AlunoModel> response = alunoController.create(aluno);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("João", response.getBody().getNome());
        assertEquals(20, response.getBody().getIdade());
        assertEquals("Engenharia", response.getBody().getCurso());
    }

    @Test
    public void testTC002_CriarAlunoSemNome() {
        AlunoModel aluno = new AlunoModel();
        aluno.setIdade(20);
        aluno.setCurso("Engenharia");

        Mockito.when(alunoService.save(any(AlunoModel.class)))
                .thenThrow(new IllegalArgumentException("Nome é obrigatório"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> alunoController.create(aluno));
        assertEquals("Nome é obrigatório", exception.getMessage());
    }

    @Test
    public void testTC003_CriarAlunoComIdadeNegativa() {
        AlunoModel aluno = new AlunoModel();
        aluno.setNome("Maria");
        aluno.setIdade(-5);
        aluno.setCurso("Matemática");

        Mockito.when(alunoService.save(any(AlunoModel.class)))
                .thenThrow(new IllegalArgumentException("Idade não pode ser negativa"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> alunoController.create(aluno));
        assertEquals("Idade não pode ser negativa", exception.getMessage());
    }
}

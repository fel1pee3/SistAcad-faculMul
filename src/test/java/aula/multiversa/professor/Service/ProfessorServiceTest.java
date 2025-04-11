package aula.multiversa.professor.Service;

import aula.multiversa.professor.exception.EmailInvalidoException;
import aula.multiversa.professor.model.ProfessorModel;
import aula.multiversa.professor.repository.ProfessorRepository;
import aula.multiversa.professor.service.ProfessorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    //TC 001 - TESTA O SISTAMA QUANDO EU REALIZO UM CADASTRO COM O EMAIL ERRADO *SEM O @ - APL
    @Test
    void deveLancarExcecaoQuandoEmailForInvalido() {

        ProfessorModel professor = new ProfessorModel();
        professor.setNome("Mini Messi");
        professor.setEmail("email-invalido");


        Assertions.assertThrows(EmailInvalidoException.class, () -> {
            professorService.cadastrarProfessor(professor);
        });
    }

    //TC 002 - TESTA BUSCAR UM PROFESSOR POR ID COM UM ID INVALIDO -- APL
    @Test
    void deveLancarExcecaoQuandoNaoEncontrarProfessorPorId() {

        Long idInexistente = 999L;

        Mockito.when(professorRepository.findById(idInexistente))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ProfessorService.ProfessorNaoEncontradoException.class, () -> {
            professorService.findById(idInexistente);
        });

        Mockito.verify(professorRepository, Mockito.times(1)).findById(idInexistente);
    }


    //TC03 - BUSCAR TODOS OS PROFESSOR
    @Test
    void deveBuscarTodosProfessores() {
        List<ProfessorModel> professores = List.of(new ProfessorModel(), new ProfessorModel());

        when(professorRepository.findAll()).thenReturn(professores);

        List<ProfessorModel> resultado = professorService.findAll();

        assertEquals(2, resultado.size());
        verify(professorRepository).findAll();
    }

    //TC04 - DELETA PROFESSOR POR ID
    @Test
    void deveExcluirProfessorPorId() {
        Long id = 1L;

        doNothing().when(professorRepository).deleteById(id);

        professorService.deleteById(id);

        verify(professorRepository).deleteById(id);
    }


}

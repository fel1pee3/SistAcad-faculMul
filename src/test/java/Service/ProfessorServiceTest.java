package Service;

import aula.multiversa.professor.model.ProfessorModel;
import aula.multiversa.professor.repository.ProfessorRepository;
import aula.multiversa.professor.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProfessorServiceTest {

    //Criação de Casos de Teste (INDIVIDUAL)

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarTodosProfessores() {
        List<ProfessorModel> professores = List.of(new ProfessorModel(), new ProfessorModel());

        when(professorRepository.findAll()).thenReturn(professores);

        List<ProfessorModel> resultado = professorService.findAll();

        assertEquals(2, resultado.size());
        verify(professorRepository).findAll();
    }

    @Test
    void deveExcluirProfessorPorId() {
        Long id = 1L;

        doNothing().when(professorRepository).deleteById(id);

        professorService.deleteById(id);

        verify(professorRepository).deleteById(id);
    }

}

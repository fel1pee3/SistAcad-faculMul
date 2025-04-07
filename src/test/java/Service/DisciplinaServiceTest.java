package Service;

import aula.multiversa.professor.model.DisciplinaModel;
import aula.multiversa.professor.model.ProfessorModel;
import aula.multiversa.professor.repository.DisciplinaRepository;
import aula.multiversa.professor.repository.ProfessorRepository;
import aula.multiversa.professor.service.DisciplinaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DisciplinaServiceTest {

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @InjectMocks
    private DisciplinaService disciplinaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarDisciplina() {
        //criar Mock disciplina
        DisciplinaModel disciplina = new DisciplinaModel();
        disciplina.setNome("Análise e Desenvolvimento de Sistemas");

        //simula comportamento de repositorio
        when(disciplinaRepository.save(any(DisciplinaModel.class))).thenReturn(disciplina);

        //chamando serviço
        DisciplinaModel resultado = disciplinaService.save(disciplina);

        // Verificações
        assertNotNull(resultado);
        assertEquals("Análise e Desenvolvimento de Sistemas", resultado.getNome());

        // Verifica se o método save foi chamado
        verify(disciplinaRepository).save(any(DisciplinaModel.class));
    }

    //Criação de Casos de Teste (INDIVIDUAL)

    @Test
    void deveRetornarDisciplinaPorId() {
        DisciplinaModel disciplina = new DisciplinaModel();
        disciplina.setId(1L);
        disciplina.setNome("Matemática");

        when(disciplinaRepository.findById(1L)).thenReturn(Optional.of(disciplina));

        Optional<DisciplinaModel> resultado = disciplinaService.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Matemática", resultado.get().getNome());
    }

    @Test
    void deveDeletarDisciplinaPorId() {
        disciplinaService.deleteById(1L);
        verify(disciplinaRepository).deleteById(1L);
    }

}

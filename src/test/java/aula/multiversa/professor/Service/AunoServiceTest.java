package aula.multiversa.professor.Service;

import aula.multiversa.professor.model.AlunoModel;
import aula.multiversa.professor.repository.AlunoRepository;
import aula.multiversa.professor.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    //TC01 - CRIAR EXCEÇÃO QUANDO EMAIL FOR NULO - APL FELIPE
    @Test
    void deveLancarExcecaoQuandoEmailForNulo() {
        AlunoModel aluno = new AlunoModel();
        aluno.setMatricula(Long.valueOf("456"));
        aluno.setNome("Maria");

        Exception excecao = assertThrows(IllegalArgumentException.class, () -> {
            alunoService.salvar(aluno);
        });

        assertEquals("Email é obrigatório", excecao.getMessage());
    }

    // TC02 - BUSCA ALUNO POR MATRICULA - PL - TA VAGO
    @Test
    void deveRetornarAlunoPorMatricula() {
        AlunoModel aluno = new AlunoModel();
        aluno.setMatricula(Long.valueOf("789"));
        aluno.setNome("Carlos");
        aluno.setEmail("carlos@email.com");

        when(alunoRepository.findById(Long.valueOf("789"))).thenReturn(Optional.of(aluno));

        Optional<AlunoModel> resultado = alunoService.buscarPorMatricula("789");

        assertTrue(resultado.isPresent());
        assertEquals("Carlos", resultado.get().getNome());
    }

    //TC03 - DELETA ALUNO POR MATRICULA
    @Test
    void deveDeletarAlunoPorMatricula() {
        String matricula = "999";

        alunoService.deletar(matricula);

        verify(alunoRepository).deleteById(matricula);
    }


}

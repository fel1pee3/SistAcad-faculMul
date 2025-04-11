package aula.multiversa.professor.controller;

import aula.multiversa.professor.model.AlunoModel;
import aula.multiversa.professor.model.DisciplinaModel;
import aula.multiversa.professor.repository.AlunoRepository;
import aula.multiversa.professor.repository.DisciplinaRepository;
import aula.multiversa.professor.service.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AlunoService alunoService;

    @MockitoBean
    private AlunoRepository alunoRepository;

    @MockitoBean
    private DisciplinaRepository disciplinaRepository;

    private DisciplinaModel disciplina;

    private final ObjectMapper objectMapper = new ObjectMapper();

    //TC001 CRIAR ALUNO COM DADOS VALIDO
    @Test
    void testCriarAluno_ComDadosValidos_DeveRetornar200() throws Exception {
        AlunoModel alunoValido = new AlunoModel();
        alunoValido.setNome("João");
        alunoValido.setEmail("joao@email.com");
        alunoValido.setDisciplinas(null);

        Mockito.when(alunoService.save(Mockito.any(AlunoModel.class))).thenReturn(alunoValido);

        mockMvc.perform(post("/alunos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoValido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.email").value("joao@email.com"));
    }

    //TC002 CRIAR ALUNO SEM NOME
    @Test
    void testCriarAluno_SemNome_DeveRetornar400() throws Exception {
        AlunoModel alunoSemNome = new AlunoModel();
        alunoSemNome.setEmail("joao@email.com");

        mockMvc.perform(post("/alunos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoSemNome)))
                .andExpect(status().isBadRequest());

        // Verificando que o método save do service NÃO foi chamado
        Mockito.verify(alunoService, Mockito.never()).save(Mockito.any(AlunoModel.class));
    }

    //TC003 CRIAR ALUNO SEM E-MAIL
    @Test
    void testCriarAluno_SemEmail_DeveRetornar400() throws Exception {
        AlunoModel alunoSemEmail = new AlunoModel();
        alunoSemEmail.setNome("Maria");

        mockMvc.perform(post("/alunos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoSemEmail)))
                .andExpect(status().isBadRequest());

        // Verificando que o método save do service NÃO foi chamado
        Mockito.verify(alunoService, Mockito.never()).save(Mockito.any(AlunoModel.class));
    }

    // TC004 - CRIAR UM ALUNO EM UMA DISCIPLINA
    @Test
    void testCriarAluno_ComDisciplina_DeveRetornar200() throws Exception {
        disciplina = new DisciplinaModel();
        disciplina.setNome("Geografia");

        // Criando aluno e associando a disciplina
        AlunoModel alunoValido = new AlunoModel();
        alunoValido.setNome("João");
        alunoValido.setEmail("joao@email.com");
        alunoValido.setDisciplinas(Set.of(disciplina));

        // Mock do service retornando o próprio aluno
        Mockito.when(alunoService.save(Mockito.any(AlunoModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(post("/alunos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoValido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.email").value("joao@email.com"))
                .andExpect(jsonPath("$.disciplinas[0].nome").value("Geografia"));
    }


    @TestConfiguration
    static class TestConfig {
        @Bean
        public AlunoService alunoService() {
            return Mockito.mock(AlunoService.class);
        }
    }
}
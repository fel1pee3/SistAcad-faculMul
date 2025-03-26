package aula.multiversa.professor.controller;

import aula.multiversa.professor.model.AlunoModel;
import aula.multiversa.professor.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private AlunoModel alunoValido;

    @BeforeEach
    void setUp() {
        alunoValido = new AlunoModel();
        alunoValido.setNome("João");
        alunoValido.setEmail("joao@email.com");
        alunoValido.setDisciplinas(null);  // Lista vazia por padrão
    }

    @Test
    void testCriarAluno_ComDadosValidos_DeveRetornar200() throws Exception {
        Mockito.when(alunoService.save(Mockito.any(AlunoModel.class))).thenReturn(alunoValido);

        mockMvc.perform(post("/alunos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoValido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.email").value("joao@email.com"));
    }

    @Test
    void testCriarAluno_SemNome_DeveRetornar400() throws Exception {
        AlunoModel alunoSemNome = new AlunoModel();
        alunoSemNome.setEmail("joao@email.com");

        mockMvc.perform(post("/alunos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoSemNome)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCriarAluno_SemEmail_DeveRetornar400() throws Exception {
        AlunoModel alunoSemEmail = new AlunoModel();
        alunoSemEmail.setNome("Maria");

        mockMvc.perform(post("/alunos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alunoSemEmail)))
                .andExpect(status().isBadRequest());
    }
}

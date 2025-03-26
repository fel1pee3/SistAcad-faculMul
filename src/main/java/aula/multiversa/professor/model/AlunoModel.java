package aula.multiversa.professor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name="Aluno")
public class AlunoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Matricula;

    @NotBlank(message = "O nome do aluno é obrigatório")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O e-mail do aluno é obrigatório")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @ManyToMany
    @JoinTable(
            name="Aluno_Disciplina",
            joinColumns = @JoinColumn(name="aluno_id"),
            inverseJoinColumns = @JoinColumn(name="disciplina_id")
    )
    private Set<DisciplinaModel> disciplinas;

    public Long getMatricula() {
        return Matricula;
    }

    public void setMatricula(Long matricula) {
        Matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<DisciplinaModel> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Set<DisciplinaModel> disciplinas) {
        this.disciplinas = disciplinas;
    }
}

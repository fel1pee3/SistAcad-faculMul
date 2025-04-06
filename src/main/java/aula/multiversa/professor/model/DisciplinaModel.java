package aula.multiversa.professor.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Disciplina")
public class DisciplinaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name="professor_id")
    private ProfessorModel professor;

    @ManyToMany(mappedBy = "disciplinas")
    private Set<AlunoModel> alunos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ProfessorModel getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorModel professor) {
        this.professor = professor;
    }

    public Set<AlunoModel> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<AlunoModel> alunos) {
        this.alunos = alunos;
    }

    public void adicionarAluno(AlunoModel aluno) {
        if (alunos == null) {
            alunos = new HashSet<>();
        }
        alunos.add(aluno);
    }

    public void setProfessor() {
    }

}

package aula.multiversa.professor.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
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

}

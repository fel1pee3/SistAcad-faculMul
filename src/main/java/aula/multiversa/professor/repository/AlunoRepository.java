package aula.multiversa.professor.repository;

import aula.multiversa.professor.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {

    void deleteById(String matricula);
}

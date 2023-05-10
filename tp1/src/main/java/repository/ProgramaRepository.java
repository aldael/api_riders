package repository;

import API.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Integer> {
    @Query(
            value = "select * from courses where id IN (:id)",
            nativeQuery = true
    )
    List<Integer> findProgramariders(Integer id);
}

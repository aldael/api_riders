package repository;

import API.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Integer> {
    @Query(
            value = "select rider_id from aparicion where show_id = :id",
            nativeQuery = true
    )
    List<Integer> findProgramariders(Integer id);
}

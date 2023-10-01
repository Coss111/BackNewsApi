package ucb.edu.ejercicioApi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.ejercicioApi.entity.DogEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DogRepository extends JpaRepository<DogEntity, Long> {

    @Override
    //@Query(value = "SELECT dog WHERE active = true AND id_dog = :id", nativeQuery = true)
    Page<DogEntity> findAll(Pageable pageable);
    

    @Modifying
    @Query(value = "UPDATE dog SET active = false WHERE id_dog = :id", nativeQuery = true)
    void deleteById(@Param("id") Long id);

}

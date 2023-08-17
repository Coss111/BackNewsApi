package ucb.edu.ejercicioApi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.ejercicioApi.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Override
    Page<News> findAll(Pageable pageable);

}

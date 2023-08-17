package ucb.edu.ejercicioApi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucb.edu.ejercicioApi.repository.NewsRepository;

@Service
public class NewsDao {

    @Autowired
    private NewsRepository newsRepository;
}

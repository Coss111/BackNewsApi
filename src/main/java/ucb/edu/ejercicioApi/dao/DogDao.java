package ucb.edu.ejercicioApi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucb.edu.ejercicioApi.repository.DogRepository;

@Service
public class DogDao {

    @Autowired
    private DogRepository dogRepository;
}

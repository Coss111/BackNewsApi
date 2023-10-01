package ucb.edu.ejercicioApi.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ucb.edu.ejercicioApi.dao.DogDao;
import ucb.edu.ejercicioApi.entity.DogEntity;
import ucb.edu.ejercicioApi.repository.DogRepository;

@Service
public class DogBl {

    private static final Logger logger = LoggerFactory.getLogger(DogBl.class);

    @Autowired
    private DogRepository dogRepository;

    @Value("${Api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private DogDao newsDao;

    public DogEntity fetchDogSave(String code) {
        String apiUrl = "https://http.dog/" + code + ".json";

        logger.debug("Obteniendo datos de la noticia desde la URL: {}", apiUrl);
        ResponseEntity<DogEntity> response = restTemplate.getForEntity(apiUrl, DogEntity.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            DogEntity dog = response.getBody();
            logger.debug("Datos del código recibidos: {}", dog);

            dogRepository.save(dog);
            logger.info("Código de estado guardado exitosamente en la base de datos.");

            return dog;
        } else {
            logger.warn("Fallo al recuperar datos del código desde la API. Código de respuesta: {}", response.getStatusCodeValue());
            return null; // Devuelve null si no se encuentra el código.
        }
    }

    public Page<DogEntity> findAllDog (int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return dogRepository.findAll(pageable);
    }

    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }
}

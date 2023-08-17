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
import ucb.edu.ejercicioApi.dao.NewsDao;
import ucb.edu.ejercicioApi.entity.News;
import ucb.edu.ejercicioApi.repository.NewsRepository;

@Service
public class NewsBl {

    private static final Logger logger = LoggerFactory.getLogger(NewsBl.class);

    @Autowired
    private NewsRepository newsRepository;

    @Value("${Api.url}")
    private String apiUrl;

    @Value("${Api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private NewsDao newsDao;

    public News fetchAndSaveNews(){
        apiUrl = apiUrl + "?access_key=" + apiKey;
        logger.debug("Obteniendo datos de la noticia desde la URL: {}", apiUrl);
        ResponseEntity<News> response = restTemplate.getForEntity(apiUrl, News.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            News noticia = response.getBody();
            logger.debug("Datos de la noticia recibidos: {}", noticia);

            newsRepository.save(noticia);
            logger.info("Noticia guardada exitosamente en la base de datos.");

            return noticia;
        } else {
            logger.warn("Fallo al recuperar datos de la noticia desde la API. Código de respuesta: {}", response.getStatusCodeValue());
            throw new RuntimeException("Error al consumir la API de noticias");
        }
    }

    public Page<News> findAllNews (int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return newsRepository.findAll(pageable);
    }
}

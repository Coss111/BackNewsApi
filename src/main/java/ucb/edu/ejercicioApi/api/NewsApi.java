package ucb.edu.ejercicioApi.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ucb.edu.ejercicioApi.bl.NewsBl;
import ucb.edu.ejercicioApi.dto.ResponseDto;
import ucb.edu.ejercicioApi.entity.News;

@RestController
@RequestMapping("/api/v1/news")
public class NewsApi {
    private static final Logger logger = LoggerFactory.getLogger(NewsApi.class);

    @Autowired
    private NewsBl newsBl;

    @GetMapping("")
    public ResponseEntity<ResponseDto<News>> fetchAndSaveNews() {
        try {
            logger.info("Iniciando el proceso para obtener y guardar una noticia mediante el endpoint /fetch.");
            News noticia = newsBl.fetchAndSaveNews();
            return new ResponseEntity<>(new ResponseDto<>(200, noticia, "Noticia guardada exitosamente"), HttpStatus.OK);

        } catch (RuntimeException e) {
            logger.error("Error al procesar la petición /fetch: ", e.getMessage());
            return new ResponseEntity<>(new ResponseDto<>(500, "Error Interno del Servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<ResponseDto<Page<News>>> listNews(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            logger.info("Iniciando el proceso para obtener una lista paginada de noticias.");
            Page<News> noticias = newsBl.findAllNews(page, size);
            return new ResponseEntity<>(new ResponseDto<>(200, noticias, "Lista de perros"), HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error al procesar la petición /list: ", e.getMessage());
            return new ResponseEntity<>(new ResponseDto<>(500, "Error Interno del Servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

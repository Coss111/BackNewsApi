package ucb.edu.ejercicioApi.api;

import org.aspectj.apache.bcel.classfile.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ucb.edu.ejercicioApi.bl.DogBl;
import ucb.edu.ejercicioApi.dto.ResponseDto;
import ucb.edu.ejercicioApi.entity.DogEntity;

@RestController
@RequestMapping("/api/v1/dog")
public class DogApi {
    private static final Logger logger = LoggerFactory.getLogger(DogApi.class);

    @Autowired
    private DogBl dogBl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/fetch/{code}")
    public ResponseEntity<ResponseDto<DogEntity>> fetchAndSaveDog(@PathVariable String code) {
        try {
            logger.info("Iniciando el proceso para obtener y guardar un código de estado mediante el endpoint /fetch.");

            // Construye la URL de la API externa
            String apiUrl = "https://http.dog/" + code + ".json";
            logger.debug("URL de la API externa: {}", apiUrl);

            // Realiza la solicitud a la API
            ResponseEntity<DogEntity> response = restTemplate.getForEntity(apiUrl, DogEntity.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                DogEntity dog = dogBl.fetchDogSave(code);

                logger.info("Código de estado guardado exitosamente.");
                return new ResponseEntity<>(new ResponseDto<>(200, dog, "Código de estado guardado exitosamente"), HttpStatus.OK);
            } else {
                logger.warn("No se encontró el código solicitado.");
                return new ResponseEntity<>(new ResponseDto<>(404, "Código no encontrado"), HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            logger.error("Error al procesar la petición /fetch: ", e);
            return new ResponseEntity<>(new ResponseDto<>(500, "Error Interno del Servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<ResponseDto<Page<DogEntity>>> listNews(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            logger.info("Iniciando el proceso para obtener una lista paginada de noticias.");
            Page<DogEntity> noticias = dogBl.findAllDog(page, size);
            return new ResponseEntity<>(new ResponseDto<>(200, noticias, "Lista de perros"), HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error al procesar la petición /list: ", e.getMessage());
            return new ResponseEntity<>(new ResponseDto<>(500, "Error Interno del Servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<DogEntity>> deleteNews(@PathVariable Long id) {
        try {
            logger.info("Iniciando el proceso para eliminar un perro.");
            dogBl.deleteDog(id);
            return new ResponseEntity<>(new ResponseDto<>(200, null, "Perro eliminado exitosamente"), HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error al procesar la petición /delete: ", e.getMessage());
            return new ResponseEntity<>(new ResponseDto<>(500, "Error Interno del Servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package ucb.edu.ejercicioApi.api;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ucb.edu.ejercicioApi.entity.News;

@RestController
@RequestMapping("/api/v1/test")
public class TestApi {
    private static Logger logger = LoggerFactory.getLogger(TestApi.class);
    public class MediaStackApiInvoker {
        public static ResponseEntity<News> invokeApi(String apiUrl, String apiKey) {
            RestTemplate restTemplate = new RestTemplate();

            // Agregar la clave de acceso (API key) como query parameter
            apiUrl = apiUrl + "?access_key=" + apiKey;

            ResponseEntity<News> response = restTemplate.getForEntity(apiUrl, News.class);
            return response;
        }

        public static void main(String[] args) {
            String apiUrl = "http://api.mediastack.com/v1/news";
            String apiKey = "3513229c530addc65459218af3a3abab";

            ResponseEntity<News> response = invokeApi(apiUrl, apiKey);

            if (response.getStatusCode().is2xxSuccessful()) {
                News news = response.getBody();
                System.out.println("Respuesta de la API: " + news);
            } else {
                System.out.println("Error en la solicitud. Código de respuesta: " + response.getStatusCodeValue());
            }
        }
    }
}

package ucb.edu.ejercicioApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DogDto implements Serializable {
    private ImageDto image;

    @JsonProperty("status_code")
    private int statusCode;

    private String title;

    private String url;

    public DogDto() {
    }

    public DogDto(ImageDto image, int statusCode, String title, String url) {
        this.image = image;
        this.statusCode = statusCode;
        this.title = title;
        this.url = url;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

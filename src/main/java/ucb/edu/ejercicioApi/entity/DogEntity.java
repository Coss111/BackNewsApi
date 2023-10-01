package ucb.edu.ejercicioApi.entity;

import jakarta.persistence.*;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "dog", catalog = "advice_db", schema = "public")
@NamedQueries({
        @NamedQuery(name = "dog.findAll", query = "SELECT d FROM DogEntity d"),
})

public class DogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dog")
    private Long id_dog;

    @Embedded
    private Image image;

    @Basic(optional = false)
    @Column(name = "status_code")
    @JsonProperty("status_code")
    private int statusCode;

    @Basic(optional = false)
    @Column(name = "title")
    private String title;

    @Basic(optional = false)
    @Column(name = "url")
    private String url;

    @Basic(optional = false)
    private boolean active = true;


    @Embeddable
    public static class Image {
        private String avif;
        private String jpg;
        private String jxl;
        private String webp;



        public String getAvif() {
            return avif;
        }

        public void setAvif(String avif) {
            this.avif = avif;
        }

        public String getJpg() {
            return jpg;
        }

        public void setJpg(String jpg) {
            this.jpg = jpg;
        }

        public String getJxl() {
            return jxl;
        }

        public void setJxl(String jxl) {
            this.jxl = jxl;
        }

        public String getWebp() {
            return webp;
        }

        public void setWebp(String webp) {
            this.webp = webp;
        }
    }

    public Long getId_dog() {
        return id_dog;
    }

    public void setId_dog(Long id_dog) {
        this.id_dog = id_dog;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public DogEntity() {
    }

    public DogEntity(Long id_dog, Image image, int statusCode, String title, String url, boolean active) {
        this.id_dog = id_dog;
        this.image = image;
        this.statusCode = statusCode;
        this.title = title;
        this.url = url;
        this.active = active;
    }

    @Override
    public String toString() {
        return "DogEntity{" +
                "id_dog=" + id_dog +
                ", image=" + image +
                ", statusCode=" + statusCode +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", active=" + active +
                '}';
    }
}

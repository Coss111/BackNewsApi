package ucb.edu.ejercicioApi.dto;

public class ImageDto {
    private String avif;
    private String jpg;
    private String jxl;
    private String webp;


    public ImageDto() {
    }

    public ImageDto(String avif, String jpg, String jxl, String webp) {
        this.avif = avif;
        this.jpg = jpg;
        this.jxl = jxl;
        this.webp = webp;
    }

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

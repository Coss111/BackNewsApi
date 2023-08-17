package ucb.edu.ejercicioApi.dto;

import java.io.Serializable;

public class NewsDto implements Serializable {
    private Long id_news;
    private String news;
    private String status;

    public NewsDto() {
    }

    public NewsDto(Long id_news, String news, String status) {
        this.id_news = id_news;
        this.news = news;
        this.status = status;
    }

    public Long getId_news() {
        return id_news;
    }

    public void setId_news(Long id_news) {
        this.id_news = id_news;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NewsDto{" +
                "id_news=" + id_news +
                ", news='" + news + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

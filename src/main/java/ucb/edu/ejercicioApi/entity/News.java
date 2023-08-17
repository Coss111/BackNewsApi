package ucb.edu.ejercicioApi.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "news", catalog = "advice_db", schema = "public")
@NamedQueries({
        @NamedQuery(name = "news.findAll", query = "SELECT d FROM News d"),
})

public class News implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_news")
    private Long id_news;

    @Basic(optional = false)
    @Column(name = "news")
    private String news;

    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    public News() {
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
        return "News{" +
                "id_news=" + id_news +
                ", news='" + news + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

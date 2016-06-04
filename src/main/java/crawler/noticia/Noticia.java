package crawler.noticia;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.net.URL;
import java.util.List;

/**
 * Created by rafa on 04/06/2016.
 */
@Getter
@Setter
@Entity
public class Noticia {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
   // private List<String> tags;
    private String titulo;
    private String texto;
    private URL imagem;
}

package model.noticia;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;

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
    private String titulo;
    @Column( length = 100000 )
    private String texto;
    private URL imagem;
    private String tags;
}

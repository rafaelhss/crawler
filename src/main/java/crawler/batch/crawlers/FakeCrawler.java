package crawler.batch.crawlers;

import model.noticia.Noticia;
import org.springframework.stereotype.Controller;

/**
 * Created by rafa on 04/06/2016.
 */
@Controller
public class FakeCrawler extends Crawler {
    public FakeCrawler() {

        Noticia n = new Noticia();
        n.setTitulo("Noticia fake 1");
        n.setTexto("Texto noticia fake 1 Texto noticia fake 1 Texto noticia fake 1 Texto noticia fake 1 Texto noticia fake 1 Texto noticia fake 1 Texto noticia fake 1 Texto noticia fake 1 Texto noticia fake 1 ");
        result.add(n);


        Noticia n2 = new Noticia();
        n2.setTitulo("Noticia fake 2");
        n2.setTexto("Texto noticia fake 2 Texto noticia fake 2 Texto noticia fake 2 Texto noticia fake 2 Texto noticia fake 2");
        result.add(n2);

        Noticia n3 = new Noticia();
        n3.setTitulo("Noticia fake 3");
        n3.setTexto("Texto noticia fake 3 Texto noticia fake 3 Texto noticia fake 3 Texto noticia fake 3 Texto noticia fake 3 ");
        result.add(n3);


    }

}

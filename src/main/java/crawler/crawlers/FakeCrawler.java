package crawler.crawlers;

import crawler.noticia.Noticia;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafa on 04/06/2016.
 */
@Controller
public class FakeCrawler implements ItemReader<Noticia> {

    private List<Noticia> result = new ArrayList<Noticia>();

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

    @Override
    public Noticia read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        Noticia n = null;
        try {
            n = result.get(0);
            result.remove(0);
        } catch (Exception e) {
        }


        return n;
    }
}

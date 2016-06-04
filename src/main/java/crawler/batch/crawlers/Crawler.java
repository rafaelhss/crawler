package crawler.batch.crawlers;

import model.noticia.Noticia;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafa on 04/06/2016.
 */
public class Crawler implements ItemReader<Noticia> {
    protected List<Noticia> result = new ArrayList<Noticia>();

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

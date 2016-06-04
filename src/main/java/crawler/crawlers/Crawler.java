package crawler.crawlers;

import crawler.noticia.Noticia;
import org.springframework.batch.item.ItemReader;

import java.util.List;

/**
 * Created by rafa on 04/06/2016.
 */
public abstract class Crawler implements ItemReader {
    public abstract List<Noticia> buscarNovasNoticias();
}

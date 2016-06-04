package crawler.batch.crawlers;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import model.noticia.Noticia;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafa on 04/06/2016.
 */
public class GloboEsporteCrawler extends Crawler {

    public GloboEsporteCrawler() {

        boolean ok = false;
        try {
            URL feedUrl = new URL("http://globoesporte.globo.com/servico/semantica/editorias/plantao/futebol/times/flamengo/feed.rss");

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));


            for (SyndEntry entry : feed.getEntries()) {
                Noticia n = new Noticia();

                List<String> tags = new ArrayList<String>();
                tags.add("futebol");
                tags.add("flamengo");
                tags.add("esporte");

               // n.setTags(tags);

                n.setTitulo(entry.getTitle());
                try {
                    n.setTexto(entry.getDescription().toString().substring(entry.getDescription().toString().indexOf("<br />")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                result.add(n);
            }


            ok = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: " + ex.getMessage());
        }

        if (!ok) {
            System.out.println();
            System.out.println("FeedReader reads and prints any RSS/Atom feed type.");
            System.out.println("The first parameter must be the URL of the feed to read.");
            System.out.println();
        }
    }

}

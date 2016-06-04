package crawler;

import crawler.crawlers.FakeCrawler;
import crawler.crawlers.GloboEsporteCrawler;
import crawler.noticia.Noticia;
import crawler.noticia.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * Created by rafa on 04/06/2016.
 */

@Controller
public class CrawlerRunner {


    @Autowired
    private NoticiaRepository noticiaRepository;
    public void crawl(){
/*
        for (Noticia n : new FakeCrawler().buscarNovasNoticias()){
            System.out.println("Fake: " + n.getTitulo());
            noticiaRepository.save(n);

        }
*/
        for (Noticia n : new GloboEsporteCrawler().buscarNovasNoticias()){
            System.out.println("globo: " + n.getTitulo());
        }

        for(Noticia n: noticiaRepository.findAll()){
            System.out.println("Noticias no bd:" + n.getTitulo());
        }

    }


}

package crawler.noticia.repository;

import crawler.noticia.Noticia;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rafa on 04/06/2016.
 */
@Repository
public class NoticiaWriter implements ItemWriter<Noticia> {
    @Autowired
    NoticiaRepository noticiaRepository;

    @Override
    public void write(List<? extends Noticia> list) throws Exception {
        noticiaRepository.save(list);
    }
}

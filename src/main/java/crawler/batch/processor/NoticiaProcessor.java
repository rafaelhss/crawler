package crawler.batch.processor;

/**
 * Created by rafa on 04/06/2016.
 */

import model.noticia.Noticia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class NoticiaProcessor implements ItemProcessor<Noticia, Noticia> {

    private static final Logger log = LoggerFactory.getLogger(NoticiaProcessor.class);

    @Override
    public Noticia process(final Noticia noticia) throws Exception {

        final Noticia transformed = new Noticia();
        transformed.setTitulo("Transf: " + noticia.getTitulo());

        return transformed;
    }

}
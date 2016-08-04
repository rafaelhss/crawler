package downloader.batch.hardmob.processor;

import downloader.batch.hardmob.model.HardMobPromo;
import model.noticia.Noticia;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by rafa on 04/08/2016.
 */
public class HardMobProcessor  implements ItemProcessor<HardMobPromo, Noticia> {
    @Override
    public Noticia process(HardMobPromo hardMobPromo) throws Exception {
        //Faz o download do corpo do link e monta uma noticia

        Noticia n = new Noticia();
        n.setTitulo(hardMobPromo.getTitulo());
        n.setTexto("link:[" + hardMobPromo.getUrl().toString() + "]:" +  hardMobPromo.getDesc());
        n.setTags("hardmob, promocoes");
        return n;
    }
}

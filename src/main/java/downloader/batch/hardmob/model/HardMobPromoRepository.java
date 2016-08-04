package downloader.batch.hardmob.model;

import model.noticia.Noticia;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rafa on 04/08/2016.
 */
public interface HardMobPromoRepository extends CrudRepository<Noticia, Long> {
}

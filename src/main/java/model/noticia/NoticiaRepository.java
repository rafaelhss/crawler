package model.noticia;

import model.noticia.Noticia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rafa on 04/06/2016.
 */
@Repository
public interface NoticiaRepository extends CrudRepository<Noticia, Long> {

    //List<Noticia> findByLastName(String lastName);
}
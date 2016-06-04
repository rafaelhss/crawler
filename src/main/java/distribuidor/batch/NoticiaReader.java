package distribuidor.batch;

import model.noticia.Noticia;
import model.noticia.NoticiaRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafa on 04/06/2016.
 */
@Repository
public class NoticiaReader implements ItemReader<Noticia> {
    protected List<Noticia> result = null;

    @Autowired
    private NoticiaRepository noticiaRepository;

    public Noticia read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(result == null) {
            result = (List<Noticia>) noticiaRepository.findAll();
        }

        Noticia n = null;
        try {
            n = result.get(0);
            result.remove(0);
        } catch (Exception e) {
        }


        return n;
    }
}

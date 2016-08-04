package downloader.batch.hardmob.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.net.URL;

/**
 * Created by rafa on 04/08/2016.
 */
@Getter
@Setter
@AllArgsConstructor
public class HardMobPromo {
    private String titulo;
    private URL url;
    private String desc;
}

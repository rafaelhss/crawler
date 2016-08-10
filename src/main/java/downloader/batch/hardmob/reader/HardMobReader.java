package downloader.batch.hardmob.reader;

import downloader.batch.hardmob.model.HardMobPromo;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository
public class HardMobReader implements ItemReader<HardMobPromo> {

    private String PROMO_DELIMITER = "class=\"threadinfo\"";
    private String TITLE = "class=\"title";
    private String HREF = "href=\"";
    private String DESC = "class=\"threaddesc\">";
    private String URL = "http://www.hardmob.com.br/promocoes/";

    private List<HardMobPromo> hardMobPromos = new ArrayList<HardMobPromo>();

    public HardMobReader() {
        String out = "";
        try {
            // get URL content
            URL url = new URL(URL);
            URLConnection conn = url.openConnection();



            conn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.setRequestProperty("Accept-Language","pt-BR,pt;q=0.8,en-US;q=0.6,en;q=0.4");
            conn.setRequestProperty("Cache-Control","max-age=0");
            conn.setRequestProperty("Connection","keep-alive");
            conn.setRequestProperty("Host","www.hardmob.com.br");
            conn.setRequestProperty("Upgrade-Insecure-Requests","1");
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36*/");




            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;

            //use FileWriter to write file

            while ((inputLine = br.readLine()) != null) {
                out += inputLine;
            }

            System.out.println("Busquei a URL. Tamanho:" + out.length());
        } catch (Exception e) {
            System.out.println("Erro buscando url:" + e.getMessage());
            e.printStackTrace();
            System.out.println("usando arquivo");
            try {
                out = new String(Files.readAllBytes(Paths.get("C:\\Users\\rafa\\Documents\\Projects\\ContentProvider\\NewsCrawler\\temp\\hardmob.txt")));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


        String[] promos = out.split(PROMO_DELIMITER);

        for (String promo : promos) {
            String link = "", title = "", desc = "";
            if (promo.indexOf(TITLE) > 0) {
                String aux = promo.substring(promo.indexOf(TITLE) + TITLE.length());
                if (aux.indexOf(HREF) > 0) {
                    aux = aux.substring(aux.indexOf(HREF) + HREF.length());
                    link = aux.substring(0, aux.indexOf("\""));
                    if (aux.indexOf(">") > 0) {
                        aux = aux.substring(aux.indexOf(">") + ">".length());
                        title = aux.substring(0, aux.indexOf("</a>"));
                    }
                    try {
                        if (aux.indexOf(DESC) > 0) {
                            aux = aux.substring(aux.indexOf(DESC) + DESC.length());
                            desc = aux.substring(0, aux.indexOf("</p>"));
                        }
                    } catch (Exception e){
                        System.out.println("Erro ao pegar descricao: " + e.getMessage());
                    }
                }
            }
            System.out.println("Promo:" + title + " - " + link);
            if (!promo.isEmpty() && !link.isEmpty()) {
                try {
                    hardMobPromos.add(new HardMobPromo(title, new URL(link), desc));
                } catch (Exception e) {
                    System.out.println("Erro ao criar promo:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }


    }


    public HardMobPromo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {


        //TODO retorna um link de promo por chamada
        //deve  popular a tabela temporaria no construtor e depois ir pegando um a um.
        if (hardMobPromos.size() > 0) {
            HardMobPromo promo = hardMobPromos.get(0);
            if (promo != null) {
                hardMobPromos.remove(0);
            }
            return promo;
        }
        else {
            return null;
        }
    }


    public static boolean DownloadHTML(String url, String fileDestination) {
        URL website;
        for (int i = 0; i < 3; i++) {
            try {
                website = new URL(url);
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream(fileDestination);
                Long bytes = fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                return (bytes > 1024); // quando nao existe retorna 414 bytes de lixo. 1024 por seguran�a

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Tentativa: " + i + "Erro au buscar url:" + url);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return true; // desisti
        //
        // try
        // {
        // PDFDownloader.DownloadPDF(urljp, filejp);
        // } catch (ConnectException ce)
        // {
        // // retentar !!
        // ce.printStackTrace();
        // j--;
        // } catch (SocketException se)
        // {
        // // retentar !!
        // se.printStackTrace();
        // j--;
        // }

    }
}

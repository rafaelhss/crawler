package mail;


import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.multipart.FormDataMultiPart;
import model.noticia.Noticia;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import mail.ConfigProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by deinf.rsoares on 17/06/2016.
 */
public class EmailDispatcher {


    private static final String baseURL = "https://api.mailgun.net/v2/";

    private static String mailgunAPIKey = ConfigProvider.getKey();

    private static <T> WebTarget createPrivateClient() {
        final Client client = ClientBuilder.newClient();
        client.register(HttpAuthenticationFeature.basic("api", mailgunAPIKey));
        return client.target(baseURL);
    }

    protected static void fireMailGun(final MultivaluedMap<String, String> postData) {
        createPrivateClient().path(ConfigProvider.getDomain() + "/messages")
                .request()
                .post(Entity.form(postData));
    }


    public static void SendSimpleMessage(String destination, Noticia noticia) {
        //Client client = Client.create();

        //client.addFilter(new HTTPBasicAuthFilter("api", ConfigProvider.getKey()));
        //String url = "https://api.mailgun.net/v3/"+ ConfigProvider.getDomain() + "/messages";

        //System.out.println(url);
        //WebResource webResource =  client.resource(url);

        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Teste doido <web.zap@"+ConfigProvider.getDomain()+">");
        formData.add("to", destination);
        //formData.add("to", "rafael.soares@bcb.gov.br");
        formData.add("subject", "chegou uma promo!");
        //formData.add("text", "H2i! Your network was generated and is ready to be visualized ");

        formData.add("html", "<html>Opa!! funciona doido! mais uma promo no hardmob detectada: <strong>" +
                "Titulo: " + noticia.getTitulo() + " <br/>" +
                "Texto: " + noticia.getTexto());

        fireMailGun(formData);

        System.out.println("Mandei mail");


    }
}

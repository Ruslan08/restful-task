import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task.Main;
import task.model.Account;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

public class OperationsTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() {
        server = Main.startServer();

        Client client = ClientBuilder.newClient();
        target = client.target(Main.BASE_URL);

        Account account1 = new Account(100, 925);
        Account account2 = new Account(200, 1450);

        Main.DATA_SOURCE.put(account1.getId(), account1);
        Main.DATA_SOURCE.put(account2.getId(), account2);
    }

    @After
    public void tearDown() {
        server.shutdownNow();
    }

    @Test
    public void transferTest() {
        target.path("/transfer")
                .queryParam("firstAccountId", 100)
                .queryParam("secondAccountId", 200)
                .queryParam("amount", 500)
                .request().get(String.class);

        Double balance1 = target.path("/balance/100").request().get(Double.class);
        Double balance2 = target.path("/balance/200").request().get(Double.class);

        assertEquals(425, balance1, 1);
        assertEquals(1950, balance2, 1);
    }

}

package task;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import task.model.Account;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static final String BASE_URL = "http://localhost:8080/task/";
    public static final Map<Long, Account> DATA_SOURCE = new HashMap<>();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws IOException {
        HttpServer server = startServer();
        fillDataSource();
        System.out.println("Enter to stop...");
        System.in.read();
        server.shutdownNow();
    }

    public static HttpServer startServer() {
        ResourceConfig rc = new ResourceConfig().packages("task/operations");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URL), rc);
    }

    private static void fillDataSource() {
        Account account1 = new Account(100, 500);
        Account account2 = new Account(200, 700);

        DATA_SOURCE.put(account1.getId(), account1);
        DATA_SOURCE.put(account2.getId(), account2);
    }
}

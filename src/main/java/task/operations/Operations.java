package task.operations;

import task.Main;
import task.model.Account;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.stream.Stream;


@Path("/")
public class Operations {

    @GET
    @Path("/transfer")
    public Response transfer(@QueryParam("firstAccountId") long id1,
                             @QueryParam("secondAccountId") long id2,
                             @QueryParam("amount") double amount)
    {
        Account fromAcc = Main.DATA_SOURCE.get(id1);
        Account toAcc = Main.DATA_SOURCE.get(id2);

        Account firstLock = Stream.of(fromAcc, toAcc).min(Comparator.comparing(Account::getId)).get();
        Account secondLock = Stream.of(fromAcc, toAcc).max(Comparator.comparing(Account::getId)).get();

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (fromAcc.getBalance() < amount) {
                    return Response
                            .status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("Account does not have enough money")
                            .build();
                }
                fromAcc.withdraw(amount);
                toAcc.deposit(amount);
            }
        }
        return Response.ok("Transfer success").build();
    }

    @GET
    @Path("/balance/{accountId}")
    public Response balance(@PathParam("accountId") final long accountId) {
        return Response.ok(Main.DATA_SOURCE.get(accountId).getBalance()).build();
    }

}

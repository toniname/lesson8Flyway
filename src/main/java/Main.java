import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    Logger logger = Logger.getLogger(getClass().getName());
    public static void main(String[] args) throws IOException, SQLException {
        Database database = Database.getInstance();
        new DatabaseInitServise().initDb();
        ClientService clientService = new ClientService(database.getConnection());
        List<Client> clients = clientService.listAll();
        Logger.getLogger("clients = " + clients);

    }
}

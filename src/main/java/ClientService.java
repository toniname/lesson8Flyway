import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private PreparedStatement createStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement selectMaxIdStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteByIdStatement;
    private PreparedStatement getAllStatement;

    public ClientService(Connection connection){
        try {
            createStatement = connection.prepareStatement(
                    "INSERT INTO client (name) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            getByIdStatement = connection.prepareStatement(
                    "SELECT name FROM client WHERE id = ?"
            );

            selectMaxIdStatement = connection.prepareStatement(
                    "SELECT max(id) AS maxId FROM client"
            );

            updateStatement = connection.prepareStatement(
                    "UPDATE client SET name = ? WHERE id = ?"
            );

            deleteByIdStatement = connection.prepareStatement(
                    "DELETE FROM client WHERE id = ?"
            );

            getAllStatement = connection.prepareStatement(
                    "SELECT id, name FROM client"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long create(String name) throws SQLException {
        checkIfNameValid(name);
        createStatement.setString(1, name);

        createStatement.executeUpdate();

        long id;

        try (ResultSet generatedKeys = createStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating client failed, no ID obtained.");
            }
        }

        return id;
    }

    public Client getById(long id) throws SQLException {
        getByIdStatement.setLong(1, id);

        try (ResultSet rs = getByIdStatement.executeQuery()) {
            if (!rs.next()) {
                return null;
            }

            Client result = new Client();
            result.setId(id);
            result.setName(rs.getString("name"));

            return result;
        }
    }

    public void setName(long id, String name) throws SQLException {
        checkIfNameValid(name);
        updateStatement.setString(1, name);
        updateStatement.setLong(2, id);

        updateStatement.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdStatement.setLong(1, id);

        deleteByIdStatement.executeUpdate();
    }

    public List<Client> listAll() throws SQLException {
        try (ResultSet rs = getAllStatement.executeQuery()) {
            List<Client> result = new ArrayList<>();
            while (rs.next()) {
                Client client = new Client();

                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));

                result.add(client);
            }
            return result;
        }
    }

    private void checkIfNameValid(String name) {
        if (name.length() < 2) {
            throw new InvalidNameLengthException("Name too short!");
        } else if (name.length() > 1000) {
            throw new InvalidNameLengthException("Name too long!");
        }
    }

}
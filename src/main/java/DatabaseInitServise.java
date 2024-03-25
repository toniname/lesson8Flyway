import org.flywaydb.core.Flyway;



public class DatabaseInitServise {
    public void initDb() {
        String connectionUrl = new Preferences().getPref("jdbc:mysql.db.database");
        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();
        flyway.migrate();
    }

}


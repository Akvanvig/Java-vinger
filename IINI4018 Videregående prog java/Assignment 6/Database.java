import java.sql.*;
private Connection forbindelse;

class Database {
    public Database(String dbNavn) throws SQLException {
        String dbDriver = "com.mysql.jdbc.Driver";
        forbindelse = DriverManager.getConnection(dbNavn);
    }

    public void lukkForbindelse() {
        forbindelse.close();
    }
}

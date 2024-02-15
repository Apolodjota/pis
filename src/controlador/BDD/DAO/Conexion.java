package controlador.BDD.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class Conexion {
    //https://www.codejava.net/java-se/jdbc/connect-to-oracle-database-via-jdbc
    private Connection connection;
    // Librería de MySQL
    //public String driver = "com.mysql.cj.jdbc.Driver";//oracle.jdbc.driver.OracleDriver
    public String driver = "oracle.jdbc.driver.OracleDriver";//ORACLE
    
    // Nombre de la base de datos
    public String database = "XE";

    // Host
    public String hostname = "localhost";

    // Puerto
    public String port = "1521";//1521...3306

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:oracle:thin:@"+hostname+":"+port+":"+database;
    //public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";//"jdbc:oracle:thin:@"+hostname+":"+port+":"+database;

    // Nombre de usuario
    public String username = "pis";

    // Clave de usuario
    public String password = "gerardo";

    private Connection conectar() {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conected!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public Connection getConnection() {
        if(connection == null)
            connection = conectar();
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

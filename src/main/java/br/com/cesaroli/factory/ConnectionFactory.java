package br.com.cesaroli.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ConnectionFactory {


    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("src/main/resources/db.properties")) {
            Properties props = new Properties();
            props.load(fs);

            return props;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo de propriedades do banco de dados" , e);
        }
    }

    public static Connection getConnection() {
        try {
            Properties props = loadProperties();
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {

            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}

package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private JDBC() {
    }

    static QueryRunner runner = new QueryRunner();
    @SneakyThrows
    public static Connection getConnection() {
        String dbUrl = System.getProperty("db.url");
        String user = System.getProperty("db.user");
        String password = System.getProperty("db.password");
        final Connection connection = DriverManager.getConnection(dbUrl, user, password);
        return connection;
    }

    public static String getPaymentStatus() {
        String codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConnection()) {
            var status = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return status;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCreditStatus() {
        String codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConnection()) {
            var status = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return status;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
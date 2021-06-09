package data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLData {
    private final static QueryRunner runner = new QueryRunner();
    private final static Connection conn = getConnection();

    public SQLData() {
    }

    @SneakyThrows
    private static Connection getConnection() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "user", "pass");
    }

    @SneakyThrows
    public static String getVerificationCode(String user) {
        val code = "SELECT code FROM auth_codes INNER JOIN users ON auth_codes.user_id = users.id WHERE login \"" + user + "\" ORDER BY created DESC";
        return runner.query(conn, code, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanCodes() {
        runner.execute(conn, "DELETE from app.auth_codes;");
        runner.execute(conn, "DELETE from app.cards;");
        runner.execute(conn, "DELETE from app.users;");
        runner.execute(conn, "DELETE from app.card_transactions;");
    }


}

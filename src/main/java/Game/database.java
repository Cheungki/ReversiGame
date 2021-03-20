package Game;

import java.sql.*;

public class database {
    public static final String url = "jdbc:mysql://localhost:3306/gameSys";
    public static final String user = "root";
    public static final String password = "zhang123qi";
    public static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";

    public synchronized static boolean login(String ID, String pwd) {
        try {
            Class.forName(jdbc_driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("select * from userinfo where id = \"%s\" and pwd = \"%s\"", ID, pwd));
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized static void createAccount(String ID, String pwd) {
        try {
            Class.forName(jdbc_driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.execute(String.format("insert into userinfo values(\"%s\", \"%s\", 0)", ID, pwd));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static boolean checkUnique(String ID) throws Exception {
        try {
            Class.forName(jdbc_driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("select * from userinfo where id = \"%s\"", ID));
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized static int getScore(String ID) throws Exception {
        try {
            Class.forName(jdbc_driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("select * from userinfo where id = \"%s\"", ID));
            rs.next();
            return rs.getInt("score");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public synchronized static void updateScore(String ID, boolean isWin) throws Exception {
        try {
            Class.forName(jdbc_driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("select score from userinfo where id = \"%s\"", ID));
            rs.next();
            int score = rs.getInt("score");
            stmt.execute(String.format("update userinfo set score %d", score + (isWin ? 1 : -1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

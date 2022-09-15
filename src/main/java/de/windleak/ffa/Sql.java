package de.windleak.ffa;

import java.sql.*;

public class Sql {
    Connection con;
    String name;

    public Sql(String name2) {
        name = name2;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + name, "root", "3V61M92HO8");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void open() {
        try {
            if (con.isClosed()) {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + name, "root", "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (!con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param name         Name der Tabelle
     * @param createString Text, der zum Erstellen benutzt wird ("create table name(createString)")
     * @return RÃ¼ckgabe des SQL
     */
    public ResultSet createTable(String name, String createString) {
        String create = " IF object_id('" + name + "', 'U') is not null BEGIN create table " + name + "(" + createString + ");END";
        return execute(create, false);
    }

    public ResultSet execute(String ex, Boolean isQuery) {
        System.out.println("executing \"" + ex + '"');
        try {
            Statement stmt = con.createStatement();
            if (isQuery) {
                return stmt.executeQuery(ex);
            } else {
                stmt.execute(ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printExecution(String ex, Boolean isQuery) {
        if (isQuery) {
            ResultSet rs = execute(ex, true);
            Sql.printResultSet(rs);
        } else {
            execute(ex, false);
        }
    }

    public static void printResultSet(ResultSet rs) {
        try {
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(metadata.getColumnName(i) + ", ");
            }
            System.out.println();
            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(rs.getString(i)).append(", ");
                }
                System.out.println();
                System.out.println(row);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

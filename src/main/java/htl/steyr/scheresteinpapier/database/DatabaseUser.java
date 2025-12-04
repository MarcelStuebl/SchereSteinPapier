package htl.steyr.scheresteinpapier.database;

import htl.steyr.scheresteinpapier.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUser {


    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, vorname, nachname FROM user";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt. executeQuery(query)) {

            while (rs.next()) {
                User user = new User(
                        rs. getInt("id"),
                        rs. getString("vorname"),
                        rs. getString("nachname")
                );
                users.add(user);
            }
        }
        return users;
    }


    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user (vorname, nachname) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt. setString(1, user.getVorname());
            pstmt.setString(2, user. getNachname());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        }
    }





}

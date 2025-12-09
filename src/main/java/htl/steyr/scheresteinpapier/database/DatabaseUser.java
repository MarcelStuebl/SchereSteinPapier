package htl.steyr.scheresteinpapier.database;

import htl.steyr.scheresteinpapier.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUser {


    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, vorname, nachname, highscore FROM user";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("vorname"),
                        rs.getString("nachname"),
                        rs.getInt("highscore")
                );
                users.add(user);
            }
        }
        return users;
    }


    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user (vorname, nachname, highscore) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getVorname());
            pstmt.setString(2, user.getNachname());
            pstmt.setInt(3, user.getHighscore());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        }
    }


    public void updateUserScore(User user) throws SQLException {
        String query = "UPDATE user SET highscore = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, user.getHighscore());
            pstmt.setInt(2, user.getId());
            pstmt.executeUpdate();
        }
    }







}

package htl.steyr.scheresteinpapier.database;

import htl.steyr.scheresteinpapier.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUser {


    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, username, highscore FROM user";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("highscore")
                );
                users.add(user);
            }
        }
        return users;
    }

    public User getUser(String username) throws SQLException {
        String query = "SELECT id, username, highscore FROM user WHERE username = ?";

        User user = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getInt("highscore")
                    );
                }
            }
        }
        return user;
    }

    public User getBestUser() throws SQLException {
        String query = "SELECT id, username, highscore FROM user ORDER BY highscore DESC LIMIT 1";

        User user = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getInt("highscore")
                    );
                }
            }
        }
        return user;
    }

    public List<User> getBestUsers(int limit) throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, username, highscore FROM user ORDER BY highscore DESC LIMIT ? ";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, limit);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getInt("highscore")
                    );
                    users.add(user);
                }
            }
        }
        return users;
    }


    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO user (username, highscore) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setInt(2, user.getHighscore());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
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

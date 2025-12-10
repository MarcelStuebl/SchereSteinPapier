package htl.steyr.scheresteinpapier.database;

import htl.steyr.scheresteinpapier.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUser {

    /**
     * Retrieves a user by their username from the database.
     * @param username The username of the user to retrieve.
     * @return A User object representing the user, or null if not found.
     * @throws SQLException if a database access error occurs.
     */
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


    /**
     * Retrieves the user with the highest score from the database.
     * @return A User object representing the user with the highest score.
     * @throws SQLException if a database access error occurs.
     */
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


    /**
     * Retrieves the top users based on their high scores.
     * @param limit The maximum number of users to retrieve.
     * @return A list of User objects representing the top users.
     * @throws SQLException if a database access error occurs.
     */
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


    /**
     * Adds a new user to the database.
     * @param user The User object to be added.
     * @throws SQLException if a database access error occurs.
     */
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


    /**
     * Updates the high score of an existing user in the database.
     * @param user The User object with updated high score.
     * @throws SQLException if a database access error occurs.
     */
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





package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.User;

import static dao.ConnectionProvider.getConnection;

public class UserDao {

    private static UserDao userDao;

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    private User map(ResultSet rs) {
        User user = null;
        try {
            user = new User();
            user.setId(rs.getInt("Id"));
            user.setEmail(rs.getString("Email"));
            user.setFullName(rs.getString("FullName"));
            user.setSex(rs.getString("Sex"));
            user.setBirthDate(rs.getDate("BirthDate").toLocalDate());
            user.setPhoneNumber(rs.getString("PhoneNumber"));
            user.setAddress(rs.getString("Address"));
            user.setSecurityQuestion(rs.getString("SecurityQuestion"));
            user.setAnswer(rs.getString("Answer"));
            user.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
            user.setApproved(rs.getBoolean("IsApproved"));
            return user;
        } catch (Exception ex) {
            return null;
        }
    }

    public User getById(int userId) {
        try {
             String query = "SELECT * FROM [User] WHERE Id = ?;";
            ResultSet rs = DbOperations.getData(query, new Object[]{userId});

            User user = null;
            if (rs.next()) {
                user = map(rs);
            }
            return user;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

    public User getByEmail(String userEmail) {
        try {
            String query = "SELECT * FROM [User] WHERE Email = ?;";
            ResultSet rs = DbOperations.getData(query, new Object[]{userEmail});

            User user = null;
            if (rs.next()) {
                user = map(rs);
            }
            return user;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

    public static void updateData(String query, Object[] args, String successMessage) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, successMessage);
        } catch (SQLException e) {
            throw e; // Ném ngoại lệ để phương thức gọi nó có thể xử lý
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác (không phải SQLException)
            throw new SQLException("An unexpected error occurred", e);
        }
    }


    public void create(User user) {
        String query = "INSERT INTO [User] (Email, Password, FullName, Sex, BirthDate, PhoneNumber, Address, SecurityQuestion, Answer) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                user.getEmail(),
                user.getPassword(),
                user.getFullName(),
                user.getSex(),
                user.getBirthDate().toString(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getSecurityQuestion(),
                user.getAnswer()
        };
        int result = DbOperations.updateData(query, args, "");

        if (result == -1) {
            JOptionPane.showMessageDialog(null, "Not old enough to register");
        } else {
            JOptionPane.showMessageDialog(null, "Registered successfully!");
        }
    }

    public void update(User user) {
        String query = "UPDATE [User] SET FullName = ?, Sex = ?, BirthDate = ?, Email = ?, PhoneNumber = ?, Address = ? WHERE Id = ?";
        Object[] args = {
                user.getFullName(),
                user.getSex(),
                user.getBirthDate().toString(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getId()
        };

        DbOperations.updateData(query, args, "User profile updated successfully!");
    }

    public User login(String email, String password) {
        User user = null;
        try {
            String query = "SELECT * FROM [User] WHERE Email = ? AND Password = ?";
            Object[] args = {email, password};

            ResultSet rs = DbOperations.getData(query, args);
            while (rs.next()) {
                user = new User();
                user.setApproved(rs.getBoolean("IsApproved"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }

    public void lockUserByEmail(String email) {
        if (!email.equalsIgnoreCase("admin@gmail.com") && !email.equalsIgnoreCase("duy@gmail.com") && !email.equalsIgnoreCase("huy@gmail.com")) {
            try {
                String query = "UPDATE [User] SET IsApproved = 0 WHERE Email = ?";
                Object[] args = {email};
                DbOperations.updateData(query, args,"");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public boolean checkUserIfBanned(String email) {
        if (email.equalsIgnoreCase("admin@gmail.com") || email.equalsIgnoreCase("duy@gmail.com") || email.equalsIgnoreCase("huy@gmail.com"))
            return false;

        try {
            String query = "SELECT IsApproved FROM [User] WHERE Email = ?";
            Object[] args = {email};

            ResultSet rs = DbOperations.getData(query, args);
            if (rs.next()) {
                boolean isApproved = rs.getBoolean("IsApproved");
                return !isApproved; // Trả về true nếu bị banned (IsApproved = 0), ngược lại false
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }


    public static User getSecurityQuestion(String email) {
        User user = null;
        try {
            ResultSet rs = DbOperations.getData("SELECT * FROM [User] WHERE Email = ?", new Object[]{email});
            while (rs.next()) {
                user = new User();
                user.setSecurityQuestion(rs.getString("SecurityQuestion"));
                user.setAnswer(rs.getString("Answer"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }

    public static void updatePassword(String email, String newPassword) {
        String query = "UPDATE [User] SET Password = ? WHERE Email = ?";
        Object[] args = {newPassword, email};

        DbOperations.updateData(query, args, "Password changed successfully!");
    }

    public List<User> findByEmail(String email) {
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM [User] WHERE Email LIKE ?";
        Object[] args = {"%" + email + "%"};

        try {
            ResultSet rs = DbOperations.getData(query, args);
            while (rs.next()) {
                User user = map(rs);
                users.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return users;
    }

    public void changeApproved(String email, boolean approved) {
        String query = "UPDATE [User] SET IsApproved = ? WHERE Email = ?";
        Object[] args = {approved, email};
        DbOperations.updateData(query, args, "Status changed successfully!");
    }

    public void changePassword(String email, String oldPassword, String newPassword) {
        try {
            String query = "SELECT * FROM [User] WHERE Email = ? AND Password = ?";
            Object[] args = {email, oldPassword};

            ResultSet rs = DbOperations.getData(query, args);

            if (rs.next()) {
                updatePassword(email, newPassword);
            } else {
                JOptionPane.showMessageDialog(null, "Old password is wrong!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void changeSecurityQuestion(String email, String password, String securityQuestion, String answer) {
        try {
            String query = "SELECT * FROM [User] WHERE Email = ? AND Password = ?";
            Object[] args = {email, password};

            ResultSet rs = DbOperations.getData(query, args);

            if (rs.next()) {
                query = "UPDATE [User] SET SecurityQuestion = ?, Answer = ? WHERE Email = ?";
                args = new Object[]{securityQuestion, answer, email};

                DbOperations.updateData(query, args, "Security question changed successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Password is wrong!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}


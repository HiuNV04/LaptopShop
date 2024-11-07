package dal;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class UserDAO extends DBContext {

    public User login(String inputUsername, String inputPassword) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select * from [user] where username = ? and password = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, inputUsername);
            stm.setString(2, inputPassword);
            rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();

                u.setId(rs.getInt("id"));
                u.setUsername(inputUsername);
                u.setPassword(inputPassword);
                u.setFullname(rs.getString("fullname"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setRole_id(rs.getInt("role_id"));
                u.setBanned(rs.getInt("banned"));
                System.out.println(u);
                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User register(User user) {
        PreparedStatement stm = null;

        String sql = "INSERT INTO [dbo].[user]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[fullname]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[role_id])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            if (user.getUsername() != null && !user.getUsername().isEmpty()
                    && user.getPassword() != null && !user.getPassword().isEmpty()
                    && user.getRole_id() != 0) {
                stm = connection.prepareStatement(sql);
                stm.setString(1, user.getUsername());
                stm.setString(2, user.getPassword());
                stm.setString(3, user.getFullname());
                stm.setString(4, user.getEmail());
                stm.setString(5, user.getPhone());
                stm.setString(6, user.getAddress());
                stm.setInt(7, user.getRole_id());
                stm.executeUpdate();

                System.out.println("Click here to login");
            } else {
                System.out.println("Insert failed: Invalid data");
            }
        } catch (SQLException ex) {
            System.out.println("Insert failed");
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public Vector<User> getAllUser() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<User> users = new Vector<>();
        String sql = "select * from [user]";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int role_id = rs.getInt("role_id");

                User u = new User(id, username, password, fullname, email, phone, address, role_id);
                users.add(u);
            }
            return users;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertUser(User user) {
        PreparedStatement stm = null;

        String sql = "INSERT INTO [dbo].[user]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[fullname]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[role_id])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getFullname());
            stm.setString(4, user.getEmail());
            stm.setString(5, user.getPhone());
            stm.setString(6, user.getAddress());
            stm.setInt(7, user.getRole_id());
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserById(int userId) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select * from [user]\n"
                + "where [id] = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            rs = stm.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setRole_id(rs.getInt("role_id"));
                System.out.println(u);
                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //get all customer (role_id = 1)
    public Vector<User> getAllCustomer() {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<User> users = new Vector<>();
        String sql = "select * from [user] where role_id = 1";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int role_id = rs.getInt("role_id");
                int banned = rs.getInt("banned");

                User u = new User(id, username, password, fullname, email, phone, address, role_id, banned);
                users.add(u);
            }
            return users;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Vector<User> getCustomerByName(String name) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Vector<User> customers = new Vector<>();
        String sql = "select * from [user]\n"
                + "where role_id = 1 and [fullname] LIKE ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
                u.setAddress(rs.getString("address"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setRole_id(rs.getInt("role_id"));
                System.out.println(u);

                customers.add(u);
            }
            return customers;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void banAnUser(int userId) {
        PreparedStatement stm = null;

        String sql = "UPDATE [dbo].[user] SET [banned] = 1 WHERE id = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.executeUpdate();

            System.out.println("Banned userId = " + userId);

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserByGoogleEmail(String email) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM [user] WHERE email = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setAddress(rs.getString("address"));
                u.setRole_id(rs.getInt("role_id"));
                u.setBanned(rs.getInt("banned"));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        UserDAO u = new UserDAO();
        String em = "huy04@gmail.com";
        User x = u.getUserByGoogleEmail(em);
        System.out.println(x);
    }

    public void insertUserFromGoogle(User u) {
        PreparedStatement stm = null;
        String sql = "INSERT INTO [dbo].[user]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[fullname]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[role_id]\n"
                + "           ,[banned])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, u.getUsername());
            stm.setString(2, "");// Đặt mật khẩu trống cho tài khoản Google
            stm.setString(3, u.getFullname());
            stm.setString(4, u.getEmail());
            stm.setString(5, u.getPhone());         
            stm.setString(6, u.getAddress());
            stm.setInt(7, u.getRole_id());
            stm.setInt(8, u.getBanned());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

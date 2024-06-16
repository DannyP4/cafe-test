/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Order;
import model.OrderDetails;
import model.Status;
import java.time.LocalDate;
import dao.UserDao;
import model.User;

/**
 *
 * @author Admin
 */
public class OrderDao {

    private static OrderDao orderDao;

    public static OrderDao getInstance() {
        if (orderDao == null) {
            orderDao = new OrderDao();
        }
        return orderDao;
    }

    public List<Order> getOrders(int userId, LocalDate fromDate, LocalDate toDate) {
        try {
            List<Order> orders = new ArrayList<>();

            String query = null;
            Object[] args = null;
            if (fromDate != null && toDate != null) {
                query = "SELECT * FROM [Order] WHERE CONVERT(DATE, CreatedAt) BETWEEN ? AND ? ";
            } else {
                // Nếu fromDate là null, đặt nó thành ngày 1 tháng 1 năm 2000
                if (fromDate == null) {
                    fromDate = LocalDate.of(2000, 1, 1);
                }
                // Nếu toDate là null, đặt nó thành ngày hiện tại
                if (toDate == null) {
                    toDate = LocalDate.now();
                }
                // Truy vấn để lấy ra tối đa 30 đơn hàng giữa fromDate và toDate.
                query = "SELECT TOP 30 * FROM [Order] WHERE CONVERT(DATE, CreatedAt) BETWEEN ? AND ? ";
            }
            if (userId != -1) {
                query += "AND UserId = ? ";
                args = new Object[]{fromDate.toString(), toDate.toString(), userId};
            } else {
                args = new Object[]{fromDate.toString(), toDate.toString()};
            }
            query += "ORDER BY CreatedAt ASC";
            ResultSet rs = DbOperations.getData(query, args);
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("Id"));
                order.setUser(UserDao.getInstance().getById(rs.getInt("UserId")));
                order.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                order.setFinalCost(rs.getDouble("FinalCost"));
                order.setShipper(StaffDao.getInstance().getById(rs.getInt("ShipperId")));
                order.setStatus(getStatusById(rs.getInt("StatusId")));
                orders.add(order);
            }
            return orders;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

    public Order getById(int id) {
        String query = "SELECT * FROM [Order] WHERE Id = ?";
        ResultSet rs = DbOperations.getData(query, new Object[]{id});
        try {
            Order order = null;
            if (rs.next()) {
                order = new Order();
                order.setId(id);
                order.setUser(UserDao.getInstance().getById(rs.getInt("UserId")));
                order.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                order.setTotalCost(rs.getDouble("TotalCost"));
                order.setShipCost(rs.getDouble("ShipCost"));
                order.setDiscount(rs.getDouble("Discount"));
                order.setFinalCost(rs.getDouble("FinalCost"));
                order.setDeliveryInfo(DeliveryInfoDao.getInstance().getById(rs.getInt("DeliveryInfoId")));
                order.setShipper(StaffDao.getInstance().getById(rs.getInt("ShipperId")));
                order.setStatus(getStatusById(rs.getInt("StatusId")));
                order.setItems(new ArrayList<>());

                rs = DbOperations.getData("SELECT * FROM OrderDetails WHERE OrderId = ?", new Object[]{id});
                while (rs.next()) {
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setId(rs.getInt("Id"));
                    orderDetails.setProduct(ProductDao.getInstance().getById(rs.getInt("ProductId")));
                    orderDetails.setQuantity(rs.getInt("Quantity"));
                    orderDetails.setUnitPrice(rs.getDouble("UnitPrice"));
                    orderDetails.setTotalAmount(rs.getDouble("TotalAmount"));
                    order.getItems().add(orderDetails);
                }
            }
            return order;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

    // Kiểm tra xem đã sử dụng discount chưa?
    private boolean hasReceivedBirthdayDiscountThisYear(int userId) {
        try {
            LocalDate startOfYear = LocalDate.now().withDayOfYear(1);
            LocalDate endOfYear = LocalDate.now().withMonth(12).withDayOfMonth(31);

            // Truy vấn tìm ra đã sử dụng discount chưa, nếu rồi trả về true
            String query = """
                    SELECT COUNT(*) AS Count FROM [Order] WHERE UserId = ?
                    AND Discount > 0 AND CreatedAt >= ? AND CreatedAt <= ?
                    """;
            Object[] args = {userId, startOfYear.toString(), endOfYear.toString()};
            ResultSet rs = DbOperations.getData(query, args);

            if (rs.next()) {
                return rs.getInt("Count") > 0;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }

    private double calculateDiscount(User user, double total) {
        LocalDate today = LocalDate.now();
        LocalDate userBirthday = user.getBirthDate();

        // Điều chỉnh năm sinh nhật để so sánh với năm hiện tại
        userBirthday = userBirthday.withYear(today.getYear());

        double discount = 0.0;
        double maxDiscount = 8.0;

        // Kiểm tra nếu hôm nay là sinh nhật người dùng
        if (today.isEqual(userBirthday) && !hasReceivedBirthdayDiscountThisYear(user.getId())) {
            // Giảm giá 30% vào sinh nhật
            discount = total * 0.30;
        } else if (today.getDayOfMonth() == 1) {
            // Giảm giá 20% vào mùng 1 hàng tháng
            discount = total * 0.20;
        }

        // max discount là 8.0$
        if (discount > maxDiscount)
            discount = maxDiscount;

        return discount;
    }

    public void saveOrder(Order order) {
        UserDao userDao = UserDao.getInstance();
        User user = userDao.getById(order.getUser().getId());

        double discount = calculateDiscount(user, order.getTotalCost());
        order.setDiscount(discount);

        String insertOrder = "INSERT INTO [Order] (UserId, TotalCost, ShipCost, Discount, DeliveryInfoId, PaymentMethod, PaymentInfoId, StatusId) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {order.getUser().getId(), order.getTotalCost(), order.getShipCost(), order.getDiscount(), order.getDeliveryInfo().getId(), order.getPaymentMethodId(), order.getPaymentInfo() == null ? null : order.getPaymentInfo().getId(), 1};

        DbOperations.updateData(insertOrder, args, "");

        final int orderId;
        ResultSet rs = DbOperations.getData("SELECT MAX(Id) AS OrderId FROM [Order]");
        try {
            if (rs.next()) {
                orderId = rs.getInt("OrderId");
            } else {
                orderId = -1;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return;
        }

        String insertItem = "INSERT INTO OrderDetails (OrderId, ProductId, Quantity, UnitPrice) VALUES (?, ?, ?, ?)";
        order.getItems().forEach(orderDetails -> {
            Object[] args2 = {orderId, orderDetails.getProduct().getId(), orderDetails.getQuantity(), orderDetails.getUnitPrice()};
            DbOperations.updateData(insertItem, args2, "");
        });


        JOptionPane.showMessageDialog(null, "Your order was placed successfully! Thanks for purchasing!");
    }

    public void updateOrder(Order order) {
        String query = "UPDATE [Order] SET StatusId = ?, ShipperId = ? WHERE Id = ?";
        Object[] args = {order.getStatus().getId(), order.getShipper() == null ? null : order.getShipper().getId(), order.getId()};
        DbOperations.updateData(query, args, "Order updated successfully");
    }

    public List<Status> getAllStatus() {
        try {
            List<Status> statusList = new ArrayList<>();
            ResultSet rs = DbOperations.getData("SELECT * FROM Status");
            while (rs.next()) {
                Status status = new Status();
                status.setId(rs.getInt("Id"));
                status.setValue(rs.getString("Value"));
                statusList.add(status);
            }
            return statusList;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

    public Status getStatusById(int id) {
        try {
            ResultSet rs = DbOperations.getData("SELECT * FROM Status WHERE Id = ?", new Object[]{id});
            Status status = null;
            if (rs.next()) {
                status = new Status();
                status.setId(rs.getInt("Id"));
                status.setValue(rs.getString("Value"));
            }
            return status;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

}

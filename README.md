# App Quản lý quán cà phê
## Sinh viên thực hiện
- Phạm Đức Long (20225737)
- Phan Đức Duy (20225831)
- Lường Văn Huy (20227124)

## Role
Ứng dụng có 2 role chính là Admin và Client, dưới đây là các tính năng:

### Admin
- Thêm, sửa, xóa sản phẩm, category
- Quản lý đơn hàng
- Thêm, sửa, xóa Staff
- Ban/ Unban Client
- Các tính năng bình thường như Client
  
### Clients
- Đổi mật khẩu, câu hỏi bảo mật
- Chỉnh sửa profile
- Thêm sản phẩm vào giỏ hàng
- Lựa chọn phương thức thanh toán, thêm thông tin thanh toán
- Mua hàng, nhận giảm giá các dịp đặc biệt

## Deploy
Tải thư mục về localhost

Trước khi chạy, hãy nhớ cài đặt JDK 17 `https://www.oracle.com/java/technologies/downloads/#jdk17-windows`

Khởi động IDE, Text Editor (Intelliji, Netbeans, VSCode, ...), mở thư mục `28_PhamDucLong` lên

Trước tiên hãy vào `src >> dao >> ConnectionProvider` để setting máy chủ SQLServer về localhost cá nhân

Sau đó vào `src >> dao >> DatabaseInit`, chạy `DatabaseInit` để tạo các bảng, ràng buộc, Trigger trong SQL Server. Nếu chạy thành công sẽ có thông báo `Table drop successfully` và `Table create successfully`

Cuối cùng, vào `src >> dao >> main` để khởi động ứng dụng. 

Lưu ý: 
- Một số lỗi có thể phát sinh ra nếu bạn không chạy được `DatabaseInit`, lúc này hãy kiểm tra lại SQLServer của bạn, ví dụ như đã bật `sa` lên chưa, có `Trust server certificate` và `Encrypt connection` hay chưa. Đã cài đặt port 1433 chưa (Vào SQL Server Configuration để cài đặt) 
- File database chỉ để xem các bảng, các Trigger mà nhóm sử dụng, không cần Exec file này vì JDBC đã tự động kết nối với SQLServer

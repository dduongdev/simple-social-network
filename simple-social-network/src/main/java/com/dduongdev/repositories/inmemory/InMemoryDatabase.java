package com.dduongdev.repositories.inmemory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dduongdev.entities.Post;
import com.dduongdev.entities.PostApprovalStatus;
import com.dduongdev.entities.User;
import com.dduongdev.entities.UserFollow;
import com.dduongdev.entities.UserRole;

public class InMemoryDatabase {
    public static List<Post> posts;
    public static List<User> users;
    public static List<UserFollow> userFollows;
    
    public static int currentPostIndex = 51;
    public static int currentUserIndex = 11;
    public static int currentUserFollowIndex = 5;

    static {
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	
        posts = new ArrayList<>();
        users = new ArrayList<>();
        userFollows = new ArrayList<>();

        users.add(new User(1, "duongdev", passwordEncoder.encode("password"), UserRole.ADMIN, LocalDateTime.now().minusYears(2)));
        users.add(new User(2, "alice", passwordEncoder.encode("password"), UserRole.CUSTOMER, LocalDateTime.now().minusMonths(8)));
        users.add(new User(3, "bob", passwordEncoder.encode("password"), UserRole.CUSTOMER, LocalDateTime.now().minusMonths(5)));
        users.add(new User(4, "charlie", passwordEncoder.encode("password"), UserRole.CUSTOMER, LocalDateTime.now().minusDays(150)));
        users.add(new User(5, "david", passwordEncoder.encode("password"), UserRole.CUSTOMER, LocalDateTime.now().minusDays(90)));
        users.add(new User(6, "eva", passwordEncoder.encode("password"), UserRole.CUSTOMER, LocalDateTime.now().minusDays(60)));
        users.add(new User(7, "frank", passwordEncoder.encode("password"), UserRole.CUSTOMER, LocalDateTime.now().minusDays(30)));
        users.add(new User(8, "grace", passwordEncoder.encode("password"), UserRole.CUSTOMER, LocalDateTime.now().minusDays(15)));
        users.add(new User(9, "harry", passwordEncoder.encode("password"), UserRole.CUSTOMER, LocalDateTime.now().minusDays(7)));
        users.add(new User(10, "dduongdev_neva_sleeps", passwordEncoder.encode("password"), UserRole.CUSTOMER, LocalDateTime.now().minusDays(3)));

        posts.add(new Post(1, "Lập trình Java từ A đến Z", "Hướng dẫn lập trình Java cơ bản và nâng cao...", 7, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(50)));
        posts.add(new Post(2, "Học Spring Boot", "Spring Boot là framework mạnh mẽ giúp phát triển ứng dụng Java nhanh chóng...", 2, PostApprovalStatus.PENDING, LocalDateTime.now().minusDays(49)));
        posts.add(new Post(3, "Cách xây dựng API RESTful", "Hướng dẫn tạo REST API trong Spring Boot...", 7, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(48)));
        posts.add(new Post(4, "Tối ưu truy vấn SQL", "Những mẹo giúp tối ưu hóa SQL Query...", 4, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(47)));
        posts.add(new Post(5, "Hướng dẫn Docker", "Docker giúp bạn container hóa ứng dụng dễ dàng hơn...", 5, PostApprovalStatus.REJECTED, LocalDateTime.now().minusDays(46)));
        posts.add(new Post(6, "Nhập môn Machine Learning", "Giới thiệu cơ bản về Machine Learning...", 7, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(45)));
        posts.add(new Post(7, "Xây dựng hệ thống microservices", "Microservices giúp ứng dụng dễ mở rộng hơn...", 7, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(44)));
        posts.add(new Post(8, "Tìm hiểu Kubernetes", "Kubernetes là nền tảng quản lý container phổ biến nhất...", 8, PostApprovalStatus.PENDING, LocalDateTime.now().minusDays(43)));
        posts.add(new Post(9, "Phân tích dữ liệu với Python", "Dùng Pandas, NumPy để phân tích dữ liệu...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(42)));
        posts.add(new Post(10, "Bảo mật ứng dụng web", "Cách bảo vệ ứng dụng web khỏi các lỗ hổng bảo mật phổ biến...", 7, PostApprovalStatus.REJECTED, LocalDateTime.now().minusDays(41)));
        posts.add(new Post(11, "Kỹ thuật Debug hiệu quả", "Hướng dẫn cách debug code nhanh và chính xác...", 7, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(30)));
        posts.add(new Post(12, "Viết Unit Test đúng cách", "Làm sao để viết unit test hiệu quả và dễ bảo trì...", 7, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(28)));
        posts.add(new Post(13, "Học DSA: Cấu trúc dữ liệu & Giải thuật", "Những thuật toán và cấu trúc dữ liệu quan trọng...", 7, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(27)));
        posts.add(new Post(14, "Tối ưu hiệu suất trong Spring Boot", "Những mẹo giúp cải thiện hiệu suất ứng dụng...", 7, PostApprovalStatus.PENDING, LocalDateTime.now().minusDays(25)));
        posts.add(new Post(15, "Tìm hiểu Kafka", "Kafka là hệ thống message queue mạnh mẽ...", 15, PostApprovalStatus.REJECTED, LocalDateTime.now().minusDays(23)));
        posts.add(new Post(16, "Sử dụng Redis trong ứng dụng Java", "Redis giúp cache dữ liệu nhanh chóng...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(21)));
        posts.add(new Post(17, "Cách viết API GraphQL với Spring Boot", "Hướng dẫn tạo GraphQL API từ cơ bản đến nâng cao...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(19)));
        posts.add(new Post(18, "Lập trình Web với React.js", "React là thư viện JavaScript mạnh mẽ...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(18)));
        posts.add(new Post(19, "Xây dựng hệ thống Load Balancer", "Cách cân bằng tải giúp tăng khả năng mở rộng...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(17)));
        posts.add(new Post(20, "Viết CI/CD Pipeline với GitHub Actions", "Hướng dẫn thiết lập CI/CD cho dự án...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(15)));
        posts.add(new Post(21, "Những nguyên tắc SOLID trong lập trình", "SOLID giúp code dễ bảo trì và mở rộng...", 9, PostApprovalStatus.PENDING, LocalDateTime.now().minusDays(14)));
        posts.add(new Post(22, "Lập trình Serverless với AWS Lambda", "Xây dựng ứng dụng không cần server...", 9, PostApprovalStatus.REJECTED, LocalDateTime.now().minusDays(12)));
        posts.add(new Post(23, "Spring Security: Authentication & Authorization", "Cách bảo mật ứng dụng Spring Boot...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(11)));
        posts.add(new Post(24, "Học lập trình Golang", "Golang là ngôn ngữ lập trình mạnh mẽ...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(9)));
        posts.add(new Post(25, "Kết nối API với OAuth2", "OAuth2 giúp xác thực người dùng an toàn...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(7)));
        posts.add(new Post(26, "Viết Middleware trong Express.js", "Hướng dẫn tạo middleware hiệu quả...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(6)));
        posts.add(new Post(27, "Cách triển khai Kubernetes trên AWS", "Kubernetes giúp quản lý container dễ dàng...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(5)));
        posts.add(new Post(28, "Học lập trình Rust từ đầu", "Rust là ngôn ngữ an toàn và hiệu suất cao...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(4)));
        posts.add(new Post(29, "Viết REST API với FastAPI", "FastAPI giúp xây dựng API nhanh và mạnh mẽ...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(3)));
        posts.add(new Post(30, "Tạo chatbot với OpenAI API", "Hướng dẫn xây dựng chatbot thông minh...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(2)));
        posts.add(new Post(31, "Phân tích dữ liệu với SQL", "Những câu truy vấn SQL quan trọng...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(2)));
        posts.add(new Post(32, "Học Docker Compose", "Docker Compose giúp quản lý container dễ dàng...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(1)));
        posts.add(new Post(33, "Xây dựng hệ thống xác thực JWT", "JWT giúp bảo mật API hiệu quả...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(1)));
        posts.add(new Post(34, "Lập trình song song trong Java", "Các kỹ thuật lập trình đa luồng...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now()));
        posts.add(new Post(35, "Học Web3 và Blockchain", "Blockchain và Web3 đang thay đổi thế giới...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now()));
        posts.add(new Post(36, "Quản lý session với Redis", "Redis giúp quản lý session hiệu quả...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now()));
        posts.add(new Post(37, "Lập trình với ASP.NET Core", "Hướng dẫn xây dựng ứng dụng với .NET...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now()));
        posts.add(new Post(38, "Xây dựng API với NestJS", "NestJS giúp tạo API mạnh mẽ trên Node.js...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now()));
        posts.add(new Post(39, "Tối ưu hóa thuật toán tìm kiếm", "Các thuật toán tìm kiếm hiệu quả nhất...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now()));
        posts.add(new Post(40, "Lập trình Python nâng cao", "Những kỹ thuật Python nâng cao...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now()));
        posts.add(new Post(41, "Bảo mật API với API Gateway", "API Gateway giúp quản lý truy cập API...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now()));
        posts.add(new Post(42, "Triển khai ứng dụng với Firebase", "Firebase giúp triển khai nhanh chóng...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now()));
        posts.add(new Post(43, "Xây dựng hệ thống recommendation", "Recommendation engine giúp cá nhân hóa nội dung...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now()));
        posts.add(new Post(44, "Cách sử dụng RabbitMQ", "RabbitMQ giúp xử lý message queue...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now()));
        posts.add(new Post(45, "Học lập trình Ruby on Rails", "Ruby on Rails giúp phát triển web nhanh chóng...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now()));
        posts.add(new Post(46, "Xây dựng chatbot với AI", "Cách sử dụng NLP để tạo chatbot thông minh...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(40)));
        posts.add(new Post(47, "Python cho người mới bắt đầu", "Học lập trình Python từ cơ bản đến nâng cao...", 9, PostApprovalStatus.PENDING, LocalDateTime.now().minusDays(39)));
        posts.add(new Post(48, "Lập trình hướng đối tượng", "Các nguyên tắc cơ bản trong OOP...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(38)));
        posts.add(new Post(49, "Sử dụng Git hiệu quả", "Hướng dẫn Git, GitHub cơ bản cho lập trình viên...", 9, PostApprovalStatus.APPROVED, LocalDateTime.now().minusDays(37)));
        posts.add(new Post(50, "Phát triển game với Unity", "Cách tạo game đơn giản bằng Unity...", 9, PostApprovalStatus.REJECTED, LocalDateTime.now().minusDays(36)));

        userFollows.add(new UserFollow(2, 3, 7, LocalDateTime.now().minusDays(40)));  // Bob theo dõi DuongDev // DuongDev theo dõi Alice
        userFollows.add(new UserFollow(4, 4, 2, LocalDateTime.now().minusDays(30)));  // Charlie theo dõi Alice
    }
}

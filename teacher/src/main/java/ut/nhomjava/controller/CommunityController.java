package ut.nhomjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Cập nhật các import để sử dụng Entity và Service mới
import ut.nhomjava.model.BinhLuan; // Thay thế Comment
import ut.nhomjava.model.BaiDang; // Thay thế Post
import ut.nhomjava.model.NguoiDung; // Thay thế User
import ut.nhomjava.service.BinhLuanService; // Thay thế CommentService
import ut.nhomjava.service.BaiDangService; // Thay thế PostService
import ut.nhomjava.service.NguoiDungService; // Thay thế UserService

@Controller
public class CommunityController {

    @Autowired
    private BaiDangService baiDangService; // Đổi từ postService

    @Autowired
    private BinhLuanService binhLuanService; // Đổi từ commentService

    @Autowired
    private NguoiDungService nguoiDungService; // Đổi từ userService

    @GetMapping("/teacher-community")
    public String showCommunity(Model model) {
        List<BaiDang> posts = baiDangService.findAll(); // Lấy danh sách BaiDang
        model.addAttribute("posts", posts);
        return "teacher-community";
    }

    @GetMapping("/teacher-community-post")
    public String showPostForm(Model model) {
        model.addAttribute("post", new BaiDang()); // Sử dụng BaiDang
        return "teacher-community-post";
    }

    @PostMapping("/teacher-community-post/save")
    public String savePost(@ModelAttribute("post") BaiDang post) { // Sử dụng BaiDang
        // Lấy người dùng hiện tại và gán làm tác giả (NguoiDung) cho BaiDang
        NguoiDung currentUser = nguoiDungService.getCurrentUser(); // Bạn cần triển khai phương thức này trong NguoiDungService
        if (currentUser != null) {
            post.setNguoiDung(currentUser); // Giả định BaiDang có setNguoiDung()
        } else {
            // Xử lý trường hợp không tìm thấy người dùng (ví dụ: gán tác giả mặc định hoặc chuyển hướng đăng nhập)
            // post.setNguoiDung(nguoiDungService.findDefaultUser());
        }

        baiDangService.save(post); // Lưu BaiDang
        return "redirect:/teacher-community";
    }

    // --- Chức năng xem chi tiết bài đăng và bình luận ---
    @GetMapping("/community/post/{postId}")
    public String viewPostDetails(@PathVariable Integer postId, Model model) { // ID của BaiDang là Integer
        BaiDang post = baiDangService.findById(postId); // Tìm BaiDang theo ID
        if (post == null) {
            return "redirect:/teacher-community";
        }
        model.addAttribute("post", post);

        // Lấy tất cả bình luận cho bài đăng này
        // Giả định BinhLuanRepository có phương thức findByBaiDang_MaBaiDang()
        List<BinhLuan> comments = binhLuanService.findByBaiDangMaBaiDang(postId); // Tìm BinhLuan theo MaBaiDang
        model.addAttribute("comments", comments);

        // Chuẩn bị một đối tượng BinhLuan rỗng cho form bình luận mới
        model.addAttribute("newComment", new BinhLuan());
        return "community-post-details";
    }

    @PostMapping("/community/post/{postId}/addComment")
    public String addComment(@PathVariable Integer postId, // ID của BaiDang là Integer
                             @ModelAttribute("newComment") BinhLuan newComment, // Sử dụng BinhLuan
                             @RequestParam(value = "parentCommentId", required = false) Integer parentCommentId) { // Xóa tham số này nếu không cần

        BaiDang post = baiDangService.findById(postId); // Tìm BaiDang theo ID
        if (post == null) {
            return "redirect:/teacher-community";
        }

        newComment.setBaiDang(post); // Gán BaiDang cho BinhLuan
        // Lấy người dùng hiện tại từ NguoiDungService
        NguoiDung currentUser = nguoiDungService.getCurrentUser(); // Bạn cần triển khai phương thức này
        newComment.setNguoiDung(currentUser); // Gán NguoiDung cho BinhLuan

        // --- ĐÃ LOẠI BỎ LOGIC BÌNH LUẬN CHA/CON (REPLY) ---
        // Vì Entity BinhLuan hiện tại không có các trường parentComment hoặc replies.
        // Nếu bạn muốn tính năng này, bạn cần thêm chúng vào Entity BinhLuan và Repository.
        // if (parentCommentId != null) {
        //     BinhLuan parentComment = binhLuanService.findById(parentCommentId);
        //     if (parentComment != null) {
        //         newComment.setParentComment(parentComment);
        //     }
        // }

        binhLuanService.save(newComment); // Lưu BinhLuan
        return "redirect:/community/post/" + postId;
    }
}
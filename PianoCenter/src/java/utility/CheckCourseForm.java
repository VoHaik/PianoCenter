/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author dinh
 */
public class CheckCourseForm {
    public boolean checkName(String name) {
        // Tên khóa học không được rỗng và có độ dài tối đa 255 ký tự
        return name != null && !name.trim().isEmpty() && name.length() <= 255;
    }

    public boolean checkImage(String image) {
        // Đường dẫn ảnh có thể null hoặc tối đa 255 ký tự
        return image == null || image.length() <= 255;
    }

    public boolean checkDescription(String description) {
        // Mô tả có thể null hoặc tối đa 500 ký tự
        return description == null || description.length() <= 500;
    }

    public boolean checkTuitionFee(String fee) {
        // Học phí là số thực có tối đa 10 chữ số và 2 chữ số thập phân
            if (fee == null || fee.trim().isEmpty()) {
        return false; // Trả về false nếu fee rỗng hoặc null
    }

    // Học phí là số thực có tối đa 10 chữ số và 2 chữ số thập phân
    String regex = "^\\d{1,8}(\\.\\d{1,2})?$";
    if (!fee.matches(regex)) return false;

    try {
        BigDecimal tuitionFee = new BigDecimal(fee);
        return tuitionFee.compareTo(BigDecimal.ZERO) > 0; // Học phí phải lớn hơn 0
    } catch (NumberFormatException e) {
        return false; // Nếu không thể chuyển đổi thành số thì cũng không hợp lệ
    }

    }

    public boolean checkDates(String start, String end) {
        // Ngày phải theo định dạng YYYY-MM-DD và startDate <= endDate
        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            return !startDate.isAfter(endDate);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkCategory(String category) {
        // Danh mục không được rỗng và có tối đa 50 ký tự
        return category != null && !category.trim().isEmpty() && category.length() <= 50;
    }

    public boolean checkStatus(String status) {
        // Chỉ chấp nhận 'Active' hoặc 'Inactive'
        return "Active".equalsIgnoreCase(status) || "Inactive".equalsIgnoreCase(status);
    }

    public boolean checkQuantity(int quantity) {
        // Số lượng phải >= 0
        return quantity >= 0;
    }
}

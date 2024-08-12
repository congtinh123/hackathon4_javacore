package HCM_JV240304_AV_HuynhCongTinh.BT2;

import java.util.Scanner;
import java.util.Stack;

public class Exam_Advance_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Bước 1: Lấy số ISBN từ người dùng
        System.out.print("Nhập số ISBN (10 chữ số): ");
        String isbn = scanner.nextLine();

        // Bước 2: Kiểm tra số lượng chữ số và định dạng
        if (isbn.length() != 10 || !isbn.matches("\\d+")) {
            System.out.println("ISBN phải là số có 10 chữ số.");
            return;
        }

        // Bước 3: Sử dụng Stack để lưu trữ các chữ số và thực hiện tính tổng
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(Character.getNumericValue(isbn.charAt(i)));
        }

        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += i * stack.pop();
        }

        // Bước 4: Kiểm tra tính hợp lệ và đưa ra kết quả
        if (sum % 11 == 0) {
            System.out.println("ISBN hợp lệ.");
        } else {
            System.out.println("ISBN không hợp lệ.");
        }
    }
}


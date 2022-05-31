package Controllers;

import io.WriteAndRead;
import models.SortByLargeToSmall;
import models.SortBySmallToLarge;
import models.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    WriteAndRead wr = new WriteAndRead();
    Scanner scanner = new Scanner(System.in);
    ArrayList<Student> students = new ArrayList<Student>();

    public void menu() {
        System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN");
        System.out.println("Chọn chức năng theo số(để tiếp tục)");
        System.out.println("1|Xem danh sách sinh viên");
        System.out.println("2|Thêm mới");
        System.out.println("3|Cập nhật");
        System.out.println("4|Xóa");
        System.out.println("5|Sắp xếp");
        System.out.println("6|Đọc từ file");
        System.out.println("7|Ghi vào file");
        System.out.println("8|Thoát");
        int choice = 0;
        try {
            System.out.println("Nhập lựa chọn");
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 0 || choice > 8) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.err.println("Nhập sai");
        }
        switch (choice) {
            case 1:
                displayStudent();
                scanner.nextLine();
                break;
            case 2:
                Student student = creatStudent();
                if (!student.equals(null)) {
                    students.add(student);
                } else {
                    System.out.println("Thêm thất bại");
                }
                scanner.nextLine();
                break;
            case 3:
                updateStudent();
                scanner.nextLine();
                break;
            case 4:
                deleteStudent();
                System.out.println("Sắp xếp thành công");
                scanner.nextLine();
                break;
            case 5:
                sortStudent();
                System.out.println("Sắp xếp thành công");
                scanner.nextLine();
                break;
            case 6:
                System.out.println("Nếu bạn tiếp tục thì toàn bộ dạnh sách hiện tại sẽ bị xóa");
                System.out.println("Nhập y để tiếp tục");
                String yout = scanner.nextLine();
                if (yout.equals("y")){
                    wr.read();
                    System.out.println("Đọc thành công");
                }
                scanner.nextLine();
                break;
            case 7:
                System.out.println("Nếu bạn tiếp tục thì data sẽ bị xóa bỏ và ghi lại");
                System.out.println("Nhập y để tiếp tục");
                String yin = scanner.nextLine();
                if (yin.equals("y")){
                    wr.write(students);
                    System.out.println("Ghi thành công");
                }
                scanner.nextLine();
                break;
            case 8:
                System.exit(0);

        }
    }

    public void displayStudent() {
        for (Student st : students
        ) {
            System.out.println(st);
        }
    }

    public Student creatStudent() {
        try {
            System.out.println("Nhập mã sinh viên");
            String id = scanner.nextLine();
            System.out.println("Nhập tên sinh viên");
            String name = scanner.nextLine();
            System.out.println("Nhập tuổi");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.println("Nhập giới tính");
            String gender = scanner.nextLine();
            System.out.println("Nhập địa chỉ");
            String address = scanner.nextLine();
            System.out.println("Nhập điểm trung bình");
            double dtb = Double.parseDouble(scanner.nextLine());
            System.out.println("Thêm thành công");
            return new Student(id, name, age, gender, address, dtb);
        } catch (Exception e) {
            System.out.println("Nhập sai dữ liệu (Chọn nhập lại)");
            System.out.println("Y|Tiếp tục");
            String choice = scanner.nextLine();
            if (choice.equals("y")) {
                return creatStudent();
            } else {
                return new Student();
            }
        }
    }

    public void updateStudent() {
        System.out.println("Nhập mã sinh viên cần cập nhật");
        String id = scanner.nextLine();
        int index = checkId(id);
        if (index == -1) {
            System.out.println("Mã sinh viên không tồn tại");
            System.out.println("Bạn muốn tiếp tục nhập y");
            String check = scanner.nextLine();
            if (check.equals("y")) {
                updateStudent();
            }
        } else {
            Student student = creatStudent();
            if (!student.equals(null)) {
                students.set(index, creatStudent());
            } else {
                System.out.println("Sửa thất bại");
            }
        }
    }

    public int checkId(String id) {
        int index = -1;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                index = i;
            }
        }
        return index;
    }

    public void deleteStudent() {
        System.out.println("Nhập mã sinh viên cần xóa");
        String id = scanner.nextLine();
        int index = checkId(id);
        if (index == -1) {
            System.out.println("Mã sinh viên không tồn tại");
            System.out.println("Bạn muốn tiếp tục nhập y");
            String check = scanner.nextLine();
            if (check.equals("y")) {
                deleteStudent();
            }
        } else {
            System.out.println("Xóa sinh viên có msv là" + id);
            System.out.println("Đồng ý nhập y");
            String yOrn = scanner.nextLine();
            if (yOrn.equals("y")) {
                students.remove(index);
                System.out.println("Xóa thành công");
            }
        }
    }

    public void sortStudent() {
        System.out.println("----Sắp xếp sinh viên theo điểm trung bình----");
        System.out.println("Chọn chức năng theo số (để tiếp tục)");
        System.out.println("1|Sắp xếp theo điềm trung bình tăng dần");
        System.out.println("2|Sắp xếp theo điềm trung bình giảm dần");
        System.out.println("3|Thoát");
        int choice;
        while (true) {
            try {
                System.out.println("Nhập lựa chọn");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 3) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.err.println("Nhập sai lựa chọn");
            }
        }

        switch (choice){
            case 1:
                students.sort(new SortBySmallToLarge());
                break;
            case 2:
                students.sort(new SortByLargeToSmall());
            case 3:
                break;
        }
    }
}

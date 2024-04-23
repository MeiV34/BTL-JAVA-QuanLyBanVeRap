package ui;
import javax.swing.*;

import entity.Phong;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FrmGhe {
	private Phong phong = new Phong();
	public class SeatButton extends JButton {
	    private String seatID; // Mã ghế
	    private String seatNumber; // Số ghế
	    private Phong phong; // Tham chiếu đến đối tượng Phong
	    private String status; // Trạng thái của ghế

	    // Constructor
	    public SeatButton(String seatID, String seatNumber, Phong phong, String status) {
	        super(seatNumber); // Gọi constructor của lớp cha (JButton) với nội dung của button là số ghế
	        this.seatID = seatID;
	        this.seatNumber = seatNumber;
	        this.phong = phong;
	        this.status = status;
	        // Set màu cho ghế tùy thuộc vào trạng thái
	        if (status.equals("Đã đặt")) {
	            setBackground(Color.RED);
	        } else {
	            setBackground(Color.GREEN);
	        }
	    }

	    // Các phương thức getter và setter (nếu cần)
	}
    
	 public JPanel taoFrmVe() {
	        JPanel pnlGhe = new JPanel();
	        pnlGhe.setLayout(new GridLayout(phong.getSoLuongGhe() / 12, 12)); // Giả sử mỗi hàng có tối đa 12 ghế

	        List<List<JButton>> danhSachGhe = taoDanhSachGhe(); // Tạo danh sách ghế

	        for (List<JButton> row : danhSachGhe) {
	            for (JButton button : row) {
	                pnlGhe.add(button); // Thêm ghế vào panel
	            }
	        }

	        return pnlGhe;
	    }

	    private List<List<JButton>> taoDanhSachGhe() {
	        List<List<JButton>> danhSachGhe = new ArrayList<>();
	        int soLuongGhe = phong.getSoLuongGhe();
	        int soHang = soLuongGhe / 12; // Giả sử mỗi hàng có tối đa 12 ghế

	        for (int i = 0; i < soHang; i++) {
	            List<JButton> hangGhe = new ArrayList<>();
	            for (int j = 0; j < 12; j++) {
	                String maGhe = "Ghe_" + (i * 12 + j + 1); // Tạo mã ghế
	                String soGhe = String.valueOf((char) ('A' + j)); // Số ghế là các ký tự từ A đến L
	                String status = "Trống"; // Mặc định là ghế trống

	                // Tạo JButton đại diện cho ghế và thêm vào danh sách
	                JButton button = new JButton(maGhe + " - " + soGhe + " (" + status + ")");
	                hangGhe.add(button);
	            }
	            danhSachGhe.add(hangGhe);
	        }

	        return danhSachGhe;
	    }

	    public static void main(String[] args) {
	        // Khởi tạo và hiển thị frame
	        JFrame frame = new JFrame("Danh sách ghế");
	        FrmGhe frmGhe = new FrmGhe();
	        frame.getContentPane().add(frmGhe.taoFrmVe(), BorderLayout.CENTER);
	        frame.setSize(800, 600);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	    }
}

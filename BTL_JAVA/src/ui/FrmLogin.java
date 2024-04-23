package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connectDB.ConnectDB;


public class FrmLogin extends JFrame implements ActionListener{
	
	private JButton loginBtn = new JButton("Login");
	private JButton resetBtn = new JButton("Reset");
	private JTextField userIDField = new JTextField();
	private JPasswordField userPasswordField = new JPasswordField();
	private JLabel userIDLabel = new JLabel("Email:");
	private JLabel userPasswordLabel = new JLabel("Mật khẩu:");
	private Connection con;
	
	
	public FrmLogin(){	
		ConnectDB.getInstance().connect();
		con = ConnectDB.getConnection();
		if (con == null) {
		    JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu");
		    return;
		}
		
		this.setTitle("Hệ thống bán vé rạp chiếu phim");
		this.setLayout(null);
		userIDLabel.setBounds(50, 75, 75, 75);
		userPasswordLabel.setBounds(50, 150, 75, 25);
		
		userIDField.setBounds(125, 100, 200, 25);
		userPasswordField.setBounds(125, 150, 200, 25);
		
		loginBtn.setBounds(125, 200, 100, 25);
		loginBtn.setFocusable(false);
		
		resetBtn.setBounds(225, 200, 100, 25);
		
		loginBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		
		this.add(userIDLabel);
		this.add(userPasswordLabel);
		this.add(userIDField);
		this.add(userPasswordField);
		this.add(loginBtn);
		this.add(resetBtn);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(420,420);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new FrmLogin();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(loginBtn)) {
			String uid = userIDField.getText().toString().trim();
			char[] c = userPasswordField.getPassword();
			String pwd = new String(c).toString().trim();
			
			  // Kiểm tra tài khoản và mật khẩu trống
            if (uid.isEmpty() || pwd.isEmpty()) {
            	JOptionPane.showMessageDialog(null,
					       "Email hoặc mật khẩu không được để trống",
					       "Lỗi!",
					       JOptionPane.ERROR_MESSAGE);
                return;
            }
            
			try {
				PreparedStatement state = con.prepareStatement("Select * from NhanVien Where Email = ? and MatKhau = ?");
				state.setString(1, uid);
				state.setString(2, pwd);
				ResultSet rs = state.executeQuery();
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
					new FrmPhim();
					setVisible(false);
				} else {
					userIDField.requestFocus();
					JOptionPane.showMessageDialog(null, "Email hoặc mật khẩu không đúng. Vui lòng nhập lại!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Đăng nhập thất bại", "Lỗi!", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			userIDField.setText("");
			userPasswordField.setText("");
		}
	}
}

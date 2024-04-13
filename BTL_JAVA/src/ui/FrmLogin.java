package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connect.ConnectDB;


public class FrmLogin extends JFrame implements ActionListener{
	
	private JButton loginBtn = new JButton("Login");
	private JButton resetBtn = new JButton("Reset");
	private JTextField userIDField = new JTextField();
	private JPasswordField userPasswordField = new JPasswordField();
	private JLabel userIDLabel = new JLabel("userID:");
	private JLabel userPasswordLabel = new JLabel("password:");
	private JLabel messageLabel = new JLabel("THIS IS A TEST");
	

	
	public FrmLogin(){	
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		
		this.setTitle("Ò_ó");
		this.setLayout(null);
		userIDLabel.setBounds(50, 75, 75, 75);
		userPasswordLabel.setBounds(50, 150, 75, 25);
		
		messageLabel.setBounds(125, 250, 250, 35);
		messageLabel.setFont(new Font(null,Font.ITALIC,25));
		
		userIDField.setBounds(125, 100, 200, 25);
		userPasswordField.setBounds(125, 150, 200, 25);
		
		loginBtn.setBounds(125, 200, 100, 25);
		loginBtn.setFocusable(false);
		
		resetBtn.setBounds(225, 200, 100, 25);
		
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String uid = userIDField.getText().toString().trim();
				char[] c = userPasswordField.getPassword();
				String pwd = new String(c).toString().trim();
				
				  // Kiểm tra tài khoản và mật khẩu trống
                if (uid.isEmpty() || pwd.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không được để trống");
                    return;
                }
                
				try {
					PreparedStatement state = con.prepareStatement("Select * from NhanVien Where username = ? and "
																	+ "passwords = ?");
					state.setString(1, uid);
					state.setString(2, pwd);
					ResultSet rs = state.executeQuery();
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
					new FrmPhim();
					setVisible(false);
				}catch(Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
				}
				
			}
		});
		resetBtn.addActionListener(this);
		
		this.add(userIDLabel);
		this.add(userPasswordLabel);
		this.add(messageLabel);
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
		// TODO Auto-generated method stub
		
	}
}

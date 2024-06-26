package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.EventObject;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import common.Utils;
import connectDB.ConnectDB;
import DAO.KhachHang_DAO;
import entity.KhachHang;

public class FrmKhachHang extends JFrame implements ActionListener, MouseListener{
	private Connection con;
	private Box boxLayout;
	private JTextField txtHoTen;
	private JTextField txtMa;
	private JTextField txtEmail;
	private JTextField txtSdt;
	private JButton btnThem;
	private AbstractButton btnXoa;
	private JButton btnSua;
	private JButton btnLamMoi;
	private DefaultTableModel modelKH;
	private JTable table;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private KhachHang_DAO khachHangDAO = new KhachHang_DAO();

	public FrmKhachHang() throws ParseException  {
		getContentPane().add(taoFrmKhachHang());
		setSize(1035,682);
	}
	
	public static void main(String[] args) {
		try {
			new FrmKhachHang().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JPanel taoFrmKhachHang(){
		// Connect 
		ConnectDB.getInstance().connect();
		con = ConnectDB.getConnection();
		if (con == null) {
		    JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu");
		    return null;
		}
		        
		//Set up background
		JPanel pnKhachHang= new JPanel();
		pnKhachHang.setBackground(new Color(228, 243, 208));
		pnKhachHang.setLayout(null);
		setLocation(250, 30);
		
		//JPanel Form - left
		JPanel pnTXT=new JPanel();
		pnTXT.setBounds(20,30,414,588);
		pnTXT.setBackground(new Color(204,241,157));
		
		//left-input
		boxLayout = Box.createVerticalBox();
		boxLayout.add(Box.createVerticalStrut(20));
		boxLayout.add(new JLabel("Mã khách hàng"));
		boxLayout.add(txtMa = new JTextField(30));
		boxLayout.add(Box.createVerticalStrut(20));
		txtMa.setEditable(false);
		
		boxLayout.add(new JLabel("Họ và tên"));
		boxLayout.add(txtHoTen = new JTextField(30));
		boxLayout.add(Box.createVerticalStrut(20));
		
		boxLayout.add(new JLabel("Email"));
		boxLayout.add(txtEmail = new JTextField(30));
		boxLayout.add(Box.createVerticalStrut(20));
		
		boxLayout.add(new JLabel("Số điện thoại"));
		boxLayout.add(txtSdt = new JTextField(30));
		boxLayout.add(Box.createVerticalStrut(20));
	
		pnTXT.add(boxLayout);
		
		//btn chuc nang
		JPanel pnBtn=new JPanel();
		pnBtn.setBackground(new Color(204, 241, 157));
		pnBtn.setLayout(new GridLayout(3,5));
		
		pnBtn.add(new JLabel());
		pnBtn.add(btnThem=new JButton(""));
		btnThem.setIcon(new ImageIcon("img/add-user.png"));
		btnThem.setToolTipText("Thêm mới");
		btnThem.setBackground(new Color(255, 255, 255));
		
		pnBtn.add(new JLabel());
		pnBtn.add(btnXoa=new JButton(""));
		btnXoa.setIcon(new ImageIcon("img/delete-user.png"));
		btnXoa.setToolTipText("Xóa");
		btnXoa.setBackground(new Color(255, 255, 255));
		pnBtn.add(new JLabel());
		pnBtn.add(new JLabel());
		pnBtn.add(new JLabel());                                                                                                                                                                                                                         
		pnBtn.add(new JLabel());
		pnBtn.add(new JLabel());
		pnBtn.add(new JLabel());
		pnBtn.add(new JLabel());
		
		pnBtn.add(btnSua=new JButton(""));
		btnSua.setIcon(new ImageIcon("img/update-user.png"));
		btnSua.setToolTipText("Cập nhật");
		btnSua.setBackground(new Color(255, 255, 255));
		pnBtn.add(new JLabel());
		
		pnBtn.add(btnLamMoi=new JButton(""));
		btnLamMoi.setIcon(new ImageIcon("img/refresh.png"));
		btnLamMoi.setToolTipText("Làm mới");
		btnLamMoi.setBackground(new Color(255, 255, 255));
		pnBtn.add(new JLabel());	
		
		pnTXT.add(pnBtn);
		pnTXT.add(Box.createVerticalStrut(60));
		
		//JPanel Table - Right
		JPanel pnTable=new JPanel();
		pnTable.setBackground(new Color(204, 241, 157));
		pnTable.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 515, 518);
		pnTable.setBounds(466, 30, 545, 588);
		
		pnTable.add(scrollPane);
		String[] header = { "Mã KH", "Họ tên", "Email", "Số điện thoại"};
		modelKH = new DefaultTableModel(header, 0);
		table = new JTable(modelKH) {
			public boolean editCellAt(int row, int column, EventObject e) 
			{ 
				// Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};

		JTableHeader header1 = table.getTableHeader();
		header1.setBackground(new Color(238, 233, 233));
		header1.setForeground(Color.black);
		header1.setFont(new Font("Tahoma", Font.BOLD, 14));

		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setRowHeight(25);
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTimKiem.setBounds(120, 20, 320, 34);
		pnTable.add(txtTimKiem);

		btnTimKiem = new JButton();
		btnTimKiem.setIcon(new ImageIcon("img/search.png"));
		btnTimKiem.setBackground(new Color(255, 255, 255));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiem.setForeground(new Color(0, 0, 0));
		btnTimKiem.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnTimKiem.setBounds(459, 20, 76, 34);
		pnTable.add(btnTimKiem);
		
		pnKhachHang.add(pnTXT);
		pnKhachHang.add(pnTable);
		
		table.addMouseListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnTimKiem.addActionListener(this);
		
		//init tu database
		DocDuLieuVaoTable();
		txtMa.setText(String.valueOf(khachHangDAO.getMaKHAutoTang()));
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pnKhachHang.setSize(1084,768);
		return pnKhachHang;		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == btnThem) {
			String maKH = txtMa.getText().trim();
			String hoTen = txtHoTen.getText().trim();
			String email = txtEmail.getText().trim();
			String sdt = txtSdt.getText().trim();
			if(maKH.isEmpty() || hoTen.isEmpty() || email.isEmpty() || sdt.isEmpty()) {
				JOptionPane.showMessageDialog(this,
		                "Vui lòng nhập đầy đủ thông tin!",
		                "Lỗi!",
		                JOptionPane.ERROR_MESSAGE);
			}else {
				if (validData()) {
					int maKHInt = Integer.parseInt(maKH);
					KhachHang kh = new KhachHang(maKHInt, hoTen, email, sdt);
					try {
						if(khachHangDAO.addKhachHang(kh)) {
							modelKH.addRow(new Object[] {kh.getMaKH(), kh.getHoTen(), kh.getEmail(), kh.getSdt()});	
							resetForm(Utils.tangMaKH(maKH));
							JOptionPane.showMessageDialog(this, "Thêm thành công!");
						} else {
							JOptionPane.showMessageDialog(this,
					                "Thêm không thành công!",
					                "Lỗi!",
					                JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				};
			}
		} else if(o == btnXoa) {
			int row = table.getSelectedRow();
			if(row >= 0) {
				int kh =(Integer) modelKH.getValueAt(row, 0);
				int choice = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xóa Khách hàng này?");
				if(choice == JOptionPane.YES_OPTION) {
					if(khachHangDAO.removeKhachHang(kh)) {
						modelKH.removeRow(row);
						resetForm(String.valueOf(khachHangDAO.getMaKHAutoTang()));
						btnThem.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(null, "Xóa không thành công!");
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng muốn xoá!");
			}
		} else if(o == btnLamMoi) {
			resetForm(String.valueOf(khachHangDAO.getMaKHAutoTang()));
			btnThem.setEnabled(true);
		} else if(o == btnSua) {
			int row = table.getSelectedRow();
			if(row < 0) {
				JOptionPane.showMessageDialog(this,
		                "Vui lòng chọn dòng muốn sửa!",
		                "Lỗi!",
		                JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String maKH = txtMa.getText().trim();
			String hoTen = txtHoTen.getText().trim();
			String email = txtEmail.getText().trim();
			String sdt = txtSdt.getText().trim();
			if(maKH.isEmpty() || hoTen.isEmpty() || email.isEmpty() || sdt.isEmpty()) {
				JOptionPane.showMessageDialog(this,
					"Vui lòng nhập đầy đủ thông tin!",
					"Lỗi!",
					JOptionPane.ERROR_MESSAGE);
			}else {
				if (validData()) {
					int maKHInt = Integer.parseInt(maKH);
					KhachHang kh = new KhachHang(maKHInt, hoTen, email, sdt);
					if(khachHangDAO.updateKhachHang(kh)) {
						table.setValueAt(txtHoTen.getText(), row, 1);
						table.setValueAt(txtEmail.getText(), row, 2); 
						table.setValueAt(txtSdt.getText(), row, 3); 
						JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
					} else {
						JOptionPane.showMessageDialog(this,
					       "Cập nhật thông tin không thành công!",
					       "Lỗi!",
					       JOptionPane.ERROR_MESSAGE);
					}
				};
			}
		} else if(o == btnTimKiem) {
			String tuKhoa = txtTimKiem.getText().trim();
			if(!tuKhoa.isEmpty()) {
				timKhachHang(tuKhoa);
			}else {
				xoaRongTable();
				DocDuLieuVaoTable();
			}
			
		}
	}
	
	private boolean validData() {
		String hoTen = txtHoTen.getText().trim();
		String email = txtEmail.getText().trim();
		String sdt = txtSdt.getText().trim();

		if (!hoTen.matches("^[\\p{Lu}][\\p{L}]*(?:[\\s'.][\\p{Lu}][\\p{L}]*)*$")) {
			txtHoTen.requestFocus();
            JOptionPane.showMessageDialog(this, "Họ tên gồm nhiều từ ngăn cách bởi khoảng trắng, ký tự đầu mỗi từ phải viết hoa, có thể có '.\nKhông chứa các ký tự đặc biệt khác", "Lỗi!", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		
		if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
			txtEmail.requestFocus();
			JOptionPane.showMessageDialog(this, "Email không đúng cú pháp!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		
		if (!sdt.matches("^0([3|4|5|7|8|9])\\d{8}")) {
			txtSdt.requestFocus();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng số điện thoại!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		return true;
		
	}
	
	private void resetForm(String maKH) {
		txtHoTen.requestFocus();
		txtMa.setText(maKH);
		txtHoTen.setText("");
		txtEmail.setText("");
		txtSdt.setText("");
	}
	
	private void DocDuLieuVaoTable() {
		List<KhachHang> listKH = khachHangDAO.getAllKhachHang();
		for (KhachHang kh : listKH) {
			Object[] rowData = {kh.getMaKH(), kh.getHoTen(), kh.getEmail(), kh.getSdt()};
			modelKH.addRow(rowData);
		}
		table.setModel(modelKH);
	}
	
	public void timKhachHang(String tuKhoa){		
		tuKhoa = tuKhoa.toLowerCase();
		List<KhachHang> dsKH = khachHangDAO.getAllKhachHang();
		for (KhachHang kh : dsKH) {
			if(kh.getHoTen().toLowerCase().contains(tuKhoa)) {
				xoaRongTable();
				modelKH.addRow(new Object[] {kh.getMaKH(), kh.getHoTen(), kh.getEmail(), kh.getSdt()});	
			}
		}
	}
	
	public void xoaRongTable() {
		DefaultTableModel d = (DefaultTableModel) table.getModel();
		d.getDataVector().removeAllElements();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMa.setText(modelKH.getValueAt(row, 0).toString());
		txtHoTen.setText(modelKH.getValueAt(row, 1).toString());
		txtEmail.setText(modelKH.getValueAt(row, 2).toString()); 
		txtSdt.setText(modelKH.getValueAt(row, 3).toString());
		btnThem.setEnabled(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
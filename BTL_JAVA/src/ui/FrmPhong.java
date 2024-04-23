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

import DAO.Phong_DAO;
import connectDB.ConnectDB;
import entity.Phong;

public class FrmPhong extends JFrame implements ActionListener, MouseListener{
	private Connection con;
	private Box boxLayout;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtSLGhe;
	private JButton btnThem;
	private AbstractButton btnXoa;
	private JButton btnSua;
	private JButton btnLamMoi;
	private DefaultTableModel model;
	private JTable table;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private Phong_DAO phongDAO = new Phong_DAO();
	public FrmPhong() throws ParseException  {
		getContentPane().add(taoFrmPhong());
		setSize(1035,682);
	}
	
	public static void main(String[] args) {
		try {
			new FrmPhong().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JPanel taoFrmPhong(){
		// Connect 
		ConnectDB.getInstance().connect();
		con = ConnectDB.getConnection();
		if (con == null) {
			JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu");
			return null;
		}
				
		//Set up background
		JPanel pnPhong= new JPanel();
		pnPhong.setBackground(new Color(228, 243, 208));
		pnPhong.setLayout(null);
		setLocation(250, 30);
		
		//JPanel Form - left
		JPanel pnTXT=new JPanel();
		pnTXT.setBounds(20,30,414,588);
		pnTXT.setBackground(new Color(204,241,157));
		
		//left-input
		boxLayout = Box.createVerticalBox();
		boxLayout.add(Box.createVerticalStrut(20));
		boxLayout.add(new JLabel("Mã phòng"));
		boxLayout.add(txtMa = new JTextField(30));
		boxLayout.add(Box.createVerticalStrut(20));
		
		boxLayout.add(new JLabel("Tên phòng"));
		boxLayout.add(txtTen = new JTextField(30));
		boxLayout.add(Box.createVerticalStrut(20));
		
		boxLayout.add(new JLabel("Số lượng ghế"));
		boxLayout.add(txtSLGhe = new JTextField(30));
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
		String[] header = { "Mã phòng", "Tên phòng", "Số lượng ghế"};
		model = new DefaultTableModel(header, 0);
		table = new JTable(model) {
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
		
		pnPhong.add(pnTXT);
		pnPhong.add(pnTable);
		
		table.addMouseListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnTimKiem.addActionListener(this);
		
		//init tu database
		DocDuLieuVaoTable();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pnPhong.setSize(1084,768);
		return pnPhong;		
						
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == btnThem) {
			String ma = txtMa.getText().trim();
			String ten = txtTen.getText().trim();
			String soLuongGhe = txtSLGhe.getText().trim();
			if(ma.isEmpty() || ten.isEmpty() || soLuongGhe.isEmpty()) {
				JOptionPane.showMessageDialog(this,
		                "Vui lòng nhập đầy đủ thông tin!",
		                "Lỗi!",
		                JOptionPane.ERROR_MESSAGE);
			}else {
				if (validData()) {
					int soLuong = Integer.parseInt(soLuongGhe);
					Phong data = new Phong(ma, ten, soLuong);
					try {
						if(phongDAO.addPhong(data)) {
							model.addRow(new Object[] {data.getMaPhong(), data.getTenPhong(), data.getSoLuongGhe()});	
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
				String data = (String) model.getValueAt(row, 0);
				int choice = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xóa Phòng này?");
				if(choice == JOptionPane.YES_OPTION) {
					if(phongDAO.removePhong(data)) {
						model.removeRow(row);
						resetForm();
//						btnThem.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(this,
				                "Xoá không thành công",
				                "Lỗi!",
				                JOptionPane.ERROR_MESSAGE);
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng muốn xoá!");
			}
		} else if(o == btnLamMoi) {
			resetForm();
//			btnThem.setEnabled(true);
		} else if(o == btnSua) {
			int row = table.getSelectedRow();
			if(row < 0) {
				JOptionPane.showMessageDialog(this,
		                "Vui lòng chọn dòng muốn sửa!",
		                "Lỗi!",
		                JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String ma = txtMa.getText().trim();
			String ten = txtTen.getText().trim();
			String soLuongGhe = txtSLGhe.getText().trim();
			if(ma.isEmpty() || ten.isEmpty() || soLuongGhe.isEmpty()) {
				JOptionPane.showMessageDialog(this,
					"Vui lòng nhập đầy đủ thông tin!",
					"Lỗi!",
					JOptionPane.ERROR_MESSAGE);
			}else {
				if (validData()) {
					int soLuong = Integer.parseInt(soLuongGhe);
					Phong data = new Phong(ma, ten, soLuong);
					if(phongDAO.updatePhong(data)) {
						table.setValueAt(txtMa.getText(), row, 0);
						table.setValueAt(txtTen.getText(), row, 1); 
						table.setValueAt(txtSLGhe.getText(), row, 2); 
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
				timPhong(tuKhoa);
			}else {
				xoaRongTable();
				DocDuLieuVaoTable();
			}
			
		}
	}
	
	private boolean validData() {
		String ma = txtMa.getText().trim();
		String ten = txtTen.getText().trim();
		String soLuong = txtSLGhe.getText().trim();

		if (!ma.matches("P\\d{3}$")) {
			txtMa.requestFocus();
            JOptionPane.showMessageDialog(this, "Mã phòng bắt đầu bằng P và theo sau là 3 chữ số","Lỗi!", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		
		if (!ten.matches("\\s*\\w+$")) {
			txtTen.requestFocus();
			JOptionPane.showMessageDialog(this, "Tên phòng chỉ được chứa các ký tự chữ cái, số và dấu gạch dưới, và không được bắt đầu hoặc kết thúc bằng khoảng trắng.", "Lỗi!", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		
		if (!soLuong.matches("^[1-9]\\d*$")) {
			txtSLGhe.requestFocus();
            JOptionPane.showMessageDialog(this, "Số lượng ghế phải lớn hơn 0!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		return true;
		
	}
	
	private void resetForm() {
		txtMa.requestFocus();
		txtMa.setText("");
		txtTen.setText("");
		txtSLGhe.setText("");
	}
	
	private void DocDuLieuVaoTable() {
		List<Phong> listData = phongDAO.getAllPhong();
		for (Phong data : listData) {
			Object[] rowData = {data.getMaPhong(), data.getTenPhong(), data.getSoLuongGhe()};
			model.addRow(rowData);
		}
		table.setModel(model);
	}
	
	public void timPhong(String tuKhoa){		
		tuKhoa = tuKhoa.toLowerCase();
		List<Phong> ds = phongDAO.getAllPhong();
		for (Phong data : ds) {
			if(data.getMaPhong().toLowerCase().contains(tuKhoa)) {
				xoaRongTable();
				model.addRow(new Object[] {data.getMaPhong(), data.getTenPhong(), data.getSoLuongGhe()});	
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
		txtMa.setText(model.getValueAt(row, 0).toString());
		txtTen.setText(model.getValueAt(row, 1).toString());
		txtSLGhe.setText(model.getValueAt(row, 2).toString()); 
//		btnThem.setEnabled(false);
		
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

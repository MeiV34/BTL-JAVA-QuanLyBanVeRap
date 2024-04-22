package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import DAO.NhanVien_DAO;
import entity.NhanVien;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;

public class FrmNhanVien extends JFrame implements ActionListener,MouseListener {
	private JLabel lblMaNV;
	private JTextField txtMaNV;
	private JLabel lblTenNV;
	private JTextField txtTenNV;
	private JLabel lblNgayVaoLam;
	private JDateChooser txtNgayVaoLam;
	private JLabel lblSDT;
	private JTextField txtSDT;
	private JLabel lblLuong;
	private JTextField txtLuong;
	private JLabel lblDC;
	private JTextField txtDC;
	private JLabel lblMail;
	private JTextField txtMail;
	private JLabel lblChucVu;
	private JComboBox cbxChucVu;
	private JComboBox cbxTimKiem;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLamMoi;
	private JLabel spacer1;
	private JLabel spacer2;
	private JLabel spacer3;
	private JLabel spacer4;
	private JLabel spacer5;
	private JLabel spacer6;
	private JLabel spacer7;
	private JLabel spacer8;
	private JLabel spacer9;
	private JLabel spacer10;
	private JLabel spacer11;
	private DefaultTableModel modelDanhSachNV;
	private JTable table;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private List<NhanVien> dsNV;
	private NhanVien_DAO nvDao;
	public FrmNhanVien() throws ParseException  {
		getContentPane().add(taoFrmNhanVien());
		setSize(1084,768);
	}
	
	public JPanel taoFrmNhanVien() {
		JPanel pn_NV= new JPanel();
		pn_NV.setBackground(new Color(228, 243, 208));
		pn_NV.setLayout(null);
		setLocation(250, 30);
		
		JPanel pnTXT=new JPanel();
		pnTXT.setBounds(20,60,416,629);
		pnTXT.setBackground(new Color(204,241,157));
		pnTXT.setLayout(new BoxLayout(pnTXT, BoxLayout.Y_AXIS));
		pnTXT.add(Box.createVerticalStrut(20));
		
		JPanel pnMaNV=new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnMaNV.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(70);
		pnMaNV.setBackground(new Color(204, 241, 157));
		pnMaNV.add(lblMaNV=new JLabel("Mã NV"));
		pnMaNV.add(txtMaNV=new JTextField(15));
		pnTXT.add(pnMaNV);
		
		JPanel pnTenNV=new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnTenNV.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(70);
		pnTenNV.setBackground(new Color(204, 241, 157));
		pnTenNV.add(lblTenNV=new JLabel("Tên NV"));
		pnTenNV.add(txtTenNV=new JTextField(15));
		pnTXT.add(pnTenNV);
		
		JPanel pnNgaySinh=new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnNgaySinh.getLayout();
		flowLayout_2.setVgap(0);
		flowLayout_2.setHgap(70);
		pnNgaySinh.setBackground(new Color(204, 241, 157));
		pnNgaySinh.add(lblNgayVaoLam=new JLabel("Ngày vào làm"));
		pnNgaySinh.add(txtNgayVaoLam=new JDateChooser());
		txtNgayVaoLam.setDateFormatString("dd-MM-yyyy");
//		JFormattedTextField txtNgaySinh=new JFormattedTextField(new MaskFormatter("dd/MM/yyyy"));
		
		pnTXT.add(pnNgaySinh);
		
		JPanel pnSDT=new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnSDT.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(70);
		pnSDT.setBackground(new Color(204, 241, 157));
		pnSDT.add(lblSDT=new JLabel("Số điện thoại	"));
		pnSDT.add(txtSDT=new JTextField(14));
		pnTXT.add(pnSDT);

		JPanel pnLuong=new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pnLuong.getLayout();
		flowLayout_4.setVgap(0);
		flowLayout_4.setHgap(70);
		pnLuong.setBackground(new Color(204, 241, 157));
		pnLuong.add(lblLuong=new JLabel("Lương"));
		pnLuong.add(txtLuong=new JTextField(15));
		pnTXT.add(pnLuong);
		
		JPanel pnDC=new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) pnDC.getLayout();
		flowLayout_5.setVgap(0);
		flowLayout_5.setHgap(70);
		pnDC.setBackground(new Color(204, 241, 157));
		pnDC.add(lblDC=new JLabel("Địa chỉ"));
		pnDC.add(txtDC=new JTextField(15));
		pnTXT.add(pnDC);
		
		JPanel pnMail=new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) pnMail.getLayout();
		flowLayout_6.setVgap(0);
		flowLayout_6.setHgap(70);
		pnMail.setBackground(new Color(204, 241, 157));
		pnMail.add(lblMail=new JLabel("Email"));
		pnMail.add(txtMail=new JTextField(15));
		pnTXT.add(pnMail);
		
		JPanel pnChucVu=new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) pnChucVu.getLayout();
		flowLayout_7.setVgap(0);
		flowLayout_7.setHgap(70);
		pnChucVu.setBackground(new Color(204, 241, 157));
		pnChucVu.add(lblChucVu=new JLabel("Chức vụ"));
//		pnChucVu.add(txtChucVu=new JTextField(15));
		cbxChucVu=new JComboBox();
		cbxChucVu.setModel(new DefaultComboBoxModel(new String[] {"Quản lý","Nhân viên"}));
		cbxChucVu.setBackground(new Color(255,255,255));
		pnChucVu.add(cbxChucVu);
		pnTXT.add(pnChucVu);
		pnTXT.add(Box.createVerticalStrut(40));
		
		JPanel pnBtn=new JPanel();
		pnBtn.setBackground(new Color(204, 241, 157));
		pnBtn.setLayout(new GridLayout(3,5));
		pnBtn.add(spacer1=new JLabel());
		pnBtn.add(btnThem=new JButton());
		btnThem.setIcon(new ImageIcon("img\\add-user.png"));
		btnThem.setToolTipText("Thêm nhân viên");
		btnThem.setBackground(new Color(255, 255, 255));
		pnBtn.add(spacer2=new JLabel());
		pnBtn.add(btnXoa=new JButton());
		btnXoa.setIcon(new ImageIcon("img\\delete-user.png"));
		btnXoa.setToolTipText("Xóa nhân viên");
		btnXoa.setBackground(new Color(255, 255, 255));
		pnBtn.add(spacer3=new JLabel());
		
		pnBtn.add(spacer4=new JLabel());
		pnBtn.add(spacer5=new JLabel());
		pnBtn.add(spacer6=new JLabel());
		pnBtn.add(spacer10=new JLabel());
		pnBtn.add(spacer11=new JLabel());
		
		pnBtn.add(spacer7=new JLabel());
		pnBtn.add(btnSua=new JButton());
		btnSua.setIcon(new ImageIcon("img\\update-user.png"));
		btnSua.setToolTipText("Sửa thông tin");
		btnSua.setBackground(new Color(255, 255, 255));
		pnBtn.add(spacer8=new JLabel());
		pnBtn.add(btnLamMoi=new JButton());
		btnLamMoi.setIcon(new ImageIcon("img\\refresh.png"));
		btnLamMoi.setToolTipText("Làm mới");
		btnLamMoi.setBackground(new Color(255, 255, 255));
		pnBtn.add(spacer9=new JLabel());		
		pnTXT.add(pnBtn);
		pnTXT.add(Box.createVerticalStrut(60));
		
		
		
		lblMaNV.setPreferredSize(lblSDT.getPreferredSize());
		lblTenNV.setPreferredSize(lblSDT.getPreferredSize());
//		lblNgayVaoLam.setPreferredSize(lblSDT.getPreferredSize());
		lblLuong.setPreferredSize(lblSDT.getPreferredSize());
		lblDC.setPreferredSize(lblSDT.getPreferredSize());
		lblMail.setPreferredSize(lblSDT.getPreferredSize());
		lblChucVu.setPreferredSize(lblSDT.getPreferredSize());
		txtNgayVaoLam.setPreferredSize(txtSDT.getPreferredSize());
		cbxChucVu.setPreferredSize(txtSDT.getPreferredSize());
		
		JPanel pntblNV=new JPanel();
		pntblNV.setBackground(new Color(204, 241, 157));
		pntblNV.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 529, 559);
		pntblNV.setBounds(466, 60, 576, 629);
		
		pntblNV.add(scrollPane);
		String[] header = { "Mã NV", "Tên NV", "Ngày sinh", "Số điện thoại", "Lương", "Địa chỉ",
				"Email","Chức vụ"};
		modelDanhSachNV = new DefaultTableModel(header, 0);

		table = new JTable(modelDanhSachNV) {
			public boolean editCellAt(int row, int column, EventObject e) 
			{ 
				// Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};
		nvDao=new NhanVien_DAO();
		docDuLieuDatabaseVaoTable();

		JTableHeader header1 = table.getTableHeader();
		header1.setBackground(new Color(238, 233, 233));
		header1.setForeground(Color.black);
		header1.setFont(new Font("Tahoma", Font.BOLD, 14));

		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setRowHeight(25);

//		table.getColumnModel().getColumn(0).setPreferredWidth(70);
//		table.getColumnModel().getColumn(1).setPreferredWidth(120);
//		table.getColumnModel().getColumn(2).setPreferredWidth(120);
//		table.getColumnModel().getColumn(3).setPreferredWidth(120);
//		table.getColumnModel().getColumn(4).setPreferredWidth(80);
//		table.getColumnModel().getColumn(5).setPreferredWidth(70);
//		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		
		cbxTimKiem = new JComboBox();
		cbxTimKiem.setBounds(20, 20, 86, 34);
		cbxTimKiem.setBackground(new Color(255, 255, 255));
		cbxTimKiem.setModel(new DefaultComboBoxModel(new String[] {"Tìm kiếm theo", "Tên nhân viên", "Mã nhân viên", "Chức vụ"}));
		pntblNV.add(cbxTimKiem);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
//		txtTimKiem.setColumns(20);
		txtTimKiem.setBounds(128, 20, 340, 34);
		pntblNV.add(txtTimKiem);

		btnTimKiem = new JButton();
		btnTimKiem.setIcon(new ImageIcon("img\\search.png"));
		btnTimKiem.setBackground(new Color(255, 255, 255));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiem.setForeground(new Color(0, 0, 0));
		btnTimKiem.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnTimKiem.setBounds(487, 20, 86, 34);
		pntblNV.add(btnTimKiem);
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTimKiem.addActionListener(this);
		table.addMouseListener(this);
		
		pn_NV.add(pnTXT);
		pn_NV.add(pntblNV);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pn_NV.setSize(1084,768);
		return pn_NV;
	}
	public void docDuLieuDatabaseVaoTable() {
		modelDanhSachNV.getDataVector().removeAllElements();
		dsNV = nvDao.getAllNV();
		for (NhanVien nv : dsNV) {
			// Format ngay
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String ngaySinh = dateFormat.format(nv.getNgayVaoLam());

			// format tien

			Locale locale = new Locale("vn", "VN");
			NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
			String moneyString = formatter.format(nv.getLuong());
			modelDanhSachNV.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(),ngaySinh,
					nv.getSdt(), moneyString, nv.getDiaChi(),
					nv.getEmail(), nv.getChucVu()});
			
		}
	}
	public void docDuLieuTimKiemVaoTable(int i,String str) {
		int row=modelDanhSachNV.getRowCount();
		for (int j = row-1; j>= 0; j--) {
			modelDanhSachNV.removeRow(j);
		}
		if(i==1) {
			dsNV =nvDao.timTheoTen_VerK(str);
			
		}
		if(i==2) {
			dsNV =nvDao.timTheoMa_VerK(str);
//			int row=modelDanhSachNV.getRowCount();
//			for (int j = row-1; j>= 0; j--) {
//				modelDanhSachNV.removeRow(j);
//			}
		}
		if(i==3) {
			dsNV =nvDao.timTheoChucVu_VerK(str);
//			int row=modelDanhSachNV.getRowCount();
//			for (int j = row-1; j>= 0; j--) {
//				modelDanhSachNV.removeRow(j);
//			}
		}
		for (NhanVien nv : dsNV) {
			// Format ngay
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String ngaySinh = dateFormat.format(nv.getNgayVaoLam());

			// format tien

			Locale locale = new Locale("vn", "VN");
			NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
			String moneyString = formatter.format(nv.getLuong());
			modelDanhSachNV.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(),ngaySinh,
					nv.getSdt(), moneyString, nv.getDiaChi(),
					nv.getEmail(), nv.getChucVu()});
			
		}
	}
	private boolean valid() {
		if(txtMaNV.getText().equals("") || txtTenNV.getText().equals("") || txtNgayVaoLam.getDate().equals("") || 
				txtSDT.getText().equals("") || txtLuong.getText().equals("") ||txtDC.getText().equals("") || 
				txtMail.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
						return false;
					}
		
		if(txtMaNV.getText().charAt(0) != 'N' || txtMaNV.getText().charAt(1) != 'V') {
			JOptionPane.showMessageDialog(this, "Qui tắc đặt mã nhân viên: NV + number");
			return false;
		}
		if(txtSDT.getText().length()!=10) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số");
			return false;
		}
		
		if(Integer.parseInt(txtLuong.getText())<=0 ) {
			JOptionPane.showMessageDialog(this, "Lương nhân viên phải >0");
			return false;
		}
		Date selectedDate = txtNgayVaoLam.getDate();
		LocalDate datachoserDate = LocalDate.ofInstant(selectedDate.toInstant(),ZoneId.systemDefault());
        LocalDate ngayHienTai = LocalDate.now();
		if(datachoserDate.isAfter(ngayHienTai)) {
			JOptionPane.showMessageDialog(this, "Ngày vào làm phải trước ngày hiện tại");
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		try {
			new FrmNhanVien().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(btnThem)) {
			if(valid()) {
				dsNV=nvDao.getAllNV();
				for (NhanVien nv : dsNV) {
					if(nv.getMaNV().equals(txtMaNV.getText())) {
						JOptionPane.showMessageDialog(this,"Trùng mã nhân viên!");
						txtMaNV.requestFocus();
						txtMaNV.selectAll();
						return;
					}
				}
				int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm nhân viên này không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					NhanVien nv = new NhanVien();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String strNgayVaoLam = dateFormat.format(txtNgayVaoLam.getDate());
					java.sql.Date ngayVaoLam = java.sql.Date.valueOf(strNgayVaoLam);
					
					nv.setMaNV(txtMaNV.getText());
					nv.setTenNV(txtTenNV.getText());
					nv.setNgayVaoLam(ngayVaoLam);
					nv.setSdt(txtSDT.getText());
					nv.setLuong(Double.parseDouble(txtLuong.getText()));
					nv.setDiaChi(txtDC.getText());
					nv.setEmail(txtMail.getText());
					nv.setChucVu(cbxChucVu.getSelectedItem().toString());
					if(nvDao.themNhanVien(nv)) {
						JOptionPane.showMessageDialog(this, "Thêm thành công");
						docDuLieuDatabaseVaoTable();
					}
				}
			}
		} else if(o.equals(btnXoa)) {
			dsNV = nvDao.getAllNV();
			String maDB = "";
			int index = 0;
			for(NhanVien x: dsNV) {
				if(x.getMaNV().equals(txtMaNV.getText() )) {
					index++;
					break;
				}
			}
			if(index == 0) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhân viên cần sửa!!");
				txtMaNV.requestFocus();
				txtMaNV.selectAll();
				return;
			} else {
				for(NhanVien x: dsNV) {
					if(x.getMaNV().equals(txtMaNV.getText() )) {
						maDB = x.getMaNV();
						break;
					}
				}
			}
			int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin"
					+ " nhân viên với mã nhân viên là " +maDB+ " không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if(n==0) {
				if(nvDao.xoaNhanVien(txtMaNV.getText())) {
					JOptionPane.showMessageDialog(this,"Xóa thành công");
					docDuLieuDatabaseVaoTable();
				}
			}
		} else if(o.equals(btnSua)) {
			if(valid()) {
				dsNV = nvDao.getAllNV();
				String nameDB = "";
				String maDB = "";
				int index = 0;
				for(NhanVien x: dsNV) {
					if(x.getMaNV().equals(txtMaNV.getText() )) {
						index++;
						break;
					}
				}
				if(index == 0) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhân viên cần sửa!!");
					txtMaNV.requestFocus();
					txtMaNV.selectAll();
					return;
				} else {
					for(NhanVien x: dsNV) {
						if(x.getMaNV().equals(txtMaNV.getText() )) {
							nameDB=x.getTenNV();
							maDB = x.getMaNV();
							break;
						}
					}
				}
				int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin"
						+ " nhân viên " + nameDB + "\n với mã nhân viên là " +maDB+ " không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					NhanVien nv = new NhanVien();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String strNgayVaoLam = dateFormat.format(txtNgayVaoLam.getDate());
					java.sql.Date ngayVaoLam = java.sql.Date.valueOf(strNgayVaoLam);
					
					nv.setMaNV(txtMaNV.getText());
					nv.setTenNV(txtTenNV.getText());
					nv.setNgayVaoLam(ngayVaoLam);
					nv.setSdt(txtSDT.getText());
					nv.setLuong(Double.parseDouble(txtLuong.getText()));
					nv.setDiaChi(txtDC.getText());
					nv.setEmail(txtMail.getText());
					nv.setChucVu(cbxChucVu.getSelectedItem().toString());
					if(nvDao.capNhat(txtMaNV.getText(),nv)) {
						JOptionPane.showMessageDialog(this, "Sửa thành công");
						docDuLieuDatabaseVaoTable();
					}
				}
			}
		} else if(o.equals(btnLamMoi)) {
			txtMaNV.setText("");
			txtTenNV.setText("");
			txtNgayVaoLam.setDate(null);
			txtSDT.setText("");
			txtLuong.setText("");
			txtDC.setText("");
			txtMail.setText("");
			cbxChucVu.setSelectedIndex(0);
		}else if(o.equals(btnTimKiem)) {
			if(cbxTimKiem.getSelectedIndex()==0) {
				docDuLieuDatabaseVaoTable();
			}else if(cbxTimKiem.getSelectedIndex()==1) {
				if(txtTimKiem.getText().equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(this,"Vui lòng nhập tên nhân viên");
				else
					docDuLieuTimKiemVaoTable(1,txtTimKiem.getText());
			}else if(cbxTimKiem.getSelectedIndex()==2) {
				if(txtTimKiem.getText().equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(this,"Vui lòng nhập mã nhân viên");
				else
					docDuLieuTimKiemVaoTable(2,txtTimKiem.getText());
			}else if(cbxTimKiem.getSelectedIndex()==3) {
				if(txtTimKiem.getText().equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(this,"Vui lòng nhập chức vụ");
				else
					docDuLieuTimKiemVaoTable(3,txtTimKiem.getText());
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaNV.setText(modelDanhSachNV.getValueAt(row, 0).toString());
		txtTenNV.setText(modelDanhSachNV.getValueAt(row, 1).toString());
		Date date;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(modelDanhSachNV.getValueAt(row, 2).toString());
			txtNgayVaoLam.setDate(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		txtSDT.setText(modelDanhSachNV.getValueAt(row, 3).toString());
		txtLuong.setText(modelDanhSachNV.getValueAt(row, 4).toString());
		txtDC.setText(modelDanhSachNV.getValueAt(row, 5).toString());
		txtMail.setText(modelDanhSachNV.getValueAt(row, 6).toString());
		cbxChucVu.setSelectedIndex((modelDanhSachNV.getValueAt(row, 7).toString()).equalsIgnoreCase("Quản lý") ? 0:1);
		 	
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

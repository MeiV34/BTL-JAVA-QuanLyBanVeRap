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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class FrmNhanVien extends JFrame implements ActionListener,MouseListener {
	private JLabel lblMaNV;
	private JTextField txtMaNV;
	private JLabel lblTenNV;
	private JTextField txtTenNV;
	private JLabel lblNgaySinh;
	private JDateChooser txtNgaySinh;
	private JLabel lblSDT;
	private JTextField txtSDT;
	private JLabel lblLuong;
	private JTextField txtLuong;
	private JLabel lblDC;
	private JTextField txtDC;
	private JLabel lblMail;
	private JTextField txtMail;
	private JLabel lblChucVu;
	private JTextField txtChucVu;
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
		add(taoFrmNhanVien());
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
		pnNgaySinh.add(lblNgaySinh=new JLabel("Ngày sinh"));
		pnNgaySinh.add(txtNgaySinh=new JDateChooser());
//		JFormattedTextField txtNgaySinh=new JFormattedTextField(new MaskFormatter("dd/MM/yyyy"));
		
		pnTXT.add(pnNgaySinh);
		
		JPanel pnSDT=new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnSDT.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(70);
		pnSDT.setBackground(new Color(204, 241, 157));
		pnSDT.add(lblSDT=new JLabel("Số điện thoại	"));
		pnSDT.add(txtSDT=new JTextField(15));
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
		pnChucVu.add(txtChucVu=new JTextField(15));
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
		
		
		
		lblMaNV.setPreferredSize(new Dimension(58, 13));
		lblTenNV.setPreferredSize(lblSDT.getPreferredSize());
		lblNgaySinh.setPreferredSize(new Dimension(58, 13));
		lblLuong.setPreferredSize(lblSDT.getPreferredSize());
		lblDC.setPreferredSize(lblSDT.getPreferredSize());
		lblMail.setPreferredSize(lblSDT.getPreferredSize());
		lblChucVu.setPreferredSize(lblSDT.getPreferredSize());
		txtNgaySinh.setPreferredSize(txtMaNV.getPreferredSize());
		
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
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
//		txtTimKiem.setColumns(20);
		txtTimKiem.setBounds(113, 20, 340, 34);
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
		btnTimKiem.setBounds(487, 19, 86, 34);
		pntblNV.add(btnTimKiem);
		
		btnLamMoi.addActionListener(this);
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
			String ngaySinh = dateFormat.format(nv.getNgaySinh());

			// format tien

			Locale locale = new Locale("vn", "VN");
			NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
			String moneyString = formatter.format(nv.getLuong());
			modelDanhSachNV.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(),ngaySinh,
					nv.getSdt(), moneyString, nv.getDiaChi(),
					nv.getEmail(), nv.getChucVu()});
			
		}
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
		if(o.equals(btnLamMoi)) {
			txtMaNV.setText("");
			txtTenNV.setText("");
			txtNgaySinh.setDate(null);
			txtSDT.setText("");
			txtLuong.setText("");
			txtDC.setText("");
			txtMail.setText("");
			txtChucVu.setText("");
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
			txtNgaySinh.setDate(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		txtSDT.setText(modelDanhSachNV.getValueAt(row, 3).toString());
		txtLuong.setText(modelDanhSachNV.getValueAt(row, 4).toString());
		txtDC.setText(modelDanhSachNV.getValueAt(row, 5).toString());
		txtMail.setText(modelDanhSachNV.getValueAt(row, 6).toString());
		txtChucVu.setText(modelDanhSachNV.getValueAt(row, 7).toString());
		
//		// Lấy giá trị từ cột 5 và cột 6 của hàng được chọn (cột ngày công chiếu và ngày kết thúc)
//		Date ngayCongChieu = (Date) tablePhimUpdate.getModel().getValueAt(row, 5);
//		Date ngayKetThuc = (Date) tablePhimUpdate.getModel().getValueAt(row, 6);
//
//		// Thiết lập giá trị cho JSpinner
//		txtNgayCCPhim.setValue(ngayCongChieu);
//		txtNgayKTPhim.setValue(ngayKetThuc);
//		
//		txtDanhGiaPhim.setText(modelPhim.getValueAt(row, 7).toString()); 	
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

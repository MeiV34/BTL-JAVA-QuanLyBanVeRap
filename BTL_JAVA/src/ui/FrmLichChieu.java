package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import DAO.LichChieu_DAO;
import DAO.NhanVien_DAO;
import entity.LichChieu;
import entity.NhanVien;
import java.awt.Component;

public class FrmLichChieu extends JFrame implements ActionListener,MouseListener  {
	private JLabel lblMaLichChieu;
	private JTextField txtMaLichChieu;
	private JLabel lblNgayChieu;
	private JDateChooser txtNgayChieu;
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
	private DefaultTableModel modelDanhSachLC;
	private JTable table;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private List<LichChieu> dsLC;
	private LichChieu_DAO lcDao;
	private JButton btnXem;
	public FrmLichChieu() throws ParseException  {
		getContentPane().add(taoFrmLichChieu());
		setSize(1035,682);
	}
	
	public JPanel taoFrmLichChieu() {
		JPanel pn_LichChieu= new JPanel();
		pn_LichChieu.setBackground(new Color(228, 243, 208));
		pn_LichChieu.setLayout(null);
		setLocation(250, 30);
		
		JPanel pnTXT=new JPanel();
		pnTXT.setBounds(655,30,345,588);
		pnTXT.setBackground(new Color(204,241,157));
		
		JPanel pNorth= new JPanel();
		pNorth.setBackground(new Color(204, 241, 157));
		pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.Y_AXIS));
		pNorth.add(Box.createVerticalStrut(30));
		
		JPanel pnMaLichChieu=new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnMaLichChieu.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(50);
		pnMaLichChieu.setBackground(new Color(204, 241, 157));
		pnMaLichChieu.add(lblMaLichChieu=new JLabel("Mã lịch chiếu"));
		pnMaLichChieu.add(txtMaLichChieu=new JTextField(11));
		pNorth.add(pnMaLichChieu);
				
		JPanel pnNgayChieu=new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnNgayChieu.getLayout();
		flowLayout_3.setVgap(15);
		flowLayout_3.setHgap(50);
		pnNgayChieu.setBackground(new Color(204, 241, 157));
		pnNgayChieu.add(lblNgayChieu=new JLabel("Ngày chiếu"));
		pnNgayChieu.add(txtNgayChieu=new JDateChooser());
		txtNgayChieu.setDateFormatString("dd-MM-yyyy");
		pNorth.add(pnNgayChieu); 
		pNorth.add(Box.createVerticalStrut(120));

		
		pnTXT.add(pNorth,BorderLayout.NORTH);
		
		JPanel pnBtn=new JPanel();
		pnBtn.setBackground(new Color(204, 241, 157));
		pnBtn.setLayout(new GridLayout(3,5));
		pnBtn.add(spacer1=new JLabel());
		pnBtn.add(btnThem=new JButton());
		btnThem.setIcon(new ImageIcon("img/plus.png"));
		btnThem.setToolTipText("Thêm lịch chiếu");
		btnThem.setBackground(new Color(255, 255, 255));
		pnBtn.add(spacer2=new JLabel());
		pnBtn.add(btnXoa=new JButton());
		btnXoa.setIcon(new ImageIcon("img/remove.png"));
		btnXoa.setToolTipText("Xóa lịch chiếu");
		btnXoa.setBackground(new Color(255, 255, 255));
		pnBtn.add(spacer3=new JLabel());
		
		pnBtn.add(spacer4=new JLabel());
		pnBtn.add(spacer5=new JLabel());
		pnBtn.add(spacer6=new JLabel());
		pnBtn.add(spacer10=new JLabel());
		pnBtn.add(spacer11=new JLabel());
		
		pnBtn.add(spacer7=new JLabel());
		pnBtn.add(btnSua=new JButton());
		btnSua.setIcon(new ImageIcon("img/update.png"));
		btnSua.setToolTipText("Sửa lịch chiếu");
		btnSua.setBackground(new Color(255, 255, 255));
		pnBtn.add(spacer8=new JLabel());
		pnBtn.add(btnLamMoi=new JButton());
		btnLamMoi.setIcon(new ImageIcon("img/refresh.png"));
		btnLamMoi.setToolTipText("Làm mới");
		btnLamMoi.setBackground(new Color(255, 255, 255));
		pnBtn.add(spacer9=new JLabel());		
		pnTXT.add(pnBtn,BorderLayout.CENTER);

		lblNgayChieu.setPreferredSize(lblMaLichChieu.getPreferredSize());
		txtNgayChieu.setPreferredSize(txtMaLichChieu.getPreferredSize());
		
		JPanel pntblNV=new JPanel();
		pntblNV.setBackground(new Color(204, 241, 157));
		pntblNV.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 553, 518);
		pntblNV.setBounds(20, 30, 601, 588);
		
		pntblNV.add(scrollPane);
		String[] header = { "Mã LC", "Ngày chiếu"};
		modelDanhSachLC = new DefaultTableModel(header, 0);

		table = new JTable(modelDanhSachLC) {
			public boolean editCellAt(int row, int column, EventObject e) 
			{ 
				// Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};
		lcDao=new LichChieu_DAO();
		docDuLieuDatabaseVaoTable();

		JTableHeader header1 = table.getTableHeader();
		header1.setBackground(new Color(238, 233, 233));
		header1.setForeground(Color.black);
		header1.setFont(new Font("Tahoma", Font.BOLD, 14));

		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setRowHeight(25);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTimKiem.setBounds(127, 20, 331, 34);
		pntblNV.add(txtTimKiem);

		btnTimKiem = new JButton();
		btnTimKiem.setIcon(new ImageIcon("img/search.png"));
		btnTimKiem.setBackground(new Color(255, 255, 255));
		btnTimKiem.setForeground(new Color(0, 0, 0));
		btnTimKiem.setBounds(21, 20, 76, 34);
		pntblNV.add(btnTimKiem);
		
		btnXem = new JButton();
		btnXem.setIcon(new ImageIcon("img/file.png"));
		btnXem.setToolTipText("Xem suât chiếu trong ngày");
		btnXem.setEnabled(false);
		btnXem.setBackground(new Color(255, 255, 255));
		btnXem.setForeground(new Color(0, 0, 0));
		btnXem.setBounds(486, 20, 76, 34);
		pntblNV.add(btnXem);
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTimKiem.addActionListener(this);
		table.addMouseListener(this);
		
		pn_LichChieu.add(pnTXT);
		pn_LichChieu.add(pntblNV);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pn_LichChieu.setSize(1084,768);
		return pn_LichChieu;
	}
	public void docDuLieuDatabaseVaoTable() {
		modelDanhSachLC.getDataVector().removeAllElements();
		dsLC = lcDao.getAllLC();
		for (LichChieu lc : dsLC) {
			// Format ngay
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String ngayChieu = dateFormat.format(lc.getNgayChieu());
			modelDanhSachLC.addRow(new Object[] { lc.getMaLichChieu(), ngayChieu});
			
		}
	}
	private boolean valid() {
		if(txtMaLichChieu.getText().equals("") || txtNgayChieu.getDate().equals("")) {
						JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
						return false;
					}
		
		if(txtMaLichChieu.getText().charAt(0) != 'L' || txtMaLichChieu.getText().charAt(1) != 'C') {
			JOptionPane.showMessageDialog(this, "Qui tắc đặt mã lịch chiếu: LC + number");
			return false;
		}
		Date selectedDate = txtNgayChieu.getDate();
		LocalDate datachoserDate = LocalDate.ofInstant(selectedDate.toInstant(),ZoneId.systemDefault());
        LocalDate ngayHienTai = LocalDate.now();
		if(datachoserDate.isBefore(ngayHienTai)) {
			JOptionPane.showMessageDialog(this, "Ngày chiếu phải sau ngày hiện tại");
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		try {
			new FrmLichChieu().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(btnThem)) {
			if(valid()) {
				dsLC=lcDao.getAllLC();
				for (LichChieu lc : dsLC) {
					if(lc.getMaLichChieu().equals(txtMaLichChieu.getText())) {
						JOptionPane.showMessageDialog(this,"Trùng mã lịch chiếu!");
						txtMaLichChieu.requestFocus();
						txtMaLichChieu.selectAll();
						return;
					}
				}
				int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm lịch chiếu này không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					LichChieu lc = new LichChieu();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String strNgayChieu = dateFormat.format(txtNgayChieu.getDate());
					java.sql.Date ngayChieu = java.sql.Date.valueOf(strNgayChieu);
					
					lc.setMaLichChieu(txtMaLichChieu.getText());
					lc.setNgayChieu(ngayChieu);
					if(lcDao.themLichChieu(lc)) {
						JOptionPane.showMessageDialog(this, "Thêm thành công");
						docDuLieuDatabaseVaoTable();
					}
				}
			}
		} else if(o.equals(btnXoa)) {
			dsLC = lcDao.getAllLC();
			String maDB = "";
			int index = 0;
			for(LichChieu x: dsLC) {
				if(x.getMaLichChieu().equals(txtMaLichChieu.getText() )) {
					index++;
					break;
				}
			}
			if(index == 0) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy mã lịch chiếu cần xóa!!");
				txtMaLichChieu.requestFocus();
				txtMaLichChieu.selectAll();
				return;
			} else {
				for(LichChieu x: dsLC) {
					if(x.getMaLichChieu().equals(txtMaLichChieu.getText() )) {
						maDB = x.getMaLichChieu();
						break;
					}
				}
			}
			int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa"
					+ " lịch chiếu với mã lịch chiếu là " +maDB+ " không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if(n==0) {
				if(lcDao.xoaLichChieu(txtMaLichChieu.getText())) {
					JOptionPane.showMessageDialog(this,"Xóa thành công");
					docDuLieuDatabaseVaoTable();
				}
			}
		} else if(o.equals(btnSua)) {
			if(valid()) {
				dsLC = lcDao.getAllLC();
				String ngayChieuDB = "";
				String maDB = "";
				int index = 0;
				for(LichChieu x: dsLC) {
					if(x.getMaLichChieu().equals(txtMaLichChieu.getText() )) {
						index++;
						break;
					}
				}
				if(index == 0) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhân viên cần sửa!!");
					txtMaLichChieu.requestFocus();
					txtMaLichChieu.selectAll();
					return;
				} else {
					for(LichChieu x: dsLC) {
						if(x.getMaLichChieu().equals(txtMaLichChieu.getText() )) {
							DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
							ngayChieuDB = dateFormat.format(x.getNgayChieu());
							maDB = x.getMaLichChieu();
							break;
						}
					}
				}
				int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin"
						+ " lịch chiếu ngày: " + ngayChieuDB + "\n với mã lịch chiếu là " +maDB+ " không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					LichChieu lc = new LichChieu();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String strNgayChieu = dateFormat.format(txtNgayChieu.getDate());
					java.sql.Date ngayChieu = java.sql.Date.valueOf(strNgayChieu);
					
					lc.setMaLichChieu(txtMaLichChieu.getText());
					lc.setNgayChieu(ngayChieu);
					if(lcDao.capNhat(txtMaLichChieu.getText(),lc)) {
						JOptionPane.showMessageDialog(this, "Sửa thành công");
						docDuLieuDatabaseVaoTable();
					}
				}
			}
		} else if(o.equals(btnLamMoi)) {
			txtMaLichChieu.setText("");
			txtNgayChieu.setDate(null);
			docDuLieuDatabaseVaoTable();
			txtMaLichChieu.setEditable(true);
		}else if(o.equals(btnTimKiem)) {
			if(txtTimKiem.getText().equalsIgnoreCase("")) 
				JOptionPane.showMessageDialog(this,"Vui lòng nhập tên nhân viên");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaLichChieu.setText(modelDanhSachLC.getValueAt(row, 0).toString());
		Date date;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(modelDanhSachLC.getValueAt(row, 1).toString());
			txtNgayChieu.setDate(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnXem.setEnabled(true);
		txtMaLichChieu.setEditable(false);
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

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.EventObject;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import DAO.Phim_DAO;
import DAO.Phong_DAO;
import DAO.SuatChieu_DAO;
import entity.LichChieu;
import entity.SuatChieu;

public class FrmSuatChieu extends JDialog implements ActionListener,MouseListener {
	private JLabel lblPhong;
	private JTextField txtMaSC;
	private JLabel lblNgayChieu;
	private JDateChooser txtNgayChieu;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLamMoi;
	private DefaultTableModel modelDanhSachSC;
	private JTable table;
	private SuatChieu_DAO scDao;
	private Phong_DAO phongDao;
	private Phim_DAO phimDao;
	private JTextField txtTimKiem;
	private AbstractButton btnTimKiem;
	private JButton btnXem;
	private List<SuatChieu> dsSC;
	private JLabel lblMaSC;
	private JTextField txtPhong;
	private JLabel lblPhim;
	private JTextField txtPhim;
	private JTextField txtTG;
	private JLabel lblTG;
	private JComboBox cbxPhong;
	private JComboBox cbxPhim;

	public FrmSuatChieu() throws ParseException  {
		getContentPane().add(taoFrmSuatChieu());
		setSize(1084,768);
		setVisible(true);
		setTitle("test");
	}
	
	public JPanel taoFrmSuatChieu() {
		JPanel pn_SuatChieu= new JPanel();
		pn_SuatChieu.setBackground(new Color(228, 243, 208));
		pn_SuatChieu.setLayout(null);
		setLocation(250, 30);
		
		JPanel pnTXT=new JPanel();
		pnTXT.setBounds(655,30,345,588);
		pnTXT.setBackground(new Color(204,241,157));
		
		JPanel pNorth= new JPanel();
		pNorth.setBackground(new Color(204, 241, 157));
		pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.Y_AXIS));
		pNorth.add(Box.createVerticalStrut(30));
		
		JPanel pnMaSuatChieu=new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnMaSuatChieu.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(50);
		pnMaSuatChieu.setBackground(new Color(204, 241, 157));
		pnMaSuatChieu.add(lblMaSC=new JLabel("Mã suất chiếu"));
		pnMaSuatChieu.add(txtMaSC=new JTextField(11));
		pNorth.add(pnMaSuatChieu);
				
		JPanel pnNgayChieu=new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnNgayChieu.getLayout();
		flowLayout_3.setVgap(15);
		flowLayout_3.setHgap(50);
		pnNgayChieu.setBackground(new Color(204, 241, 157));
		pnNgayChieu.add(lblNgayChieu=new JLabel("Ngày chiếu"));
		pnNgayChieu.add(txtNgayChieu=new JDateChooser());
		txtNgayChieu.setDateFormatString("dd-MM-yyyy");
		pNorth.add(pnNgayChieu); 
		
		JPanel pnPhong=new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) pnPhong.getLayout();
		flowLayout_7.setVgap(15);
		flowLayout_7.setHgap(50);
		pnPhong.setBackground(new Color(204, 241, 157));
		pnPhong.add(lblPhong=new JLabel("Phòng"));
		phongDao=new Phong_DAO();
		cbxPhong=new JComboBox();
		cbxPhong.setModel(new DefaultComboBoxModel(phongDao.getALLTenPhong()));
		cbxPhong.setBackground(new Color(255,255,255));
		pnPhong.add(cbxPhong);
		pNorth.add(pnPhong);
		
		JPanel pnPhim=new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pnPhim.getLayout();
		flowLayout_4.setVgap(15);
		flowLayout_4.setHgap(50);
		pnPhim.setBackground(new Color(204, 241, 157));
		pnPhim.add(lblPhim=new JLabel("Phim"));
		phimDao=new Phim_DAO();
		cbxPhim=new JComboBox();
		cbxPhim.setModel(new DefaultComboBoxModel(phimDao.getALLTenPhim()));
		cbxPhim.setBackground(new Color(255,255,255));
		pnPhim.add(cbxPhim);
		pNorth.add(pnPhim);
		
		JPanel pnThoiGian=new JPanel();
		FlowLayout flowLayout3 = (FlowLayout) pnThoiGian.getLayout();
		flowLayout3.setVgap(15);
		flowLayout3.setHgap(50);
		pnThoiGian.setBackground(new Color(204, 241, 157));
		pnThoiGian.add(lblTG=new JLabel("Thời gian"));
		pnThoiGian.add(txtTG=new JTextField(11));
		pNorth.add(pnThoiGian);
		pNorth.add(Box.createVerticalStrut(60));

		
		pnTXT.add(pNorth,BorderLayout.NORTH);
		
		JPanel pnBtn=new JPanel();
		pnBtn.setBackground(new Color(204, 241, 157));
		pnBtn.setLayout(new GridLayout(3,5));
		pnBtn.add(new JLabel());
		pnBtn.add(btnThem=new JButton());
		btnThem.setIcon(new ImageIcon("img/plus.png"));
		btnThem.setToolTipText("Thêm lịch chiếu");
		btnThem.setBackground(new Color(255, 255, 255));
		pnBtn.add(new JLabel());
		pnBtn.add(btnXoa=new JButton());
		btnXoa.setIcon(new ImageIcon("img/remove.png"));
		btnXoa.setToolTipText("Xóa lịch chiếu");
		btnXoa.setBackground(new Color(255, 255, 255));
		pnBtn.add(new JLabel());
		
		pnBtn.add(new JLabel());
		pnBtn.add(new JLabel());
		pnBtn.add(new JLabel());
		pnBtn.add(new JLabel());
		pnBtn.add(new JLabel());
		
		pnBtn.add(new JLabel());
		pnBtn.add(btnSua=new JButton());
		btnSua.setIcon(new ImageIcon("img/update.png"));
		btnSua.setToolTipText("Sửa lịch chiếu");
		btnSua.setBackground(new Color(255, 255, 255));
		pnBtn.add(new JLabel());
		pnBtn.add(btnLamMoi=new JButton());
		btnLamMoi.setIcon(new ImageIcon("img/refresh.png"));
		btnLamMoi.setToolTipText("Làm mới");
		btnLamMoi.setBackground(new Color(255, 255, 255));
		pnBtn.add(new JLabel());		
		pnTXT.add(pnBtn,BorderLayout.CENTER);

		lblNgayChieu.setPreferredSize(lblMaSC.getPreferredSize());
		lblPhong.setPreferredSize(lblMaSC.getPreferredSize());
		lblPhim.setPreferredSize(lblMaSC.getPreferredSize());
		lblTG.setPreferredSize(lblPhong.getPreferredSize());
		txtNgayChieu.setPreferredSize(txtMaSC.getPreferredSize());
		cbxPhong.setPreferredSize(txtMaSC.getPreferredSize());
		cbxPhim.setPreferredSize(txtMaSC.getPreferredSize());
		
		JPanel pntblNV=new JPanel();
		pntblNV.setBackground(new Color(204, 241, 157));
		pntblNV.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 553, 518);
		pntblNV.setBounds(20, 30, 601, 588);
		
		pntblNV.add(scrollPane);
		String[] header = { "Mã SC", "Ngày chiếu","Phòng","Phim","Thời gian",};
		modelDanhSachSC = new DefaultTableModel(header, 0);

		table = new JTable(modelDanhSachSC) {
			public boolean editCellAt(int row, int column, EventObject e) 
			{ 
				// Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};
		scDao=new SuatChieu_DAO();
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
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTimKiem.addActionListener(this);
		table.addMouseListener(this);
		
		pn_SuatChieu.add(pnTXT);
		pn_SuatChieu.add(pntblNV);
		
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pn_SuatChieu.setSize(1084,768);
		return pn_SuatChieu;
	}
	public void docDuLieuDatabaseVaoTable() {
		modelDanhSachSC.getDataVector().removeAllElements();
		dsSC = scDao.getAllSC();
		for (SuatChieu sc : dsSC) {
			// Format ngay
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String ngayChieu = dateFormat.format(sc.getLichChieu().getNgayChieu());
			modelDanhSachSC.addRow(new Object[] { sc.getMaSC(), ngayChieu
					,sc.getPhong().getTenPhong(),sc.getPhim().getTenPhim(),sc.getSuatChieu()});
			
		}
	}
	private boolean valid() {
		if(txtMaSC.getText().equals("") || txtNgayChieu.getDate().equals("")) {
						JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
						return false;
					}
		
		if(txtMaSC.getText().charAt(0) != 'L' || txtMaSC.getText().charAt(1) != 'C') {
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
//		Object o=e.getSource();
//		if(o.equals(btnThem)) {
//			if(valid()) {
//				dsSC=scDao.getAllSC();
//				for (SuatChieu sc : dsSC) {
//					if(sc.getMaSC().equals(txtMaSC.getText())) {
//						JOptionPane.showMessageDialog(this,"Trùng mã suất chiếu!");
//						txtMaSC.requestFocus();
//						txtMaSC.selectAll();
//						return;
//					}
//				}
//				int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm suất chiếu này không?", "Cảnh báo",
//						JOptionPane.YES_NO_OPTION);
//				if (n == 0) {
//					SuatChieu sc = new SuatChieu();
//					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//					String strNgayChieu = dateFormat.format(txtNgayChieu.getDate());
//					java.sql.Date ngayChieu = java.sql.Date.valueOf(strNgayChieu);
//					
//					sc.setMaLichChieu(txtMaSC.getText());
//					sc.setNgayChieu(ngayChieu);
//					if(scDao.themLichChieu(sc)) {
//						JOptionPane.showMessageDialog(this, "Thêm thành công");
//						docDuLieuDatabaseVaoTable();
//					}
//				}
//			}
//		} else if(o.equals(btnXoa)) {
//			dsSC = scDao.getAllLC();
//			String maDB = "";
//			int index = 0;
//			for(LichChieu x: dsSC) {
//				if(x.getMaLichChieu().equals(txtMaSC.getText() )) {
//					index++;
//					break;
//				}
//			}
//			if(index == 0) {
//				JOptionPane.showMessageDialog(this, "Không tìm thấy mã lịch chiếu cần xóa!!");
//				txtMaSC.requestFocus();
//				txtMaSC.selectAll();
//				return;
//			} else {
//				for(LichChieu x: dsSC) {
//					if(x.getMaLichChieu().equals(txtMaSC.getText() )) {
//						maDB = x.getMaLichChieu();
//						break;
//					}
//				}
//			}
//			int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa"
//					+ " lịch chiếu với mã lịch chiếu là " +maDB+ " không?", "Cảnh báo",
//					JOptionPane.YES_NO_OPTION);
//			if(n==0) {
//				if(scDao.xoaLichChieu(txtMaSC.getText())) {
//					JOptionPane.showMessageDialog(this,"Xóa thành công");
//					docDuLieuDatabaseVaoTable();
//				}
//			}
//		} else if(o.equals(btnSua)) {
//			if(valid()) {
//				dsSC = scDao.getAllLC();
//				String ngayChieuDB = "";
//				String maDB = "";
//				int index = 0;
//				for(LichChieu x: dsSC) {
//					if(x.getMaLichChieu().equals(txtMaSC.getText() )) {
//						index++;
//						break;
//					}
//				}
//				if(index == 0) {
//					JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhân viên cần sửa!!");
//					txtMaSC.requestFocus();
//					txtMaSC.selectAll();
//					return;
//				} else {
//					for(LichChieu x: dsSC) {
//						if(x.getMaLichChieu().equals(txtMaSC.getText() )) {
//							DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//							ngayChieuDB = dateFormat.format(x.getNgayChieu());
//							maDB = x.getMaLichChieu();
//							break;
//						}
//					}
//				}
//				int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin"
//						+ " lịch chiếu ngày: " + ngayChieuDB + "\n với mã lịch chiếu là " +maDB+ " không?", "Cảnh báo",
//						JOptionPane.YES_NO_OPTION);
//				if (n == 0) {
//					LichChieu lc = new LichChieu();
//					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//					String strNgayChieu = dateFormat.format(txtNgayChieu.getDate());
//					java.sql.Date ngayChieu = java.sql.Date.valueOf(strNgayChieu);
//					
//					lc.setMaLichChieu(txtMaSC.getText());
//					lc.setNgayChieu(ngayChieu);
//					if(scDao.capNhat(txtMaSC.getText(),lc)) {
//						JOptionPane.showMessageDialog(this, "Sửa thành công");
//						docDuLieuDatabaseVaoTable();
//					}
//				}
//			}
//		} else if(o.equals(btnLamMoi)) {
//			txtMaSC.setText("");
//			txtNgayChieu.setDate(null);
//			docDuLieuDatabaseVaoTable();
//			txtMaSC.setEditable(true);
//			txtPhong.setText("");
//		}else if(o.equals(btnTimKiem)) {
//			if(txtTimKiem.getText().equalsIgnoreCase("")) 
//				JOptionPane.showMessageDialog(this,"Vui lòng nhập tên nhân viên");
//		}
	}
	public int checkCbx(JComboBox cbx, String str) {
		int index=0;
		for (int i = 0; i < cbx.getItemCount(); i++) {
			String cbxItem=cbx.getItemAt(i).toString();
			if(cbxItem.contains(str)) {
				index=i;
				break;
			}
		}
		return index;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaSC.setText(modelDanhSachSC.getValueAt(row, 0).toString());
		Date date;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(modelDanhSachSC.getValueAt(row, 1).toString());
			txtNgayChieu.setDate(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbxPhong.setSelectedIndex(checkCbx(cbxPhong, modelDanhSachSC.getValueAt(row, 2).toString()));
		cbxPhim.setSelectedIndex(checkCbx(cbxPhim, modelDanhSachSC.getValueAt(row, 3).toString()));
		txtTG.setText(modelDanhSachSC.getValueAt(row, 4).toString());
		txtMaSC.setEditable(false);
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

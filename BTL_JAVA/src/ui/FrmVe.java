package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import java.sql.Connection;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.EventObject;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import DAO.LichChieu_DAO;
import DAO.Ve_DAO;
import connectDB.ConnectDB;
import entity.ChiTietSuatChieu;
import entity.LichChieu;
import entity.Phim;

public class FrmVe extends JFrame implements ActionListener,MouseListener {
	private JLabel labPhong, labTenPhim, labSuatChieu, labSoGhe, labSoLuongVe, labNgayChieu,
				   labMaKH, labTenKH, labEmail, labSDT,
				   labThanhTien,
				   labVND;
	private JDateChooser txtSuatChieu;
	private JTextField txtPhong, txtTenPhim, txtSoGhe, txtSoLuongVe,txtNgayChieu,
	   				   txtMaKH, txtTenKH, txtEmail, txtSDT,
					   txtThanhTien;
	private JButton btnDatVe, btnBanVe;
	private JTable tableLichChieu, tableSuatChieu;
	private DefaultTableModel modelLichChieu, modelSuatChieu;
	private JScrollPane scrollLichChieu, scrollSuatChieu;
	private Connection con;
	private LichChieu_DAO lichChieu_DAO = new LichChieu_DAO(); 
	private Ve_DAO ve_DAO = new Ve_DAO();
	public FrmVe() throws ParseException, IOException  {
		add(taoFrmVe());
		setSize(1030,645);
	}
	public JPanel taoFrmVe() throws IOException {
		// Connect 
		ConnectDB.getInstance().connect();
		con = ConnectDB.getConnection();// Gán kết nối từ ConnectDB vào biến thành viên con
		if (con == null) {
			JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu");
			return null;
		}
		
		// Icon
		BufferedImage originalImage1  = ImageIO.read(new File("img//Ticket-add.png"));
        Image scaledImage1 = originalImage1.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imgIcon1 = new ImageIcon(scaledImage1);
		
		BufferedImage originalImage2  = ImageIO.read(new File("img//Payment.png"));
        Image scaledImage2 = originalImage2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imgIcon2 = new ImageIcon(scaledImage2);
		// Font
		Font font = new Font("Arials",Font.BOLD,20);
		// Label
		labPhong = new JLabel("Phòng:");
		labTenPhim = new JLabel("Tên phim:");
		labSuatChieu = new JLabel("Suất chiếu:");
		labSoGhe = new JLabel("Số ghế:");
		labSoLuongVe = new JLabel("Số lượng vé:");
		labMaKH = new JLabel("Mã khách hàng:");
		labTenKH = new JLabel("Tên khách hàng:");
		labEmail = new JLabel("Email:");
		labSDT = new JLabel("Số điện thoại:");
		labThanhTien = new JLabel("Thành tiền:");
		labThanhTien.setFont(font);
		labVND = new JLabel("VND");
		labVND.setFont(font);
		labVND.setForeground(Color.red);
		labVND.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));
		labNgayChieu = new JLabel("Ngày chiếu:");
		
		// TextField
		txtPhong = new JTextField(15);
		txtPhong.setFocusable(false);
		txtTenPhim = new JTextField(15);
		txtTenPhim.setFocusable(false);
		txtNgayChieu = new JTextField();
		txtNgayChieu.setFocusable(false);
		txtSuatChieu = new JDateChooser();
		txtSuatChieu.setFocusable(false);
		txtSoGhe = new JTextField(15);
		txtSoGhe.setFocusable(false);
		txtSoLuongVe = new JTextField(15);
		txtSoLuongVe.setFocusable(false);
		txtMaKH = new JTextField(15);
		txtTenKH = new JTextField(15);
		txtEmail = new JTextField(15);
		txtSDT = new JTextField(15);
		txtThanhTien = new JTextField();
		txtThanhTien.setFocusable(false);
		
		// Button
		Border in = BorderFactory.createEmptyBorder(10, 50, 10, 50);
		Border out = BorderFactory.createTitledBorder("");
		Border com = BorderFactory.createCompoundBorder(out,in);
		btnBanVe = new JButton(imgIcon2);
		btnBanVe.setBorder(com);
		btnDatVe = new JButton(imgIcon1);
		btnDatVe.setBorder(com);
		
		// Table
		String[] lichchieu = {"Mã lịch chiếu", "Ngày chiếu"};
		String[] suatchieu = {"Mã suất chiếu", "Phim", "Ngày chiếu","Phòng","Suất chiếu"};
		modelLichChieu = new DefaultTableModel(lichchieu,0);
		modelSuatChieu = new DefaultTableModel(suatchieu,0);
		tableLichChieu = new JTable(modelLichChieu){
			public boolean editCellAt(int row, int column, EventObject e) 
			{ 
				// Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};
		tableSuatChieu = new JTable(modelSuatChieu){
			public boolean editCellAt(int row, int column, EventObject e) 
			{ 
				// Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};
		scrollLichChieu = new JScrollPane(tableLichChieu);
		scrollSuatChieu = new JScrollPane(tableSuatChieu);
		
		// Panel
		JPanel pnlVe = new JPanel();
		pnlVe.setLayout(new BorderLayout());
		pnlVe.setBackground(new Color(228,243,208));
		pnlVe.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			JPanel pnlVeLeft = new JPanel();
			pnlVeLeft.setLayout(new BorderLayout());
			pnlVeLeft.setBackground(new Color(204, 241, 157));
				JPanel pnlVeLeftTop = new JPanel();
				pnlVeLeftTop.setLayout(new BoxLayout(pnlVeLeftTop, BoxLayout.X_AXIS));
				pnlVeLeftTop.setBackground(new Color(204, 241, 157));
				Border inBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
				Border outBorder1 = BorderFactory.createTitledBorder("Thông tin vé");
				Border comBorder1 = BorderFactory.createCompoundBorder(outBorder1,inBorder);
				pnlVeLeftTop.setBorder(comBorder1);
					JPanel pnlVeLeftTopLeft = new JPanel();
					pnlVeLeftTopLeft.setLayout(new BoxLayout(pnlVeLeftTopLeft, BoxLayout.Y_AXIS));
					pnlVeLeftTopLeft.setBackground(new Color(204, 241, 157));
					pnlVeLeftTopLeft.add(labPhong);
					pnlVeLeftTopLeft.add(Box.createVerticalStrut(39));
					pnlVeLeftTopLeft.add(labTenPhim);
					pnlVeLeftTopLeft.add(Box.createVerticalStrut(39));
					pnlVeLeftTopLeft.add(labNgayChieu);
					pnlVeLeftTopLeft.add(Box.createVerticalStrut(39));
					pnlVeLeftTopLeft.add(labSuatChieu);
					pnlVeLeftTopLeft.add(Box.createVerticalStrut(39));
					pnlVeLeftTopLeft.add(labSoGhe);
					pnlVeLeftTopLeft.add(Box.createVerticalStrut(39));
					pnlVeLeftTopLeft.add(labSoLuongVe);
					JPanel pnlVeLeftTopRight = new JPanel();
					pnlVeLeftTopRight.setLayout(new BoxLayout(pnlVeLeftTopRight, BoxLayout.Y_AXIS));
					pnlVeLeftTopRight.setBackground(new Color(204, 241, 157));
					pnlVeLeftTopRight.add(txtPhong);
					pnlVeLeftTopRight.add(Box.createVerticalStrut(33));
					pnlVeLeftTopRight.add(txtTenPhim);
					pnlVeLeftTopRight.add(Box.createVerticalStrut(33));
					pnlVeLeftTopRight.add(txtNgayChieu);
					pnlVeLeftTopRight.add(Box.createVerticalStrut(33));
					pnlVeLeftTopRight.add(txtSuatChieu);
					pnlVeLeftTopRight.add(Box.createVerticalStrut(33));
					pnlVeLeftTopRight.add(txtSoGhe);
					pnlVeLeftTopRight.add(Box.createVerticalStrut(33));
					pnlVeLeftTopRight.add(txtSoLuongVe);
				pnlVeLeftTop.add(pnlVeLeftTopLeft,BorderLayout.WEST);
				pnlVeLeftTop.add(Box.createHorizontalStrut(32));
				pnlVeLeftTop.add(pnlVeLeftTopRight,BorderLayout.CENTER);
				
				JPanel pnlVeLeftCen = new JPanel();
				pnlVeLeftCen.setLayout(new BoxLayout(pnlVeLeftCen, BoxLayout.X_AXIS));
				pnlVeLeftCen.setBackground(new Color(204, 241, 157));
				Border outBorder2 = BorderFactory.createTitledBorder("Thông tin khách hàng");
				Border comBorder2 = BorderFactory.createCompoundBorder(outBorder2, inBorder);
				pnlVeLeftCen.setBorder(comBorder2);
					JPanel pnlVeLeftCenLeft = new JPanel();
					pnlVeLeftCenLeft.setLayout(new BoxLayout(pnlVeLeftCenLeft, BoxLayout.Y_AXIS));
					pnlVeLeftCenLeft.setBackground(new Color(204, 241, 157));
					pnlVeLeftCenLeft.add(labMaKH);
					pnlVeLeftCenLeft.add(Box.createVerticalStrut(29));
					pnlVeLeftCenLeft.add(labTenKH);
					pnlVeLeftCenLeft.add(Box.createVerticalStrut(29));
					pnlVeLeftCenLeft.add(labEmail);
					pnlVeLeftCenLeft.add(Box.createVerticalStrut(29));
					pnlVeLeftCenLeft.add(labSDT);
					JPanel pnlVeLeftCenRight = new JPanel();
					pnlVeLeftCenRight.setLayout(new BoxLayout(pnlVeLeftCenRight, BoxLayout.Y_AXIS));
					pnlVeLeftCenRight.setBackground(new Color(204, 241, 157));
					pnlVeLeftCenRight.add(txtMaKH);
					pnlVeLeftCenRight.add(Box.createVerticalStrut(20));
					pnlVeLeftCenRight.add(txtTenKH);
					pnlVeLeftCenRight.add(Box.createVerticalStrut(20));
					pnlVeLeftCenRight.add(txtEmail);
					pnlVeLeftCenRight.add(Box.createVerticalStrut(20));
					pnlVeLeftCenRight.add(txtSDT);
				pnlVeLeftCen.add(pnlVeLeftCenLeft);
				pnlVeLeftCen.add(Box.createHorizontalStrut(10));
				pnlVeLeftCen.add(pnlVeLeftCenRight);
					
				JPanel pnlVeLeftBottom = new JPanel();
				pnlVeLeftBottom.setLayout(new BorderLayout());
				pnlVeLeftBottom.setBackground(new Color(224, 249, 194, 1));
				Border outBorder3 = BorderFactory.createTitledBorder("Thành tiền");
				Border comBorder3 = BorderFactory.createCompoundBorder(outBorder3, inBorder);
				pnlVeLeftBottom.setBorder(comBorder3);
				pnlVeLeftBottom.add(labThanhTien,BorderLayout.NORTH);
					JPanel pnlVND = new JPanel();
					pnlVND.setLayout(new BorderLayout());
					pnlVND.setBackground(new Color(224, 249, 194, 1));
					pnlVND.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
					pnlVND.add(txtThanhTien ,BorderLayout.CENTER);
					pnlVND.add(labVND ,BorderLayout.EAST);
				pnlVeLeftBottom.add(pnlVND,BorderLayout.SOUTH);
			pnlVeLeft.add(pnlVeLeftTop,BorderLayout.NORTH);
			pnlVeLeft.add(pnlVeLeftCen,BorderLayout.CENTER);
			pnlVeLeft.add(pnlVeLeftBottom,BorderLayout.SOUTH);
			
			JPanel pnlVeRight = new JPanel();
			pnlVeRight.setLayout(new BoxLayout(pnlVeRight, BoxLayout.Y_AXIS));
			pnlVeRight.setBackground(new Color(204, 241, 157));
				JPanel pnlVeRightTop = new JPanel();
				pnlVeRightTop.setLayout(new BoxLayout(pnlVeRightTop, BoxLayout.X_AXIS));
				pnlVeRightTop.add(scrollLichChieu);
				JPanel pnlVeRightCen = new JPanel();
				pnlVeRightCen.setLayout(new BoxLayout(pnlVeRightCen, BoxLayout.X_AXIS));
				pnlVeRightCen.add(scrollSuatChieu);
				JPanel pnlVeRightBottom = new JPanel();
				pnlVeRightBottom.setLayout(new BoxLayout(pnlVeRightBottom, BoxLayout.X_AXIS));
				pnlVeRightBottom.setBackground(new Color(204, 241, 157));
				pnlVeRightBottom.add(Box.createHorizontalStrut(400));
				pnlVeRightBottom.add(btnDatVe);
				pnlVeRightBottom.add(Box.createHorizontalStrut(10));
				pnlVeRightBottom.add(btnBanVe);
			pnlVeRight.add(pnlVeRightTop);
			pnlVeRight.add(Box.createVerticalStrut(20));
			pnlVeRight.add(pnlVeRightCen);
			pnlVeRight.add(Box.createVerticalStrut(20));
			pnlVeRight.add(pnlVeRightBottom);
			pnlVeRight.add(Box.createVerticalStrut(20));
		pnlVe.add(pnlVeLeft, BorderLayout.WEST);
		pnlVe.add(pnlVeRight, BorderLayout.CENTER);
		
		// ActionListener
		tableLichChieu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableLichChieu.getSelectedRow();
				Date ngayChieu = (Date) tableLichChieu.getModel().getValueAt(row, 1);
				DocDuSCVaoTable(ngayChieu);
			}
		});
		tableSuatChieu.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = tableSuatChieu.getSelectedRow();
		        String maSuatChieu = (String) tableSuatChieu.getValueAt(selectedRow, 0); // Lấy mã suất chiếu từ bảng "Suất chiếu"
		        
		    }
		});

		// Dọc dữ liệu 
		DocDuLCVaoTable();
		
		pnlVe.setSize(1040,645);
		return pnlVe;
	}

	public void DocDuLieuSuatChieuVaoTable() {
		List<ChiTietSuatChieu> listSC = ve_DAO.getListSuatChieu();
		for (ChiTietSuatChieu sc : listSC) {
			modelSuatChieu.addRow(new Object[] {sc.getMaSC(),sc.getPhim(), sc.getNgayChieu(),
					sc.getPhong(),sc.getSc()});		
		}
		tableSuatChieu.setModel(modelSuatChieu);
	}
	public void DocDuLCVaoTable() {
		List<LichChieu> listchieus = lichChieu_DAO.getAllLC();
		for (LichChieu lc : listchieus) {
			modelLichChieu.addRow(new Object[] {lc.getMaLichChieu(),lc.getNgayChieu()});		
		}
		tableLichChieu.setModel(modelLichChieu);
	}
	private Icon createImage(URL resource) {
		// TODO Auto-generated method stub
		return null;
	}
	public void XoaTrangTable() {
		DefaultTableModel d = (DefaultTableModel) tableSuatChieu.getModel();
		d.getDataVector().removeAllElements();
	}
	public void DocDuSCVaoTable(Date ngayChieu) {
		XoaTrangTable();
		List<ChiTietSuatChieu> suatChieu = ve_DAO.timTheoNgayChieu(ngayChieu);
		for (ChiTietSuatChieu sc : suatChieu) {
			modelSuatChieu.addRow(new Object[] {sc.getMaSC(),sc.getPhim(), sc.getNgayChieu(),
					sc.getPhong(),sc.getSc()});			
		}
		tableSuatChieu.setModel(modelSuatChieu);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableLichChieu.getSelectedRow();
		Date ngayChieu = (Date) tableLichChieu.getModel().getValueAt(row, 1);
		DocDuSCVaoTable(ngayChieu);
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		try {
			new FrmVe().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


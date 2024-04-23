package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

import connectDB.*;
import DAO.Phim_DAO;
import ui.FrmNhanVien;
import entity.Phim;

public class FrmPhim extends JFrame implements ActionListener,MouseListener{
	private JButton btnMain , btnPhim, btnLich, btnVe, btnPhong, btnKhachHang, btnNhanVien,
					btnMenu,
					btnFind,btnAdd,btnDel,btnDelAll,btnUpdate;
	private JLabel labTenRap, labImgLogo ,
				   labIdPhim, labTenPhim, labDaoDienPhim, labTheLoaiPhim, labThoiLuongPhim,labNgayCCPhim,labNgayKTPhim,labDanhGiaPhim,
				   labPoster1, labPoster2; 
	private JTextField txtFind,
			   		   txtIdPhim, txtTenPhim, txtDaoDienPhim, txtThoiLuongPhim, txtDanhGiaPhim;
	private JSpinner txtNgayCCPhim,txtNgayKTPhim;
	private JComboBox<String> cboTheLoai;
	private JMenuBar menuBar;
	private JMenu LogOutMenu;
	private DefaultTableModel modelPhim;
	private JTable tablePhimUpdate ;
	private JTable tablePhimFind ;
	// DAO
	private Phim_DAO phim_DAO = new Phim_DAO();
	private Connection con;
	private String[] head;
	private JLayeredPane layeredPane = new JLayeredPane();
	private FrmNhanVien frmNhanVien;
	private FrmLichChieu frmLichChieu;
	private JPanel pnlNV, pnlVe;
	private JPanel pnlLichChieu;
	private FrmKhachHang frmKH;
//	private FrmBanVe frmBanVe;
	public void resetTargetMenu() {
		btnMain.setBackground(new Color(204, 241, 157));
        btnPhim.setBackground(new Color(204, 241, 157));
        btnLich.setBackground(new Color(204, 241, 157));
        btnLich.setBackground(new Color(204, 241, 157));
        btnVe.setBackground(new Color(204, 241, 157));
        btnPhong.setBackground(new Color(204, 241, 157));
        btnKhachHang.setBackground(new Color(204, 241, 157));
        btnNhanVien.setBackground(new Color(204, 241, 157));
	}
	
	public FrmPhim() throws IOException, SQLException, ParseException {
		super("Rạp Phim BHV");
		
		// Connect 
		ConnectDB.getInstance().connect();
		con = ConnectDB.getConnection();// Gán kết nối từ ConnectDB vào biến thành viên con
        if (con == null) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu");
            return;
        }
		JPanel pnlAll = new JPanel();
		pnlAll.setLayout(new BorderLayout());
		
		// Table Phim
		head = new String[]{"Mã phim", "Tên", "Đạo diễn", "Thể loại","Thời lượng"
				,"Ngày công chiếu", "Ngày kết thúc","Đánh giá"};
		modelPhim = new DefaultTableModel(head,0);
		tablePhimUpdate = new JTable(modelPhim);
		tablePhimFind = new JTable(modelPhim);
		JScrollPane scrollUpdate = new JScrollPane(tablePhimUpdate);
		JScrollPane scrollFind = new JScrollPane(tablePhimFind);

		// Img
			// Logo
	        BufferedImage originalImage  = ImageIO.read(new File("img/OIP.jpg"));
	        int newWidth = 74; 
            int newHeight = 74; 
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setClip(new Ellipse2D.Float(0, 0, newWidth, newHeight));
            g2.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g2.dispose();
            
			ImageIcon imgIcon1 = new ImageIcon(resizedImage);
			// Bars Menu
			BufferedImage originalImage2  = ImageIO.read(new File("img/bars-solid.jpg"));
            Image scaledImage2 = originalImage2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			
			ImageIcon imgIcon2 = new ImageIcon(scaledImage2);
			// Img posters
			BufferedImage originalImage3  = ImageIO.read(new File("img/poster1.jpg"));
            Image scaledImage3 = originalImage3.getScaledInstance(425, 670, Image.SCALE_SMOOTH);
            
			BufferedImage originalImage4  = ImageIO.read(new File("img/poster2.jpg"));
            Image scaledImage4 = originalImage4.getScaledInstance(425, 670, Image.SCALE_SMOOTH);
			
		    ImageIcon imgIcon3 = new ImageIcon(scaledImage3);
			ImageIcon imgIcon4 = new ImageIcon(scaledImage4);
			
			// Find
			BufferedImage image5  = ImageIO.read(new File("img/magnifying-glass-solid.jpg"));
            Image scaledImage5 = image5.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            ImageIcon imgIcon5 = new ImageIcon(scaledImage5);
            
		// Font
		Font font1 = new Font("Arials",Font.BOLD,16);
		Font font2 = new Font("Arials",Font.BOLD,20);
		Font font3 = new Font("Arials",Font.CENTER_BASELINE,20);
		
		// DateTime
        
		txtNgayCCPhim = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor date1 = new JSpinner.DateEditor(txtNgayCCPhim, "dd/MM/yyyy");
        txtNgayCCPhim.setEditor(date1);
        txtNgayCCPhim.setValue(new Date());
        
        txtNgayKTPhim = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor date2 = new JSpinner.DateEditor(txtNgayKTPhim, "dd/MM/yyyy");
        txtNgayKTPhim.setEditor(date2);
        txtNgayKTPhim.setValue(new Date());
		
        
		// Label 
		labImgLogo = new JLabel(imgIcon1);
		labTenRap = new JLabel("Rạp phim BHV");
		labTenRap.setForeground(Color.white);
		labTenRap.setFont(font2);
		
		labPoster1 = new JLabel(imgIcon3);
		labPoster2 = new JLabel(imgIcon4);
		// Label Phim
		labIdPhim = new JLabel("Mã phim:");
		labTenPhim = new JLabel("Tên phim:");
		labDaoDienPhim = new JLabel("Đạo diễn phim:");
		labTheLoaiPhim = new JLabel("Thể loại:");
		labThoiLuongPhim = new JLabel("Thời lượng phim:");
		labNgayCCPhim = new JLabel("Ngày công chiếu:");
		labNgayKTPhim = new JLabel("Ngày kết thúc:");
		labDanhGiaPhim = new JLabel("Đánh giá:");
		
		// Button
		btnMain = new JButton("Trang Chủ");
		btnMain.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50));
		btnMain.setBackground(new Color(12, 138, 255));
		btnMain.setFont(font1);
		
		btnPhim = new JButton("Phim");
		btnPhim.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
		btnPhim.setBackground(new Color(204, 241, 157));
		btnPhim.setFont(font1);
		
		btnLich = new JButton("Lịch Chiếu");
		btnLich.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
		btnLich.setBackground(new Color(204, 241, 157));
		btnLich.setFont(font1);
		
		btnVe = new JButton("Vé");
		btnVe.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
		btnVe.setBackground(new Color(204, 241, 157));
		btnVe.setFont(font1);
		
		btnPhong = new JButton("Phòng");
		btnPhong.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
		btnPhong.setBackground(new Color(204, 241, 157));
		btnPhong.setFont(font1);
		
		btnKhachHang = new JButton("Khách Hàng");
		btnKhachHang.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
		btnKhachHang.setBackground(new Color(204, 241, 157));
		btnKhachHang.setFont(font1);
		
		btnNhanVien = new JButton("Nhân Viên");
		btnNhanVien.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
		btnNhanVien.setBackground(new Color(204, 241, 157));
		btnNhanVien.setFont(font1);
		
		btnMenu = new JButton(imgIcon2);
		
		btnFind = new JButton(imgIcon5);
		btnAdd = new JButton("Thêm");
		btnDel = new JButton("Xóa");
		btnDelAll = new JButton("Làm mới");
		btnUpdate = new JButton("Sửa");
		
		// TextField
		txtFind = new JTextField();
 		txtFind.setPreferredSize(new Dimension(650,30));
 		txtIdPhim = new JTextField();
 		txtTenPhim = new JTextField();
 		txtDaoDienPhim = new JTextField();
 		txtThoiLuongPhim = new JTextField();
 		txtDanhGiaPhim = new JTextField();
		
 		
 		// Menu item Phim
 		JMenuItem UpdateFilm = new JMenuItem("Cập nhật");
 		UpdateFilm.setPreferredSize(new Dimension(200, 50)); 
 		UpdateFilm.setBackground(new Color(204, 241, 157));
        JMenuItem FindFilm = new JMenuItem("Tìm kiếm");
        FindFilm.setPreferredSize(new Dimension(200, 50));
        FindFilm.setBackground(new Color(204, 241, 157));
        JMenuItem ListFilm = new JMenuItem("Xem danh sách Phim");
        ListFilm.setPreferredSize(new Dimension(200, 50)); 
        ListFilm.setBackground(new Color(204, 241, 157));
        
        // Menu item Lịch chiếu
      	JMenuItem UpdateLich = new JMenuItem("Cập nhật");
      	UpdateLich.setPreferredSize(new Dimension(200, 50));
      	UpdateLich.setBackground(new Color(204, 241, 157));
        JMenuItem FindLich = new JMenuItem("Tìm kiếm");
        FindLich.setPreferredSize(new Dimension(200, 50)); 
        FindLich.setBackground(new Color(204, 241, 157));
        JMenuItem ListLich = new JMenuItem("Xem danh sách Lịch chiếu");
        ListLich.setPreferredSize(new Dimension(200, 50)); 
        ListLich.setBackground(new Color(204, 241, 157));
        
        // Menu item Vé
      	JMenuItem DonDatVe = new JMenuItem("Đặt vé");
      	DonDatVe.setPreferredSize(new Dimension(200, 50));
      	DonDatVe.setBackground(new Color(204, 241, 157));
        JMenuItem ChiTietDonDatVe = new JMenuItem("Danh sách đơn đặt");
        ChiTietDonDatVe.setPreferredSize(new Dimension(200, 50)); 
        ChiTietDonDatVe.setBackground(new Color(204, 241, 157));
      	JMenuItem DonBanVe = new JMenuItem("Bán vé");
      	DonBanVe.setPreferredSize(new Dimension(200, 50));
      	DonBanVe.setBackground(new Color(204, 241, 157));
        JMenuItem ChiTietDonBanVe = new JMenuItem("Danh sách đơn bán");
        ChiTietDonBanVe.setPreferredSize(new Dimension(200, 50)); 
        ChiTietDonBanVe.setBackground(new Color(204, 241, 157));
        
        
        // Menu item Phòng
      	JMenuItem UpdatePhong = new JMenuItem("Cập nhật");
      	UpdatePhong.setPreferredSize(new Dimension(200, 50)); 
      	UpdatePhong.setBackground(new Color(204, 241, 157));
        JMenuItem FindPhong = new JMenuItem("Tìm kiếm");
        FindPhong.setPreferredSize(new Dimension(200, 50));  
        FindPhong.setBackground(new Color(204, 241, 157));
        JMenuItem ListPhong = new JMenuItem("Xem danh sách Phòng");
        ListPhong.setPreferredSize(new Dimension(200, 50));
        ListPhong.setBackground(new Color(204, 241, 157));
        
        // Menu item Khách Hàng
      	JMenuItem UpdateKH = new JMenuItem("Cập nhật");
      	UpdateKH.setPreferredSize(new Dimension(200, 50));
      	UpdateKH.setBackground(new Color(204, 241, 157));
        JMenuItem FindKH = new JMenuItem("Tìm kiếm");
        FindKH.setPreferredSize(new Dimension(200, 50));
        FindKH.setBackground(new Color(204, 241, 157));
        JMenuItem ListKH = new JMenuItem("Xem danh sách Khách Hàng");
        ListKH.setPreferredSize(new Dimension(200, 50));
        ListKH.setBackground(new Color(204, 241, 157));
        
        // Menu item Nhân Viên
      	JMenuItem UpdateNV = new JMenuItem("Cập nhật");
      	UpdateNV.setPreferredSize(new Dimension(200, 50)); 
      	UpdateNV.setBackground(new Color(204, 241, 157));
        JMenuItem FindNV = new JMenuItem("Tìm kiếm");
        FindNV.setPreferredSize(new Dimension(200, 50)); 
        FindNV.setBackground(new Color(204, 241, 157));
        JMenuItem ListNV = new JMenuItem("Xem danh sách Khách Hàng");
        ListNV.setPreferredSize(new Dimension(200, 50)); 
        ListNV.setBackground(new Color(204, 241, 157));
        
 		// Tạo các JPopupMenu cho từng nút menu
        JPopupMenu phim = new JPopupMenu();
        phim.add(UpdateFilm);
        phim.add(FindFilm);

        JPopupMenu lichChieu = new JPopupMenu();
        lichChieu.add(UpdateLich);
        lichChieu.add(FindLich);
        lichChieu.add(ListLich);

        JPopupMenu Ve = new JPopupMenu();
        Ve.add(DonDatVe);
        Ve.add(DonBanVe);
        Ve.add(ChiTietDonDatVe);
        Ve.add(ChiTietDonBanVe);
        
        JPopupMenu Phong = new JPopupMenu();
        Phong.add(UpdatePhong);
        Phong.add(FindPhong);
        Phong.add(ListPhong);

        JPopupMenu KhachHang = new JPopupMenu();
        KhachHang.add(UpdateKH);
        KhachHang.add(FindKH);
        KhachHang.add(ListKH);
        
        JPopupMenu NhanVien = new JPopupMenu();
        NhanVien.add(UpdateNV);
        NhanVien.add(FindNV);
        NhanVien.add(ListNV);
        
        // Thêm sự kiện cho mỗi nút menu để hiển thị JPopupMenu
        btnPhim.addActionListener(new PopupMenuListener(phim, btnPhim));
        btnLich.addActionListener(this);
        btnVe.addActionListener(new PopupMenuListener(Ve, btnVe));
        btnPhong.addActionListener(new PopupMenuListener(Phong, btnPhong));
        btnKhachHang.addActionListener(this);
        btnNhanVien.addActionListener(this);
 		
		//Top
			// Tạo các JMenu
			 // Tạo JMenuBar
	        menuBar = new JMenuBar();
	        // Tạo JMenu
	        LogOutMenu = new JMenu();
	        LogOutMenu.setIcon(imgIcon2);
	        LogOutMenu.setBackground(new Color(204, 241, 157));
	        // Tạo các JMenuItem và thêm vào JMenu
	        JMenuItem itemDangXuat = new JMenuItem("Đăng xuất");
	        LogOutMenu.add(itemDangXuat);
	        // Thêm JMenu vào JMenuBar
	        menuBar.add(LogOutMenu);
	        
	        // Đặt JMenuBar vào JFrame
	        setJMenuBar(menuBar);

		
		// Left
		JPanel pnlLeft = new JPanel();
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
		pnlLeft.setBackground(new Color(65, 60, 60));
		
			JPanel pnlSubLeft1 = new JPanel();
			pnlSubLeft1.setLayout(new BoxLayout(pnlSubLeft1, BoxLayout.X_AXIS));
			pnlSubLeft1.add(Box.createHorizontalStrut(10));
			pnlSubLeft1.add(labImgLogo);
			pnlSubLeft1.add(Box.createHorizontalStrut(10));
			pnlSubLeft1.add(labTenRap);
			pnlSubLeft1.setBackground(new Color(65, 60, 60));
			
			JPanel pnlSubLeft2 = new JPanel();
			pnlSubLeft2.setLayout(new GridLayout(7,1,3,3));
			pnlSubLeft2.add(btnMain);
			pnlSubLeft2.add(btnPhim);
			pnlSubLeft2.add(btnLich);
			pnlSubLeft2.add(btnVe);
			pnlSubLeft2.add(btnPhong);
			pnlSubLeft2.add(btnKhachHang);
			pnlSubLeft2.add(btnNhanVien);
			pnlSubLeft2.setBackground(new Color(65, 60, 60));
			pnlLeft.add(pnlSubLeft1);
			pnlLeft.add(pnlSubLeft2);
			
			layeredPane.setBackground(new Color(0, 51, 255));
			layeredPane.setBounds(201, 0, 416, 629);
			// Center
			
			// Panels

			JPanel pnlCenMain = new JPanel();
			pnlCenMain.setLayout(new BoxLayout(pnlCenMain, BoxLayout.X_AXIS));
			pnlCenMain.setBounds(0, 0,1035,682);
			pnlCenMain.setBackground(new Color(65, 60, 60));
			pnlCenMain.add(Box.createHorizontalStrut(55));
			pnlCenMain.add(labPoster1);
			pnlCenMain.add(Box.createHorizontalStrut(55));
			pnlCenMain.add(labPoster2);
			
			JPanel pnlCenPhimUpdate = new JPanel();
			pnlCenPhimUpdate.setLayout(new BorderLayout());
			pnlCenPhimUpdate.setBounds(0, 0,1035,682);
			pnlCenPhimUpdate.setBackground(new Color(65, 60, 60));
				JPanel pnlContentUpdate = new JPanel();
				pnlContentUpdate.setLayout(new BorderLayout());
				pnlContentUpdate.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
				pnlContentUpdate.setBackground(new Color(65, 60, 60));

					JPanel pnlCenTopUpdate = new JPanel();
					pnlCenTopUpdate.setLayout(new FlowLayout());
					pnlCenTopUpdate.setBackground(new Color(204, 241, 157));
					pnlCenTopUpdate.add(btnAdd);
					pnlCenTopUpdate.add(btnDel);
					pnlCenTopUpdate.add(btnDelAll);
					pnlCenTopUpdate.add(btnUpdate);
					
					JPanel pnlCenCenUpdate = new JPanel();
					pnlCenCenUpdate.setLayout(new BoxLayout(pnlCenCenUpdate, BoxLayout.X_AXIS));
					pnlCenCenUpdate.add(scrollUpdate, BorderLayout.CENTER);
					pnlContentUpdate.add(pnlCenTopUpdate, BorderLayout.NORTH);
					pnlContentUpdate.add(pnlCenCenUpdate, BorderLayout.CENTER);
					JPanel pnlCenRight= new JPanel();
					pnlCenRight.setLayout(new FlowLayout());
					pnlCenRight.setBackground(new Color(204, 241, 157));
					pnlCenRight.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
						JPanel pnlSubCenRight1 = new JPanel();
						pnlSubCenRight1.setLayout(new BoxLayout(pnlSubCenRight1, BoxLayout.Y_AXIS));
						pnlSubCenRight1.setBackground(new Color(204, 241, 157));
						pnlSubCenRight1.add(labIdPhim);
						pnlSubCenRight1.add(Box.createVerticalStrut(20));
						pnlSubCenRight1.add(labTenPhim);
						pnlSubCenRight1.add(Box.createVerticalStrut(20));
						pnlSubCenRight1.add(labDaoDienPhim);
						pnlSubCenRight1.add(Box.createVerticalStrut(20));
						pnlSubCenRight1.add(labTheLoaiPhim);
						pnlSubCenRight1.add(Box.createVerticalStrut(20));
						pnlSubCenRight1.add(labThoiLuongPhim);
						pnlSubCenRight1.add(Box.createVerticalStrut(20));
						pnlSubCenRight1.add(labNgayCCPhim);
						pnlSubCenRight1.add(Box.createVerticalStrut(20));
						pnlSubCenRight1.add(labNgayKTPhim);
						pnlSubCenRight1.add(Box.createVerticalStrut(20));
						pnlSubCenRight1.add(labDanhGiaPhim);
						
						JPanel pnlSubCenRight2= new JPanel();
						pnlSubCenRight2.setLayout(new BoxLayout(pnlSubCenRight2, BoxLayout.Y_AXIS));
						pnlSubCenRight2.setBackground(new Color(204, 241, 157));
						pnlSubCenRight2.add(txtIdPhim);
						pnlSubCenRight2.add(Box.createVerticalStrut(15));
						pnlSubCenRight2.add(txtTenPhim);
						pnlSubCenRight2.add(Box.createVerticalStrut(15));
						pnlSubCenRight2.add(txtDaoDienPhim);
						pnlSubCenRight2.add(Box.createVerticalStrut(15));
						String[] str = {"Hài hước","Kinh dị","Lãng mạn","Hành động","Viễn tưởng","Anime","Phiêu lưu","Bí ẩn"};
						cboTheLoai = new JComboBox<String>(str);
						pnlSubCenRight2.add(cboTheLoai);
						pnlSubCenRight2.add(Box.createVerticalStrut(15));
						pnlSubCenRight2.add(txtThoiLuongPhim);
						pnlSubCenRight2.add(Box.createVerticalStrut(15));
						pnlSubCenRight2.add(txtNgayCCPhim);
						pnlSubCenRight2.add(Box.createVerticalStrut(15));
						pnlSubCenRight2.add(txtNgayKTPhim);
						pnlSubCenRight2.add(Box.createVerticalStrut(15));
						pnlSubCenRight2.add(txtDanhGiaPhim);
					pnlCenRight.add(pnlSubCenRight1);
					pnlCenRight.add(pnlSubCenRight2);
			pnlCenPhimUpdate.add(pnlContentUpdate, BorderLayout.CENTER);
			pnlCenPhimUpdate.add(pnlCenRight, BorderLayout.EAST);
		
			JPanel pnlCenPhimFind = new JPanel();
			pnlCenPhimFind.setLayout(new BorderLayout());
			pnlCenPhimFind.setBounds(0, 0,1035,682);
			pnlCenPhimFind.setBackground(new Color(65, 60, 60));
				JPanel pnlContentFind = new JPanel();
				pnlContentFind.setLayout(new BorderLayout());
				pnlContentFind.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
				pnlContentFind.setBackground(new Color(65, 60, 60));
					JPanel pnlCenTopTopFind = new JPanel();
					pnlCenTopTopFind.setLayout(new FlowLayout());
					pnlCenTopTopFind.setBackground(new Color(204, 241, 157));
					pnlCenTopTopFind.add(btnFind);
					pnlCenTopTopFind.add(txtFind);
					JPanel pnlCenCenFind = new JPanel();
					pnlCenCenFind.setLayout(new BoxLayout(pnlCenCenFind, BoxLayout.X_AXIS));
					pnlCenCenFind.add(scrollFind, BorderLayout.CENTER);
				pnlContentFind.add(pnlCenTopTopFind, BorderLayout.NORTH);
				pnlContentFind.add(pnlCenCenFind, BorderLayout.CENTER);
			pnlCenPhimFind.add(pnlContentFind, BorderLayout.CENTER);	
		
		
		// ActionListeners
		itemDangXuat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FrmLogin();
			}
		});
		btnMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                btnMain.setBackground(new Color(12, 138, 255));
                resetTargetMenu();
                switchPanels(pnlCenMain);
            }
        });
		
		UpdateFilm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UpdateFilm.setBackground(new Color(12, 138, 255));
				FindFilm.setBackground(new Color(204, 241, 157));
				switchPanels(pnlCenPhimUpdate);
			}
		});
		FindFilm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FindFilm.setBackground(new Color(12, 138, 255));
				UpdateFilm.setBackground(new Color(204, 241, 157));
	            ListFilm.setBackground(new Color(204, 241, 157));
	            switchPanels(pnlCenPhimFind);
			}
		
		});
		btnPhim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thay đổi kích thước của nút
                resetTargetMenu();
                btnPhim.setBackground(new Color(12, 138, 255));
                
            }
        });
		btnVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thay đổi kích thước của nút
            	resetTargetMenu();
                btnVe.setBackground(new Color(12, 138, 255));
            }
        });
		frmKH=new FrmKhachHang();
		JPanel pnlKH = frmKH.taoFrmKhachHang();
		btnKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//            	reset màu xong set lại màu btn được nhấn
				resetTargetMenu();
				btnKhachHang.setBackground(new Color(12, 138, 255));
				switchPanels(pnlKH);
		}
	});
		frmLichChieu = new FrmLichChieu();
		pnlLichChieu =  frmLichChieu.taoFrmLichChieu();
		btnLich.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//            	reset màu xong set lại màu btn được nhấn
				resetTargetMenu();
				btnLich.setBackground(new Color(12, 138, 255));
				switchPanels(pnlLichChieu);
		}
	});
		frmNhanVien = new FrmNhanVien();
		pnlNV =  frmNhanVien.taoFrmNhanVien();
		btnNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//            	reset màu xong set lại màu btn được nhấn
				resetTargetMenu();
				btnNhanVien.setBackground(new Color(12, 138, 255));
				switchPanels(pnlNV);
		}
	});

		layeredPane.add(pnlCenMain, JLayeredPane.PALETTE_LAYER); 
		layeredPane.add(pnlCenPhimUpdate, JLayeredPane.PALETTE_LAYER); 
		layeredPane.add(pnlCenPhimFind, JLayeredPane.PALETTE_LAYER);
        // Đọc dữ liệu vào bảng
		DocDuLieuVaoTable();
		
		btnAdd.addActionListener(this);
		btnDel.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelAll.addActionListener(this);
		btnFind.addActionListener(this);
		tablePhimUpdate.addMouseListener(this);

		pnlAll.add(layeredPane);
		pnlAll.add(pnlLeft, BorderLayout.WEST);
		this.add(pnlAll);
		this.setSize(1280,720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	 public static class PopupMenuListener implements ActionListener {
	        private JPopupMenu popupMenu;
	        private Component invoker;

	        public PopupMenuListener(JPopupMenu popupMenu, Component invoker) {
	            this.popupMenu = popupMenu;
	            this.invoker = invoker;
	        }

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            popupMenu.show(invoker, 0, invoker.getHeight());
	        }
	    }
	
	public void DocDuLieuVaoTable() {
		List<Phim> listPhim = phim_DAO.getAllPhim();
		for (Phim p : listPhim) {
			modelPhim.addRow(new Object[] {p.getIdPhim(),p.getTenPhim(),p.getDaoDien(),
					p.getTheLoai(),p.getThoiLuong(),p.getNgayCC(),
					p.getNgayKT(),p.getDanhGia()});		
		}
		tablePhimUpdate.setModel(modelPhim);
	}
	public void XoaRongTable() {
		DefaultTableModel d = (DefaultTableModel) tablePhimFind.getModel();
		d.getDataVector().removeAllElements();
	}
	public void timPhimTheoTenPhim(String tenPhim){
		tenPhim = tenPhim.toLowerCase();
		List<Phim> dsPhim = phim_DAO.getAllPhim();
		for (Phim p : dsPhim) {
			if(p.getIdPhim().contains(tenPhim)) {
				XoaRongTable();
				modelPhim.addRow(new Object[] {p.getIdPhim(),p.getTenPhim(),p.getDaoDien(),
						p.getTheLoai(),p.getThoiLuong(),p.getNgayCC(),
						p.getNgayKT(),p.getDanhGia()});	
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		java.util.Date ngayCC = (java.util.Date) txtNgayCCPhim.getValue();
		java.util.Date ngayKT = (java.util.Date) txtNgayKTPhim.getValue();
		Object o = e.getSource();
		if(o == btnAdd) {
			if( txtIdPhim.getText().equals("") || 
					txtTenPhim.getText().equals("") ||
					txtDaoDienPhim.getText().equals("") || 
					txtThoiLuongPhim.getText().equals("") ||
					txtDanhGiaPhim.getText().equals("") 
			) {
				JOptionPane.showMessageDialog(null, "Vui lòng không được để trống!");
			}else {
                
                int comparResult = ngayCC.compareTo(ngayKT);
                
				if(comparResult < 0) {
					String idPhim = txtIdPhim.getText();
					String tenPhim = txtTenPhim.getText();
					String daoDien = txtDaoDienPhim.getText();
					String theLoai = cboTheLoai.getSelectedItem().toString();
					int thoiLuong = Integer.parseInt(txtThoiLuongPhim.getText());

					int danhGia = Integer.parseInt(txtDanhGiaPhim.getText());
						Phim p = new Phim(idPhim, tenPhim, daoDien, theLoai, thoiLuong, ngayCC, ngayKT, danhGia);
						try {
							if(phim_DAO.addPhim(p)) {
								modelPhim.addRow(new Object[] {p.getIdPhim(),p.getTenPhim(),p.getDaoDien(),
										p.getTheLoai(),p.getThoiLuong(),p.getNgayCC(),
										p.getNgayKT(),p.getDanhGia()});	
								JOptionPane.showMessageDialog(this, "Thêm thành công!");
							}else {
							JOptionPane.showMessageDialog(this, "Thêm không thành công!");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}else {
					JOptionPane.showMessageDialog(this, "Ngày chiếu phim phải trươc ngày kết thúc!");
				}
			}
		}else if(o == btnUpdate) {
			int row = tablePhimUpdate.getSelectedRow();
			if(row >= 0) {
				String idPhim = txtIdPhim.getText();
				String tenPhim = txtTenPhim.getText();
				String daoDien = txtDaoDienPhim.getText();
				String theLoai = cboTheLoai.getSelectedItem().toString();
				int thoiLuong = Integer.parseInt(txtThoiLuongPhim.getText());
				int danhGia = Integer.parseInt(txtDanhGiaPhim.getText());
				
				Phim p = new Phim(idPhim, tenPhim, daoDien, theLoai, thoiLuong, ngayCC, ngayKT, danhGia);
				if(phim_DAO.updatePhim(p)) {
					
					// Định dạng ngày công chiếu và ngày kết thúc
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String formattedNgayCongChieu = sdf.format(ngayCC);
					String formattedNgayKetThuc = sdf.format(ngayKT);
					
					tablePhimUpdate.setValueAt(txtTenPhim.getText(),row ,1);
					tablePhimUpdate.setValueAt(txtDaoDienPhim.getText(),row ,2);
					tablePhimUpdate.setValueAt(cboTheLoai.getSelectedItem(),row, 3); 
					tablePhimUpdate.setValueAt(txtThoiLuongPhim.getText(),row, 4); 
					tablePhimUpdate.setValueAt(formattedNgayCongChieu,row, 5); 
					tablePhimUpdate.setValueAt(formattedNgayKetThuc,row, 6); 
					tablePhimUpdate.setValueAt(txtDanhGiaPhim.getText(),row, 7); 
				}else {
					JOptionPane.showMessageDialog(this, "Sửa không thành công!");
				}
			}else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn sửa!");
			}
		}else if(o == btnDelAll) {
			txtIdPhim.setText("");
			txtTenPhim.setText("");
			txtDaoDienPhim.setText(""); 
			txtThoiLuongPhim.setText("");
			txtDanhGiaPhim.setText("");
		}else if(o == btnDel) {
			int row = tablePhimUpdate.getSelectedRow();
			if(row >= 0) {
				String p =(String) modelPhim.getValueAt(row, 0);
				int choice = JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn xóa Phim này ?");
				if(choice == JOptionPane.YES_OPTION) {
					if(phim_DAO.removePhim(p)) {
						modelPhim.removeRow(row);
						JOptionPane.showMessageDialog(null, "Xóa thành công!");
					}else {
						JOptionPane.showMessageDialog(null, "Xóa không thành công!");
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng để xóa!");
			}
		}else if(o == btnFind) {
			String tuKhoa = txtFind.getText().trim();
			if(!(tuKhoa.equals(""))) {
				timPhimTheoTenPhim(tuKhoa);
			}else {
				XoaRongTable();
				DocDuLieuVaoTable();
			}
		}
	}
	public static void main(String[] args) throws IOException, SQLException, ParseException {
		new FrmPhim();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tablePhimUpdate.getSelectedRow();
		txtIdPhim.setText(modelPhim.getValueAt(row, 0).toString());
		txtTenPhim.setText(modelPhim.getValueAt(row, 1).toString());
		txtDaoDienPhim.setText(modelPhim.getValueAt(row, 2).toString()); 
		cboTheLoai.setSelectedItem(modelPhim.getValueAt(row, 3).toString());
		txtThoiLuongPhim.setText(modelPhim.getValueAt(row, 4).toString());
		
		// Lấy giá trị từ cột 5 và cột 6 của hàng được chọn (cột ngày công chiếu và ngày kết thúc)
		Date ngayCongChieu = (Date) tablePhimUpdate.getModel().getValueAt(row, 5);
		Date ngayKetThuc = (Date) tablePhimUpdate.getModel().getValueAt(row, 6);

		// Thiết lập giá trị cho JSpinner
		txtNgayCCPhim.setValue(ngayCongChieu);
		txtNgayKTPhim.setValue(ngayKetThuc);
		
		txtDanhGiaPhim.setText(modelPhim.getValueAt(row, 7).toString()); 
		
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

package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.ChiTietVeBan_DAO;
import common.Utils;

import javax.swing.border.Border;

import connectDB.ConnectDB;
import entity.ChiTietVe;

public class FrmDanhSachVeBan extends JFrame implements ActionListener {
	private Connection con;
	private JButton btnFindMaVe;
	private JButton btnFindTenPhim;
	private JTextField txtFindMaVe;
	private JTextField txtFindTenPhim;
	private String[] head;
	private DefaultTableModel model;
	private JTable table;
	private ChiTietVeBan_DAO chiTietVeBanDAO = new ChiTietVeBan_DAO();

	public FrmDanhSachVeBan() throws ParseException {
		getContentPane().add(taoFrmDanhSachVeBan());
		setSize(1035, 682);
	}

	public static void main(String[] args) {
		try {
			new FrmDanhSachVeBan().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JPanel taoFrmDanhSachVeBan() {
		// Connect
		ConnectDB.getInstance().connect();
		con = ConnectDB.getConnection();
		if (con == null) {
			JOptionPane.showMessageDialog(null, "Không thể kết nối đến cơ sở dữ liệu");
			return null;
		}

		JPanel pnDsVeBan = new JPanel();
		pnDsVeBan.setBackground(new Color(228, 243, 208));
		pnDsVeBan.setLayout(null);
		setLocation(250, 30);

		// Table danh sach ve ban
		head = new String[] {"Mã vé", "Tên phim", "Ngày chiếu", "Suất chiếu", "Phòng", "Danh sách ghế", "Thành tiền", "Tên khách hàng", "Số điện thoại"};
		model = new DefaultTableModel(head, 0);
		table = new JTable(model);
		JScrollPane scrollFind = new JScrollPane(table);

		btnFindMaVe = new JButton("Tìm theo mã vé");
		btnFindTenPhim = new JButton("Tìm theo tên phim");
		
		txtFindMaVe= new JTextField();
 		txtFindMaVe.setPreferredSize(new Dimension(250,30));
 		txtFindTenPhim= new JTextField();
 		txtFindTenPhim.setPreferredSize(new Dimension(250,30));
 		
		JPanel pnlCenPhimFind = new JPanel();
		pnlCenPhimFind.setLayout(new BorderLayout());
		pnlCenPhimFind.setBounds(0, 0, 1035, 682);
		pnlCenPhimFind.setBackground(new Color(228, 243, 208));
		JPanel pnlContentFind = new JPanel();
		pnlContentFind.setLayout(new BoxLayout(pnlContentFind, BoxLayout.Y_AXIS));
		pnlContentFind.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pnlContentFind.setBackground(new Color(228, 243, 208));
		JPanel pnlCenTopTopFind = new JPanel();
		pnlCenTopTopFind.setLayout(new BoxLayout(pnlCenTopTopFind, BoxLayout.X_AXIS));
		Border outBorder = BorderFactory.createTitledBorder("Tìm kiếm");
		Border inBorder = BorderFactory.createEmptyBorder(10, 10, 0, 10);
		Border comBorder = BorderFactory.createCompoundBorder(inBorder, outBorder);
		pnlCenTopTopFind.setBorder(comBorder);
		pnlCenTopTopFind.setBackground(new Color(204, 241, 157));
		JPanel pnlSubFind_1 = new JPanel();
		pnlSubFind_1.setLayout(new FlowLayout());
		pnlSubFind_1.setBackground(new Color(204, 241, 157));
		pnlSubFind_1.add(btnFindTenPhim);
		pnlSubFind_1.add(txtFindMaVe);
		JPanel pnlSubFind_2 = new JPanel();
		pnlSubFind_2.setLayout(new FlowLayout());
		pnlSubFind_2.setBackground(new Color(204, 241, 157));
		pnlSubFind_2.add(btnFindMaVe);
		pnlSubFind_2.add(txtFindTenPhim);
		pnlCenTopTopFind.add(pnlSubFind_1);
		pnlCenTopTopFind.add(pnlSubFind_2);
		JPanel pnlCenCenFind = new JPanel();
		pnlCenCenFind.setLayout(new BoxLayout(pnlCenCenFind, BoxLayout.X_AXIS));
		pnlCenCenFind.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pnlCenCenFind.add(scrollFind, BorderLayout.CENTER);
		pnlContentFind.add(pnlCenTopTopFind);
		pnlContentFind.add(pnlCenCenFind);
		pnlCenPhimFind.add(pnlContentFind, BorderLayout.CENTER);
		pnDsVeBan.add(pnlCenPhimFind);
		
		DocDsVeBanVaoTable();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pnDsVeBan.setSize(1084, 768);
		return pnDsVeBan;

	}
	
	public void DocDsVeBanVaoTable() {
		List<ChiTietVe> list = chiTietVeBanDAO.getListVeBan("NV001");
		for (ChiTietVe data : list) {
			model.addRow(new Object[] {data.getMaVe(), data.getTenPhim(), data.getNgayChieu(), data.getSuatChieu(), data.getTenPhong(), data.getDsGhe(), data.getSoLuongGhe()*Utils.GIAVE.getValue(), data.getTenKH(), data.getSdt()});		
		}
		table.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

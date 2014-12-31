package ui;

import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

import object.Barang;
import object.Transaksi;
import system.*;
import ui.listener.CustActionListener;

public class WindowDataTransaksi extends JFrame {
	private Core core;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Vector<Barang> barang = new Vector<Barang>();
	private Vector<String> nmBarang = new Vector<String>();

	private JTable tbl;

	public WindowDataTransaksi(Core core) {
		super("Data Transaksi Obat Herbal");
		this.core = core;
		setResizable(false);

		setSize(500, 300);
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		setLayout(null);
		Container container = this.getContentPane();
		tbl = new JTable(Operator.resultSetToTableModel(Operator
				.getListTransaksi(core.getConnection())));
		Operator.disableTableEdit(tbl);
		JPanel panTbl = new JPanel();
		panTbl.setLayout(new BorderLayout());
		panTbl.add(new JScrollPane(tbl), BorderLayout.CENTER);
		panTbl.setBounds(0, 0, 495, 200);
		panTbl.setBackground(Color.WHITE);
		JButton buttonDelete = new JButton("Delete");
		buttonDelete.addActionListener(new CustActionListener(core, this, tbl,
				buttonDelete, CustActionListener.HAPUS_TRANS));
		buttonDelete.setBounds(65, 220, 170, 30);
		buttonDelete.setForeground(Color.BLACK);
		buttonDelete.setBackground(Color.WHITE);
		
		JButton buttonDetail = new JButton("Detail");
		buttonDetail.addActionListener(new CustActionListener(core, this, tbl,
				buttonDetail, CustActionListener.SHOW_DETIL_TRANSAKSI));
		buttonDetail.setBounds(255, 220, 170, 30);
		buttonDetail.setForeground(Color.BLACK);
		buttonDetail.setBackground(Color.WHITE);
		
		container.add(buttonDelete);
		container.add(buttonDetail);
		container.add(panTbl);
	}

	public Transaksi getTransaksi() {
		if (tbl.getSelectedRow() >= 0) {
			String val = tbl.getValueAt(tbl.getSelectedRow(), 0).toString();
			
			return new Transaksi(val);
		} else {
			return null;
		}
	}

	public void resetForm() {
		if (tbl.getSelectedRow() >= 0) {
			((DefaultTableModel) tbl.getModel())
					.removeRow(tbl.getSelectedRow());
		}
	}
}

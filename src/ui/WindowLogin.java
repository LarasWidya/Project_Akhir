package ui;

import java.awt.*;

import javax.swing.*;

import system.*;
import ui.listener.CustActionListener;

public class WindowLogin extends JFrame {

	final private Core core;

	private JButton btnLogin;
	private JTextField txUsr;
	private JPasswordField txPsw;
	private JLabel lblUsr, lblPsw;

	private Container container;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public WindowLogin(Core core) {
		super("Login");
		this.core = core;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		setResizable(false);
		JLabel labelHeader = new JLabel("<HTML><h2>Aplikasi Toko Obat Herbal</h2></HTML>");
		labelHeader.setForeground(Color.DARK_GRAY);
		labelHeader.setBounds(40,10,250,20);
		
		container = this.getContentPane();
		container.setLayout(null);
		//container.setBackground(Color.WHITE);
		btnLogin = new JButton("<HTML><H3>Login</H3></HTML>");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(Color.WHITE);
		txUsr = new JTextField(15);
		txPsw = new JPasswordField(15);
		lblUsr = new JLabel("<HTML><H3>Username</H3></HTML>");
		lblPsw = new JLabel("<HTML><H3>Password</H3></HTML>");

		lblUsr.setHorizontalAlignment(JLabel.RIGHT);
		lblPsw.setHorizontalAlignment(JLabel.RIGHT);

		lblUsr.setBounds(10, 50, 70, 20);
		txUsr.setBounds(100, 50, 180, 25);
		lblPsw.setBounds(10, 80, 70, 20);
		txPsw.setBounds(100, 80, 180, 25);
		btnLogin.setBounds(30, 120, 235, 30);

		btnLogin.addActionListener(new CustActionListener(core, this, btnLogin));
		container.add(labelHeader);
		container.add(lblUsr);
		container.add(txUsr);
		container.add(lblPsw);
		container.add(txPsw);
		container.add(btnLogin);
	}

	public String getUser() {
		return txUsr.getText();
	}

	public String getPass() {
		return txPsw.getText();
	}
}

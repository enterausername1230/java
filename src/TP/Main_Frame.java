package TP;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Status.Book_Status;
import Status.Member_Status;
import Status.Rent_Status;

import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.io.InputStream;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JPasswordField;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.*;
import javax.swing.DefaultComboBoxModel;

public class Main_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField Search_Filed;
	private JTextField tf_ISBN;
	private JTextField tf_Title;
	private JTextField tf_Authir;
	private JTextField tf_Pub;
	private JTextField tf_Price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Frame frame = new Main_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	JLabel book_img = new JLabel("");
	private JTextField Rent_tf;

	public Main_Frame() {
		// UPDATE `java03_team03`.`USER_TABLE` SET `USER_RENT_CNT` = '0' WHERE
		// (`USER_PHONE_NUMBER` = '01044421234');
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 723);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel Search = new JPanel();
		Search.setBackground(Color.WHITE);
		JPanel Main_panel = new JPanel();
		Main_panel.setBorder(UIManager.getBorder("TextArea.border"));
		Main_panel.setBackground(Color.WHITE);
		Main_panel.setBounds(36, 100, 597, 558);
		contentPane.add(Main_panel);
		Main_panel.setLayout(null);
		Search_Filed = new JTextField();
		Search_Filed.setHorizontalAlignment(SwingConstants.LEFT);
		Search_Filed.setToolTipText("\uCC45\uC774\uB984\uC744 \uC785\uB825\uD558\uC138\uC694");
		Search_Filed.setBackground(SystemColor.text);
		Search_Filed.setBounds(118, 0, 358, 30);
		Main_panel.add(Search_Filed);
		Search_Filed.setColumns(10);
		JButton Search_Button = new JButton("Q");
		Search_Button.setToolTipText(
				"\uCC45\uC774\uB984\uC744 \uC785\uB825\uD588\uB098\uC694? \uADF8\uB7EC\uBA74 \uB20C\uB7EC\uC8FC\uC138\uC694");
		Search_Button.setFont(new Font("±¼¸²", Font.BOLD, 20));
		Search_Button.setBackground(SystemColor.activeCaption);
		book_img.setBackground(Color.GRAY);

		book_img.setBounds(337, 29, 199, 250);
		Search.add(book_img);

		JButton Rental_btn = new JButton("\uB300\uCD9C");
		Rental_btn.setFont(new Font("ÈÞ¸Õ°íµñ", Font.PLAIN, 12));
		Rental_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rental_Form Rf = new Rental_Form();
				Rf.setVisible(true);
			}
		});
		Rental_btn.setBackground(SystemColor.activeCaption);
		Rental_btn.setBounds(34, 303, 163, 40);
		Search.add(Rental_btn);
		Search_Button.addActionListener(new ActionListener() {// ÅØ½ºÆ®ÇÊµåÇØ´çÇÏ´Â ¹®ÀÚ¿­À» ±â¹ÝÀ¸·Î DBÃâ·ÂµÇµµ·ÏÇÔ.
			public void actionPerformed(ActionEvent e) {

				try {

					Class.forName("com.mysql.cj.jdbc.Driver");
					java.sql.Connection conn = DriverManager.getConnection(
							"jdbc:mysql://jdb.deu.monster:60001/java03_team03", "java03_team03", "980601eh!");
					System.out.println("DB ¿¬°á ¿Ï·á");
					Statement stmt = conn.createStatement();

					String string_Oder = "select BOOK_ISBN,BOOK_TITLE,BOOK_AUTHIR,BOOK_PUB,BOOK_PRICE,BOOK_IMAGE,BOOK_PRE from BOOK_TABLE where BOOK_TITLE="
							+ "'" + Search_Filed.getText() + "'" + ";";

					ResultSet rs2 = stmt.executeQuery(string_Oder);
					while (rs2.next()) {

						tf_ISBN.setText(rs2.getString("BOOK_ISBN"));

						tf_Title.setText(rs2.getString("BOOK_TITLE"));

						tf_Authir.setText(rs2.getString("BOOK_AUTHIR"));
						tf_Pub.setText(rs2.getString("BOOK_PUB"));
						tf_Price.setText(rs2.getString("BOOK_PRICE"));

						if (rs2.getString("BOOK_PRE").equals("1")) {
							Rent_tf.setText("´ë¿©°¡´É");
							Rental_btn.setVisible(true);
						} else {
							Rent_tf.setText("´ë¿© ºÒ°¡´É");
							Rental_btn.setVisible(false);
						}
						try {
							InputStream img = rs2.getBinaryStream("BOOK_IMAGE");
							book_img.setIcon(new ImageIcon(ImageIO.read(img)));

						} catch (Exception e3) {

						}

					}
					if (tf_Title.getText().equals("")) {
						Search.setVisible(false);
					} else {
						Search.setVisible(true);
					}

				} catch (ClassNotFoundException e1) {

				} catch (SQLException e2) {

				}

			}
		});

		Search_Button.setBounds(474, 0, 78, 30);
		Main_panel.add(Search_Button);

		Search.setVisible(false);
		Search.setBounds(117, 105, 577, 443);
		Main_panel.add(Search);
		Search.setLayout(null);

		tf_ISBN = new JTextField();
		tf_ISBN.setBounds(134, 29, 173, 21);
		Search.add(tf_ISBN);
		tf_ISBN.setColumns(10);

		JLabel BOOK_ISBN = new JLabel("ISBN");
		BOOK_ISBN.setFont(new Font("ÈÞ¸Õ°íµñ", Font.PLAIN, 12));
		BOOK_ISBN.setBounds(34, 29, 84, 21);
		Search.add(BOOK_ISBN);

		JLabel BOOK_TITLE = new JLabel("\uC81C\uBAA9");
		BOOK_TITLE.setFont(new Font("ÈÞ¸Õ°íµñ", Font.PLAIN, 12));
		BOOK_TITLE.setBounds(34, 60, 84, 21);
		Search.add(BOOK_TITLE);

		tf_Title = new JTextField();
		tf_Title.setColumns(10);
		tf_Title.setBounds(134, 60, 173, 21);
		Search.add(tf_Title);

		JLabel BOOK_AUTHIR = new JLabel("\uC800\uC790");
		BOOK_AUTHIR.setFont(new Font("ÈÞ¸Õ°íµñ", Font.PLAIN, 12));
		BOOK_AUTHIR.setBounds(34, 95, 84, 21);
		Search.add(BOOK_AUTHIR);

		tf_Authir = new JTextField();
		tf_Authir.setColumns(10);
		tf_Authir.setBounds(134, 95, 173, 21);
		Search.add(tf_Authir);

		JLabel BOOK_PUB = new JLabel("\uCD9C\uD310\uC0AC");
		BOOK_PUB.setFont(new Font("ÈÞ¸Õ°íµñ", Font.PLAIN, 12));
		BOOK_PUB.setBounds(34, 126, 84, 21);
		Search.add(BOOK_PUB);

		tf_Pub = new JTextField();
		tf_Pub.setColumns(10);
		tf_Pub.setBounds(134, 126, 173, 21);
		Search.add(tf_Pub);

		JLabel BOOK_PRICE = new JLabel("\uAC00\uACA9");
		BOOK_PRICE.setFont(new Font("ÈÞ¸Õ°íµñ", Font.PLAIN, 12));
		BOOK_PRICE.setBounds(34, 157, 84, 21);
		Search.add(BOOK_PRICE);

		tf_Price = new JTextField();
		tf_Price.setColumns(10);
		tf_Price.setBounds(134, 157, 173, 21);
		Search.add(tf_Price);

		JButton Hide_button = new JButton("\uD655\uC778");
		Hide_button.setFont(new Font("ÈÞ¸Õ°íµñ", Font.PLAIN, 12));
		Hide_button.setBackground(SystemColor.activeCaption);
		Hide_button.addActionListener(new ActionListener() {// ´Ù½Ã ÆÐ³Î¼û±è.
			public void actionPerformed(ActionEvent e) {

				tf_ISBN.setText("");
				tf_Title.setText("");

				tf_Authir.setText("");
				tf_Pub.setText("");
				tf_Price.setText("");

				Rent_tf.setText("");
				book_img.setIcon(new ImageIcon());

				Search.setVisible(false);
				Search_Filed.setText("");
			}
		});
		Hide_button.setBounds(213, 410, 97, 23);
		Search.add(Hide_button);

		JButton Return_btn = new JButton("\uBC18\uB0A9");
		Return_btn.setFont(new Font("ÈÞ¸Õ°íµñ", Font.PLAIN, 12));
		Return_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Return_Frame rF = new Return_Frame();
				rF.setVisible(true);
			}
		});
		Return_btn.setBackground(SystemColor.activeCaption);
		Return_btn.setBounds(213, 303, 163, 40);
		Search.add(Return_btn);

		JLabel lblNewLabel_1 = new JLabel("\uD655\uC778\uC744 \uB20C\uB7EC \uB2EB\uC544\uC8FC\uC138\uC694");
		lblNewLabel_1.setFont(new Font("ÈÞ¸Õ°íµñ", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(168, 383, 184, 23);
		Search.add(lblNewLabel_1);

		JLabel Rent_pos = new JLabel("\uC0C1\uD0DC");
		Rent_pos.setFont(new Font("ÈÞ¸Õ°íµñ", Font.PLAIN, 12));
		Rent_pos.setBounds(33, 201, 57, 15);
		Search.add(Rent_pos);

		Rent_tf = new JTextField();
		Rent_tf.setColumns(10);
		Rent_tf.setBounds(134, 198, 173, 21);
		Search.add(Rent_tf);

		JButton sel_btn = new JButton("\uBC84\uD2BC");
		sel_btn.setFont(new Font("ÈÞ¸Õ°íµñ", Font.PLAIN, 12));
		sel_btn.setBackground(SystemColor.activeCaption);
		sel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sel_btn.getText().equals("µµ¼­ÇöÈ²")) {
					Book_Status bs = new Book_Status();
					bs.setVisible(true);
				}
				if (sel_btn.getText().equals("È¸¿øÇöÈ²")) {
					Member_Status ms = new Member_Status();
					ms.setVisible(true);
				}
				if (sel_btn.getText().equals("È¸¿ø°ü¸®")) {
					Member_ctrl_form mcf = new Member_ctrl_form();
					mcf.setVisible(true);

				}
				if (sel_btn.getText().equals("µµ¼­°ü¸®")) {
					book_form bf = new book_form();
					bf.setVisible(true);

				}
				if (sel_btn.getText().equals("´ë¿©ÇöÈ²")) {
					Rent_Status rs = new Rent_Status();
					rs.setVisible(true);

				}
			}
		});
		sel_btn.setBounds(118, 40, 103, 23);
		Main_panel.add(sel_btn);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("ÈÞ¸Õ°íµñ", Font.PLAIN, 12));
		comboBox.setBackground(SystemColor.activeCaption);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String txt = comboBox.getSelectedItem().toString();
				sel_btn.setText(txt);

			}
		});
		comboBox.setBounds(233, 40, 102, 23);
		Main_panel.add(comboBox);
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "\uB3C4\uC11C\uD604\uD669", "\uD68C\uC6D0\uD604\uD669",
						"\uD68C\uC6D0\uAD00\uB9AC", "\uB3C4\uC11C\uAD00\uB9AC", "\uB300\uC5EC\uD604\uD669" }));

		JLabel lblNewLabel_2 = new JLabel("<-\uC0AC\uC6A9\uAC00\uB2A5\uD55C \uC11C\uBE44\uC2A4");
		lblNewLabel_2.setBounds(347, 40, 160, 23);
		Main_panel.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("DEU Library");
		lblNewLabel.setBounds(153, 60, 398, 30);
		contentPane.add(lblNewLabel);
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 30));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(12, 10, 820, 22);
		contentPane.add(panel);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}

package TP;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import comm.dbConnector;
import java.awt.Font;

public class Rental_Form extends JFrame {

	private JPanel contentPane;
	private JTextField tf;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField tf2;
	dbConnector dbConn = new dbConnector();
	int i = 0; // 사용자 카운트

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rental_Form frame = new Rental_Form();
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
	public Rental_Form() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel RENT_2 = new JPanel();

		JLabel img = new JLabel("");
		img.setBackground(Color.GRAY);

		RENT_2.setLayout(null);
		RENT_2.setBackground(Color.WHITE);
		RENT_2.setBounds(12, 10, 392, 390);
		contentPane.add(RENT_2);

		JLabel Rent2LabelIsbn = new JLabel("ISBN");
		Rent2LabelIsbn.setBounds(12, 10, 57, 15);
		RENT_2.add(Rent2LabelIsbn);

		tf = new JTextField();
		tf.setColumns(10);
		tf.setBounds(91, 7, 116, 21);
		RENT_2.add(tf);

		JLabel Rent2LabelTitle = new JLabel("\uC81C\uBAA9");
		Rent2LabelTitle.setBounds(12, 63, 57, 15);
		RENT_2.add(Rent2LabelTitle);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(91, 60, 116, 21);
		RENT_2.add(textField_1);

		JLabel Rent2LabelAuthir = new JLabel("\uC800\uC790");
		Rent2LabelAuthir.setBounds(12, 88, 57, 15);
		RENT_2.add(Rent2LabelAuthir);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(91, 85, 116, 21);
		RENT_2.add(textField_2);

		JLabel Rent2LabelPub = new JLabel("\uCD9C\uD310\uC0AC");
		Rent2LabelPub.setBounds(12, 113, 57, 15);
		RENT_2.add(Rent2LabelPub);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(91, 110, 116, 21);
		RENT_2.add(textField_3);

		JLabel Rent2LabelPrice = new JLabel("\uAC00\uACA9");
		Rent2LabelPrice.setBounds(12, 138, 57, 15);
		RENT_2.add(Rent2LabelPrice);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(91, 135, 116, 21);
		RENT_2.add(textField_4);

		JLabel RentLabelUserPhone = new JLabel("\uC5F0\uB77D\uCC98");
		RentLabelUserPhone.setBounds(12, 186, 57, 15);
		RENT_2.add(RentLabelUserPhone);

		tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(91, 183, 116, 21);
		RENT_2.add(tf2);

		// 이미지 리스트
		ArrayList<Image> tmpImg = new ArrayList<>();

		JButton returnConfirmationButton = new JButton("\uD655\uC778");
		returnConfirmationButton.setBackground(SystemColor.activeCaption);
		returnConfirmationButton.setBounds(283, 6, 97, 23);
		RENT_2.add(returnConfirmationButton);
		returnConfirmationButton.addActionListener(new ActionListener() { // panel_4
			public void actionPerformed(ActionEvent e) {

				String str = "select BOOK_TITLE, BOOK_AUTHIR, BOOK_PUB, BOOK_PRICE, BOOK_PRE, BOOK_IMAGE FROM BOOK_TABLE WHERE BOOK_ISBN = "
						+ "'" + tf.getText() + "'" + ";";

				try {
					ResultSet src = dbConn.executeQurey(str);

					while (src.next()) {

						textField_1.setText(src.getString("BOOK_TITLE"));
						textField_2.setText(src.getString("BOOK_AUTHIR"));
						textField_3.setText(src.getString("BOOK_PUB"));
						textField_4.setText(src.getString("BOOK_PRICE"));

						// DB에서 BLOB 자료형으로 저장된 데이터 그림 데이터로 변환
						//

						try {
							InputStream img2 = src.getBinaryStream("BOOK_IMAGE");
							img.setIcon(new ImageIcon(ImageIO.read(img2)));

						} catch (Exception e1) {
						}
					}

				} catch (Exception e4) {
					// TODO: handle exception
				}

			}
		}

		);

		// 대여 완료
		JButton RC = new JButton("\uB300\uC5EC\uC644\uB8CC");
		RC.setBackground(SystemColor.activeCaption);
		RC.setBounds(283, 328, 97, 23);
		RENT_2.add(RC);

		img.setBounds(219, 39, 161, 186);
		RENT_2.add(img);

		JLabel cnt = new JLabel("");
		JLabel aaa = new JLabel("");

		cnt.setBounds(301, 332, 57, 15);
		RENT_2.add(cnt);

		JButton mc = new JButton("\uD68C\uC6D0\uD655\uC778");
		mc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String str = "select USER_BOOL FROM USER_TABLE WHERE USER_PHONE_NUMBER = " + "'"
						+ tf2.getText() + "'" + ";";

				try {
					ResultSet src = dbConn.executeQurey(str);
					while (src.next()) {
						String c = src.getString("USER_BOOL");
						if (c.equals("1")) {
							//현재 회원임
							JOptionPane.showMessageDialog(null, "회원입니다.", "도서대출", JOptionPane.ERROR_MESSAGE);
							RC.setVisible(true);
						}
						if (c.equals("0")) {
							//탈퇴된 회원임
							JOptionPane.showMessageDialog(null, "회원이아닙니다.", "도서대출", JOptionPane.ERROR_MESSAGE);
							RC.setVisible(false);
						}
					}
				} catch (Exception e345) {

				}

			}
		});
		mc.setBackground(SystemColor.activeCaption);
		mc.setFont(new Font("휴먼고딕", Font.PLAIN, 12));
		mc.setBounds(91, 214, 97, 23);
		RENT_2.add(mc);
		RC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원테이블에서 부울값가져오려면
				String rentsql = "SELECT USER_BOOL,USER_RENT_CNT FROM USER_TABLE WHERE USER_PHONE_NUMBER = " + "'"
						+ tf.getText() + "'" + ";"; //
				// SQL 에서 현재 시각을 아는 함수 now() 함수를 사용하여 대여 시간을 구했고, date_add(now(), interval 14 day)를 이용해 현재 시각에서 14일을 더한 값을 반납날짜로 지정함.
				String sql = "insert into RENT_TABLE(BOOK_ISBN,RENT_RETURN,USER_PHONE_NUMBER,RENT_DATE,RENT_RETURN_TIME) values(?,?,?,now(),date_add(now(), interval 14 day))";

				// 책대여권수는 user table 폰넘버로 구별하도록하고

				Connection tmpConn = dbConn.getConnection();

				ResultSet src = dbConn.executeQurey(rentsql);// 유저 부울 값 가져옴

				try {
					while (src.next()) {// 책대여권수
						cnt.setText(src.getString("USER_RENT_CNT"));
						aaa.setText(src.getString("USER_BOOL"));
					}
				} catch (Exception e2) {

				}
				try {// 대여권수가 1이거나 탈퇴된회원 ( 부울값=0) 일때 대출실패로 ㄱ
					if (cnt.getText().equals("1")) {
						JOptionPane.showMessageDialog(null, "대출실패", "회원님은 탈퇴되었거나 아직 대여중입니다.",
								JOptionPane.ERROR_MESSAGE);
					} else {
						PreparedStatement ps = tmpConn.prepareStatement(sql);
						// ps.setString(1, ISBN_TF.getText());
						ps.setString(1, tf.getText());// isbn
						ps.setString(2, "0");
						ps.setString(3, tf2.getText());


						int count = ps.executeUpdate();
						if (count == 0) {
							JOptionPane.showMessageDialog(null, "대출실패", "도서대출", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "대출되었습니다.", "도서반납", JOptionPane.NO_OPTION);
						}
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String str3 = "UPDATE BOOK_TABLE SET BOOK_PRE = 0 where BOOK_ISBN = '" + tf.getText() + "' ;";

				int src3 = dbConn.executeUpdate(str3);

				String str4 = "UPDATE USER_TABLE SET USER_RENT_CNT =  USER_RENT_CNT + 1 where USER_PHONE_NUMBER = '" + tf2.getText()
						+ "' ;";

				int src4 = dbConn.executeUpdate(str4);

			}
		});
	}
}
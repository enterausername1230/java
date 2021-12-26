package TP;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import comm.dbConnector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Connection;

public class Return_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField name;
	private JTextField number;
	private JTextField mail;
	dbConnector dbConn = new dbConnector();
	int i = 0; // 사용자 카운트
	private JTextField rent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Return_Frame frame = new Return_Frame();
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
	public Return_Frame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel RENT_2 = new JPanel();
		RENT_2.setLayout(null);
		RENT_2.setBackground(Color.WHITE);
		RENT_2.setBounds(12, 10, 392, 390);
		contentPane.add(RENT_2);

		JLabel Rent2LabelIsbn = new JLabel("ISBN");
		Rent2LabelIsbn.setBounds(12, 10, 57, 15);
		RENT_2.add(Rent2LabelIsbn);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(91, 7, 116, 21);
		RENT_2.add(textField);

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

		JLabel Rent2LabelName = new JLabel("\uBC18\uB0A9\uC790");
		Rent2LabelName.setBounds(12, 184, 57, 15);
		RENT_2.add(Rent2LabelName);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(91, 181, 116, 21);
		RENT_2.add(name);

		JLabel RentLabelUserPhone = new JLabel("\uC5F0\uB77D\uCC98");
		RentLabelUserPhone.setBounds(12, 209, 57, 15);
		RENT_2.add(RentLabelUserPhone);

		number = new JTextField();
		number.setColumns(10);
		number.setBounds(91, 206, 116, 21);
		RENT_2.add(number);

		JLabel Rent2LabelEmail = new JLabel("\uC774\uBA54\uC77C");
		Rent2LabelEmail.setBounds(12, 234, 57, 15);
		RENT_2.add(Rent2LabelEmail);
		JLabel img = new JLabel("");
		img.setBackground(Color.DARK_GRAY);
		img.setBounds(230, 59, 150, 199);
		RENT_2.add(img);
		mail = new JTextField();
		mail.setColumns(10);
		mail.setBounds(91, 231, 116, 21);
		RENT_2.add(mail);

		// 이미지 리스트
		ArrayList<Image> tmpImg = new ArrayList<>();

		JButton returnConfirmationButton = new JButton("\uD655\uC778");
		returnConfirmationButton.setBackground(SystemColor.activeCaption);
		returnConfirmationButton.setBounds(283, 6, 97, 23);
		RENT_2.add(returnConfirmationButton);
		
		returnConfirmationButton.addActionListener(new ActionListener() { // panel_4
			public void actionPerformed(ActionEvent e) {
				rent.setVisible(false);
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력하세요", "Message", JOptionPane.ERROR_MESSAGE);
				} // 제목 저자 출판사 가격
				else {
					String str = "select BOOK_TITLE, BOOK_AUTHIR, BOOK_PUB, BOOK_PRICE, BOOK_PRE, BOOK_IMAGE FROM BOOK_TABLE WHERE BOOK_ISBN = "
							+ "'" + textField.getText() + "'" + ";";
					String rent2 = "select USER_PHONE_NUMBER FROM RENT_TABLE WHERE BOOK_ISBN = " + "'" + textField.getText() + "'"
							+ ";";
					
					try {
						ResultSet src = dbConn.executeQurey(str);
						
						while (src.next()) {
							if (src.getInt(5) == 0) {
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
							} else {
								JOptionPane.showMessageDialog(null, src.getString(1) + "는 대여중이 아닙니다.", "Message",
										JOptionPane.ERROR_MESSAGE);
							}

						}
						

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.print("e1 에러");
					}
					try {
						ResultSet src = dbConn.executeQurey(rent2);
						while(src.next()) {
							rent.setText(src.getString("USER_PHONE_NUMBER"));
						}
					}
					catch (Exception e2) {
						// TODO: handle exception
					}
					String rent3 = "select USER_NAME,USER_MAIL,USER_PHONE_NUMBER FROM USER_TABLE WHERE USER_PHONE_NUMBER = "+"'"+rent.getText()+"'";
					try {
						ResultSet src = dbConn.executeQurey(rent3);
						while(src.next()) {
							name.setText(src.getString("USER_NAME"));
							number.setText(src.getString("USER_PHONE_NUMBER"));
							mail.setText(src.getString("USER_MAIL"));
						}
					}//
					catch (Exception e3) {
						// TODO: handle exception
					}
					
					/*try {
						String rent4= "UPDATE `java03_team03`.`BOOK_TABLE` SET `BOOK_PRE` = '1' WHERE (`BOOK_ISBN` = "+"'"+textField.getText()+"'";
					
						ResultSet src=dbConn.executeQurey(rent4);
					

						while(src.next()) {
							String str8 = "UPDATE BOOK_TABLE SET BOOK_PRE = '1' WHERE ISBN="+"'"+textField.getText()+"'";
							// System.out.println(str);
							int src8 = dbConn.executeUpdate(str8);
						}
						


					}
					
					catch (Exception e4) {
						
					}
					*/

				}
			}
		});
		// 대여 완료
		JButton returnCompleteButton = new JButton("\uBC18\uB0A9\uC644\uB8CC");
		returnCompleteButton.setBackground(SystemColor.activeCaption);
		returnCompleteButton.setBounds(283, 357, 97, 23);
		RENT_2.add(returnCompleteButton);

		rent = new JTextField();
		rent.setBounds(230, 63, 150, 186);
		RENT_2.add(rent);
		rent.setColumns(10);

		returnCompleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// textField_5 반납자 textField_6 연착처 textField_7 이메일
				if (name.getText().equals("") && number.getText().equals("") && mail.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "빈칸을 모두 입력해 주세요", "Message", JOptionPane.ERROR_MESSAGE);
				} else {
					String str = "update RENT_TABLE SET RENT_RETURN = 1 " + " WHERE USER_PHONE_NUMBER  = " + "'"
							+ number.getText() + "' AND BOOK_ISBN = '" + textField.getText() + "' AND RENT_RETURN = 0;";
					int src = dbConn.executeUpdate(str);
					String str2 = "update USER_TABLE SET USER_RENT_CNT = USER_RENT_CNT - 1 where USER_PHONE_NUMBER = '"
							+ number.getText() + "' ;";
					int src2 = dbConn.executeUpdate(str2);
					String str3 = "UPDATE BOOK_TABLE SET BOOK_PRE = 1 where BOOK_ISBN = '" + textField.getText()
							+ "' ;";
					int src3 = dbConn.executeUpdate(str3);

					JOptionPane.showMessageDialog(null, name.getText() + "는 " + textField_1.getText() + " 를 반납하였습니다.",
							"Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}

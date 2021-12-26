package Status;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;

import comm.dbConnector;

public class Member_Status extends JFrame {
	dbConnector dbConn = new dbConnector();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;

	public Member_Status() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		// JTable 관련 -NORTH
		JTable table;
		DefaultTableModel tableModel;

		Object[][] data = new Object[0][7]; // 일단 빈 row 값 삽입, 이때 두번째 인덱스 값 9은 9개의 열이 존제한다는 뜻으로 선언
		String[] columnNames = { "휴대전화", "이름", "생년월일", "메일", "이미지", "가입날짜", "대여권수" };

		tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel);
		// 이미지 리스트
		ArrayList<Image> tmpImg = new ArrayList<>();
		try {
			ResultSet src = dbConn.executeQurey(
					"select USER_PHONE_NUMBER, USER_NAME, USER_BIRTH, USER_MAIL, USER_IMAGE, USER_REG_DATA, USER_RENT_CNT from USER_TABLE WHERE USER_BOOL = 1;");
			while (src.next()) {

				// ResultSet같은 경우 Index 1번부터 시작

				Object[] tmp = { src.getString(1), src.getString(2), src.getString(3), src.getString(4), "없음",
						src.getString(6), src.getInt(7) };
				tableModel.addRow(tmp);

				
			
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 에서 어떤 row가 클릭될 경우 사용되는 이벤트
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// 이벤트 처리를 위한 table 관련 객제 정보 받기
				JTable sourceTable = (JTable) e.getSource();

				// 클릭한 행 및 컬럼 위치 확보
				int clickedTableRow = sourceTable.getSelectedRow(); // 행
				int clickedTableColumn = sourceTable.getSelectedColumn();// 필드

				if (clickedTableColumn == 4) {
					// 이미지 셀에 클릭하면 해당 row에 맞는 이미지 출력 - 이전에 sql 질의에서 받아온 arraylist에서 얻어오기
					JFrame showBookImgWindows = new JFrame();
					JLabel bookImg = new JLabel(new ImageIcon(tmpImg.get(clickedTableRow)));
					showBookImgWindows.getContentPane().add(bookImg);

					// 이미지를 보여주기 위해 임시로 생기는 창의 'x'버튼 누를 경우 창만 종료되는 이벤트리스너 구현
					showBookImgWindows.addWindowListener(new WindowAdapter() {

						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							System.out.println("Closed sub windows");
							e.getWindow().dispose(); // 해당 창만 완전 종료함
						}

					});
					showBookImgWindows.pack();
					showBookImgWindows.setLocationRelativeTo(null); // 화면 정중앙 배치
					showBookImgWindows.setVisible(true);

				}
			}
		});
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 784, 420);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 420, 784, 141);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel number = new JLabel("\uC804\uD654\uBC88\uD638");
		number.setFont(new Font("휴먼고딕", Font.PLAIN, 12));
		number.setBounds(12, 10, 57, 15);
		panel.add(number);

		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setFont(new Font("휴먼고딕", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(302, 10, 57, 15);
		panel.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setToolTipText("\uC804\uD654\uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694!");
		textField.setBounds(81, 7, 116, 21);
		panel.add(textField);
		textField.setColumns(10);

		JButton search = new JButton("\uAC80\uC0C9");
		search.setFont(new Font("휴먼고딕", Font.PLAIN, 12));
		search.setBackground(SystemColor.inactiveCaptionBorder);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rent2 = "select USER_NAME,USER_BIRTH,USER_MAIL,USER_REG_DATA,USER_RENT_CNT FROM USER_TABLE WHERE USER_PHONE_NUMBER = "
						+ "'" + textField.getText() + "'" + ";";
				
				try {
					ResultSet src = dbConn.executeQurey(rent2);

					while (src.next()) {

						textField_1.setText(src.getString("USER_NAME"));
						textField_2.setText(src.getString("USER_BIRTH"));
						textField_3.setText(src.getString("USER_MAIL"));
						textField_4.setText(src.getString("USER_REG_DATA"));
						
						// DB에서 BLOB 자료형으로 저장된 데이터 그림 데이터로 변환
						//

					}

				} catch (Exception e45) {
					// TODO: handle exception
				}

				// 여기서부터는 빌린 책 목록이 나오도록함.

				String rent3 = "select BOOK_ISBN,RENT_RETURN FROM RENT_TABLE WHERE USER_PHONE_NUMBER = " + "'" + textField.getText()
						+ "'" + ";";

				try {
					ResultSet src = dbConn.executeQurey(rent3);
					while (src.next()) {

						String n= src.getString("RENT_RETURN");
						if(n.equals("1")) {
							textField_6.setText("");
						}
						else{
							textField_6.setText(src.getString("BOOK_ISBN"));
						}
					}
				} catch (Exception e45) {
					// TODO: handle exception
				}

			}
		});
		search.setBounds(81, 31, 97, 23);
		panel.add(search);

		JLabel lblNewLabel_1_1 = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
		lblNewLabel_1_1.setFont(new Font("휴먼고딕", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(302, 35, 57, 15);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("\uBA54\uC77C");
		lblNewLabel_1_1_1.setFont(new Font("휴먼고딕", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(312, 69, 57, 15);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("\uAC00\uC785\uB0A0\uC9DC");
		lblNewLabel_1_1_1_1.setFont(new Font("휴먼고딕", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(302, 105, 57, 15);
		panel.add(lblNewLabel_1_1_1_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(371, 7, 188, 21);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(371, 35, 188, 21);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(371, 66, 188, 21);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(371, 102, 188, 21);
		panel.add(textField_4);

		JLabel dd = new JLabel("\uB300\uC5EC\uC911\uC778 \uCC45");
		dd.setFont(new Font("휴먼고딕", Font.PLAIN, 12));
		dd.setBounds(626, 10, 83, 15);
		panel.add(dd);

		textField_6 = new JTextField();
		textField_6.setBounds(626, 52, 116, 21);
		panel.add(textField_6);
		textField_6.setColumns(10);
		// 화면 표시
		setSize(800, 600);
		setLocationRelativeTo(null); // 화면 정중앙 배치
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Member_Status();
	}
}
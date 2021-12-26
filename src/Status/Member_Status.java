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
		// JTable ���� -NORTH
		JTable table;
		DefaultTableModel tableModel;

		Object[][] data = new Object[0][7]; // �ϴ� �� row �� ����, �̶� �ι�° �ε��� �� 9�� 9���� ���� �����Ѵٴ� ������ ����
		String[] columnNames = { "�޴���ȭ", "�̸�", "�������", "����", "�̹���", "���Գ�¥", "�뿩�Ǽ�" };

		tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel);
		// �̹��� ����Ʈ
		ArrayList<Image> tmpImg = new ArrayList<>();
		try {
			ResultSet src = dbConn.executeQurey(
					"select USER_PHONE_NUMBER, USER_NAME, USER_BIRTH, USER_MAIL, USER_IMAGE, USER_REG_DATA, USER_RENT_CNT from USER_TABLE WHERE USER_BOOL = 1;");
			while (src.next()) {

				// ResultSet���� ��� Index 1������ ����

				Object[] tmp = { src.getString(1), src.getString(2), src.getString(3), src.getString(4), "����",
						src.getString(6), src.getInt(7) };
				tableModel.addRow(tmp);

				
			
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ���� � row�� Ŭ���� ��� ���Ǵ� �̺�Ʈ
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// �̺�Ʈ ó���� ���� table ���� ���� ���� �ޱ�
				JTable sourceTable = (JTable) e.getSource();

				// Ŭ���� �� �� �÷� ��ġ Ȯ��
				int clickedTableRow = sourceTable.getSelectedRow(); // ��
				int clickedTableColumn = sourceTable.getSelectedColumn();// �ʵ�

				if (clickedTableColumn == 4) {
					// �̹��� ���� Ŭ���ϸ� �ش� row�� �´� �̹��� ��� - ������ sql ���ǿ��� �޾ƿ� arraylist���� ������
					JFrame showBookImgWindows = new JFrame();
					JLabel bookImg = new JLabel(new ImageIcon(tmpImg.get(clickedTableRow)));
					showBookImgWindows.getContentPane().add(bookImg);

					// �̹����� �����ֱ� ���� �ӽ÷� ����� â�� 'x'��ư ���� ��� â�� ����Ǵ� �̺�Ʈ������ ����
					showBookImgWindows.addWindowListener(new WindowAdapter() {

						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							System.out.println("Closed sub windows");
							e.getWindow().dispose(); // �ش� â�� ���� ������
						}

					});
					showBookImgWindows.pack();
					showBookImgWindows.setLocationRelativeTo(null); // ȭ�� ���߾� ��ġ
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
		number.setFont(new Font("�޸հ��", Font.PLAIN, 12));
		number.setBounds(12, 10, 57, 15);
		panel.add(number);

		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setFont(new Font("�޸հ��", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(302, 10, 57, 15);
		panel.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setToolTipText("\uC804\uD654\uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694!");
		textField.setBounds(81, 7, 116, 21);
		panel.add(textField);
		textField.setColumns(10);

		JButton search = new JButton("\uAC80\uC0C9");
		search.setFont(new Font("�޸հ��", Font.PLAIN, 12));
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
						
						// DB���� BLOB �ڷ������� ����� ������ �׸� �����ͷ� ��ȯ
						//

					}

				} catch (Exception e45) {
					// TODO: handle exception
				}

				// ���⼭���ʹ� ���� å ����� ����������.

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
		lblNewLabel_1_1.setFont(new Font("�޸հ��", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(302, 35, 57, 15);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("\uBA54\uC77C");
		lblNewLabel_1_1_1.setFont(new Font("�޸հ��", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(312, 69, 57, 15);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("\uAC00\uC785\uB0A0\uC9DC");
		lblNewLabel_1_1_1_1.setFont(new Font("�޸հ��", Font.PLAIN, 12));
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
		dd.setFont(new Font("�޸հ��", Font.PLAIN, 12));
		dd.setBounds(626, 10, 83, 15);
		panel.add(dd);

		textField_6 = new JTextField();
		textField_6.setBounds(626, 52, 116, 21);
		panel.add(textField_6);
		textField_6.setColumns(10);
		// ȭ�� ǥ��
		setSize(800, 600);
		setLocationRelativeTo(null); // ȭ�� ���߾� ��ġ
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Member_Status();
	}
}
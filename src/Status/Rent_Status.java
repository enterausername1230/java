package Status;

import java.awt.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.*;

import comm.dbConnector;

public class Rent_Status extends JFrame {
	dbConnector dbConn = new dbConnector();

	public Rent_Status() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		// JTable ���� -NORTH
		JTable table;
		DefaultTableModel tableModel;
		Object[][] data = new Object[0][6]; // �ϴ� �� row �� ����, �̶� �ι�° �ε��� �� 9�� 9���� ���� �����Ѵٴ� ������ ����
		String[] columnNames = { "SEQ", "�뿩��¥", "�ݳ���¥", "�ݳ�����", "ISBN", "�޴���ȭ" };

		tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel);
		try {
			ResultSet src = dbConn.executeQurey("select * from RENT_TABLE;");
			while (src.next()) {
				// SQL���� BOOK_PRE �ʵ� ������� ���� �´� ���ڿ� ���� - 1:����, 2:�̺���
				String liveState;

				if (src.getInt(4) == 1) {
					liveState = "�ݳ��Ϸ�";
				} else {
					liveState = "�ݳ����";
				}

				// ResultSet���� ��� Index 1������ ����

				Object[] tmp = { src.getInt(1), src.getString(2), src.getString(3), liveState, src.getString(5),
						src.getString(6) };
				tableModel.addRow(tmp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(new JScrollPane(table), BorderLayout.NORTH);
		// ȭ�� ǥ��
		setSize(800, 600);
		setLocationRelativeTo(null); // ȭ�� ���߾� ��ġ
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Rent_Status();
	}

}

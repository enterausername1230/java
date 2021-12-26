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
		// JTable 관련 -NORTH
		JTable table;
		DefaultTableModel tableModel;
		Object[][] data = new Object[0][6]; // 일단 빈 row 값 삽입, 이때 두번째 인덱스 값 9은 9개의 열이 존제한다는 뜻으로 선언
		String[] columnNames = { "SEQ", "대여날짜", "반납날짜", "반납여부", "ISBN", "휴대전화" };

		tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel);
		try {
			ResultSet src = dbConn.executeQurey("select * from RENT_TABLE;");
			while (src.next()) {
				// SQL에서 BOOK_PRE 필드 결과값에 따라 맞는 문자열 배정 - 1:보유, 2:미보유
				String liveState;

				if (src.getInt(4) == 1) {
					liveState = "반납완료";
				} else {
					liveState = "반납대기";
				}

				// ResultSet같은 경우 Index 1번부터 시작

				Object[] tmp = { src.getInt(1), src.getString(2), src.getString(3), liveState, src.getString(5),
						src.getString(6) };
				tableModel.addRow(tmp);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(new JScrollPane(table), BorderLayout.NORTH);
		// 화면 표시
		setSize(800, 600);
		setLocationRelativeTo(null); // 화면 정중앙 배치
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Rent_Status();
	}

}

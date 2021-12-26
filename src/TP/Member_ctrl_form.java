package TP;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JToggleButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import java.awt.TextField;
import java.awt.CardLayout;
import javax.swing.JSeparator;
import net.miginfocom.swing.MigLayout;
import java.awt.Panel;
import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDesktopPane;
import java.awt.Label;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
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
import comm.dbConnector;
import Status.Member_Status;

public class Member_ctrl_form extends JFrame {
	private JPanel contentPane;
	private JTextField un;
	private JTextField ub;
	private JTextField ph;
	private JTextField us;
	private final Action action = new SwingAction();
	JLabel secondImg = new JLabel();	//�̹����� �ű� ���̺�

	private String filePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member_ctrl_form frame = new Member_ctrl_form();
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
	public Member_ctrl_form() {
		JButton im = new JButton("ȸ�����"); // ȸ�����
		im.setBackground(SystemColor.activeCaption);
		im.setBounds(12, 133, 97, 23);

		// ArrayList<Image> tmpImg = new ArrayList<>(); // �̹��� ����
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(5, 5, 1603, 23);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton selectMemberManagementButton = new JButton("ȸ������"); // ȸ������
		selectMemberManagementButton.setBounds(0, 0, 97, 23);
		panel.add(selectMemberManagementButton);

		JLabel lb = new JLabel("");
		lb.setBounds(509, 4, 57, 15);
		panel.add(lb);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(0, 28, 575, 457);
		contentPane.add(panel_5);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setLayout(null);

		JLabel labelName = new JLabel("�̸�");
		labelName.setBounds(16, 10, 41, 15);
		panel_5.add(labelName);

		// ȸ�� �̸��� �Է��� �ؽ�Ʈ �ʵ�
		un = new JTextField();
		un.setBounds(78, 7, 116, 21);
		panel_5.add(un);
		un.setColumns(10);

		JLabel labelBirth = new JLabel("�������");
		labelBirth.setBounds(16, 34, 57, 15);
		panel_5.add(labelBirth);

		// ȸ���� ��������� �Է��� �ؽ�Ʈ �ʵ�

		JLabel labelPhoneNumber = new JLabel("����ó");
		labelPhoneNumber.setBounds(16, 59, 41, 15);
		panel_5.add(labelPhoneNumber);

		// ȸ���� �޴���ȭ�� �Է��� �ؽ�Ʈ �ʵ�
		ub = new JTextField();
		ub.setBounds(78, 31, 116, 21);
		panel_5.add(ub);
		ub.setColumns(10);

		JLabel labelMail = new JLabel("�̸���");
		labelMail.setBounds(16, 84, 57, 23);
		panel_5.add(labelMail);

		// ȸ���� �̸��ϸ� �Է��� �ؽ�Ʈ �ʵ�
		us = new JTextField();
		us.setBounds(78, 87, 217, 21);
		panel_5.add(us);
		us.setColumns(20);

		JLabel img = new JLabel(""); // �̹���
		img.setForeground(Color.BLACK);
		img.setBackground(SystemColor.activeCaption);
		img.setBounds(354, 10, 166, 200);
		panel_5.add(img);
		ImageIcon icon = new ImageIcon("gray.jpg");
		img.setIcon(icon);
		
		

		
		
		panel_5.add(im);
		
		
		JButton dm = new JButton("ȸ������"); // ȸ�� ����
		dm.setBackground(SystemColor.activeCaption);
		dm.setBounds(12, 166, 97, 23);
		panel_5.add(dm);
		// ȸ�� �̹���
		JFileChooser memberImg = new JFileChooser();
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("All Images", "jpg", "jpge", "png", "gif",
				"svg");
		memberImg.setFileFilter(fileFilter);
		ph = new JTextField();
		ph.setBounds(78, 56, 217, 21);
		panel_5.add(ph);
		ph.setColumns(20);
		// �̸��� �Է��ϰ� "ȸ���˻�" ��ư�� ������ �������,����ó,�̸����� ���� USER_BIRTH, USER_PHONE_NUMBER,
		// USER_MAIL�� ���
		JButton memberSearchButton = new JButton("ȸ���˻�"); // ȸ�� �˻�
		memberSearchButton.setBackground(SystemColor.activeCaption);
		memberSearchButton.setBounds(206, 6, 97, 23);
		panel_5.add(memberSearchButton);
		memberSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dbConnector dbConn = new dbConnector();
				String str = "select USER_BIRTH,USER_MAIL,USER_PHONE_NUMBER,USER_IMAGE,USER_NAME FROM USER_TABLE WHERE USER_NAME = "
						+ "'" + un.getText() + "'" + ";";
				try {
					ResultSet src = dbConn.executeQurey(str);
					while (src.next()) {
						String s = src.getString("USER_NAME");

						ub.setText(src.getString("USER_BIRTH"));
						ph.setText(src.getString("USER_PHONE_NUMBER"));

						us.setText(src.getString("USER_MAIL"));

						try {
							InputStream img2 = src.getBinaryStream("USER_IMAGE");
							img.setIcon(new ImageIcon(ImageIO.read(img2)));
							
							secondImg = img;
						} catch (Exception e3) {

						}
					}
					src.close();

				} catch (SQLException e2) {

				}
				//�̹��� ��â���� ����
				JFrame showBookImgWindows = new JFrame();
				showBookImgWindows.add(secondImg);
				showBookImgWindows.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						e.getWindow().dispose(); // �ش� â�� ���� ������
					}

				});
				showBookImgWindows.pack();
				showBookImgWindows.setLocationRelativeTo(null); // ȭ�� ���߾� ��ġ
				showBookImgWindows.setVisible(true);
				
				if (ph.getText().equals("")) {
					im.setVisible(true);
					dm.setVisible(false);
					JOptionPane.showMessageDialog(null, "ȸ���� �ƴմϴ�. '���'�� �����մϴ�.", "Message", JOptionPane.ERROR_MESSAGE);
				} else {
					im.setVisible(false);
					dm.setVisible(true);
					JOptionPane.showMessageDialog(null, "ȸ���Դϴ�. '����'�� '����'�� �����մϴ�.", "Message",
							JOptionPane.ERROR_MESSAGE);
				}

				

			}
		});
		// �ؽ�Ʈ �ʵ忡 ���� �Է��ϰ� "ȸ�����" ��ư�� ������ �̸�, �������, ����ó, �̸���, ������� USER_TABLE�� �����͸� ����
		// ȸ�� ����
		JButton dm_1 = new JButton("ȸ�� ����");
		dm_1.setBackground(SystemColor.activeCaption);
		dm_1.setBounds(12, 203, 97, 23);
		panel_5.add(dm_1);
		dm_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbConnector dbConn = new dbConnector();
				if (un.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է��ϼ���!", "Message", JOptionPane.ERROR_MESSAGE);
				} else {
					String resultSetSql = "select USER_BOOL from USER_TABLE WHERE USER_NAME = " + "'" + un.getText()
							+ "' ;";
					try {
						ResultSet resultSetSrc = dbConn.executeQurey(resultSetSql);
						if (resultSetSrc.next()) {
							if (resultSetSrc.getInt(1) == 1) {
								if (!ph.getText().equals("")) {
									String str = "update USER_TABLE SET USER_PHONE_NUMBER = '" + ph.getText()
											+ "' WHERE USER_NAME = " + "'" + un.getText() + "' ;";
									// System.out.println(str);
									int src = dbConn.executeUpdate(str);
									src = 0;
									str = null;
								}

								if (!us.getText().equals("")) {
									String str = "update USER_TABLE SET USER_MAIL = '" + us.getText()
											+ "' WHERE USER_NAME = " + "'" + un.getText() + "';";
									// System.out.println(str);
									int src2 = dbConn.executeUpdate(str);
									src2 = 0;
									str = null;
								}

								if (!ub.getText().equals("")) {
									String str = "update USER_TABLE SET USER_BIRTH = '" + ub.getText()
											+ "' WHERE USER_NAME = " + "'" + un.getText() + "' ;";
									int src1 = dbConn.executeUpdate(str);
									src1 = 0;
									str = null;
								}

							} else {
								JOptionPane.showMessageDialog(null, un.getText() + "�� ȸ���� �ƴմϴ�.", "Message",
										JOptionPane.ERROR_MESSAGE);
							}
							resultSetSrc.close();

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				un.setText("");
				ph.setText("");
				ub.setText("");
				us.setText("");
			}
		});
		im.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbConnector dbConn = new dbConnector();
				String sql = "insert into USER_TABLE(USER_PHONE_NUMBER,USER_NAME,USER_BIRTH,USER_MAIL,USER_IMAGE,USER_BOOL,USER_RENT_CNT) values(?,?,?,?,?,?,?)";
				Connection tmpConn = dbConn.getConnection();
				try {
					PreparedStatement ps = tmpConn.prepareStatement(sql);

					ps.setString(1, ph.getText());
					ps.setString(2, un.getText());
					ps.setString(3, ub.getText());
					ps.setString(4, us.getText());
					File tmpFile = new File(filePath);

					ps.setBinaryStream(5, new FileInputStream(tmpFile), tmpFile.length());

					ps.setString(6, "1");
					ps.setString(7, "0");
					int count = ps.executeUpdate();

					if (count == 0) {
						JOptionPane.showMessageDialog(null, "��� : " + un.getText() + "��(��) ��Ͽ� �����Ͽ����ϴ�.", "�ű�ȸ�����",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "��� : " + un.getText() + "��(��) ����� �Ϸ�Ǿ����ϴ�.", "�ű�ȸ�����",
								JOptionPane.NO_OPTION);
					}
					tmpConn.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		// ���̺��� ���� ��� �����ؼ� �����Ұ��� �𸣰���

		dm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dbConnector dbConn = new dbConnector();
				String str = "select USER_RENT_CNT,USER_BOOL FROM USER_TABLE WHERE USER_NAME= " + "'" + un.getText()
						+ "'" + ";";

				try {// �׳� �뿩���� å������ �����Ұ���
					ResultSet src = dbConn.executeQurey(str);

					while (src.next()) {

						String s = src.getString("USER_RENT_CNT");

						if (s.equals("0")) {

							String str2 = "update USER_TABLE SET USER_BOOL = 0 where USER_NAME= '" + un.getText()
									+ "';";
							int src2 = dbConn.executeUpdate(str2);
						}
						if (s.equals("1")) {

							JOptionPane.showMessageDialog(null, "ȸ�������Ұ���", "�뿩���� ������ �ֽ��ϴ�.", JOptionPane.ERROR_MESSAGE);
						}

					}
					src.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block

				}

			}
		});

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(16, 108, 57, 15);
		panel_5.add(lblNewLabel_6);

		// "ȸ����Ϻ���" ��ư�� ������ USER_TABLE�� ���� ��� ������ JTABLE�� ���
		JButton membershipListButton = new JButton("\uD68C\uC6D0\uBAA9\uB85D\uBCF4\uAE30");
		membershipListButton.setBackground(SystemColor.activeCaption);
		membershipListButton.setBounds(12, 268, 154, 23);
		panel_5.add(membershipListButton);
		membershipListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(null, "���̺�ǥ�� ���� ��ħ��", "Message",
				// JOptionPane.ERROR_MESSAGE);
				Member_Status ms = new Member_Status();
				ms.setVisible(true);
			}

		});

		// ���� ��� ��ư
		JButton btnNewButton = new JButton("\uC0AC\uC9C4\uB4F1\uB85D");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setBounds(392, 220, 97, 23);
		panel_5.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = memberImg.showOpenDialog(null);
				filePath = memberImg.getSelectedFile().getPath();
				JOptionPane.showMessageDialog(null, filePath, "����� ���õ� ������", JOptionPane.NO_OPTION);
				ImageIcon icon = new ImageIcon(filePath);
				img.setIcon(icon);
				
				
				secondImg = img;	//�̹��� ����
				//�̹��� ��â���� ����
				JFrame showBookImgWindows = new JFrame();
				showBookImgWindows.add(secondImg);
				showBookImgWindows.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						e.getWindow().dispose(); // �ش� â�� ���� ������
					}

				});
				showBookImgWindows.pack();
				showBookImgWindows.setLocationRelativeTo(null); // ȭ�� ���߾� ��ġ
				showBookImgWindows.setVisible(true);

			}
		});
		
		/*
		img.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFrame showBookImgWindows = new JFrame();
				showBookImgWindows.add(img);
				showBookImgWindows.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						e.getWindow().dispose(); // �ش� â�� ���� ������
					}

				});
				showBookImgWindows.pack();
				showBookImgWindows.setLocationRelativeTo(null); // ȭ�� ���߾� ��ġ
				showBookImgWindows.setVisible(true);
				
			}
		});
		*/


	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {

		}
	}
}

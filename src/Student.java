import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Student {
	private Connection connection;

	private JFrame frmStudentMangment;
	private JTextField textFieldID;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textFieldStudentName;
	private JTextField textFieldFees;
	private JButton btnNewButton;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDel;
	private JLabel lblNewLabel_4;
	private JTable table;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField textSearch;
	private JTextField textFieldDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student window = new Student();
					window.frmStudentMangment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Student() {
		initialize();
		connection = SqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStudentMangment = new JFrame();
		frmStudentMangment.getContentPane().setBackground(Color.YELLOW);
		frmStudentMangment.setTitle("Student Management");
		frmStudentMangment.setBounds(100, 100, 708, 481);
		frmStudentMangment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentMangment.getContentPane().setLayout(null);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(336, 139, 136, 30);
		frmStudentMangment.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\NOUR ALSHAR\\eclipse-workspace\\MohamadNourAlShaar_1933176\\img\\download.png"));
		lblNewLabel.setBounds(10, 81, 182, 177);
		frmStudentMangment.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(287, 155, 46, 14);
		frmStudentMangment.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Student Name:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(220, 191, 105, 20);
		frmStudentMangment.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Fees:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(275, 242, 46, 14);
		frmStudentMangment.getContentPane().add(lblNewLabel_3);
		
		textFieldStudentName = new JTextField();
		textFieldStudentName.setColumns(10);
		textFieldStudentName.setBounds(336, 187, 136, 30);
		frmStudentMangment.getContentPane().add(textFieldStudentName);
		
		textFieldFees = new JTextField();
		textFieldFees.setColumns(10);
		textFieldFees.setBounds(336, 228, 136, 30);
		frmStudentMangment.getContentPane().add(textFieldFees);
		
		btnNewButton = new JButton("new");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String query = "select ID, Name, Fees, DateOfBirth from StudentInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					textFieldID.setText(null);
					textFieldStudentName.setText(null);
					textFieldFees.setText(null);
					textFieldDate.setText(null);
		
					
					
					rs.close();
					pst.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2);
				}
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnNewButton.setBounds(531, 92, 136, 37);
		frmStudentMangment.getContentPane().add(btnNewButton);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				try {
			
					String query = "insert into StudentInfo (ID,Name,Fees,DateOfBirth) values (?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textFieldID.getText());
					pst.setString(2, textFieldStudentName.getText());
					pst.setString(3, textFieldFees.getText());
					pst.setString(3, textFieldDate.getText());
					
			
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Saved");
					pst.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					
					JOptionPane.showMessageDialog(null, e2);
				}
				
				
				
				
			}
		});
		btnInsert.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnInsert.setIcon(new ImageIcon("C:\\Users\\NOUR ALSHAR\\eclipse-workspace\\MohamadNourAlShaar_1933176\\img\\button_blue_add.png"));
		btnInsert.setBounds(531, 132, 136, 42);
		frmStudentMangment.getContentPane().add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					

					String query = "update StudentInfo set ID='"+ textFieldID.getText() + "', Name'" + textFieldStudentName.getText() +
							"', Fees='" + textFieldFees.getText() + "',Date='"+ textFieldDate.getText() +  "' where ID='" + 
							textFieldID.getText() + "'";
					
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Updated");
					pst.close();
			
			
		} catch (Exception e2) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, e2);
		}
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnUpdate.setIcon(new ImageIcon("C:\\Users\\NOUR ALSHAR\\eclipse-workspace\\MohamadNourAlShaar_1933176\\img\\button_pink_close.png"));
		btnUpdate.setBounds(531, 183, 136, 42);
		frmStudentMangment.getContentPane().add(btnUpdate);
		
		btnDel = new JButton("Delete");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int action = JOptionPane.showConfirmDialog(null, "Are Yu sure to delete?", "Delete",JOptionPane.YES_NO_OPTION);
				if (action == 0) {
					
					
					try {
						

						String query = "delete from StudentInfo where ID='" + textFieldID.getText() + "'";
						
						PreparedStatement pst = connection.prepareStatement(query);
						
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Data Removed");
						pst.close();
				
				
					} catch (Exception e2) {
						// TODO: handle exception
						
						JOptionPane.showMessageDialog(null, e2);
					}
					
					
					
				}
				
				

				
				
				
				
			}
		});
		btnDel.setIcon(new ImageIcon("C:\\Users\\NOUR ALSHAR\\eclipse-workspace\\MohamadNourAlShaar_1933176\\img\\button_red_stop.png"));
		btnDel.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnDel.setBounds(531, 236, 136, 42);
		frmStudentMangment.getContentPane().add(btnDel);
		
		lblNewLabel_4 = new JLabel("Date Of Birth:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(220, 280, 101, 14);
		frmStudentMangment.getContentPane().add(lblNewLabel_4);
		
		table = new JTable();
		table.setBounds(67, 358, 529, 84);
		frmStudentMangment.getContentPane().add(table);
		
		lblNewLabel_5 = new JLabel("LaSalle College-Student Dashboeard  ");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_5.setBounds(152, 11, 466, 30);
		frmStudentMangment.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("International School - Montreal Canada");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_6.setForeground(Color.BLACK);
		lblNewLabel_6.setBounds(201, 52, 395, 20);
		frmStudentMangment.getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Student Informaion");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setForeground(Color.BLACK);
		lblNewLabel_7.setBounds(273, 90, 182, 14);
		frmStudentMangment.getContentPane().add(lblNewLabel_7);
		
		JDateChooser datetext = new JDateChooser();
		datetext.setBounds(67, 269, 136, 30);
		frmStudentMangment.getContentPane().add(datetext);
		
		textSearch = new JTextField();
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		textSearch.setBounds(275, 327, 168, 20);
		frmStudentMangment.getContentPane().add(textSearch);
		textSearch.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Seach Students by Name:");
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(83, 328, 182, 14);
		frmStudentMangment.getContentPane().add(lblNewLabel_8);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(336, 269, 136, 25);
		frmStudentMangment.getContentPane().add(textFieldDate);
		textFieldDate.setColumns(10);
	}
}

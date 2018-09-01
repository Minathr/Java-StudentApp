package a01043797.assignment5.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import a01043797.assignment5.dao.IOStream;
import a01043797.assignment5.data.Student;
import a01043797.assignment5.database.DatabaseDAO;

@SuppressWarnings("serial")
public class StudentFrame extends JFrame {

	ArrayList<Student> students = new ArrayList<Student>();
	DatabaseDAO studentDB;
	private DataDialog dialog;

	private final StudentFrame studentFrame;

	public StudentFrame() {
		super("Driver");

		studentFrame = this;

		createMenu();
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		studentDB = new DatabaseDAO();
		studentDB.loadDriver();
		studentDB.connect();
	}
	
	private void createMenu() {
		// Creating menu bar and adding that to the frame
		JMenuBar mainMenuBar = new JMenuBar();
		setJMenuBar(mainMenuBar);

		// Adding menus to menu bar
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		mainMenuBar.add(fileMenu);
		JMenu dataMenu = new JMenu("Data");
		dataMenu.setMnemonic('D');
		mainMenuBar.add(dataMenu);
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		mainMenuBar.add(helpMenu);

		// Adding items to the menus
		JMenuItem load = new JMenuItem("Load Data", KeyEvent.VK_L);
		fileMenu.add(load);
		JMenuItem save = new JMenuItem("Save Data", KeyEvent.VK_S);
		fileMenu.add(save);
		fileMenu.addSeparator();
		JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_E);
		fileMenu.add(exit);
		
		JMenuItem random = new JMenuItem("Random Student", KeyEvent.VK_R);
		dataMenu.add(random);		
		JMenuItem choice = new JMenuItem("Choose Student", KeyEvent.VK_C);
		dataMenu.add(choice);
		
		JMenuItem about = new JMenuItem("About", KeyEvent.VK_A);
		helpMenu.add(about);

		// Adding actions to menu items
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String inputFile = "studentData.txt";
				students = IOStream.reader(inputFile);
				String createQuery = "create table A01043797Student("
						+ "studentID VARCHAR(9), "
						+ "firstName VARCHAR(10), "
						+ "lastName VARCHAR(10), "
						+ "quizzes decimal(5,2), "
						+ "assignments decimal(5,2), "
						+ "exams decimal(5,2), "
						+ "primary key (studentID))" ;
				//need to check if the table exists
				ResultSet foundTable;
				foundTable = studentDB.findTable("A01043797Student");

				if (foundTable != null) {
					String deleteQuery = "Drop table A01043797Student";
					studentDB.deleteTable(deleteQuery);
				}
				
				if ( studentDB.createTable(createQuery) ) {
					int i = 0;
					while (i < students.size()) {
						String insertQuery = "insert into A01043797Student values('"
							+students.get(i).getStudentID()+"', '"
							+students.get(i).getFirstName()+"', '"
							+students.get(i).getLastName()+"', '"
							+students.get(i).getQuizzes()+"', '"
							+students.get(i).getAssignments()+"', '"
							+students.get(i).getExams()+"');";
						studentDB.updateData(insertQuery);
						i++;
					}
					JOptionPane.showMessageDialog(StudentFrame.this, 
							"The A01043797Student table has been created and data has been added to it from input file", "Load data", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(StudentFrame.this, 
							"Creating A01043797Student table has been failed.", "Load data", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( students.size() == 0 )
				{
					JOptionPane.showMessageDialog(StudentFrame.this, "Please load the data first.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {				
					int i = 0;
					while (i < students.size()) {
						String updateQuery = "Update A01043797Student set "
							+ "firstName = '" + students.get(i).getFirstName()
							+ "', lastName = '" + students.get(i).getLastName()+"', '"
							+ "', quizzes = '" + students.get(i).getQuizzes()+"', '"
							+ "', assignments = '" + students.get(i).getAssignments()+"', '"
							+ "', exams = '" + students.get(i).getExams()+"' "
							+ "where studentID == '" + students.get(i).getStudentID() + "';";
						studentDB.updateData(updateQuery);
						i++;
					}
					JOptionPane.showMessageDialog(StudentFrame.this, 
							"The A01043797Student table has been updated.", "Save data", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		exit.addActionListener(e -> {
			String outputFile = "output.txt";
			try {
				IOStream.writer(outputFile, students);
			} catch (Exception exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			}
			studentDB.shutDown();
			System.exit(0);
		});
		
		random.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*if ( students.size() == 0 )
				{
					JOptionPane.showMessageDialog(StudentFrame.this, "Please load the data first.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					Random rnd = new Random();
					int  i = rnd.nextInt(students.size()) + 0;
					dialog = new DataDialog(StudentFrame.this, students.get(i));
					dialog.setVisible(true);
					students.set(i, dialog.getNewStudent());
				}*/
				JOptionPane.showMessageDialog(StudentFrame.this, "This feature is not available.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		choice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if ( students.size() == 0 )
				{
					JOptionPane.showMessageDialog(StudentFrame.this, "Please load the data first.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String studentID = JOptionPane.showInputDialog(StudentFrame.this, "Please enter the Student ID", "Student ID", JOptionPane.OK_CANCEL_OPTION);
					String selectQuery = "select * from A01043797Student where studentID = "+studentID + ";" ;
					ResultSet chosenStudent = studentDB.selectData(selectQuery);
					if (chosenStudent == null) {
						JOptionPane.showMessageDialog(StudentFrame.this, "There is no student with student ID of " + studentID, "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						dialog = new DataDialog(StudentFrame.this, chosenStudent);
						dialog.setVisible(true);
						String updateQuery = "update A01043797Student set " 
								+ "firstName = " + dialog.getNewStudent().getFirstName()
								+ "lastName = " + dialog.getNewStudent().getLastName()
								+ "quizzes = " + dialog.getNewStudent().getQuizzes()
								+ "assignments = " + dialog.getNewStudent().getAssignments()
								+ "exams = " + dialog.getNewStudent().getExams()
								+" where studentID= " + dialog.getNewStudent().getStudentID()+ ";";
						studentDB.updateData(updateQuery);
						for (int i=0; i<students.size(); i++)
						{
							if (students.get(i).getStudentID().equals(studentID))
							{
								students.set(i, dialog.getNewStudent());
								break;
							}
						}
					}
				}
			}
		});
		
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(StudentFrame.this, "Fatemeh Taheri - A01043797", "Assignment4", JOptionPane.INFORMATION_MESSAGE);				
			}
		});
	}

}

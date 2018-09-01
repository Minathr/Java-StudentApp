package a01043797.assignment5.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a01043797.assignment5.data.Student;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

public class DataDialog extends JFrame {

	private Student newStudent;
	private JPanel contentPane;
	private JTextField TXTFirstName;
	private JTextField TXTLastName;
	private JTextField TXTStudentID;
	private JTextField TXTQuiz;
	private JTextField TXTAssignment;
	private JTextField TXTExam;
	
	public DataDialog(StudentFrame studentFrame, ResultSet student) {
		setTitle("Student Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600,400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][grow][]", "[][][][][][][][][][][][][][]"));
		
		JLabel firstName = new JLabel("First name");
		contentPane.add(firstName, "cell 1 1");
		
		TXTFirstName = new JTextField();
		try {
			TXTFirstName.setText(student.getString(2));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane.add(TXTFirstName, "cell 15 1,growx");
		TXTFirstName.setColumns(10);
		
		JLabel lastName = new JLabel("Last name");
		contentPane.add(lastName, "cell 1 3");
		
		TXTLastName = new JTextField();
		try {
			TXTLastName.setText(student.getString(3));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane.add(TXTLastName, "cell 15 3,growx");
		TXTLastName.setColumns(10);
		
		JLabel studentID = new JLabel("Student ID");
		contentPane.add(studentID, "cell 1 5");
		
		TXTStudentID = new JTextField();
		try {
			TXTStudentID.setText(student.getString(1));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane.add(TXTStudentID, "cell 15 5,growx");
		TXTStudentID.setColumns(10);
		
		JLabel marks = new JLabel("Marks");
		contentPane.add(marks, "cell 1 7");
		
		JLabel quizzes = new JLabel("Quiz marks");
		contentPane.add(quizzes, "cell 3 8");
		
		TXTQuiz = new JTextField();
		try {
			TXTQuiz.setText(student.getString(4));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane.add(TXTQuiz, "cell 15 8,growx");
		TXTQuiz.setColumns(10);
		
		JLabel assignments = new JLabel("Assignment marks");
		contentPane.add(assignments, "cell 3 10");
		
		TXTAssignment = new JTextField();
		try {
			TXTAssignment.setText(student.getString(5));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane.add(TXTAssignment, "cell 15 10,growx");
		TXTAssignment.setColumns(10);
		
		JLabel exams = new JLabel("Exam marks");
		contentPane.add(exams, "cell 3 12");
		
		TXTExam = new JTextField();
		try {
			TXTExam.setText(student.getString(6));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane.add(TXTExam, "cell 15 12,growx");
		TXTExam.setColumns(10);
		
		JButton saveBtn = new JButton("Save");
		contentPane.add(saveBtn, "cell 14 13");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newStudent.setFirstName(TXTFirstName.getText());
				newStudent.setLastName(TXTLastName.getText());
				newStudent.setStudentID(TXTStudentID.getText());
				try {
					newStudent.setQuizzes(Double.parseDouble(TXTQuiz.getText()));
					newStudent.setAssignments(Double.parseDouble(TXTAssignment.getText()));
					newStudent.setExams(Double.parseDouble(TXTExam.getText()));
					dispose();
				} catch (Exception exeption) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(DataDialog.this, 
							"Please Enter a valid number in marks fields.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton cancelBtn = new JButton("Cancel");
		contentPane.add(cancelBtn, "cell 16 13");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public Student getNewStudent() {
		return newStudent;
	}
	

}
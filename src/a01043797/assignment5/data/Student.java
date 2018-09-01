package a01043797.assignment5.data;

public class Student {
	private String firstName;
	private String lastName;
	private String studentID;
	private double quizzes;
	private double assignments;
	private double exams;
	

	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String firstName, String lastName, String studentID, Double quizzes, Double assignments, Double exams) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
		this.quizzes = quizzes;
		this.assignments = assignments;
		this.exams = exams;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName != null && firstName.trim().length() > 0) {
			this.firstName = firstName;
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName != null && lastName.trim().length() > 0) {
			this.lastName = lastName;
		}
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		if (studentID != null && studentID.trim().length() > 0) {
			this.studentID = studentID;
		}
	}
	
	public double getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(double quizzes) {
		this.quizzes = quizzes;
	}

	public double getAssignments() {
		return assignments;
	}

	public void setAssignments(double assignments) {
		this.assignments = assignments;
	}

	public double getExams() {
		return exams;
	}

	public void setExams(double exams) {
		this.exams = exams;
	}

	
	@Override
	public String toString() {
		return this.firstName+ " " + this.lastName + ", " +this.studentID 
				+ " - Quizzes: " + this.quizzes
				+ ", Assignments: " + this.assignments
				+ ", Exams: " + this.exams;	
	}
}

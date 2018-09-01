package a01043797.assignment5.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import a01043797.assignment5.data.Student;


public class IOStream {

	public static ArrayList<Student> reader(String fileName) {
		File file = new File(fileName);
		ArrayList<Student> students = new ArrayList<Student>();
		Scanner input;
		try {
			input = new Scanner(file);
			int i=0;
			
			while (input.hasNext()) {
				String infoLine = input.nextLine();
				String[] info = infoLine.split("\\|");
				Double quizzes =Double.valueOf(info[3]);
				Double assignments =Double.valueOf(info[4]);
				Double exams =Double.valueOf(info[5]);
				Student student = new Student (info[0], info[1], info[2], quizzes, assignments, exams);
				students.add(student);
				i++;
			}

			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public static void writer(String fileName, ArrayList<Student> students) throws Exception {
		File file = new File(fileName);
		if (file.exists()) {
			try(PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true))))
			{
				for (int i=0; i<students.size(); i++) 
				{
					Student s = students.get(i);
					output.println(s);
				}
				output.println();
				output.close();
			} catch (IOException e) {
				System.out.println("Writing to output.txt failed.");
				System.exit(0);
			}
		}
		else {
			PrintWriter output = new PrintWriter(file);

			for (int i=0; i<students.size(); i++) 
			{
				Student s = students.get(i);
				output.println(s);
			}
			output.println();
			output.close();
		}
	}
}
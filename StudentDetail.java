
import java.util.Scanner;
public class StudentDetail {
	String [] names;
	char [] grades;
	double [][] marks;
	StudentDetail(){
		names = new String[5];
		grades = new char[5];
		marks = new double[5][4];
	}
	String getName(int index) {
		return names[index];
	}
	double getAvgScores(int index) {
		double avg = 0;
		for(int i = 0; i < 4; i++) {
			avg += marks[index][i];
	}
		avg = avg/4;
		return avg;
	}
	char getGrade(int index) {
		double avg = getAvgScores(index);
		if(90 <= avg && avg <= 100)
			return grades[index] = 'A';
		else if(80 <= avg && avg <= 89)
			return grades[index] = 'B';
		else if(70 <= avg && avg <= 79)
			return grades[index] = 'C';
		else if(60 <= avg && avg <= 69)
			return grades[index] = 'D';
		return grades[index] = 'F';
	}
}
public class StudentDetailTest {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		StudentDetail stDetail = new StudentDetail();
		for(int i = 0; i < 5; i++) {
			System.out.print("Enter name of student "+ (i+1) +" : ");
			stDetail.names[i] = sc.nextLine();
			for(int sub = 0; sub < 4; sub++) {
				boolean done = false;
				while(done != true) {
					try {
					System.out.println("Enter marks in subject " + (sub+1) + " : ");
					double currMarks = stDetail.marks[i][sub] = sc.nextDouble();
					if(currMarks < 0 || currMarks > 100)
						throw new Exception("Marks not in range [1,100]");
					done = true;
					}
					catch(Exception ex) {
						System.out.println(ex);
						System.out.println("Enter marks between 1 and 100");
					}
				}
			}
			String extra = sc.nextLine();
		}
		for(int i = 0; i < 5; i++) {
			System.out.println("Student "+(i+1)+" : ");
			System.out.println("Average test score : " + stDetail.getAvgScores(i));
			System.out.println("Letter grade : " + stDetail.getGrade(i));
			System.out.println("");
		}
		sc.close();
	}
}

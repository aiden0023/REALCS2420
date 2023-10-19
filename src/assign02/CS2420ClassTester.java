package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * This class contains tests for CS2420Class.
 * 
 * @author Aaron Wood and Aiden Fornalski and Henry Sippel
 * @version 2023-08-31 
 */
public class CS2420ClassTester {

	private CS2420Class emptyClass, verySmallClass, smallClass, largeClass;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyClass = new CS2420Class();
		
		verySmallClass = new CS2420Class();
		verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Drew", "Hall", 2323232, new EmailAddress("howdy", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Riley", "Nguyen", 4545454, new EmailAddress("hello", "gmail.com")));

		smallClass = new CS2420Class();
		smallClass.addAll("src/assign02/a_small_2420_class.txt");

		largeClass = new CS2420Class();
		largeClass.addStudent(new CS2420Student("Jane", "Smith", 1010101, new EmailAddress("large", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Jess", "Smith", 1010102, new EmailAddress("larger", "gmail.com")));
		largeClass.addStudent(new CS2420Student("John", "Lecy", 1010134, new EmailAddress("Lecy", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Bob", "McNewman", 1010235, new EmailAddress("bobMcNewman", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Kai", "Yeller", 1045101, new EmailAddress("gamerboi", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Andrew", "Craig", 1016701, new EmailAddress("free", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Adam", "Griffin", 1010801, new EmailAddress("wifi", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Kieran", "Albers", 1076101, new EmailAddress("bird", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Josie", "Laffrey", 1090101, new EmailAddress("wave", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Millie", "Galvon", 1010901, new EmailAddress("canam", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Jan", "Lee", 1010190, new EmailAddress("Cookie", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Toby", "Ugory", 9010101, new EmailAddress("pod", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Leaf", "Onion", 1057101, new EmailAddress("shoes", "gmail.com")));
		largeClass.addStudent(new CS2420Student("River", "Baker", 1270101, new EmailAddress("telly", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Kira", "Cook", 1011701, new EmailAddress("house", "gmail.com")));
		largeClass.addStudent(new CS2420Student("Jules", "Keipane", 2514101, new EmailAddress("sofa", "gmail.com")));


		// FILL IN -- Extend this tester to add more tests for the CS 2420 classes above, as well as to
		// create and test larger CS 2420 classes.
		// (HINT: For larger CS 2420 classes, generate random names, uNIDs, contact info, and scores in a 
		// loop, instead of typing one at a time.)
	}
	
	// Empty CS 2420 class tests --------------------------------------------------------------------------

	@Test
	public void testEmptyLookupUNID() {
		assertNull(emptyClass.lookup(1234567));
	}
	
	@Test
	public void testEmptyLookupContactInfo() {
		ArrayList<CS2420Student> students = emptyClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(0, students.size());
	}
	
	@Test
	public void testEmptyAddScore() {
		// ensure no exceptions thrown
		emptyClass.addScore(1234567, 100, "assignment");
	}

	@Test
	public void testEmptyClassAverage() {
		assertEquals(0, emptyClass.computeClassAverage(), 0);
	}
	
	@Test
	public void testEmptyContactList() {
		ArrayList<EmailAddress> contactList = emptyClass.getContactList();
		assertEquals(0, contactList.size());
	}

	// Very small CS 2420 class tests --------------------------------------------------------------------

	@Test
	public void testVerySmallLookupUNID() {
		UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
		CS2420Student actual = verySmallClass.lookup(2323232);
		assertEquals(expected, actual);
	}


	@Test
	public void testVerySmallLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
		ArrayList<CS2420Student> actualStudents = verySmallClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}
	
	@Test
	public void testVerySmallAddDuplicateStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, 
				new EmailAddress("hi", "gmail.com")));
		assertFalse(actual);
	}
	
	@Test
	public void testVerySmallAddNewStudent() {
		boolean actual = verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010100, 
				new EmailAddress("hi", "gmail.com")));
		assertTrue(actual);		
	}

	@Test
	public void testVerySmallStudentFinalScore0() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}
	
	@Test
	public void testVerySmallStudentFinalGradeNA() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		assertEquals("N/A", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentFinalScore() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(55, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals(55, student.computeFinalScore(), 0.001);
	}
	
	@Test
	public void testVerySmallStudentFinalGrade() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals("B", student.computeFinalGrade());
	}
	
	@Test
	public void testVerySmallStudentComputeScoreTwice() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		student.computeFinalScore();   
		student.addScore(70, "lab");
		student.addScore(54.5, "exam");				
		assertEquals(64.75, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallUpdateName() {
		verySmallClass.lookup(1010101).updateName("John", "Doe");
		ArrayList<CS2420Student> students = verySmallClass.lookup(new EmailAddress("hi", "gmail.com"));
		assertEquals("John", students.get(0).getFirstName());
		assertEquals("Doe", students.get(0).getLastName());
	}	
	
	// Small CS 2420 class tests -------------------------------------------------------------------------

	@Test
	public void testSmallLookupContactInfo() {
		UofUStudent expectedStudent1 = new UofUStudent("Kennedy", "Miller", 888888);
		UofUStudent expectedStudent2 = new UofUStudent("Taylor", "Miller", 999999);

		ArrayList<CS2420Student> actualStudents = smallClass.lookup(new EmailAddress("we_love_puppies", "geemail.com"));
		assertEquals(2, actualStudents.size());
		assertTrue(actualStudents.contains(expectedStudent1));
		assertTrue(actualStudents.contains(expectedStudent2));
	}
	
	@Test
	public void testSmallGetContactList() {
		ArrayList<EmailAddress> actual = smallClass.getContactList();
		assertEquals(9, actual.size());
	}

	@Test
	public void testSmallStudentFinalScore() {
		CS2420Student student = smallClass.lookup(333333);
		assertEquals(95.6027, student.computeFinalScore(), 0.001);
	}
		
	@Test
	public void testSmallComputeClassAverage() {
		assertEquals(78.3191, smallClass.computeClassAverage(), 0.001);
	}

	// Large CS 2420 class tests --------------------------------------------------------------------------

	@Test
	public void testLargeLookupUNID() {
		UofUStudent expected = new UofUStudent("Andrew", "Craig", 1016701);
		CS2420Student actual = largeClass.lookup(1016701);
		assertEquals(expected, actual);
	}

	@Test
	public void testLargeLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Jane", "Smith", 1010101);
		ArrayList<CS2420Student> actualStudents = largeClass.lookup(new EmailAddress("large", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}

	@Test
	public void testLargeAddDuplicateStudent() {
		boolean actual = largeClass.addStudent(new CS2420Student("Jan", "Lee", 1010190,
				new EmailAddress("Cookie", "gmail.com")));
		assertFalse(actual);
	}

	@Test
	public void testLargeAddNewStudent() {
		boolean actual = largeClass.addStudent(new CS2420Student("Kieran", "Jones", 1076123,
				new EmailAddress("dog", "gmail.com")));
		assertTrue(actual);
	}

	@Test
	public void testLargeStudentFinalScore0() {
		CS2420Student student = largeClass.lookup(1010101);
		student.addScore(87.5, "assignment");
		student.addScore(73, "exam");
		student.addScore(90, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}

	@Test
	public void testLargeStudentFinalGradeNA() {
		CS2420Student student = largeClass.lookup(1010190);
		student.addScore(100, "assignment");
		student.addScore(73, "exam");
		student.addScore(57, "lab");
		assertEquals("N/A", student.computeFinalGrade());
	}

	@Test
	public void testLargeStudentFinalScore() {
		CS2420Student student = largeClass.lookup(2514101);
		student.addScore(57, "assignment");
		student.addScore(80, "exam");
		student.addScore(90, "lab");
		student.addScore(88.9, "quiz");
		student.addScore(87, "assignment");
		student.addScore(85, "lab");
		student.addScore(77.7, "quiz");
		assertEquals(78.28, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testLargeStudentFinalGrade() {
		CS2420Student student = largeClass.lookup(1045101);
		student.addScore(86.5, "assignment");
		student.addScore(70, "exam");
		student.addScore(50, "lab");
		student.addScore(50, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(95, "quiz");
		assertEquals("C+", student.computeFinalGrade());
	}
	@Test
	public void testLargeStudentComputeScoreTwice() {
		CS2420Student student = largeClass.lookup(1010190);
		student.addScore(86.5, "assignment");
		student.addScore(70, "exam");
		student.addScore(85, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(85, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		student.computeFinalScore();
		student.addScore(70, "lab");
		student.addScore(54.5, "exam");
		assertEquals(62.25, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testLargeUpdateName() {
		largeClass.lookup(1270101).updateName("River", "Baker");
		ArrayList<CS2420Student> students = largeClass.lookup(new EmailAddress("telly", "gmail.com"));
		assertEquals("River", students.get(0).getFirstName());
		assertEquals("Baker", students.get(0).getLastName());
	}



	// Extra CS 2420 class tests --------------------------------------------------------------------------

	@Test
	public void nonExistentCategoryForScore() {
		//ensure no exceptions are thrown
		verySmallClass.addScore(1010101, 100.0, "foo");
	}

	@Test
	public void examScoreBelow65() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(100.0, "assignment");
		student.addScore(50.0, "exam");
		student.addScore(90.0, "lab");
		student.addScore(95.0, "quiz");
		student.computeFinalScore();
		assertEquals(50.0, student.computeFinalScore(), 0.001);
	}
}



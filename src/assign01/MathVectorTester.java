package assign01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class assesses the correctness of the Vector class.
 *
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true).
 *
 * @author Aaron Wood and Aiden Fornalski
 * @version 2023-08-23
 */
public class MathVectorTester {

	private MathVector rowVec, rowVecTranspose, colVecTranspose, rowUnitVec, rowSumVec, colVec, longRowVec, longColVec, longRowVecTranspose, longColVecTranspose, longRowUnitVec, colUnitVec, longColUnitVec, longRowSumVec, colSumVec, longColSumVec;

	@BeforeEach
	public void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][]{{3, 1, 2}});

		// Creates a column vector with three elements: 3.0, 1.0, 2.0
		rowVecTranspose = new MathVector(new double[][]{{3}, {1}, {2}});

		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		rowUnitVec = new MathVector(new double[][]{{1, 1, 1}});

		// Creates a row vector with three elements: 4.0, 2.0, 3.0
		rowSumVec = new MathVector(new double[][]{{4, 2, 3}});

		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}});

		//creates a col vector with five elements
		colUnitVec = new MathVector(new double[][]{{12}, {17}, {2}, {4.4}, {5}});

		//creates a row vector with five elements (the sum of colVec and colUnitVec)
		colSumVec = new MathVector(new double[][]{{1}, {19.5}, {38}, {1.2600000000000002}, {12.1}});

		//creates a transposed vector of colVec
		colVecTranspose = new MathVector(new double[][]{{-11, 2.5, 36, -3.14, 7.1}});

		//creates a row vector with eighteen elements
		longRowVec = new MathVector(new double[][]{{3, 1, 2, 5, 7, 32, 56, 12, 49, 123, 67, 35, 94, 81, 90, 46, 71, 55}});

		//creates a row vector with eighteen elements
		longRowUnitVec = new MathVector(new double[][]{{4, 3, 5, 1, 30, 45, 2, 17, 25, 10, 93, 4.5, 3.2, 14, 72, 83, 81, 18}});

		//creates a row vector with eighteen elements (the sum of longRowVec and longRowUnitVec)
		longRowSumVec = new MathVector(new double[][]{{7, 4, 7, 6, 37, 77, 58, 29, 74, 133, 160, 39.5, 97.2, 95, 162, 129, 152, 73}});

		//creates a col vector with fourteen elements
		longColVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}, {8}, {72}, {89}, {2}, {9.8}, {43.4}, {99}, {27.3}, {67}});

		//creates a col vector with fourteen elements
		longColUnitVec = new MathVector(new double[][]{{2}, {3}, {5}, {4}, {17}, {83}, {8.8}, {67}, {7}, {10}, {11}, {12}, {17}, {27}});

		//creates a col vector with fourteen elements (the sum of longColVec and longColUnitVec)
		longColSumVec = new MathVector(new double[][]{{-9.0}, {5.5}, {41.0}, {0.8599999999999999}, {24.1}, {91.0}, {80.8}, {156.0}, {9.0}, {19.8}, {54.4}, {111.0}, {44.3}, {94.0}});

		//creates a transposed vector of longColVec
		longColVecTranspose = new MathVector(new double[][]{{-11, 2.5, 36, -3.14, 7.1, 8, 72, 89, 2, 9.8, 43.4, 99, 27.3, 67}});

		//creates a transposed vector of longRowVec
		longRowVecTranspose = new MathVector(new double[][]{{3}, {1}, {2}, {5}, {7}, {32}, {56}, {12}, {49}, {123}, {67}, {35}, {94}, {81}, {90}, {46}, {71}, {55}});
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void smallRowVectorEquality() {
		assertTrue(rowVec.equals(new MathVector(new double[][]{{3, 1, 2}})));
	}

	@Test
	public void longRowVectorEquality() {
		assertTrue(longRowVec.equals(new MathVector(new double[][]{{3, 1, 2, 5, 7, 32, 56, 12, 49, 123, 67, 35, 94, 81, 90, 46, 71, 55}})));
	}

	@Test
	public void smallColVectorEquality() {
		assertTrue(colVec.equals(new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}})));
	}

	@Test
	public void longColVectorEquality() {
		assertTrue(longColVec.equals(new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}, {8}, {72}, {89}, {2}, {9.8}, {43.4}, {99}, {27.3}, {67}})));
	}

	@Test
	public void smallRowVectorInequality() {
		assertFalse(rowVec.equals(rowUnitVec));
	}

	@Test
	public void longRowVectorInequality() {
		assertFalse(longRowVec.equals(rowUnitVec));
	}

	@Test
	public void smallColVectorInequality() {
		assertFalse(colVec.equals(rowUnitVec));
	}

	@Test
	public void longColVectorInequality() {
		assertFalse(longColVec.equals(rowUnitVec));
	}

	@Test
	public void createVectorFromBadArray() {
	  double arr[][] = {{1, 2}, {3, 4}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}

	@Test
	public void transposeSmallRowVector() {
		System.out.println();
		MathVector transposeResult = rowVec.transpose();
		assertTrue(transposeResult.equals(rowVecTranspose));
	}

	@Test
	public void transposeLongRowVector() {
		System.out.println();
		MathVector transposeResult = longRowVec.transpose();
		assertTrue(transposeResult.equals(longRowVecTranspose));
	}

	@Test
	public void transposeSmallColVector() {
		System.out.println();
		MathVector transposeResult = colVec.transpose();
		assertTrue(transposeResult.equals(colVecTranspose));
	}

	@Test
	public void transposeLongColVector() {
		System.out.println();
		MathVector transposeResult = longColVec.transpose();
		assertTrue(transposeResult.equals(longColVecTranspose));
	}

	@Test
	public void addRowAndColVectors() {
	  assertThrows(IllegalArgumentException.class, () -> { rowVec.add(colVec); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}

	@Test
	public void addSmallRowVectors() {
		MathVector addResult = rowVec.add(rowUnitVec);
		assertTrue(addResult.equals(rowSumVec));
	}

	@Test
	public void addLongRowVectors() {
		MathVector addResult = longRowVec.add(longRowUnitVec);
		assertTrue(addResult.equals(longRowSumVec));
	}

	@Test
	public void addSmallColVectors() {
		MathVector addResult = colVec.add(colUnitVec);
		assertTrue(addResult.equals(colSumVec));
	}

	@Test
	public void addLongColVectors() {
		MathVector addResult = longColVec.add(longColUnitVec);
		assertTrue(addResult.equals(longColSumVec));
	}

	@Test
	public void dotProductSmallRowVectors() {
		double dotProdResult = rowVec.dotProduct(rowUnitVec);
		assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);
	}

	@Test
	public void dotProductLongRowVectors() {
		double dotProdResult = longRowVec.dotProduct(longRowUnitVec);
		assertEquals(dotProdResult, 3 * 4 + 1 * 3 + 2 * 5 + 5 * 1 + 7 * 30 + 32 * 45 + 56 * 2 + 12 * 17 + 49 * 25 + 123 * 10 + 67 * 93 + 35 * 4.5 + 94 * 3.2 + 81 * 14 + 90 * 72 + 46 * 83 + 71 * 81 + 55 * 18);
	}

	@Test
	public void dotProductSmallColVectors() {
		double dotProdResult = colVec.dotProduct(colUnitVec);
		assertEquals(dotProdResult, -11 * 12 + 2.5 * 17 + 36 * 2 + (-3.14 * 4.4) + 7.1 * 5);
	}

	@Test
	public void dotProductLongColVectors() {
		double dotProdResult = longColVec.dotProduct(longColUnitVec);
		assertEquals(dotProdResult, -11 * 2 + 2.5 * 3 + 36 * 5 + (-3.14 * 4) + 7.1 * 17 + 8 * 83 + 72 * 8.8 + 89 * 67 + 2 * 7 + 9.8 * 10 + 43.4 * 11 + 99 * 12 + 27.3 * 17 + 67 * 27);
	}

	@Test
	public void smallRowVectorLength() {
		double vecLength = rowVec.magnitude();
		assertEquals(vecLength, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));
	}

	@Test
	public void longRowVectorLength() {
		double vecLength = longRowVec.magnitude();
		assertEquals(vecLength, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0 + 5.0 * 5.0 + 7.0 * 7.0 + 32.0 * 32.0 + 56.0 * 56.0 + 12.0 * 12.0 + 49.0 * 49.0 + 123.0 * 123.0 + 67.0 * 67.0 + 35.0 * 35.0 + 94.0 * 94.0 + 81.0 * 81.0 + 90.0 * 90.0 + 46.0 * 46.0 + 71.0 * 71.0 + 55.0 * 55.0));
	}

	@Test
	public void smallColVectorLength() {
		double vecLength = colVec.magnitude();
		assertEquals(vecLength, Math.sqrt(-11.0 * -11.0 + 2.5 * 2.5 + 36.0 * 36.0 + (-3.14) * (-3.14) + 7.1 * 7.1));
	}

	@Test
	public void longColVectorLength() {
		double vecLength = longColVec.magnitude();
		assertEquals(vecLength, Math.sqrt(-11.0 * -11.0 + 2.5 * 2.5 + 36.0 * 36.0 + (-3.14) * (-3.14) + 7.1 * 7.1 + 8.0 * 8.0 + 72.0 * 72.0 + 89.0 * 89.0 + 2.0 * 2.0 + 9.8 * 9.8 + 43.4 * 43.4 + 99.0 * 99.0 + 27.3 * 27.3 + 67.0 * 67.0));
	}

	@Test
	public void smallRowVectorNormalize() {
		MathVector normalVec = rowVec.normalize();
		double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{3.0 / length, 1.0 / length, 2.0 / length}})));
	}

	@Test
	public void longRowVectorNormalize() {
		MathVector normalVec = longRowVec.normalize();
		double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0 + 5.0 * 5.0 + 7.0 * 7.0 + 32.0 * 32.0 + 56.0 * 56.0 + 12.0 * 12.0 + 49.0 * 49.0 + 123.0 * 123.0 + 67.0 * 67.0 + 35.0 * 35.0 + 94.0 * 94.0 + 81.0 * 81.0 + 90.0 * 90.0 + 46.0 * 46.0 + 71.0 * 71.0 + 55.0 * 55.0);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{3.0/length, 1.0/length, 2.0/length, 5.0/length, 7.0/length, 32.0/length, 56.0/length, 12.0/length, 49.0/length, 123.0/length, 67.0/length, 35.0/length, 94.0/length, 81.0/length, 90.0/length, 46.0/length, 71.0/length, 55.0/length}})));
	}

	@Test
	public void smallColVectorNormalize() {
		MathVector normalVec = colVec.normalize();
		double length = Math.sqrt(-11.0 * -11.0 + 2.5 * 2.5 + 36.0 * 36.0 + (-3.14) * (-3.14) + 7.1 * 7.1);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{-11 / length}, {2.5 / length}, {36.0 / length}, {-3.14 / length}, {7.1 / length}})));
	}

	@Test
	public void longColVectorNormalize() {
		MathVector normalVec = longColVec.normalize();
		double length = Math.sqrt(-11.0 * -11.0 + 2.5 * 2.5 + 36.0 * 36.0 + (-3.14) * (-3.14) + 7.1 * 7.1 + 8.0 * 8.0 + 72.0 * 72.0 + 89.0 * 89.0 + 2.0 * 2.0 + 9.8 * 9.8 + 43.4 * 43.4 + 99.0 * 99.0 + 27.3 * 27.3 + 67.0 * 67.0);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{-11/length}, {2.5/length}, {36.0/length}, {-3.14/length}, {7.1/length}, {8.0/length}, {72.0/length}, {89.0/length}, {2.0/length}, {9.8/length}, {43.4/length}, {99.0/length}, {27.3/length}, {67.0/length}})));
	}

	@Test
	public void smallColVectorToString() {
		String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1";
		assertEquals(resultStr, colVec.toString());
	}

	@Test
	public void smallRowVectorToString() {
		String resultStr = "3.0 1.0 2.0";
		assertEquals(resultStr, rowVec.toString());
	}

	@Test
	public void longRowVectorToString() {
		String resultStr = "3.0 1.0 2.0 5.0 7.0 32.0 56.0 12.0 49.0 123.0 67.0 35.0 94.0 81.0 90.0 46.0 71.0 55.0";
		assertEquals(resultStr, longRowVec.toString());
	}

	@Test
	public void longColVectorToString() {
		String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1\n8.0\n72.0\n89.0\n2.0\n9.8\n43.4\n99.0\n27.3\n67.0";
		assertEquals(resultStr, longColVec.toString());
	}
}

package assign01;

/**
 * This class represents a simple row or column vector of numbers.
 * In a row vector, the numbers are written horizontally (eg, `{{1, 2, 3, 4}}`).
 * In a column vector, the numbers are written vertically (eg, `{{1}, {2}, {3}, {4}}`).
 *
 * @author Aaron Wood and Aiden Fornalski
 * @version 2023-08-23
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data;
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;

	/**
	 * Creates a new row or column vector.
	 * For a row vector, the input array is expected to have 1 row and a positive number of columns,
	 * and this number of columns represents the vector's length.
	 * For a column vector, the input array is expected to have 1 column and a positive number of rows,
	 * and this number of rows represents the vector's length.
	 *
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the input 2D array is not
	 *         compatible with a row or column vector
	 */
	public MathVector(double[][] data) {
		if(data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if(data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");

		if(data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		}
		else if(data[0].length == 1) {
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		}
		else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");

		// Create the array and copy data over.
		if(this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for(int i=0; i < this.data.length; i++) {
			for(int j=0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	/**
	 * Determines whether this vector is "equal to" another vector, where equality is
	 * defined as both vectors being row (or both being column), having the same
	 * vector length, and containing the same numbers in the same positions.
	 *
	 * @param other - another vector to compare
	 */
	public boolean equals(Object other) {
		if(!(other instanceof MathVector))
			return false;

		MathVector otherVec = (MathVector)other;
		boolean equals = false;

		if (this.isRowVector == otherVec.isRowVector && this.vectorSize == otherVec.vectorSize) { //vector size check
			if (!this.isRowVector) { //col vector
				for (int i = 0; i < this.data.length; i++) {
					if (this.data[i][0] == otherVec.data[i][0]) {
						equals = true;
					} else {
						equals = false;
						break;
					}
				}
			} else { //row vector
				for (int i = 0; i < this.vectorSize; i++) {
					if (this.data[0][i] == otherVec.data[0][i]) {
						equals = true;
					} else {
						equals = false;
						break;
					}
				}
			}
		}

		return equals;
	}

	/**
	 * Generates a returns a new vector that is the transposed version of this vector.
	 */
	public MathVector transpose() {
		double[][] data;
		if (this.isRowVector) { //checking to see if vector is row or col
			data = new double[this.vectorSize][1];
			for(int i=0; i < this.vectorSize; i++) {
				data[i][0] = this.data[0][i];
			}
		} else {
			data = new double[1][this.vectorSize];
			for(int i=0; i < this.vectorSize; i++) {
				data[0][i] = this.data[i][0];
			}
		}

		return new MathVector(data);
	}

	/**
	 * Generates and returns a new vector representing the sum of this vector and another vector.
	 *
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */
	public MathVector add(MathVector other) {
		MathVector newVector;
		if (!(other.isRowVector == this.isRowVector)) { //checking for exception
			throw new IllegalArgumentException();
		}

		if (this.isRowVector) { //checking to see if vector is row or col
			newVector = new MathVector(new double[1][this.vectorSize]);
		} else {
			newVector = new MathVector(new double[this.vectorSize][1]);
		}

		for(int i=0; i < this.data.length; i++) { //loop for adding each value of the vector
			for (int j = 0; j < this.data[0].length; j++) {
				newVector.data[i][j] = this.data[i][j] + other.data[i][j];
			}
		}

		return newVector;
	}

	/**
	 * Computes and returns the dot product of this vector and another vector.
	 *
	 * @param other - another vector to be combined with this vector to produce the dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */
	public double dotProduct(MathVector other) {
		double[] temp = new double[this.vectorSize];
		double sum = 0;
		if (!(other.isRowVector == this.isRowVector)) { //checking for exception
			throw new IllegalArgumentException();
		}

		//converting vectors' data to double[]
		double[] thisData = this.doubleArrToSingleArr();
		double[] otherData = other.doubleArrToSingleArr();

		for(int i = 0; i < this.vectorSize; i++){ //multiplying values of each vector together
			temp[i] = thisData[i] * otherData[i];
		}

		for (double value : temp) { //looping to add each value of temp array together
			sum += value;
		}

		return sum;
	}

	/**
	 * Computes and returns this vector's magnitude (also known as a vector's length) .
	 */
	public double magnitude() {
		double length = 0;
		double[] thisData = this.doubleArrToSingleArr(); //converting vector data to double[]
		for (int i = 0; i < thisData.length; i++) { //looping to compute each value of the vector to the second power
			length += Math.pow(thisData[i], 2);
		}
		length = Math.sqrt(length); //square rooting above loop result to find the final length of the vector

		return length;
	}

	/**
	 * Generates and returns a normalized version of this vector.
	 */
	public MathVector normalize() {
		double[][] tempArr = this.data;
		double length = this.magnitude(); //computing length of the vector
		if (this.isRowVector) { //checking to see if vector is row or col
			for (int i = 0; i < this.vectorSize; i++) { //loop to compute each value of the vector divided by the length
				tempArr[0][i] = tempArr[0][i] / length;
			}
		} else {
			for (int i = 0; i < this.vectorSize; i++) { //loop to compute each value of the vector divided by the length
				tempArr[i][0] = tempArr[i][0] / length;
			}
		}

		return new MathVector(tempArr);
	}

	/**
	 * Generates and returns a textual representation of this vector.
	 * For example, "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and
	 * "1.0
	 *  2.0
	 *  3.0
	 *  4.0
	 *  5.0" for a sample column vector of length 5.
	 *  In both cases, notice the lack of a newline or space after the last number.
	 */
	public String toString() {
		String output = "";
		if (this.isRowVector) { //checking to see if vector is row or col
			for(int i=0; i < this.vectorSize-1; i++) { //loop to create String output
				output+= this.data[0][i] + " ";
			}
			output += this.data[0][this.vectorSize-1];
		} else {
			for(int i=0; i < this.vectorSize-1; i++) { //loop to create String output
				output+= this.data[i][0] + "\n";
			}
			output += this.data[this.vectorSize-1][0];
		}

		return output;
	}

	/**
 	* Helper method for several class methods; converts the data of a vector to a double[].
 	*/
	private double[] doubleArrToSingleArr () {
		double[] singleArr;
		if(this.isRowVector) { //checking to see if vector is row or col
			singleArr = this.data[0]; //converting row vector to double[]
		}
		else {
			singleArr = new double[this.vectorSize];
			for(int i = 0; i < this.vectorSize; i++){ //loop to convert col vector to double[]
				singleArr[i] = this.data[i][0];
			}
		}
		return singleArr;
	}
}

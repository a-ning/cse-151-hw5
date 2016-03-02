/* ----------------------------------------------------------------------------
 * Alicia Ning A10796746
 * Gabriel Gaddi A10851046
 * CSE 151 - Chaudhuri
 * 03 March 2016
 * --------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class hw5 {

	/* function to read files */
	public static LinkedList<String[]> read (File file) {
		LinkedList<String[]> res = new LinkedList<String[]>();
		String line = null;

		try {
			FileReader fReader = new FileReader (file);
			BufferedReader bReader = new BufferedReader (fReader);

			/* while there are still lines to read */
			while ((line = bReader.readLine()) != null) {
				/* split each line by spaces */
				String[] vals = line.split ("  ");

				/* add to LL */
				res.add (vals);
			}

			/* close file */
			bReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println ("Failed to open file");
		} catch (IOException ex) {
			System.out.println ("Error reading file");
		}

		return res;
	}

	public static void kPerceptron (LinkedList<String[]> data, int strlen) {
		Iterator<String[]> it = data.iterator();
		double product;
		String sequence;
		int label;

		while (it.hasNext()) {
			String[] curr = it.next();

			product = 0.0;
			sequence = curr[0];
			label = Integer.parseInt (curr[1]);

			for (int i = 0; i < sequence.length(); i++) {

			}
		}
	}

	/* main function */
	public static void main (String[] args) {
		LinkedList<String[]> trainData, testData;
		File trainFile, testFile;

		trainFile = new File ("hw5train.txt");
		testFile = new File ("hw5test.txt");

		trainData = read (trainFile);
		testData = read (testFile);
	}
}
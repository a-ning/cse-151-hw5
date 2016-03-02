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

	/* function to calculate dot product */
	public static int dot (int[] a, int[] b) {
		int res = 0;

		for (int i = 0; i < a.length; i++) {
			res += a[i] * b[i];
		}

		return res;
	}

	/* function to correct w if a mistake was made */
	public static int[] adjust (int[] w, int y, int[] x) {
		for (int i = 0; i < x.length; i++) {
			w[i] += y * x[i];
		}

		return w;
	}

	public static void kPerceptron (LinkedList<String[]> data, int p) {
		/* size of w according to p; alphabet size is 20 */
		int[] w = new int[(int)Math.pow(20, p)];

		/* initialize w to 0 */
		for (int i = 0; i < w.length; i++) {
			w[i] = 0;
		}

		/* sequences and label to pull from data */
		String sSequence, tSequence;
		int label;

		Iterator<String[]> it = data.iterator();

		String[] curr = it.next();
		sSequence = curr[0];
		
		while (it.hasNext()) {
			curr = it.next();

			tSequence = curr[0];
			label = Integer.parseInt (curr[1]);

			int[] res = kernel (sSequence, tSequence, p);

			if (label * dot (w, res) <= 0) {
				w = adjust (w, label, res);
			}
		}
	}

	public static int[] kernel (String s, String t, int p) {
		return new int[1];
	}

	/* main function */
	public static void main (String[] args) {
		LinkedList<String[]> trainData, testData;
		File trainFile, testFile;

		trainFile = new File ("hw5train.txt");
		testFile = new File ("hw5test.txt");

		trainData = read (trainFile);
		testData = read (testFile);

		kPerceptron (trainData, 3);
		kPerceptron (trainData, 4);
	}
}
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

	/* function to find substrings */
	public static ArrayList<String> findSubstrings (String string, int p) {
		ArrayList<String> res = new ArrayList<String>();

		int j = p;

		for (int i = 0; j <= string.length(); i++, j++) {
			String sub = string.substring (i, j);
			res.add (sub);
		}

		return res;
	}

	/* string kernel */
	public static int kernel (String s, String t, int p) {
		/* find substrings of s and t */
		ArrayList<String> subS = findSubstrings (s, p);
		ArrayList<String> subT = findSubstrings (t, p);

		/* find # common substrings btwn s and t */
		ArrayList<String> common = new ArrayList<String> (subS);
		common.retainAll (subT);
		Set<String> commonUnique = new HashSet<String> (common);

		return commonUnique.size();
	}

	/* kernel perceptron */
	public static void kPerceptron (LinkedList<String[]> trainData, 
	LinkedList<String[]> testData, int p) {
		/* size of w according to p; alphabet size is 20 */
		int[] w = new int[(int)Math.pow(20, p)];

		/* initialize w to 0 */
		Arrays.fill (w, 0);

		/* sequences and label to pull from data */
		String sSequence, tSequence;
		int label;
		Vector<String> seqs = new Vector<String>();
		Vector<Integer> labels = new Vector<Integer>();

		Iterator<String[]> it = trainData.iterator();
		
		String[] curr = it.next();

		seqs.add (curr[0]);
		labels.add (Integer.parseInt (curr[1]));

		double res;
		
		while (it.hasNext()) {
			curr = it.next();

			/* get current sequence and label */
			tSequence = curr[0];
			label = Integer.parseInt (curr[1]);

			/* variable to hold the summation */
			res = 0.0;

			/* y_i1 * K(x_i1, x) + ... + y_ik * K (x_ik, x) 
			 * x_i1..x_ik 	= previously mislabeled sequences,
			 * y_i1..y_ik 	= their labels,
			 * x 			= current sequence */
			for (int i = 0; i < seqs.size(); i++) {
				sSequence = seqs.elementAt (i);
				res += labels.elementAt (i) * kernel (sSequence, tSequence, p);
			}

			/* if predicted wrong */
			if (label * res <= 0) {
				seqs.add (tSequence);
				labels.add (label);
			}

			// tSequence = curr[0];
			// label = Integer.parseInt (curr[1]);

			// res = res + label * kernel (sSequence, tSequence, p);

			// if (label * dot (w, res) <= 0) {
			// 	w = adjust (w, label, res);
			// }
		}

		/* training error */
		float trainError = errs (trainData, seqs, labels, p);
		System.out.println ("\ttraining error = " + trainError);

		/* test error */
		float testError = errs (testData, seqs, labels, p);
		System.out.println ("\ttest error = " + testError + "\n");
	}

	public static float errs (LinkedList<String[]> data, Vector<String> seqs, 
	Vector<Integer> labels, int p) {
		String sSeq, tSeq;
		int label;
		double res;
		int errs = 0;

		Iterator<String[]> it = data.iterator();

		while (it.hasNext()) {
			String[] curr = it.next();
			tSeq = curr[0];
			label = Integer.parseInt (curr[1]);
			res = 0.0;

			for (int i = 0; i < seqs.size(); i++) {
				sSeq = seqs.elementAt (i);
				res += labels.elementAt (i) * kernel (sSeq, tSeq, p);
			}

			if (label * res <= 0) {
				errs++;
			}
		}

		return (float)errs / data.size();
	}

	/* main function */
	public static void main (String[] args) {
		LinkedList<String[]> trainData, testData;
		File trainFile, testFile;

		trainFile = new File ("hw5train.txt");
		testFile = new File ("hw5test.txt");

		trainData = read (trainFile);
		testData = read (testFile);

		System.out.println ("running kernel perceptron with p = 3...\n");
		kPerceptron (trainData, testData, 3);

		System.out.println ("running kernel perceptron with p = 4...\n");
		kPerceptron (trainData, testData, 4);
	}
}
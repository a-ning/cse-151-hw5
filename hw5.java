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
	public static void kPerceptron (LinkedList<String[]> data, int p) {
		/* size of w according to p; alphabet size is 20 */
		int[] w = new int[(int)Math.pow(20, p)];

		/* initialize w to 0 */
		Arrays.fill (w, 0);

		/* sequences and label to pull from data */
		String sSequence, tSequence;
		int label;

		Iterator<String[]> it = data.iterator();

		/* !!!!!!!!!!!!!!!!!!!!!!!!! gabe halp !!!!!!!!!!!!!!!!!!!!!!!!! */

		/* i dont think this is right -- the way it is now, i'm taking
		 * two different sequences from the data and comparing their
		 * substrings. but if i'm thinking about this correctly, shouldn't
		 * i be taking one sequence at a time and comparing its substrings
		 * to the entire alphabet's collection of possible substrings? 
		 *
		 * so right now in K (s, t), s and t come from the data set. 
		 * but should only s come from the data set and t be the alphabet
		 * of 20? pls confirm 
		 *
		 * (and if this is the case, do you remember how to find all the
		 * possible substrings over the alphabet...) */
		
		String[] curr = it.next();
		sSequence = curr[0];
		
		while (it.hasNext()) {
			curr = it.next();

			tSequence = curr[0];
			label = Integer.parseInt (curr[1]);

			int res = kernel (sSequence, tSequence, p);

			// if (label * dot (w, res) <= 0) {
			// 	w = adjust (w, label, res);
			// }
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

		kPerceptron (trainData, 3);
		kPerceptron (trainData, 4);
	}
}
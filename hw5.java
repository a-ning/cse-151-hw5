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
	public static LinkedList<int[]> read (File file) {
		LinkedList<int[]> res = new LinkedList<int[]>();
		String line = null;

		try {
			FileReader fReader = new FileReader (file);
			BufferedReader bReader = new BufferedReader (fReader);

			/* while there are still lines to read */
			while ((line = bReader.readLine()) != null) {
				/* split each line by spaces */
				String[] vals = line.split (" ");
				/* array to store values */
				int[] fVals = new int[vals.length];

				/* store values as integers */
				for (int i = 0; i < fVals.length; i++) {
					fVals[i] = Integer.parseInt (vals[i]);
				}

				/* add to LL */
				res.add (fVals);
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
}

public static void main (String[] args) {
	
}
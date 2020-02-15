
// Yash Navadiya
// CWID 10455328

package Anagrams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author 12016
 *
 */
public class Anagrams {

	final Integer[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
			89, 97, 101 };
	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;

	/**
	 * 
	 */
	public Anagrams() {
		this.buildLetterTable();
		anagramTable = new HashMap<Long, ArrayList<String>>();
	}

	/**
	 * 
	 */
	private void buildLetterTable() {
		Character alphabet[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		letterTable = new HashMap<Character, Integer>();
		for (int i = 0; i < 26; i++) {
			letterTable.put(alphabet[i], primes[i]);
		}
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public Long myHashCode(String s) {
		if (s == "" || s == null) {
			throw new NullPointerException();
		} else {
			Long result = (long) 1;
			for (int j = 0; j < s.length(); j++) {
				Integer tmp = letterTable.get(s.charAt(j));
				result = result * tmp;
			}
			return result;
		}
	}

	/**
	 * 
	 * @param s
	 */
	public void addWord(String s) {
		Long tmp = myHashCode(s);
		ArrayList<String> list = anagramTable.get(tmp);
		if (anagramTable.containsKey(tmp)) {
			list.add(s);
			anagramTable.put(tmp, list);
		} else {
			ArrayList<String> t = new ArrayList<String>();
			t.add(s);
			anagramTable.put(tmp, t);
		}
	}

	/**
	 * 
	 * @param s
	 * @throws IOException
	 */
	private void processFile(String s) throws IOException {
		FileInputStream fStream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}

	/**
	 * 
	 * @return
	 */
	private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {

		ArrayList<Map.Entry<Long, ArrayList<String>>> tmp = new ArrayList<>();
		int max = 0;
		for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
			if (entry.getValue().size() > max) {
				tmp.clear();
				tmp.add(entry);
				max = entry.getValue().size();
			} else if (entry.getValue().size() == max) {
				tmp.add(entry);
			}
		}
		return tmp;

	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime / 1000000000);
		System.out.println(" Time : " + seconds);
		System.out.println(" List of max anagrams : " + maxEntries);
	}

}

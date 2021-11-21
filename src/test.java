import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Set;

public class test {
	static File DicFile = new File("dictionary.txt");
	static Trie trie;
	static Set<String> initialSet = new HashSet<String>();
	static Set<String> finalSet = new HashSet<String>();
	static Set<String> userWords = new HashSet<String>();

	public static void main(String[] args) throws FileNotFoundException {

		Scanner input = new Scanner(System.in);
		System.out.println(
				"Enter a choice\n1) Create an empty trie\n2) Create a trie with initial letters\n3) Insert a word\n4) Delete a word\n5) List all words that begin with a prefix\n6) Size of the trie\n7) end ");

		String choice = input.next();

		while (!choice.equals("7")) {
			if (choice.equals("1")) {
				trie = new Trie();
				System.out.println("Empty trie created");
			
				
			} else if (choice.equals("2")) {
				System.out.println("Enter a word");
				String initialWord = input.next();
				trie = new Trie();
				addToTrie(initialWord);
				System.out.println("Trie initialized with: " + initialWord);
				

			} else if (choice.equals("3")) {
				System.out.println("Enter a word123");
				String word = input.next();
				addToTrie(word);
			

			} else if (choice.equals("4")) {
				System.out.println("Enter a word to remove");
				String wordRemoved = input.next();
				trie.remove(wordRemoved);
			}
			else if (choice.equals("5")) {
				System.out.println("Enter a prefix");
				String prefix = input.next();
				System.out.println("Prefixes found are:");
				System.out.println(trie.display(prefix).toString());

			}
			else if(choice.equals("6")){
				System.out.println("Tree size is: "+trie.size());
				
			}
			choice = input.next();

		}

		// System.out.println(set.toString());
//		System.out.println(finalSet.toString());
//		isInList(finalSet,DicFile,trie);

	}

	public static void isInList(Set<String> set1, File file, Trie trie) throws FileNotFoundException {

		Scanner myReader = new Scanner(file);

		while (myReader.hasNextLine()) {

			String data = myReader.nextLine();

			for (String i : set1) {
				if (i.equals(data)) {
					trie.insert(data);
					

				}

			}

		}
		myReader.close();
	}

	public static void permutation(String str, Set<String> set) {

		permutation("", str, set);

	}

	static int fact(int a) {
		int i, f = 1;

		// Loop to find the factorial
		// of the given number
		for (i = 2; i <= a; i++)
			f = f * i;

		return f;
	}

	private static void permutation(String prefix, String str, Set<String> set) {
		int n = str.length();
		if (n == 0)
			set.add(prefix);
		else {
			for (int i = 0; i < n; i++)
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), set);
		}
	}

	public static Trie createEmptyTree() {
		Trie trie = new Trie();
		return trie;
	}

	public static void addToTrie(String word) throws FileNotFoundException {

		userWords.add(word);
		for (String i : userWords) {
			permutation(i, initialSet);
		}
		for (String i : initialSet) {
			for (int k = 0; k < i.length(); k++) {
				permutation(i.substring(k), finalSet);
			}

		}
		try {
			isInList(finalSet, DicFile, trie);
		} catch (NullPointerException e) {
			System.out.println("No trie is found. Create a new trie.");
		}
		System.out.println(userWords);
	
	}

}
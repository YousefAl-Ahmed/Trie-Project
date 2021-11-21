import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Trie<T extends Comparable<? super T>> {
	private TrieNode<T> root;
	int size = 0;
	public Trie() {
		root = new TrieNode<T>('.');
	}

 void insert(String word) {
		TrieNode<T> tmp = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (tmp.children[ch - 'A'] == null) {
				tmp.children[ch - 'A'] = new TrieNode<T>(ch);
				size++;
			}
			tmp = tmp.children[ch - 'A'];
		}
		tmp.isWord = true;
	}
 
  boolean isEmpty()
 {
     for (int i = 0; i < 26; i++)
         if (root.children[i] != null)
             return false;
     return true;
 }

	private TrieNode<T> getNode(String word) {
		TrieNode<T> tmp = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (tmp.children[ch - 'A'] == null)
				return null;
			tmp = tmp.children[ch - 'A'];
		}
		return tmp;

	}

	public boolean contains(String word) {
		TrieNode<T> node = getNode(word);
		return node != null && node.isWord;
	}

	public boolean isPrefix(String prefix) {
		return getNode(prefix) != null;
	}
	Set display(String prefix) {
		char ch = prefix.charAt(0);
		char[] s = new char[100];
		Set<String> wordsArray = new HashSet<String>();
		return display(root.children[ch-'A'],s,0,wordsArray);
		
		//System.out.println(root.children['P'-'A'].ch);
	}

	Set display(TrieNode tmp,char[] string, int level,Set<String> setWords)
	{
	    // If node is leaf node, it indicates end
	    // of string, so a null character is added
	    // and string is displayed
		String words = "";
		if(tmp ==null) return setWords;
	    if (tmp.isWord) 
	    {
	        //string[level] = '\0';
	      //  System.out.println(Arrays.toString(string));
	        for(char i :(string)) {
	        	words = words+i;
	        }
	    }
	 
	    
	    for (int i = 0; i < 26; i++) 
	    {
	        // if NON NULL child is found
	        // add parent key to str and
	        // call the display function recursively
	        // for child node
	    	
	        if (tmp.children[i]!=null) 
	        {
	            
	        	string[level] = tmp.ch;
	            string[level+1] = (char) (i + 'A');
	            
	             display(tmp.children[i], string, level + 1,setWords);
	        }
	    }

	    setWords.add(words.split("\n")[0]);
	    
	    return setWords;
	  
	}
	int size() {
		return size;
	}
	TrieNode remove(String key) {
		return remove(root,key,0);
	}
	// Recursive function to delete a key from given Trie
     TrieNode remove(TrieNode root, String key, int depth)
    {
        // If tree is empty
        if (root == null)
            return null;
 
        // If last character of key is being processed
        if (depth == key.length()) {
 
            // This node is no more end of word after
            // removal of given key
            if (root.isWord)
                root.isWord = false;
 
            // If given is not prefix of any other word
//            if (isEmpty()) {
//                root = null;
//            }
 
            return root;
        }
 
        // If not last character, recur for the child
        // obtained using ASCII value
        int index = key.charAt(depth) - 'A';
        root.children[index] =
            remove(root.children[index], key, depth + 1);
 
        // If root does not have any child (its only child got
        // deleted), and it is not end of another word.
        if (root==null && root.isWord == false){
            root = null;
        }
 
        return root;
    }

}


public class TrieNode<T extends Comparable<? super T>> {
	
public char ch;
public boolean isWord;
public TrieNode<T> [] children;

public TrieNode(char ch) {
	this.ch = ch;
	isWord = false;
	children = new TrieNode[26];
	
	
}

}

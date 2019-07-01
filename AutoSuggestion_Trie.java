// Auto suggestions.
// Author: kei
// Date  : June 30, 2019
public class AutoSuggestion_Trie {

	public static void main(String[] args) {		
		
		// Build Trie. 
        Trie trie = new Trie();
        trie.insert("aa");
        trie.insert("abc");
        trie.insert("abcd");
        trie.insert("abbbaba");
        
        // Test printAutoSuggestions(). 
		trie.printAutoSuggestions(trie.root, "ab");
//		abbbaba
//		abc
//		abcd
		System.out.println();
		trie.printAutoSuggestions(trie.root, "abc");
//		abc
//		abcd
		System.out.println();
		trie.printAutoSuggestions(trie.root, ""); // Enter a non-empty string.
		System.out.println();
		trie.printAutoSuggestions(trie.root, "ad"); // No string found.
		System.out.println();
		trie.printAutoSuggestions(trie.root, "aa"); // aa
		System.out.println();
		
		trie = new Trie();
		trie.insert("hello"); 
		trie.insert("dog"); 
		trie.insert("hell"); 
		trie.insert("cat"); 
		trie.insert("a"); 
		trie.insert("hel"); 
		trie.insert("help"); 
		trie.insert("helps"); 
		trie.insert("helping"); 
	    trie.printAutoSuggestions(trie.root, "hel"); 
	    System.out.println();
//	    hel
//	    hell
//	    hello
//	    help
//	    helping
//	    helps
		
	    
	    
		

	}
	
	

}

class TrieNode {
    // R links to node children. 
    private TrieNode[] links;
    private final int R = 26;
    private boolean isEnd;
    // Number of child nodes (non-null links). 
    private int size;
    
    public TrieNode() {
        links = new TrieNode[R];
        isEnd = false;
        size = 0;
    }
    
    public boolean containsKey(char c) {
        return links[c - 'a'] != null;
    }
    
    public TrieNode get(char c) {
        return links[c - 'a'];
    }
    
    public void put(char c, TrieNode node) {
        links[c - 'a'] = node;
        size++;
    }
    
    public void setEnd() {
        isEnd = true;
    }
    
    public boolean isEnd() {
        return isEnd;
    }
    
    public int getNumChild() {
        return size;
    }
}


class Trie {
    public TrieNode root;
    
    public Trie() {
        root = new TrieNode();      
    }
    
    // Inserts a word into the trie. O(m) time, O(m) space, where m is the word length. 
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (!node.containsKey(currChar)) {
                node.put(currChar, new TrieNode());
            }
            node = node.get(currChar);
        }
        node.setEnd();
    }
    
    // Return the last node. 
    public TrieNode getLastNode(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (!node.containsKey(currChar)) {  
                return null;
            }
            node = node.get(currChar);
        }
        
        return node;
    }
    
    // Print suggestions for given query prefix. 
    public int printAutoSuggestions(TrieNode root, String query) {
    	if (query.isEmpty()) {
    		System.out.println("Enter a non-empty string.");
			return 0;
		}
    	
    	// Get the last node of the query. 
    	TrieNode lastNode = this.getLastNode(query);
    	if (lastNode == null) {
    		// There is no string that starts with this query. 
    		System.out.println("No string found.");
			return 0;
		}
    	
    	if (lastNode.isEnd() && lastNode.getNumChild() == 0) {
    		// The query is present as a word, and there is no subtree
    		// below the last node. 
    		System.out.println(query);
			return -1;
		}
    	
    	if (lastNode.getNumChild() != 0) {
            StringBuilder prefix = new StringBuilder(query);
            suggestionsRecur(lastNode, prefix);
		}
    	
		return 1;
    }

    // Print auto-suggestions for given prefix. 
    // @param node  the last node in the prefix. 
    private void suggestionsRecur(TrieNode node, StringBuilder prefix) {
		if (node.isEnd()) {
            System.out.println(prefix.toString());
		}
		if (node.getNumChild() == 0) {
			// No child node. 
			return;
		}
		
		for (int i = 0; i < 26; i++) {
			char c = (char) (i + 'a');
			if (node.containsKey(c)) {	
                StringBuilder curPrefix = new StringBuilder(prefix);
                curPrefix.append(c);
                suggestionsRecur(node.get(c), curPrefix);
			}	
		}
	}
    
    
    
    
}







































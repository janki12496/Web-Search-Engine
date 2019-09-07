package SearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.print.attribute.standard.Finishings;

/*************************************************************************
 *  Compilation:  javac TST.java
 *  Execution:    java TST < words.txt
 *  Dependencies: StdIn.java
 *
 *  Symbol table with string keys, implemented using a ternary search
 *  trie (TST).
 *
 *
 *  % java TST < shellsST.txt
 *  by 4
 *  sea 6
 *  sells 1
 *  she 0
 *  shells 3
 *  shore 7
 *  the 5

 *
 *  % java TST
 *  theory the now is the time for all good men

 *  Remarks
 *  --------
 *    - can't use a key that is the empty string ""
 *
 *************************************************************************/

public class TST<Value> {
    private int N;       // size
    private Node root;   // root of TST

    private class Node {
        private char c;                 // character
        private Node left, mid, right;  // left, middle, and right subtries
        private Value val;              // value associated with string
    }

    // return number of key-value pairs
    public int size() {
        return N;
    }

   /**************************************************************
    * Is string key in the symbol table?
    **************************************************************/
    public boolean contains(String key) {
        return get(key) != null;
    }

    public Value get(String key) {
        if (key == null) throw new NullPointerException();
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        Node x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

    // return subtrie corresponding to given key
    private Node get(Node x, String key, int d) {
        if (key == null) throw new NullPointerException();
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        if (x == null) return null;
        char c = key.charAt(d);
        if      (c < x.c)              return get(x.left,  key, d);
        else if (c > x.c)              return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid,   key, d+1);
        else                           return x;
    }


   /**************************************************************
    * Insert string s into the symbol table.
    **************************************************************/
    public void put(String s, Value val) {
        if (!contains(s)) N++;
        root = put(root, s, val, 0);
    }

    private Node put(Node x, String s, Value val, int d) {
        char c = s.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if      (c < x.c)             x.left  = put(x.left,  s, val, d);
        else if (c > x.c)             x.right = put(x.right, s, val, d);
        else if (d < s.length() - 1)  x.mid   = put(x.mid,   s, val, d+1);
        else                          x.val   = val;
        return x;
    }


   /**************************************************************
    * Find and return longest prefix of s in TST
    **************************************************************/
    public String longestPrefixOf(String s) {
        if (s == null || s.length() == 0) return null;
        int length = 0;
        Node x = root;
        int i = 0;
        while (x != null && i < s.length()) {
            char c = s.charAt(i);
            if      (c < x.c) x = x.left;
            else if (c > x.c) x = x.right;
            else {
                i++;
                if (x.val != null) length = i;
                x = x.mid;
            }
        }
        return s.substring(0, length);
    }

    // all keys in symbol table
    public Iterable<String> keys() {
        Queue<String> queue = new Queue<String>();
        collect(root, "", queue);
        return queue;
    }

    // all keys starting with given prefix
    public Iterable<String> prefixMatch(String prefix) {
        Queue<String> queue = new Queue<String>();
        Node x = get(root, prefix, 0);
        if (x == null) return queue;
        if (x.val != null) queue.enqueue(prefix);
        collect(x.mid, prefix, queue);
        return queue;
    }

    // all keys in subtrie rooted at x with given prefix
    private void collect(Node x, String prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.left,  prefix,       queue);
        if (x.val != null) queue.enqueue(prefix + x.c);
        collect(x.mid,   prefix + x.c, queue);
        collect(x.right, prefix,       queue);
    }


    // return all keys matching given wildcard pattern
    public Iterable<String> wildcardMatch(String pat) {
        Queue<String> queue = new Queue<String>();
        collect(root, "", 0, pat, queue);
        return queue;
    }
 
    private void collect(Node x, String prefix, int i, String pat, Queue<String> q) {
        if (x == null) return;
        char c = pat.charAt(i);
        if (c == '.' || c < x.c) collect(x.left, prefix, i, pat, q);
        if (c == '.' || c == x.c) {
            if (i == pat.length() - 1 && x.val != null) q.enqueue(prefix + x.c);
            if (i < pat.length() - 1) collect(x.mid, prefix + x.c, i+1, pat, q);
        }
        if (c == '.' || c > x.c) collect(x.right, prefix, i, pat, q);
    }
    public static int findoccurrences(String pat,String txt){
    	BoyerMoore boyermoore1 = new BoyerMoore(pat);
		String old=txt;
		int count=0;
		while(txt.contains(pat)) {
			int offset1 = boyermoore1.search(txt);
			if(txt.length()<old.length()) {
				//list.add(offset1+(old.length() - txt.length()));
			}
			else {
				//list.add(offset1);
			}
			txt=txt.substring(offset1+pat.length());		
			count++;
		}
		return count;
}


    // test client
    public static void main(String[] args)throws Exception {
		/*
		 * In file=new In("benefits_repository29b1.txt"); String text=file.readAll();
		 */
        
       // System.out.println(text);
    	File f=new File("benefits_repository29b1.txt");
    	FileReader fr = new FileReader(f);
    	BufferedReader br = new BufferedReader(fr);
    	StringBuffer text=new StringBuffer("");
		String sCurrentLine;

		while ((sCurrentLine = br.readLine()) != null) {
			System.out.println(sCurrentLine);
			text.append(sCurrentLine);
		}
    	
        StringTokenizer s=new StringTokenizer(text.toString()," -.;,()\n\t");
        
    	//String[] keys = {"she","sells","sea","shells","by","the","sea","shore"}; 

    	// build symbol table from standard input
        TST<int[][]> st = new TST<int[][]>();
       	
       	String[] key = {"the","complex","PPI","prediction","others"};
       	int occurrences;
       	
       	while(s.hasMoreTokens()) {
       		int index[][]=new int[1][2];
            String token=s.nextToken();
               
            occurrences=findoccurrences(token, text.toString());
        	index[0][0]=1;
            index[0][1]=occurrences;
            
            st.put(token, index);
       	}  	    	
       	for(String ky:key) {  
            StdOut.println("key ="+ky+", value = "+ ((st.get(ky) != null)?st.get(ky):0));
           }
		/*
		 * for(int i=0;i<key.length;i++) if(st.get(key[i])== null)
		 * System.out.println("key = "+key[i]+ " Occurrences= 0"); else
		 * System.out.println("key = "+key[i]+ " Occurrences="
		 * +st.get(key[i])[0][0]+" "+st.get(key[i])[0][1]);
		 */
    }
}
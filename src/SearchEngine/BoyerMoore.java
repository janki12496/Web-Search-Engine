package SearchEngine;

import java.awt.List;
import java.nio.file.Files;
import java.util.*;

import SearchEngine.In;;

/***************************************************************
 *  Compilation:  javac BoyerMoore.java
 *  Execution:    java BoyerMoore pattern text
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using the
 *  bad-character rule part of the Boyer-Moore algorithm.
 *  (does not implement the strong good suffix rule)
 *
 *  % java BoyerMoore abracadabra abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:               abracadabra
 *
 *  % java BoyerMoore rab abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:         rab
 *
 *  % java BoyerMoore bcara abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:                                   bcara
 *
 *  % java BoyerMoore rabrabracad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad
 *
 *  % java BoyerMoore abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 ***************************************************************/

public class BoyerMoore {
    private final int R;     // the radix
    private int[] right;     // the bad-character skip array

    private char[] pattern;  // store the pattern as a character array
    private String pat;      // or as a string

    // pattern provided as a string
    public BoyerMoore(String pat) {
        this.R = 256;
        this.pat = pat;

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    }

    // pattern provided as a character array
    public BoyerMoore(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++)
            this.pattern[j] = pattern[j];

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pattern.length; j++)
            right[pattern[j]] = j;
    }

    // return offset of first match; N if no match
    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return N;                       // not found
    }


    // return offset of first match; N if no match
    public int search(char[] text) {
        int M = pattern.length;
        int N = text.length;
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pattern[j] != text[i+j]) {
                    skip = Math.max(1, j - right[text[i+j]]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return N;                       // not found
    }

    public static ArrayList<Integer> findoccurrences(String pat,String txt){
		ArrayList<Integer> list= new ArrayList<Integer>();
		BoyerMoore boyermoore1 = new BoyerMoore(pat);
		String old=txt;
		while(txt.contains(pat)) {
			int offset1 = boyermoore1.search(txt);
			if(txt.length()<old.length()) {
				list.add(offset1+(old.length() - txt.length()));
			}
			else {
				list.add(offset1);
			}
			txt=txt.substring(offset1+pat.length());		
		}
		return list;
}

    // test client
    public static void main(String[] args) {
       
        // There are two implmentations of search
 	   	// one is with String and the other is an array of chars
 	  	In file=new In("HardDisk.txt");
 	   	String pat[] = {"hard","disk","hard disk","hard drive","hard dist", "xltpru"};
    	String text=file.readAll();
    	ArrayList<Integer> list= new ArrayList<Integer>();
    	long start,end;
    	int num=100;
    	
    	for(int i=0;i<pat.length;i++) {
		   if(!text.contains(pat[i]))
        			StdOut.println(pat[i]+ " not present");
		   start=System.nanoTime();
		   for(int j=0;j<num;j++) {   
			   list=findoccurrences(pat[i], text);
		   }
		   
		   end=System.nanoTime();
		   if(list.isEmpty()) {}else
		   
			   StdOut.println(pat[i] + " is at positions: "+list);
		   System.out.println(pat[i]+" has "+list.size()+" occurrences");
		   System.out.println("Average CPU Time: "+(end-start)/num+"\n");
       }
    }        			
       	
}
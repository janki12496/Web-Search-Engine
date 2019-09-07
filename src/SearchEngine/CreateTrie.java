package SearchEngine;
import SearchEngine.In;
import SearchEngine.QuickSelect;
import SearchEngine.TST;

import java.io.File;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateTrie {
	
	public static TST<Integer> st;
	public static File[] myfile;
	
	public static void createTST() {
		
		st = new TST<Integer>();
		myfile = new File("Test").listFiles();
		int i=1;
		
		for (File f : myfile) {
			if (f.isFile()) {
				In file = new In(f);
				String text = file.readAll();
				text=text.replaceAll("\\W"," ");
				String domain = "\\w+";
				Pattern r = Pattern.compile(domain);
				Matcher a = r.matcher(text);
				
				while (a.find( )) {
					String token = a.group(0).toLowerCase();
					if (st.get(token) == null) {
						st.put(token, i);
						i++;
					}
				}
			}
		}
		//StdOut.println(st.size()); // prints trie size and trie
		/*
		 * for (String key : st.keys()) StdOut.println(key + " " + st.get(key));
		 */		
	}
}

package SearchEngine;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import SearchEngine.In;

public class CreateInvertedIndex {
	
	public static int[][] arr;
	
	public static void createInvertedIndex() {
		File[] myfile = CreateTrie.myfile;
		TST<Integer> st = CreateTrie.st;
		arr=new int[myfile.length+1][st.size()+1];	
		
		int pagenumber=1;
		for (File f : myfile) {
			HashMap<String,Integer> freq = new HashMap<String,Integer>( );
			for(int c1=0;c1<myfile.length+1;c1++) {
				arr[c1][0]=c1;
			}
			
			for(int r1=0;r1<st.size()+1;r1++) { arr[0][r1]=r1; }
			
			if (f.isFile()) {
				In file = new In(f);
				
				String text = file.readAll();
				text=text.replaceAll("\\W"," ");
				String domain = "\\w+";
				Pattern r = Pattern.compile(domain);
				Matcher a = r.matcher(text);
				int b=1;
				
				while (a.find( )) {
					String token = a.group(0).toLowerCase();
					
					Integer count = freq.get(token.toLowerCase()); // get the previous count for this word
					if (count == null)
						count = 0; // if not in map, previous count is zero
					freq.put(token, 1 + count);
				}
				
				for (Entry<String, Integer> ent : freq.entrySet( )) {
					arr[0][0]=0;
					int index=st.get(ent.getKey());
					arr[pagenumber][index]=ent.getValue();	
				}
			}
			pagenumber++;
		}		 
		//System.out.println(Arrays.deepToString(arr).replace("], ", "]\n")); 		  //prints index
	}
}

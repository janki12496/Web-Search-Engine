package SearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import SearchEngine.TST;

public class CreateTST {
public static TST<Integer> st;	
public static HashMap<Integer,ArrayList<Integer>> index;
	
	public static void createTST() throws Exception{
		File[] myfile=new File("C:\\Users\\MEERA\\Documents\\MAC\\ACC\\workspace\\WebSearchEngine\\TEXT").listFiles();
		st = new TST<Integer>();
		index = new HashMap<Integer, ArrayList<Integer>>();
		int i=0; 
		 FileReader fr;
		 BufferedReader br;
		for(File f:myfile) {
			 if(f.isFile()) { 
				 fr = new FileReader(f);
			    	br = new BufferedReader(fr);
			    	
				
				  In file=new In(f); 
				  String text=file.readAll();
				 
				
				 StringTokenizer s=new StringTokenizer(text.toString()," -.;, ()\n\t");
			
				 while(s.hasMoreTokens()) {
					ArrayList<Integer> list=new ArrayList<Integer>(); 
					String token=s.nextToken();
					int c=0;
					
					for(File allfiles: myfile) { 
						if(allfiles.isFile()) { 
							
							  In allfile=new In(allfiles); 
							  String txt=allfile.readAll();
							 
							 
							 list.add(c,TST.findoccurrences(token, txt.toString()));						
							 c++;
						 }
					}
					//System.out.println(token+" "+st.get(token));
					if(st.get(token)==null) {
						st.put(token, i);
						index.put(i,list);	
					}
					i++;	
				
				 }
			}
		 }
		
		 for (Integer name: index.keySet()){ 
			  System.out.print(" \n" +name + " Val: " );
			  for(int j=0;j<myfile.length;j++) {
				  System.out.print(" "+index.get(name).get(j));
			  }
		 }
		 for (String key : st.keys()) {
				StdOut.println("TST:::"+key + " " + st.get(key));
			}
		 
		
	}
	
}

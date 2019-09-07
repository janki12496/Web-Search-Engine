package SearchEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class GetTopTenPagesByFrequency {
	
	public static ArrayList<File> toptenpages(String[] searched) {

		File[] myfile = CreateTrie.myfile;
		TST<Integer> st = CreateTrie.st;
		int[][] arr = CreateInvertedIndex.arr;
		
		Integer[] list = new Integer[myfile.length];		
		ArrayList<File> files=new ArrayList<File>();
		 
		for(int i=0;i<myfile.length;i++)
			list[i]=0;
		
		for(int s=0;s<searched.length;s++) { 
  
			  if(st.get(searched[s].toLowerCase())==null){
				//System.out.print(" No match found");  
			  }
			  else {
				  int indextoarr=st.get(searched[s].toLowerCase());
				  for(int n=0;n<myfile.length;n++) { 
					  //list[n] = 0;
					  list[n]=list[n]+arr[n+1][indextoarr];
				  }
			  } 
		  } 
		  
		  ArrayList<Integer> oldlist = new ArrayList<Integer>(Arrays.asList(list));
		 
		  int i=list.length-11;
		  
		  for (int j =list.length-1;j> i; j--) { 
			  int rank=QuickSelect.select(list,j); 
			  files.add(myfile[oldlist.indexOf(rank)]);
			  oldlist.set(oldlist.indexOf(rank), null);
			 } 
		  //System.out.println(files);
		  return files;	 
	}

}

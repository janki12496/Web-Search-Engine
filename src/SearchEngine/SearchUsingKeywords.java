package SearchEngine;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;

public class SearchUsingKeywords {
	
	public static void main(String args[]) {
		
		System.out.println("Search Here: ");
		Scanner input = new Scanner(System.in);
		String keywords=input.nextLine();	
		String[] searched = keywords.split("\\s+");
		long start,end;
		
		CreateTrie.createTST();
		CreateInvertedIndex.createInvertedIndex();
		
		int flag=1;
		System.out.print("\nWord Occurrence: ");
		for(int i=0; i<searched.length;i++) {
			int totalwordoccurrence=Frequency.findoccurences(searched[i].toLowerCase());
			if(totalwordoccurrence==0) {
				flag=0;
				List<Entry<String, Integer>> suggessions = GetSuggessions.givesuggestion(searched[i]);
				System.out.print("\n"+searched[i]+": "+Frequency.findoccurences(searched[i].toLowerCase())+"   (Try: ");
				for(int j=0;j<suggessions.size();j++) 
					 System.out.print(suggessions.get(j).getKey()+", ");
				System.out.print("...)");
			}
			else
				System.out.print("\n"+searched[i]+": "+Frequency.findoccurences(searched[i].toLowerCase()));	
		}
		
		System.out.println("\n\nResults:");		
		if(flag==0) {
			List<Entry<File, Integer>> topTenPagesByEditDistance=GetTopTenPagesByEditDistance.findeditdistance(searched);			 
			for(int k=0;k<topTenPagesByEditDistance.size();k++) 
				System.out.println(topTenPagesByEditDistance.get(k).getKey()); 
	
		}
		else{
			start=System.currentTimeMillis();
			ArrayList<File> files=GetTopTenPagesByFrequency.toptenpages(searched);
			end=System.currentTimeMillis();
			for (File f : files) { System.out.println(f); }
			System.out.println("\nResults obtained in- "+(end-start)+" seconds");
		}	
	}

}

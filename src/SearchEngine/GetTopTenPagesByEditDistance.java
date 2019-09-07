package SearchEngine;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import SearchEngine.QuickSelect;
import SearchEngine.In;
import SearchEngine.Sequences;

public class GetTopTenPagesByEditDistance {
	public static HashMap<File, Integer> hashtable;

	public static List<Entry<File, Integer>> findeditdistance(String keywords[]) {

		hashtable = new HashMap<File, Integer>();
		File[] myfile = CreateTrie.myfile;
		
		for (File f : myfile) {
			In file = new In(f);
			String text = file.readAll();
			int smallest = 0;
			for (int k = 0; k < keywords.length; k++) {
				int c = 0;
				StringTokenizer s = new StringTokenizer(text, " -.;, ()\n\t");
				Integer[] distance = new Integer[s.countTokens()];
				while (s.hasMoreTokens()) {
					String token = s.nextToken();
					distance[c] = Sequences.editDistance(keywords[k], token);
					c++;
				}
				Integer[] input = new Integer[10];

				for (int i = 0; i < 10; i++) {
					input[i] = QuickSelect.select(distance, i);
				}
				for (int i = 0; i < 10; i++) {
					if(input[i]==null)
						input[i]=1000000;
				}
				for (int i = 0; i < 5; i++) {
					smallest = smallest + input[i];
				}
			}
			hashtable.put(f, smallest);
		}
		List<Entry<File, Integer>> lowestTenEntries = hashtable.entrySet().stream()
				.sorted(Comparator.comparing(Entry::getValue)).limit(10).collect(Collectors.toList());
		
		return lowestTenEntries;
	}
}

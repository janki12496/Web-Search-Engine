package SearchEngine;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class GetSuggessions {
	public static List<Entry<String, Integer>> givesuggestion(String wrongword) {
		
		Hashtable<String, Integer> h= new Hashtable<String, Integer>();
		int distance = 0;
			for (String key : CreateTrie.st.keys()) {
				distance=Sequences.editDistance(wrongword.toLowerCase(), key);
				if(distance!=0)
					h.put(key,distance);
			}
			List<Entry<String, Integer>> lowestThreeEntries = h.entrySet()
			        .stream()
			        .sorted(Comparator.comparing(Entry::getValue))
			        .limit(3)
			        .collect(Collectors.toList());

			return lowestThreeEntries;
	}
}
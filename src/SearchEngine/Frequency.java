package SearchEngine;

public class Frequency {
	public static int findoccurences(String searched) {
		int countofword=0;
		TST<Integer> st = CreateTrie.st;
		if(st.get(searched)==null){
			return 0;
		}
		else {
			for(int i=0;i<CreateTrie.myfile.length;i++) {
				countofword+=CreateInvertedIndex.arr[i+1][CreateTrie.st.get(searched)];
			}
		//	System.out.println(countofword);
			return countofword;
		}
	}

}

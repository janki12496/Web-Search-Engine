# Web-Search-Engine
Web search engine for efficient search of web pages designed using HashMap, TST, Edit Distance and Sorting concepts

Built an web search engine for efficient search of web pages based on Advanced computing concepts. . For around 1000 web pages, the search engine creates a single inverted index which keeps track of word occurrence in each web page and uses quick select algorithm for searching the most relevant web pages. It perform spell checking and web page suggestion through edit distance and dual pivot quick sort. 

Technology Used: Java, Eclipse IDE

The file WebPages.zip contains 100 webpages and Test.zip contains 1000 webpages.
The webpages are converted into text file using JSoup and then processed to obtain results. The search engine gives 10 relevant web pages depending on the total frequcy of the keywords in each Web Page. If keyword is not present in any of the web pages, it suggests other keywords to try, as well as lists 10 web pages by searching other words similar to searched keywords (using edit distance). 

Run SearchUsingKeywords.java for searching
See Output.pdf for results obtained

Note: When you run this search engine for the first time, it takes some time as it creates a single Trie and Inverted Index for all the Web pages (and that to once). On the second run, the output will be obtained in few seconds as, now, the Trie and Inverted Index are created already(when ran first time) and it will take constant time to access.   

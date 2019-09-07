# Web-Search-Engine
Web search engine for efficient search of web pages designed using HashMap, TST, Edit Distance and Sorting concepts

Built an web search engine for efficient search of web pages based on Advanced computing concepts. . For around 1000 web pages, the search engine creates a single inverted index which keeps track of word occurrence in each web page and uses quick select algorithm for searching the most relevant web pages. It perform spell checking and web page suggestion through edit distance and dual pivot quick sort. 

Technology Used: Java, Eclipse IDE

The file WebPages.zip contains 100 webpages and Test.zip contains 1000 webpages.
The webpages are converted into text file using JSoup and then processed to obtain results. The search engine gives 10 relevant web pages depending on the total frequcy of the keywords in each Web Page. If keyword is not present in any of the web pages, it suggests other keywords to try, as well as lists 10 web pages by searching other words similar to searched keywords (using edit distance). 

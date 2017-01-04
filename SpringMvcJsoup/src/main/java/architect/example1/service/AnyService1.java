package architect.example1.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class AnyService1 {
	private Set<String> historicUrl = new HashSet<>();
	private Set<String> usefulUrl = new HashSet<>();
	private String searchTerm;
	private long time = System.currentTimeMillis();
	private long maxSearchTime;
	
	public void crawl(String url) {
		if (System.currentTimeMillis() - time > maxSearchTime) {
			return;
		}
		Document document;
		try {
			document = Jsoup.connect(url).get();
		} catch (Exception e) {
			return;
		}
		// If the entire context does not contain the search term, the article is irrelevant, abandon the resource. 
		if (document.text().contains(searchTerm)) {
			usefulUrl.add(url);
			System.out.println("URL = " + url);
			String[] links = {"a[href]", "[src]", "link[href]"};
			for (String link : links) {
				Elements elements = document.select(link);
				for (Element element : elements) {
					url = element.attr("href");
					String html = element.html();
					// If the link contains the search term, it is strongly related, just go to visit the website.
					// If the link doesn't contain the serach term, visit the website with .5 of probability.
					double p = html.contains(searchTerm) ? 1 : 0.5;
					if (!historicUrl.contains(url) && Math.random() <= p) {
						historicUrl.add(url);
						crawl(url);
					}
				}
			}
		}
	}
	
	public Set<String> crawl(String searchTerm, String baseUrl, long maxSearchTime) {
		this.searchTerm = searchTerm;
		this.maxSearchTime = maxSearchTime;
		crawl(baseUrl);
		return this.usefulUrl;
	}
	
	@Test
	public void test() throws IOException {
		Set<String> usefulUrl = crawl("Obama", "http://www.nytimes.com", Long.MAX_VALUE);
		System.out.println(usefulUrl);
	}
}

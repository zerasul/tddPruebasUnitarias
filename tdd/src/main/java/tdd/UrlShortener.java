package tdd;

public class UrlShortener {
	
	private CacheController cacheController = CacheControllerFactory.getInstance();

	public String shorter(String url) {
		// TODO Auto-generated method stub
		return this.cacheController.shortUrl(url);
	}

	public String reverseUrl(String shortenUrl) {
		// TODO Auto-generated method stub
		return this.cacheController.reverseUrl(shortenUrl);
	}

}

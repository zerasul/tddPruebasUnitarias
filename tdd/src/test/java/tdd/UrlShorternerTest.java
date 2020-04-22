package tdd;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UrlShortener.class, CacheController.class, CacheControllerFactory.class})
public class UrlShorternerTest {

	@Mock
	private CacheController controller;
	
	private UrlShortener fixture;
	
	@Before
	public void setup() {
		PowerMockito.mockStatic(CacheControllerFactory.class);
		PowerMockito.when(CacheControllerFactory.getInstance()).thenReturn(this.controller);
		Mockito.when(this.controller.shortUrl("http://google.es")).thenReturn("http://aaa.l/aassd");
		Mockito.when(this.controller.reverseUrl("http://aaa.l/aassd")).thenReturn("http://google.es");
		this.fixture= new UrlShortener();
		Whitebox.setInternalState(this.fixture, "cacheController", this.controller);
	}
	
	@Test
	public void shortenUrlTest() {
		String url = "http://google.es";
		String shortener = this.fixture.shorter(url);
		
		assertEquals("http://aaa.l/aassd", shortener);
	}
	
	@Test
	public void getreverseUrlTest() {
		String shortenUrl="http://aaa.l/aassd";
		String url = this.fixture.reverseUrl(shortenUrl);
		assertEquals("http://google.es", url);
	}
}

package jp.co.axa.apidemo;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author md.shariful
 * 
 * Test depends on test case design
 * here i write, just some sample Test
 * 
 */
public class ApiPropertiesTest extends ApiDemoApplicationTests {

	@Value("${spring.h2.console.enabled}")
	String h2_console_enabled;

	@Value("${spring.datasource.url}")
	String datasource_url;

	/**
	 * 
	 */
	@Test
	public void h2_console_enabled() {
		assertThat(h2_console_enabled).isEqualTo("true");
	}

	/**
	 * 
	 */
	@Test
	public void datasource_url() {
		assertThat(datasource_url).containsIgnoringCase("testdb");
	}

}

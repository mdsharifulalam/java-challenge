package jp.co.axa.apidemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author md.shariful
 * 
 * Test depends on test case design
 * here i write, just some sample Test
 * 
 */
@TestPropertySource(locations = "/application.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {

	@Test
	public void contextLoads() {
	}

}

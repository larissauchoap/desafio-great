package testlink;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

public class testDesafioGreat {

	static WebDriver driver;

	@Before
	public void setUp() throws Exception {
		//Abrindo o Site
		driver.get("http://testlink.org/");
		
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();	
	}

	@Test
	public void testeValidarGitHubTestlink() {
		//Salvando as variáveis para os Asserts
		String textoDoGithubHeaderEsperado = "testlink-code";
		String textoDoGithubAboutEsperado = "TestLink Open Source Test & Requirement Management System";
		String tituloDoGithub = "GitHub - TestLinkOpenSourceTRMS/testlink-code at testlink_1_9_20_fixed";
		
		//Abrindo o GitHub do Testlink
		WebElement linkGitHub = driver.findElement(By.xpath("/html/body/div/div[3]/div/a[3]"));
		linkGitHub.click();
		
		//Pegando os textos para validar no Assert
		String textoDoGithubHeaderSite = driver.findElement(By.xpath("//*[@id=\"repository-container-header\"]/div[1]/div/div/strong/a")).getText();
		String textoDoGithubAbout = driver.findElement(By.xpath("//*[@id=\"repo-content-pjax-container\"]/div/div/div[3]/div[2]/div/div[1]/div/p")).getText();	
		
		//Asserts Finais
		assertEquals(textoDoGithubHeaderEsperado, textoDoGithubHeaderSite);
		assertEquals(textoDoGithubAboutEsperado, textoDoGithubAbout);
        assertEquals(tituloDoGithub,driver.getTitle());
	}
	
	@Test
	public void testeBuscarNoGitHub() {
		//Salvando as variáveis para os Asserts
		String tituloDaPagina = "Search · Test · GitHub";
		String textoDaBuscaEsperado = "1,206 code results in TestLinkOpenSourceTRMS/testlink-code or view all results on GitHub";
		
		//Abrindo o GitHub do Testlink
		WebElement linkGitHub = driver.findElement(By.xpath("/html/body/div/div[3]/div/a[3]"));
		linkGitHub.click();
	
		//Realizando a busca
		WebElement campoBusca = driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div/div/div[1]/div/div/form/label/input[1]"));
		campoBusca.sendKeys("Test");
		campoBusca.sendKeys(Keys.ENTER);
		
		//Pegando os textos para validar no Assert
		String textoDaBusca = driver.findElement(By.xpath("//*[@id=\"repo-content-pjax-container\"]/div/div/div[3]/div/div[1]/h3")).getText();
				
		//Asserts Finais		
		 assertEquals(tituloDaPagina,driver.getTitle());
		 assertEquals(textoDaBuscaEsperado, textoDaBusca);
	}
   
	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//Fechando o navegador
		driver.quit();
	}

}

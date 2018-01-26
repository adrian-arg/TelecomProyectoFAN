package Tests;
import static org.testng.Assert.assertTrue;

import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.BasePage;
import Pages.ContactInformation;
import Pages.ContactSearch;
import Pages.CustomerCare;
import Pages.HomeBase;
import Pages.SalesBase;
import Pages.setConexion;
import Tests.TestBase.waitFor;

public class SalesNominaciones extends TestBase{

	@BeforeClass
	public void Init() {
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		loginFranciso(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		HomeBase homePage = new HomeBase(driver);
		try {Thread.sleep(6000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    String a = driver.findElement(By.id("tsidLabel")).getText();
	    if (a.contains("Ventas")){}
	    else {
	    	homePage.switchAppsMenu();
	    	try {Thread.sleep(2000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	    	homePage.selectAppFromMenuByName("Ventas");
	    	try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}            
	    }
	    driver.findElement(By.xpath("//a[@href=\'https://crm--sit--c.cs14.visual.force.com/apex/taClientSearch']")).click();		
		SalesBase SB = new SalesBase(driver);
		SB.BuscarAvanzada("Cliente", "Wholesale", "", "", "");
		WebElement cli = driver.findElement(By.id("tab-scoped-2"));
		if (cli.getText().contains("Cliente Wholesale")) {
			cli.click();
		}
		sleep(3000);
		WebElement cua = driver.findElement(By.id("tab-scoped-2")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(6).findElement(By.tagName("svg"));
		cua.click();
		sleep(10000);
		
	}

	//@AfterMethod
	public void IceB() {
		driver.navigate().refresh();
	}
	
	//@AfterClass
	public void Exit() {
		driver.quit();
		sleep(2000);
	}
	
	@Test(groups = "Sales") 
	  public void TS75995_Nominacion_Argentino_Validar_cantidad_de_lineas(){
		BasePage dni = new BasePage(driver);
		dni.setSimpleDropdown(driver.findElement(By.id("DocumentTypeSearch")),"DNI");
		sleep (2000);
		WebElement ddd = driver.findElement(By.id("DocumentInputSearch"));
		ddd.sendKeys("10000019");
		List<WebElement> gen = driver.findElements(By.id("GenderSearch"));
  	for(WebElement g : gen) {
  		if(g.getText().equals("Masculino")) {
  			g.click();}}
	}
  	
	@Test(groups = "Sales")
	public void TS95075_SalesCPQ_Nominacion_Argentino_Verificar_Datos_Para_Nominar_Cliente_Existente(){
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("DNI", "10000018", "femenino");
		sleep(6000);
		assertTrue(!driver.findElement(By.id("FirstName")).getAttribute("value").isEmpty());
		
	}
	
	@Test(groups = "Sales")
	public void TS95094_SalesCPQ_Nominacion_Extranjero_Verificar_Confirmacion_Exitosa(){
		String FilePath = "C:\\Users\\florangel\\Downloads\\mapache.jpg";
		SalesBase SB = new SalesBase(driver);
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", "1324567", "femenino");
		sleep(6000);
		driver.findElement(By.id("PermanencyDueDate")).sendKeys("30/06/2018");
		CC.obligarclick(driver.findElement(By.id("Contact_nextBtn")));
		sleep(5000);
		driver.findElement(By.id("MethodSelectionPassport|0")).findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();
		driver.findElement(By.id("ValidationMethod_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys(FilePath);
		sleep(1000);
		CC.obligarclick(driver.findElement(By.id("DocumentMethod_nextBtn")));
		sleep(18000);
		System.out.println(driver.findElement(By.cssSelector(".slds-grid.slds-wrap.ng-pristine.ng-valid")).findElement(By.id("TextBlock2")).findElement(By.className("ng-binding")).findElements(By.tagName("p")).get(2).getText());
		assertTrue(driver.findElements(By.id("TextBlock2")).get(1).findElements(By.tagName("p")).get(3).getText().toLowerCase().contains("nominaci\u00f3n exitosa"));
		
	}
	
	@Test(groups = "Sales")
	public void TS95114_SalesCPQ_Nominacion_Extranjero_Verificar_Datos_Nominar_Cliente_Extranjero(){
		assertTrue((driver.findElement(By.id("DocumentTypeSearch")).isEnabled()));
		assertTrue((driver.findElement(By.cssSelector(".slds-select_container.vlc-control-wrapper.vlc-slds__border.vlc-slds__border--primary")).findElement(By.tagName("label")).getText().contains("TIPO DE DOCUMENTO")));
		assertTrue((driver.findElement(By.id("DocumentInputSearch")).isEnabled())&&(driver.findElement(By.cssSelector(".slds-form-element.vlc-flex.ng-pristine.ng-scope.ng-valid-pattern.ng-invalid.ng-invalid-required.ng-valid-minlength.ng-valid-maxlength")).findElement(By.tagName("label")).getText().contains("DOCUMENTO")));
		assertTrue(driver.findElement(By.id("GenderSearch|0")).isEnabled()&&(driver.findElement(By.id("GenderSearch|0")).findElement(By.tagName("label")).getText().contains("G\u00e9nero")));
	}
	
	@Test(groups = "Sales")
	public void TS95118_SalesCPQ_Nominacion_Extranjero_Verificar_Formato_De_Fecha_PlazoPermanencia(){
		String FilePath = "C:\\Users\\florangel\\Downloads\\mapache.jpg";
		SalesBase SB = new SalesBase(driver);
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", "1324567", "femenino");
		sleep(6000);
		driver.findElement(By.id("PermanencyDueDate")).sendKeys("30/06/2021");
		assertTrue(driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")).getText().contains("La permanencia no puede ser mayor a 2 a�os a partir de la fecha o menor a la fecha actual"));
		sleep(1000);
		driver.findElement(By.id("PermanencyDueDate")).clear();
		driver.findElement(By.id("PermanencyDueDate")).sendKeys("30/06/2018");
		//driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("algoaqui@yahoo.com.ar");
		CC.obligarclick(driver.findElement(By.id("Contact_nextBtn")));
		sleep(5000);
		assertTrue(driver.findElement(By.id("MethodSelectionPassport|0")).isEnabled());
	}
	
	@Test(groups = "Sales")
	public void TS95119_SalesCPQ_Nominacion_Extranjero_Verificar_Documento_Adjunto_Pasaporte(){
		String FilePath = "C:\\Users\\florangel\\Downloads\\mapache.jpg";
		SalesBase SB = new SalesBase(driver);
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", "1324567", "femenino");
		sleep(6000);
		driver.findElement(By.id("PermanencyDueDate")).sendKeys("30/06/2018");
		//driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("algoaqui@yahoo.com.ar");
		CC.obligarclick(driver.findElement(By.id("Contact_nextBtn")));
		sleep(5000);
		driver.findElement(By.id("MethodSelectionPassport|0")).findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();
		driver.findElement(By.id("ValidationMethod_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys(FilePath);
		sleep(1000);
		driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")).getText().toLowerCase().contains("documento de identidad superada");
	}
	
	@Test(groups = "Sales")
	public void TS95138_SalesCPQ_Nominacion_Argentino_Verificar_Formulario_De_Documentacion(){
		SalesBase SB = new SalesBase(driver);
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("DNI", "10000018", "femenino");
		sleep(6000);
		driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("algoaqui@yahoo.com.ar");
		CC.obligarclick(driver.findElement(By.id("Contact_nextBtn")));
		sleep(5000);
		SB.seleccionarMetodoValidacion("DOC");
		sleep(5000);
		assertTrue(driver.findElement(By.id("FileDocumentImage")).isEnabled());
	}
	
	@Test(groups = "Sales")
	public void TS95154_SalesCPQ_Nominacion_Extranjero_Verificar_Paso_De_Validacion_En_BL_Cliente_Nuevo_No_BL(){
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", "1324657", "femenino");
		sleep(6000);
		//assertTrue(!);
	}
	
	@Test(groups = "Sales")
	public void TS95157_SalesCPQ_Nominacion_Extranjero_Verificar_Solicitud_De_Ingreso_Pasaporte_Cliente_Nuevo(){
		String FilePath = "C:\\Users\\florangel\\Downloads\\mapache.jpg";
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		Random aleatorio = new Random(System.currentTimeMillis());
		aleatorio.setSeed(System.currentTimeMillis());
		int intAletorio = aleatorio.nextInt(8999999)+1000000;
		contact.searchContact("Pasaporte", Integer.toString(intAletorio), "femenino");
		sleep(6000);
		driver.findElement(By.id("PermanencyDueDate")).sendKeys("30/06/2018");
		//driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("algoaqui@yahoo.com.ar");
		CC.obligarclick(driver.findElement(By.id("Contact_nextBtn")));
		sleep(5000);
		driver.findElement(By.id("MethodSelectionPassport|0")).findElement(By.cssSelector(".slds-radio--faux.ng-scope")).click();
		driver.findElement(By.id("ValidationMethod_nextBtn")).click();
		sleep(5000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys(FilePath);
		sleep(1000);
		driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")).getText().toLowerCase().contains("documento de identidad superada");
	}
	
	@Test(groups = "Sales")
	public void TS94977_SalesCPQ_Nominacion_Argentino_Verificar_Formulario_De_Documentacion_Adjunto(){
		String FilePath = "C:\\Users\\florangel\\Downloads\\mapache.jpg";
		SalesBase SB = new SalesBase(driver);
		CustomerCare CC = new CustomerCare(driver);
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("DNI", "10000018", "femenino");
		sleep(6000);
		driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).sendKeys("algoaqui@yahoo.com.ar");
		CC.obligarclick(driver.findElement(By.id("Contact_nextBtn")));
		sleep(5000);
		SB.seleccionarMetodoValidacion("DOC");
		sleep(5000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys(FilePath);
		sleep(1000);
		driver.findElement(By.cssSelector(".message.description.ng-binding.ng-scope")).getText().toLowerCase().contains("documento de identidad superada");
	}
	
	@Test(groups = "Sales")
	public void TS95156_SalesCPQ_Nominacion_Extranjero_Verificar_Campo_Fecha_De_Permanencia_Cliente_Nuevo(){
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("Pasaporte", "1324657", "femenino");
		sleep(6000);
		assertTrue(driver.findElement(By.id("PermanencyDueDate")).isEnabled());
		assertTrue(driver.findElements(By.cssSelector(".slds-form-element__control.slds-input-has-icon.slds-input-has-icon--right")).get(2).findElement(By.tagName("label")).getText().toLowerCase().contains("plazo de permanencia"));
	}
	
	@Test(groups = "Sales")
	public void TS76058_Nominacion_Argentino_Verificar_solicitud_de_datos_para_la_nominacion() {
		sleep(5000);
		boolean a = false;
		List <WebElement> element = driver.findElements(By.cssSelector(".slds-form-element__label.vlc-slds-inline-control__label.ng-binding"));
		for (WebElement x : element) {
			if (x.getText().toLowerCase().contains("g�nero")) {
				a = true;
			}
		}
		Assert.assertTrue(driver.findElement(By.id("DocumentTypeSearch")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("DocumentInputSearch")).isEnabled());
		Assert.assertTrue(a);
	}
	
	
	@Test(groups = "Sales")
	public void TS76344_Nominacion_Argentino_Verificar_creacion_de_la_cuenta() {
		ContactSearch contact = new ContactSearch(driver);
		contact.searchContact("DNI", "10000019", "masculino");
		sleep(5000);
		driver.findElement(By.cssSelector(".slds-input.form-control.ng-pristine.ng-untouched.ng-valid.ng-empty")).click();
		sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"EmailSelectableItems\"]/div/ng-include/div/ng-form/div[1]/div[1]/input")).sendKeys("asdasd@gmail.com");
		driver.findElement(By.id("Contact_nextBtn")).click();
		sleep(7000);
		List <WebElement> valdni = driver.findElements(By.cssSelector(".slds-form-element__label.ng-binding.ng-scope"));
		for (WebElement x : valdni) {
			if (x.getText().toLowerCase().contains("validaci�n por documento de identidad")) {
				x.click();
				break;
			}
		}
		driver.findElement(By.id("ValidationMethod_nextBtn")).click();
		sleep(7000);
		driver.findElement(By.id("FileDocumentImage")).sendKeys("C:\\Users\\Nicolas\\Desktop\\descarga.jpg");
		driver.findElement(By.id("DocumentMethod_nextBtn")).click();
		sleep(7000);
		BasePage bp = new BasePage(driver);
		bp.setSimpleDropdown(driver.findElement(By.id("ImpositiveCondition")), "IVA Consumidor Final");
		bp.setSimpleDropdown(driver.findElement(By.id("State")), "Ciudad Aut�noma de Buenos Aires");
		driver.findElement(By.id("CityTypeAhead")).click();
		driver.findElement(By.id("CityTypeAhead")).sendKeys("f");
		sleep(3000);
		driver.findElement(By.cssSelector(".typeahead.dropdown-menu.ng-scope.am-fade.bottom-left")).click();
		/*driver.findElement(By.id("LegalStreetTypeAhead")).click();
		driver.findElement(By.id("LegalStreetTypeAhead")).sendKeys("f");
		sleep(3000);
		driver.findElement(By.xpath("//*[text() = '+ Nueva Calle']")).click();*/
		
	}
	
}
  	//DONDE APARECEN LAS LINEAS PREPAGAS DEL CLIENTE
	

	
	
	
		

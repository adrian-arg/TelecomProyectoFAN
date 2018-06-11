package Tests;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Function;

import Pages.BasePage;
import Pages.OM;
import Pages.SalesBase;
import Pages.setConexion;

public class OM_Flujos_Ruben extends TestBase {

	private WebDriver driver;
	private WebDriverWait wait;
	private FluentWait<WebDriver> fluentWait; // Fluent Wait
	private OM pageOm;

	@BeforeClass(alwaysRun = true)
	public void init() throws Exception {
		this.driver = setConexion.setupEze();
		wait = new WebDriverWait(driver, 20);
		fluentWait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		pageOm = new OM(driver);
		sleep(5000);
		// Usuario Victor OM
		login(driver, "https://crm--sit.cs14.my.salesforce.com/", "U585991", "Testa10k");
		sleep(5000);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		driver.switchTo().defaultContent();
		sleep(2000);
		BasePage bp = new BasePage(driver);
		bp.cajonDeAplicaciones("Sales");

		// click +
		sleep(5000);
		pageOm.clickMore();
		sleep(3000);

		// click en Ordenes
		pageOm.clickOnListTabs("Orders");
	}

	// @AfterClass(alwaysRun=true)
	public void tearDown() {
		sleep(2000);
		driver.quit();
		sleep(1000);
	}

	@Test(groups = "OM")
	public void F_Alta_de_linea_en_salesforce() {

		String accountName = "Buda OM";
		String plan = "Plan Prepago Nacional";

		// Crear Orden
		WebElement newOrderButton = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("new"))));
		newOrderButton.click();

		// Completar Formulario de Nueva Orden
		WebElement dateFormatButton = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("dateFormat"))));
		driver.findElement(By.id("accid")).sendKeys(accountName);
		dateFormatButton.click();
		Select statusSelect = new Select(driver.findElement(By.id("Status")));
		statusSelect.selectByVisibleText("Draft");
		driver.findElement(By.name("save")).click();

		// sleep(5000);
		// System.out.println(driver.findElement(By.id("Status_ileinner")).getText());
		// System.out.println(driver.findElement(By.xpath("//*[@id=\"Status_ileinner\"]")).getText());
		// System.out.println(driver.findElement(By.id("Status_ilecell")).getText());
		// System.out.println(driver.findElement(By.xpath("//*[@id=\"Status_ilecell\"]")).getText());
		// sleep(10000);

		// Click en CPQ
		sleep(3000);
		WebElement cpqButton = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("vlocity_cmt__cpq"))));
		String orderNumber = driver.findElement(By.cssSelector(".noSecondHeader.pageType")).getText();
		orderNumber = orderNumber.replace("Order ", "");
		System.out.println("Order #" + orderNumber);
		cpqButton.click();

		// Seleccionar Plan
		// DocumentationError #1
		// Los elementos no aparecen en la lista como dice la documentaci�n
		sleep(5000);
		SalesBase sb = new SalesBase(driver);
		sb.elegirplan(plan);

		// Ingreso Linea
		sleep(10000);
		WebElement planLineaButton = driver.findElement(
				By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[3]/div[10]/div/button"));
		planLineaButton.click();
		sleep(1000);
		WebElement configureLineaButton = driver.findElement(By.xpath(
				"//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[3]/div[10]/div/div/ul/li[3]/a/span"));
		configureLineaButton.click();
		sleep(1000);
		WebElement lineaInput = driver.findElement(
				By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[4]/div[1]/input"));
		lineaInput.sendKeys(pageOm.getRandomNumber(10));
		// sleep(1000);
		WebElement closeLineaInputButton = driver.findElement(By.xpath(
				"/html/body/div[1]/div[1]/ng-include/div/div[2]/div[2]/div[3]/div/div/ng-include/div/div[1]/div/button"));
		closeLineaInputButton.click();

		// Ingreso SIM Data
		sleep(10000);
		WebElement extendPlanButton = driver.findElement(
				By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[3]/div[1]/div[1]/button"));
		extendPlanButton.click();
		sleep(1000);
		WebElement simCardButton = driver.findElement(By.xpath(
				"//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/button"));
		simCardButton.click();
		sleep(1000);
		WebElement configureSimButton = driver.findElement(By.xpath(
				"//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/div/ul/li[3]/a/span"));
		configureSimButton.click();
		WebElement iccidInput = driver.findElement(
				By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[14]/div[1]/input"));
		iccidInput.sendKeys(pageOm.getRandomNumber(9));
		WebElement imsiInput = driver.findElement(
				By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[15]/div[1]/input"));
		imsiInput.sendKeys(pageOm.getRandomNumber(8));
		WebElement kiInput = driver.findElement(
				By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[16]/div[1]/input"));
		kiInput.sendKeys(pageOm.getRandomNumber(9));
		WebElement closeSimInputButton = driver.findElement(By.xpath(
				"/html/body/div[1]/div[1]/ng-include/div/div[2]/div[2]/div[3]/div/div/ng-include/div/div[1]/div/button"));
		closeSimInputButton.click();

		// Editar Record
		sleep(5000);
		WebElement viewRecordButton = driver.findElement(By.xpath("//*[@id=\"-import-btn\"]"));
		viewRecordButton.click();
		sleep(5000);
		WebElement editOrderButton = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("edit"))));
		editOrderButton.click();
		WebElement gestionElement = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("00Nc0000002IvyM"))));
		Select gestionSelect = new Select(gestionElement);
		gestionSelect.selectByVisibleText("Venta");
		driver.findElement(By.name("save")).click();

		// Descomponer Orden
		sleep(10000);
		driver.findElement(By.name("ta_submit_order")).click();

		// Popup Login
		sleep(20000);
		String currentWindowHandle = driver.getWindowHandle();
		pageOm.switchToPopup(currentWindowHandle);
		driver.findElement(By.id("hint_00Dc0000003w19T005c0000003FI6A")).click();

		// Plan de orquestacion
		driver.switchTo().window(currentWindowHandle);
		sleep(20000);
		pageOm.completarFlujoOrquestacion();

		WebElement viewOriginalOrderButton = driver
				.findElement(By.xpath("//*[@id=\"wholepage\"]/div/ng-include/div/div[1]/div/div[1]/a"));
		viewOriginalOrderButton.click();

		sleep(30000);

		currentWindowHandle = driver.getWindowHandle();
		pageOm.switchToPopup(currentWindowHandle);
		WebElement orderStatus = driver.findElement(By.id("Status_ileinner"));

		Assert.assertEquals(orderStatus.getText(), "Activated");

	}

	@Test(groups = "OM")
	public void F_Alta_de_linea_en_salesforce_Test_de_Esperas() {

		String accountName = "Buda OM";
		String plan = "Plan Prepago Nacional";

		// Crear Orden

		WebElement newOrderButton = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.name("new"));
			}
		});

		// WebElement newOrderButton = wait
		// .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("new"))));
		newOrderButton.click();

		// Completar Formulario de Nueva Orden
		WebElement dateFormatButton = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("dateFormat"))));
		driver.findElement(By.id("accid")).sendKeys(accountName);
		dateFormatButton.click();
		Select statusSelect = new Select(driver.findElement(By.id("Status")));
		statusSelect.selectByVisibleText("Draft");
		driver.findElement(By.name("save")).click();

		// Click en CPQ
		sleep(3000);
		WebElement cpqButton = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("vlocity_cmt__cpq"))));
		String orderNumber = driver.findElement(By.cssSelector(".noSecondHeader.pageType")).getText();
		orderNumber = orderNumber.replace("Order ", "");
		System.out.println("Order #" + orderNumber);
		cpqButton.click();

		// Seleccionar Plan
		// DocumentationError #1
		// Los elementos no aparecen en la lista como dice la documentaci�n
		sleep(5000);
		// WebElement newOrderButton = wait.until(new Function<WebDriver, WebElement>(){
		// public WebElement apply(WebDriver driver) {
		// return driver.findElement(By.name("new"));
		// }
		// });
		SalesBase sb = new SalesBase(driver);

		// driver.findElement(By.cssSelector(".slds-button.custom-view-dropdown-button.slds-button_neutral.slds-p-right_small.slds-picklist__label.cpq-base-header-picklist-label"))
		sb.elegirplan(plan);

		// Ingreso Linea
		sleep(10000);
		WebElement planLineaButton = driver.findElement(
				By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[3]/div[10]/div/button"));
		planLineaButton.click();
		sleep(1000);
		WebElement configureLineaButton = driver.findElement(By.xpath(
				"//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[3]/div[10]/div/div/ul/li[3]/a/span"));
		configureLineaButton.click();
		sleep(1000);
		WebElement lineaInput = driver.findElement(
				By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[4]/div[1]/input"));
		lineaInput.sendKeys(pageOm.getRandomNumber(10));
		// sleep(1000);
		WebElement closeLineaInputButton = driver.findElement(By.xpath(
				"/html/body/div[1]/div[1]/ng-include/div/div[2]/div[2]/div[3]/div/div/ng-include/div/div[1]/div/button"));
		closeLineaInputButton.click();

		// Ingreso SIM Data
		sleep(10000);
		WebElement extendPlanButton = driver.findElement(
				By.xpath("//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[3]/div[1]/div[1]/button"));
		extendPlanButton.click();
		sleep(1000);
		WebElement simCardButton = driver.findElement(By.xpath(
				"//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/button"));
		simCardButton.click();
		sleep(1000);
		WebElement configureSimButton = driver.findElement(By.xpath(
				"//*[@id=\"tab-default-1\"]/div[1]/ng-include/div/div/div/div[4]/div[2]/div/ng-include/div/div[2]/ng-include/div/div[1]/div/div[2]/div[11]/div[2]/div/ul/li[3]/a/span"));
		configureSimButton.click();
		WebElement iccidInput = driver.findElement(
				By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[14]/div[1]/input"));
		iccidInput.sendKeys(pageOm.getRandomNumber(9));
		WebElement imsiInput = driver.findElement(
				By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[15]/div[1]/input"));
		imsiInput.sendKeys(pageOm.getRandomNumber(8));
		WebElement kiInput = driver.findElement(
				By.xpath("//*[@id=\"js-cpq-product-cart-config-form\"]/div[1]/div/form/div[16]/div[1]/input"));
		kiInput.sendKeys(pageOm.getRandomNumber(9));
		WebElement closeSimInputButton = driver.findElement(By.xpath(
				"/html/body/div[1]/div[1]/ng-include/div/div[2]/div[2]/div[3]/div/div/ng-include/div/div[1]/div/button"));
		closeSimInputButton.click();

		// Editar Record
		sleep(5000);
		WebElement viewRecordButton = driver.findElement(By.xpath("//*[@id=\"-import-btn\"]"));
		viewRecordButton.click();
		sleep(5000);
		WebElement editOrderButton = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("edit"))));
		editOrderButton.click();
		WebElement gestionElement = wait
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("00Nc0000002IvyM"))));
		Select gestionSelect = new Select(gestionElement);
		gestionSelect.selectByVisibleText("Venta");
		driver.findElement(By.name("save")).click();

		// Descomponer Orden
		sleep(10000);
		driver.findElement(By.name("ta_submit_order")).click();

		// Popup Login
		sleep(20000);
		String currentWindowHandle = driver.getWindowHandle();
		pageOm.switchToPopup(currentWindowHandle);
		driver.findElement(By.id("hint_00Dc0000003w19T005c0000003FI6A")).click();

		// Plan de orquestacion
		driver.switchTo().window(currentWindowHandle);
		// sleep(20000);
		pageOm.completarFlujoOrquestacion();

		WebElement viewOriginalOrderButton = driver
				.findElement(By.xpath("//*[@id=\"wholepage\"]/div/ng-include/div/div[1]/div/div[1]/a"));
		viewOriginalOrderButton.click();

		sleep(30000);

		currentWindowHandle = driver.getWindowHandle();
		pageOm.switchToPopup(currentWindowHandle);
		WebElement orderStatus = driver.findElement(By.id("Status_ileinner"));

		Assert.assertEquals(orderStatus.getText(), "Activated");

	}

	//TS_CRM_OM_Gestion_Cambio_De_Numero utiliza FluentWaits
	@Test(groups = "OM")
	public void TS_CRM_OM_Gestion_Cambio_De_Numero() {
		
		String gestion = "Cambio de n�mero";

		pageOm.selectVistaByVisibleText("RubenOM-Activated");
		sleep(5000);
		WebElement accountName = driver.findElement(By.xpath("//*[@id=\"801c0000000KzlI_SALES_ACCOUNT_NAME\"]/a"));
		accountName.click();
		sleep(5000);
		pageOm.irAChangeToOrder();
		sleep(5000);

		// Ingresar Fecha Futura
		// driver.findElement(By.id("RequestDate")).sendKeys(pageOm.getFechaAvanzadaFormateada_MM_dd_yyyy());
		driver.findElement(By.id("RequestDate")).sendKeys("10-09-2018");
		sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"a1zc0000003XcLmAAK-1\"]/div[2]/div[3]/button")).click();
		
		//FluentWait por la demora incierta luego de ingresar la fecha
		pageOm.goToSimConfig();
		
		sleep(2000);
		pageOm.cambiarNumero(pageOm.getRandomNumber(10));

		// View Record
		sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"-import-btn\"]")).click();

		sleep(5000);
		pageOm.setGestionField(gestion);

		// Descomponer Orden
		sleep(5000);
		driver.findElement(By.name("ta_submit_order")).click();

		sleep(5000);
		pageOm.completarFlujoOrquestacion();

		/*
		 * Lanza error el �ltimo m�todo por problemas en el ambiente An unknown error
		 * has occurred. Please contact your system administrator for assistance.
		 */

	}

}
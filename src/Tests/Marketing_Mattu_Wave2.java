package Tests;

import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select; 
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.BasePage;
import Pages.CustomerCare;
import Pages.Marketing;
import Pages.setConexion;

public class Marketing_Mattu_Wave2 extends TestBase{
	
	private WebDriver driver;
	Marketing mMarketing;
	
	//-------------------------------------------------------------------------------------------------
	//@Befor&After
	@BeforeClass(alwaysRun=true)
	public void readySteady() throws Exception {
		this.driver = setConexion.setupEze();
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		loginMarketing(driver);
		try {Thread.sleep(5000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		if (!driver.findElement(By.id("tsidLabel")).getText().toLowerCase().equals("marketing")) {
			driver.findElement(By.id("tsidLabel")).click();
			WebElement wMenu = driver.findElement(By.id("tsid-menuItems"));
			List<WebElement> wMenuOptions = wMenu.findElements(By.tagName("a"));
			for (WebElement wAux:wMenuOptions) {
				if(wAux.getText().toLowerCase().equals("marketing")) {
					wAux.click();
				}
			}
		}
	}
	@BeforeMethod(alwaysRun=true)
	public void go() throws Exception {
		/*WebElement wMenu = driver.findElement(By.id("tabBar"));
		List<WebElement> wMenuElements = wMenu.findElements(By.tagName("li"));
		for (WebElement wAux : wMenuElements) {
			if(wAux.findElement(By.tagName("a")).getText().toLowerCase().equals("campa�as")) {
				wAux.click();
			}
		}*/
		driver.findElement(By.id("Campaign_Tab")).click();
	}
	/*@AfterMethod(alwaysRun=true)
	public void byeByeTab() {
		try {
			CustomerCare cCC = new CustomerCare(driver);
			WebElement wActiveTab = cCC.obtenerPesta�aActiva();
			if (!wActiveTab.findElement(By.className("tabText")).getText().toLowerCase().equals("club personal")) {
				mMarketing.closeActiveTab();
			}
		} catch (IndexOutOfBoundsException e) {
			//AllwaysEmpty
		}
	}*/
	//@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 1
	@Test(groups = {"Marketing", "Ola2", "GestionDeCampaniasEnMarketingCloudDeClubPersonal"})
	public void TS102035_Poder_crear_una_campa�a_en_MKT() {
		driver.findElement(By.className("pbButton")).findElement(By.tagName("input")).click();
		WebElement wSelect = driver.findElement(By.id("p3"));
		wSelect.click();
		List<WebElement> wOptions = wSelect.findElements(By.tagName("option"));
		boolean bComercialCampaigns = false;
		for (WebElement wAux : wOptions) {
			if (wAux.getText().toLowerCase().equals("commercial campaigns")) {
				bComercialCampaigns = true;
				wAux.click();
			}
		}
		Assert.assertTrue(bComercialCampaigns);
		WebElement wContinuar = driver.findElement(By.id("bottomButtonRow"));
		wContinuar.findElement(By.name("save")).click();
		
		WebElement wTable = driver.findElement(By.className("pbBody"));
		List<WebElement> wBody = wTable.findElements(By.tagName("tbody"));
		List<WebElement> wTd = wBody.get(0).findElements(By.tagName("td"));
		Assert.assertTrue(wTd.get(0).getText().contains("Nombre de la campa�a"));
		Assert.assertTrue(wTd.get(14).getText().contains("Channel Type"));
		Assert.assertTrue(wTd.get(18).getText().contains("Channel"));
		Assert.assertTrue(wTd.get(28).getText().equals("Descripci�n"));
		
		wTd = wBody.get(2).findElements(By.tagName("td"));
		Assert.assertTrue(wTd.get(0).getText().contains("Fecha de inicio"));
		Assert.assertTrue(wTd.get(2).getText().equals("Fecha final"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 2
	@SuppressWarnings("deprecation")
	@Test(groups = {"Marketing", "Ola2", "GestionDeCampaniasEnMarketingCloudDeClubPersonal"})
	public void TS102037_Creacion_exitosa_de_una_campa�a_en_MKT() {
		driver.findElement(By.className("pbButton")).findElement(By.tagName("input")).click();
		WebElement wSelect = driver.findElement(By.id("p3"));
		wSelect.click();
		List<WebElement> wOptions = wSelect.findElements(By.tagName("option"));
		boolean bComercialCampaigns = false;
		for (WebElement wAux : wOptions) {
			if (wAux.getText().toLowerCase().equals("commercial campaigns")) {
				bComercialCampaigns = true;
				wAux.click();
			}
		}
		Assert.assertTrue(bComercialCampaigns);
		WebElement wContinuar = driver.findElement(By.id("bottomButtonRow"));
		wContinuar.findElement(By.name("save")).click();
		
		WebElement wInfoCampania = driver.findElement(By.className("pbBody"));
		wInfoCampania.findElement(By.id("cpn1")).sendKeys("Nombre de la campania");
		driver.findElement(By.className("requiredBlock")).click();

		Select sSelectDropdown = new Select(driver.findElement(By.id("00Nc00000036par")));
		sSelectDropdown.selectByVisibleText("Captura");
		
		sSelectDropdown = new Select(driver.findElement(By.id("00Nc00000036paq")));
		sSelectDropdown.selectByVisibleText("Alta/Portin Nuevo Cliente  (Nuevo DNI/CUIT)");
		
		sSelectDropdown = new Select(driver.findElement(By.id("00Nc00000036pap")));
		sSelectDropdown.selectByVisibleText("N/A");
		
		sSelectDropdown = new Select(driver.findElement(By.id("00Nc00000036pas")));
		sSelectDropdown.selectByVisibleText("IN");
		
		sSelectDropdown = new Select(driver.findElement(By.id("00Nc00000036pat_unselected")));
		sSelectDropdown.selectByVisibleText("SMS");
		WebElement wAdd = driver.findElement(By.className("multiSelectPicklistCell"));
		List<WebElement> wOption = wAdd.findElements(By.tagName("a"));
		wOption.get(0).click();
		
		driver.findElement(By.id("00Nc00000036pao")).sendKeys("Campaign Objetive");
		
		java.util.Date dFechaCompleta = new Date();
	    String sFecha =  dFechaCompleta.getDate() + "/" + (dFechaCompleta.getMonth()+1 + "/" + dFechaCompleta.toString().substring(24, 28));
		driver.findElement(By.id("cpn5")).sendKeys(sFecha);
		
		List<WebElement> wGuardar = driver.findElement(By.id("bottomButtonRow")).findElements(By.tagName("input"));
		boolean bGuardar = false;
		for (WebElement wAux : wGuardar) {
			if (wAux.getAttribute("value").toLowerCase().equals("guardar")) {
				bGuardar = true;
				wAux.click();
			}
		}
		Assert.assertTrue(bGuardar);
		Assert.assertTrue(driver.findElement(By.id("cpn1_ileinner")).getText().equals("Nombre de la campania"));
		Assert.assertTrue(driver.findElement(By.id("00Nc00000036par_ileinner")).getText().equals("Captura"));
		Assert.assertTrue(driver.findElement(By.id("00Nc00000036paq_ileinner")).getText().equals("Alta/Portin Nuevo Cliente  (Nuevo DNI/CUIT)"));
		Assert.assertTrue(driver.findElement(By.id("00Nc00000036pap_ilecell")).getText().equals("N/A"));
		Assert.assertTrue(driver.findElement(By.id("00Nc00000036pas_ileinner")).getText().equals("IN"));
		Assert.assertTrue(driver.findElement(By.id("00Nc00000036pat_ileinner")).getText().equals("SMS"));
		Assert.assertTrue(driver.findElement(By.id("00Nc00000036pao_ileinner")).getText().equals("Campaign Objetive"));
		Assert.assertTrue(driver.findElement(By.id("cpn5_ileinner")).getText().equals("sFecha"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 3
	@Test(groups = {"Marketing", "Ola2", "ConfiguracionDeCampaniasEnMarketingCloudDeClubPersonal"})
	public void TS102092_Campo_Obligatorio_Canal_Alta_de_Campa�a() {
		driver.findElement(By.className("pbButton")).findElement(By.tagName("input")).click();
		WebElement wSelect = driver.findElement(By.id("p3"));
		wSelect.click();
		List<WebElement> wOptions = wSelect.findElements(By.tagName("option"));
		boolean bComercialCampaigns = false;
		for (WebElement wAux : wOptions) {
			if (wAux.getText().toLowerCase().equals("commercial campaigns")) {
				bComercialCampaigns = true;
				wAux.click();
			}
		}
		Assert.assertTrue(bComercialCampaigns);
		WebElement wContinuar = driver.findElement(By.id("bottomButtonRow"));
		wContinuar.findElement(By.name("save")).click();
		
		WebElement wInfoCampania = driver.findElement(By.className("pbBody"));
		wInfoCampania.findElement(By.id("cpn1")).sendKeys("Nombre de la campania");
		driver.findElement(By.className("requiredBlock")).click();

		Select sSelectDropdown = new Select(driver.findElement(By.id("00Nc00000036par")));
		sSelectDropdown.selectByVisibleText("Captura");
		
		List<WebElement> wTd = wInfoCampania.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		List<WebElement> wColumns = wTd.get(4).findElements(By.tagName("td"));
		WebElement wChannel = wColumns.get(3);
		Assert.assertTrue(wChannel.findElement(By.tagName("div")).getAttribute("class").equals("condRequiredInput"));
		Assert.assertTrue(wChannel.findElement(By.tagName("table")).getAttribute("class").equals("multiSelectPicklistTable"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 4
	@Test(groups = {"Marketing", "Ola2", "ConfiguracionDeCampaniasEnMarketingCloudDeClubPersonal"})
	public void TS102100_Campo_Obligatorio_Fecha_de_Vigencia_desde_Alta_de_Campa�a() {
		driver.findElement(By.className("pbButton")).findElement(By.tagName("input")).click();
		WebElement wSelect = driver.findElement(By.id("p3"));
		wSelect.click();
		List<WebElement> wOptions = wSelect.findElements(By.tagName("option"));
		boolean bComercialCampaigns = false;
		for (WebElement wAux : wOptions) {
			if (wAux.getText().toLowerCase().equals("commercial campaigns")) {
				bComercialCampaigns = true;
				wAux.click();
			}
		}
		Assert.assertTrue(bComercialCampaigns);
		WebElement wContinuar = driver.findElement(By.id("bottomButtonRow"));
		wContinuar.findElement(By.name("save")).click();
		
		WebElement wBody = driver.findElement(By.className("pbSubsection")).findElement(By.tagName("tbody"));
		List<WebElement> wTd = wBody.findElements(By.tagName("td"));
		Assert.assertTrue(wTd.get(1).findElement(By.tagName("div")).getAttribute("class").equals("requiredInput"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 5
	@Test(groups = {"Marketing", "Ola2", "ConfiguracionDeCampaniasEnMarketingCloudDeClubPersonal"})
	public void TS102072_Campo_Obligatorio_Nombre_de_Campa�a_Alta_de_Campa�a() {
		driver.findElement(By.className("pbButton")).findElement(By.tagName("input")).click();
		WebElement wSelect = driver.findElement(By.id("p3"));
		wSelect.click();
		List<WebElement> wOptions = wSelect.findElements(By.tagName("option"));
		boolean bComercialCampaigns = false;
		for (WebElement wAux : wOptions) {
			if (wAux.getText().toLowerCase().equals("commercial campaigns")) {
				bComercialCampaigns = true;
				wAux.click();
			}
		}
		Assert.assertTrue(bComercialCampaigns);
		WebElement wContinuar = driver.findElement(By.id("bottomButtonRow"));
		wContinuar.findElement(By.name("save")).click();
		
		WebElement wInfoCampania = driver.findElement(By.className("pbBody"));
		List<WebElement> wTd = wInfoCampania.findElement(By.tagName("tbody")).findElements(By.tagName("td"));
		WebElement wNombreDeCampania = wTd.get(1);
		Assert.assertTrue(wNombreDeCampania.findElement(By.tagName("div")).getAttribute("class").equals("requiredInput"));
		Assert.assertTrue(wNombreDeCampania.findElement(By.tagName("input")).getAttribute("id").equals("cpn1"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 6
	@Test(groups = {"Marketing", "Ola2", "ConfiguracionDeCampaniasEnMarketingCloudDeClubPersonal"})
	public void TS102076_Campo_Obligatorio_Objetivo_de_Campa�a_Alta_de_Campa�a() {
		driver.findElement(By.className("pbButton")).findElement(By.tagName("input")).click();
		WebElement wSelect = driver.findElement(By.id("p3"));
		wSelect.click();
		List<WebElement> wOptions = wSelect.findElements(By.tagName("option"));
		boolean bComercialCampaigns = false;
		for (WebElement wAux : wOptions) {
			if (wAux.getText().toLowerCase().equals("commercial campaigns")) {
				bComercialCampaigns = true;
				wAux.click();
			}
		}
		Assert.assertTrue(bComercialCampaigns);
		WebElement wContinuar = driver.findElement(By.id("bottomButtonRow"));
		wContinuar.findElement(By.name("save")).click();
		
		WebElement wInfoCampania = driver.findElement(By.className("pbBody"));
		List<WebElement> wTd = wInfoCampania.findElement(By.tagName("tbody")).findElements(By.tagName("td"));
		WebElement wNombreDeCampania = wTd.get(27);
		
		Assert.assertTrue(wNombreDeCampania.findElement(By.tagName("div")).getAttribute("class").equals("requiredInput"));
		Assert.assertTrue(wNombreDeCampania.findElement(By.tagName("textarea")).getAttribute("id").equals("00Nc00000036pao"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 7
	@Test(groups = {"Marketing", "Ola2", "ConfiguracionDeCampaniasEnMarketingCloudDeClubPersonal"})
	public void TS102080_Campo_Obligatorio_Tipo_de_Campa�a_Alta_de_Campa�a() {
		driver.findElement(By.className("pbButton")).findElement(By.tagName("input")).click();
		WebElement wSelect = driver.findElement(By.id("p3"));
		wSelect.click();
		List<WebElement> wOptions = wSelect.findElements(By.tagName("option"));
		boolean bComercialCampaigns = false;
		for (WebElement wAux : wOptions) {
			if (wAux.getText().toLowerCase().equals("commercial campaigns")) {
				bComercialCampaigns = true;
				wAux.click();
			}
		}
		Assert.assertTrue(bComercialCampaigns);
		WebElement wContinuar = driver.findElement(By.id("bottomButtonRow"));
		wContinuar.findElement(By.name("save")).click();
		
		WebElement wInfoCampania = driver.findElement(By.className("pbBody"));
		List<WebElement> wTd = wInfoCampania.findElement(By.tagName("tbody")).findElements(By.tagName("td"));
		WebElement wNombreDeCampania = wTd.get(3);
		
		Assert.assertTrue(wNombreDeCampania.findElement(By.tagName("div")).getAttribute("class").equals("requiredInput"));
		Assert.assertTrue(wNombreDeCampania.findElement(By.tagName("select")).getAttribute("id").equals("00Nc00000036par"));
	}
	
	//-------------------------------------------------------------------------------------------------
	//TCC = 8
	/*@Test(groups = {"Marketing", "Ola2", "ConfiguracionDeCampaniasEnMarketingCloudDeClubPersonal"})
	public void TS102110_Dependencia_de_Campos_Picklist_Alta_Campa�a() {
		driver.findElement(By.className("pbButton")).findElement(By.tagName("input")).click();
		WebElement wSelect = driver.findElement(By.id("p3"));
		wSelect.click();
		List<WebElement> wOptions = wSelect.findElements(By.tagName("option"));
		boolean bComercialCampaigns = false;
		for (WebElement wAux : wOptions) {
			if (wAux.getText().toLowerCase().equals("commercial campaigns")) {
				bComercialCampaigns = true;
				wAux.click();
			}
		}
		Assert.assertTrue(bComercialCampaigns);
		WebElement wContinuar = driver.findElement(By.id("bottomButtonRow"));
		wContinuar.findElement(By.name("save")).click();
		
		WebElement wInfoCampania = driver.findElement(By.className("pbBody"));
		List<WebElement> wTd = wInfoCampania.findElement(By.tagName("tbody")).findElements(By.tagName("td"));
		WebElement wNombreDeCampania = wTd.get(3);
	}*/
	
	//-------------------------------------------------------------------------------------------------
	//Abrir Pagina
	//@Test
	public void AbrirPagina() {
		
	}
	
	/*Fecha del sistema
	java.util.Date dFechaCompleta = new Date();
    boolean bAssert = false;
    String sFecha =  fechaCompleta.getDate()+"/"+(fechaCompleta.getMonth()+1);
	int iFechaActual = Integer.parseInt(wFechaDesde.findElement(By.className("slds-is-today")).findElement(By.tagName("span")).getText());
	*/
	
}
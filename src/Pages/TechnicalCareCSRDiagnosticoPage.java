package Pages;
import java.awt.Frame;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;  



public class TechnicalCareCSRDiagnosticoPage extends BasePage{
	
	final WebDriver driver;
	 
	
	@FindBy(className="slds-truncate")// escoger entre Diagnosticar y Desactivar
	private WebElement diagnosticar;
	
	@FindBy (className="slds-checkbox--faux")
	private WebElement inconveniente;
	
	@FindBy(id="IssueSelectStep_nextBtn")
	private WebElement continuar;
	
	@FindBy(css=".imgItemContainer.ng-scope")
	private  List<WebElement> preguntas;
	
	@FindBy(id="KnowledgeBaseResults_nextBtn")
	private WebElement nextBtn;
	
	@FindBy(id="NetworkCategory_nextBtn")
	private WebElement nextCategoria;
	
	@FindBy(xpath="//*[@class='imgItemContainer ng-scope']") //
	private List<WebElement> listaDeInconvenientes;
	
	@FindBy(id="KnowledgeBaseResults_prevBtn") // escoger entre coninuar o anterior
	private WebElement continuarOanterior;
	
	@FindBy (id="ClosedCaseKnowledgeBase")
	private WebElement casoGenerado;
	
	@FindBy(xpath=".//*[@id='SimilCaseInformation']/div/p/p[2]/span/strong[1]")
	private WebElement existCaso;
	
	@FindBy(id="ExistSimilCase_nextBtn") 
	private WebElement next;
	
	
	@FindBy(xpath="//*[@id='SimilCaseExistCommentUpdateMessage']/div/p/p[2]/span/strong")
	private WebElement numCaso;
	
	
	
	
	@FindBy(xpath=".//*[@id='DeregisterSpeechMessage']/div/p/p")
	private WebElement SpeechMessage;
	
	@FindBy(id="DeregisterSpeech_nextBtn") //Speech
	private WebElement continuaroAnterior2;
	
	@FindBy(id="AdressInput")
	private WebElement direccion;
	
	@FindBy(id="GeoMock")
	private WebElement buscarEnMapa;
	
	@FindBy(id="MobileUpdate")
	private WebElement actualizarEquipo;
	
	@FindBy(id="MobileModel")
	private WebElement buscarEquipo;
	
	@FindBy(id="HlrDeregisterUpdate")
	private WebElement consultarHLR;
	
	@FindBy(className="slds-form-element__label ng-binding ng-scope")
	private WebElement servicioFunciona;
		
	@FindBy(id="SmsServiceDiagnosis_prevBtn")
	private WebElement diagnosticodeServicioSMS;
		
	@FindBy(className="slds-text-body_regular")
	private WebElement menuOpcion;
			
	@FindBy(className="card-top")
	private WebElement planConTarjeta;
	
	@FindBy (css= ".slds-form-element__control.slds-input-has-icon.slds-input-has-icon--left")
	private WebElement search;
	
	

		


	public TechnicalCareCSRDiagnosticoPage(WebDriver driver){
		this.driver = driver;
			PageFactory.initElements(driver, this);

	}
	
	public void buscarServicio(String servicio)throws InterruptedException {
		sleep(8000);
		Accounts accPage = new Accounts(driver);
		driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".via-slds.addedValueServices-container")));
		sleep(8000);
		driver.findElement(By.cssSelector(".slds-form-element__control.slds-input-has-icon.slds-input-has-icon--left")).click();
		sleep(8000);
		driver.switchTo().activeElement().sendKeys(servicio);
		
	}

	public boolean elementExists(WebElement element) throws InterruptedException {
	    sleep(2000);
		    try {
 		      boolean isDisplayed = element.getSize().height > 0;
		       return isDisplayed;
		    }   catch (Exception ex) {
		         return false;
		  }
	}
	
	
	public void clickOpcionEnAsset(String Asset,String Opcion) {
	    boolean assetEncontrado=false,opcion=false;
	    Opcion=Opcion.toLowerCase();
	    Accounts accPage = new Accounts(driver);
	    sleep(8000);
	    driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".console-card.active")));

	    List<WebElement> asset=driver.findElements(By.cssSelector(".console-card.active"));
	    for(WebElement a:asset) {
	      
	      if(a.findElement(By.className("header-right")).getText().contains(Asset)) {
	        assetEncontrado=true;
	        List<WebElement> opciones=a.findElement(By.className("actions")).findElements(By.tagName("li"));
	        for(WebElement o:opciones) {
	          
	          if(o.findElement(By.tagName("span")).getText().toLowerCase().contains(Opcion)) {
	        	  sleep(3000);
	            o.findElement(By.tagName("span")).click();
	            opcion=true;
	            break;
	          }
	        }
	      }
	    if(assetEncontrado) break;
	    }
	    if(!assetEncontrado) System.out.println("Asset No encontrado");
	    if(!opcion) System.out.println("asset encontrado, Opcion No encontrada");
	  }
	
	
	public void verDetalles() {
		sleep (7000);
		BasePage cambioFrameByID=new BasePage();
		 driver.switchTo().frame(cambioFrameByID.getFrameForElement(driver, By.cssSelector(".slds-grid.slds-wrap.slds-card.slds-m-bottom--small.slds-p-around--medium")));
		    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+driver.findElement(By.cssSelector(".slds-button.slds-button--brand")).getLocation().y+" )");
		    sleep(7000);
		    driver.findElement(By.cssSelector(".slds-button.slds-button--brand")).click();
	}
	
	public void clickDiagnosticarServicio(String servicio) {
	      sleep(5000);
	      Accounts accPage = new Accounts(driver);
	      driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".slds-card__body.cards-container")));
	      List<WebElement> tablas=driver.findElements(By.cssSelector(".slds-card__body.cards-container"));
	      List<WebElement> servicios=tablas.get(selectionTable(servicio)).findElements(By.xpath("//table//tbody//tr"));
	      for(WebElement service : servicios) {
	        System.out.println(service.getText());
	        if(service.getText().toLowerCase().contains(servicio.toLowerCase())) {
	          ((JavascriptExecutor)driver).executeScript("window.scrollTo(0," + service.getLocation().y+")");
	          sleep(1000);
	          service.findElement(By.className("slds-cell-shrink")).click();
	          sleep(2000);
	          try {
	             sleep(2000);
	           List<WebElement> actions=  service.findElement(By.className("slds-cell-shrink")).findElements(By.xpath("//*[@class='dropdown__list']//li"));
	        for (WebElement opt : actions) {
	         if (opt.isDisplayed()) {
	          opt.click();
	          break;
	            }
	          }
	        }
	          catch(org.openqa.selenium.ElementNotVisibleException e) {
	            System.out.println("No es posible seleccionar el boton diagnosticar... Verifique!!");
	            e.printStackTrace();

	          }
	        }
	      } 
	  }
	public void clickDiagnosticarServicio(String servicio, String subServicio, boolean clickOnSubServicio) {
	    sleep(5000);
	    boolean sEncontrado=true;
	    Accounts accPage = new Accounts(driver);
	    driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".slds-card__body.cards-container")));
	    List<WebElement> tablas=driver.findElements(By.cssSelector(".slds-card__body.cards-container"));
	    //Listado de opciones
	    List<WebElement> servicios=tablas.get(0).findElements(By.xpath("//table//tbody//tr"));
	    for(WebElement S:servicios) {
	      if(S.getText().toLowerCase().contains(servicio.toLowerCase())) {
	        S.findElement(By.className("addedValueServices-arrowWrapper")).click();
	        sleep(2000);
	        sEncontrado=false;
	        break;
	      }
	    }
	    if(sEncontrado) { System.out.println("Servicio no encontrado."); return;}
	    
	    List<WebElement> sServicios=tablas.get(selectionTable(servicio)).findElements(By.xpath("//table//tbody//tr"));
	        for(WebElement service:sServicios) {
	          if(service.getText().toLowerCase().contains(subServicio.toLowerCase()) ) {
	            ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+service.getLocation().y+")");
	            sleep(1000);
	            service.findElement(By.className("slds-cell-shrink")).click();
	            sleep(2000);
	            if (clickOnSubServicio) {
	              
	            try {
	               sleep(2000);
	               List<WebElement> actions= service.findElement(By.className("slds-cell-shrink")).findElements(By.xpath("//*[@class='dropdown__list']//li"));
	            for (WebElement opt : actions) {
	             if (opt.isDisplayed()) {
	              opt.click();
	              break;
	                }
	              }
	            }
	              catch(org.openqa.selenium.ElementNotVisibleException e) {
	                System.out.println("No es posible seleccionar el boton diagnosticar... Verifique!!");
	                e.printStackTrace();

	              }
	            }
	          }
	          }    
	  }
	
	public boolean validarOpcionesXSubServicio(String subServicio ) {
	    List<WebElement> tablas=driver.findElements(By.cssSelector(".slds-card__body.cards-container"));
	
		 List<WebElement> sServicios=tablas.get(0).findElements(By.xpath("//table//tbody//tr"));
	        for(WebElement service:sServicios) {
	          if(service.getText().toLowerCase().contains(subServicio.toLowerCase()) ) {
	            ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+service.getLocation().y+")");
	               sleep(2000);
	               List<WebElement> actions= service.findElement(By.className("slds-cell-shrink")).findElements(By.xpath("//*[@class='dropdown__list']//li"));
	            for (WebElement opt : actions) {
	             if (opt.isDisplayed()) {
	            	 System.out.println("*********"+opt.getText());
	            	 return true;
	                }
	              }
	          	}
	        }
	        return false;
	  }

	public boolean validarOpcionesXServicio(String servicio ) {
	    List<WebElement> tablas=driver.findElements(By.cssSelector(".slds-card__body.cards-container"));
		 List<WebElement> sServicios=tablas.get(0).findElements(By.xpath("//table//tbody//tr"));
	        for(WebElement service:sServicios) {
	          if(service.isDisplayed() && service.getText().toLowerCase().contains(servicio.toLowerCase()) ) {
	            ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+service.getLocation().y+")");
	               sleep(2000);
	               return true;
	              }
	          	}
	        
	        return false;
	  }
	
	public boolean validarEstado(String servicio ) {
	     sleep(5000);
	        Accounts accPage = new Accounts(driver);
	        driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".slds-card__body.cards-container")));
	        List<WebElement> tablas=driver.findElements(By.cssSelector(".slds-card__body.cards-container"));
	        //Listado de opciones
	        List<WebElement> servicios=tablas.get(0).findElements(By.xpath("//table//tbody//tr"));
	        for(WebElement service : servicios) {
	          if(service.getText().toLowerCase().contains(servicio.toLowerCase())) {
	                ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+service.getLocation().y+")");
	            service.findElement(By.className("addedValueServices-arrowWrapper")).click();
	            sleep(2000);
	            List<WebElement> sServicios=tablas.get(1).findElements(By.xpath("//*[@class='dropdown__list']//li"));
	            for(WebElement subService : sServicios) {
	              if (subService.isDisplayed()) {
	               // System.out.println(subService.getText());
	          }
	            if(!service.getText().toLowerCase().contains("Activo") && service.isDisplayed() ) {
	                return false;
	 
	          }
	        }
	    }
	      return true;
	}
			return false;
	}
	/*public boolean validarEstado(String servicio ) {
		 sleep(5000);
		    Accounts accPage = new Accounts(driver);
		    driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".slds-card__body.cards-container")));
		    List<WebElement> tablas=driver.findElements(By.cssSelector(".slds-card__body.cards-container"));
		    //Listado de opciones
		    List<WebElement> servicios=tablas.get(0).findElements(By.xpath("//table//tbody//tr"));
		    for(WebElement service : servicios) {
		      if(service.getText().toLowerCase().contains(servicio.toLowerCase())) {
		            ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+service.getLocation().y+")");
		        service.findElement(By.className("addedValueServices-arrowWrapper")).click();
		        sleep(2000);
		        List<WebElement> sServicios=tablas.get(selectionTable(servicio)).findElements(By.xpath("//*[@class='addedValueServices']/tr"));
		        for(WebElement subService : sServicios) {
		        	if (subService.isDisplayed()) {
		        		System.out.println(subService.getText());
					}
		        	
		   //       if(!service.getText().toLowerCase().contains("Activo") && service.isDisplayed() ) {
		//        	  return false;
		  //        }   
		      }
		    }
	  }
			return true;
}*/
		           
	
	
	public void selectionInconvenient(String inconvenientName) {
		sleep(4000);
	      driver.switchTo().frame(getFrameForElement(driver, By.id("IssueSelectStep")));
	      sleep(2000);
	      for (WebElement opt : getlistaDeInconvenientes()) {
	        if (opt.getText().equalsIgnoreCase(inconvenientName)) {
	          opt.click();
	          sleep(2000);
	          break;
	        }
	    
	      }	     
	  }
	

	public boolean validarInconveniente(String inconvenientName) {
			driver.switchTo().frame(getFrameForElement(driver, By.id("IssueSelectStep")));
			for (WebElement opt : getlistaDeInconvenientes()) {
				if(opt.getText().contains(inconvenientName)) {
		        	return true;
				}
		}
			return false;
	}	
	
	public void continuar() {
		scrollToElement(getContinuar());
		sleep(2000);
		continuar.click();
		sleep(5000);
		
	}
	
	/**
	 * Seleciona cuenta despues de diagnosticar
	 * @param opcion
	 * @author Quelys
	 */
	public void seleccionarRespuesta(String opcion) {
	    Accounts accPage = new Accounts(driver);
	      driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".imgItemContainer.ng-scope")));
	      	List<WebElement> preguntas=driver.findElements(By.cssSelector(".imgItemContainer.ng-scope"));
	      		for(WebElement p:preguntas) {
	      			if(p.getText().toLowerCase().contains(opcion)) {
	      				p.click();
	      					sleep(2000);
	      							return;
	      							
	      						}
	      		}
	      		
	 }
	
	public void categoriaRed(String categoria) {
		  sleep(5000);
		Accounts accPage = new Accounts(driver);
			 driver.switchTo().frame(accPage.getFrameForElement(driver, By.cssSelector(".imgItemContainer.ng-scope")));
	    		List<WebElement> opc=driver.findElements(By.cssSelector(".imgItemContainer.ng-scope"));
	    			for (WebElement o:opc) {
	    				if(o.getText().toLowerCase().contains(categoria)) {
	    	      				o.click();
	    	      					sleep(2000);
	    	      						return;
	    	      					
	    			}
	    		
	}
	}
	
	
	public String verificarCaso() throws InterruptedException {
		String caso="";
		if(elementExists(existCaso)) {
			caso=existCaso.getText();
			getNext().click();
			 
			}
			else {	
				//agregar lo que falta de las pantallas
				caso=numCaso.getText();
			}
			
		return caso;			
	}
	
	

	
	
	public  boolean verificarOpciones(WebElement element,String data){
	    Select select = new Select(element);
	    List<WebElement> options = select.getOptions();

	    for (WebElement opt : options) {
	        if (opt.getText().toLowerCase().contains(data.toLowerCase())) {
	         return true;
	        }
	    }
	    return false;
	}
	
		
			    	
	public WebElement getPlanConTarjeta() {
		return planConTarjeta;
	}

	
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor)driver)
	        .executeScript("arguments[0].scrollIntoView();", element);
	  }

	public WebElement  getmenuOpcion(int opcion) {
	      List<WebElement>menuOptions=driver.findElements(By.className("slds-text-body_regular"));
	            return menuOptions.get(opcion);
		
	}
	
	
	public WebDriver getDriver() {
		return driver;
	}


	public WebElement getDiagnosticar() {
		return diagnosticar;
	}


	public WebElement getInconveniente() {
		return inconveniente;
	}


	public WebElement getContinuar() {
		return continuar;
	}


	public WebElement getNextBtn() {
		return nextBtn;
	}




	public List<WebElement> getPreguntas() {
		return preguntas;
	}

	public List< WebElement> getlistaDeInconvenientes() {
		return listaDeInconvenientes;
	}


	public WebElement getNextCategoria() {
		return nextCategoria;
	}

	public WebElement getContinuarOanterior() {
		return continuarOanterior;
	}


	public WebElement getCasoGenerado() {
		return casoGenerado;
	}


	public WebElement getExistCaso() {
		return existCaso;
	}


	public WebElement getContinuar2() {
		return continuaroAnterior2;
	}


	public WebElement getDireccion() {
		return direccion;
	}


	public WebElement getBuscarEnMapa() {
		return buscarEnMapa;
	}


	public WebElement getActualizarEquipo() {
		return actualizarEquipo;
	}


	public WebElement getBuscarEquipo() {
		return buscarEquipo;
	}


	public WebElement getConsultarHLR() {
		return consultarHLR;
	}


	public WebElement getServicioFunciona() {
		return servicioFunciona;
	}


	public WebElement getMenuOpcion() {
		return menuOpcion;
	}


	
	public WebElement getNumeroCaso() {
		return numCaso;
	}


	public WebElement getNext() {
		return next;
	}
	
	private int selectionTable(String serviceName) {
		switch (serviceName.toUpperCase()) {
		case "SMS":
			return 1;
		case "VOZ":
			return 2;
		default:
			return 0;
		}
	}

	public WebElement getSearch() {
		return search;
	}
	

	}
	
		
	
	
	


package Tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import Pages.remoteScriptExec;

public class Reportes {
	
	remoteScriptExec rsePage = new remoteScriptExec();
	String sDateFormat = "dd/MM/yyyy HH:mm:ss";
	String sDateFormatBorn = "dd/MM/yyyy";
	SimpleDateFormat sdfDateFormat;
	List<String> sListOfFiles = null;
	
	//Before & AfterClass
	
	@BeforeClass(alwaysRun=true)
	public void init() throws MalformedURLException, UnknownHostException, FileNotFoundException, IOException, JSchException, SftpException {
		rsePage.FTPConnection();
		System.out.println("Connection stablished.");
		rsePage.FTPDownload();
		System.out.println("Download completed.");
	}
	
	//@AfterMethod(alwaysRun=true)
	public void cleanUp() {
		for(String sAux : sListOfFiles) {
			rsePage.deleteFile(sAux);
		}
	}
	
	@AfterClass(alwaysRun=true)
	public void quit() throws IOException {
		rsePage.deleteAllFiles();
	}
	
	//Tests
	
	//Test #1
	@Test
	public void TS125398_CRM_Interfaz_LCRM_Individuo() throws IOException, ParseException {
		String sName = "_INDIVIDUO_";
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIDIndividuo = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDIndividuo, 18));
			Assert.assertFalse(sIDIndividuo.isEmpty());
			
			String sNumeroTelefono = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroTelefono, 40));
			
			String sCodUsuarioAlta = lsAux.get(2);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioAlta, 18));
			
			String sDepartamentoEmpresa = lsAux.get(3);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sDepartamentoEmpresa, 80));
			
			String sMarcaNoLlamar = lsAux.get(4);
			Integer.parseInt(sMarcaNoLlamar);
			Assert.assertTrue(sMarcaNoLlamar.equals("1") || sMarcaNoLlamar.equals("0"));
			
			String sEmail = lsAux.get(5);
			if (!sEmail.isEmpty()) sEmail.contains("@");
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEmail, 80));
			
			String sMarcaEnviarMail = lsAux.get(6);
			Integer.parseInt(sMarcaEnviarMail);
			Assert.assertTrue(sMarcaEnviarMail.equals("1") || sMarcaEnviarMail.equals("0"));
			
			String sMarcaEnviarFax = lsAux.get(7);
			Integer.parseInt(sMarcaEnviarFax);
			Assert.assertTrue(sMarcaEnviarFax.equals("1") || sMarcaEnviarFax.equals("0"));
			
			String sNumeroTelefonoCasa = lsAux.get(8);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroTelefonoCasa, 40));
			
			String sCodUsuarioMod = lsAux.get(9);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sFechaUltInteraccion = lsAux.get(10);
			if (!sFechaUltInteraccion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaUltInteraccion);
			}
			
			String sNumeroMovil = lsAux.get(11);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroMovil, 40));
			
			String sNombre = lsAux.get(12);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNombre, 40));
			
			String sApellido = lsAux.get(13);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sApellido, 80));
			
			String sTituloCortesia = lsAux.get(14);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTituloCortesia, 128));
			
			String sFechaAltaContacto = lsAux.get(15);
			if (!sFechaAltaContacto.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaAltaContacto);
			}
			
			String sEdad = lsAux.get(16);
			Integer.parseInt(sEdad);
			
			String sIdContacto = lsAux.get(17);
			if (!sIdContacto.isEmpty()) {
				Integer.parseInt(sIdContacto);
			}
			
			String sTipoDocumento = lsAux.get(19);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoDocumento, 255));
			
			String sNumeroDocumento = lsAux.get(20);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroDocumento, 30));
			
			String sCUIL = lsAux.get(18);
			if (!sCUIL.isEmpty()) {
				rsePage.verifyCUITCUIT(sCUIL, sNumeroDocumento);
			}
			
			String sLicenciaDeConducir = lsAux.get(21);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sLicenciaDeConducir, 50));
			
			String sCodEmpleado = lsAux.get(22);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodEmpleado, 255));
			
			String sCodPersonaContacto = lsAux.get(23);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodPersonaContacto, 18));
			
			String sMarcaFraude = lsAux.get(24);
			Integer.parseInt(sMarcaFraude);
			Assert.assertTrue(sMarcaFraude.equals("1") || sMarcaFraude.equals("0"));
			
			String sGenero = lsAux.get(25);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sGenero, 255));
			
			String sEstado = lsAux.get(26);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstado, 255));
			
			String sFechaNacimiento = lsAux.get(27);
			if (!sFechaNacimiento.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormatBorn);
				sdfDateFormat.parse(sFechaNacimiento);
			}
			
			String sOcupacion = lsAux.get(28);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sOcupacion, 50));
			
			String sEstadoLaboral = lsAux.get(29);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoLaboral, 255));
			
			String sNivelSatisfaccion = lsAux.get(30);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNivelSatisfaccion, 255));
			
			String sFechaCreaAudit = lsAux.get(31);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(32);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sFechaMod = lsAux.get(33);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sFechaCreacion = lsAux.get(34);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sOrigenContacto = lsAux.get(35);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sOrigenContacto, 40));
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #2
	@Test
	public void TS125399_CRM_Interfaz_LCRM_IndividuoCuentaCliente() throws ParseException, IOException {
		String sName = "_INDIVIDUOCUENTACLIENTE_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIDIndividuo = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDIndividuo, 18));
			Assert.assertFalse(sIDIndividuo.isEmpty());
			
			String sIDCuentaCliente = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDCuentaCliente, 255));
			
			String sFechaCreaAudit = lsAux.get(2);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(3);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sRol = lsAux.get(4);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sRol, 18));
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #3
	@Test
	public void TS125400_CRM_Interfaz_LCRM_CuentaCliente() throws ParseException, IOException {
		String sName = "_CUENTACLIENTE_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIdCuentaCliente = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdCuentaCliente, 18));
			Assert.assertFalse(sIdCuentaCliente.isEmpty());
			
			String sCodCuenta = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuenta, 255));
			Assert.assertFalse(sCodCuenta.isEmpty());
			
			String sMarcaFraude = lsAux.get(2);
			Integer.parseInt(sMarcaFraude);
			Assert.assertTrue(sMarcaFraude.equals("1") || sMarcaFraude.equals("0"));
			
			String sMarcaPartner = lsAux.get(3);
			Integer.parseInt(sMarcaPartner);
			Assert.assertTrue(sMarcaPartner.equals("1") || sMarcaPartner.equals("0"));
			
			String sMarcaPrensa = lsAux.get(4);
			Integer.parseInt(sMarcaPrensa);
			Assert.assertTrue(sMarcaPrensa.equals("1") || sMarcaPrensa.equals("0"));
			
			String sMarcaNoNominado = lsAux.get(5);
			Integer.parseInt(sMarcaNoNominado);
			Assert.assertTrue(sMarcaNoNominado.equals("1") || sMarcaNoNominado.equals("0"));
			
			String sMarcaVIP = lsAux.get(6);
			Integer.parseInt(sMarcaVIP);
			Assert.assertTrue(sMarcaVIP.equals("1") || sMarcaVIP.equals("0"));
			
			String sValorCuenta = lsAux.get(7);
			Double.parseDouble(sValorCuenta);
			
			String sIngresoBrutoAnual = lsAux.get(8);
			Double.parseDouble(sIngresoBrutoAnual);
			
			String sIngresoNetoAnual = lsAux.get(9);
			Double.parseDouble(sIngresoNetoAnual);
			
			String sFechaFundacion = lsAux.get(10);
			if (!sFechaFundacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaFundacion);
			}
			
			String sNumeroFax = lsAux.get(11);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroFax, 40));
			
			String sRazonSocial = lsAux.get(12);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sRazonSocial, 255));
			Assert.assertFalse(sRazonSocial.isEmpty());
			
			String sNumeroTelefonoPpal = lsAux.get(13);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroTelefonoPpal, 40));
			
			String sNumeroTelefonoAlternativo = lsAux.get(14);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroTelefonoAlternativo, 40));
			
			String sEstado = lsAux.get(15);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstado, 255));
			
			String sCUIT = lsAux.get(16);
			if (!sCUIT.isEmpty()) {
				rsePage.verifyCUITCUIT(sCUIT, sCUIT.split("-")[1]);
			}
			
			String sMarcaJubilado = lsAux.get(17);
			Integer.parseInt(sMarcaJubilado);
			Assert.assertTrue(sMarcaJubilado.equals("1") || sMarcaJubilado.equals("0"));
			
			String sFechaModEmail = lsAux.get(18);
			if (!sFechaModEmail.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModEmail);
			}
			
			String sCantidadEmpleados = lsAux.get(19);
			Integer.parseInt(sCantidadEmpleados);
			
			String sIndustria = lsAux.get(20);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIndustria, 40));
			
			String sEmail = lsAux.get(21);
			if (!sEmail.isEmpty()) sEmail.contains("@");
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEmail, 255));
			
			String sSegmentoNivel1 = lsAux.get(22);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sSegmentoNivel1, 255));
			
			String sSegmentoNivel2 = lsAux.get(23);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sSegmentoNivel2, 255));
			
			String sCodPersonaContacto = lsAux.get(24);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodPersonaContacto, 18));
			
			String sCodCuentaOrigen = lsAux.get(25);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuentaOrigen, 18));
			
			String sFechaCreaAudit = lsAux.get(26);
			Assert.assertFalse(sIdCuentaCliente.isEmpty());
			sdfDateFormat = new SimpleDateFormat(sDateFormat);
			sdfDateFormat.parse(sFechaCreaAudit);
			
			String sCodUsuariosAlta = lsAux.get(27);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuariosAlta, 18));
			
			String sFechaModAudit = lsAux.get(28);
			Assert.assertFalse(sFechaModAudit.isEmpty());
			sdfDateFormat = new SimpleDateFormat(sDateFormat);
			sdfDateFormat.parse(sFechaModAudit);
			
			String sCodCuentaPadre = lsAux.get(29);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuentaPadre, 18));
			
			String sCodCliente = lsAux.get(30);
			Integer.parseInt(sCodCliente);
			
			String sFechaMod = lsAux.get(31);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sCodUsuarioMod = lsAux.get(32);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sFechaCreacion = lsAux.get(33);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sRiesgoCrediticio = lsAux.get(34);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sRiesgoCrediticio, 18));
			
			String sTipoCuenta = lsAux.get(35);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoCuenta, 80));
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #4
	@Test
	public void TS125401_CRM_Interfaz_LCRM_IndividuoCuentaFacturacion() throws ParseException, IOException {
		String sName = "_INDIVIDUOCUENTAFACTURACION_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIdIndividuo = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdIndividuo, 18));
			Assert.assertFalse(sIdIndividuo.isEmpty());
			
			String sIdCuentaFacturacion = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdCuentaFacturacion, 255));
			
			String sFechaCreaAudit = lsAux.get(2);
			Assert.assertFalse(sFechaCreaAudit.isEmpty());
			sdfDateFormat = new SimpleDateFormat(sDateFormat);
			sdfDateFormat.parse(sFechaCreaAudit);
			
			String sFechaModAudit = lsAux.get(3);
			Assert.assertFalse(sFechaModAudit.isEmpty());
			sdfDateFormat = new SimpleDateFormat(sDateFormat);
			sdfDateFormat.parse(sFechaModAudit);
			
			String sRol = lsAux.get(4);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sRol, 18));
			Assert.assertTrue(sRol.equalsIgnoreCase("Referente de Pago")); //Ask. It says 'Campo Fijo'
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #5
	@Test
	public void TS125402_CRM_Interfaz_LCRM_CuentaFacturacion() throws ParseException, IOException {
		String sName = "_CUENTAFACTURACION_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sCodCuenta = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuenta, 255));
			Assert.assertFalse(sCodCuenta.isEmpty());
			
			String sIdCuentaFacturacion = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdCuentaFacturacion, 18));
			Assert.assertFalse(sIdCuentaFacturacion.isEmpty());
			
			String sMarcaDeudaFinanciada = lsAux.get(2);
			Assert.assertFalse(sMarcaDeudaFinanciada.isEmpty());
			Integer.parseInt(sMarcaDeudaFinanciada);
			Assert.assertTrue(sMarcaDeudaFinanciada.equals("1") || sMarcaDeudaFinanciada.equals("0"));
			
			String sMarcaFOL = lsAux.get(3);
			Assert.assertFalse(sMarcaFOL.isEmpty());
			Integer.parseInt(sMarcaFOL);
			Assert.assertTrue(sMarcaFOL.equals("1") || sMarcaFOL.equals("0"));
			
			String sMarcaCompraFinanciada = lsAux.get(4);
			Assert.assertFalse(sMarcaCompraFinanciada.isEmpty());
			Integer.parseInt(sMarcaCompraFinanciada);
			Assert.assertTrue(sMarcaCompraFinanciada.equals("1") || sMarcaCompraFinanciada.equals("0"));
			
			String sMarcaDebito = lsAux.get(5);
			Assert.assertFalse(sMarcaDebito.isEmpty());
			Integer.parseInt(sMarcaDebito);
			Assert.assertTrue(sMarcaDebito.equals("1") || sMarcaDebito.equals("0"));
			
			String sDireccionEmail = lsAux.get(6);
			Assert.assertFalse(sDireccionEmail.isEmpty());
			Assert.assertTrue(sDireccionEmail.contains("@"));
			Assert.assertTrue(rsePage.verifyTextMaxSize(sDireccionEmail, 80));
			
			String sCodCuentaFacturacion = lsAux.get(7);
			Assert.assertFalse(sCodCuentaFacturacion.isEmpty());
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuentaFacturacion, 5));
			Integer.parseInt(sCodCuentaFacturacion);
			
			String sCodCuentaPadre = lsAux.get(8);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuentaPadre, 18));
			
			String sCodCuentaOrigen = lsAux.get(9);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodCuentaOrigen, 18));
			
			String sIdMedioDePago = lsAux.get(10);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdMedioDePago, 18));
			
			String sCodUsuarioAlta = lsAux.get(11);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioAlta, 18));
			
			String sCodUsuarioMod = lsAux.get(12);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sCodCliente = lsAux.get(13);
			Integer.parseInt(sCodCliente);
			
			String sNumeroTelefonoPpal = lsAux.get(14);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroTelefonoPpal, 40));
			
			String sNumeroTelefonoAlternativo = lsAux.get(15);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroTelefonoAlternativo, 40));
			
			String sCicloFacturacion = lsAux.get(16);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCicloFacturacion, 255));
			
			String sIDDireccionFacturacion = lsAux.get(17);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDDireccionFacturacion, 255));
			
			String sIDDireccionEnvio = lsAux.get(18);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDDireccionEnvio, 255));
			
			String sMarcaSocio = lsAux.get(19);
			Integer.parseInt(sMarcaSocio);
			Assert.assertTrue(sMarcaSocio.equals("1") || sMarcaSocio.equals("0"));
			
			String sMarcaMorosidad = lsAux.get(20);
			Integer.parseInt(sMarcaMorosidad);
			Assert.assertTrue(sMarcaMorosidad.equals("1") || sMarcaMorosidad.equals("0"));
			
			String sFechaModEmail = lsAux.get(21);
			if (!sFechaModEmail.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModEmail);
			}
			
			String sPuntosAcumulados = lsAux.get(22);
			Integer.parseInt(sPuntosAcumulados);
			
			String sCategoriaSocio = lsAux.get(23);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCategoriaSocio, 255));
			
			String sTipoCuenta = lsAux.get(24);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoCuenta, 40));
			
			String sMetodoEntrega = lsAux.get(25);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sMetodoEntrega, 255));
			
			String sFrecuenciaFacturacion = lsAux.get(26);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sFrecuenciaFacturacion, 255));
			
			String sSLA = lsAux.get(27);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sSLA, 255));
			
			String sTipoRegistro = lsAux.get(28);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoRegistro, 18));
			
			String sTipoDePago = lsAux.get(29);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoDePago, 255));
			
			String sRiesgoCrediticio = lsAux.get(30);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sRiesgoCrediticio, 18));
			
			String sContactoPrimario = lsAux.get(31);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sContactoPrimario, 255));
			
			String sFechaCreaAudit = lsAux.get(32);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(33);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sFechaCreacion = lsAux.get(34);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sFechaMod = lsAux.get(35);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sEstado = lsAux.get(36);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstado, 255));
			
			String sPreferenciasContacto = lsAux.get(37);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sPreferenciasContacto, 255));
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #6
	@Test
	public void TS125403_CRM_Interfaz_LCRM_ProductoAquirido() throws ParseException, IOException {
		String sName = "_PRODUCTOADQUIRIDO_";
		
		//rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIDProductoAdquirido = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDProductoAdquirido, 18));
			Assert.assertFalse(sIDProductoAdquirido.isEmpty());
			
			String sIDCuentaFacturacion = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDCuentaFacturacion, 18));
			Assert.assertFalse(sIDCuentaFacturacion.isEmpty());
			
			String sIDProducto = lsAux.get(2);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDProducto, 18));
			Assert.assertFalse(sIDProducto.isEmpty());
			
			String sMarcaProdCompetencia = lsAux.get(3);
			Integer.parseInt(sMarcaProdCompetencia);
			
			String sFechaInstalacion = lsAux.get(4);
			if (!sFechaInstalacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaInstalacion);
			}
			
			String sCodUsuarioAlta = lsAux.get(5);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioAlta, 18));
			
			String sCodUsuarioMod = lsAux.get(6);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sCantUnidadesProdAdquiridas = lsAux.get(7);
			Double.parseDouble(sCantUnidadesProdAdquiridas);
			
			String sTipoProducto = lsAux.get(8);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoProducto, 255));
			
			String sEstadoProductoAdq = lsAux.get(9);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoProductoAdq, 40));
			
			String sNombreProducto = lsAux.get(10);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNombreProducto, 255));
			
			String sNumeroSerie = lsAux.get(11);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroSerie, 80));
			
			String sEstadoEnRed = lsAux.get(12);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoEnRed, 255));
			
			String sSubestadoProductoAdq = lsAux.get(13);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sSubestadoProductoAdq, 255));
			
			String sNumeroLinea = lsAux.get(14);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroLinea, 10));
			
			String sFechaActivaci�n = lsAux.get(15);
			if (!sFechaActivaci�n.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaActivaci�n);
			}
			
			String sEstadoProvisionamiento = lsAux.get(16);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoProvisionamiento, 255));
			
			String sFamiliaProducto = lsAux.get(17);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sFamiliaProducto, 255));
			
			String sCodProductoPadre = lsAux.get(18);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodProductoPadre, 255));
			
			String sFechaCreacion = lsAux.get(19);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sFechaCreaAudit = lsAux.get(20);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(21);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sIMEI = lsAux.get(22);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIMEI, 15));
			
			String sIdOrdenItem = lsAux.get(23);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdOrdenItem, 18));
			Assert.assertFalse(sIdOrdenItem.isEmpty());
			
			String sCodProductoRaiz = lsAux.get(24);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodProductoRaiz, 255));
			
			String sICCID = lsAux.get(25);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sICCID, 255));
			
			String sNMU = lsAux.get(26);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNMU, 200));
			
			String sGamaEquipo = lsAux.get(27);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sGamaEquipo, 200));
			
			String sCodProducto = lsAux.get(28);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodProducto, 510));
			
			String sIMSI = lsAux.get(29);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIMSI, 512));
			
			String sCodSuscripcion = lsAux.get(30);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodSuscripcion, 72));
			
			String sIDProductoAdquiridoReferente = lsAux.get(31);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDProductoAdquiridoReferente, 510));
			
			String sMarcaMigracion = lsAux.get(32);
			if (!sMarcaMigracion.isEmpty()) {
				Integer.parseInt(sMarcaMigracion);
				Assert.assertTrue(sMarcaMigracion.equals("1") || sMarcaMigracion.equals("0"));
			}
			
			String sMarcaGarantiaInvalida = lsAux.get(33);
			if (!sMarcaGarantiaInvalida.isEmpty()) {
				Integer.parseInt(sMarcaGarantiaInvalida);
				Assert.assertTrue(sMarcaGarantiaInvalida.equals("1") || sMarcaGarantiaInvalida.equals("0"));
			}
			
			String sNivelJerarquia = lsAux.get(34);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNivelJerarquia, 255));
		}
		
		sListOfFiles = sFiles;
	}
	
	//Test #7
	@Test
	public void TS125404_CRM_Interfaz_LCRM_Orden() throws ParseException, IOException {
		String sName = "_ORDEN_";
		
		rsePage.checkName(sName);
		
		List<List<String>> sList = new ArrayList<List<String>>();
		
		List<String> sFiles = rsePage.findFiles(sName);
		
		for (String sAux : sFiles) {
			sList.add(rsePage.readTxt(sAux));
		}
		
		for (List<String> lsAux : sList) {
			String sIDOrden = lsAux.get(0);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDOrden, 18));
			Assert.assertFalse(sIDOrden.isEmpty());
			
			String sNumeroOrden = lsAux.get(1);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroOrden, 30));
			Assert.assertFalse(sNumeroOrden.isEmpty());
			
			String sIdCuentaFacturacion = lsAux.get(2);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIdCuentaFacturacion, 18));
			Assert.assertFalse(sIdCuentaFacturacion.isEmpty());
			
			String sTipoGestion = lsAux.get(3);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoGestion, 255));
			
			String sCodUsuarioAlta = lsAux.get(4);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioAlta, 18));
			
			String sEstadoOrden = lsAux.get(5);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoOrden, 40));
			
			String sMetodoEntrega = lsAux.get(6);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sMetodoEntrega, 255));
			
			String sPrecioTotalOrden = lsAux.get(7);
			Double.parseDouble(sPrecioTotalOrden);
			
			String sFechaInicioOrden = lsAux.get(8);
			if (!sFechaInicioOrden.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaInicioOrden);
			}
			
			String sFechaFinOrden = lsAux.get(9);
			if (!sFechaFinOrden.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaFinOrden);
			}
			
			String sFechaVenta = lsAux.get(10);
			if (!sFechaVenta.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaVenta);
			}
			
			String sFechaEntrega = lsAux.get(11);
			if (!sFechaEntrega.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaEntrega);
			}
			
			String sFechaModificaci�n = lsAux.get(12);
			if (!sFechaModificaci�n.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModificaci�n);
			}
			
			String sCodUsuarioMod = lsAux.get(13);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodUsuarioMod, 18));
			
			String sEstadoTrackeo = lsAux.get(14);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoTrackeo, 255));
			
			String sFechaCreaAudit = lsAux.get(15);
			if (!sFechaCreaAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreaAudit);
			}
			
			String sFechaModAudit = lsAux.get(16);
			if (!sFechaModAudit.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaModAudit);
			}
			
			String sFechaCreacion = lsAux.get(17);
			if (!sFechaCreacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaCreacion);
			}
			
			String sFechaMod = lsAux.get(18);
			if (!sFechaMod.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaMod);
			}
			
			String sNumeroComprobante = lsAux.get(19);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroComprobante, 255));
			
			String sNumeroPreFactura = lsAux.get(20);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sNumeroPreFactura, 255));
			
			String sCodMetodoPago = lsAux.get(21);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodMetodoPago, 255));
			
			String sCodBanco = lsAux.get(22);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodBanco, 10));
			
			String sCodTarjeta = lsAux.get(23);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCodTarjeta, 10));
			
			String sCFT = lsAux.get(24);
			Double.parseDouble(sCFT);
			
			String sCanalOrigen = lsAux.get(25);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sCanalOrigen, 255));
			
			String sEstadoProvisionamientoOrden = lsAux.get(26);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sEstadoProvisionamientoOrden, 255));
			
			String sFechaActivacion = lsAux.get(27);
			if (!sFechaActivacion.isEmpty()) {
				sdfDateFormat = new SimpleDateFormat(sDateFormat);
				sdfDateFormat.parse(sFechaActivacion);
			}
			
			String sIDPuntoDeVenta = lsAux.get(28);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sIDPuntoDeVenta, 18));
			
			String sTipoSubgestion = lsAux.get(29);
			Assert.assertTrue(rsePage.verifyTextMaxSize(sTipoSubgestion, 255));
		}
		
		sListOfFiles = sFiles;
	}

}
package com.smarteinc.api.tests;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.smarteinc.objects.PEApiInputObjects;
import com.smarteinc.objects.PIPLInputObjects;
import com.smarteinc.peapi.PEAPI_Library;
import static com.smarteinc.utility.APIUtility.*;

import com.smarteinc.utility.APIUtility;
import com.smarteinc.utility.ConnectHttps;
import com.smarteinc.utility.ExcelUtility;

public class DirectDialTest {

	Properties prop = new Properties();
	PEApiInputObjects obj;

	private static Logger logger = initializeLogger(new MatchBackTest());

	public static Logger initializeLogger(Object classObject) {
		System.setProperty("logDirectory", "..\\com.smarteinc.automation\\logs");
		logger = LogManager.getLogger(classObject.getClass().getSimpleName());
		return logger;
	}

	@BeforeTest
	public void beforeTest() throws IOException, IOException {
		obj = new PEApiInputObjects();
		String propFileName = "/Config/Tranalyzer.properties";

		InputStream inputStream = MatchBackTest.class.getResourceAsStream(propFileName);

		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {

			}
		}

		// ConnectHttps.execute();
	}

	@Test
	public void DirectdialTest1() throws Exception {
		PEAPI_Library peLib = new PEAPI_Library();
		Response res = null;
		XSSFSheet sheet = null;
		String file = MatchBackTest.class.getResource("/TestData/DirectDial/Salesforce_B_EMEA-6.xlsx").getPath();

		try {
			String token = "Bearer DG_QA_DA_23ff65c7-53a2-4d0b-abe7-fe41dac3dba1";
			String URL = "https://ddapi.smarteinc.com/directdial/fetch";
			sheet = ExcelUtility.openSpreadSheet(file, "6");
			int lastRow = sheet.getLastRowNum();

			Map<String, String> hm = new HashMap<String, String>();
			PEAPI_Library tran = new PEAPI_Library();

			PIPLInputObjects obj = new PIPLInputObjects();
			for (int row = 1; row <= lastRow; row++) {
				// Input fields
				System.out.println("row" + row);
				String pcGuid = ExcelUtility.getCellData(sheet, row, "pc_guid");
				String rcGuid = ExcelUtility.getCellData(sheet, row, "rc_guid");
				String linkedInURL = ExcelUtility.getCellData(sheet, row, "linkedin_url");
				String action = ExcelUtility.getCellData(sheet, row, "action");
				String key_name = ExcelUtility.getCellData(sheet, row, "key_name");

				String strBody = getJsonBody("pc_guid", pcGuid, "rc_guid", rcGuid, "linkedin_url", linkedInURL,
						"action", action, "key_name", key_name);

				RestAssured.useRelaxedHTTPSValidation();
				res = peLib.getResponseFordirectDial(strBody, URL, token);

				// System.out.println(res.asString());
				List<String> lstOutput = Arrays.asList("errorMessage", "direct_dial_flag", "direct_dial_1",
						"direct_dial_2");

				tran.updateExcelCell(lstOutput, sheet, row, res);

			}

			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;

			try {

				ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) 
		{
			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;
			ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			ex.printStackTrace();
		}}
		
	@Test
	public void DirectdialTest2() throws Exception {
		PEAPI_Library peLib = new PEAPI_Library();
		Response res = null;
		XSSFSheet sheet = null;
		String file = MatchBackTest.class.getResource("/TestData/DirectDial/Salesforce_B_EMEA-7.xlsx").getPath();

		try {
			String token = "Bearer DG_QA_DA_23ff65c7-53a2-4d0b-abe7-fe41dac3dba1";
			String URL = "https://ddapi.smarteinc.com/directdial/fetch";
			sheet = ExcelUtility.openSpreadSheet(file, "7");
			int lastRow = sheet.getLastRowNum();

			Map<String, String> hm = new HashMap<String, String>();
			PEAPI_Library tran = new PEAPI_Library();

			PIPLInputObjects obj = new PIPLInputObjects();
			for (int row = 1; row <= lastRow; row++) {
				// Input fields
				System.out.println("row" + row);
				String pcGuid = ExcelUtility.getCellData(sheet, row, "pc_guid");
				String rcGuid = ExcelUtility.getCellData(sheet, row, "rc_guid");
				String linkedInURL = ExcelUtility.getCellData(sheet, row, "linkedin_url");
				String action = ExcelUtility.getCellData(sheet, row, "action");
				String key_name = ExcelUtility.getCellData(sheet, row, "key_name");

				String strBody = getJsonBody("pc_guid", pcGuid, "rc_guid", rcGuid, "linkedin_url", linkedInURL,
						"action", action, "key_name", key_name);

				RestAssured.useRelaxedHTTPSValidation();
				res = peLib.getResponseFordirectDial(strBody, URL, token);

				// System.out.println(res.asString());
				List<String> lstOutput = Arrays.asList("errorMessage", "direct_dial_flag", "direct_dial_1",
						"direct_dial_2");

				tran.updateExcelCell(lstOutput, sheet, row, res);

			}

			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;

			try {

				ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;
			ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			ex.printStackTrace();
		}}
	
	@Test
	public void DirectdialTest3() throws Exception {
		PEAPI_Library peLib = new PEAPI_Library();
		Response res = null;
		XSSFSheet sheet = null;
		String file = MatchBackTest.class.getResource("/TestData/DirectDial/Salesforce_B_EMEA-8.xlsx").getPath();

		try {
			String token = "Bearer DG_QA_DA_23ff65c7-53a2-4d0b-abe7-fe41dac3dba1";
			String URL = "https://ddapi.smarteinc.com/directdial/fetch";
			sheet = ExcelUtility.openSpreadSheet(file, "8");
			int lastRow = sheet.getLastRowNum();

			Map<String, String> hm = new HashMap<String, String>();
			PEAPI_Library tran = new PEAPI_Library();

			PIPLInputObjects obj = new PIPLInputObjects();
			for (int row = 1; row <= lastRow; row++) {
				// Input fields
				System.out.println("row" + row);
				String pcGuid = ExcelUtility.getCellData(sheet, row, "pc_guid");
				String rcGuid = ExcelUtility.getCellData(sheet, row, "rc_guid");
				String linkedInURL = ExcelUtility.getCellData(sheet, row, "linkedin_url");
				String action = ExcelUtility.getCellData(sheet, row, "action");
				String key_name = ExcelUtility.getCellData(sheet, row, "key_name");

				String strBody = getJsonBody("pc_guid", pcGuid, "rc_guid", rcGuid, "linkedin_url", linkedInURL,
						"action", action, "key_name", key_name);

				RestAssured.useRelaxedHTTPSValidation();
				res = peLib.getResponseFordirectDial(strBody, URL, token);

				// System.out.println(res.asString());
				List<String> lstOutput = Arrays.asList("errorMessage", "direct_dial_flag", "direct_dial_1",
						"direct_dial_2");

				tran.updateExcelCell(lstOutput, sheet, row, res);

			}

			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;

			try {

				ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;
			ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			ex.printStackTrace();
		}}
	
	@Test
	public void DirectdialTest4() throws Exception {
		PEAPI_Library peLib = new PEAPI_Library();
		Response res = null;
		XSSFSheet sheet = null;
		String file = MatchBackTest.class.getResource("/TestData/DirectDial/Salesforce_B_EMEA-9.xlsx").getPath();

		try {
			String token = "Bearer DG_QA_DA_23ff65c7-53a2-4d0b-abe7-fe41dac3dba1";
			String URL = "https://ddapi.smarteinc.com/directdial/fetch";
			sheet = ExcelUtility.openSpreadSheet(file, "9");
			int lastRow = sheet.getLastRowNum();

			Map<String, String> hm = new HashMap<String, String>();
			PEAPI_Library tran = new PEAPI_Library();

			PIPLInputObjects obj = new PIPLInputObjects();
			for (int row = 1; row <= lastRow; row++) {
				// Input fields
				System.out.println("row" + row);
				String pcGuid = ExcelUtility.getCellData(sheet, row, "pc_guid");
				String rcGuid = ExcelUtility.getCellData(sheet, row, "rc_guid");
				String linkedInURL = ExcelUtility.getCellData(sheet, row, "linkedin_url");
				String action = ExcelUtility.getCellData(sheet, row, "action");
				String key_name = ExcelUtility.getCellData(sheet, row, "key_name");

				String strBody = getJsonBody("pc_guid", pcGuid, "rc_guid", rcGuid, "linkedin_url", linkedInURL,
						"action", action, "key_name", key_name);

				RestAssured.useRelaxedHTTPSValidation();
				res = peLib.getResponseFordirectDial(strBody, URL, token);

				// System.out.println(res.asString());
				List<String> lstOutput = Arrays.asList("errorMessage", "direct_dial_flag", "direct_dial_1",
						"direct_dial_2");

				tran.updateExcelCell(lstOutput, sheet, row, res);

			}

			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;

			try {

				ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;
			ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			ex.printStackTrace();
		}}
	
	@Test
	public void DirectdialTest5() throws Exception {
		PEAPI_Library peLib = new PEAPI_Library();
		Response res = null;
		XSSFSheet sheet = null;
		String file = MatchBackTest.class.getResource("/TestData/DirectDial/Salesforce_B_EMEA-10.xlsx").getPath();

		try {
			String token = "Bearer DG_QA_DA_23ff65c7-53a2-4d0b-abe7-fe41dac3dba1";
			String URL = "https://ddapi.smarteinc.com/directdial/fetch";
			sheet = ExcelUtility.openSpreadSheet(file, "10");
			int lastRow = sheet.getLastRowNum();

			Map<String, String> hm = new HashMap<String, String>();
			PEAPI_Library tran = new PEAPI_Library();

			PIPLInputObjects obj = new PIPLInputObjects();
			for (int row = 1; row <= lastRow; row++) {
				// Input fields
				System.out.println("row" + row);
				String pcGuid = ExcelUtility.getCellData(sheet, row, "pc_guid");
				String rcGuid = ExcelUtility.getCellData(sheet, row, "rc_guid");
				String linkedInURL = ExcelUtility.getCellData(sheet, row, "linkedin_url");
				String action = ExcelUtility.getCellData(sheet, row, "action");
				String key_name = ExcelUtility.getCellData(sheet, row, "key_name");

				String strBody = getJsonBody("pc_guid", pcGuid, "rc_guid", rcGuid, "linkedin_url", linkedInURL,
						"action", action, "key_name", key_name);

				RestAssured.useRelaxedHTTPSValidation();
				res = peLib.getResponseFordirectDial(strBody, URL, token);

				// System.out.println(res.asString());
				List<String> lstOutput = Arrays.asList("errorMessage", "direct_dial_flag", "direct_dial_1",
						"direct_dial_2");

				tran.updateExcelCell(lstOutput, sheet, row, res);

			}

			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;

			try {

				ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;
			ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			ex.printStackTrace();
		}}
	
	//@Test
	public void DirectdialTest6() throws Exception {
		PEAPI_Library peLib = new PEAPI_Library();
		Response res = null;
		XSSFSheet sheet = null;
		String file = MatchBackTest.class.getResource("/TestData/DirectDial/Salesforce_Input-13.2.xlsx").getPath();

		try {
			String token = "Bearer DG_QA_DA_23ff65c7-53a2-4d0b-abe7-fe41dac3dba1";
			String URL = "https://ddapi.smarteinc.com/directdial/fetch";
			sheet = ExcelUtility.openSpreadSheet(file, "13.2");
			int lastRow = sheet.getLastRowNum();

			Map<String, String> hm = new HashMap<String, String>();
			PEAPI_Library tran = new PEAPI_Library();

			PIPLInputObjects obj = new PIPLInputObjects();
			for (int row = 1; row <= lastRow; row++) {
				// Input fields
				System.out.println("row" + row);
				String pcGuid = ExcelUtility.getCellData(sheet, row, "pc_guid");
				String rcGuid = ExcelUtility.getCellData(sheet, row, "rc_guid");
				String linkedInURL = ExcelUtility.getCellData(sheet, row, "linkedin_url");
				String action = ExcelUtility.getCellData(sheet, row, "action");
				String key_name = ExcelUtility.getCellData(sheet, row, "key_name");

				String strBody = getJsonBody("pc_guid", pcGuid, "rc_guid", rcGuid, "linkedin_url", linkedInURL,
						"action", action, "key_name", key_name);

				RestAssured.useRelaxedHTTPSValidation();
				res = peLib.getResponseFordirectDial(strBody, URL, token);

				// System.out.println(res.asString());
				List<String> lstOutput = Arrays.asList("errorMessage", "direct_dial_flag", "direct_dial_1",
						"direct_dial_2");

				tran.updateExcelCell(lstOutput, sheet, row, res);

			}

			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;

			try {

				ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;
			ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			ex.printStackTrace();
		}}
	
	//@Test
	public void DirectdialTest7() throws Exception {
		PEAPI_Library peLib = new PEAPI_Library();
		Response res = null;
		XSSFSheet sheet = null;
		String file = MatchBackTest.class.getResource("/TestData/DirectDial/Salesforce_Input-11.xlsx").getPath();

		try {
			String token = "Bearer DG_QA_DA_23ff65c7-53a2-4d0b-abe7-fe41dac3dba1";
			String URL = "https://ddapi.smarteinc.com/directdial/fetch";
			sheet = ExcelUtility.openSpreadSheet(file, "11");
			int lastRow = sheet.getLastRowNum();

			Map<String, String> hm = new HashMap<String, String>();
			PEAPI_Library tran = new PEAPI_Library();

			PIPLInputObjects obj = new PIPLInputObjects();
			for (int row = 1; row <= lastRow; row++) {
				// Input fields
				System.out.println("row" + row);
				String pcGuid = ExcelUtility.getCellData(sheet, row, "pc_guid");
				String rcGuid = ExcelUtility.getCellData(sheet, row, "rc_guid");
				String linkedInURL = ExcelUtility.getCellData(sheet, row, "linkedin_url");
				String action = ExcelUtility.getCellData(sheet, row, "action");
				String key_name = ExcelUtility.getCellData(sheet, row, "key_name");

				String strBody = getJsonBody("pc_guid", pcGuid, "rc_guid", rcGuid, "linkedin_url", linkedInURL,
						"action", action, "key_name", key_name);

				RestAssured.useRelaxedHTTPSValidation();
				res = peLib.getResponseFordirectDial(strBody, URL, token);

				// System.out.println(res.asString());
				List<String> lstOutput = Arrays.asList("errorMessage", "direct_dial_flag", "direct_dial_1",
						"direct_dial_2");

				tran.updateExcelCell(lstOutput, sheet, row, res);

			}

			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;

			try {

				ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;
			ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			ex.printStackTrace();
		}}
	
	
	//@Test
	public void Netfactor() throws Exception {
		PEAPI_Library peLib = new PEAPI_Library();
		Response res = null;
		XSSFSheet sheet = null;

		List<String> lstStatus = new ArrayList<String>();

		String file = MatchBackTest.class.getResource("/TestData/DirectDial/remaining_IPs.xlsx").getPath();
		try {
			String token = "Basic YXBpQHNtYXJ0ZS5jb206S2lUdTZCQUw=";
			String URL = "https://ip2c.bombora.com/v1/ip2company/";
			
			sheet = ExcelUtility.openSpreadSheet(file, "Sheet1");
			int lastRow = sheet.getLastRowNum();

			Map<String, String> hm = new HashMap<String, String>();
			PEAPI_Library tran = new PEAPI_Library();

			PIPLInputObjects obj = new PIPLInputObjects();

			for (int row = 1; row <= lastRow; row++) {
				// Input fields

				System.out.println("Row " + row);
				String ip = ExcelUtility.getCellData(sheet, row, "IP");
				//String key = "Basic YXBpQHNtYXJ0ZS5jb206S2lUdTZCQUw=";

				RestAssured.baseURI= "https://ip2c.bombora.com/v1/ip2company/"; 
				
				res = given().queryParam("ip", ip)
                        .header("Authorization", "Basic YXBpQHNtYXJ0ZS5jb206S2lUdTZCQUw=")                                        
                        .contentType("application/x-www-form-urlencoded")
                        .when().get();
			 

			List<String> lstPIPLOutput = new ArrayList<String>();	
			lstPIPLOutput.add(res.asString());
		
			//RestAssured.useRelaxedHTTPSValidation();
			//res = peLib.getResponseForNetfactor(res, URL, token);
			
			tran.updateExcelCell(lstPIPLOutput, sheet, row);
			}

			// }

			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;

			try {

				ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		catch (Exception ex) {
			System.out.println(res.asString());
			System.out.println(ex.getMessage());
			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;
			ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			Assert.fail("PE API script has failed");
		}

	}
	
	//@Test
	public void Kickfire() throws Exception {
		PEAPI_Library peLib = new PEAPI_Library();
		Response res = null;
		XSSFSheet sheet = null;

		List<String> lstStatus = new ArrayList<String>();

		String file = MatchBackTest.class.getResource("/TestData/DirectDial/remaining_IPs-1.xlsx").getPath();
		try {
			//String token = "Basic YXBpQHNtYXJ0ZS5jb206S2lUdTZCQUw=";
			String URL = "https://api.kickfire.com/v3/company:(all)?ip=99.76.145.124&key=839949551af0c844";
			
			sheet = ExcelUtility.openSpreadSheet(file, "Sheet1");
			int lastRow = sheet.getLastRowNum();

			Map<String, String> hm = new HashMap<String, String>();
			PEAPI_Library tran = new PEAPI_Library();

			PIPLInputObjects obj = new PIPLInputObjects();

			for (int row = 1; row <= lastRow; row++) {
				// Input fields

				System.out.println("Row " + row);
				String ip = ExcelUtility.getCellData(sheet, row, "IP");
				//String key = "Basic YXBpQHNtYXJ0ZS5jb206S2lUdTZCQUw=";

				RestAssured.baseURI= "https://ip2c.bombora.com/v1/ip2company/"; 
				
				res = given().queryParam("ip", ip)
						.queryParam("key", "eoswg33aawbcv2")
                        .contentType("application/x-www-form-urlencoded")
                        .when().get();
			 

			List<String> lstPIPLOutput = new ArrayList<String>();	
			lstPIPLOutput.add(res.asString());
		
			//RestAssured.useRelaxedHTTPSValidation();
			//res = peLib.getResponseForNetfactor(res, URL, token);
			
			tran.updateExcelCell(lstPIPLOutput, sheet, row);
			}

			// }

			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;

			try {

				ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		catch (Exception ex) {
			System.out.println(res.asString());
			System.out.println(ex.getMessage());
			String fileName = file.substring(file.lastIndexOf('/') + 1);
			String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;
			ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
			Assert.fail("PE API script has failed");
		}

	}

	//@Test
    public void IPRegistry() throws Exception {
        PEAPI_Library peLib = new PEAPI_Library();
        Response res = null;
        XSSFSheet sheet = null;

        List<String> lstStatus = new ArrayList<String>();

        String file = MatchBackTest.class.getResource("/TestData/DirectDial/remaining_IPs.xlsx").getPath();
        try {
            sheet = ExcelUtility.openSpreadSheet(file, "Sheet1");
            int lastRow = sheet.getLastRowNum();
            Map<String, String> hm = new HashMap<String, String>();
            PEAPI_Library tran = new PEAPI_Library();
            PIPLInputObjects obj = new PIPLInputObjects();
            for (int row = 1; row <= lastRow; row++) {
                // Input fields
                //String URL = "https://api.ipregistry.co/76.98.133.219";
                System.out.println("Row " + row);
                String ip = ExcelUtility.getCellData(sheet, row, "IP");        
                String url = "https://api.ipregistry.co/" + ip ;                
                RestAssured.baseURI = url; 

                res = given().queryParam("ip", ip).
                		queryParam("key", "eoswg33aawbcv2").
                		queryParam("hostname", "true")
                        .contentType("application/x-www-form-urlencoded").
                        when().
                        get();

                List<String> lstPIPLOutput = new ArrayList<String>();
                lstPIPLOutput.add(res.asString()); 

                // RestAssured.useRelaxedHTTPSValidation();
                // res = peLib.getResponseForNetfactor(res, URL, token);

                tran.updateExcelCell(lstPIPLOutput, sheet, row);
            }
            // }

            String fileName = file.substring(file.lastIndexOf('/') + 1);
            String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;

            try {
                 ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        catch (Exception ex) {
            System.out.println(res.asString());
            System.out.println(ex.getMessage());
            String fileName = file.substring(file.lastIndexOf('/') + 1);
            String newFile = file.substring(0, file.lastIndexOf('/')) + "/New" + fileName;
            ExcelUtility.saveChangesToAnother(newFile, sheet.getWorkbook());
            Assert.fail("PE API script has failed");
        }
    }
	
}

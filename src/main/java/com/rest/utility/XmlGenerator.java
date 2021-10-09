package com.rest.utility;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XmlGenerator {

	public static final String xmlFilePath = System.getProperty("user.dir") + "/TestSuite.xml";

	public static void main(String[] args) {

		try {
			String excelPath = "ExcelFiles/TestSuite.xlsx";
			String sheetName = "TestScenarious";
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			// root element
			Element root = document.createElement("suite");
			root.setAttribute("name", "Progressive Automation - Rest Services");
			document.appendChild(root);
			int rowCount = ExcelLib.getRowCount(excelPath, sheetName);
			for (int i = 1; i < rowCount + 1; i++) {
				if (ExcelLib.getCellValue(excelPath, sheetName, i, 5).equalsIgnoreCase("Y")) {
					String name = ExcelLib.getCellValue(excelPath, sheetName, i, 0) + "_"
							+ ExcelLib.getCellValue(excelPath, sheetName, i, 1);
					String className = ExcelLib.getCellValue(excelPath, sheetName, i, 4);
					root.appendChild(getCompany(document, name, className));
				}
			}

			// create the xml file
			// transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
			DOMSource domSource = new DOMSource(document);
			// StreamResult streamResult = new StreamResult(new
			// File(xmlFilePath));
			StreamResult streamResult = new StreamResult(new File(xmlFilePath).toURI().getPath());
			transformer.transform(domSource, streamResult);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	private static Node getCompany(Document doc, String name, String className) {
		Element test = doc.createElement("test");
		test.setAttribute("name", name);
		Element classes = doc.createElement("classes");
		Element classObj = doc.createElement("class");
		classObj.setAttribute("name", className);
		test.appendChild(classes);
		classes.appendChild(classObj);
		return test;
	}
}

package com.timexautoweb.view;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.web.servlet.view.xslt.AbstractXsltView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.timexautoweb.domain.Timesheet;

public class XMLView extends AbstractXsltView {

	public static final String MAP_KEY = "timesheetsJSPVar";

	@Override
	public Source createXsltSource(Map model, String rootName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = document.createElement(rootName);

		List<Timesheet> timesheets = (List<Timesheet>) model.get(MAP_KEY);
		for (Timesheet it : timesheets) {
			Timesheet timesheet = (Timesheet) it;
			Element timesheetNode = document.createElement("timesheet");

			Element idNode = document.createElement("timesheetid");
			Text textiNode = document.createTextNode(((Integer) timesheet.getId()).toString());
			idNode.appendChild(textiNode);

			Element pedNode = document.createElement("periodEndingDate");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Text textpedNode = document.createTextNode(dateFormat.format(timesheet.getPeriodEndingDate()));
			pedNode.appendChild(textpedNode);

			Element statusNode = document.createElement("statusCode");
			Text textstatusNode = document.createTextNode(String.valueOf(timesheet.getStatusCode()));
			statusNode.appendChild(textstatusNode);

			timesheetNode.appendChild(idNode);
			timesheetNode.appendChild(pedNode);
			timesheetNode.appendChild(statusNode);
			root.appendChild(timesheetNode);
		}
		System.out.println(root.toString());
		DOMSource src = new DOMSource(root);

		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(root);
			StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);
		} catch (Exception ioe) {
			// I/O error
			ioe.printStackTrace();
		}

		return src;
	}
}

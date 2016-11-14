package edu.erau.SE300_WW;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * This class is used with the datePicker to format the date in a traditional MM/DD/YYYY format. 
 * This code was obtained from: http://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-calendar-component.
 *
 */
public class DateLabelFormatter extends AbstractFormatter {

	private String datePattern = "MM/dd/yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	
	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}
		
		return "";
	}

	
}
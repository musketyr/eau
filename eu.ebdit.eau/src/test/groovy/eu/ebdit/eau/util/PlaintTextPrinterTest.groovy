package eu.ebdit.eau.util;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.util.Locale;

import org.junit.Ignore;
import org.junit.Test;


public class PlaintTextPrinterTest {

    private static final String TEXT_TO_MATCH =
'''
 50.00% Master Report
        points: 2/4(6)
        detail: This is root summary report
     0.00% Child Report 1
           points: 0/2(4)
   100.00% Child Report 2
           points: 2/2
      100.00% Grand-child Report 2.1
              points: 2/2
'''


   @Test
    public void testPlainTextPrinter(){
		Locale original = Locale.getDefault()
		Locale.setDefault(Locale.ENGLISH)
        def writer = new StringWriter()
        new PlainTextPrinter().printReport(XmlReportPrinterTest.FIXURE, writer)
        assertEquals(TEXT_TO_MATCH, writer.toString())
		Locale.setDefault(original)
    }
}

package eu.ebdit.eau.util;

import static org.junit.Assert.*;

import java.io.StringWriter;

import org.junit.Ignore;
import org.junit.Test;


public class PlaintTextPrinterTest {

    private static final String TEXT_TO_MATCH =
'''
  50% Master Report
        points:  2/4(6)        
        details: This is root summary report
     0% Child Report 1
           points:  0/2(4)
   100% Child Report 2
           points:  2/2
      100% Grand-child Report 2.1
              points:  2/2
'''


   @Test @Ignore
    public void testPlainTextPrinter(){
        def writer = new StringWriter()
        new PlainTextPrinter().printReport(XmlReportPrinterTest.FIXURE, writer)
        assertEquals(TEXT_TO_MATCH, writer.toString())
    }
}


package eu.ebdit.eau.util;

import static org.junit.Assert.*;
import static org.junit.Assert.*;

import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.Diff;
import org.junit.Test;

import org.junit.After;

import eu.ebdit.eau.Report;
import eu.ebdit.eau.util.XmlReportPrinter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

public class XmlReportPrinterTest {
    
    private static final EXAMPLE_XML = 
'''
	<report>
		<description>Master Report</description>
		<successPercentage>0.5</successPercentage>
		<points>2</points>
		<maxPoints>4</maxPoints>
		<maxPointsWithBonus>6</maxPointsWithBonus>
		<details>This is root summary report</details>
		<reports>
        		<report>
                		<description>Child Report 1</description>
                		<successPercentage>0</successPercentage>
                		<points>0</points>
                		<maxPoints>2</maxPoints>
                		<maxPointsWithBonus>4</maxPointsWithBonus>
                	</report>
                	<report>
                		<description>Child Report 2</description>
                		<successPercentage>1</successPercentage>
                		<points>2</points>
                		<maxPoints>2</maxPoints>
                		<reports>
                        		<report>
                                		<description>Grand-child Report 2.1</description>
                                		<successPercentage>1</successPercentage>
                                		<points>2</points>
                                		<maxPoints>2</maxPoints>
        				</report>
                		</reports>
        		</report>
		</reports>
	</report>	
'''

    public static final FIXURE = [
                                   description: 'Master Report',
                                   successPercentage: 0.5,
                                   points: 2,
                                   maxPoints: 4,
                                   maxPointsWithBonus: 6,
                                   details: 'This is root summary report',
                                   reports: 
                                   [
                                    	    [
                                             description: 'Child Report 1',
                                             successPercentage: 0,
                                             points: 0,
                                             maxPoints: 2,
                                             maxPointsWithBonus: 4
                                             ],
                                            [
                                             description: 'Child Report 2',
                                             successPercentage: 1,
                                             points: 2,
                                             maxPoints: 2,
                                             reports:
                                        	  [[
                                        	   description: 'Grand-child Report 2.1',
                                                   successPercentage: 1,
                                                   points: 2,
                                                   maxPoints: 2,
                                                   reports: []
                                        	   ]]
                                             ]
                                   ]
    ]

    private boolean ignoreWhitespace;

    @Before void setUp(){
	ignoreWhitespace = XMLUnit.getIgnoreWhitespace()
	XMLUnit.setIgnoreWhitespace(true)
    }

    @After void tearDown(){
	XMLUnit.setIgnoreWhitespace(ignoreWhitespace)
    }
    
    @Test void testPrinterWorks() {
	def writer = new StringWriter()
	def printer = new XmlReportPrinter()
	printer.printReport(FIXURE, writer)
	Diff xmlDiff = new Diff(EXAMPLE_XML.toString(), writer.toString())
	Assert.assertTrue(xmlDiff.toString(), xmlDiff.similar())
    }
    
}

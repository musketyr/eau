package eu.ebdit.eau;

import java.io.Writer;

/**
 * Interface for classes which can print report to specified writer.
 * 
 * @author Vladimir Orany
 * 
 */
public interface Printer {
    /**
     * Prints report to specified writer.
     * 
     * @param report
     *            report to be printed
     * @param writer
     *            writer where to write report, use {@link java.io.StringWriter}
     *            if you don't know which one to use
     * @return the same writer as was writer parameter
     */
    Writer printReport(Object report, Writer writer);
}

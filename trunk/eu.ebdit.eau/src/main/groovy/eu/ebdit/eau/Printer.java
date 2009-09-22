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
     *            writer where to write report
     */
    void printReport(Object report, Writer writer);
}


package eu.ebdit.eau.testing;
import java.io.File;
import java.util.List;

import eu.ebdit.eau.testing.xml.TestScoreXMLParser;


/**
 * @author Vladimir Orany
 *
 */
public class ScoreFromXMLTest extends BaseTest {
	
	protected List<TestScore> getScoreList() throws Exception{
		return new TestScoreXMLParser().parse(new File(getClass().getResource("/TestClass.points.eau.xml").toURI()));
	}
}


package eu.ebdit.eau.testing;
import java.io.File;
import java.util.List;


/**
 * @author Vladimir Orany
 *
 */
public class ScoreFromXMLTest extends BaseTest {
	
	protected List<TestScore> getScoreList() throws Exception{
		return new TestScoreXMLParser(new File(getClass().getResource("/TestClass.points.eau.xml").toURI())).parse();
	}
}

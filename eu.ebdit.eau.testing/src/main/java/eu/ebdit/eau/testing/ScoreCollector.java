package eu.ebdit.eau.testing;

import eu.ebdit.eau.Score;

public interface ScoreCollector {

    Iterable<Score> getScores();

}
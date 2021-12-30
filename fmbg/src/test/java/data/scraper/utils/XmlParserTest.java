package data.scraper.utils;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.Assert;
import org.junit.Test;

public class XmlParserTest {

    @Test
    public void testIsBoardGameTrue() {
        // Given
        String gameId = "1";
        XmlParser xp = new XmlParser(gameId);

        // Then
        Assert.assertTrue(xp.isBoardGame());
    }

    @Test
    public void testIsBoardGameFalse() throws UnrecognizedPropertyException {
        // Given
        String gameId = "353519";
        XmlParser xp = new XmlParser(gameId);

        // Then
        Assert.assertFalse(xp.isBoardGame());
    }
}

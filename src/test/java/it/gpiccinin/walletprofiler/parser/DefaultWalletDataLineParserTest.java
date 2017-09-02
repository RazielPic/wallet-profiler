package it.gpiccinin.walletprofiler.parser;

import it.gpiccinin.walletprofiler.entry.DefaultWalletEntry;
import it.gpiccinin.walletprofiler.entry.WalletEntry;
import it.gpiccinin.walletprofiler.header.DefaultWalletHeader;
import it.gpiccinin.walletprofiler.header.WalletHeader;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Giulio on 26/03/2016.
 */
public class DefaultWalletDataLineParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void testParseLineWithWrongNumberOfArguments() throws Exception {
        DefaultWalletDataLineParser lineParser = new DefaultWalletDataLineParser();
        lineParser.parseLine("ciao\",\"come\",\"va?");
    }

    @Test
    public void testParseLineWithCorrectData() throws Exception {
        String line = "\"2015-11-16 14:22:42\",\"Mio Portafoglio\",\"expense\",\"Accommodation\",\"-461.57\",\"EUR\",\"Robe a caso\",\"\",\"\",\"Ciccio\",\"Pasticcio\"";
        String[] expectedParsedLine = {"2015-11-16 14:22:42", "Mio Portafoglio", "expense", "Accommodation", "-461.57", "EUR", "Robe a caso", "", "", "Ciccio", "Pasticcio"};
        DefaultWalletDataLineParser lineParser = new DefaultWalletDataLineParser();

        String[] actualParsedLine = lineParser.parseLine(line);

        assertArrayEquals(expectedParsedLine, actualParsedLine);
    }

    @Test
    public void testCreateWalletHeader() throws Exception {
        String line = "\"2015-11-16 14:22:42\",\"Mio Portafoglio\",\"expense\",\"Accommodation\",\"-461.57\",\"EUR\",\"Robe a caso\",\"\",\"\",\"Ciccio\",\"Pasticcio\"";
        DefaultWalletDataLineParser lineParser = new DefaultWalletDataLineParser();
        String[] actualParsedLine = lineParser.parseLine(line);

        WalletHeader walletHeader = lineParser.createWalletHeader(actualParsedLine);

        assertTrue(walletHeader instanceof DefaultWalletHeader);
    }

    @Test
    public void testCreateWalletEntry() throws Exception {
        String line = "\"2015-11-16 14:22:42\",\"Mio Portafoglio\",\"expense\",\"Accommodation\",\"-461.57\",\"EUR\",\"Robe a caso\",\"\",\"\",\"Ciccio\",\"Pasticcio\"";
        DefaultWalletDataLineParser lineParser = new DefaultWalletDataLineParser();
        String[] actualParsedLine = lineParser.parseLine(line);

        WalletEntry walletEntry = lineParser.createWalletEntry(actualParsedLine);

        assertTrue(walletEntry instanceof DefaultWalletEntry);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWalletEntryWhenPassingIncorrectDataThenExceptionIsThrown() throws Exception {
        String line = "\"Data\",\"Portafogli\",\"Tipo di categoria\",\"Nome categoria\",\"Importo\",\"Valuta\",\"Note\",\"Luogo\",\"Indirizzo\",\"Nome\",\"Cognome\"";
        DefaultWalletDataLineParser lineParser = new DefaultWalletDataLineParser();
        String[] actualParsedLine = lineParser.parseLine(line);

        lineParser.createWalletEntry(actualParsedLine);
    }
}
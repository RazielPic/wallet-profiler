package it.gpiccinin.walletprofiler.parser;

import it.gpiccinin.walletprofiler.Wallet;
import it.gpiccinin.walletprofiler.WalletDataFile;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Giulio on 26/03/2016.
 */
public class WalletDataFileParserTest {

    private static final String SAMPLE_FEED_CSV_PATH = "it/gpiccinin/walletprofiler/sampleFeed.csv";
    private static final String WRONG_FEED_CSV_PATH  = "it/gpiccinin/walletprofiler/wrongFeed.csv";

    @Test
    public void testParseFileWhenPassingCsvFileWith11LineElementsThenDefaultDataFileIsReturned() throws Exception {
        URL csvFileUrl = WalletDataFileParser.class.getClassLoader().getResource(SAMPLE_FEED_CSV_PATH);
        File csvFile = new File(csvFileUrl.toURI());

        WalletDataFileParser fileParser = new WalletDataFileParser(csvFile);

        WalletDataFile walletDataFile = fileParser.parseFile();

        Map<String, Wallet> wallets = walletDataFile.createWallets();
        assertEquals(1, wallets.size());
        assertEquals(32, wallets.get("Mio Portafoglio").getWalletEntries().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseFileWhenPassingCsvFileWithNonSupportedNumberOfLineElementsThenExceptionIsThrown() throws Exception {
        URL csvFileUrl = WalletDataFileParser.class.getClassLoader().getResource(WRONG_FEED_CSV_PATH);
        File csvFile = new File(csvFileUrl.toURI());

        WalletDataFileParser fileParser = new WalletDataFileParser(csvFile);

        fileParser.parseFile();
    }
}
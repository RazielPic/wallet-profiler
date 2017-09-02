package it.gpiccinin.walletprofiler.parser;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Giulio on 26/03/2016.
 */
public class WalletDataLineParserFactoryTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWalletDataLineParserWhenLineIsNotCorrectlySplit() throws Exception {
        WalletDataLineParserFactory factory = new WalletDataLineParserFactory();

        factory.createWalletDataLineParser("ciao\",\"come\",\"va?");
    }

    @Test
    public void testCreateWalletDataLineParserWhenLineIsCorrectlySplitIn11StringsThenCreateDefaultWalletDataFile() throws Exception {
        WalletDataLineParserFactory factory = new WalletDataLineParserFactory();
        String line = "\"2015-11-16 14:22:42\",\"Mio Portafoglio\",\"expense\",\"Accommodation\",\"-461.57\",\"EUR\",\"Robe a caso\",\"\",\"\",\"Ciccio\",\"Pasticcio\"";

        WalletDataLineParser lineParser = factory.createWalletDataLineParser(line);

        assertTrue(lineParser instanceof DefaultWalletDataLineParser);
    }
}
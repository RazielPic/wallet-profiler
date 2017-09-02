package it.gpiccinin.walletprofiler.parser;

/**
 * Created by Giulio on 26/03/2016.
 */
public class WalletDataLineParserFactory {

    private static final String DATA_SPLITTER = "\",\"";

    public WalletDataLineParser createWalletDataLineParser(String line) {
        if (line.split(DATA_SPLITTER).length == 11) {
            return new DefaultWalletDataLineParser();
        } else {
            throw new IllegalArgumentException("Unexpected number of arguments.");
        }
    }
}

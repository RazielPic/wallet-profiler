package it.gpiccinin.walletprofiler.parser;

import it.gpiccinin.walletprofiler.WalletDataFile;
import it.gpiccinin.walletprofiler.entry.WalletEntry;
import it.gpiccinin.walletprofiler.header.WalletHeader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giulio on 26/03/2016.
 */
public class WalletDataFileParser {

    private final File file;

    public WalletDataFileParser(File walletDataFile) {
        file = walletDataFile;
    }

    public WalletDataFile parseFile() throws IllegalArgumentException, IOException {
        WalletDataFile walletDataFile = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

            String header = reader.readLine();
            WalletDataLineParser lineParser = new WalletDataLineParserFactory().createWalletDataLineParser(header);
            WalletHeader walletHeader = lineParser.createWalletHeader(lineParser.parseLine(header));

            List<WalletEntry> walletEntries = new ArrayList<>();
            while (reader.ready()) {
                String entry = reader.readLine();
                WalletEntry walletEntry = lineParser.createWalletEntry(lineParser.parseLine(entry));
                walletEntries.add(walletEntry);
            }

            walletDataFile = new WalletDataFile(walletHeader);
            walletDataFile.setWalletEntries(walletEntries);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return walletDataFile;
    }
}

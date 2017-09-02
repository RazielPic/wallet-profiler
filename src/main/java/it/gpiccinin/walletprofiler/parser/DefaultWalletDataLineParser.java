package it.gpiccinin.walletprofiler.parser;

import it.gpiccinin.walletprofiler.entry.DefaultWalletEntry;
import it.gpiccinin.walletprofiler.entry.WalletEntry;
import it.gpiccinin.walletprofiler.header.DefaultWalletHeader;
import it.gpiccinin.walletprofiler.header.WalletHeader;

/**
 * Created by Giulio on 26/03/2016.
 */
public class DefaultWalletDataLineParser implements WalletDataLineParser {

    private static final int DATA_SIZE = 11;

    @Override
    public String[] parseLine(String line) {
        String[] splitLine = line.split("\",\"");
        if (splitLine.length != DATA_SIZE) {
            throw new IllegalArgumentException("Wrong number of arguments: " + DATA_SIZE + " expected.");
        }
        splitLine[0] = splitLine[0].replaceFirst("\"", "");
        splitLine[DATA_SIZE - 1] = splitLine[DATA_SIZE - 1].replaceAll("\"$", "");
        return splitLine;
    }

    @Override
    public WalletHeader createWalletHeader(String[] headerData) {
        DefaultWalletHeader walletHeader = new DefaultWalletHeader();
        walletHeader.setData(headerData);
        return walletHeader;
    }

    @Override
    public WalletEntry createWalletEntry(String[] entryData) {
        DefaultWalletEntry walletEntry = new DefaultWalletEntry();
        walletEntry.setData(entryData);
        return walletEntry;
    }
}

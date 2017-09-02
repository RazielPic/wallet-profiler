package it.gpiccinin.walletprofiler.parser;

import it.gpiccinin.walletprofiler.entry.WalletEntry;
import it.gpiccinin.walletprofiler.header.WalletHeader;

/**
 * Created by Giulio on 26/03/2016.
 */
public interface WalletDataLineParser {

    String[] parseLine(String line);

    WalletHeader createWalletHeader(String[] headerData);

    WalletEntry createWalletEntry(String[] entryData);
}

package it.gpiccinin.walletprofiler.table;

import it.gpiccinin.walletprofiler.entry.DefaultWalletEntry;
import it.gpiccinin.walletprofiler.entry.WalletEntry;
import it.gpiccinin.walletprofiler.header.WalletHeader;

/**
 * Created by Giulio on 20/03/2016.
 */
public class WalletEntryTableRecordFactory {

    private final WalletHeader walletHeader;

    public WalletEntryTableRecordFactory(WalletHeader walletHeader) {
        this.walletHeader = walletHeader;
    }

    public WalletEntryTableRecord createWalletEntryTableRecord(WalletEntry walletEntry) {
        if (walletEntry instanceof DefaultWalletEntry) {
            return new DefaultWalletEntryTableRecord(walletHeader, walletEntry);
        } else {
            throw new IllegalArgumentException("Unexpected number of arguments.");
        }
    }
}

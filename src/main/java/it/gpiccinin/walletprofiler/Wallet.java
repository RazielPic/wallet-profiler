package it.gpiccinin.walletprofiler;

import it.gpiccinin.walletprofiler.entry.WalletEntry;
import it.gpiccinin.walletprofiler.header.WalletHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giulio on 19/03/2016.
 */
public class Wallet {

    private final String            name;
    private final WalletHeader      walletHeader;
    private final List<WalletEntry> walletEntries;

    public Wallet(String name, WalletHeader walletHeader) {
        this.name = name;
        this.walletHeader = walletHeader;
        walletEntries = new ArrayList<>();
    }

    public void addWalletEntry(WalletEntry walletEntry) throws IllegalArgumentException {
        int expectedSize = walletHeader.getSize();
        if (expectedSize == walletEntry.getSize()) {
            walletEntries.add(walletEntry);
        } else {
            throw new IllegalArgumentException("Wrong number of elements: " + expectedSize + " were expected.");
        }
    }

    public String getName() {
        return name;
    }

    public WalletHeader getWalletHeader() {
        return walletHeader;
    }

    public List<WalletEntry> getWalletEntries() {
        return walletEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Wallet wallet = (Wallet) o;

        if (name != null ? !name.equals(wallet.name) : wallet.name != null) {
            return false;
        }
        if (walletHeader != null ? !walletHeader.equals(wallet.walletHeader) : wallet.walletHeader != null) {
            return false;
        }

        for (WalletEntry walletEntry : walletEntries) {
            if (!wallet.getWalletEntries().contains(walletEntry)) {
                return false;
            }
        }
        for (WalletEntry walletEntry : wallet.getWalletEntries()) {
            if (!walletEntries.contains(walletEntry)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (walletHeader != null ? walletHeader.hashCode() : 0);
        result = 31 * result + walletEntries.hashCode();
        return result;
    }
}

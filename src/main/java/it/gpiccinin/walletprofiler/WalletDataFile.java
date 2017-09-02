package it.gpiccinin.walletprofiler;

import it.gpiccinin.walletprofiler.entry.WalletEntry;
import it.gpiccinin.walletprofiler.header.WalletHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Giulio on 19/03/2016.
 */
public class WalletDataFile {

    private WalletHeader      walletHeader;
    private List<WalletEntry> walletEntries;

    public WalletDataFile(WalletHeader walletHeader) {
        setWalletHeader(walletHeader);
        walletEntries = new ArrayList<>();
    }

    public void setWalletHeader(WalletHeader walletHeader) {
        this.walletHeader = walletHeader;
    }

    public void setWalletEntries(List<WalletEntry> walletEntries) {
        this.walletEntries.addAll(walletEntries);
    }

    public Map<String, Wallet> createWallets() {
        Map<String, Wallet> wallets = new HashMap<>();
        for (WalletEntry entry : walletEntries) {
            String walletName = entry.getWalletName();
            if (wallets.containsKey(walletName)) {
                Wallet selectedWallet = wallets.get(walletName);
                selectedWallet.addWalletEntry(entry);
            } else {
                Wallet newWallet = new Wallet(walletName, walletHeader);
                newWallet.addWalletEntry(entry);
                wallets.put(walletName, newWallet);
            }
        }
        return wallets;
    }
}

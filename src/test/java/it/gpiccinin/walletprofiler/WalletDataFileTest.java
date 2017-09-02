package it.gpiccinin.walletprofiler;

import it.gpiccinin.walletprofiler.entry.WalletEntry;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Giulio on 20/03/2016.
 */
public class WalletDataFileTest {

    @Test
    public void testCreateWalletsWhenThereAreNoEntriesThenWalletsAreNotCreated() throws Exception {
        WalletDataFile walletDataFile = new WalletDataFile(new FivePropertiesWalletHeader());

        Map<String, Wallet> wallets = walletDataFile.createWallets();

        assertTrue(wallets.isEmpty());
    }

    @Test
    public void testCreateWalletsWhenSettingHeaderAndEntriesThenWalletsAreCorrectlyCreated() throws Exception {
        String[] headerData = {"Coso", "Cosa", "Cose", "Cosi", "Altre cose"};
        FivePropertiesWalletHeader walletHeader = new FivePropertiesWalletHeader();
        walletHeader.setData(headerData);

        final String wallet1Name = "Portafoglio_1";
        final String wallet2Name = "Portafoglio_2";
        List<WalletEntry> entries = new ArrayList<>();
        FivePropertiesWalletEntry walletEntry1 = new FivePropertiesWalletEntry();
        walletEntry1.setData(new String[]{"2015-11-16 14:22:42", wallet1Name, "-16.6", "EUR", "Nota a cacchio"});
        FivePropertiesWalletEntry walletEntry2 = new FivePropertiesWalletEntry();
        walletEntry2.setData(new String[]{"2015-09-13 00:00:30", wallet2Name, "-5.30", "EUR", "Boh"});
        FivePropertiesWalletEntry walletEntry3 = new FivePropertiesWalletEntry();
        walletEntry3.setData(new String[]{"2014-05-29 12:34:56", wallet1Name, "1400.0", "EUR", "Finalmente paga!"});
        entries.add(walletEntry1);
        entries.add(walletEntry2);
        entries.add(walletEntry3);

        WalletDataFile walletDataFile = new WalletDataFile(walletHeader);
        walletDataFile.setWalletEntries(entries);

        Map<String, Wallet> wallets = walletDataFile.createWallets();

        assertEquals(2, wallets.size());
        assertEquals(2, wallets.get(wallet1Name).getWalletEntries().size());
        assertEquals(1, wallets.get(wallet2Name).getWalletEntries().size());
    }
}
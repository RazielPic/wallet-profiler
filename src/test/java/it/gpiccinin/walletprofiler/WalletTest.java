package it.gpiccinin.walletprofiler;

import it.gpiccinin.walletprofiler.header.DefaultWalletHeader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Giulio on 20/03/2016.
 */
public class WalletTest {

    private FivePropertiesWalletHeader walletHeader;
    private FivePropertiesWalletEntry  walletEntry1;
    private FivePropertiesWalletEntry  walletEntry2;
    private FivePropertiesWalletEntry  walletEntry3;

    @Before
    public void setUp() throws Exception {
        walletHeader = new FivePropertiesWalletHeader();
        walletHeader.setData(new String[]{"Coso", "Cosa", "Cose", "Cosi", "Altre cose"});
        walletEntry1 = new FivePropertiesWalletEntry();
        walletEntry1.setData(new String[]{"2015-11-16 14:22:42", "Portafoglio_1", "-16.6", "EUR", "Nota a cacchio"});
        walletEntry2 = new FivePropertiesWalletEntry();
        walletEntry2.setData(new String[]{"2015-09-13 00:00:30", "Portafoglio_1", "-5.30", "EUR", "Boh"});
        walletEntry3 = new FivePropertiesWalletEntry();
        walletEntry3.setData(new String[]{"2014-05-29 12:34:56", "Portafoglio_1", "1400.0", "EUR", "Finalmente paga!"});
    }

    @Test
    public void testGetName() throws Exception {
        Wallet wallet = new Wallet("Ciccio", new DefaultWalletHeader());

        assertEquals("Ciccio", wallet.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWalletEntryWhenNumberOfHeaderArgumentsIdDifferentFromNumberOfEntryArguments() throws Exception {
        Wallet wallet = new Wallet("Ciccio", new DefaultWalletHeader());
        wallet.addWalletEntry(new FivePropertiesWalletEntry());
    }

    @Test
    public void testAddWalletEntryWhenNumberOfArgumentsMatchHeaderArgumentsThenFillEntryList() throws Exception {
        Wallet wallet = new Wallet("Ciccio", walletHeader);
        wallet.addWalletEntry(walletEntry1);
        wallet.addWalletEntry(walletEntry2);
        wallet.addWalletEntry(walletEntry3);

        assertEquals(3, wallet.getWalletEntries().size());
    }

    @Test
    public void testEqualsWhenWalletsAreEqualEvenIfWalletEntriesOrderIsDifferent() throws Exception {
        Wallet wallet1 = new Wallet("Ciccio", walletHeader);
        wallet1.addWalletEntry(walletEntry1);
        wallet1.addWalletEntry(walletEntry2);
        wallet1.addWalletEntry(walletEntry3);
        Wallet wallet2 = new Wallet("Ciccio", walletHeader);
        wallet2.addWalletEntry(walletEntry3);
        wallet2.addWalletEntry(walletEntry1);
        wallet2.addWalletEntry(walletEntry2);

        assertEquals(wallet1, wallet2);
    }

    @Test
    public void testEqualsWhenWalletsAreNotEqualBecauseOfWalletName() throws Exception {
        Wallet wallet1 = new Wallet("Ciccio", walletHeader);
        wallet1.addWalletEntry(walletEntry1);
        wallet1.addWalletEntry(walletEntry2);
        wallet1.addWalletEntry(walletEntry3);
        Wallet wallet2 = new Wallet("Pasticcio", walletHeader);
        wallet2.addWalletEntry(walletEntry1);
        wallet2.addWalletEntry(walletEntry2);
        wallet2.addWalletEntry(walletEntry3);

        assertNotEquals(wallet1, wallet2);
    }

    @Test
    public void testEqualsWhenWalletsAreNotEqualBecauseOfWalletHeader() throws Exception {
        Wallet wallet1 = new Wallet("Ciccio", walletHeader);
        wallet1.addWalletEntry(walletEntry1);
        wallet1.addWalletEntry(walletEntry2);
        wallet1.addWalletEntry(walletEntry3);
        FivePropertiesWalletHeader anotherWalletHeader = new FivePropertiesWalletHeader();
        anotherWalletHeader.setData(new String[]{"Robo", "Roba", "Robe", "Cosi", "Altre cose"});
        Wallet wallet2 = new Wallet("Ciccio", anotherWalletHeader);
        wallet2.addWalletEntry(walletEntry1);
        wallet2.addWalletEntry(walletEntry2);
        wallet2.addWalletEntry(walletEntry3);

        assertNotEquals(wallet1, wallet2);
    }

    @Test
    public void testEqualsWhenWalletsAreNotEqualBecauseOfWalletEntriesLength() throws Exception {
        Wallet wallet1 = new Wallet("Ciccio", walletHeader);
        wallet1.addWalletEntry(walletEntry1);
        wallet1.addWalletEntry(walletEntry2);
        wallet1.addWalletEntry(walletEntry3);
        Wallet wallet2 = new Wallet("Ciccio", walletHeader);
        wallet2.addWalletEntry(walletEntry1);
        wallet2.addWalletEntry(walletEntry2);
        wallet2.addWalletEntry(walletEntry3);
        FivePropertiesWalletEntry walletEntry4 = new FivePropertiesWalletEntry();
        walletEntry4.setData(new String[]{"2014-05-29 12:34:56", "MioPortafoglio", "0.0", "JPY", "Finalmente paga!"});
        wallet2.addWalletEntry(walletEntry4);

        assertNotEquals(wallet1, wallet2);
    }

    @Test
    public void testEqualsWhenWalletsAreNotEqualBecauseOfDifferentWalletEntries() throws Exception {
        Wallet wallet1 = new Wallet("Ciccio", walletHeader);
        wallet1.addWalletEntry(walletEntry1);
        wallet1.addWalletEntry(walletEntry2);
        wallet1.addWalletEntry(walletEntry3);
        Wallet wallet2 = new Wallet("Ciccio", walletHeader);
        wallet2.addWalletEntry(walletEntry1);
        FivePropertiesWalletEntry anotherWalletEntry2 = new FivePropertiesWalletEntry();
        anotherWalletEntry2.setData(new String[]{"2014-05-29 12:34:56", "MioPortafoglio", "0.0", "JPY", "Finalmente paga!"});
        wallet2.addWalletEntry(anotherWalletEntry2);
        wallet2.addWalletEntry(walletEntry3);

        assertNotEquals(wallet1, wallet2);
    }
}
package it.gpiccinin.walletprofiler.entry;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Giulio on 19/03/2016.
 */
public class DefaultWalletEntryTest {

    private static final String[] ENTRY_DATA_BASE = {"2015-11-16 14:22:42", "Mio Portafoglio", "expense", "Accommodation", "-461.57", "EUR", "Robe a caso", "", "", "Ciccio", "Pasticcio"};

    private DefaultWalletEntry defaultWalletEntry;

    @Before
    public void setUp() throws Exception {
        defaultWalletEntry = new DefaultWalletEntry();
        defaultWalletEntry.setData(ENTRY_DATA_BASE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDataWithWrongNumberOfArguments() throws Exception {
        DefaultWalletEntry walletEntry = new DefaultWalletEntry();
        walletEntry.setData(new String[0]);
    }

    @Test
    public void testSetDataWithCorrectData() throws Exception {
        DefaultWalletEntry walletEntry = new DefaultWalletEntry();

        walletEntry.setData(ENTRY_DATA_BASE);

        assertEquals(LocalDateTime.of(2015, 11, 16, 14, 22, 42), walletEntry.getDate());
        assertEquals("Mio Portafoglio", walletEntry.getWalletName());
        assertEquals("expense", walletEntry.getCategoryType());
        assertEquals("Accommodation", walletEntry.getCategoryName());
        assertEquals(-461.57, walletEntry.getAmount(), 0.0);
        assertEquals(Currency.getInstance("EUR"), walletEntry.getCurrency());
        assertEquals("Robe a caso", walletEntry.getDescription());
        assertEquals("", walletEntry.getPlace());
        assertEquals("", walletEntry.getAddress());
        assertEquals("Ciccio Pasticcio", walletEntry.getUser());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDataWhenPassingIncorrectDataThenExceptionIsThrown() throws Exception {
        String[] entryData = {"Data", "Portafogli", "Tipo di categoria", "Nome categoria", "Importo", "Valuta", "Note", "Luogo", "Indirizzo", "Nome", "Cognome"};
        DefaultWalletEntry walletEntry = new DefaultWalletEntry();

        walletEntry.setData(entryData);
    }

    @Test
    public void testEqualsWhenEntriesAreEqual() throws Exception {
        DefaultWalletEntry walletEntry1 = new DefaultWalletEntry();
        walletEntry1.setData(ENTRY_DATA_BASE);

        assertEquals(defaultWalletEntry, walletEntry1);
    }

    @Test
    public void testEqualsWhenEntriesAreNotEqualBecauseOfDataField() throws Exception {
        String differentData = "2015-11-16 14:22:41";
        String[] entryData1 = Arrays.copyOf(ENTRY_DATA_BASE, ENTRY_DATA_BASE.length);
        entryData1[0] = differentData;
        DefaultWalletEntry walletEntry1 = new DefaultWalletEntry();
        walletEntry1.setData(entryData1);

        assertNotEquals(defaultWalletEntry, walletEntry1);
    }

    @Test
    public void testEqualsWhenEntriesAreNotEqualBecauseOfWalletNameField() throws Exception {
        String differentWalletName = "ciccio";
        String[] entryData1 = Arrays.copyOf(ENTRY_DATA_BASE, ENTRY_DATA_BASE.length);
        entryData1[1] = differentWalletName;
        DefaultWalletEntry walletEntry1 = new DefaultWalletEntry();
        walletEntry1.setData(entryData1);

        assertNotEquals(defaultWalletEntry, walletEntry1);
    }

    @Test
    public void testEqualsWhenEntriesAreNotEqualBecauseOfCategoryTypeField() throws Exception {
        String differentCategoryType = "pasticcio";
        String[] entryData1 = Arrays.copyOf(ENTRY_DATA_BASE, ENTRY_DATA_BASE.length);
        entryData1[2] = differentCategoryType;
        DefaultWalletEntry walletEntry1 = new DefaultWalletEntry();
        walletEntry1.setData(entryData1);

        assertNotEquals(defaultWalletEntry, walletEntry1);
    }

    @Test
    public void testEqualsWhenEntriesAreNotEqualBecauseOfCategoryNameField() throws Exception {
        String differentCategoryName = "paperino";
        String[] entryData1 = Arrays.copyOf(ENTRY_DATA_BASE, ENTRY_DATA_BASE.length);
        entryData1[3] = differentCategoryName;
        DefaultWalletEntry walletEntry1 = new DefaultWalletEntry();
        walletEntry1.setData(entryData1);

        assertNotEquals(defaultWalletEntry, walletEntry1);
    }

    @Test
    public void testEqualsWhenEntriesAreNotEqualBecauseOfAmountField() throws Exception {
        String differentAmount = "66.6";
        String[] entryData1 = Arrays.copyOf(ENTRY_DATA_BASE, ENTRY_DATA_BASE.length);
        entryData1[4] = differentAmount;
        DefaultWalletEntry walletEntry1 = new DefaultWalletEntry();
        walletEntry1.setData(entryData1);

        assertNotEquals(defaultWalletEntry, walletEntry1);
    }

    @Test
    public void testEqualsWhenEntriesAreNotEqualBecauseOfCurrencyField() throws Exception {
        String differentCurrency = "JPY";
        String[] entryData1 = Arrays.copyOf(ENTRY_DATA_BASE, ENTRY_DATA_BASE.length);
        entryData1[5] = differentCurrency;
        DefaultWalletEntry walletEntry1 = new DefaultWalletEntry();
        walletEntry1.setData(entryData1);

        assertNotEquals(defaultWalletEntry, walletEntry1);
    }

    @Test
    public void testEqualsWhenEntriesAreNotEqualBecauseOfDescriptionField() throws Exception {
        String differentDescription = "The quick brown fox jumps over the lazy dog.";
        String[] entryData1 = Arrays.copyOf(ENTRY_DATA_BASE, ENTRY_DATA_BASE.length);
        entryData1[6] = differentDescription;
        DefaultWalletEntry walletEntry1 = new DefaultWalletEntry();
        walletEntry1.setData(entryData1);

        assertNotEquals(defaultWalletEntry, walletEntry1);
    }

    @Test
    public void testEqualsWhenEntriesAreNotEqualBecauseOfPlaceField() throws Exception {
        String differentPlace = "Trieste, forse";
        String[] entryData1 = Arrays.copyOf(ENTRY_DATA_BASE, ENTRY_DATA_BASE.length);
        entryData1[7] = differentPlace;
        DefaultWalletEntry walletEntry1 = new DefaultWalletEntry();
        walletEntry1.setData(entryData1);

        assertNotEquals(defaultWalletEntry, walletEntry1);
    }

    @Test
    public void testEqualsWhenEntriesAreNotEqualBecauseOfAddressField() throws Exception {
        String differentData = "via Caio, 9999";
        String[] entryData1 = Arrays.copyOf(ENTRY_DATA_BASE, ENTRY_DATA_BASE.length);
        entryData1[8] = differentData;
        DefaultWalletEntry walletEntry1 = new DefaultWalletEntry();
        walletEntry1.setData(entryData1);

        assertNotEquals(defaultWalletEntry, walletEntry1);
    }

    @Test
    public void testEqualsWhenEntriesAreNotEqualBecauseOfUserField() throws Exception {
        String differentUser = "Foo Bar";
        String[] entryData1 = Arrays.copyOf(ENTRY_DATA_BASE, ENTRY_DATA_BASE.length);
        entryData1[9] = differentUser;
        DefaultWalletEntry walletEntry1 = new DefaultWalletEntry();
        walletEntry1.setData(entryData1);

        assertNotEquals(defaultWalletEntry, walletEntry1);
    }
}
package it.gpiccinin.walletprofiler.header;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Giulio on 26/03/2016.
 */
public class DefaultWalletHeaderTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSetDataWithWrongNumberOfArguments() throws Exception {
        new DefaultWalletHeader().setData(new String[0]);
    }

    @Test
    public void testSetDataWithCorrectData() throws Exception {
        String[] headerData = {"Data", "Portafogli", "Tipo di categoria", "Nome categoria", "Importo", "Valuta", "Note", "Luogo", "Indirizzo", "Nome", "Cognome"};
        DefaultWalletHeader walletHeader = new DefaultWalletHeader();

        walletHeader.setData(headerData);

        List<String> data = walletHeader.getFields();

        assertTrue(data.contains("Data"));
        assertTrue(data.contains("Portafogli"));
        assertTrue(data.contains("Tipo di categoria"));
        assertTrue(data.contains("Nome categoria"));
        assertTrue(data.contains("Importo"));
        assertTrue(data.contains("Valuta"));
        assertTrue(data.contains("Note"));
        assertTrue(data.contains("Luogo"));
        assertTrue(data.contains("Indirizzo"));
        assertTrue(data.contains("Nome"));
        assertTrue(data.contains("Cognome"));
    }

    @Test
    public void testEqualsWhenWalletHeadersAreEqual() throws Exception {
        String[] headerData = {"Data", "Portafogli", "Tipo di categoria", "Nome categoria", "Importo", "Valuta", "Note", "Luogo", "Indirizzo", "Nome", "Cognome"};
        DefaultWalletHeader walletHeader = new DefaultWalletHeader();
        walletHeader.setData(headerData);
        DefaultWalletHeader walletHeader2 = new DefaultWalletHeader();
        walletHeader2.setData(headerData);

        assertEquals(walletHeader, walletHeader2);
    }

    @Test
    public void testEqualsWhenWalletHeadersAreNotEqual() throws Exception {
        String[] headerData = {"Data", "Portafogli", "Tipo di categoria", "Nome categoria", "Importo", "Valuta", "Note", "Luogo", "Indirizzo", "Nome", "Cognome"};
        DefaultWalletHeader walletHeader = new DefaultWalletHeader();
        walletHeader.setData(headerData);
        String[] headerData2 = {"Data123", "Portafogli", "Tipo di categoria", "Nome categoria", "Importo", "Valuta", "Note", "Luogo", "Indirizzo", "Nome", "Cognome"};
        DefaultWalletHeader walletHeader2 = new DefaultWalletHeader();
        walletHeader2.setData(headerData2);

        assertNotEquals(walletHeader, walletHeader2);
    }
}
package it.gpiccinin.walletprofiler;

import it.gpiccinin.walletprofiler.entry.WalletEntry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

/**
 * Created by Giulio on 20/03/2016.
 */
public class FivePropertiesWalletEntry implements WalletEntry {

    private LocalDateTime date       = null;
    private String        walletName = "";
    private double        amount     = 0.0;
    private Currency      currency   = null;

    @Override
    public void setData(String[] entryData) {
        if (entryData.length == 5) {
            date = LocalDateTime.parse(entryData[0].replace(' ', 'T'), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            walletName = entryData[1];
            amount = Double.parseDouble(entryData[2]);
            currency = Currency.getInstance(entryData[3]);
        }
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public String getWalletName() {
        return walletName;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }
}

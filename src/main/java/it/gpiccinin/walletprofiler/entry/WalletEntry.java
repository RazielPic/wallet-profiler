package it.gpiccinin.walletprofiler.entry;

import java.time.LocalDateTime;
import java.util.Currency;

/**
 * Created by Giulio on 19/03/2016.
 */
public interface WalletEntry {

    void setData(String[] entryData);

    int getSize();

    String getWalletName();

    LocalDateTime getDate();

    double getAmount();

    Currency getCurrency();
}

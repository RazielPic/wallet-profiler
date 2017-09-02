package it.gpiccinin.walletprofiler.table;

import javafx.beans.property.SimpleStringProperty;

import java.util.Map;

/**
 * Created by Giulio on 20/03/2016.
 */
public interface WalletEntryTableRecord {

    Map<String, SimpleStringProperty> getColumns();
}

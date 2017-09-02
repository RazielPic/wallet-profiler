package it.gpiccinin.walletprofiler.table;

import it.gpiccinin.walletprofiler.entry.DefaultWalletEntry;
import it.gpiccinin.walletprofiler.entry.WalletEntry;
import it.gpiccinin.walletprofiler.header.WalletHeader;
import javafx.beans.property.SimpleStringProperty;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Giulio on 20/03/2016.
 */
public class DefaultWalletEntryTableRecord implements WalletEntryTableRecord {

    private final SimpleStringProperty date;
    private final SimpleStringProperty categoryType;
    private final SimpleStringProperty categoryName;
    private final SimpleStringProperty amount;
    private final SimpleStringProperty currency;
    private final SimpleStringProperty description;
    private final SimpleStringProperty place;
    private final SimpleStringProperty address;
    private final SimpleStringProperty user;

    private final Map<String, SimpleStringProperty> columns;

    public DefaultWalletEntryTableRecord(WalletHeader walletHeader, WalletEntry walletEntry) {
        columns = new HashMap<>();

        DefaultWalletEntry defaultWalletEntry = (DefaultWalletEntry) walletEntry;
        date = new SimpleStringProperty(defaultWalletEntry.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm")));
        categoryType = new SimpleStringProperty(defaultWalletEntry.getCategoryType());
        categoryName = new SimpleStringProperty(defaultWalletEntry.getCategoryName());
        amount = new SimpleStringProperty(Double.toString(defaultWalletEntry.getAmount()));
        currency = new SimpleStringProperty(defaultWalletEntry.getCurrency().toString());
        description = new SimpleStringProperty(defaultWalletEntry.getDescription());
        place = new SimpleStringProperty(defaultWalletEntry.getPlace());
        address = new SimpleStringProperty(defaultWalletEntry.getAddress());
        user = new SimpleStringProperty(defaultWalletEntry.getUser());

        fillColumns(walletHeader);
    }

    private void fillColumns(WalletHeader walletHeader) {
        columns.put(walletHeader.getFields().get(0), dateProperty());
        columns.put(walletHeader.getFields().get(2), categoryTypeProperty());
        columns.put(walletHeader.getFields().get(3), categoryNameProperty());
        columns.put(walletHeader.getFields().get(4), amountProperty());
        columns.put(walletHeader.getFields().get(5), currencyProperty());
        columns.put(walletHeader.getFields().get(6), descriptionProperty());
        columns.put(walletHeader.getFields().get(7), placeProperty());
        columns.put(walletHeader.getFields().get(8), addressProperty());
        columns.put(walletHeader.getFields().get(9), userProperty());
    }

    @Override
    public Map<String, SimpleStringProperty> getColumns() {
        return columns;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getCategoryType() {
        return categoryType.get();
    }

    public SimpleStringProperty categoryTypeProperty() {
        return categoryType;
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public SimpleStringProperty categoryNameProperty() {
        return categoryName;
    }

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public String getCurrency() {
        return currency.get();
    }

    public SimpleStringProperty currencyProperty() {
        return currency;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public String getPlace() {
        return place.get();
    }

    public SimpleStringProperty placeProperty() {
        return place;
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }
}

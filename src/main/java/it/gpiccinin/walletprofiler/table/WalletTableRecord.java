package it.gpiccinin.walletprofiler.table;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Giulio on 20/03/2016.
 */
public class WalletTableRecord {

    private final SimpleStringProperty name;
    private final SimpleStringProperty size;

    public WalletTableRecord(String name, int size) {
        this.name = new SimpleStringProperty(name);
        this.size = new SimpleStringProperty(Integer.toString(size));
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getSize() {
        return size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }
}

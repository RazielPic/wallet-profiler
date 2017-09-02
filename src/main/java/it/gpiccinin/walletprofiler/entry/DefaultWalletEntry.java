package it.gpiccinin.walletprofiler.entry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Giulio on 19/03/2016.
 */
public class DefaultWalletEntry implements WalletEntry {

    private static final int ENTRY_SIZE = 11;

    private LocalDateTime date;
    private String        walletName;
    private String        categoryType;
    private String        categoryName;
    private double        amount;
    private Currency      currency;
    private String        description;
    private String        place;
    private String        address;
    private String        user;

    @Override
    public void setData(String[] entryData) {
        if (entryData.length != 11) {
            throw new IllegalArgumentException("Wrong number of arguments: " + 11 + " expected.");
        }
        try {
            date = LocalDateTime.parse(entryData[0].replace(' ', 'T'), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            walletName = entryData[1];
            categoryType = entryData[2];
            categoryName = entryData[3];
            amount = Double.parseDouble(entryData[4]);
            currency = Currency.getInstance(entryData[5]);
            description = entryData[6];
            place = entryData[7];
            address = entryData[8];
            user = entryData[9] + " " + entryData[10];
        } catch (Exception e) {
            Logger.getLogger(DefaultWalletEntry.class.getName()).log(Level.WARNING, e.getMessage(), e);
            throw new IllegalArgumentException("Wrong argument type: " + e.getMessage());
        }
    }

    @Override
    public int getSize() {
        return ENTRY_SIZE;
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

    public String getCategoryType() {
        return categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public String getAddress() {
        return address;
    }

    public String getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultWalletEntry that = (DefaultWalletEntry) o;

        if (Double.compare(that.amount, amount) != 0) {
            return false;
        }
        if (date != null ? !date.equals(that.date) : that.date != null) {
            return false;
        }
        if (walletName != null ? !walletName.equals(that.walletName) : that.walletName != null) {
            return false;
        }
        if (categoryType != null ? !categoryType.equals(that.categoryType) : that.categoryType != null) {
            return false;
        }
        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) {
            return false;
        }
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (place != null ? !place.equals(that.place) : that.place != null) {
            return false;
        }
        if (address != null ? !address.equals(that.address) : that.address != null) {
            return false;
        }

        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date != null ? date.hashCode() : 0;
        result = 31 * result + (walletName != null ? walletName.hashCode() : 0);
        result = 31 * result + (categoryType != null ? categoryType.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);

        return result;
    }
}

package it.gpiccinin.walletprofiler.header;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Giulio on 26/03/2016.
 */
public class DefaultWalletHeader extends ArrayList<String> implements WalletHeader {

    @Override
    public void setData(String[] headerData) {
        if (headerData.length != 11) {
            throw new IllegalArgumentException("Wrong number of arguments: " + 11 + " expected.");
        }
        addAll(Arrays.asList(headerData));
    }

    @Override
    public int getSize() {
        return size();
    }

    @Override
    public List<String> getFields() {
        return this;
    }
}

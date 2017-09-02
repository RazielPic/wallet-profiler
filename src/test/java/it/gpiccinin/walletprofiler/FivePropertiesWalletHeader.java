package it.gpiccinin.walletprofiler;

import it.gpiccinin.walletprofiler.header.WalletHeader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Giulio on 26/03/2016.
 */
public class FivePropertiesWalletHeader implements WalletHeader {

    private List<String> data = new ArrayList<>();

    @Override
    public void setData(String[] headerData) {
        data.addAll(Arrays.asList(headerData));
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public List<String> getFields() {
        return data;
    }
}

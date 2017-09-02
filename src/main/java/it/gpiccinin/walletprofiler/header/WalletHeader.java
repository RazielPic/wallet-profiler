package it.gpiccinin.walletprofiler.header;

import java.util.List;

/**
 * Created by Giulio on 20/03/2016.
 */
public interface WalletHeader {

    void setData(String[] headerData);

    int getSize();

    List<String> getFields();
}

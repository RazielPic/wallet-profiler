package it.gpiccinin.walletprofiler;

import java.time.LocalDate;
import java.util.Map;

/**
 * Created by Giulio on 27/03/2016.
 */
public interface ChartLogic {

    Map<LocalDate, Double> getDataSetForWallet(Wallet wallet);
}

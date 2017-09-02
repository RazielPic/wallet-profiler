package it.gpiccinin.walletprofiler;

import it.gpiccinin.walletprofiler.entry.WalletEntry;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Giulio on 27/03/2016.
 */
public class CumulativeChartLogic implements ChartLogic {

    private Map<Wallet, Map<LocalDate, Double>> cumulativeDataForWallet = new HashMap<>();

    @Override
    public Map<LocalDate, Double> getDataSetForWallet(Wallet wallet) {
        if (!cumulativeDataForWallet.containsKey(wallet)) {
            List<WalletEntry> walletEntries = wallet.getWalletEntries();

            Set<LocalDate> days = new TreeSet<>();
            for (WalletEntry entry : walletEntries) {
                days.add(entry.getDate().toLocalDate());
            }

            Map<LocalDate, Double> dailyExpenses = new TreeMap<>();
            double expense = 0;
            for (LocalDate day : days) {
                for (WalletEntry entry : walletEntries) {
                    if (day.equals(entry.getDate().toLocalDate())) {
                        expense += entry.getAmount();
                    }
                }
                dailyExpenses.put(day, expense);
            }

            cumulativeDataForWallet.put(wallet, dailyExpenses);
        }

        return cumulativeDataForWallet.get(wallet);
    }
}

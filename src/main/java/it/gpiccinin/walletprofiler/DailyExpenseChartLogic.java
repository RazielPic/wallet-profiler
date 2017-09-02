package it.gpiccinin.walletprofiler;

import it.gpiccinin.walletprofiler.entry.WalletEntry;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Giulio on 26/03/2016.
 */
public class DailyExpenseChartLogic implements ChartLogic {

    private Map<Wallet, Map<LocalDate, Double>> dailyExpensesForWallet = new HashMap<>();

    @Override
    public Map<LocalDate, Double> getDataSetForWallet(Wallet wallet) {
        if (!dailyExpensesForWallet.containsKey(wallet)) {
            List<WalletEntry> walletEntries = wallet.getWalletEntries();

            Set<LocalDate> days = new TreeSet<>();
            for (WalletEntry entry : walletEntries) {
                days.add(entry.getDate().toLocalDate());
            }

            Map<LocalDate, Double> dailyExpenses = new TreeMap<>();
            for (LocalDate day : days) {
                double expense = 0;
                for (WalletEntry entry : walletEntries) {
                    if (day.equals(entry.getDate().toLocalDate())) {
                        expense += entry.getAmount();
                    }
                }
                dailyExpenses.put(day, expense);
            }

            dailyExpensesForWallet.put(wallet, dailyExpenses);
        }

        return dailyExpensesForWallet.get(wallet);
    }
}

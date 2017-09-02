package it.gpiccinin.walletprofiler;

import it.gpiccinin.walletprofiler.parser.WalletDataFileParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Giulio on 18/03/2016.
 */
public class WalletProfilerLogic {

    private static final ResourceBundle RESOURCES = ResourceBundle.getBundle("it.gpiccinin.walletprofiler.resources.walletprofiler", Locale.getDefault(), WalletProfilerLogic.class.getClassLoader());

    private static final Logger LOGGER = Logger.getLogger(WalletProfilerLogic.class.getName());

    private static final String CUMULATIVE_CHART_LABEL    = RESOURCES.getString("walletprofiler.cumulative.chart.label");
    private static final String DAILY_EXPENSE_CHART_LABEL = RESOURCES.getString("walletprofiler.daily.expense.chart.label");

    private final Map<String, Wallet>      wallets;
    private final DailyExpenseChartLogic   dailyExpenseChartLogic;
    private final CumulativeChartLogic     cumulativeChartLogic;
    private       WalletProfilerController view;
    private       String                   selectedWalletName;

    public WalletProfilerLogic() {
        wallets = new HashMap<>();
        dailyExpenseChartLogic = new DailyExpenseChartLogic();
        cumulativeChartLogic = new CumulativeChartLogic();
    }

    public void installView(WalletProfilerController view) {
        this.view = view;
    }

    public void onImportCsv() {
        view.showCsvFileChooser();
    }

    public void parseCsvFile(File file) {
        if (file != null) {
            WalletDataFileParser fileParser = new WalletDataFileParser(file);
            try {
                view.clearViews();
                WalletDataFile walletDataFile = fileParser.parseFile();
                wallets.clear();
                wallets.putAll(walletDataFile.createWallets());
                view.showWallets(wallets);
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.FINE, e.getMessage(), e);
                view.showAlert(Level.WARNING, e.getMessage());
                view.highlightCsvFileText();
            } catch (IOException e) {
                LOGGER.log(Level.FINE, "Unable to read CSV file.", e);
                view.showAlert(Level.WARNING, "Unable to read CSV file.");
                view.highlightCsvFileText();
            }
        }
    }

    public void setSelectedWalletName(String name) {
        this.selectedWalletName = name;
    }

    public void onShowSelectedWalletTable() {
        view.showSingleWalletTable(wallets.get(selectedWalletName));
    }

    public void onShowChart(String graphType) {
        if (DAILY_EXPENSE_CHART_LABEL.equals(graphType)) {
            view.showChart(dailyExpenseChartLogic, wallets.get(selectedWalletName));
        } else if (CUMULATIVE_CHART_LABEL.equals(graphType)) {
            view.showChart(cumulativeChartLogic, wallets.get(selectedWalletName));
        }
    }
}

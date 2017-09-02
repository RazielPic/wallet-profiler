package it.gpiccinin.walletprofiler;

import it.gpiccinin.walletprofiler.entry.WalletEntry;
import it.gpiccinin.walletprofiler.header.WalletHeader;
import it.gpiccinin.walletprofiler.table.WalletEntryTableRecord;
import it.gpiccinin.walletprofiler.table.WalletEntryTableRecordFactory;
import it.gpiccinin.walletprofiler.table.WalletTableRecord;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class WalletProfilerController implements Initializable {

    @FXML
    public Button                                 importCsvButton;
    @FXML
    public TextField                              csvFileText;
    @FXML
    public TableView<WalletTableRecord>           walletsTable;
    @FXML
    public TableColumn<WalletTableRecord, String> walletColumn;
    @FXML
    public TableColumn<WalletTableRecord, String> numEntriesColumn;
    @FXML
    public TableView<WalletEntryTableRecord>      singleWalletTable;
    @FXML
    public Button                                 dailyExpenseChartButton;
    @FXML
    public Button                                 cumulativeChartButton;
    @FXML
    public BorderPane                             chartsPane;

    private WalletProfilerLogic logic;
    private Stage               stage;

    public WalletProfilerController(WalletProfilerLogic logic) {
        this.logic = logic;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        importCsvButton.setOnAction(event -> logic.onImportCsv());

        walletsTable.setItems(FXCollections.observableArrayList());
        singleWalletTable.setItems(FXCollections.observableArrayList());

        walletColumn.setCellValueFactory(param -> param.getValue().nameProperty());
        numEntriesColumn.setCellValueFactory(param -> param.getValue().sizeProperty());

        walletsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logic.setSelectedWalletName(newValue.getName());
                logic.onShowSelectedWalletTable();
                chartsPane.setCenter(null);
            }
        });

        dailyExpenseChartButton.setOnAction(event -> {
            String chartName = ((Button) event.getSource()).getText();
            logic.onShowChart(chartName);
        });

        cumulativeChartButton.setOnAction(event -> {
            String chartName = ((Button) event.getSource()).getText();
            logic.onShowChart(chartName);
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showCsvFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a CSV file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));
        File csvFile = fileChooser.showOpenDialog(stage);
        csvFileText.setStyle("");
        if (csvFile != null) {
            csvFileText.setText(csvFile.getAbsolutePath());
            logic.parseCsvFile(csvFile);
        } else {
            csvFileText.setText("");
        }
    }

    public void clearViews() {
        walletsTable.getItems().clear();
        singleWalletTable.getColumns().clear();
        singleWalletTable.getItems().clear();
        chartsPane.setCenter(null);
    }

    public void showWallets(Map<String, Wallet> wallets) {
        for (String walletName : wallets.keySet()) {
            WalletTableRecord tableRecord = new WalletTableRecord(walletName, wallets.get(walletName).getWalletEntries().size());
            walletsTable.getItems().add(tableRecord);
        }
    }

    public void showSingleWalletTable(Wallet wallet) {
        singleWalletTable.getSelectionModel().clearSelection();
        singleWalletTable.getItems().clear();
        singleWalletTable.getColumns().clear();

        WalletHeader walletHeader = wallet.getWalletHeader();

        createSingleWalletTableColumns(walletHeader);

        WalletEntryTableRecordFactory tableRecordFactory = new WalletEntryTableRecordFactory(walletHeader);
        List<WalletEntry> walletEntries = wallet.getWalletEntries();
        for (WalletEntry entry : walletEntries) {
            WalletEntryTableRecord entryTableRecord = tableRecordFactory.createWalletEntryTableRecord(entry);
            singleWalletTable.getItems().add(entryTableRecord);
        }
    }

    public void showChart(ChartLogic chartLogic, Wallet wallet) {
        Map<LocalDate, Double> dataSet = chartLogic.getDataSetForWallet(wallet);
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(wallet.getName());
        for (Map.Entry<LocalDate, Double> data : dataSet.entrySet()) {
            series.getData().add(new XYChart.Data<>(data.getKey().toString(), data.getValue()));
        }
        LineChart<String, Number> chart = new LineChart<>(new CategoryAxis(), new NumberAxis());
        chart.getData().add(series);

        for (XYChart.Data<String, Number> data : chart.getData().get(0).getData()) {
            Tooltip.install(data.getNode(), new Tooltip(data.getYValue().toString()));
        }

        chartsPane.setCenter(chart);
    }

    public void showAlert(Level level, String message) {
        Alert alert = null;
        if (Level.WARNING.equals(level)) {
            alert = new Alert(Alert.AlertType.WARNING);
        } else if (Level.SEVERE.equals(level)) {
            alert = new Alert(Alert.AlertType.ERROR);
        }
        if (alert != null) {
            alert.setTitle(level.getName());
            alert.setHeaderText(level.getName());
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

    public void highlightCsvFileText() {
        csvFileText.setStyle("-fx-text-box-border: red;-fx-focus-color: red;");
    }

    private void createSingleWalletTableColumns(WalletHeader walletHeader) {
        for (String columnName : walletHeader.getFields()) {
            TableColumn<WalletEntryTableRecord, String> column = new TableColumn<>(columnName);
            column.setCellValueFactory(param -> param.getValue().getColumns().get(columnName));
            singleWalletTable.getColumns().add(column);
        }
    }
}

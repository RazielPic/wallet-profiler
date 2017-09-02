package it.gpiccinin.walletprofiler;

import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;

import static org.junit.Assert.*;

/**
 * Created by Giulio on 19/03/2016.
 */
public class WalletProfilerLogicTest {

    private static final String SAMPLE_FEED_CSV_PATH = "it/gpiccinin/walletprofiler/sampleFeed.csv";
    private static final String WRONG_FEED_CSV_PATH  = "it/gpiccinin/walletprofiler/wrongFeed.csv";

    @Test
    public void testOnImportCsv() throws Exception {
        WalletProfilerLogic logic = new WalletProfilerLogic();
        FakeWalletProfilerController view = new FakeWalletProfilerController(logic);
        logic.installView(view);

        logic.onImportCsv();

        assertTrue(view.isFileChooserShown());
    }

    @Test
    public void testParseCsvFileWhenSelectedFileIsNullThenDoNothing() throws Exception {
        WalletProfilerLogic logic = new WalletProfilerLogic();
        FakeWalletProfilerController view = new FakeWalletProfilerController(logic);
        logic.installView(view);

        logic.parseCsvFile(null);

        assertNull(view.getAlertMessage());
    }

    @Test
    public void testParseCsvFileWhenSelectedFileIsACorrectThenDoNotShowAlerts() throws Exception {
        WalletProfilerLogic logic = new WalletProfilerLogic();
        FakeWalletProfilerController view = new FakeWalletProfilerController(logic);
        logic.installView(view);

        ClassLoader classLoader = WalletProfilerLogicTest.class.getClassLoader();
        URL csvFileUrl = classLoader.getResource(SAMPLE_FEED_CSV_PATH);
        File csvFile = new File(csvFileUrl.toURI());

        logic.parseCsvFile(csvFile);

        assertNull(view.getAlertMessage());
    }

    @Test
    public void testParseCsvFileWhenSelectedFileIsNotCorrectThenShowAlerts() throws Exception {
        WalletProfilerLogic logic = new WalletProfilerLogic();
        FakeWalletProfilerController view = new FakeWalletProfilerController(logic);
        logic.installView(view);

        ClassLoader classLoader = WalletProfilerLogicTest.class.getClassLoader();
        URL csvFileUrl = classLoader.getResource(WRONG_FEED_CSV_PATH);
        File csvFile = new File(csvFileUrl.toURI());

        logic.parseCsvFile(csvFile);

        assertEquals("Unexpected number of arguments.", view.getAlertMessage());
    }

    private class FakeWalletProfilerController extends WalletProfilerController {

        private boolean isFileChooserShown = false;
        private String  alertMessage       = null;

        public FakeWalletProfilerController(WalletProfilerLogic logic) {
            super(logic);
        }

        @Override
        public void showCsvFileChooser() {
            isFileChooserShown = true;
        }

        @Override
        public void clearViews() {
            //do nothing
        }

        @Override
        public void showWallets(Map<String, Wallet> wallets) {
            //do nothing
        }

        @Override
        public void showAlert(Level level, String message) {
            alertMessage = message;
        }

        @Override
        public void highlightCsvFileText() {
            //do nothing
        }

        public boolean isFileChooserShown() {
            return isFileChooserShown;
        }

        public String getAlertMessage() {
            return alertMessage;
        }
    }
}
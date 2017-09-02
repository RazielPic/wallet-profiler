package it.gpiccinin.walletprofiler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class WalletProfilerLauncher extends Application {

    private static final String WALLET_PROFILER_FXML      = "/it/gpiccinin/walletprofiler/walletProfiler.fxml";
    private static final String WALLET_PROFILER_RESOURCES = "it.gpiccinin.walletprofiler.resources.walletprofiler";

    @Override
    public void start(Stage primaryStage) throws Exception {
        WalletProfilerLogic logic = new WalletProfilerLogic();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(WALLET_PROFILER_FXML));
        ClassLoader classLoader = WalletProfilerLauncher.class.getClassLoader();
        loader.setResources(ResourceBundle.getBundle(WALLET_PROFILER_RESOURCES, Locale.getDefault(), classLoader));
        loader.setControllerFactory(param -> new WalletProfilerController(logic));
        loader.setClassLoader(classLoader);
        Parent root = loader.load();
        WalletProfilerController controller = loader.getController();
        controller.setStage(primaryStage);
        logic.installView(controller);

        primaryStage.setTitle("Wallet Profiler v0.1");
        primaryStage.setScene(new Scene(root, 700, 450));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JewelleryStoreApp extends Application {

    private static JewelleryStoreManager storeManager = new JewelleryStoreManager();

    public static JewelleryStoreManager getStoreManager() {
        return storeManager;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_view.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Jewellery Store Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

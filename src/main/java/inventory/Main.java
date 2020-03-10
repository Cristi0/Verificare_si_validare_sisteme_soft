package inventory;

import inventory.logger.ApplicationLogger;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import inventory.controller.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        InventoryRepository repo= new InventoryRepository();
        InventoryService service = new InventoryService(repo);
        ApplicationLogger.log(Level.INFO, service.getAllProducts().toString());
        ApplicationLogger.log(Level.INFO, service.getAllParts().toString());
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/fxml/MainScreen.fxml"));

        Parent root=loader.load();
        MainScreenController ctrl=loader.getController();
        ctrl.setService(service);

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

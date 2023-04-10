package client;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		
		// calls start method
		launch(args);
	
		
	}

	@Override
	public void start(Stage homePage) throws Exception {
		
		//Stage homePage = new Stage();
		homePage.show();
	}

}

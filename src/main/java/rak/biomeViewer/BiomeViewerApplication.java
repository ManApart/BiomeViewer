package rak.biomeViewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import rak.planetGenerator.PlanetGeneratorApplication;
import rak.planetGenerator.model.BiomeCollection;
import rak.planetGenerator.parsers.BiomeParser;
import rak.utility.ResourceLoader;

import java.io.IOException;

public class BiomeViewerApplication extends Application {
	private final static String MAIN_MENU = "MainMenu";
	private static Stage primaryStage;
	private static BiomeCollection biomeCollection;
	
	public static void main(String[] args) {
        ResourceLoader.setRootClass(PlanetGeneratorApplication.class);
		launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		BiomeViewerApplication.primaryStage = primaryStage;
		parseBiomes();
		
        primaryStage.setTitle("BiomeViwer");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images//Logo Icon.png")));
        setScene(MAIN_MENU);
        primaryStage.show();
	}

	public static void setScene(String sceneName){
		try {
			Scene scene = loadFXML(sceneName);
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Scene loadFXML(String fileName) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(BiomeViewerApplication.class.getResource("view/" + fileName + ".fxml"));
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		return scene;
	}
	
	public static BiomeCollection getBiomeCollection(){
		return biomeCollection;
	}
	
	public static void parseBiomes() {
		BiomeViewerApplication.biomeCollection = new BiomeParser().parseBiomes("EarthlikeBiomes");
		BiomeViewerApplication.biomeCollection.addAll(new BiomeParser().parseBiomes("RockyBiomes"));
		BiomeViewerApplication.biomeCollection.addAll(new BiomeParser().parseBiomes("GassBiomes"));
		BiomeViewerApplication.biomeCollection.addAll(new BiomeParser().parseBiomes("StarBiomes"));
	}

}

package rak.biomeViewer.controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import rak.biomeViewer.BiomeViewerApplication;
import rak.biomeViewer.ui.BiomeGridPane;
import rak.planetGenerator.model.Biome;

public class MainMenuController {

	@FXML private GridPane biomeGrid;
	BiomeGridPane biomeGridPane;
	
	public void initialize(){
		biomeGridPane = new BiomeGridPane(biomeGrid);
		generateBiomeGrid();
	}
	
	@FXML private void reload(){
		BiomeViewerApplication.parseBiomes();
		generateBiomeGrid();
	}

	
	private void generateBiomeGrid() {
		ArrayList<Biome> biomes = BiomeViewerApplication.getBiomeCollection().getAllItems();
		biomeGridPane.reinitialize(biomes);
	}

}

package rak.biomeViewer.ui;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import rak.planetGenerator.model.Biome;
import rak.utility.ColorConverter;

public class BiomeGridPane {
	
	private GridPane biomeGrid;
	
	private static final int NUMBER_OF_HEADER_ROWS = 2;
	
	private ColorConverter colorConverter = new ColorConverter();

	public BiomeGridPane(GridPane biomeGrid) {
		this.biomeGrid = biomeGrid;
	}

	public void reinitialize(ArrayList<Biome> biomes) {
		if (biomeGrid != null){
			clearAllRowsButHeader();
			addBiomeRows(biomes);
		}
	}
	
	private void clearAllRowsButHeader() {
		for (int i=biomeGrid.getChildren().size()-1; i>0; i--){
			Node child = biomeGrid.getChildren().get(i);
			if (isBodyRow(child)){
				biomeGrid.getChildren().remove(i);
			}
		}
	}
	
	private boolean isBodyRow(Node child){
		Integer row = GridPane.getRowIndex(child);
		return row != null && row > NUMBER_OF_HEADER_ROWS-1;
	}

	private void addBiomeRows(ArrayList<Biome> biomes) {
		
		for (int i=0; i<biomes.size(); i++){
			Biome biome = biomes.get(i);
			int row = i + NUMBER_OF_HEADER_ROWS;
			addRow(row, biome);
		}
	}

	private void addRow(int row, Biome biome) {
		addLabel(row, 0, biome.getName());		
		addLabel(row, 1, biome.getAltitude(), biome.getAltitudeVariation());		
		addLabel(row, 2, biome.getTemperature(), biome.getTemperatureVariation());		
		addLabel(row, 3, biome.getPercipitation(), biome.getPercipitationVariation());		
		addLabel(row, 4, biome.getColor());		
	}

	private void addLabel(int row, int column, int value, int variation) {
		addLabel(row, column, "" + value + " +- " + variation);
	}
	
	private void addLabel(int row, int column, String value) {
		Label label = new Label("" + value);
		GridPane.setRowIndex(label, row);
		GridPane.setColumnIndex(label, column);
		biomeGrid.getChildren().add(label);
	}
	
	private void addLabel(int row, int column, Color value) {
		String colorHex = colorConverter.convertJFXToHex(value);
		String invertedHex = colorConverter.convertJFXToHex(value.invert());
		TextField textField = new TextField("" + colorHex);
		textField.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill:" + invertedHex);
		
		GridPane.setRowIndex(textField, row);
		GridPane.setColumnIndex(textField, column);
		biomeGrid.getChildren().add(textField);
	}


	
	

}

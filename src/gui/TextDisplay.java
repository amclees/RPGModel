package gui;

import javafx.scene.text.Text;

public class TextDisplay {
	Text text;
	
	public TextDisplay(Text text) {
		this.text = text;
	}
	
	public void print(String value) {
		this.text.setText(this.text.getText() + "\n" + value);
	}
}

package gui;

import core.GridGenerator;
import environment.CombatManager;
import environment.Grid;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {
  VBox main;
  Grid grid;
  DetailDisplay detailDisplay;
  Text text;
  TextDisplay textDisplay;
  GridDisplay gridDisplay;
  CombatManager combat;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    main = new VBox();
    main.getStyleClass().add("display");
    main.setAlignment(Pos.CENTER);

    grid = GridGenerator.makeGrid();

    text = new Text("Welcome!");
    text.setFont(new Font(14));
    text.setWrappingWidth(350);
    text.setTextAlignment(TextAlignment.LEFT);
    this.textDisplay = new TextDisplay(text);
    ScrollPane textScroll = new ScrollPane(text);
    textScroll.setStyle("-fx-padding: 12px;");
    textScroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    textScroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    textScroll.setFitToHeight(true);
    // textScroll.setFitToWidth(true);
    textScroll.setPrefWidth(400);
    textScroll.setMinWidth(textScroll.getPrefWidth() / 2);

    combat = new CombatManager(grid, this.textDisplay);

    detailDisplay = new DetailDisplay(grid);
    detailDisplay.display(0, 0);
    main.getChildren().add(detailDisplay.getNode());

    gridDisplay = new GridDisplay(grid, this);

    HBox nextRound = new HBox();
    nextRound.setAlignment(Pos.TOP_CENTER);

    Button nextRoundButton = new Button("Next Round");
    nextRound.getChildren().add(nextRoundButton);

    TextField nextRoundCount = new TextField();
    nextRoundCount.setText("1");
    nextRound.getChildren().add(nextRoundCount);

    nextRoundButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        int rounds = 0;
        try {
          rounds = Integer.parseInt(nextRoundCount.getText());
          nextRoundCount.getStyleClass().remove("red-textfield");
        } catch (Exception exc) {
          nextRoundCount.getStyleClass().add("red-textfield");
          return;
        }
        combat.round(rounds);
        textScroll.setVvalue(1);
        updateDetail();
        gridDisplay.setSelected(detailDisplay.getX(), detailDisplay.getY());
        gridDisplay.update();
      }
    });

    main.getChildren().add(nextRound);

    Node gridPane = gridDisplay.getPane();
    gridPane.getStyleClass().add("grid");

    ScrollPane scroll = new ScrollPane(gridPane);
    scroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    scroll.setFitToHeight(true);
    scroll.setFitToWidth(true);

    main.getChildren().add(scroll);

    HBox split = new HBox();
    split.setAlignment(Pos.CENTER);

    split.getChildren().add(textScroll);
    split.getChildren().add(main);

    Scene scene = new Scene(split, 1920, 1080);
    scene.getStylesheets().add("/gui/main.css");
    stage.setTitle("RPG Model");
    stage.setScene(scene);
    stage.setMaximized(true);
    stage.show();
  }

  public void updateDetail() {
    try {
      this.detailDisplay.display(this.detailDisplay.getCharacter());
    } catch (NullPointerException e) {
      this.detailDisplay.display(this.detailDisplay.getX(), this.detailDisplay.getY());
    }

    this.main.getChildren().set(0, detailDisplay.getNode());
  }

}

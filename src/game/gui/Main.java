package game.gui;
	
import java.io.IOException;
import java.util.*;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
//import game.engine.titans.u;
import game.engine.weapons.PiercingCannon;
import game.engine.weapons.SniperCannon;
import game.engine.weapons.VolleySpreadCannon;
import game.engine.weapons.WallTrap;
import game.engine.weapons.Weapon;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Main extends Application implements EventHandler<ActionEvent>{
	
    private boolean flag = true;
	private Scene scene1;
	private Scene scene2;
	private Scene scene3;
	private Scene easyscene4;
	private Scene hardscene5;
	private Button passbut;
	private Scene scene6;
	//private BackgroundImage backgroundImage;
	private Label thescore;
	private Label turn;
	private Label curphase;
	private Label totalresources;
	private Label dangerlevel;
	
	
	private Scene gameoverscene;
	private Label weaponshoplab;
	private Label label6;
	private Label label7;
	
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn6;
	private AnchorPane n;
	private RadioButton radioButton1;
	private RadioButton radioButton2;
	private Battle thebattle;
	private Stage primaryStage;
	private AnchorPane a4;
	private AnchorPane a5;
	private AnchorPane a6;

	
	
	///change
	private HBox h;
	private Button[] weaponsShopButtons;
	
	private Label[] laneslabels; 
	private VBox [] lanesWeapons;
	private BorderPane [] lanesTitans;
	private Pane[] lanesTitansTn;
	private Pane[] lanesTitansWn;
	
	
	



	// to make image resizable

	    public ImageView  setimage(Stage z){
	     ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/assets/505623.jpg")));
        imageView.fitWidthProperty().bind(z.widthProperty());
        imageView.fitHeightProperty().bind(z.heightProperty());
       return imageView;
	}
	
	public String getTitanPic(Titan t){
		if (t instanceof PureTitan){
			return "/assets/titan1.jpeg";
		}
		else if (t instanceof AbnormalTitan){
			return "/assets/titan2.jpeg";
		}
		else if (t instanceof ArmoredTitan){
			return "/assets/titan3.jpeg";
		}
		else if (t instanceof ColossalTitan){
			return "/assets/titan4.jpeg";
		}
		else
			return "";

	}

	public String getWeaponPic(Weapon w){
		if (w instanceof PiercingCannon){
			return "/assets/piercingcannon.jpg";
		}
		else if (w instanceof SniperCannon){
			return "/assets/snipercannon.jpg";
		}
		else if (w instanceof VolleySpreadCannon){
			return "/assets/volleyspreadcannon.jpg";
		}
		else if (w instanceof WallTrap){
			return "/assets/walltrap.jpg";
		}
		else
			return "";

	}

	public ImageView getTitanImageView(Titan t) {
		String imagePath = getTitanPic(t);
		if (!imagePath.isEmpty()) {
			ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
			
			if (imagePath.equals("/assets/titan1.jpeg") || imagePath.equals("/assets/titan3.jpeg") ){
			imageView.setFitWidth(50); // Adjust size as needed
			imageView.setFitHeight(35); // Adjust size as needed
			}
			
			if (imagePath.equals("/assets/titan4.jpeg")){
				imageView.setFitWidth(50); // Adjust size as needed
				imageView.setFitHeight(50); // Adjust size as needed	
			}
			
			if (imagePath.equals("/assets/titan2.jpeg")){
			imageView.setFitWidth(50); // Adjust size as needed
			imageView.setFitHeight(25); // Adjust size as needed
			}
			return imageView;
		}
		return null;
	}
	
	
	
	public ImageView getWeaponImageView(Weapon w) {
		String imagePath = getWeaponPic(w);
		if (!imagePath.isEmpty()) {
			ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
			imageView.setFitWidth(30); 
			imageView.setFitHeight(30); 
			Random random=new Random();
			int rand_X=random.nextInt(120);
			int rand_Y=random.nextInt(20);
			imageView.setLayoutX(rand_X);
			imageView.setLayoutY(rand_Y);

			return imageView;
		}
		return null;
	}


	@Override
	public void handle(ActionEvent action) {
		if (action.getSource() == passbut){
			thebattle.passTurn();
			updateGameInfo();
		}
		else if(action.getSource() == btn6){
			primaryStage.setScene(scene1);
		}
			
	}
	
	public void showErrorDialog(Stage ownerStage, String headerText, String contentText) {
	    Stage dialogStage = new Stage();
	    dialogStage.initModality(Modality.WINDOW_MODAL);
	    dialogStage.initOwner(ownerStage);
	    dialogStage.setTitle("Error");

	    Label headerLabel = new Label(headerText);
	    Label contentLabel = new Label(contentText);
	    Button closeButton = new Button("Close");
	    closeButton.setOnAction(e -> dialogStage.close());

	    // Style the labels
	    headerLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: red;");
	    contentLabel.setStyle("-fx-font-size: 14px; -fx-padding: 10;");

	    VBox vbox = new VBox(20); // Increased spacing
	    vbox.setPadding(new javafx.geometry.Insets(15, 20, 15, 20)); // Added padding
	    vbox.getChildren().addAll(headerLabel, contentLabel, closeButton);

	    // Fade Transition for content label
	    FadeTransition blink = new FadeTransition(Duration.seconds(1), contentLabel);
	    blink.setFromValue(1.0);
	    blink.setToValue(0.5); // Adjusted for better readability
	    blink.setCycleCount(FadeTransition.INDEFINITE);
	    blink.setAutoReverse(true);
	    blink.play();

	    // Scale Transition for dialog entrance
	    ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), vbox);
	    scaleTransition.setFromX(0.5);
	    scaleTransition.setFromY(0.5);
	    scaleTransition.setToX(1);
	    scaleTransition.setToY(1);
	    scaleTransition.play();

	    Scene scene = new Scene(vbox);
	    dialogStage.setScene(scene);
	    dialogStage.showAndWait();
	}

	private Optional<Integer> getWhichLane() {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		VBox dialogVbox = new VBox(20);
		dialogVbox.setAlignment(Pos.CENTER);

		TextField textField = new TextField();
		textField.setPromptText("Enter lane number");

		Button submitButton = new Button("Submit");
		Button cancelButton = new Button("Cancel");
		Label errorLabel = new Label();
		errorLabel.setTextFill(Color.RED); // Set the error message in red
		errorLabel.setVisible(false); // Hide error initially

		final Integer[] laneNumber = {null}; // Array to hold the lane number because it needs to be effectively final

		submitButton.setOnAction(e -> {
			try {
				int inputNumber = Integer.parseInt(textField.getText()) - 1; // Assuming lanes are 1-indexed
				if (inputNumber >= 0 && inputNumber < thebattle.getOriginalLanes().size()) {
					laneNumber[0] = inputNumber; // Store valid input number
					dialog.close();
				} 
				else {
					errorLabel.setText("Invalid lane number, please try again.");
					FadeTransition blink = new FadeTransition(Duration.seconds(1),errorLabel);
					blink.setFromValue(1.0);
					blink.setToValue(0.0);
					blink.setCycleCount(FadeTransition.INDEFINITE);
					blink.setAutoReverse(true);
					blink.play();
					errorLabel.setVisible(true);
					textField.setText("");
				}
			}
			
			catch (NumberFormatException ex) {
				errorLabel.setText("Invalid input, please enter a number.");
				FadeTransition blink = new FadeTransition(Duration.seconds(1),errorLabel);
				blink.setFromValue(1.0);
				blink.setToValue(0.0);
				blink.setCycleCount(FadeTransition.INDEFINITE);
				blink.setAutoReverse(true);
				blink.play();
				errorLabel.setVisible(true);
				textField.setText("");
			}
			
		});

		cancelButton.setOnAction(e -> {
			laneNumber[0] = null; // Explicitly set to null to indicate cancellation
			dialog.close();
		});

		dialogVbox.getChildren().addAll(new Text("Please enter your lane number:"), textField, errorLabel, submitButton, cancelButton);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.showAndWait();

		return Optional.ofNullable(laneNumber[0]); // Return the result as Optional
	}

//	 public void setupLanesDisplay(AnchorPane anchor) {
		//    VBox[] vboxes = new VBox[thebattle.getOriginalLanes().size()];
		//    laneslabels = new Label[thebattle.getOriginalLanes().size()];
	//   lanesTitans = new BorderPane[thebattle.getOriginalLanes().size()];
	//	lanesTitansTn=new Pane[thebattle.getOriginalLanes().size()];
	// 	lanesTitansWn=new Pane[thebattle.getOriginalLanes().size()];
	// 	VBox[] lanesWeapons = new VBox[thebattle.getOriginalLanes().size()];

	//    int i = 0;
	//   for (Lane lane : thebattle.getOriginalLanes()) {
	//	   vboxes[i] = new VBox(10);
	//      laneslabels[i] = new Label("Lane:" + (i+1) + "  Danger:"+ lane.getDangerLevel() +"          Wall Health:"+ lane.getLaneWall().getCurrentHealth());

	//	   lanesTitansTn[i]=new Pane();
	//	   lanesTitansWn[i]=new Pane();
	//	   lanesTitansTn[i].setPrefWidth(450);
	//   lanesTitansWn[i].setPrefWidth(150);
	//   lanesTitansWn[i].setStyle("-fx-background-color: white;");

	//   lanesTitans[i] = new BorderPane();
	//   lanesTitans[i].setStyle("-fx-background-color: pink;");
	//	lanesTitans[i].setLayoutX(0);
	//	lanesTitans[i].setLayoutY(150);
	//	lanesTitans[i].setPrefHeight(50);
	//	lanesTitans[i].setPrefWidth(600);
	//	lanesTitans[i].setLeft(lanesTitansWn[i]);
	//	lanesTitans[i].setRight(lanesTitansTn[i]);




	//   for (Titan titan : lane.getTitans()) {
				   //         Label titanLabel = new Label(titan.toString());
		            //         lanesTitans[i].getChildren().add(titanLabel);
		            //     }

			   //     lanesWeapons[i] = new VBox();
		        //      vboxes[i].getChildren().addAll(laneslabels[i], lanesTitans[i]);
		        //      i++;
		        //    }

            //    VBox lanesBox = new VBox(40);
		        //    lanesBox.getChildren().addAll(vboxes);
		    //    lanesBox.setLayoutX(0);
		    //    lanesBox.setLayoutY(34);

		    //    anchor.getChildren().add(lanesBox);
		    //}
	
	
	public void setupLanesDisplay(AnchorPane anchor) {
		   // VBox[] vboxes = new VBox[thebattle.getOriginalLanes().size()];
		
		    laneslabels = new Label[thebattle.getOriginalLanes().size()];
		    lanesTitans = new BorderPane[thebattle.getOriginalLanes().size()];
			lanesTitansTn=new Pane[thebattle.getOriginalLanes().size()];
		 	lanesTitansWn=new Pane[thebattle.getOriginalLanes().size()];
		 	
		 	//VBox[] lanesWeapons = new VBox[thebattle.getOriginalLanes().size()];

		    int i = 0;
		   for (Lane lane : thebattle.getOriginalLanes()) {
			   //vboxes[i] = new VBox(10);
		       laneslabels[i] = new Label("Lane:" + (i+1) + "       Danger:"+ lane.getDangerLevel() +"          Wall Health:"+ lane.getLaneWall().getCurrentHealth());
		       laneslabels[i].setStyle("-fx-text-fill: white; -fx-font-size: 16px;"); 
		       laneslabels[i].setStyle("-fx-background-color: #FFA07A;"); // Coral color
			   lanesTitansTn[i]=new Pane();
			   lanesTitansTn[i].setPrefWidth(450);
			   lanesTitansWn[i]=new Pane();
			   lanesTitansWn[i].setPrefWidth(150);
			   lanesTitansWn[i].setStyle("-fx-background-color: grey;");

			   lanesTitans[i] = new BorderPane();
			   lanesTitans[i].setStyle("-fx-background-color: pink;");
			   lanesTitans[i].setLayoutX(0);
			   //lanesTitans[i].setLayoutY(150);
			   lanesTitans[i].setPrefHeight(50);
			   lanesTitans[i].setPrefWidth(600);
			   lanesTitans[i].setLayoutY(65 + i * 120);
				
				anchor.getChildren().add(laneslabels[i]); // add label to anchor pane
				laneslabels[i].setLayoutY(42 + i * 120); // set layoutY to be above lanesTitans
				
				lanesTitans[i].setLeft(lanesTitansWn[i]);
				lanesTitans[i].setRight(lanesTitansTn[i]);
    
               //nb we need to add titans details here
			   for (Titan titan : lane.getTitans()) {
		            Label titanLabel = new Label(titan.toString());
		            lanesTitans[i].getChildren().add(titanLabel);
		        }
		        anchor.getChildren().add(lanesTitans[i]);
		        i++;
		    }
		}

	 public void setupWeaponShop(AnchorPane anchor) {
		    String[] weaponDescriptions = {
		        "Anti Titan Shell\nPiercing\n25\n10",
		        "Long Range Spear\nSniper\n25\n35",
		        "Wall Spread\nVolleySpread\n100\n5",
		        "Proximity Trap\nWallTrap\n75\n100",
		        "PASS TURN"
		    };

		    
		    
		    weaponsShopButtons = new Button[weaponDescriptions.length]; 

		    HBox weaponsBox = new HBox(10); //10 spacing
		    weaponsBox.setLayoutX(0);
		    weaponsBox.setLayoutY(635);
		    weaponsBox.setPrefHeight(120);
		    weaponsBox.setPrefWidth(600);
		    weaponsBox.setStyle("-fx-background-color:  pink;");

		    weaponshoplab = new Label("THE WEAPON SHOP");
		    weaponshoplab.setStyle("-fx-background-color: pink; -fx-font-weight: bold;");
		    weaponshoplab.setLayoutX(0);
		    weaponshoplab.setLayoutY(600);
		    weaponshoplab.setPrefHeight(35);
		    weaponshoplab.setPrefWidth(600);
		    weaponshoplab.setWrapText(true);
		    weaponshoplab.setAlignment(Pos.CENTER);

		    for (int i = 0; i < weaponDescriptions.length; i++) {
		    	
		        Button button = new Button(weaponDescriptions[i]);
		        DropShadow shadow = new DropShadow();

		     // Adding the shadow when the button is pressed
		        button.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
		        button.setEffect(shadow);
		     });

		     // Removing the shadow when the button is released
		        button.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
		        button.setEffect(null);
		     });
		        button.setPrefHeight(120);
		        button.setPrefWidth(120);
		        button.setStyle("-fx-background-color: magenta;");
		        button.setWrapText(true);
		        setupButtonEvents(button, i);
		        weaponsBox.getChildren().add(button);
		        weaponsShopButtons[i] = button; // Assign the button to the array
		    }
		    
		  
		    
		    anchor.getChildren().addAll(weaponsBox, weaponshoplab);
		}

	 public void setupButtonEvents(Button button, int index) {
		    button.setOnAction(event -> {
		        if (index == weaponsShopButtons.length - 1) {
		            thebattle.passTurn();
		            updateLanesDisplay();
		            updateGameInfo();
		        } else {
		            Optional<Integer> laneIndexOpt = getWhichLane();
		            laneIndexOpt.ifPresent(laneIndex -> {
		                Lane selectedLane = thebattle.getOriginalLanes().get(laneIndex);
		                try {
		                    thebattle.purchaseWeapon(index + 1, selectedLane);
							updateLanesDisplay();
		                    updateGameInfo();
		                    //catch (InsufficientResourcesException | InvalidLaneException e)
		                } catch (InsufficientResourcesException | InvalidLaneException e) {
		                	showErrorDialog(primaryStage, "Insufficient Resources", "You do not have enough resources to purchase this weapon");
		                }
		                
		            });
		        }
		    });
		}

	




	public void updateGameInfo() {
		  thescore.setText("Score: " + thebattle.getScore());
		  turn.setText("Turn: " + thebattle.getNumberOfTurns());
		  curphase.setText("Phase: " + thebattle.getBattlePhase().name());
		  totalresources.setText("Resources: " + thebattle.getResourcesGathered());
	}

	public void updateLanesDisplay() {
		if(thebattle.isGameOver()){
			int s = thebattle.getScore();
			String score = s + "";
			label6.setText("YOUR SCORE IS:   " + score);
			//giveError("Game Over","Score","\""+thebattle.getScore() + "\"");
			primaryStage.setScene(scene6);
			return;
		}
		 int i = 0;
		    for (Lane lane : thebattle.getOriginalLanes()) {
		        laneslabels[i].setText("Lane:" + (i + 1) + "  Danger:" + lane.getDangerLevel() + "          Wall Health:" + lane.getLaneWall().getCurrentHealth());
		        if (lane.getLaneWall().getCurrentHealth() <= 0) {
		            lanesTitans[i].getChildren().clear();
		            lanesTitans[i].setStyle("-fx-background-color: black;");
		        } else {
		           // List<ImageView> imageViewsTitans = new ArrayList<>();
		            List<ImageView> imageViewsWeapons = new ArrayList<>();
		            List<StackPane> titanPanes = new ArrayList<>(); // This will store StackPanes containing the ImageView and Label
		            //double yOffset = 5; // Start Y offset for the first Titan to be relatively low within the lane
		            
		            for (Titan titan : lane.getTitans()) {
		                ImageView titanImageView = getTitanImageView(titan);
		                int distanceFromBase = (titan.getDistance());
		                titanImageView.setLayoutX(distanceFromBase);

		                Label titanLabel = new Label("HP: " + titan.getCurrentHealth());
		                titanLabel.setStyle("-fx-text-fill: white; -fx-font-size: 10px; -fx-font-weight: bold; -fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 1px;");

		                StackPane titanPane = new StackPane();
		                titanPane.getChildren().addAll(titanImageView, titanLabel);
		                titanPane.setLayoutX(distanceFromBase);
		                titanPane.setLayoutY(0); // Set yOffset within the lane's visual boundaries

		                StackPane.setAlignment(titanLabel, Pos.TOP_LEFT);
		                StackPane.setMargin(titanLabel, new Insets(5, 0, 0, 5));

		               // yOffset += 40; // Consistent vertical spacing for each Titan within the same lane

		                titanPanes.add(titanPane);
		            }
		            for (Weapon weapon : lane.getWeapons()) {
		                imageViewsWeapons.add(getWeaponImageView(weapon));
		            }
		            lanesTitansTn[i].getChildren().clear();
		            lanesTitansTn[i].getChildren().addAll(titanPanes); // Adding the StackPanes of Titans
		            lanesTitansWn[i].getChildren().clear();
		            lanesTitansWn[i].getChildren().addAll(imageViewsWeapons);
		        }
		        i++;
		    }
		    primaryStage.show();
		}
	
	public void createEasyMode() {
		try {
			thebattle = new Battle(1,0, 400, 3, 250);

		}
		catch (IOException e) {
			showErrorDialog(primaryStage, "SOMETHING MALFUNCTIONED!", "UNABLE TO READ FILE");
		}

		a4 = new AnchorPane();
		easyscene4 = new Scene(a4, 600, 750);
		primaryStage.setScene(easyscene4);

		HBox infoBox = new HBox();
		infoBox.setLayoutX(8);
		infoBox.setLayoutY(8);
		infoBox.setSpacing(58);
		thescore = new Label("Score:" + thebattle.getScore());
		turn = new Label("Turn:" + thebattle.getNumberOfTurns());
		curphase = new Label("Phase:" + thebattle.getBattlePhase());
		totalresources = new Label("Resources:" + thebattle.getResourcesGathered());
		thescore.setTextFill(Color.WHITE);
		turn.setTextFill(Color.WHITE);
		curphase.setTextFill(Color.WHITE);
		totalresources.setTextFill(Color.WHITE);
		
		infoBox.setLayoutX(80);
		infoBox.setStyle("-fx-background-color: #800080;");
		
		infoBox.getChildren().addAll(thescore, turn, curphase, totalresources);
		
		
		BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, null, null);
		Background background = new Background(backgroundFill);

		a4.setBackground(background);
		
		a4.getChildren().add(infoBox);

		setupWeaponShop(a4);
		setupLanesDisplay(a4);

		primaryStage.show();
	}
	public void createHardMode() {
		try {
			thebattle = new Battle(1,0, 400, 5, 125);
		} catch (IOException e) {
			showErrorDialog(primaryStage, "SOMETHING MALFUNCTIONED!", "UNABLE TO READ FILE");
		}

		a5 = new AnchorPane();
		hardscene5 = new Scene(a5, 600, 750);
		primaryStage.setScene(hardscene5);
		HBox infoBox = new HBox();
		infoBox.setLayoutX(8);
		infoBox.setLayoutY(8);
		infoBox.setSpacing(58);
		
		thescore = new Label("Score:" + thebattle.getScore());
		turn = new Label("Turn:" + thebattle.getNumberOfTurns());
		curphase = new Label("Phase:" + thebattle.getBattlePhase());
		totalresources = new Label("Resources:" + thebattle.getResourcesGathered());
		
		thescore.setTextFill(Color.WHITE);
		turn.setTextFill(Color.WHITE);
		curphase.setTextFill(Color.WHITE);
		totalresources.setTextFill(Color.WHITE);
		
		infoBox.setStyle("-fx-background-color: #800080;");
		infoBox.setLayoutX(60);
		infoBox.setAlignment(Pos.CENTER);
		infoBox.getChildren().addAll(thescore, turn, curphase, totalresources);
		
		BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, null, null);
		Background background = new Background(backgroundFill);

		a5.setBackground(background);
		
		a5.getChildren().add(infoBox);

		setupWeaponShop(a5);
		setupLanesDisplay(a5);

		primaryStage.show();
	}
	public void start(Stage primaryStage) {
		try {
			Image img = new Image(getClass().getResourceAsStream("/assets/505623.jpg"));
			BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);

			this.primaryStage = primaryStage;
			primaryStage.setTitle("Attack On Titans");
			primaryStage.setResizable(false);
//SCENE1 1
			Button btn1=new Button("START");
			//btn1.setStyle("-fx-font-family: 'Snap Itc Regular'; -fx-font-size: 16px; -fx-font-weight: bold;");

			ScaleTransition pulse = new ScaleTransition(Duration.millis(500), btn1);
			pulse.setFromX(1);
			pulse.setFromY(1);
			pulse.setToX(1.05);
			pulse.setToY(1.05);
			pulse.setCycleCount(ScaleTransition.INDEFINITE);
			pulse.setAutoReverse(true);
			pulse.play();
			ImageView imageView1 = setimage(primaryStage);
            
			Label l3 = new Label("     SELECT MODE:");
			l3.setStyle("-fx-background-color: white; -fx-font-weight: bold;");

			l3.setLayoutX(200);
			l3.setLayoutY(230);
			l3.setPrefHeight(50);
			l3.setPrefWidth(150);
			l3.setWrapText(true); // Enable text wrapping

			btn1.setLayoutX(250);
			btn1.setLayoutY(375);
			btn1.setPrefHeight(50);
			btn1.setPrefWidth(100);

			AnchorPane a = new AnchorPane();
			a.setBackground(bGround);
			a.getChildren().add(imageView1);
			a.getChildren().add(btn1);

	     	// showErrorDialog(primaryStage, "An error occurred", "Please try again later.");
			scene1 = new Scene(a,600,750);

			a.prefWidthProperty().bind(scene1.widthProperty());
			a.prefHeightProperty().bind(scene1.heightProperty());


			ImageView imageView2 = setimage(primaryStage);
			//SCENE2
			ToggleGroup toggleGroup = new ToggleGroup();
			RadioButton radioButton1 = new RadioButton("Easy ");
			radioButton1.setToggleGroup(toggleGroup);
			radioButton1.setSelected(true);
			RadioButton radioButton2 = new RadioButton("Hard");
			radioButton2.setToggleGroup(toggleGroup);

			// Set the background color of the radio buttons to white
			radioButton1.setStyle("-fx-background-color: white;");
			radioButton2.setStyle("-fx-background-color: white;");

			VBox vbox = new VBox(radioButton1, radioButton2);

			Label l = new Label("Press Buttons to Play Game\nPurchase weapons "
					+ "from store or choose to Pass Turn.\nYour Goal is to Protect the walls");
			l.setStyle("-fx-background-color: white; -fx-font-weight: bold;");
			l.setLayoutX(160);
			l.setLayoutY(260);
			l.setPrefHeight(130);
			l.setPrefWidth(250);
			l.setWrapText(true); // Enable text wrapping
			l.setAlignment(Pos.CENTER);

			vbox.setLayoutX(250);
			vbox.setLayoutY(375);
			vbox.setPrefHeight(50);
			vbox.setPrefWidth(200);

			AnchorPane a2 = new AnchorPane();


			Button btn2=new Button("BEGIN");
			pulse = new ScaleTransition(Duration.millis(500), btn2);
			pulse.setFromX(1);
			pulse.setFromY(1);
			pulse.setToX(1.05);
			pulse.setToY(1.05);
			pulse.setCycleCount(ScaleTransition.INDEFINITE);
			pulse.setAutoReverse(true);
			pulse.play();

			btn2.setLayoutX(230);
			btn2.setLayoutY(500);
			btn2.setPrefHeight(50);
			btn2.setPrefWidth(100);

			a2.getChildren().add(imageView2);
			a2.getChildren().add(vbox);
			a2.getChildren().add(btn2);
			a2.getChildren().add(l3);

			scene2 = new Scene(a2,600,750);
			a2.setBackground(bGround);

			btn1.setOnAction(e->primaryStage.setScene(scene2));

			//SCENE3

			AnchorPane a3 = new AnchorPane();
			Label x = new Label("Game Instructions:");
			x.setLayoutX(150);
			x.setLayoutY(80);
			x.setPrefHeight(50);
			x.setPrefWidth(100);
			a3.getChildren().add(x);

			a3.getChildren().add(l);

			Button btn3=new Button("Ready?");
			 pulse = new ScaleTransition(Duration.millis(500), btn3);
			pulse.setFromX(1);
			pulse.setFromY(1);
			pulse.setToX(1.05);
			pulse.setToY(1.05);
			pulse.setCycleCount(ScaleTransition.INDEFINITE);
			pulse.setAutoReverse(true);
			pulse.play();
			btn3.setLayoutX(250);
			btn3.setLayoutY(500);
			btn3.setPrefHeight(50);
			btn3.setPrefWidth(100);
			a3.getChildren().add(btn3);
			scene3 = new Scene(a3, 600, 750);

			a3.setBackground(bGround);

			primaryStage.setScene(scene3);
			btn2.setOnAction(e ->primaryStage.setScene(scene3));

			btn3.setOnAction(e -> {
				if (radioButton1.isSelected()) {
					createEasyMode();
				} else if (radioButton2.isSelected()) {
					createHardMode();
				}
			});
			
			
			AnchorPane a6 = new AnchorPane();
			scene6 = new Scene(a6, 600,750);
			
			
			Image backgroundImage = new Image("/assets/gameover.jpg");

			// Create an ImageView to display the image
			ImageView backgroundView = new ImageView(backgroundImage);

			// Set the size of the ImageView to match the size of the AnchorPane
			backgroundView.setFitWidth(500);
			backgroundView.setFitHeight(500);

			// Add the ImageView to the AnchorPane
			a6.getChildren().add(backgroundView);

			// Apply CSS to make the AnchorPane transparent so that the image is visible
			a6.setStyle("-fx-background-color: transparent;");
			a6.setStyle("-fx-background-color: #000000;");
			
			

			
            label6 = new Label();
            
            label6.setLayoutX(120);
			label6.setLayoutY(100);
			label6.setPrefHeight(130);
			label6.setPrefWidth(250);
			label6.setWrapText(true); // Enable text wrapping
			label6.setAlignment(Pos.CENTER);
			label6.setStyle("-fx-font-weight: bold;");
			label6.setTextFill(Color.WHITE);
			label6.setStyle("-fx-font-family: 'Times New Roman'; -fx-fill: #FFFFFF; -fx-font-size: 27px;");

            
           // label7 = new Label();
           // label7.setText("GAME OVER: YOU LOST!");
            
           // label7.setLayoutX(150);
           // label7.setLayoutY(80);
          //  label7.setPrefHeight(50);
          //  label7.setPrefWidth(100);
		//	a3.getChildren().add(x);
            

			Button btn6=new Button("PLAY AGAIN?");
			 pulse = new ScaleTransition(Duration.millis(500), btn6);
			pulse.setFromX(1);
			pulse.setFromY(1);
			pulse.setToX(1.05);
			pulse.setToY(1.05);
			pulse.setCycleCount(ScaleTransition.INDEFINITE);
			pulse.setAutoReverse(true);
			pulse.play();
			btn6.setLayoutX(120);
			btn6.setLayoutY(350);
			btn6.setPrefHeight(100);
			btn6.setPrefWidth(250);
			
			
		
			//btn6.setOnAction(this);
			
			btn6.setOnAction(event -> {
			    primaryStage.setScene(scene1);
			});
			
			
            a6.getChildren().addAll(label6,btn6);

			primaryStage.setScene(scene1);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

			

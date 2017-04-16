package application;

import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.AND_RADIO;
import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.OR_RADIO;
import static com.neu.msd.AuthorRetriever.constants.ButtonConstants.SEARCH_AUTHORS;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_LENGTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SCENE_WIDTH;
import static com.neu.msd.AuthorRetriever.constants.SceneContants.SEARCH;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.controlsfx.control.CheckComboBox;

import com.neu.msd.AuthorRetriever.constants.PositionAlias;
import com.neu.msd.AuthorRetriever.constants.ValidationConstants;
import com.neu.msd.AuthorRetriever.model.Author;
import com.neu.msd.AuthorRetriever.model.Paper;
import com.neu.msd.AuthorRetriever.model.SearchCriteria;
import com.neu.msd.AuthorRetriever.model.ServiceInfo;
import com.neu.msd.AuthorRetriever.service.SearchService;
import com.neu.msd.AuthorRetriever.service.SearchServiceImpl;
import com.neu.msd.AuthorRetriever.service.UserServiceImpl;
import com.neu.msd.AuthorRetriever.util.AlertUtil;
import com.neu.msd.AuthorRetriever.util.ConferenceUtil;
import com.neu.msd.AuthorRetriever.util.NavigationBar;
import com.neu.msd.AuthorRetriever.util.SceneStack;
import com.neu.msd.AuthorRetriever.validation.SearchSceneValidation;

import javafx.scene.control.Separator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.layout.ColumnConstraints;

@SuppressWarnings({ "rawtypes", "restriction", "unchecked" })
public class SearchScene {

		public static void displaySearchScene(Stage primaryStage){
		
		GridPane grid2 = new GridPane();
		grid2.setAlignment(Pos.TOP_LEFT);
		grid2.setHgap(10);
		grid2.setVgap(10);
		grid2.setPadding(new Insets(25, 25, 25, 25));
		
	
	     ColumnConstraints col1 = new ColumnConstraints();
	     col1.setPercentWidth(50);
	     ColumnConstraints col2 = new ColumnConstraints();
	     col2.setPercentWidth(25);
	     ColumnConstraints col3 = new ColumnConstraints();
	     col3.setPercentWidth(20);
	     ColumnConstraints col4 = new ColumnConstraints();
	     col3.setPercentWidth(20);
	     grid2.getColumnConstraints().addAll(col1,col2,col3, col4);
		
		SearchCriteria searchCriteria = new SearchCriteria();
		
		BorderPane titleHbox = NavigationBar.getHeaderPane(SEARCH, primaryStage);
		grid2.add(titleHbox, 0, 0, 20, 1);
		
		CheckBox paperCheck = new CheckBox("Search based on paper information");
		paperCheck.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid2.add(paperCheck, 0, 2);
		
		Label numberOfPapers = new Label("Number of minimum Publications:");
		grid2.add(numberOfPapers, 0, 3);
		
		TextField numberOfPapersField = new TextField();
		numberOfPapersField.setPromptText("Number of papers/articles");
		grid2.add(numberOfPapersField, 1, 3);
		
		Label nameOfConference = new Label("Conference published/not published for:");
		grid2.add(nameOfConference, 0, 4);
		
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Published in",
			        "Not published in"
			    );
		ComboBox publishComboBox = new ComboBox(options);
		publishComboBox.setValue("Published in");
		grid2.add(publishComboBox, 1, 4);
		
		publishComboBox.setOnAction(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
	        	String comboSelection = publishComboBox.getValue().toString();
			}
		});
		
		TextField confName = new TextField();
		confName.setPromptText("Conference name");
		
		final ObservableList<String> conferenceList = FXCollections.observableArrayList();
		ConferenceUtil.getConferences().forEach((conference) -> {
			conferenceList.add(conference);
		});

		 // Create the CheckComboBox with the data 
		final CheckComboBox<String> checkComboBox = new CheckComboBox<String>(conferenceList);
		grid2.add(checkComboBox, 2, 4);
		 
		checkComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c) {
				// TODO Auto-generated method stub
				System.out.println("checkComboBox"+checkComboBox.getCheckModel().getCheckedItems());
			}
		});

		Label yearRange = new Label("Year range:");
		grid2.add(yearRange, 0, 5);
		
		ObservableList<String> options1 = 
			    FXCollections.observableArrayList(
			        "between",
			        "before",
			        "after"
			    );
		ComboBox yearRangeComboBox = new ComboBox(options1);
		yearRangeComboBox.setValue("before");
		grid2.add(yearRangeComboBox, 1, 5);
		
		final TextField fromYear = new TextField();
		fromYear.setPromptText("Year");
		
	    final Label betweenYearLabel = new Label("to"); 
	    final TextField toYear = new TextField();
	    toYear.setPromptText("Year");
	    toYear.setDisable(true);
	    
	    grid2.add(fromYear, 2, 5);
	    grid2.add(betweenYearLabel, 3, 5);
	    grid2.add(toYear, 4, 5, 13, 1);
	    System.out.println(yearRangeComboBox.getValue());
	    
	    yearRangeComboBox.setOnAction(new EventHandler<ActionEvent>() {
	    	 
	        @Override
	        public void handle(ActionEvent e) {
	        	String yearRangeSelection = yearRangeComboBox.getValue().toString();
	            
	        	if(yearRangeSelection.equals("before") || yearRangeSelection.equals("after")){
	        		toYear.setDisable(true);
	        	}else{
	        		toYear.setDisable(false);
	        	}
	        }
	    });
	    
	    Label titleKeyword = new Label("Keyword or Title:");
		grid2.add(titleKeyword, 0, 6);
		
		TextField titleKeywordValue = new TextField();
		titleKeywordValue.setPromptText("Enter keyword or title");
		grid2.add(titleKeywordValue, 1, 6);
		
		//Add union condition
		final ToggleGroup unionGroup = new ToggleGroup();
		
		RadioButton radioButtonAnd = new RadioButton(AND_RADIO);
		radioButtonAnd.setToggleGroup(unionGroup);
		radioButtonAnd.setUserData(AND_RADIO);
		radioButtonAnd.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
		RadioButton radioButtonOr = new RadioButton(OR_RADIO);
		radioButtonOr.setUserData(OR_RADIO);
		radioButtonOr.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		radioButtonOr.setToggleGroup(unionGroup);
		
		HBox hBoxAndOr1 = new HBox();
		hBoxAndOr1.getChildren().addAll(radioButtonAnd, radioButtonOr);
		hBoxAndOr1.setSpacing(30);
		hBoxAndOr1.setAlignment(Pos.BOTTOM_CENTER);
		grid2.add(hBoxAndOr1, 0, 8, 20, 1);
		
		unionGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// TODO Auto-generated method stub

				if (unionGroup.getSelectedToggle() != null) {
					if(unionGroup.getSelectedToggle().getUserData().toString().equals(radioButtonOr.getText())){
						searchCriteria.setUnion(true);
					}else{
						searchCriteria.setUnion(false);
					}
	            }     
			}
			
		});
		
		CheckBox serviceCheck = new CheckBox("Search based on service information");
		serviceCheck.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid2.add(serviceCheck, 0, 10);
		
		Label servedInConference = new Label("Conference served/not served in:");
		grid2.add(servedInConference, 0, 11);
		
		ObservableList<String> serveOptions = 
			    FXCollections.observableArrayList(
			        "Served in",
			        "Not served in"
			    );
		ComboBox serveComboBox = new ComboBox(serveOptions);
		serveComboBox.setValue("Served in");
		grid2.add(serveComboBox, 1, 11);
		
		serveComboBox.setOnAction(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
	        	String serveComboSelection = serveComboBox.getValue().toString();
			}
		});
		
		final CheckComboBox<String> checkConfServedBox = new CheckComboBox<String>(conferenceList);
		 
		checkConfServedBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c) {
				// TODO Auto-generated method stub
				System.out.println("checkConfServedBox"+checkConfServedBox.getCheckModel().getCheckedItems());
			}
		});
		
		grid2.add(checkConfServedBox, 2, 11);
		
		Label yearRangeServed = new Label("Year range:");
		grid2.add(yearRangeServed, 0, 12);
		
		ObservableList<String> servedYearOptions = 
			    FXCollections.observableArrayList(
			        "between",
			        "before",
			        "after"
			    );
		ComboBox yearRangeServedComboBox = new ComboBox(servedYearOptions);
		yearRangeServedComboBox.setValue("before");
		grid2.add(yearRangeServedComboBox, 1, 12);
		
		final TextField fromYearServed = new TextField();
		fromYearServed.setPromptText("Year");
		
		final Label betweenYearServedLabel = new Label("to"); 
	    
		final TextField toYearServed = new TextField();
	    toYearServed.setPromptText("Year");
	    toYearServed.setDisable(true);
	    
	    grid2.add(fromYearServed, 2, 12);
	    grid2.add(betweenYearServedLabel, 3, 12);
	    grid2.add(toYearServed, 4, 12, 13, 1);
	    
	    yearRangeServedComboBox.setOnAction(new EventHandler<ActionEvent>() {
	    	 
	        @Override
	        public void handle(ActionEvent e) {
	        	String yearRangeServedSelection = yearRangeServedComboBox.getValue().toString();
	            
	            if(yearRangeServedSelection.equals("before") || yearRangeServedSelection.equals("after")){
	            	toYearServed.setDisable(true);
	            }else{
	            	toYearServed.setDisable(false);
	            }
	        }
	    });
	    
	    Label position = new Label("Position served as:");
		grid2.add(position, 0, 13);
		
		List<String> positions =new ArrayList<>(Arrays.asList("All", "General Chair", "Program Chair", "Conference Chair","External Review Committee"));
		ObservableList<String> positionOptions = FXCollections.observableArrayList(positions);
		
		ComboBox positionComboBox = new ComboBox(positionOptions);
		positionComboBox.setValue(positions.get(0));
		grid2.add(positionComboBox, 1, 13);
		
		//Add union condition
		/*final ToggleGroup unionGroup2 = new ToggleGroup();
		
		RadioButton radioButtonAnd2 = new RadioButton(AND_RADIO);
		radioButtonAnd2.setToggleGroup(unionGroup2);
		radioButtonAnd2.setUserData(AND_RADIO);
		radioButtonAnd2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
		RadioButton radioButtonOr2 = new RadioButton(OR_RADIO);
		radioButtonOr2.setUserData(OR_RADIO);
		radioButtonOr2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		radioButtonOr2.setToggleGroup(unionGroup2);
		
		HBox hBoxAndOr2 = new HBox();
		hBoxAndOr2.getChildren().addAll(radioButtonAnd2, radioButtonOr2);
		hBoxAndOr2.setSpacing(30);
		hBoxAndOr2.setAlignment(Pos.BOTTOM_CENTER);
		grid2.add(hBoxAndOr2, 0, 15, 20, 1);*/
		
		Separator separator = new Separator();
		separator.setOrientation(Orientation.HORIZONTAL);
		separator.setStyle("-fx-border-color: #b22222; -fx-border-width: 1 0 0 0 ;");
		grid2.add(separator, 0, 15, 20, 1);
		
		CheckBox authorCheck = new CheckBox("Search based on author name");
		authorCheck.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid2.add(authorCheck, 0, 17);
		
		Label authorNameLabel = new Label("Author Name:");
		grid2.add(authorNameLabel, 0, 18);
		
		TextField authorNameValue = new TextField();
		authorNameValue.setPromptText("Enter author name");
		grid2.add(authorNameValue, 1, 18);
		
		Button btn = new Button(SEARCH_AUTHORS);
		btn.setStyle("-fx-border-color: #b22222");
		
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbBtn.getChildren().add(btn);
		hbBtn.setSpacing(10);
		grid2.add(hbBtn, 1, 25);
		
		Scene searchScene = new Scene(grid2, SCENE_LENGTH, SCENE_WIDTH, Color.BEIGE);
		SceneStack.setCurrentScene(searchScene);
		primaryStage.setScene(searchScene);
		primaryStage.show();
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            	Paper paperInfo = null;
            	ServiceInfo serviceInfo = null;
            	String authorName = null;
            	
            	String isCriteriaValid = SearchSceneValidation.validateCriteria(paperCheck, serviceCheck, authorCheck);
            	
            	if(isCriteriaValid.equalsIgnoreCase(ValidationConstants.NO_CRITERIA_SELECTED)){
            		AlertUtil.displayAlert("Error", 
            								"Oops, you got soemthing wrong!", 
        									ValidationConstants.NO_CRITERIA_SELECTED);
            		return;
            	}else{
            		if(paperCheck.isSelected()){
            			
            			String conferenceSelection = checkComboBox.getCheckModel().getCheckedItems().toString();
            			
            			String isPaperInfoValid = SearchSceneValidation.validatePaperInfo(numberOfPapersField, 
            										conferenceSelection.substring(1, conferenceSelection.length() - 1).trim(),
													yearRangeComboBox,
													fromYear,
													toYear,
													titleKeywordValue);
            			
            			if(!isPaperInfoValid.equalsIgnoreCase(ValidationConstants.VALID_PAPER_CRITERIA)){
            				AlertUtil.displayAlert("Error", "Oops, you got soemthing wrong!", isPaperInfoValid);
            				return;
            			}else{
            				//set paper info bean
            				paperInfo = setPaperInformation(numberOfPapersField, 
															publishComboBox,
															conferenceSelection.substring(1, conferenceSelection.length() - 1).trim(),
															yearRangeComboBox,
															fromYear,
															toYear,
															titleKeywordValue);
            			}
            				
            		}
            		
            		if(serviceCheck.isSelected()){
            			
            			String conferenceServedSelection = checkConfServedBox.getCheckModel().getCheckedItems().toString();

            			String isServiceInfoValid = SearchSceneValidation.validateServiceInformation(conferenceServedSelection.substring(1, conferenceServedSelection.length() - 1),
														yearRangeServedComboBox,
														fromYearServed,
														toYearServed);
            			
            			if(!isServiceInfoValid.equalsIgnoreCase(ValidationConstants.VALID_SERVICE_CRITERIA)){
            				AlertUtil.displayAlert("Error", "Oops, you got soemthing wrong!", isServiceInfoValid);
            				return;
            			}else{
            				//set paper info bean
            				serviceInfo = setServiceInformation(serveComboBox,
            													conferenceServedSelection.substring(1, conferenceServedSelection.length() - 1),
        														positionComboBox,
																yearRangeServedComboBox,
																fromYearServed,
																toYearServed);
            			}
            		}
            		
            		if(authorCheck.isSelected()){
            			
            			String isAuthorValid = SearchSceneValidation.validateAuthorCriteria(authorNameValue);
            			if(isAuthorValid.equalsIgnoreCase(ValidationConstants.VALID_AUHTOR)){
            				authorName = authorNameValue.getText();
            			}else{
            				AlertUtil.displayAlert("Error", "Oops, you got soemthing wrong!", isAuthorValid);
            				return;
            			}
            		}
            	}
            		
        		searchCriteria.setPaperInfo(paperInfo);
        		searchCriteria.setServiceInfo(serviceInfo);
        		searchCriteria.setAuthorName(authorName);
        		
    			SearchService searchService = new SearchServiceImpl();
        		List<Author> authors = new ArrayList<Author>();
				try {
					authors = searchService.searchAuthorsByCriteria(searchCriteria);
					
					if(!authors.isEmpty() && authors.size() != 0){
						SceneStack.pushSceneToStack(searchScene);
						ResultScene.displayResultScene(authors,primaryStage);
						
					}else{
						AlertUtil.displayAlert("Error", 
								"Oops, you got soemthing wrong!", 
								ValidationConstants.NO_AUTHORS_FOUND);
						return;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					AlertUtil.displayAlert("Error", 
											"Oops, you got soemthing wrong!", 
											ValidationConstants.SQL_FAILURE);
					return;
				}
        	}
		});
	}
		
	public static Paper setPaperInformation(TextField numberOfPapersField,
											ComboBox publishComboBox,
											String confName,
											ComboBox yearRangeComboBox,
											TextField fromYear,
											TextField toYear,
											TextField titleKeywordValue){
    		
		Paper paperInfo = new Paper();
		
		//Set minimum number of papers
		if(!numberOfPapersField.getText().isEmpty()){
			paperInfo.setNumOfPapersPublished(Integer.parseInt(numberOfPapersField.getText()));
		}
		
		//Set published or not published
		if(publishComboBox.getValue().equals("Published in")){
			paperInfo.setPublished(true);
		}else{
			paperInfo.setPublished(false);
		}
		
		//Set conference name
		paperInfo.setConferenceName(confName);
		
		//Set date options
		paperInfo.setOptions(yearRangeComboBox.getValue().toString());
		
		//Set start date and/or end date
		if(!fromYear.getText().isEmpty() || !toYear.getText().isEmpty())
			if(yearRangeComboBox.getValue().equals("between")){
				if(!fromYear.getText().isEmpty()) 
					paperInfo.setStartDate(Integer.parseInt(fromYear.getText()));
				if(!toYear.getText().isEmpty()) 
					paperInfo.setEndDate(Integer.parseInt(toYear.getText()));
			}else if(yearRangeComboBox.getValue().equals("before") 
					|| yearRangeComboBox.getValue().equals("after")){
				if(!fromYear.getText().isEmpty()) {
					paperInfo.setStartDate(Integer.parseInt(fromYear.getText()));
					paperInfo.setEndDate(Integer.parseInt(fromYear.getText()));
				}
		}
		
		//Set keyword
		paperInfo.setKeyword(titleKeywordValue.getText());
		
		return paperInfo;
	}	
	
	public static ServiceInfo setServiceInformation(ComboBox serveComboBox,
													String confNameServedIn,
													ComboBox positionComboBox,
													ComboBox yearRangeServedComboBox,
													TextField fromYearServed,
													TextField toYearServed){
		
		ServiceInfo serviceInfo = new ServiceInfo();
		
		//Set has served of not
		if(serveComboBox.getValue().toString().equalsIgnoreCase("Served in")){
    		serviceInfo.setHasServed(true);
		}else{
			serviceInfo.setHasServed(false);
		}
		
		//Set conference name
		serviceInfo.setConferenceName(confNameServedIn);
		
		//set options
		serviceInfo.setOptions(yearRangeServedComboBox.getValue().toString());
		
		//Set position
		String position = positionComboBox.getValue().toString();
		switch(position){
			case "All": serviceInfo.setPosition(PositionAlias.ALL_ALIAS);
				break;
			case "General Chair": serviceInfo.setPosition(PositionAlias.GENERAL_CHAIR_ALIAS);
				break;
			case "Program Chair": serviceInfo.setPosition(PositionAlias.PROGRAM_CHAIR_ALIAS);
				break;
			case "Conference Chair": serviceInfo.setPosition(PositionAlias.CONFERENCE_CHAIR_ALIAS);
				break;
			case "External Review Committee": serviceInfo.setPosition(PositionAlias.EXTERNAL_REVIEW_ALIAS);
				break;
			default : serviceInfo.setPosition(PositionAlias.ALL_ALIAS);
		}
		
		//Set start date and/or end date
		if(!fromYearServed.getText().isEmpty() || !toYearServed.getText().isEmpty()){
			if(yearRangeServedComboBox.getValue().equals("between")){
				serviceInfo.setStartDate(Integer.parseInt(fromYearServed.getText()));
				serviceInfo.setEndDate(Integer.parseInt(toYearServed.getText()));
			}else if(yearRangeServedComboBox.getValue().equals("before") 
					|| yearRangeServedComboBox.getValue().equals("after")){
				if(!fromYearServed.getText().isEmpty()) {
					serviceInfo.setStartDate(Integer.parseInt(fromYearServed.getText()));
					serviceInfo.setEndDate(Integer.parseInt(fromYearServed.getText()));
				}
				//serviceInfo.setStartDate(Integer.parseInt(fromYearServed.getText()));
				//serviceInfo.setStartDate(Integer.parseInt(toYearServed.getText()));
			}
		}
		
		return serviceInfo;
	}
	
}

package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ControllerEdit {


    @FXML
    javafx.scene.image.ImageView imageView1 = new javafx.scene.image.ImageView();
    @FXML
    ChoiceBox choices;
    @FXML
    public Image images;
    @FXML
    public Image editedImage;
    @FXML
    public Button rotateButton;
    @FXML
    public Image originalImage;
    @FXML
    static ImageView editView;
    @FXML
    public Button flipButton;
    @FXML
    public ChoiceBox  choiceAspectRatio;
    @FXML
    public Slider sliderBright;
    @FXML
    public Pane paneImage;

    @FXML
    void setEditImage(Image image) {

        images = image;
        imageView1.setImage(image);
    }

    @FXML
    public void onChoiceClicked(MouseEvent event) {

        choices.getSelectionModel().selectedItemProperty()
                .addListener((v, oldVal, newVal) -> effect(images, (String) newVal));
    }

    //The below method is used to add Rotate functionality to the image on the click of Rotate button
    @FXML
    public void onRotateButtonClicked(ActionEvent event) {

        rotateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double value = imageView1.getRotate();
                imageView1.setRotate(value + 30);
            }
        });

    }

    //This method is used add the effects to the image
    public void effect(Image image, String value) {

        switch (value) {
            case "Sepia":
                SepiaTone sepiaTone = new SepiaTone();
                imageView1.setEffect(sepiaTone);
                break;
            case "Bloom":
                Bloom bloomView = new Bloom();
                imageView1.setEffect(bloomView);
                break;
            case "Glow":
                Glow glowView = new Glow();
                imageView1.setEffect(glowView);
                break;
            case "Blend":
                Blend blendView = new Blend();
                imageView1.setEffect(blendView);
                break;
            case "GaussianBlur":
                GaussianBlur gaussianBlurView = new GaussianBlur();
                imageView1.setEffect(gaussianBlurView);
                break;
            case "Lighting":
                Lighting lightingView = new Lighting();
                imageView1.setEffect(lightingView);
                break;
            case "Reflection":
                Reflection reflectionView = new Reflection();
                imageView1.setEffect(reflectionView);
                break;
            default:
                System.out.println("Choice Does Not Exist");
                break;
        }
        editedImage = imageView1.getImage();

    }

    //This method is used to rotate the image in anti-clockwise
    public void flipButtonClicked(ActionEvent event) {

        flipButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double value = imageView1.getRotate();
                imageView1.setRotate(value + 180);
            }
        });

    }

    //This method triggers an event to change the size of the image
    public void choiceAspectRatioClicked(MouseEvent event) {

        choiceAspectRatio.getSelectionModel().selectedItemProperty()
                .addListener((v, oldVal, newVal) -> aspectRatio(images, (String) newVal));

    }

    //This method changes the size of the image
    public void aspectRatio(Image image,String value){

        switch(value){
            case "Square":
                imageView1.setFitWidth(500);
                imageView1.setFitHeight(500);
                imageView1.setImage(image);
                break;
            case "Wide":
                imageView1.setFitWidth(700);
                imageView1.setFitHeight(500);
                imageView1.setImage(image);
                break;
            case "16:9":
                imageView1.setFitWidth(1000);
                imageView1.setFitHeight(900);
                imageView1.setImage(image);
                break;
            case "7:5":
                imageView1.setFitWidth(700);
                imageView1.setFitHeight(500);
                imageView1.setImage(image);
                break;
            default:
                System.out.println("Choice Does Not Exist");
                break;
        }
    }

    //This method changes the scene to a new scene to save the image on click of Save image
    public void saveImageAction(MouseEvent mouseEvent) throws IOException {

        ControllerEdit.editView = imageView1;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("save.fxml"));

        Parent saveView = loader.load();
        Scene save = new Scene(saveView);

        //Stage Information
        Stage window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        window.setScene(save);
        window.show();
    }

    //This method is used to change the brightness of the image
    public void sliderEvent(MouseEvent event){
        Double valueBrightness = sliderBright.valueProperty().doubleValue();
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(valueBrightness);
        imageView1.setEffect(colorAdjust);
    }
    //This method cancels the changes made to the image
    public void cancelButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("edit.fxml"));

        Parent editView = loader.load();
        Scene edit = new Scene(editView);

        //Stage Information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();

        FXMLLoader loaders = new FXMLLoader();
        loaders.setLocation(getClass().getResource("sample.fxml"));

        Controller controller= loaders.getController();

        Image image = new Image(new File(controller.str).toString());
        ControllerEdit controllerEdit=loader.getController();
        controllerEdit.setEditImage(image);
    }
}

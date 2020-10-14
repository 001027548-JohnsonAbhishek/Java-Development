package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

public class Controller {

    static String  str;
    @FXML
    public TextArea textArea;

    @FXML
    public Pane paneview;

    @FXML
    private Pane filterView;

    @FXML
    public Image setImage;


    /*
        diplayImage()-This method triggers an event to diplay the image on the click
        of upload button
    */
    @FXML
    void displayimage(ActionEvent event) {
        paneview.getChildren().clear();

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\njabh\\JavaProject\\images"));
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile!=null) {
           Image image = new Image("file:///"+selectedFile.getAbsolutePath(), 100, 100, false, false);
           javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(image);
            str="file:///"+selectedFile.getAbsolutePath();
            this.thumbNail(image);
            this.readPixelsInfo(image);

            imageView.setFitWidth(300);
            imageView.setFitHeight(300);
            paneview.getChildren().add(imageView);
        }
    }

    //This method is used to display the properties of image
    @FXML
    void readPixelsInfo(Image image){

        PixelReader pixelReader = image.getPixelReader();

            if(pixelReader == null){
                System.out.println("Cannot read the pixels from the image");
            }

        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        double progress = image.getProgress();


        this.writeInfoMessage("Image Width: " + width);
        this.writeInfoMessage("Image Height: " + height);
        this.writeInfoMessage("Progress: " + progress);
    }

    //This method displays the image properties in a text box
    private void writeInfoMessage(String msg)
    {
        this.textArea.appendText(msg + "\n");
    }

    /* changeScreenButton()-This method is used to change the current scene to edit
        scene on click of edit button
   */
    public void changeScreenButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("edit.fxml"));

        Parent editView = loader.load();
        Scene edit = new Scene(editView);

        //Stage Information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(edit);
        window.show();

        Image image = new Image(new File(str).toString());
        ControllerEdit controllerEdit=loader.getController();
          controllerEdit.setEditImage(image);


    }

    //Display the image in 100*100 thumbnails
    public void thumbNail(Image image){
        ImageView sephiaView= new ImageView(image);
        sephiaView.setFitWidth(100);
        sephiaView.setFitHeight(100);

        filterView.getChildren().add(sephiaView);
    }

}

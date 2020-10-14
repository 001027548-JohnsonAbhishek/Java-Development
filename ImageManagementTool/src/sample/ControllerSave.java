package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControllerSave {

    @FXML
    private ChoiceBox formatChoice;

    @FXML
    private TextField fileName;

    ImageView preserveImageView ;

    //This method calls the getFromatChoiceValue() which captures the file format chosen by the user
    public void choiceFormat(javafx.scene.input.MouseEvent mouseEvent) {
        getFormatChoiceValue();
    }


    public  String getFormatChoiceValue(){
        return (String)formatChoice.getValue();
    }

    //This method calls the getFileName() which captures the file name chose by the user
    public void fileName(ActionEvent actionEvent){
         getFileName();
    }

    public String getFileName(){
        return fileName.getText();
    }

    public void buttonAction(ActionEvent event) throws IOException {
        saveImage();

        FXMLLoader loaders = new FXMLLoader();
        loaders.setLocation(getClass().getResource("sample.fxml"));
        Controller controller= loaders.getController();

        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        // set content text
        a.setContentText(this.getFileName()+this.getFormatChoiceValue()+"  saved to the location:"+
                        "\n"+controller.str);

        // show the dialog
        a.show();

    }

    //This method is used to save a file to its destination on click of save button
    public void saveImage(){

        preserveImageView=ControllerEdit.editView;
        String format = this.getFormatChoiceValue();
        String fileName = this.getFileName();
        File outputFile = new File("C:\\Users\\njabh\\JavaProject\\"+fileName+format);
        BufferedImage bImage = SwingFXUtils.fromFXImage(preserveImageView.snapshot(null, null),null);

        try {
            ImageIO.write(bImage,"png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

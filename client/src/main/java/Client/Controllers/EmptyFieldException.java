package Client.Controllers;

import javafx.scene.control.TextField;

class EmptyFieldException extends Exception{
    private TextField emptyField;
    EmptyFieldException(TextField t){
        super();
        emptyField = t;
    }

    TextField getEmptyField() {
        return emptyField;
    }
}

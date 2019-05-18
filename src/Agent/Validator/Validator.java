package TravelExperts.Agent.Validator;

import javafx.scene.control.TextField;

import javax.swing.*;

public class Validator {

    //method to check if the value exists in the textfield
    public static boolean IsProvided(TextField textField, String msg) {
        boolean result = true;
        if (textField == null){
            result = false;
            JOptionPane.showMessageDialog(null, msg, "Empty Fields Error",JOptionPane.ERROR_MESSAGE);
            textField.requestFocus();
        }

        return result;
    }

    //method to check if the value entered in the textfiled is Integer
    public static boolean IsInt(TextField textField, String msg) {
        boolean result = true;
        try{
            int i = Integer.parseInt(textField.toString());

        }catch (NumberFormatException | NullPointerException ex){
            result = false;
            JOptionPane.showMessageDialog(null, msg, "Empty Fields Error",JOptionPane.ERROR_MESSAGE);
            textField.requestFocus();
        }
        return result;
    }

    //method to check if the value entered in the textfiled is integer & positive
    public static boolean IsNonNegative(TextField textField, String msg) {
        boolean result = true;
        try{
            int i = Integer.parseInt(textField.toString());
            if(i<0)
            {
                result = false;
            }
        }catch (NumberFormatException | NullPointerException ex){
            result = false;
            JOptionPane.showMessageDialog(null, msg, "Empty Fields Error",JOptionPane.ERROR_MESSAGE);
            textField.requestFocus();
        }
        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfcharacter;

import java.io.File;
import javafx.event.ActionEvent;

/**
 *
 * @author thomasnewman
 */
public interface Character {
    String getName();
    String getRace();
    void handleOpen(ActionEvent event);
    void handleSave(ActionEvent event);
    void displayExceptionAlert(Exception ex);
    String add();
    void clear();
}

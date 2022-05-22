package helpers;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {
    public boolean isCellEditable(int row, int column){
        return false;
    }
}

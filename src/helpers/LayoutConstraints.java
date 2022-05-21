package helpers;

import java.awt.*;

public class LayoutConstraints extends GridBagConstraints {
    private GridBagConstraints gridBagConstraints;

    public LayoutConstraints() {
        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.insets = new Insets(10, 10, 0, 10);
        this.gridBagConstraints.anchor = GridBagConstraints.WEST;
    }

    public GridBagConstraints constraints() {
        return gridBagConstraints;
    }

    public void addLine() {
        this.gridBagConstraints.gridy = this.gridBagConstraints.gridy + 1;
        this.gridBagConstraints.gridx = 0;
    }

    public void addColumn() {
        this.gridBagConstraints.gridx = this.gridBagConstraints.gridx + 1;
    }
}

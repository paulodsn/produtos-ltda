package helpers;

import java.awt.*;

public class LayoutConstraints extends GridBagConstraints {
    private GridBagConstraints gridBagConstraints;

    public LayoutConstraints() {
        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.insets = new Insets(10, 10, 10, 10);
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

    public void allSpace() {
        this.gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.gridBagConstraints.weightx = 1.0;
        this.gridBagConstraints.weighty = 1.0;
    }

    public void dynamicSpace() {
        this.gridBagConstraints.weightx = 0;
        this.gridBagConstraints.weighty = 0;
    }
}

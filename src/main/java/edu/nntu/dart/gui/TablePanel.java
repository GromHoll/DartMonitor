package edu.nntu.dart.gui;

import edu.nntu.dart.entity.WavePoint;
import edu.nntu.dart.out.WaveOutput;

import javax.swing.*;
import java.util.List;

/**
 * Created by Nikita Pavlov on 23.10.13
 */
public class TablePanel implements WaveOutput {
    private JPanel mainPanel;
    private JTable infoTable;
    private WaveTableModel tableModel;

    public TablePanel() {
        tableModel = new WaveTableModel();
        infoTable.setModel(tableModel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void output(List<WavePoint> list) {
        tableModel.setData(list);
    }
}

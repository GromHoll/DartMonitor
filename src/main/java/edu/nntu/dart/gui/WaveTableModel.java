package edu.nntu.dart.gui;

import edu.nntu.dart.entity.WavePoint;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaveTableModel extends DefaultTableModel {

    private List<WavePoint> wavePoints = new ArrayList<>();

    private static final String columnNames[] = {"Height", "Date"};

    public void setData(List<WavePoint> list) {
        if(list != null) {
            wavePoints = list;
            fireTableDataChanged();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return Double.class;
            default:
                return String.class;
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if(columnIndex < 0 || columnIndex >= columnNames.length) {
            return "";
        } else {
            return columnNames[columnIndex];
        }
    }

    @Override
    public int getRowCount() {
        return (wavePoints != null) ? wavePoints.size() : 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        WavePoint wavePoint = wavePoints.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return wavePoint.getHeight();
            case 1:
                return wavePoint.getDate().toString();
        }

        return "";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

}

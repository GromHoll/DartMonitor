package edu.nntu.dart.gui;

import edu.nntu.dart.entity.WavePoint;
import edu.nntu.dart.out.WaveOutput;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Nikita Pavlov on 23.10.13
 */
public class WaveChartPanel implements WaveOutput {
    private JPanel mainPanel;

    private WaveXYDataSet dataSet = new WaveXYDataSet();

    public WaveChartPanel() {
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Wave", "Time",
                "Height", dataSet, false, true, false);
        ChartPanel chartPanel = new ChartPanel(chart, true);
        mainPanel.add(chartPanel, BorderLayout.CENTER);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void output(List<WavePoint> list) {
        dataSet.setData(list);
    }
}

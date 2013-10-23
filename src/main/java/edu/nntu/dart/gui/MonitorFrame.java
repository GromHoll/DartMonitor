package edu.nntu.dart.gui;

import edu.nntu.dart.entity.WavePoint;
import edu.nntu.dart.out.WaveOutput;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class MonitorFrame extends JFrame implements WaveOutput, ServerListener {

    private Icon red_icon;
    private Icon green_icon;

    private JPanel mainPanel;
    private JLabel imageLabel;
    private JLabel statusLabel;
    private JLabel lastUpdateLabel;
    private JPanel visualPanel;

    public MonitorFrame(String name) {
        super(name);
        init();
    }

    private void init() {
        red_icon   = loadIcon("/edu/nntu/images/red.png");
        green_icon = loadIcon("/edu/nntu/images/green.png");

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAvailable(false);
    }

    public void setVisualPanel(JPanel vp) {
        visualPanel.add(vp);
        this.pack();
    }

    private Icon loadIcon(String path) {
        try {
            return new ImageIcon(getClass().getResource(path));
        } catch(Exception exc) {
            System.out.println("[ERROR] Image " + path + " not found.");
            return null;
        }
    }

    private void setServerState(Icon icon, String info) {
        imageLabel.setIcon(icon);
        statusLabel.setText(info);
    }

    @Override
    public void setAvailable(boolean isAvailable) {
        if(isAvailable) {
            setServerState(green_icon, "Available");
        } else {
            setServerState(red_icon, "Not available");
        }
    }

    @Override
    public void output(List<WavePoint> list) {
        lastUpdateLabel.setText(new Date().toString());
    }
}

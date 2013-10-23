package edu.nntu.dart;

import edu.nntu.dart.data.DartDataParser;
import edu.nntu.dart.data.DataLoader;
import edu.nntu.dart.data.DataParser;
import edu.nntu.dart.data.UrlDataLoader;
import edu.nntu.dart.entity.WavePoint;
import edu.nntu.dart.gui.MonitorFrame;
import edu.nntu.dart.gui.ServerListener;
import edu.nntu.dart.gui.TablePanel;
import edu.nntu.dart.out.WaveOutput;
import edu.nntu.dart.out.FileOutput;

import java.util.*;

public class DartController extends Thread {

    private static final String DART_URL = "http://www.ndbc.noaa.gov/data/realtime2/21402.dart";

    private static final int TIMEOUT = 60000;

    private boolean isNeedStop = false;
    
    private DataLoader dataLoader;
    private DataParser dataParser;

    private Set<WavePoint> wavePointSet = new HashSet<>();

    private List<ServerListener> serverListeners = new LinkedList<>();
    private List<WaveOutput> waveOutputs = new LinkedList<>();

    public DartController() {
        init();
    }

    private void init() {
        dataLoader = new UrlDataLoader(DART_URL);
        dataParser = new DartDataParser();
        
        FileOutput fo = new FileOutput("out.txt");
        MonitorFrame mf = new MonitorFrame("WavePoint monitor");
        TablePanel tablePanel = new TablePanel();
        mf.setVisualPanel(tablePanel.getMainPanel());
        mf.setVisible(true);

        serverListeners.add(mf);
        waveOutputs.add(fo);
        waveOutputs.add(tablePanel);
        waveOutputs.add(mf);
    }

    @Override
    public void run() {
        while(!isNeedStop) {
            String data = dataLoader.getData();
            List<WavePoint> wavePoints = dataParser.parseData(data);
            processEarthquakes(wavePoints);
            notifyServerListener(dataLoader.isAvailable());
            sleep();
        }
    }

    private void sleep() {
        try {
            sleep(TIMEOUT);
        } catch(InterruptedException exc) {
            isNeedStop = true;
        }
    }

    private void processEarthquakes(List<WavePoint> wavePoints) {
        wavePointSet.addAll(wavePoints);
        notifyOutputs();
    }

    private void notifyOutputs() {
        List<WavePoint> wavePoints = new ArrayList<>(wavePointSet);
        Collections.sort(wavePoints, new Comparator<WavePoint>() {
            public int compare(WavePoint o1, WavePoint o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        for(WaveOutput eqo : waveOutputs) {
            eqo.output(wavePoints);
        }
    }

    private void notifyServerListener(boolean isAvailable) {
        for(ServerListener sl : serverListeners) {
            sl.setAvailable(isAvailable);
        }
    }
}

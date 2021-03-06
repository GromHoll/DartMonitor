package edu.nntu.dart.gui;

import edu.nntu.dart.entity.WavePoint;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYDatasetTableModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaveXYDataSet extends AbstractXYDataset {
    private List<WavePoint> wavePoints = new ArrayList<>();

    private static final long MIN_INTERVAL = 900; /* 15 min */

    public void setData(List<WavePoint> list) {
        if(list != null) {
            wavePoints = list;
            fireDatasetChanged();
        }
    }

    @Override
    public int getSeriesCount() {
        return 1;
    }

    @Override
    public Comparable getSeriesKey(int series) {
        return 0;
    }

    @Override
    public int getItemCount(int series) {
        return wavePoints.size();
    }

    @Override
    public Number getX(int series, int item) {
        return wavePoints.get(item).getDate().getTime();
    }

    @Override
    public Number getY(int series, int item) {
        return wavePoints.get(item).getHeight();
    }
}

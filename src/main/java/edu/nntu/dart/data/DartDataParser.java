package edu.nntu.dart.data;

import edu.nntu.dart.entity.WavePoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class DartDataParser implements DataParser {


    @Override
    public List<WavePoint> parseData(String data) {
        ArrayList<WavePoint> wavePoints = new ArrayList<>();

        String waveStrings[] = data.split("\n");

        for(String s : waveStrings) {
            WavePoint wp = parseWavePoint(s);
            if(wp != null) {
                wavePoints.add(wp);
            }
        }

        return wavePoints;
    }

    private WavePoint parseWavePoint(String data) {
        WavePoint wavePoint = null;

        try {
            String tokens[] = data.split(" ");

            Date date = new Date(Integer.parseInt(tokens[0]) - 1900,
                                 Integer.parseInt(tokens[1]),
                                 Integer.parseInt(tokens[2]),
                                 Integer.parseInt(tokens[3]),
                                 Integer.parseInt(tokens[4]),
                                 Integer.parseInt(tokens[5]));
            double height = Double.parseDouble(tokens[7]);
            wavePoint = new WavePoint(date, height);
        } catch(Exception exc) {

        }
        return wavePoint;
    }


}

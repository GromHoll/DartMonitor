package edu.nntu.dart.data;

import edu.nntu.dart.entity.WavePoint;

import java.util.List;

public interface DataParser {
    public List<WavePoint> parseData(String data);
}

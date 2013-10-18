package edu.nntu.dart.out;

import edu.nntu.dart.entity.WavePoint;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class FileOutput implements WaveOutput {

    private final String fileName;

    public FileOutput(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void output(List<WavePoint> list) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(fileName));
            for(WavePoint wp : list) {
                ps.println(wp.getDate() + " : " + wp.getHeight());
            }
        } catch(IOException exc) {
            if(ps != null) {
                ps.close();
            }
        }

    }
}

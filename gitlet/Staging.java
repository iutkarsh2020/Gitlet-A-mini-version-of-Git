package gitlet;

import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;
import java.util.ArrayList;
/*
represents staging area for gitlet
 */
public class Staging implements Serializable {
    ArrayList<String> addition;
    ArrayList<String> removal;
    public Staging(){
        addition = new ArrayList<>();
        removal = new ArrayList<>();
    }
}

package gitlet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
/*
the filetracked has hashmap as blobs
key - content of the file
value - path of the file according to sta1
 */
public class Commit implements Serializable {
    String message;
    String  time;
    String parent;
    HashMap<String,String> filetracked;// it will be list of blobs
    public Commit(String parent,String message,String timestamp){
        this.message = message;
        this.parent = parent;
        time = timestamp;
        // will have to see what to do about the list of blobs
        filetracked = new HashMap<>();
    }
}

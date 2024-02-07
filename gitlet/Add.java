package gitlet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Add {
    public static void add(String name){
        // lets 1st find the old commit as an object
        File f = new File(Main.headLocation);
        // now f contains the head commit location
        Commit head = Utils.readObject(f,Commit.class);
        // now 1st this commit object should check if file with name - name is present in this commit of not
        HashMap<String,String> map = head.filetracked;
        File newfile = new File(name);
        String contents = Utils.readContentsAsString(newfile);
        //contents contain whatever is present inside the file now
        //ArrayList<String> bloob =
        if(!map.containsKey(name)){
            // old commit does not contain this file so it should be added
            map.put(name,contents);
        }
    }
}

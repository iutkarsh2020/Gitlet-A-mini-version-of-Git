package gitlet;

import net.sf.saxon.style.XSLMergeAction;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;

/** Driver class for Gitlet, the tiny stupid version-control system.
 *  @author
 */
public class Main{
    public static String commitLocation = ".gitlet/Commit";
    // at this location,you will have a staging area object
    public static String stagingareaLocation = ".gitlet/stagingarea.txt";
    // at this location, you will have a blob object
    public static String blobsLocation = ".gitlet/blob.txt";
    public static String masterLocation = ".gitlet/master.txt";
    public static String headLocation = ".gitlet/head.txt";
    public static String head,master;
    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    /*
    This will make diretories in the .gitlet folder. will make a new data structure of blow,staging area and will write the object on the
    destinations.
     */
    public static void init() throws Exception{
        File git = new File(".gitlet");
        git.mkdir();
        // .gitlet file will be created
        File commit = new File(".gitlet/Commit");
        commit.mkdir();
        //inside .gitlet directory, Commit directory has been created
        File stage = new File(".gitlet/stagingarea.txt");
        // stagingarea file has been created inside the
        File blob = new File(".gitlet/blob.txt");
        stage.createNewFile();
        blob.createNewFile();
        // blob file will contain object of blob
        // now will make object of all these and will put in these files
        Commit first = new Commit(null,"Initial commit","00:00:00 UTC, Thursday, 1 January 1970");
        // during init time,it will not contain any blobs
        byte[] serialisedfirst = Utils.serialize(first);
        String name = Utils.sha1(serialisedfirst); // will give the SHA1 hashing of the 1st commit
        File thiscommit = new File(".gitlet/Commit/"+name+".txt");
        //change the location of master and heaed pointer
        changePointers(first);
        Utils.writeObject(thiscommit,first);
        // commit as an object is written to .gitlet/Commit/"+name+".txt
        Staging st = new Staging();
        File f = new File(stagingareaLocation);
        Utils.writeObject(f,st);
        //*********see difference between this blob and the blobs present  in commits
        Blobs b = new Blobs();
        f = new File(blobsLocation);
        Utils.writeObject(f,b);
        // in the blobs location we have now access to the arraylist of blobs
    }
    public static void changePointers(Commit c) throws IOException {
        File f = new File(masterLocation);
        if(f.exists())
            f.delete();
        f.createNewFile();
        Utils.writeObject(f,c);
        f = new File(headLocation);
        if(f.exists())
            f.delete();
        f.createNewFile();
        Utils.writeObject(f,c);
    }
    public static void main(String... args) throws Exception{

        // FILL THIS IN
        if(args[0].equals("init")){
            if(args.length>1){
                // throw error
                System.out.println("Invalid Command line argument");
            }
            else if(new File(".gitlet").exists()){
                // means init command is useless in this case,gitlet is already working in this directory
                System.out.println("A Gitlet version-control system already exists in the current directory.");
            }
            else{
                init();
            }
        }
        else if(args[0].equals("exit")){
            File f = new File("abc");
            f.delete();
            System.out.println(f);
        }
        else if(args[0].equals("add")){
            if(args.length<2) {
                System.exit(0);
            }
            if(!(new File(args[1]).exists())){
                // file does not exists
                System.out.println("File does not exist");
                System.exit(0);
            }
            Add.add(args[1]);
        }
        else if(args[0].equals("commit")){

        }

    }

}

package vcs;
import java.util.ArrayList;

public final class Staging {
    private ArrayList<String> touch = new ArrayList<String>();
    private ArrayList<String> remove = new ArrayList<String>();
    private ArrayList<String> mkdir = new ArrayList<String>();
    private ArrayList<String> writeToFile = new ArrayList<String>();
    private ArrayList<String> cd = new ArrayList<String>();
    /*
       each trackable command has an arrayList (+ getters and setters,
       it helps at vcs status operation when I have to print
       the operations from staging
     */

    public void setTouch(String s) {
        touch.add(s);
    }
    public void setRemove(String s) {
        remove.add(s);
    }
    public void setMkdir(String s) {
        mkdir.add(s);
    }
    public void setWriteToFile(String s) {
        writeToFile.add(s);
    }
    public void setCd(String s) {
        cd.add(s);
    }
    public ArrayList<String> getTouch() {
        return touch;
    }
    public ArrayList<String> getRemove() {
        return remove;
    }
    public ArrayList<String> getMkdir() {
        return mkdir;
    }
    public ArrayList<String> getWriteToFile() {
        return writeToFile;
    }
    public ArrayList<String> getCd() {
        return cd;
    }
    /*
     * clear() method to clear the arrays from staging
     * after commit or rollback;
     * isEmpty() method, it helps at commit operation
     * to test if it has something to commit
     */
    public void clear() {
        touch.clear();
        remove.clear();
        mkdir.clear();
        writeToFile.clear();
        cd.clear();
    }
    public boolean isEmpty() {
        return (touch.isEmpty() && remove.isEmpty()
                && mkdir.isEmpty() && writeToFile.isEmpty() && cd.isEmpty());
    }
}

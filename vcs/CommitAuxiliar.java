/*
 * @Copyright Bogdan Negru
 */
package vcs;

import filesystem.FileSystemSnapshot;
import utils.OutputWriter;
import utils.IDGenerator;
/*
 * in this class are the characteristics of a commit
 * with id, message and a clone of the file system
 * snapshot at the moment when I created this commit
 */
public final class CommitAuxiliar {
    private int id;
    private String message;
    private FileSystemSnapshot files;

    public CommitAuxiliar(FileSystemSnapshot buff, String msg,
             OutputWriter out) {
        this.files = buff.cloneFileSystem();
        this.id = IDGenerator.generateCommitID();
        this.message = msg;
    }

    public int getId() {
        return this.id;
    }
    public String getMesage() {
        return this.message;
    }
    public FileSystemSnapshot getFile() {
        return this.files;
    }
    public void setFileSystemSnapshot(FileSystemSnapshot buff) {
        this.files = buff.cloneFileSystem();
    }
}

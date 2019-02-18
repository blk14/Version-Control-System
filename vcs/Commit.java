/*
 * @Copyright Bogdan Negru
 */
package vcs;
/*
 * In this class I have an array of commits
 * it is used by a branch instance
 */
import filesystem.FileSystemSnapshot;
import java.util.ArrayList;
import utils.OutputWriter;

public final class Commit {
  private ArrayList<CommitAuxiliar> commits;

  public Commit() {
    commits = new ArrayList<CommitAuxiliar>();
  }

  public void addCommit(FileSystemSnapshot files, String msg,
              OutputWriter out) {
      CommitAuxiliar aux = new CommitAuxiliar(files, msg, out);
      commits.add(aux);
   }

    public ArrayList<CommitAuxiliar> getCommits() {
        return this.commits;
    }
}

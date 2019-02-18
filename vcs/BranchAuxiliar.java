/*
 * @Copyright Bogdan Negru
 */

package vcs;

/*
 * this class has the characteristics of a
 * branch with a name and an object with access
 * at an array of commits
 */
public final class BranchAuxiliar {
  private Commit commit;
  private String name;

  BranchAuxiliar(Commit c, String name) {
    this.commit = c;
    this.name = name;
  }

  public Commit getCommit() {
    return this.commit;
  }

  public String getName() {
    return this.name;
  }
}

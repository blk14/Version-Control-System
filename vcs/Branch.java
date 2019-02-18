/*
 * @Copyright Bogdan Negru
 */
package vcs;

import java.util.ArrayList;

/*
 * this class contains an array with existing
 * branches in file system
 */
public final class Branch {
  private ArrayList<BranchAuxiliar> branches;

  public Branch() {
    branches = new ArrayList<BranchAuxiliar>();
  }

  public void addBranch(BranchAuxiliar aux) {
    branches.add(aux);
  }

  public ArrayList<BranchAuxiliar> getBranches() {
    return this.branches;
  }
}

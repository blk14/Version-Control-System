/*
 * @Copyright Bogdan Negru
 */

package vcs;

import java.util.ArrayList;
import utils.ErrorCodeManager;
import utils.OperationType;

public final class BranchOperation extends VcsOperation {

  public BranchOperation(OperationType type, ArrayList<String> operationArgs) {
    super(type, operationArgs);
  }
  /*
   * (non-Javadoc)
   * @see vcs.VcsOperation#execute(vcs.Vcs)
   * - check if the name given in operatonArgs is valid (there is no branch
   * with the same name)
   * - create a new branch and add it to the object "branches" from vcs
   */
  public int execute(Vcs vcs) {
    for (int i = 0; i < vcs.getBranch().getBranches().size(); i++) {
      if (vcs.getBranch().getBranches().get(i).getName().equals(operationArgs.get(0))) {
        return ErrorCodeManager.VCS_BAD_CMD_CODE;
      }
    }
    BranchAuxiliar newBranch = new BranchAuxiliar(new Commit(), operationArgs.get(0));
    newBranch.getCommit().addCommit(vcs.getActiveSnapshot(), "First Commit",
        vcs.getOutputWriter());
    vcs.getBranch().addBranch(newBranch);
    return ErrorCodeManager.OK;
  }
}

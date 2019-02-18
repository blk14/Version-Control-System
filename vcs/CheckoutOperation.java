/*
 * @Copyright Bogdan Negru
 */
package vcs;

import java.util.ArrayList;
import utils.OperationType;
import utils.ErrorCodeManager;
import filesystem.FileSystemSnapshot;

public final class CheckoutOperation extends VcsOperation {

  public CheckoutOperation(OperationType type, ArrayList<String> operationArgs) {
    super(type, operationArgs);
  }
  /*
   * (non-Javadoc)
   * @see vcs.VcsOperation#execute(vcs.Vcs)
   * in this method I have 2 cases:
   * 	-(1) one for changing the commit (if there is -c in command)
   * 	-(2) one for changing the branch
   */
  public int execute(Vcs vcs) {
    if (!vcs.getStaging().isEmpty()) {
      return ErrorCodeManager.VCS_STAGED_OP_CODE;
    }
    if (operationArgs.get(0).equals("-c")) {
      int ok = -1;
      /*
       * case 1:
       * looking for commit position in the array of commits of
       * the current branch (check with id) and keep it in "ok"
       */
      for (int i = 0; i < vcs.getCurrentBranch().getCommit().getCommits().size(); i++) {
        if (vcs.getCurrentBranch().getCommit().getCommits().get(i).getId()
                == Integer.parseInt(operationArgs.get(1))) {
          ok = i;
        }
      }
      if (ok == -1) {
        return ErrorCodeManager.VCS_BAD_PATH_CODE;
      } else {
    	  /*
    	   * -if there is not a commit with that id => error
    	   * else set the active snapshot from vcs with the file
    	   * system snapshot of the current branch;
    	   */
        if (ok > 0) {
          vcs.setActiveSnapshot(vcs.getCurrentBranch().getCommit().getCommits()
                      .get(ok).getFile());
         } else {
        	 /*
        	  * in the case in which there is just one snapshot,
        	  * create a new one
        	  */
           vcs.setActiveSnapshot(new FileSystemSnapshot(
                     vcs.getOutputWriter()));
            }
        /*
         * delete the commits that were jumped
         */
         for (int i = vcs.getCurrentBranch().getCommit().getCommits().size() - 1;
                  i > ok; i--) {
              vcs.getCurrentBranch().getCommit().getCommits().remove(i);
              }
             }
        } else {
        	/* case 2:
        	 * if I have to change the branch
        	 * - looking for the position of the branch with the
        	 * same name as the argument given as parameter
        	 * - save the position
        	 */
          int ok = -1;
          for (int i = 0; i < vcs.getBranch().getBranches().size(); i++) {
              if (vcs.getBranch().getBranches().get(i).getName()
            		  .equals(operationArgs.get(0))) {
                  ok = i;
              }
          }
          if (ok == -1) {
              return ErrorCodeManager.VCS_BAD_CMD_CODE;
          } else {
        	  /*
        	   * if there is not a branch with the same name => error
        	   * - set the current branch from vcs with desired branch (from position ok)
        	   * - set the active snapshot from vcs with the last commit of the new
        	   * current branch
        	   */
              vcs.setCurrentBranch(vcs.getBranch().getBranches().get(ok));
              vcs.setActiveSnapshot(vcs.getCurrentBranch().getCommit().getCommits()
              .get(vcs.getCurrentBranch().getCommit().getCommits().size() - 1).getFile());
          }
        }
        return ErrorCodeManager.OK;
    }
}

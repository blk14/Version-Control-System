/*
 * @Copyright Bogdan Negru
 */
package vcs;

import java.util.ArrayList;

import utils.OperationType;
import utils.ErrorCodeManager;

public final class StatusOperation extends VcsOperation {

    public StatusOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
    /*
     * (non-Javadoc)
     * @see vcs.VcsOperation#execute(vcs.Vcs)
     * In execute method I take the name of the file on which
     * the action is focused from the not empty arrays from the staging
     * (I have access at staging with vcs object) and I print the
     * message
     */
    public int execute(Vcs vcs) {
      vcs.getOutputWriter().write("On branch: " + vcs.getCurrentBranch().getName() + "\n"
              + "Staged changes:\n");
      if (!vcs.getStaging().getMkdir().isEmpty()) {
          for (int i = 0; i < vcs.getStaging().getMkdir().size(); i++) {
               vcs.getOutputWriter().write("	Created directory "
                      + vcs.getStaging().getMkdir().get(i) + "\n");
          }
      }
      if (!vcs.getStaging().getTouch().isEmpty()) {
          for (int i = 0; i < vcs.getStaging().getTouch().size(); i++) {
              vcs.getOutputWriter().write("	Created file "
                      + vcs.getStaging().getTouch().get(i) + "\n");
          }
      }
      if (!vcs.getStaging().getWriteToFile().isEmpty()) {
          for (int i = 0; i < vcs.getStaging().getWriteToFile().size(); i++) {
              vcs.getOutputWriter().write("	Added \"" + vcs.getStaging()
              .getWriteToFile().get(i) + "\" to file "
                      + vcs.getStaging().getWriteToFile().get(i) + "\n");
          }
      }
      if (!vcs.getStaging().getRemove().isEmpty()) {
          for (int i = 0; i < vcs.getStaging().getRemove().size(); i++) {
              vcs.getOutputWriter().write("	Removed"
                      + vcs.getStaging().getRemove().get(i) + "\n");
          }
      }
      if (!vcs.getStaging().getCd().isEmpty()) {
          for (int i = 0; i < vcs.getStaging().getCd().size(); i++) {
              vcs.getOutputWriter().write("	Changed directory to "
                      + vcs.getStaging().getCd().get(i));
          }
      }
    return ErrorCodeManager.OK;
    }
}

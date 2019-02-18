/*
 * @Copyright Bogdan Negru
 */
package vcs;
import java.util.ArrayList;
import utils.OperationType;
import utils.ErrorCodeManager;

public final class CommitOperation extends VcsOperation {

    public CommitOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
    /*
     * (non-Javadoc)
     * @see vcs.VcsOperation#execute(vcs.Vcs)
     * - check if there is operations on staging
     * - add a new commit in current branch and sent
     * as parameter the active snapshot (which will be cloned) from vcs,
     * message and outputWriter
     */
    public int execute(Vcs vcs) {
        if (vcs.getStaging().isEmpty()) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        } else {
            vcs.getCurrentBranch().getCommit()
            .addCommit(vcs.getActiveSnapshot(), operationArgs.get(0),
                    vcs.getOutputWriter());
            vcs.getStaging().clear();
        }
        return ErrorCodeManager.OK;
    }
}

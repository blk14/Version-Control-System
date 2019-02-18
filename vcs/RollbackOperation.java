/*
 * @Copyright Bogdan Negru
 */
package vcs;
import java.util.ArrayList;
import utils.OperationType;
import utils.ErrorCodeManager;

public final class RollbackOperation extends VcsOperation {

    public RollbackOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
    /*
     * -clear the staging
     * -set the active snapshot of file system from
     * vcs object with the file system snapshot from the last
     * commit of the current branch
     */
    public int execute(Vcs vcs) {
        vcs.getStaging().clear();
        vcs.setActiveSnapshot(vcs.getCurrentBranch().getCommit().getCommits()
                .get(vcs.getCurrentBranch().getCommit().getCommits().size() - 1).getFile());
        return ErrorCodeManager.OK;
    }
}

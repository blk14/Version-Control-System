/*
 * @Copyright Bogdan Negru
 */
package vcs;
import java.util.ArrayList;
import utils.OperationType;
import utils.ErrorCodeManager;

public final class LogOperation extends VcsOperation {

    public LogOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
/*
 * (non-Javadoc)
 * @see vcs.VcsOperation#execute(vcs.Vcs)
 * print all the commits from the current branch and the message
 */
    public int execute(Vcs vcs) {
        for (int i = 0; i < vcs.getCurrentBranch().getCommit().getCommits().size(); i++) {
            vcs.getOutputWriter().write("Commit id: "
                + vcs.getCurrentBranch().getCommit().getCommits().get(i).getId() + "\n");

             if (i < vcs.getCurrentBranch().getCommit().getCommits().size() - 1) {
                vcs.getOutputWriter().write("Message: "
                    + vcs.getCurrentBranch().getCommit().getCommits().get(i).getMesage()
                    + "\n" + "\n");
            } else {
                 vcs.getOutputWriter().write("Message: "
                     + vcs.getCurrentBranch().getCommit().getCommits().get(i).getMesage()
                     + "\n");
            }
        }
        return ErrorCodeManager.OK;
    }
}

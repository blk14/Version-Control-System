/*
 * @Copyright Bogdan Negru
 */
package vcs;
/*
 * this class is similar with InvalidFileSystemOperation
 * return an error message for the case in which there
 * is no such operation in vcs
 */
import java.util.ArrayList;
import utils.ErrorCodeManager;
import utils.OperationType;

public final class InvalidVcsOperation extends VcsOperation {

  public InvalidVcsOperation(OperationType type, ArrayList<String> operationArgs) {
    super(type, operationArgs);
  }

  public int execute(Vcs vcs) {
    return ErrorCodeManager.VCS_BAD_CMD_CODE;
  }
}

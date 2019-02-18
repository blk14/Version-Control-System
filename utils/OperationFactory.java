package utils;

import filesystem.CatOperation;
import filesystem.CdOperation;
import filesystem.LsOperation;
import filesystem.MkdirOperation;
import filesystem.RemoveOperation;
import filesystem.TouchOperation;
import filesystem.WriteToFileOperation;
import filesystem.PrintOperation;
import filesystem.InvalidFileSystemOperation;
import vcs.BranchOperation;
import vcs.CheckoutOperation;
import vcs.CommitOperation;
import vcs.LogOperation;
import vcs.RollbackOperation;
import vcs.StatusOperation;
import vcs.InvalidVcsOperation;
import java.util.ArrayList;

public final class OperationFactory {
    private static OperationFactory instance = null;

    private OperationFactory() {
    }

    /**
     * Gets the instance.
     *
     * @return the instance
     */
    public static OperationFactory getInstance() {
        if (instance == null) {
            instance = new OperationFactory();
        }

        return instance;
    }

    /**
     * Creates a custom operation.
     *
     * @param type the operation type
     * @param args the operation arguments
     * @return the operation
     */
    public AbstractOperation createOperation(OperationType type, ArrayList<String> args) {
        switch (type) {
            case CAT:
                return new CatOperation(type, args);
            case CHANGEDIR:
                return new CdOperation(type, args);
            case LIST:
                return new LsOperation(type, args);
            case MAKEDIR:
                return new MkdirOperation(type, args);
            case REMOVE:
                return new RemoveOperation(type, args);
            case TOUCH:
                return new TouchOperation(type, args);
            case WRITETOFILE:
                return new WriteToFileOperation(type, args);
            case PRINT:
                return new PrintOperation(type, args);
            case FILESYSTEM_INVALID_OPERATION:
                return new InvalidFileSystemOperation(type, args);

            // TODO vcs operations
                /*
                 * return a new operation object (a specific one)
                 * to abstract operation object;
                 * in case of commit I want to concatenate
                 * the args which are par of the message
                 */
            case STATUS:
                return new StatusOperation(type, args);
            case BRANCH:
                return new BranchOperation(type, args);
            case COMMIT:
                String buffer = new String();
                for (int i = 1; i < args.size(); i++) {
                    buffer += args.get(i);
                    if (i < args.size() - 1) {
                        buffer += " ";
                    }
                }
                args.clear();
                args.add(buffer);
                return new CommitOperation(type, args);
            case CHECKOUT:
                return new CheckoutOperation(type, args);
            case LOG:
                return new LogOperation(type, args);
            case ROLLBACK:
                return new RollbackOperation(type, args);
            case VCS_INVALID_OPERATION:
                return new InvalidVcsOperation(type, args);

            default:
                return null;
        }
    }
}

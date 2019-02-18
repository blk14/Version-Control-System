package vcs;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.OutputWriter;
import utils.Visitor;
import utils.OperationType;
import java.util.ArrayList;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    private Staging staging = new Staging();
    private Branch branches;
    private BranchAuxiliar currentBranch;
/*
 * the object "staging" has access to the operations on staging
 * the object "branches" has access to all branches and all
 * commits from the entire file system;
 * also i have getters + setters
 *
 * setStaging method is called when a trackable operation
 * is given and it puts that operation on staging
 */
  public void setStaging(OperationType type, ArrayList<String> operationArgs) {
    switch (type) {
      case TOUCH:
        staging.setTouch(operationArgs.get(1));
        break;
      case REMOVE:
        staging.setRemove(operationArgs.get(1));
        break;
      case WRITETOFILE:
        staging.setWriteToFile(operationArgs.get(1));
        break;
      case MAKEDIR:
        staging.setMkdir(operationArgs.get(1));
        break;
      case CHANGEDIR:
        staging.setCd(operationArgs.get(0));
        break;
      default:
        break;
    }
  }
    public OutputWriter getOutputWriter() {
        return this.outputWriter;
    }
    public Branch getBranch() {
        return this.branches;
    }
    public BranchAuxiliar getCurrentBranch() {
        return this.currentBranch;
    }
    public Staging getStaging() {
        return this.staging;
    }
    public void setActiveSnapshot(FileSystemSnapshot files) {
        this.activeSnapshot = files;
    }
    public FileSystemSnapshot getActiveSnapshot() {
        return this.activeSnapshot;
    }
    public void setCurrentBranch(BranchAuxiliar branch) {
        this.currentBranch = branch;
    }
    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initialisations.
     * -branch master
     * -First commit from branch master
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);

        //TODO other initialisations
        this.branches = new Branch();
        this.currentBranch = new BranchAuxiliar(new Commit(), "master");
        this.currentBranch.getCommit().addCommit(activeSnapshot, "First commit",
        		outputWriter);
        branches.addBranch(currentBranch);
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        //TODO
        return vcsOperation.execute(this);
//        return 0;
    }

    //TODO methods through which vcs operations interact with this
}

package API.Database;

import androidx.annotation.NonNull;

import javax.annotation.Nullable;

public interface OnDataSentToDB {

    /**
     * This method will be triggered from within the database class.
     * Simply check the boolean value of `isTaskSuccessful` to see if the operation was
     * successful.
     *
     * Usage example:
     * databaseRef.getBranch(branchId, new DatabaseRequestCallback() {
     *      @Override
     *      public void onObjectWrittenToDB(@NonNull boolean isTaskSuccessful) {
     *          if (isTaskSuccessful) {
     *              // Object was successfully written to the database!
     *          }
     *          else {
     *              // Some error occurred
     *          }
     *      }
     * });
     * @param isTaskSuccessful - A boolean parameter which indicates
     *                           if writing to database was successful or not.
     */
    public void onObjectWrittenToDB(boolean isTaskSuccessful);
}
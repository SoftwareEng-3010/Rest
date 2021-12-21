package API.Database;

import javax.annotation.Nullable;

/**
 * This interface is used to pass data from the `RestDB` class,
 * which handles Database queries, to any class which requires
 * information querying from the Firestore database.
 */
public interface OnDataReceivedFromDB {
    /**
     * This method will be triggered from within the database class.
     * Simply perform a casting to the object you expect to be returned.
     *
     * Usage example:
     * databaseRef.getBranch(branchId, new OnDataReceivedFromDB() {
     *      @Override
     *      public void onObjectReturnedFromDB(@Nullable Object obj) {
     *          menu = (Menu) obj;
     *          setupUI();
     *      }
     * });
     * @param obj - The object parsed from the document which was returned
     *              and parsed into some business entity object.
     */
    public void onObjectReturnedFromDB(@Nullable Object obj);
}

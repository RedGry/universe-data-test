import java.util.List;
import java.util.Objects;

public abstract class RecursiveMerge<T> {
    /**
     * Merges two instances of {@link T}. No need to implement.
     * It can be any function, for example sum function if {@link T} is of Integer type
     *
     * @param first  first instance to merge
     * @param second second instance to merge
     * @return merged instance
     */
    public abstract T merge(T first, T second);

    /**
     * Merges list of values {@link T} using {@link RecursiveMerge#merge(T, T)}.
     *
     * @param values list of T values
     * @return merged instance
     */
    public T merge(List<T> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Values list must not be null or empty");
        }

        return mergeRecursive(values, 0, values.size() - 1);
    }

    /**
     * Recursively merges values in the given range of the list [left, right].
     * Uses divide-and-conquer strategy: splits the range into two halves,
     * merges each half recursively, and then merges the results.
     *
     * @param values the list of values to merge (must not be null, value must not be null)
     * @param left   the starting index of the range (inclusive)
     * @param right  the ending index of the range (inclusive)
     * @return the merged result of all values in the specified range
     */
    private T mergeRecursive(List<T> values, int left, int right) {
        if (left > right) {
            throw new IllegalArgumentException("Left index can't be greater right index");
        }

        if (left == right) {
            return Objects.requireNonNull(values.get(left), "List value must not be null");
        }

        int mid = (left + right) / 2;
        T leftMerged = mergeRecursive(values, left, mid);
        T rightMerged = mergeRecursive(values, mid + 1, right);

        return merge(leftMerged, rightMerged);
    }
}

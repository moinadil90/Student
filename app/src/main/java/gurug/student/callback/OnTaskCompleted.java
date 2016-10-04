package gurug.student.callback;

public interface OnTaskCompleted {
    void onTaskCompleted(String response, String lPurpose);
    void onTaskError(String response, String lPurpose);
}

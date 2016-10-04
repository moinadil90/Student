package gurug.student.util;
import com.android.volley.Request;

public class RestConstants {

    public static String BASE_URL         = "http://jservice.io/api/";
    public static int GET_METHOD          = Request.Method.GET;
    public static int POST_METHOD         = Request.Method.POST;
    public static int DELETE_METHOD       = Request.Method.DELETE;

    public static String QUESTIONS       = BASE_URL+"clues";
}

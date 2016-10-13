package gurug.student.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gurug.student.callback.OnTaskCompleted;


public class HttpCalls {
    private OnTaskCompleted mOnTaskCompleted;
    private Context mContext;
    public HttpCalls(OnTaskCompleted mOnTaskCompleted, Context mContext) {
        this.mOnTaskCompleted = mOnTaskCompleted;
        this.mContext         = mContext;
    }

    public void questionApi(int lMethod, String lUrl, final String lPurpose) {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(lMethod, lUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mOnTaskCompleted.onTaskCompleted(response.toString(), lPurpose);
                String string_response = response.toString();
                Log.i("Response","Response"+string_response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                JSONObject json = null;
                String err = "err";
                NetworkResponse response = error.networkResponse;
                if (lPurpose.equals("QUESTION_API")) {

                    if (response != null && response.data != null) {
                        switch (response.statusCode) {
                            case 400:
                                try {
                                    String responsestring = new String(response.data);
                                    json = new JSONObject(responsestring);
                                    err = json.getJSONObject("error").getString("message");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 501:
                                err = "Not able to connect to server, please try again later";
                                break;
                        }
                        //Additional cases to be shown here
                    }

                    if(err.equalsIgnoreCase("err")) {
                        if (error instanceof TimeoutError) {
                            err = "Please check your internet connection.";
                        } else if (error instanceof NoConnectionError) {
                            err = "Please check your internet connection.";
                        }
                    }
                    mOnTaskCompleted.onTaskError(err, lPurpose);
                }
            }
        });
        queue.add(jsObjRequest);
    }


}

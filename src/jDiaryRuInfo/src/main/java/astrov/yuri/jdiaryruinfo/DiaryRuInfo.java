package astrov.yuri.jdiaryruinfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

/**
 * Class for present information fron diary.ru web service.
 * Created by Yuri Astrov on 26.07.14.
 */
public class DiaryRuInfo {
    private JSONObject jsonObj = null;

    public DiaryRuInfo(InputStream is) throws IOException {
        try {
            String s = convertStreamToString(is);
            jsonObj = new JSONObject(s);
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJSONObject() {
        return jsonObj;
    }

    /**
     *
     * @return
     */
    public boolean isError() {
        if (jsonObj == null)
            return true;
        return jsonObj.has("error");
    }

    /**
     * <p>Check info for new public or private messages, comments.</p>
     * @return true if no new public or private messages, comments
     */
    public boolean isEmpty() {
        if(getDiscuss() + getNewComments()+getUmails() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (jsonObj == null) {
            return "No connection";
        }
        if(jsonObj.has("error")) {
            try {
                return jsonObj.getString("error");
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(jsonObj.getJSONObject("userinfo").getString("username"))
                    .append(" \nNew Umails: ").append(getUmails())
                    .append(" \nNew commnets: ").append(getNewComments())
                    .append(" \nNew discuss: ").append(getDiscuss());
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * <p>Return number of Umails (post messages)</p>
     * @return int
     */
    public int getUmails() {
        int i = 0;
        try {
            JSONObject umails = jsonObj.getJSONObject("umails");
            i = umails.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * <p>Get number of new comments.</p>
     * @return int
     */
    public int getNewComments() {
        int i = 0;
        try {
            JSONObject newcomments = jsonObj.getJSONObject("newcomments");
            i = newcomments.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * <p>Get number of new discuss.</p>
     * @return int
     */
    public int getDiscuss () {
        int i = 0;
        try {
            JSONObject disc = jsonObj.getJSONObject("discuss");
            i = disc.getInt("count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * <p>Return null or error message.</p>
     * @return String
     */
    public String getError() {
        if (!jsonObj.has("error"))
            return null;
        String error = null;
        try {
            error = jsonObj.getString("error");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return error;
    }

    /**
     *
     * @return JSONObject with User info
     * @throws JSONException
     */
    public JSONObject getUserInfo() throws JSONException {
        return jsonObj.getJSONObject("userinfo");
    }

    /**
     * <p>Load String from InputStream object.</p>
     * @param is InputStream
     * @return String
     * @throws java.io.UnsupportedEncodingException
     */
    private static String convertStreamToString(InputStream is)
                                        throws UnsupportedEncodingException,
                                               IOException {
        try (BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(is, "UTF-8"))) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        return sb.toString();
        }
    }
}

package service.SpacesContest.Http.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

/**
 *
 * @author umansilla
 */
public class UtilHttpAvayaSpacesApi {

    public String getSpaceIdAfterCreateSpace(JSONObject jsonNewSpace) {
        return jsonNewSpace.getJSONArray("data").getJSONObject(0).getString("topicId");
    }

    public String getJoinURL(JSONObject jsonNewSpaceInformChannelClient) {
        String joinURL = null; 
        List<String> list = extractUrls(jsonNewSpaceInformChannelClient.getJSONObject("inviteContent").getString("text"));
        for (String string : list) {
            joinURL = string;
        }
        return joinURL;
    }

    private List<String> extractUrls(String text) {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find()) {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        return containedUrls;
    }
}

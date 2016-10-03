package lincoln.testview.util;

/**
 * Created by lincoln on 16/4/18.
 */
public class GlobalUtil {
    public static final boolean IS_ONLINE_MODE= true;
    public static String ROOTURL= "";
    public static String QUESTION_ROOT_URL= "";
    public static String SHARE_ROOT_URL="";


    private static final String ROOTURL_ONLINE= "http://yjwd.mobi/api/";
    private static final String ROOTURL_TEST= "http://192.168.4.137/api/";

    public static String QUESTION_ROOT_URL_ONLINE= "http://yjwd.mobi/api";
    public static String QUESTION_ROOT_URL_TEST= "http://192.168.4.137/api";


    static{
        if (IS_ONLINE_MODE) {
            ROOTURL = ROOTURL_ONLINE;
            QUESTION_ROOT_URL  = QUESTION_ROOT_URL_ONLINE;
            SHARE_ROOT_URL = "http://yjwd.mobi/invite/";
        }else {
            ROOTURL = ROOTURL_TEST;
            QUESTION_ROOT_URL  = QUESTION_ROOT_URL_TEST;
            SHARE_ROOT_URL = "http://192.168.4.137/invite/";

        }
    }

}

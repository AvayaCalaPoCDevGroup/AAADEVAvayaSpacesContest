package service.SpacesContest.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author umansilla
 */
public class DateFormatedNow {

    public String getDateNow() {
       return new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
    }
}

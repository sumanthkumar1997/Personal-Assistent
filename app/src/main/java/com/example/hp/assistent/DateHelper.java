package com.example.hp.assistent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by HP on 18-07-2018.
 */

public class DateHelper {

    public static String convertDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yy", Locale.getDefault());
        return format.format(new Date(time));
    }
}

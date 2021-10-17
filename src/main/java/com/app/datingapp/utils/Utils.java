package com.app.datingapp.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;

public class Utils {

    //    public static final String SERVER_ADD = "http://" + InetAddress.getLoopbackAddress().getHostName();
    public static final String SERVER_ADD = "http://ec2-3-92-136-160.compute-1.amazonaws.com";
    public static final String DEFAULT_PIC = "anonym.png";
    public static String UPLOAD_ROOT = "images/"; //local
    //    public static String UPLOAD_ROOT = "../images/";
    public static String ALLOWED_ADDRESS = "http://ec2-3-92-136-160.compute-1.amazonaws.com";
    //    public static String ALLOWED_ADDRESS = "http://localhost:8080";
    public static final int BAD_REQUEST = 400;


    public static int getUserAge(LocalDate birthDate, LocalDate currentDate) {

        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public static HashMap<String, Integer> getStringIntegerHashMap(List<Object[]> usersByHobby2) {

        HashMap<String, Integer> resultHashMap = new HashMap<>();

        for (Object[] aResultList : usersByHobby2) {
            resultHashMap.put(aResultList[1].toString(),
                    Integer.valueOf(aResultList[0].toString()));
        }
        return resultHashMap;
    }
}

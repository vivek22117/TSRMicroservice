package com.vivek.tsr.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vivek.tsr.domain.TSRRequest;
import org.apache.http.entity.ContentType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by HARSHA on 23-02-2018.
 */
public class AppUtil {


    public static String convert(String mimeType, Object request) throws JsonProcessingException {

        switch (mimeType){
            case "application/json":
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,true);
                return new JsonUtility().convertToJson(request);
        }
        return "";
    }

    public static boolean isValidContentType(TSRRequest tsrRequest){
        return ContentType.APPLICATION_JSON.getMimeType().equals(tsrRequest.getContentType())
                || ContentType.APPLICATION_XML.getMimeType().equals(tsrRequest.getContentType());
    }

    public static String getFormattedDate(String date) {
        Date parse = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            parse = dateFormat.parse(date);
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ");
        String formattedDate = formatter.format(parse);
        System.out.println(formattedDate);
        return formattedDate;
    }

    public static List<String> getNumberOfIntervals(String timeProvided) {
        String startTime = String.valueOf(Instant.parse(timeProvided).truncatedTo(ChronoUnit.SECONDS));
        String firstInterval = String.valueOf(Instant.parse(timeProvided).plus(43200, ChronoUnit.SECONDS));
        return Arrays.asList(startTime, firstInterval);
    }

}

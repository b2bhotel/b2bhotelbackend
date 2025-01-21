package com.b2b.hotel.in.utils;

import com.b2b.hotel.in.exception.B2bHotelException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class RequestUtils {

    public Map<String,String> getHeaders(HttpServletRequest request)
    {
        return Collections.list(request.getHeaderNames()).stream().collect(Collectors.toMap(h ->h , request :: getHeader));
    }

    public  <T> T extractAndSetIds(String flowId, T objectType) {

        if(StringUtils.isEmpty(flowId))
        {
            throw new B2bHotelException("FlowId should be present in Request Header");
        }
        // Split the flowId to extract the parts
        String[] idArray = flowId.split("_");

        if (idArray.length < 3) {
            throw new B2bHotelException("Invalid flowId format. Expected format: hotelId_agentId_hotelCode");
        }

        String hotelId = idArray[0];
        String agentId = idArray[1];
        String hotelCode = idArray[2]; // Assuming this is the correct order

        // Use reflection to set the fields in the object
        setField(objectType, "hotelId", hotelId);
        setField(objectType, "agentId", agentId);
        setField(objectType, "hotelCode", hotelCode);
        setField(objectType, "flowId", flowId);
        return objectType;
    }

    private <T> void setField(T object, String fieldName, String value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);  // Set the field accessible if it’s private
            field.set(object, value);    // Set the field value
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error setting field " + fieldName + " in object", e);
        }
    }
}

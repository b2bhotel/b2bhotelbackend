package com.b2b.hotel.in.service;

import com.b2b.hotel.in.dto.BaseResponse;
import com.b2b.hotel.in.dto.BaseResponseBuilder;
import com.b2b.hotel.in.dto.rate.DatewiseRate;
import com.b2b.hotel.in.dto.rate.DatewiseRateIntoDatabase;
import com.b2b.hotel.in.dto.rate.RoomPricingData;
import com.b2b.hotel.in.dto.rate.RoomWiseDateWiseRate;
import com.b2b.hotel.in.dto.rate.enums.InclusionType;
import com.b2b.hotel.in.mapper.DateWiseRateMapper;
import com.b2b.hotel.in.repository.RateRepository;
import com.b2b.hotel.in.utils.DateUtil;
import com.b2b.hotel.in.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    private final RateRepository rateRepository;
    private final HttpServletRequest request;
    private final DateWiseRateMapper dateWiseRateMapper;

    @Override
    public ResponseEntity<BaseResponse<DatewiseRateIntoDatabase>> addRoomRateDatewise(DatewiseRate datewiseRate) {
        Map<String,String> headerMap = RequestUtils.getHeaders(request);
        String flowId = headerMap.getOrDefault("flowid", null);
        datewiseRate=RequestUtils.extractAndSetIds(flowId,datewiseRate);

        DatewiseRateIntoDatabase entity = dateWiseRateMapper.dateWiseRateToDatewiseRateIntoDatabase(datewiseRate);
        entity.setId("678d5971cda09c16e77c0b56");
        DatewiseRateIntoDatabase response = rateRepository.save(entity);
        return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), "Room rate enter successfully", response), HttpStatus.OK);




    }

    @Override
    public ResponseEntity<BaseResponse<DatewiseRateIntoDatabase>> updateOrInsertDatewiseRate(DatewiseRate datewiseRate) {
        Map<String,String> headerMap = RequestUtils.getHeaders(request);
        String flowId = headerMap.getOrDefault("flowid", null);
        datewiseRate=RequestUtils.extractAndSetIds(flowId,datewiseRate);
        Optional<DatewiseRateIntoDatabase> existingRateOpt = rateRepository.findByFlowId(datewiseRate.getFlowId());

        if (existingRateOpt.isPresent()) {
            DatewiseRateIntoDatabase existingRateFromDatabase = existingRateOpt.get();

            DatewiseRate finalDatewiseRateRequest = datewiseRate;
            existingRateFromDatabase.getRoomRates().compute(finalDatewiseRateRequest.getRoomType(), (roomType, roomData) -> {
                if (roomData == null|| !roomType.equals(roomData.getRoomType())) {
                    return createNewRoomTypeData(finalDatewiseRateRequest);
                }

                roomData.getRoomRateInclusionWise().compute(finalDatewiseRateRequest.getInclusion(), (inclusionType, inclusionData) -> {
                    if (inclusionData == null || !roomData.getRoomRateInclusionWise().containsKey(inclusionType)) {
                        return createNewInclusionData(finalDatewiseRateRequest);
                    }

                    for (RoomPricingData pricingData : finalDatewiseRateRequest.getRoomPricingDataList()) {
                        String pricingDateString = pricingData.getPricingDate().toString(); // Assuming pricingDate is LocalDate
                        LocalDate pricingDate = pricingData.getPricingDate();

                        // Check if the date already exists in inclusionData
                        if (inclusionData.containsKey(pricingDate)) {
                            System.out.println("containskey");
                            // Update existing RoomPricingData
                            RoomPricingData existingPricingData = inclusionData.get(pricingDate);
                            existingPricingData.setRoomRate(pricingData.getRoomRate());
                            existingPricingData.setExtraBedRate(pricingData.getExtraBedRate());
                            existingPricingData.setChildwithBed(pricingData.getChildwithBed());
                            existingPricingData.setPricingDay(pricingData.getPricingDay());
                            // If you want to modify other fields, you can do so here.
                        } else {
                            System.out.println("Notcontainskey");
                            // Add new RoomPricingData
                            inclusionData.put(pricingDate, pricingData);
                        }
//                        inclusionData.put(pricingData.getPricingDate(), pricingData);
                    }

                    return inclusionData;
                });

                return roomData;
            });

            DatewiseRateIntoDatabase response = rateRepository.save(existingRateFromDatabase);
            return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), "Room rate Updated successfully", response), HttpStatus.OK);
        } else {
            DatewiseRateIntoDatabase newRate = dateWiseRateMapper.dateWiseRateToDatewiseRateIntoDatabase(datewiseRate);
            DatewiseRateIntoDatabase response = rateRepository.save(newRate);
            return new ResponseEntity<>(BaseResponseBuilder.buildResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), "Room rate Inserted successfully", response), HttpStatus.OK);

        }
    }

    private RoomWiseDateWiseRate createNewRoomTypeData(DatewiseRate datewiseRate) {
        Map<InclusionType, Map<LocalDate, RoomPricingData>> inclusionWiseMap = new HashMap<>();
        inclusionWiseMap.put(datewiseRate.getInclusion(), createNewInclusionData(datewiseRate));

        return RoomWiseDateWiseRate.builder()
            .roomType(datewiseRate.getRoomType())
            .maxPax(datewiseRate.getMaxPax())
            .childCompAge(datewiseRate.getChildCompAge())
            .noOfRooms(datewiseRate.getNoOfRooms())
            .inclusion(datewiseRate.getInclusion())
            .roomRateInclusionWise(inclusionWiseMap)
            .build();
    }

    private Map<LocalDate, RoomPricingData> createNewInclusionData(DatewiseRate datewiseRate) {
        Map<LocalDate, RoomPricingData> pricingDataMap = new HashMap<>();
        for (RoomPricingData pricingData : datewiseRate.getRoomPricingDataList()) {
            pricingDataMap.put(pricingData.getPricingDate(), pricingData);
        }
        return pricingDataMap;
    }
}


package com.b2b.hotel.in.repository;




import com.b2b.hotel.in.dto.room.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {

    Optional<List<Room>> findRoomByFlowId(String flowId);

    Optional<Room> findRoomById(String roomId);


    Optional<Room> findRoomByIdAndFlowId(String roomId, String flowId);

    String findRoomNameByFlowIdAndRoomName(String flowId, String roomName);
}

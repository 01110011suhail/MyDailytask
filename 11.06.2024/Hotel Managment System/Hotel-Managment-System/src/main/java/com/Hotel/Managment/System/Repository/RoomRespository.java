package com.Hotel.Managment.System.Repository;

import com.Hotel.Managment.System.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRespository extends JpaRepository<Room,Long> {

    @Query("select distinct r.roomType from Room r")
    List<String> findDistinctRoomTypes();
}

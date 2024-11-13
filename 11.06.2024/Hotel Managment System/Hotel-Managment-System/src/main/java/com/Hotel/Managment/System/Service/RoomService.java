package com.Hotel.Managment.System.Service;

import com.Hotel.Managment.System.Model.Room;
import com.Hotel.Managment.System.Repository.BookedRoomRepository;
import com.Hotel.Managment.System.Repository.RoomRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService{
    private final RoomRespository roomRespository;

    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws SQLException, IOException {
        Room room=new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);

        if(!file.isEmpty()){
            byte[] photoBytes= file.getBytes();
            Blob photoblob= new SerialBlob(photoBytes);
            room.setPhoto(photoblob);

        }


        return roomRespository.save(room);
    }

    @Override
    public List<String> getAllRoomTypes(){
        return roomRespository.findDistinctRoomTypes();
    }
}

    package com.Hotel.Managment.System.Controller;

    import com.Hotel.Managment.System.Model.Room;
    import com.Hotel.Managment.System.Response.RoomResponse;
    import com.Hotel.Managment.System.Service.IRoomService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.math.BigDecimal;
    import java.sql.SQLException;
    import java.util.List;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/rooms")
    public class RoomController {
        private final IRoomService roomService;
        @PostMapping("/add/new-room")
        public ResponseEntity<RoomResponse> addNewRoom(
                @RequestParam("photo")MultipartFile photo,
                @RequestParam("roomType")String roomType,
                @RequestParam("roomPrice")BigDecimal roomPrice
                ) throws SQLException, IOException {
            Room saveroom = roomService.addNewRoom(photo,roomType,roomPrice);
            RoomResponse response= new RoomResponse(saveroom.getId(),saveroom.getRoomType(),saveroom.getRoomPrice());

            return ResponseEntity.ok(response);


        }
        @GetMapping("/room/types")
        public List<String> getRoomTypes(){
            return roomService.getAllRoomTypes();
        }
    }

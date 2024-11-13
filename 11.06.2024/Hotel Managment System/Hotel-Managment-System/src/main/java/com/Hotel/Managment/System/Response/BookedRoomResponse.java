package com.Hotel.Managment.System.Response;

import com.Hotel.Managment.System.Model.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookedRoomResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "room_id", nullable = false)
    private Long roomId;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkindate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkoutdate;

    @Column(name = "guest_name", nullable = false)
    private String guestName;

    @Column(name = "guest_email", nullable = false)
    private String guestEmail;

    @Column(name = "num_of_adults", nullable = false)
    private Integer numOfAdults;

    @Column(name = "num_of_children", nullable = false)
    private Integer numOfChildren;

    @Column(name = "total_no_of_guests", nullable = false)
    private Integer totalNoOfGuests;

    @Column(name = "booking_confirmation_code", nullable = false)
    private String bookingConfirmationCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")

    @Column(name = "room", nullable = false)
    private Room room;

    public BookedRoomResponse(Long roomId, LocalDate checkindate, LocalDate checkoutdate, String bookingConfirmationCode) {
        this.roomId = roomId;
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}

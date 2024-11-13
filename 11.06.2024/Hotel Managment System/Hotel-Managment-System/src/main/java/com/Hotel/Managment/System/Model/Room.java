package com.Hotel.Managment.System.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assuming you want auto-generated IDs
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(name = "room_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal roomPrice;

    @Column(name = "is_booked", nullable = false)
    private Boolean isBooked = false;
    @Lob
    private Blob photo;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "room") // Added mappedBy
    private List<BookedRoom> bookings = new ArrayList<>();

    public void addBooking(BookedRoom booking) {
        if(bookings==null){
            bookings= new ArrayList<>();
        }
        bookings.add(booking);
        booking.setRoom(this);
        isBooked = true;
        String bookingCode = RandomStringUtils.randomNumeric(10);
        booking.setBookingConfirmationCode(bookingCode); // Assuming BookedRoom has a setBookingCode method
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomType='" + roomType + '\'' +
                ", roomPrice=" + roomPrice +
                ", isBooked=" + isBooked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return id != null && id.equals(room.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
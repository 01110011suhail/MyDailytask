package com.Hotel.Managment.System.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booked_room") // Corrected the table name
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) // Use a different name for the primary key
    private Long id; // Change roomId to id

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
    @JoinColumn(name = "room_id", nullable = false) // This is the foreign key
    private Room room;

    public void calculateTotalNoGuests() {
        this.totalNoOfGuests = numOfAdults + numOfChildren;
    }

    public void setNumOfChildren(Integer numOfChildren) {
        this.numOfChildren = numOfChildren;
        calculateTotalNoGuests();
    }

    public void setNumOfAdults(Integer numOfAdults) {
        this.numOfAdults = numOfAdults;
        calculateTotalNoGuests();
    }
}
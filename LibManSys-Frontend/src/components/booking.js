function BookingDto (bookingStartTime, bookingEndTime, bookingID, bookingDate) {
    this.bookingStartTime = bookingStartTime
    this.bookingEndTime = bookingEndTime
    this.bookingDate = bookingDate
    this.bookingID = bookingID
}

export default {
    name: 'booking' ,
    data () {
        return {
            bookings: [],
            newBooking: ''. 
            errorBooking: '',
            respone: []
        }
    }
}
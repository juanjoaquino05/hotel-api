package com.alten.hotel_api.constant;

public final class ErrorMessages {
    private ErrorMessages(){}
    public static final String THREE_DAY_LIMIT = "Can't reserve for more than 3 days";
    public static final String TODAY_RESERVATION_ERROR = "Can't place a reservation for the same day";
    public static final String ROOM_ALREADY_RESERVE = "There's a reservation for one of the days";
    public static final String END_DATE_BEFORE_START_DATE = "End date needs to be after start date";
    public static final String PAST_DATE_RESERVATION = "Can't place a reservation for a past date";
    public static final String MAX_DAYS_RESERVATION = "Can't place a reservation before 30 days of start date";


}

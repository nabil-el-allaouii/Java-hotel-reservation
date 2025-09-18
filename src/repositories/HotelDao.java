package repositories;

import java.util.Collection;

import modal.Hotel;

public interface HotelDao {
    void save(String name , String address , int availableRooms , double rating);
    Collection<Hotel> getAllHotels();
    Collection<Hotel> findAvailableHotels();
}

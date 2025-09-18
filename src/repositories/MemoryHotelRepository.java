package repositories;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import modal.Hotel;
import repositories.HotelDao;

public class MemoryHotelRepository implements HotelDao {
    private final Map<String , Hotel> hotels = new HashMap<>();
    @Override
    public void save(String name , String address , int availableRooms , double rating){
        Hotel hotel = new Hotel(name, address, availableRooms, rating);
        hotels.put(hotel.getId(), hotel);
    }
    @Override
    public Collection<Hotel> getAllHotels(){
        return hotels.values();
    }

    @Override
    public Collection<Hotel> findAvailableHotels(){
        return hotels.values().stream().filter(hotel-> hotel.getAvailableRooms() > 0).collect(Collectors.toList());
    }
}

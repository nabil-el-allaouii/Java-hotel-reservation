package services;

import java.util.Collection;

import modal.Hotel;
import repositories.HotelDao;

public class HotelService {
    private final HotelDao hotelRepo;

    public HotelService(HotelDao hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    public void addHotel(String name , String address , int availableRooms , double rating){
        hotelRepo.save(name, address , availableRooms , rating);
    }

    public Collection<Hotel> getAllHotels(){
        return hotelRepo.getAllHotels();
    }

    public Collection<Hotel> getAvailableHotels(){
        return hotelRepo.findAvailableHotels();
    }

}

package services;

import com.domus.domus.entities.Apartment;
import com.domus.domus.entities.User;
import com.domus.domus.repositories.ApartmentRepository;
import com.domus.domus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository,
                            UserRepository userRepository) {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
    }

    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    public void markAsPaid(Long id) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Квартира не найдена"));
        apartment.setHasPaid(true);
        apartmentRepository.save(apartment);
    }

    public List<Apartment> getDebtors() {
        return apartmentRepository.findAll().stream()
                .filter(a -> !Boolean.TRUE.equals(a.getHasPaid()))
                .collect(Collectors.toList());
    }

    public Apartment getMyApartment(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return apartmentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Квартира не найдена"));
    }
}

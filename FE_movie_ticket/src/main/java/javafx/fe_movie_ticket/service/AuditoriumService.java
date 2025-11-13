package javafx.fe_movie_ticket.service;

import jakarta.transaction.Transactional;
import javafx.fe_movie_ticket.entity.Auditorium;
import javafx.fe_movie_ticket.repository.AuditoriumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class AuditoriumService {

        private final AuditoriumRepository auditoriumRepository;

        public Auditorium getAuditoriumById(Long auditoriumId) {
            return auditoriumRepository.findById(auditoriumId)
                    .orElseThrow(() -> new RuntimeException("Auditorium not found"));
        }

        public List<Auditorium> getAllActiveAuditoriums() {
            return auditoriumRepository.findByIsActiveTrue();
        }

        // Comment out vì Auditorium không có field 'location'
        // public List<Auditorium> getAuditoriumsByLocation(String location) {
        //     return auditoriumRepository.findByLocationContainingIgnoreCaseAndIsActiveTrue(location);
        // }

//        // Cập nhật thông tin auditorium
//        public Auditorium updateAuditorium(Long auditoriumId, Auditorium auditoriumDetails) {
//            Auditorium auditorium = getAuditoriumById(auditoriumId);
//            auditorium.setName(auditoriumDetails.getName());
//            auditorium.setDescription(auditoriumDetails.getDescription());
//
//            return auditoriumRepository.save(auditorium);
//        }


        public void deactivateAuditorium(Long auditoriumId) {
            Auditorium auditorium = getAuditoriumById(auditoriumId);
            auditorium.setActive(false);
            auditoriumRepository.save(auditorium);
        }

        // Kích hoạt lại auditorium
        public void activateAuditorium(Long auditoriumId) {
            Auditorium auditorium = getAuditoriumById(auditoriumId);
            auditorium.setActive(true);
            auditoriumRepository.save(auditorium);
        }

}

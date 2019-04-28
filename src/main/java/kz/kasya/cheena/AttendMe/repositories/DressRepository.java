package kz.kasya.cheena.AttendMe.repositories;

import kz.kasya.cheena.AttendMe.models.entities.Dress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DressRepository extends JpaRepository<Dress, Long>{
    List<Dress> findAllByDeletedAtIsNull();
}

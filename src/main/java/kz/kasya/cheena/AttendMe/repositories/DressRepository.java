package kz.kasya.cheena.AttendMe.repositories;

import kz.kasya.cheena.AttendMe.models.entities.Dress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DressRepository extends JpaRepository<Dress, Long>{
    List<Dress> findAllByDeletedAtIsNull();


    @Override
    Page<Dress> findAll(Pageable pageable);

    @Query(value = "SELECT * from dresses where id regexp ?1 and name like ?2 " , nativeQuery = true)
    Page<Dress> findAll(Pageable pageable, String regexpNum , String name);
}

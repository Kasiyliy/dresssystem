package kz.kasya.cheena.AttendMe.services;

import kz.kasya.cheena.AttendMe.exceptions.ServiceException;
import kz.kasya.cheena.AttendMe.models.entities.Dress;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface DressService {

    Dress findById(Long id) throws ServiceException;

    List<Dress> findAll();

    List<Dress> findAllWithDeleted();

    Dress update(Dress Dress) throws ServiceException;

    Dress save(Dress Dress) throws ServiceException;

    void delete(Dress Dress) throws ServiceException;

    void deleteById(Long id) throws ServiceException;

    Page<Dress> findAll(Optional<Integer> page);

    Page<Dress> findAllWithSorting(Optional<Integer> page,
                                   Optional<Integer> size,
                                   Optional<String> sortBy);

    Page<Dress> findAllWithSortingType2(Optional<Integer> page,
                                        Optional<Integer> size,
                                        Optional<String[]> sortBy);

    Page<Dress> findAllWithSortingType2AndFilter(Optional<Integer> page,
                                                        Optional<Integer> size,
                                                        Optional<String[]> sortBy,
                                                        Optional<String> name,
                                                        Optional<Integer> id);

}

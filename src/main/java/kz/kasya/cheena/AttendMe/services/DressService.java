package kz.kasya.cheena.AttendMe.services;

import kz.kasya.cheena.AttendMe.exceptions.ServiceException;
import kz.kasya.cheena.AttendMe.models.entities.Dress;

import java.util.List;


public interface DressService {

    Dress findById(Long id) throws ServiceException;
    List<Dress> findAll();
    List<Dress> findAllWithDeleted();
    Dress update(Dress Dress) throws ServiceException ;
    Dress save(Dress Dress) throws ServiceException ;
    void delete(Dress Dress) throws ServiceException ;
    void deleteById(Long id) throws ServiceException ;

}

package kz.kasya.cheena.AttendMe.services.impl;


import kz.kasya.cheena.AttendMe.exceptions.ServiceException;
import kz.kasya.cheena.AttendMe.models.entities.Dress;
import kz.kasya.cheena.AttendMe.repositories.DressRepository;
import kz.kasya.cheena.AttendMe.services.DressService;
import kz.kasya.cheena.AttendMe.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 10.04.2019
 * @project logistic_server
 */
@Service
public class DressServiceImpl implements DressService {

    private DressRepository dressRepository;

    @Autowired
    public DressServiceImpl(DressRepository dressRepository) {
        this.dressRepository = dressRepository;
    }

    @Override
    public Dress findById(Long id) throws ServiceException {
        Optional<Dress> DressOptional = dressRepository.findById(id);
        return DressOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("Dress not found")
                .build());
    }

    @Override
    public List<Dress> findAll() {
        return dressRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<Dress> findAllWithDeleted() {
        return dressRepository.findAll();
    }

    @Override
    public Dress update(Dress Dress) throws ServiceException {
        if(Dress.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Dress is null")
                    .build();
        }
        return  dressRepository.save(Dress);
    }

    @Override
    public Dress save(Dress Dress) throws ServiceException {
        if(Dress.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("Dress already exists")
                    .build();
        }
        return  dressRepository.save(Dress);
    }

    @Override
    public void delete(Dress Dress) throws ServiceException {
        if(Dress.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Dress is null")
                    .build();
        }
        Dress = findById(Dress.getId());
        Dress.setDeletedAt(new Date());
        dressRepository.save(Dress);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("id is null")
                    .build();
        }
        Dress Dress = findById(id);
        Dress.setDeletedAt(new Date());
        dressRepository.save(Dress);
    }
}

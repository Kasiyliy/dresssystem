package kz.kasya.cheena.AttendMe.services.impl;


import kz.kasya.cheena.AttendMe.exceptions.ServiceException;
import kz.kasya.cheena.AttendMe.models.entities.Dress;
import kz.kasya.cheena.AttendMe.repositories.DressRepository;
import kz.kasya.cheena.AttendMe.services.DressService;
import kz.kasya.cheena.AttendMe.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Page<Dress> findAll(Optional<Integer> page) {
        return dressRepository.findAll(PageRequest.of(page.orElse(0),5));
    }

    @Override
    public Page<Dress> findAllWithSorting(Optional<Integer> page,Optional<Integer> size,Optional<String> sortBy) {
        String sorters = sortBy.orElse("id");
        Sort sort = Sort.by(Arrays.stream(sorters.split(",")).map(e -> Sort.Order.asc(e)).collect(Collectors.toList()));
        return dressRepository.findAll(PageRequest.of(page.orElse(0),size.orElse(5),sort));
    }

    @Override
    public Page<Dress> findAllWithSortingType2(Optional<Integer> page,Optional<Integer> size,Optional<String[]> sortBy) {
        Sort sort = null;
        if(sortBy.isPresent()){
            String[] sorters = sortBy.get();
            List<Sort.Order> sorts = Arrays.stream(sorters)
                    .map(s -> s.split("-")[0].trim().equalsIgnoreCase("asc")
                            ? Sort.Order.asc( s.split("-")[1]) : Sort.Order.desc( s.split("-")[1]))
                    .collect(Collectors.toList());
            sort = Sort.by(sorts);
        }else{
            sort = Sort.by("id");
        }
        return dressRepository.findAll(PageRequest.of(page.orElse(0),size.orElse(5),sort));
    }

    @Override
    public Page<Dress> findAllWithSortingType2AndFilter(Optional<Integer> page,
                                                        Optional<Integer> size,
                                                        Optional<String[]> sortBy,
                                                        Optional<String> name,
                                                        Optional<Integer> id) {
        Sort sort = null;
        if(sortBy.isPresent()){
            String[] sorters = sortBy.get();
            List<Sort.Order> sorts = Arrays.stream(sorters)
                    .map(s -> s.split("-")[0].trim().equalsIgnoreCase("asc")
                            ? Sort.Order.asc( s.split("-")[1]) : Sort.Order.desc( s.split("-")[1]))
                    .collect(Collectors.toList());
            sort = Sort.by(sorts);
        }else{
            sort = Sort.by("id");
        }
        String regexpNum = id.isPresent() ? "^[" + id.get() + "]*$": "^[0-9]*$";
        String nameStr = name.isPresent() ? "%" + name.get() + "%": "%%";
        return dressRepository.findAll(PageRequest.of(page.orElse(0),size.orElse(5),sort), regexpNum, nameStr);
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

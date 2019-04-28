package kz.kasya.cheena.AttendMe.controllers.rest;

import kz.kasya.cheena.AttendMe.controllers.BaseController;
import kz.kasya.cheena.AttendMe.exceptions.ServiceException;
import kz.kasya.cheena.AttendMe.models.dtos.DressDto;
import kz.kasya.cheena.AttendMe.models.entities.Dress;
import kz.kasya.cheena.AttendMe.models.mappers.DressMapper;
import kz.kasya.cheena.AttendMe.services.DressService;
import kz.kasya.cheena.AttendMe.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dresses")
public class DressController extends BaseController {

     private DressService dressService;
     private DressMapper dressMapper;

     @Autowired
     public DressController(DressService dressService, DressMapper dressMapper) {
          this.dressService = dressService;
          this.dressMapper = dressMapper;
     }

     @GetMapping
     public ResponseEntity<?> getAll() {
          return buildResponse(dressMapper.toDtoList(dressService.findAll()), HttpStatus.OK);
     }

     @GetMapping("{id}")
     public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
          return buildResponse(dressMapper.toDto(dressService.findById(id)), HttpStatus.OK);
     }

     @PostMapping
     public ResponseEntity<?> add(@RequestBody DressDto dressDto) throws ServiceException {
          Dress dress = dressMapper.toEntity(dressDto);
          dress = dressService.save(dress);
          return buildResponse(dressMapper.toDto(dress), HttpStatus.OK);
     }


     @DeleteMapping("{id}")
     public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException {
          dressService.deleteById(id);
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @DeleteMapping
     public ResponseEntity<?> delete(@RequestBody DressDto dressDto) throws ServiceException {
          dressService.delete(dressMapper.toEntity(dressDto));
          return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
     }

     @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
     public ResponseEntity<?> update(@RequestBody DressDto dressDto) throws ServiceException {
          Dress dress = dressService.update(dressMapper.toEntity(dressDto));
          return buildResponse(SuccessResponse.builder()
                  .message("updated")
                  .data(dressMapper.toDto(dress))
                  .build(), HttpStatus.OK);
     }

}

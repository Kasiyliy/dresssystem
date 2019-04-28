package kz.kasya.cheena.AttendMe.models.mappers;

import kz.kasya.cheena.AttendMe.models.dtos.DressDto;
import kz.kasya.cheena.AttendMe.models.entities.Dress;
import kz.kasya.cheena.AttendMe.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class DressMapper extends AbstractModelMapper<Dress, DressDto> {

    private ModelMapper modelMapper;

    @Autowired
    public DressMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DressDto toDto(Dress Dress) {
        return modelMapper.map(Dress, DressDto.class);
    }

    @Override
    public Dress toEntity(DressDto dressDto) {
        return modelMapper.map(dressDto, Dress.class);
    }

    @Override
    public List<DressDto> toDtoList(List<Dress> Dresss) {
        return Dresss.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Dress> toEntityList(List<DressDto> dressDtos) {
        return dressDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.AlergenoDTO;
import com.myweddingplanner.back.dto.ImagenAlergenoDTO;
import com.myweddingplanner.back.model.Alergeno;
import com.myweddingplanner.back.model.ImagenAlergeno;
import com.myweddingplanner.back.repository.AlergenoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlergenoServiceImpl implements AlergenoService{

    private final AlergenoRepository repository;

    public AlergenoServiceImpl(AlergenoRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AlergenoDTO> findById(Long id) {

        Optional<Alergeno> opt = repository.findById(id);

        return (opt.isEmpty()) ? Optional.empty() : Optional.of(toDTO(opt.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlergenoDTO> findAll() {

        List<AlergenoDTO> list = new ArrayList<>();

        for (Alergeno a: repository.findAll()){
            list.add(toDTO(a));
        }
        return list;
    }

    @Override
    public AlergenoDTO save(AlergenoDTO dto) {

        Alergeno entity = (dto.getId() != null)
                ? repository.findById(dto.getId()).orElseGet(Alergeno::new)
                : new Alergeno();

        entity.setNombre(dto.getNombre());



        List<ImagenAlergeno> imgs = (dto.getImagenes() == null) ? List.of()
                : dto.getImagenes().stream().map(this::toEntityImagen).toList();

        entity.setImagenes(imgs);





        Alergeno saved = repository.save(entity);
        return toDTO(saved);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public AlergenoDTO toDTO(Alergeno a){

        AlergenoDTO dto = new AlergenoDTO();

        dto.setId(a.getId());
        dto.setNombre(a.getNombre());

        List<ImagenAlergenoDTO> imagenes = (a.getImagenes() == null) ?
                List.of() : a.getImagenes().stream().map(this::toDTOImagen).toList();
        dto.setImagenes(new ArrayList<>(imagenes));

        return dto;
    }

    public Alergeno toEntity(AlergenoDTO dto){

        Alergeno a = new Alergeno();

        if (dto.getId() != null) a.setId(dto.getId());

        a.setNombre(dto.getNombre());

        if (dto.getImagenes() != null) {
            List<ImagenAlergeno> imagenes = new ArrayList<>();

            for (ImagenAlergenoDTO imgDto : dto.getImagenes()){
                ImagenAlergeno img = new ImagenAlergeno();
                img.setId(imgDto.getId());
                img.setEnlace(imgDto.getEnlace());
                img.setTipo(imgDto.getTipo());
                img.setAlergeno(a);
                imagenes.add(img);
            }
            a.setImagenes(imagenes);
        }
        return a;
    }

    private ImagenAlergenoDTO toDTOImagen(ImagenAlergeno img) {
        ImagenAlergenoDTO dto = new ImagenAlergenoDTO();
        dto.setId(img.getId());
        dto.setEnlace(img.getEnlace());
        dto.setTipo(img.getTipo());
        return dto;
    }

    private ImagenAlergeno toEntityImagen(ImagenAlergenoDTO dto) {
        ImagenAlergeno e = new ImagenAlergeno();
        e.setId(dto.getId());
        e.setEnlace(dto.getEnlace());
        e.setTipo(dto.getTipo());
        return e;
    }

}

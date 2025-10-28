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

    private final AlergenoRepository alergenoRepository;
    private final ImagenAlergenoService imagenAlergenoService;

    public AlergenoServiceImpl(AlergenoRepository repository, ImagenAlergenoService imagenAlergenoService) {
        this.alergenoRepository = repository;
        this.imagenAlergenoService = imagenAlergenoService;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AlergenoDTO> findById(Long id) {

        Optional<Alergeno> opt = alergenoRepository.findById(id);

        return (opt.isEmpty()) ? Optional.empty() : Optional.of(toDTO(opt.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlergenoDTO> findAll() {

        List<AlergenoDTO> list = new ArrayList<>();

        for (Alergeno a: alergenoRepository.findAll()){
            list.add(toDTO(a));
        }
        return list;
    }

    @Override
    public AlergenoDTO save(AlergenoDTO dto) {

        Alergeno entity = (dto.getId() != null)
                ? alergenoRepository.findById(dto.getId()).orElseGet(Alergeno::new)
                : new Alergeno();

        entity.setNombre(dto.getNombre());

        sincronizarImagenes(entity, dto.getImagenes());

        Alergeno saved = alergenoRepository.save(entity);

        return toDTO(saved);
    }

    @Override
    public void deleteById(Long id) {
        alergenoRepository.deleteById(id);
    }

    @Override
    public AlergenoDTO toDTO(Alergeno alergeno){

        AlergenoDTO dto = new AlergenoDTO();

        dto.setId(alergeno.getId());

        dto.setNombre(alergeno.getNombre());

        List<ImagenAlergenoDTO> imagenes = (alergeno.getImagenes() == null) ?
                List.of() : alergeno.getImagenes().stream().map(imagenAlergenoService::toDTO).toList();

        dto.setImagenes(new ArrayList<>(imagenes));

        return dto;
    }

    private void sincronizarImagenes (Alergeno entity, List<ImagenAlergenoDTO> imagenesDTO){

        /*
        Tengo que comparar dos listas de imagenes, la que viene en el DTO y la que vienen en la entidad
         */


        // Si la entidad no tiene imagenes inicializamos el array vacío,
        // esto es que no tenemos imagenes en la bbdd

        if (entity.getImagenes() == null){
            entity.setImagenes(new ArrayList<>());
        }

        // en esta variable guardamos el list vacío o no que tenga la entidad
        List<ImagenAlergeno> imagenesEntidad = entity.getImagenes();

        // en esta variable guardamos las imagenes que estando en la entidad no esten en dto
        List<ImagenAlergeno> aEliminar = new ArrayList<>();

        // para cada imagen de alergeno guardada en la BBDD
        // la comparamos con el list de imagenes que vienen en el dto
        // si encontramos dos id iguales es que ya está en bbdd
        // entonces actualizamos la info de la bbdd con lo que venga en dto
        // si no encontramos en el dto la imagen que tenemos en bbdd la agregamos a la lista para eliminar
        for (ImagenAlergeno img : imagenesEntidad){

            boolean encontradoEnDTO = false;

            /*
                    una imagen del list de imagenes de la entidad

                    existe en dto?

                        SI -> seteamos los miembros por si cambian y mantenemos el id

                        NO -> la agregamos al list aEliminar

             */

            if (imagenesDTO != null){

                // comparamos con la imagen actual todas las del list del dto

                for (ImagenAlergenoDTO imagenEnDTO : imagenesDTO){

                    // si ambas tiene id asignado y el id coincide, es que son la misma instancia

                    if (imagenEnDTO.getId() != null
                            && img.getId() != null
                            && imagenEnDTO.getId().equals(img.getId())){

                        // seteo la info por si ha variado y mantengo el id intacto
                        img.setEnlace(imagenEnDTO.getEnlace());
                        img.setTipo(imagenEnDTO.getTipo());

                        // salgo del bucle porque ya encontré una coincidencia
                        encontradoEnDTO = true;

                        break;
                    }
                }
            }

            if (!encontradoEnDTO){
                aEliminar.add(img);
            }

        }

        // eliminamos de la lista de imagenes de la entidad en memoria
        // las imagenes que no encontramos en el dto
        for (ImagenAlergeno imagen : aEliminar){
            imagenesEntidad.remove(imagen);
        }

        // ya modificamos las que estaban en dto y en entidad
        // eliminamos de la entidad las que ya no estaban en dto
        // tenemos que agregar a la entidad las que estan en dto pero no en la entidad

        if (imagenesDTO != null){
            for (ImagenAlergenoDTO imagenDTO : imagenesDTO){
                boolean yaExiste = false;

                if (imagenDTO.getId() != null){
                    for (ImagenAlergeno existente : imagenesEntidad){
                        if (existente.getId() != null && existente.getId().equals(imagenDTO.getId())){
                            yaExiste = true;
                            break;
                        }
                    }
                }

                if(!yaExiste){
                    ImagenAlergeno nueva = new ImagenAlergeno();
                    nueva.setEnlace(imagenDTO.getEnlace());
                    nueva.setTipo(imagenDTO.getTipo());
                    nueva.setAlergeno(entity);
                    imagenesEntidad.add(nueva);
                }
            }
        }

    }

}

package com.myweddingplanner.back.service;

import com.myweddingplanner.back.dto.ImagenProductoDTO;
import com.myweddingplanner.back.dto.ProductoDTO;
import com.myweddingplanner.back.model.ImagenProducto;
import com.myweddingplanner.back.model.Producto;
import com.myweddingplanner.back.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{


    private final ProductoRepository productoRepository;
    private final ImagenProductoService imagenProductoService;

    public ProductoServiceImpl(ProductoRepository repository, ImagenProductoService imagenProductoService) {
        this.productoRepository = repository;
        this.imagenProductoService = imagenProductoService;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductoDTO> findById(Long id) {

        Optional<Producto> opt = productoRepository.findById(id);

        return (opt.isEmpty()) ? Optional.empty() : Optional.of(toDTO(opt.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> findAll() {

        List<ProductoDTO> list = new ArrayList<>();

        for (Producto a: productoRepository.findAll()){
            list.add(toDTO(a));
        }
        return list;
    }

    @Override
    public ProductoDTO save(ProductoDTO dto) {
        Producto entity = (dto.getId() != null)
                ? productoRepository.findById(dto.getId()).orElseGet(Producto::new)
                : new Producto();

        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setEnlaceCompra(dto.getEnlaceCompra());
        entity.setValor(dto.getValor());

        sincronizarImagenes(entity, dto.getImagenes());

        Producto saved = productoRepository.save(entity);

        return toDTO(saved);
    }



    @Override
    public void deleteById(Long id) {

        productoRepository.deleteById(id);

    }

    @Override
    public ProductoDTO toDTO(Producto producto) {

        ProductoDTO dto = new ProductoDTO();

        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setEnlaceCompra(producto.getEnlaceCompra());
        dto.setValor(producto.getValor());

        List<ImagenProductoDTO> imagenes = (producto.getImagenes() == null) ?
                List.of() : producto.getImagenes().stream().map(imagenProductoService::toDTO).toList();
        dto.setImagenes(new ArrayList<>(imagenes));

        return dto;
    }

    private void sincronizarImagenes(Producto entity, List<ImagenProductoDTO> imagenesDTO) {
          /*
        Tengo que comparar dos listas de imagenes, la que viene en el DTO y la que vienen en la entidad
         */


        // Si la entidad no tiene imagenes inicializamos el array vacío,
        // esto es que no tenemos imagenes en la bbdd

        if (entity.getImagenes() == null){
            entity.setImagenes(new ArrayList<>());
        }

        // en esta variable guardamos el list vacío o no que tenga la entidad
        List<ImagenProducto> imagenesEntidad = entity.getImagenes();

        // en esta variable guardamos las imagenes que estando en la entidad no esten en dto
        List<ImagenProducto> aEliminar = new ArrayList<>();

        // para cada imagen de alergeno guardada en la BBDD
        // la comparamos con el list de imagenes que vienen en el dto
        // si encontramos dos id iguales es que ya está en bbdd
        // entonces actualizamos la info de la bbdd con lo que venga en dto
        // si no encontramos en el dto la imagen que tenemos en bbdd la agregamos a la lista para eliminar
        for (ImagenProducto img : imagenesEntidad){

            boolean encontradoEnDTO = false;

            /*
                    una imagen del list de imagenes de la entidad

                    existe en dto?

                        SI -> seteamos los miembros por si cambian y mantenemos el id

                        NO -> la agregamos al list aEliminar

             */

            if (imagenesDTO != null){

                // comparamos con la imagen actual todas las del list del dto

                for (ImagenProductoDTO imagenEnDTO : imagenesDTO){

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
        for (ImagenProducto imagen : aEliminar){
            imagenesEntidad.remove(imagen);
        }

        // ya modificamos las que estaban en dto y en entidad
        // eliminamos de la entidad las que ya no estaban en dto
        // tenemos que agregar a la entidad las que estan en dto pero no en la entidad

        if (imagenesDTO != null){
            for (ImagenProductoDTO imagenDTO : imagenesDTO){
                boolean yaExiste = false;

                if (imagenDTO.getId() != null){
                    for (ImagenProducto existente : imagenesEntidad){
                        if (existente.getId() != null && existente.getId().equals(imagenDTO.getId())){
                            yaExiste = true;
                            break;
                        }
                    }
                }

                if(!yaExiste){
                    ImagenProducto nueva = new ImagenProducto();
                    nueva.setEnlace(imagenDTO.getEnlace());
                    nueva.setTipo(imagenDTO.getTipo());
                    nueva.setProducto(entity);
                    imagenesEntidad.add(nueva);
                }
            }
        }
    }

}

package com.avistamientos_animales.demo.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.avistamientos_animales.demo.model.Animal;
import com.avistamientos_animales.demo.model.Avistamiento;
import com.avistamientos_animales.demo.model.Multimedia;
import com.avistamientos_animales.demo.model.Observador;
import com.avistamientos_animales.demo.model.Ubicacion;
import com.avistamientos_animales.demo.repository.AnimalRepository;
import com.avistamientos_animales.demo.repository.AvistamientoRepository;
import com.avistamientos_animales.demo.repository.ObservadorRepository;
import com.avistamientos_animales.demo.repository.UbicacionRepository;
import com.avistamientos_animales.demo.service.AnimalService;
import com.avistamientos_animales.demo.service.AvistamientoService;
import com.avistamientos_animales.demo.service.MultimediaService;
import com.avistamientos_animales.demo.service.ObservadorService;
import com.avistamientos_animales.demo.service.UbicacionService;

@Controller
@RequestMapping("/avistamientos")
public class AvistamientoController {

    private final String RUTA_UPLOADS = "C:/imagenes_avistamientos/";

    @Autowired
    private AvistamientoService avistamientoService;
    @Autowired
    private AnimalService animalService;
    @Autowired
    private UbicacionService ubicacionService;
    @Autowired
    private ObservadorService observadorService;
    @Autowired
    private MultimediaService multimediaService;

    @Autowired
    private ObservadorRepository observadorRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private AvistamientoRepository avistamientoRepository;
    // Mostrar lista de avistamientos
    @GetMapping("/consultar")
    public String listar(Model model) {
        model.addAttribute("avistamientos", avistamientoService.listar());
        return "avistamiento-consultar";
    }
    // Mostrar formulario de agregar
    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("avistamiento", new Avistamiento());
        model.addAttribute("animales", animalService.listar());
        model.addAttribute("ubicaciones", ubicacionService.listar());
        model.addAttribute("observadores", observadorService.listar());
        return "avistamiento-agregar";
    }
    // Guardar avistamiento
  @PostMapping("/guardar")
public String guardar(
        @RequestParam String tipo,
        @RequestParam String clima,
        @RequestParam String descripcion,
        @RequestParam String cantidad,
        @RequestParam String idAnimal,
        @RequestParam String idObservador,
        @RequestParam String idUbicacion,
        @RequestParam("archivos") MultipartFile[] archivos
) throws IOException {

    Avistamiento avistamiento = new Avistamiento();
    avistamiento.setIdAvistamiento(UUID.randomUUID().toString());
    avistamiento.setTipo(tipo);
    avistamiento.setClima(clima);
    avistamiento.setDescripcion(descripcion);
    avistamiento.setCantidad(cantidad);

    Observador observador = observadorRepository.findById(idObservador)
            .orElseThrow(() -> new RuntimeException("Observador no encontrado"));
    Animal animal = animalRepository.findById(idAnimal)
            .orElseThrow(() -> new RuntimeException("Animal no encontrado"));
    Ubicacion ubicacion = ubicacionRepository.findById(idUbicacion)
            .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));

    avistamiento.setObservador(observador);
    avistamiento.setAnimal(animal);
    avistamiento.setUbicacion(ubicacion);

    // Crear carpeta si no existe
    File carpeta = new File(RUTA_UPLOADS);
    if (!carpeta.exists()) carpeta.mkdirs();

    // Guardar archivos en disco y crear Multimedia
    for (MultipartFile archivo : archivos) {
        if (!archivo.isEmpty()) {
            String nombreUnico = UUID.randomUUID() + "_" + archivo.getOriginalFilename();
            File destino = new File(RUTA_UPLOADS + nombreUnico);
            archivo.transferTo(destino);

            Multimedia multimedia = new Multimedia();
            multimedia.setIdImagen(multimediaService.generarNuevoId());
            multimedia.setArchivo(nombreUnico); // solo nombre del archivo
            multimedia.setTipo(archivo.getContentType());
            multimedia.setFecha(LocalDate.now());
            multimedia.setAvistamiento(avistamiento);

            avistamiento.getMultimedias().add(multimedia);
        }
    }

    avistamientoRepository.save(avistamiento);
    return "redirect:/avistamientos/consultar";
}



    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        avistamientoService.eliminar(id);
        return "redirect:/avistamientos/consultar";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable String id, Model model) {
        Avistamiento avistamiento = avistamientoService.obtenerPorId(id);
        if (avistamiento != null) {
            model.addAttribute("avistamiento", avistamiento);
            model.addAttribute("animales", animalService.listar());
            model.addAttribute("ubicaciones", ubicacionService.listar());
            model.addAttribute("observadores", observadorService.listar());
            return "avistamiento-editar";
        } else {
            return "redirect:/avistamientos/consultar";
        }
    }
    @PostMapping("/actualizar")
public String actualizar(
        @ModelAttribute Avistamiento avistamiento,
        @RequestParam("archivos") MultipartFile[] archivos
) throws IOException {

    // Recuperar el avistamiento completo desde BD (importante)
    Avistamiento original = avistamientoService.obtenerPorId(avistamiento.getIdAvistamiento());

    // Actualizar campos modificables
    original.setTipo(avistamiento.getTipo());
    original.setClima(avistamiento.getClima());
    original.setDescripcion(avistamiento.getDescripcion());
    original.setCantidad(avistamiento.getCantidad());
    original.setAnimal(avistamiento.getAnimal());
    original.setUbicacion(avistamiento.getUbicacion());

    // Crear carpeta si no existe
    File carpeta = new File(RUTA_UPLOADS);
    if (!carpeta.exists()) carpeta.mkdirs();

    // Guardar nuevas imágenes si hay
    if (archivos != null) {
        for (MultipartFile archivo : archivos) {
            if (!archivo.isEmpty()) {

                String nombreUnico = UUID.randomUUID() + "_" + archivo.getOriginalFilename();
                File destino = new File(RUTA_UPLOADS + nombreUnico);
                archivo.transferTo(destino);

                Multimedia multimedia = new Multimedia();
                multimedia.setIdImagen(multimediaService.generarNuevoId());
                multimedia.setArchivo(nombreUnico);
                multimedia.setTipo(archivo.getContentType());
                multimedia.setFecha(LocalDate.now());
                multimedia.setAvistamiento(original);

                original.getMultimedias().add(multimedia);
            }
        }
    }

    avistamientoService.guardar(original);
    return "redirect:/avistamientos/consultar";
}


    @GetMapping("/multimedia/{idAvistamiento}")
    public String verMultimedia(@PathVariable String idAvistamiento, Model model) {
        Avistamiento avistamiento = avistamientoService.obtenerPorId(idAvistamiento);
        
        if (avistamiento != null) {
            model.addAttribute("avistamiento", avistamiento);
            model.addAttribute("multimedias", avistamiento.getMultimedias());
            return "avistamiento-multimedia";
        } else {
            return "redirect:/avistamientos/consultar";
        }
    }
    @GetMapping("")
    public String vistaPrincipal(Model model, Principal principal) {

    // Recupera al usuario desde Spring Security
    Observador usuario = observadorService.buscarPorCorreo(principal.getName());

    model.addAttribute("usuario", usuario);
    return "avistamientos"; // tu avistamientos.html
}
}

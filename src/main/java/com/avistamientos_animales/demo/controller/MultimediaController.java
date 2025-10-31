package com.avistamientos_animales.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avistamientos_animales.demo.model.Avistamiento;
import com.avistamientos_animales.demo.model.Multimedia;
import com.avistamientos_animales.demo.service.MultimediaService;
import com.avistamientos_animales.demo.service.AvistamientoService;

@Controller
@RequestMapping("/multimedia")
public class MultimediaController {
    @Autowired
    private MultimediaService multimediaService;
    @Autowired
    private AvistamientoService avistamientoService;
    // Mostrar lista de multimedia
    @GetMapping("/consultar")
    public String listar(Model model) {
        model.addAttribute("multimedias", multimediaService.listar());
        return "multimedia-consultar";
    }
    // Mostrar formulario de agregar
    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("multimedia", new Multimedia());
        model.addAttribute("avistamientos", avistamientoService.listar());
        return "multimedia-agregar";
    }
    // Guardar multimedia
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Multimedia multimedia) {
        multimediaService.guardar(multimedia);
        return "redirect:/multimedia";
    }
    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        Multimedia multimedia = multimediaService.obtenerPorId(id);
        
        if (multimedia != null) {
            String idAvistamiento = multimedia.getAvistamiento().getIdAvistamiento();
            
            // Eliminar la multimedia
            multimediaService.eliminar(id);

            // Verificar si el avistamiento quedó sin multimedia
            Avistamiento avistamiento = multimedia.getAvistamiento();
            if (avistamiento.getMultimedias() == null || avistamiento.getMultimedias().isEmpty()) {
                avistamientoService.eliminar(idAvistamiento);
                System.out.println("El avistamiento " + idAvistamiento + " fue eliminado porque ya no tiene multimedia asociada.");
                return "redirect:/avistamientos/consultar";
            }

            // Si aún tiene multimedia, volver a su lista
            return "redirect:/avistamientos/multimedia/" + idAvistamiento;
        }

        return "redirect:/avistamientos/consultar";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable String id, Model model) {
        Multimedia multimedia = multimediaService.obtenerPorId(id);
        if (multimedia != null) {
            model.addAttribute("multimedia", multimedia);
            model.addAttribute("avistamientos", avistamientoService.listar());
            return "multimedia-editar";
        } else {
            return "redirect:/multimedia/consultar";
        }
    }
    // Actualizar multimedia
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Multimedia multimedia) {
        multimediaService.actualizar(multimedia);
        return "redirect:/multimedia/consultar";
    }
}

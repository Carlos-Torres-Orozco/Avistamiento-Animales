package com.avistamientos_animales.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.avistamientos_animales.demo.model.Ubicacion;
import com.avistamientos_animales.demo.service.UbicacionService;

@Controller
@RequestMapping("/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    //Mostrar lista de ubicaciones
    @GetMapping("/consultar")
    public String listar(Model model) {
        model.addAttribute("ubicaciones", ubicacionService.listar());
        return "ubicacion-consultar";
    }

    // Mostrar formulario de agregar
    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("ubicacion", new Ubicacion());
        List<String> paises = Arrays.asList(
    "Afganistán", "Albania", "Alemania", "Andorra", "Angola",
    "Antigua y Barbuda", "Arabia Saudita", "Argelia", "Argentina", "Armenia",
    "Australia", "Austria", "Azerbaiyán", "Bahamas", "Bangladés",
    "Barbados", "Baréin", "Bélgica", "Belice", "Benín",
    "Bhután", "Bielorrusia", "Birmania", "Bolivia", "Bosnia y Herzegovina",
    "Botsuana", "Brasil", "Brunéi", "Bulgaria", "Burkina Faso",
    "Burundi", "Bután", "Cabo Verde", "Camboya", "Camerún",
    "Canadá", "Catar", "Chad", "Chile", "China",
    "Chipre", "Colombia", "Comoras", "Congo (Brazzaville)", "Congo (Kinshasa)",
    "Corea del Norte", "Corea del Sur", "Costa de Marfil", "Costa Rica", "Croacia",
    "Cuba", "Dinamarca", "Dominica", "Ecuador", "Egipto",
    "El Salvador", "Emiratos Árabes Unidos", "Eritrea", "Eslovaquia", "Eslovenia",
    "España", "Estados Unidos", "Estonia", "Esuatini", "Etiopía",
    "Filipinas", "Finlandia", "Fiyi", "Francia", "Gabón",
    "Gambia", "Georgia", "Ghana", "Granada", "Grecia",
    "Guatemala", "Guinea", "Guinea-Bisáu", "Guinea Ecuatorial", "Guyana",
    "Haití", "Honduras", "Hungría", "India", "Indonesia",
    "Irak", "Irán", "Irlanda", "Islandia", "Islas Marshall",
    "Islas Salomón", "Israel", "Italia", "Jamaica", "Japón",
    "Jordania", "Kazajistán", "Kenia", "Kirguistán", "Kiribati",
    "Kuwait", "Laos", "Lesoto", "Letonia", "Líbano",
    "Liberia", "Libia", "Liechtenstein", "Lituania", "Luxemburgo",
    "Madagascar", "Malasia", "Malaui", "Maldivas", "Malí",
    "Malta", "Marruecos", "Mauricio", "Mauritania", "México",
    "Micronesia", "Moldavia", "Mónaco", "Mongolia", "Montenegro",
    "Mozambique", "Namibia", "Nauru", "Nepal", "Nicaragua",
    "Níger", "Nigeria", "Noruega", "Nueva Zelanda", "Omán",
    "Países Bajos", "Pakistán", "Palaos", "Panamá", "Papúa Nueva Guinea",
    "Paraguay", "Perú", "Polonia", "Portugal", "Reino Unido",
    "República Centroafricana", "República Checa", "República Dominicana", "Ruanda", "Rumania",
    "Rusia", "Samoa", "San Cristóbal y Nieves", "San Marino", "San Vicente y las Granadinas",
    "Santa Lucía", "Santo Tomé y Príncipe", "Senegal", "Serbia", "Seychelles",
    "Sierra Leona", "Singapur", "Siria", "Somalia", "Sri Lanka",
    "Sudáfrica", "Sudán", "Sudán del Sur", "Suecia", "Suiza",
    "Surinam", "Tailandia", "Tanzania", "Tayikistán", "Timor Oriental",
    "Togo", "Tonga", "Trinidad y Tobago", "Túnez", "Turkmenistán",
    "Turquía", "Tuvalu", "Ucrania", "Uganda", "Uruguay",
    "Uzbekistán", "Vanuatu", "Venezuela", "Vietnam", "Yemen",
    "Yibuti", "Zambia", "Zimbabue"
    );
    model.addAttribute("paises", paises);
        return "ubicacion-agregar";
    }

    // Guardar ubicacion
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Ubicacion ubicacion) {
        ubicacionService.guardar(ubicacion);
        return "redirect:/ubicaciones";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        ubicacionService.eliminar(id);
        return "redirect:/ubicaciones/consultar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable String id, Model model) {
        Ubicacion ubicacion = ubicacionService.obtenerPorId(id);
        if (ubicacion != null) {
            model.addAttribute("ubicacion", ubicacion);
            List<String> paises = Arrays.asList(
    "Afganistán", "Albania", "Alemania", "Andorra", "Angola",
    "Antigua y Barbuda", "Arabia Saudita", "Argelia", "Argentina", "Armenia",
    "Australia", "Austria", "Azerbaiyán", "Bahamas", "Bangladés",
    "Barbados", "Baréin", "Bélgica", "Belice", "Benín",
    "Bhután", "Bielorrusia", "Birmania", "Bolivia", "Bosnia y Herzegovina",
    "Botsuana", "Brasil", "Brunéi", "Bulgaria", "Burkina Faso",
    "Burundi", "Bután", "Cabo Verde", "Camboya", "Camerún",
    "Canadá", "Catar", "Chad", "Chile", "China",
    "Chipre", "Colombia", "Comoras", "Congo (Brazzaville)", "Congo (Kinshasa)",
    "Corea del Norte", "Corea del Sur", "Costa de Marfil", "Costa Rica", "Croacia",
    "Cuba", "Dinamarca", "Dominica", "Ecuador", "Egipto",
    "El Salvador", "Emiratos Árabes Unidos", "Eritrea", "Eslovaquia", "Eslovenia",
    "España", "Estados Unidos", "Estonia", "Esuatini", "Etiopía",
    "Filipinas", "Finlandia", "Fiyi", "Francia", "Gabón",
    "Gambia", "Georgia", "Ghana", "Granada", "Grecia",
    "Guatemala", "Guinea", "Guinea-Bisáu", "Guinea Ecuatorial", "Guyana",
    "Haití", "Honduras", "Hungría", "India", "Indonesia",
    "Irak", "Irán", "Irlanda", "Islandia", "Islas Marshall",
    "Islas Salomón", "Israel", "Italia", "Jamaica", "Japón",
    "Jordania", "Kazajistán", "Kenia", "Kirguistán", "Kiribati",
    "Kuwait", "Laos", "Lesoto", "Letonia", "Líbano",
    "Liberia", "Libia", "Liechtenstein", "Lituania", "Luxemburgo",
    "Madagascar", "Malasia", "Malaui", "Maldivas", "Malí",
    "Malta", "Marruecos", "Mauricio", "Mauritania", "México",
    "Micronesia", "Moldavia", "Mónaco", "Mongolia", "Montenegro",
    "Mozambique", "Namibia", "Nauru", "Nepal", "Nicaragua",
    "Níger", "Nigeria", "Noruega", "Nueva Zelanda", "Omán",
    "Países Bajos", "Pakistán", "Palaos", "Panamá", "Papúa Nueva Guinea",
    "Paraguay", "Perú", "Polonia", "Portugal", "Reino Unido",
    "República Centroafricana", "República Checa", "República Dominicana", "Ruanda", "Rumania",
    "Rusia", "Samoa", "San Cristóbal y Nieves", "San Marino", "San Vicente y las Granadinas",
    "Santa Lucía", "Santo Tomé y Príncipe", "Senegal", "Serbia", "Seychelles",
    "Sierra Leona", "Singapur", "Siria", "Somalia", "Sri Lanka",
    "Sudáfrica", "Sudán", "Sudán del Sur", "Suecia", "Suiza",
    "Surinam", "Tailandia", "Tanzania", "Tayikistán", "Timor Oriental",
    "Togo", "Tonga", "Trinidad y Tobago", "Túnez", "Turkmenistán",
    "Turquía", "Tuvalu", "Ucrania", "Uganda", "Uruguay",
    "Uzbekistán", "Vanuatu", "Venezuela", "Vietnam", "Yemen",
    "Yibuti", "Zambia", "Zimbabue"
    );
    model.addAttribute("paises", paises);
            return "ubicacion-editar";
        } else {
            // Redirige si no encuentra la ubicacion
            return "redirect:/ubicaciones";
        }
    }

    // 🔹 Guardar cambios de la ubicacio
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("ubicacion") Ubicacion ubicacion) {
        ubicacionService.actualizar(ubicacion);
        return "redirect:/ubicaciones/consultar";
    }
}

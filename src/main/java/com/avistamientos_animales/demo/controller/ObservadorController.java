package com.avistamientos_animales.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.avistamientos_animales.demo.model.Observador;
import com.avistamientos_animales.demo.service.ObservadorService;

@Controller
@RequestMapping("/observadores")
public class ObservadorController {
     @Autowired
    private ObservadorService observadorService;

    @Autowired
        private PasswordEncoder passwordEncoder;

    //Mostrar lista de observadores
    @GetMapping("/consultar")
    public String listar(Model model) {
        model.addAttribute("observadores", observadorService.listar());
        return "observador-consultar";
    }

    // Mostrar formulario de agregar
    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("observador", new Observador());
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
        return "observador-agregar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Observador observador) {

        String encrypted = passwordEncoder.encode(observador.getContrasena());
        observador.setContrasena(encrypted);

        observadorService.guardar(observador);

        return "redirect:/login";
    }

    @PostMapping("/guardarObser")
    public String guardarObservadorAdmin(@ModelAttribute Observador observador) {

        String encrypted = passwordEncoder.encode(observador.getContrasena());
        observador.setContrasena(encrypted);

        observadorService.guardar(observador);

        // Redirige a la lista de observadores
        return "redirect:/observadores";
    }


    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        observadorService.eliminar(id);
        return "redirect:/observadores/consultar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable String id, Model model) {
        Observador observador = observadorService.obtenerPorId(id);
        if (observador != null) {
            model.addAttribute("observador", observador);
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
            return "observador-editar";
        } else {
            // Redirige si no encuentra el observador
            return "redirect:/observadores";
        }
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("observador") Observador observador) {
        observadorService.actualizar(observador);
        return "redirect:/observadores";
    }

    @GetMapping("/crear-cuenta")
    public String mostrarFormularioLogIn(Model model) {
        model.addAttribute("observador", new Observador());
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
        return "observadores-creacion";
    }
}

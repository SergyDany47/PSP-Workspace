package com.uem.hash.controlador;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.uem.hash.modelo.entidad.Usuario;
import com.uem.hash.modelo.negocio.GestorUsuario;

@Controller
public class ControladorPersona {

	@Autowired
	private GestorUsuario gestorUsuario;

	@PostMapping("/doLogin")
	public String login(@RequestParam("nombre") String username, @RequestParam("pass") String password, Model model)
			throws NoSuchAlgorithmException {

		String passwordHasheada = gestorUsuario.hashear(password);
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(passwordHasheada);

		boolean validado = gestorUsuario.validar(usuario);

		if (validado) {

			model.addAttribute("fecha", new Date());
			List<String> lista = new ArrayList<String>();
			lista.add(username);
			lista.add(passwordHasheada);
			model.addAttribute("lista", lista);
			return "inicio";

		} else {

			return "errorLogin";
		}
	}

	@PostMapping("/doSignUp")
	public String signup(@RequestParam("nombre") String username, @RequestParam("pass") String password, Model model)
			throws NoSuchAlgorithmException {

		String passwordHasheada = gestorUsuario.hashear(password);
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(passwordHasheada);

		boolean validado = gestorUsuario.registrarUsuario(usuario);

		if (validado) {

			model.addAttribute("fecha", new Date());
			List<String> lista = new ArrayList<String>();
			lista.add(username);
			lista.add(passwordHasheada);
			model.addAttribute("lista", lista);
			return "inicio";

		} else {

			return "errorLogin";
		}
	}
}
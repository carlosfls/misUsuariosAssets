package com.springboot.jdbc.core.controller;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.jdbc.core.dao.ModuloDao;
import com.springboot.jdbc.core.dao.UsuarioDao;
import com.springboot.jdbc.core.entity.Modulo;
import com.springboot.jdbc.core.entity.UsuarioAssets;

@Controller
@RequestMapping("empresa")
public class MainController {
	
	Log logger = LogFactory.getLog(MainController.class);

	@Autowired
	UsuarioDao usuarioDao;

	@Autowired
	ModuloDao moduloDao;
	
	@GetMapping("/")
	public String getUsuariosPage(Model modelo) {
		List<UsuarioAssets> listado = new ArrayList<UsuarioAssets>();
		usuarioDao.listarUsuariosAssets().forEach(listado::add);
		modelo.addAttribute("listaUs", listado);

		return "usersHome";
	}
	
	@GetMapping("/estado/{estado}")
	public String getUsuariosPorEstado(@PathVariable(name = "estado")boolean estado,Model modelo) {
		List<UsuarioAssets> listado = new ArrayList<UsuarioAssets>();
		if(estado) {
			usuarioDao.listarUsuariosAssets().stream().filter(u -> u.isActivo()).forEach(listado::add);
		}else {
			usuarioDao.listarUsuariosAssets().stream().filter(u -> !u.isActivo()).forEach(listado::add);
		}
		modelo.addAttribute("listaUs", listado);
		return "usersHome";
	}

	@GetMapping("resetPassword/{idUser}")
	public String resetPassword(@PathVariable("idUser") String idUser, RedirectAttributes redAttributes) {
		UsuarioAssets userReset = null;
		userReset = usuarioDao.verDetalles(idUser);
		if (userReset == null) {
			redAttributes.addFlashAttribute("mensajeError", "ha ocurrido un error");
			logger.error("ha ocurrido un error");
			return "redirect:/empresa/";
		} else {
			int exito = usuarioDao.resetPassword(userReset);
			logger.info(exito + " filas afectadas..");
			redAttributes.addFlashAttribute("mensaje", "cambios efectuados satisfactoriamenete");
			return "redirect:/empresa/";
		}
	}

	@GetMapping("cambiaEstado/{idUser}/{estado}")
	public String cambiarEstado(@PathVariable("idUser") String idUser, @PathVariable("estado") boolean estado,
			RedirectAttributes redAttributes) {
		UsuarioAssets userReset = null;
		userReset = usuarioDao.verDetalles(idUser);
		if (userReset == null) {
			logger.error("ha ocurrido un error");
			redAttributes.addFlashAttribute("mensajeError", "ha ocurrido un error");
			return "redirect:/empresa/";
		} else {
			int exito = usuarioDao.cambiarEstado(userReset, estado);
			redAttributes.addFlashAttribute("mensaje", "cambios efectuados satisfactoriamenete");
			logger.info(exito + " filas afectadas..");
			return "redirect:/empresa/";
		}

	}

	@GetMapping("modulos/{idUser}/{acceso}")
	public String getModulosUs(@PathVariable(name = "idUser",required = true) String idUser,@PathVariable(name = "acceso")String acceso, Model modelo) {
		List<Modulo> misModulos = new ArrayList<>();
		if(acceso.equals("all")) {
			moduloDao.misModulos(idUser).forEach(misModulos::add);
		}else {
			moduloDao.misModulos(idUser).stream().filter(m -> m.getAcceso().equals(acceso)).forEach(misModulos::add);
		}
		modelo.addAttribute("misModulos", misModulos);
		modelo.addAttribute("accesosUsr", moduloDao.populateFilter(idUser));
		modelo.addAttribute("id", idUser);
		modelo.addAttribute("titulo", "Modulos del Usuario: " + idUser);
		return "modulosUs";
	}
}

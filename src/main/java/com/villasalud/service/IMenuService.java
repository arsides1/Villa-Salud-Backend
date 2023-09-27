package com.villasalud.service;

import com.villasalud.model.Menu;

import java.util.List;

public interface IMenuService extends ICRUD<Menu>{
	
	List<Menu> listarMenuPorUsuario(String nombre);
}

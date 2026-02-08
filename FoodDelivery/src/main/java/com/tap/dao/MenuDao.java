package com.tap.dao;

import java.util.List;

import com.tap.model.Menu;

public interface MenuDao {

	void addMenu(Menu menu);

	Menu getMenu(int id);

	void updateMenu(Menu menu);

	void deleteMenu(int id);

	List<Menu> getAllMenus();

	List<Menu> getAllMenusByRestaurant(int id);

}

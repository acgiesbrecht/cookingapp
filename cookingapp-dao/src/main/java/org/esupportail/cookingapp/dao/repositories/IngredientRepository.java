package org.esupportail.cookingapp.dao.repositories;

import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	/**
	 * Finds the {@link Ingredient} by its name.
	 * @param name
	 * @return
	 */
	Ingredient findByName(String name);
	
}

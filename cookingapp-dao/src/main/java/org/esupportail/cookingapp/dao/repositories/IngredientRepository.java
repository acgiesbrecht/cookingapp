package org.esupportail.cookingapp.dao.repositories;

import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>, JpaSpecificationExecutor<Ingredient> {

	/**
	 * Finds the {@link Ingredient} by its name.
	 * @param name
	 * @return
	 */
	Ingredient findByName(String name);
	
	/**
	 * Finds the {@link Ingredient}s which name starting with the string parameter.
	 * @param name
	 * @return
	 */
	Page<Ingredient> findByNameStartingWith(String name, Pageable pageable);
	
}

package org.esupportail.cookingapp.dao.repositories;

import org.esupportail.cookingapp.domain.beans.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	/**
	 * Finds the list of {@link Recipe} which name match the parameter.
	 * @param name
	 * @return
	 */
	Iterable<Recipe> findByName(String name);
	
}

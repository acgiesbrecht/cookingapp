package org.esupportail.cookingapp.web.beans;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import static org.esupportail.cookingapp.web.beans.Conversions.sortOrder2Direction;

import org.esupportail.cookingapp.domain.beans.Ingredient;
import org.esupportail.cookingapp.domain.services.IngredientService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import fj.data.Option;

@Named
public class IngredientLazyDataModel extends LazyDataModel<Ingredient> {

	/**
	 * Serialization id.
	 */
	private static final long serialVersionUID = -1799343349969561213L;

	/**
	 * A filter on {@link Ingredient}'s name.
	 */
	private String filter;
	
	/**
	 * Search result.
	 */
	private Page<Ingredient> page;

	/**
	 * A service.
	 */
	@Inject
	private IngredientService ingredientService;

	@Override
	public List<Ingredient> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		final Option<String> filtered = Option.fromString(filter);
		final int pageNumber = Double.valueOf(Math.ceil(first / pageSize)).intValue();
		final Sort sort = (sortField != null) ? new Sort(sortOrder2Direction(sortOrder), sortField) : null;
		if (filtered.isSome()) {
			page = ingredientService.getIngredientsStartingWith(filtered.some(), pageNumber, pageSize, sort);
		} else {
			page = ingredientService.getIngredients(pageNumber, pageSize, sort, filters);
		}
		this.setRowCount(Long.valueOf(page.getTotalElements()).intValue());
		return page.getContent();
	}
	
    @Override  
    public Object getRowKey(final Ingredient ingredient) {  
        return ingredient.getName();  
    }

	@Override
	public Ingredient getRowData(final String rowKey) {
		for (Ingredient ingredient : page) {
			if (ingredient.getName().equals(rowKey))
				return ingredient;
		}
		return null;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilter(final String filter) {
		this.filter = filter;
	}
	
	/**
	 * @return filter
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * @param filter the filter to set
	 */
	public IngredientLazyDataModel withFilter(final String filter) {
		this.filter = filter;
		return this;
	}
}

/**
 * 
 */
package org.esupportail.cookingapp.web.rewrite;

/**
 * @author llevague
 *
 */
public class NavigationRules {
		
	//////////////////////////////// OUTBOUND ///////////////////////////////
	
	public static final String REDIRECT = "?faces-redirect=true";
	
	public static final String HOME = "/stylesheets/welcome.xhtml";
	public static final String NOTFOUND = "/stylesheets/404.xhtml";

	public static final String INGREDIENTS_LIST = "/stylesheets/ingredients/list.xhtml";
	public static final String INGREDIENT_ADD = "/stylesheets/ingredients/add.xhtml";
	
	public static final String RECIPES_LIST = "/stylesheets/recipes/list.xhtml";
	public static final String RECIPE_ADD = "/stylesheets/recipes/add.xhtml";
	
	
	private  NavigationRules() {
	}
}

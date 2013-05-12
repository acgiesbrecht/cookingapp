/**
 * 
 */
package org.esupportail.cookingapp.utils;

import static fj.Ord.ord;
import static fj.Ordering.EQ;
import static fj.Ordering.GT;
import static fj.Ordering.LT;

import java.text.CollationKey;
import java.text.Collator;

import org.esupportail.cookingapp.domain.beans.Ingredient;

import fj.F;
import fj.Ord;
import fj.Ordering;

/**
 * @author llevague
 *
 */
public class SortUtils {
	
	/**
	 * Private constructor.
	 */
	private SortUtils() {
	}
	
	/**
	 * Sort {@link Ingredient} objects.
	 */
	public static final Ord<Ingredient> ingredientOrd = ord(new F<Ingredient, F<Ingredient, Ordering>>() {
		final Collator collator = Collator.getInstance();
		public F<Ingredient, Ordering> f(final Ingredient i1) {
			return new F<Ingredient, Ordering>() {
				public Ordering f(final Ingredient i2) {
					final CollationKey k1 = 
							collator.getCollationKey(i1.getName());
					final CollationKey k2 = 
							collator.getCollationKey(i2.getName());
					final int x = k1.compareTo(k2);
					return x < 0 ? LT : x == 0 ? EQ : GT;
				}
			};
		};
	});

}

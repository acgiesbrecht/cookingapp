/**
 * 
 */
package org.esupportail.cookingapp.web.rewrite;

import static org.ocpsoft.rewrite.servlet.config.Path.matches;

import org.ocpsoft.rewrite.config.Condition;
import org.ocpsoft.rewrite.context.EvaluationContext;
import org.ocpsoft.rewrite.event.Rewrite;

/**
 * @author llevague
 *
 */
public abstract class ResourcesCondition implements Condition {
	
	
	public static Condition excluded() {
		return new ResourcesCondition() {
			@Override
			public boolean evaluate(final Rewrite event,
					final EvaluationContext context) {
				
				return matches(".*")
//						.andNot(matches(".*javax\\.faces\\.resource.*"))
//						.andNot(matches(".*\\.css.*"))
//						.andNot(matches(".*\\.js.*"))
						.evaluate(event, context);
			}
		};
	}
	public static Condition included() {
		return new ResourcesCondition() {
			@Override
			public boolean evaluate(final Rewrite event,
					final EvaluationContext context) {
				return matches(".*javax\\.faces\\.resource.*")
						.or(matches("*\\.css"))
						.or(matches("*\\.js"))
						.evaluate(event, context);
			}
		};
	}
}

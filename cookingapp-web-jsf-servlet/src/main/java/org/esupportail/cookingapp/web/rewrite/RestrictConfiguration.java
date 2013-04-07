package org.esupportail.cookingapp.web.rewrite;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.context.EvaluationContext;
import org.ocpsoft.rewrite.event.Rewrite;
import org.ocpsoft.rewrite.param.Constraint;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;

public class RestrictConfiguration extends HttpConfigurationProvider {
		
	public static final String authorizedCharsPattern = "[a-zA-Z0-9/:&?.-=_+]*";
	
	public static final Constraint<String> authorizedChars = new Constraint<String>() {
		@Override
		public boolean isSatisfiedBy(Rewrite event, EvaluationContext context,
				String value) {
			return value.matches(authorizedCharsPattern);
		}
	};
	
	@Override
	public int priority() {
		return -1000;
	}

	@Override
	public Configuration getConfiguration(final ServletContext context) {
		return ConfigurationBuilder.begin()
				
//				.addRule()
//	            .when(Direction.isInbound()
//	                     .andNot(DispatchType.isForward())
//	                     .and(Path.captureIn("path"))
//	                     .and(URL.captureIn("url")
//	                    		 .where("url").constrainedBy(authorizedChars))
//	                     
//                          .and(RequestParameter.matchesAll("{name}", "{value}")
//			                  .where("name").constrainedBy(authorizedChars)
//			                  .where("value").constrainedBy(authorizedChars)
//			                  .orNot(RequestParameter.exists("{}")))
//	            )
//	            .perform(Forward.to("{path}"))
	            
	            // Block direct access to XHTML files
//	            .addRule()
//				.when(Direction.isInbound()
//						.andNot(DispatchType.isForward())
//						.and(ResourcesCondition.excluded())
//					)
//				.perform(SendStatus.error(404))

			;
	}
}

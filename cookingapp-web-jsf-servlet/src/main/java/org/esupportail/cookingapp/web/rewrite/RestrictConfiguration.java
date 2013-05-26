package org.esupportail.cookingapp.web.rewrite;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.DispatchType;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Substitute;

public class RestrictConfiguration extends HttpConfigurationProvider {

	@Override
	public int priority() {
		return -1000;
	}
	
	@Override
	public Configuration getConfiguration(final ServletContext context) {
		return ConfigurationBuilder.begin()
					            
	            // Block direct access to XHTML files
               .addRule()
               .when(Direction.isInbound()
                        .andNot(DispatchType.isForward())
                        .and(Path.matches("{*}.xhtml")
                        		.or(Path.matches("{*}/{*}/")))
                        .andNot(Path.matches("{*}javax.faces.resource{*}")))
						.perform(Substitute.with("/404"))
			;
	}
}

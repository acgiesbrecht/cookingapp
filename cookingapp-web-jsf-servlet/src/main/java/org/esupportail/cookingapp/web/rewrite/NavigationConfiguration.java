package org.esupportail.cookingapp.web.rewrite;

import static org.esupportail.cookingapp.web.rewrite.NavigationRules.HOME;
import static org.esupportail.cookingapp.web.rewrite.NavigationRules.NOTFOUND;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Resource;
import org.ocpsoft.rewrite.servlet.config.Substitute;

public class NavigationConfiguration extends HttpConfigurationProvider {
		
	@Override
	public int priority() {
		return 10;
	}

	@Override
	public Configuration getConfiguration(final ServletContext context) {
		return ConfigurationBuilder.begin()
				
				//////////////////////////////// INBOUND ////////////////////////////////
				
				// Inbound access rules
				.addRule()
				.when(Direction.isInbound().and(Path.matches("/")))
				.perform(Forward.to(HOME))
				
				.addRule()
				.when(Direction.isInbound().and(Path.matches("/404")))
				.perform(Forward.to(NOTFOUND))
								
				.addRule()
				.when(Direction.isInbound()
						.and(Path.matches("/{objects}"))
						.and(Resource.exists("/stylesheets/{objects}/list.xhtml"))
				)
				.perform(Forward.to("/stylesheets/{objects}/list.xhtml"))
				
				.addRule()
				.when(Direction.isInbound()
						.and(Path.matches("/{objects}/{action}"))
						.and(Resource.exists("/stylesheets/{objects}/{action}.xhtml"))
				)
				.perform(Forward.to("/stylesheets/{objects}/{action}.xhtml"))
				
				.addRule()
				.when(Direction.isInbound()
						.and(Path.matches("/{objects}/{action}/{id}"))
						.and(Resource.exists("/stylesheets/{objects}/{action}.xhtml"))
				)
				.perform(Forward.to("/stylesheets/{objects}/{action}.xhtml?pk={id}"))
				

				//////////////////////////////// OUTBOUND ///////////////////////////////
				
				
				// Outbound access rules
				
				.addRule()
				.when(Direction.isOutbound()
						.and(Path.matches("/stylesheets/{objects}/list.xhtml"))
				)
				.perform(Substitute.with("/{objects}"))
				
				.addRule()
				.when(Direction.isOutbound()
						.and(Path.matches("/stylesheets/{objects}/{action}.xhtml"))
				)
				.perform(Substitute.with("/{objects}/{action}"))
			;
	}
}

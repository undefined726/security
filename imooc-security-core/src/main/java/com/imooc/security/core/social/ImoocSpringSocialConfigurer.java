/**
 * 
 */
package com.imooc.security.core.social;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author zhailiang
 *
 */
public class ImoocSpringSocialConfigurer extends SpringSocialConfigurer {

	private String filterProcessesUrl;

	public ImoocSpringSocialConfigurer(String filterProcessesUrl) {
		super();
		this.filterProcessesUrl = filterProcessesUrl;
	}
	
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		return (T) filter;
	}

	private <T> T getDependency(ApplicationContext applicationContext, Class<T> dependencyType) {
		try {
			T dependency = applicationContext.getBean(dependencyType);
			return dependency;
		} catch (NoSuchBeanDefinitionException e) {
			throw new IllegalStateException("SpringSocialConfigurer depends on " + dependencyType.getName() +". No single bean of that type found in application context.", e);
		}
	}
}

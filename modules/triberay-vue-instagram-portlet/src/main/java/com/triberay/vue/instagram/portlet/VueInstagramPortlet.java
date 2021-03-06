package com.triberay.vue.instagram.portlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.triberay.vue.instagram.config.InstagramConfiguration;
import com.triberay.vue.instagram.constants.InstagramKeys;

import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.Map;

import javax.portlet.*;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Triberay
 * @author Wouter Vernaillen
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.triberayexamples",
		"com.liferay.fragment.entry.processor.portlet.alias=" + InstagramKeys.INSTAGRAM_PORTLET_WIDGETNAME,
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.header-portlet-css=/lib/main.css",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.name=" + InstagramKeys.INSTAGRAM_PORTLET_KEY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class VueInstagramPortlet extends MVCPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(
			"mainRequire",
			npmResolver.resolveModuleName("triberay-vue-instagram-portlet") + " as main");

		renderRequest.setAttribute(InstagramConfiguration.class.getName(), instagramConfiguration);

		super.doView(renderRequest, renderResponse);
	}

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		instagramConfiguration = ConfigurableUtil.createConfigurable(InstagramConfiguration.class, properties);
	}

	@Reference
	private NPMResolver npmResolver;

	private InstagramConfiguration instagramConfiguration;
}

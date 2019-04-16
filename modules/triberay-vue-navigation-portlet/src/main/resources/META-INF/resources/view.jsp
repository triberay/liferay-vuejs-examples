<%@ include file="/init.jsp" %>

<aui:script require="<%= mainRequire %>">
	main.initVueApp(
	    'navapp',
		'<%= siteLayouts %>',
		'<%= siteUrl %>',
		'<%= currentLayoutId %>',
		'<%= parentLayoutId %>',
		$('#wrapper.vueNavClosed').length > 0,
		'${themeDisplay.getPathThemeImages()}/clay/icons.svg'
	);
</aui:script>

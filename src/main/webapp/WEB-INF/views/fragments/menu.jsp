<%--
- menu.jsp
-
- Copyright (C) 2012-2024 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.davgavser"
				action="https://store.steampowered.com/app/813780/Age_of_Empires_II_Definitive_Edition/?l=spanish" />
			<acme:menu-suboption code="master.menu.anonymous.felsolagu"
				action="https://store.steampowered.com/app/730/CounterStrike_2/" />
			<acme:menu-suboption code="master.menu.anonymous.pabespnar"
				action="https://www.atleticodemadrid.com/" />
			<acme:menu-suboption code="master.menu.anonymous.ignnarber"
				action="https://www.leagueoflegends.com/es-es" />
			<acme:menu-suboption code="master.menu.anonymous.luisgp9"
				action="https://sevillafc.es/" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator"
			access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts"
				action="/administrator/user-account/list" />
			<acme:menu-separator />
			<acme:menu-suboption
				code="master.menu.administrator.populate-initial"
				action="/administrator/system/populate-initial" />
			<acme:menu-suboption code="master.menu.administrator.populate-sample"
				action="/administrator/system/populate-sample" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shut-down"
				action="/administrator/system/shut-down" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.banner"
				action="/administrator/banner/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.developer"
			access="hasRole('Developer')">
			<acme:menu-suboption code="master.menu.list.training-module"
				action="/developer/training-module/list" />
			<acme:menu-suboption code="master.menu.show.developer-dashboard"
				action="/developer/developer-dashboard/show" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider"
			access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link"
				action="http://www.example.com/" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer"
			access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link"
				action="http://www.example.com/" />
		</acme:menu-option>
		<acme:menu-option code="master.menu.client" access="hasRole('Client')">
			<acme:menu-suboption code="master.menu.client.contract.list"
				action="/client/contract/list" />
			<acme:menu-suboption code="master.menu.client.dashboard"
				action="/client/client-dashboard/show" />


		</acme:menu-option>



		<acme:menu-option code="master.menu.auditor"
			access="hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.auditor.code-audits"
				action="/auditor/code-audit/list-mine" />
			<!-- <acme:menu-suboption code="master.menu.auditor.all-code-audits" action="/auditor/code-audit/list-all"/> -->
			<acme:menu-suboption code="master.menu.auditor.dashboard"
				action="/auditor/auditor-dashboard/show" />
		</acme:menu-option>



		<acme:menu-option code="master.menu.manager"
			access="hasRole('Manager')">
			<acme:menu-suboption code="master.menu.manager.project.list"
				action="/manager/project/list" />
			<acme:menu-suboption code="master.menu.manager.userStory.list"
				action="/manager/user-story/list" />
			<acme:menu-suboption code="master.menu.manager.dashboard"
				action="/manager/manager-dashboard/show" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.claims" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.any.claims"
				action="/any/claim/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.claims" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.any.claims"
				action="/any/claim/list" />
		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up"
			action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in"
			action="/anonymous/system/sign-in" access="isAnonymous()" />

		<acme:menu-option code="master.menu.user-account"
			access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data"
				action="/authenticated/user-account/update" />
			<acme:menu-suboption code="master.menu.user-account.become-provider"
				action="/authenticated/provider/create"
				access="!hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.provider"
				action="/authenticated/provider/update" access="hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.become-consumer"
				action="/authenticated/consumer/create"
				access="!hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.consumer"
				action="/authenticated/consumer/update" access="hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.become-auditor"
				action="/authenticated/auditor/create" access="!hasRole('Auditor')" />
			<acme:menu-suboption code="master.menu.user-account.auditor"
				action="/authenticated/auditor/update" access="hasRole('Auditor')" />
			<acme:menu-suboption code="master.menu.user-account.become-developer"
				action="/authenticated/developer/create"
				access="!hasRole('Developer')" />
			<acme:menu-suboption code="master.menu.user-account.developer"
				action="/authenticated/developer/update"
				access="hasRole('Developer')" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out"
			action="/authenticated/system/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>


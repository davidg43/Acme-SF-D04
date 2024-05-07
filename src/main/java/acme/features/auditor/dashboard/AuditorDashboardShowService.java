/*
 * AuditorDashboardShowService.java
 *
 * Copyright (C) 2012-2024 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.auditor.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.AuditorDashboard;
import acme.roles.Auditor;

@Service
public class AuditorDashboardShowService extends AbstractService<Auditor, AuditorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {

		AuditorDashboard dashboard;
		Integer totalStaticCodeAudits;
		Integer totalDynamicCodeAudits;
		Double averageNumberOfAuditRecords;
		Double deviationNumberOfAuditRecords;
		Integer minimumNumberOfAuditRecords;
		Integer maximumNumberOfAuditRecords;
		Double averagePeriodInAuditRecords;
		Double deviationPeriodInAuditRecords;
		Double minimumPeriodInAuditRecords;
		Double maximumPeriodInAuditRecords;

		totalStaticCodeAudits = this.repository.totalStaticCodeAudits();
		totalDynamicCodeAudits = this.repository.totalDynamicCodeAudits();
		averageNumberOfAuditRecords = this.repository.averageNumberOfAuditRecords();
		deviationNumberOfAuditRecords = this.repository.deviationNumberOfAuditRecords();
		minimumNumberOfAuditRecords = this.repository.minimumNumberOfAuditRecords();
		maximumNumberOfAuditRecords = this.repository.maximumNumberOfAuditRecords();
		averagePeriodInAuditRecords = this.repository.averagePeriodInAuditRecords();
		deviationPeriodInAuditRecords = this.repository.deviationPeriodInAuditRecords();
		minimumPeriodInAuditRecords = this.repository.minimumPeriodInAuditRecords();
		maximumPeriodInAuditRecords = this.repository.maximumPeriodInAuditRecords();

		dashboard = new AuditorDashboard();
		dashboard.setTotalStaticCodeAudits(totalStaticCodeAudits);
		dashboard.setTotalDynamicCodeAudits(totalDynamicCodeAudits);
		dashboard.setAverageNumberOfAuditRecords(averageNumberOfAuditRecords);
		dashboard.setDeviationNumberOfAuditRecords(deviationNumberOfAuditRecords);
		dashboard.setMinimumNumberOfAuditRecords(minimumNumberOfAuditRecords);
		dashboard.setMaximumNumberOfAuditRecords(maximumNumberOfAuditRecords);
		dashboard.setAveragePeriodInAuditRecords(averagePeriodInAuditRecords);
		dashboard.setDeviationPeriodInAuditRecords(deviationPeriodInAuditRecords);
		dashboard.setMinimumPeriodInAuditRecords(minimumPeriodInAuditRecords);
		dashboard.setMaximumPeriodInAuditRecords(maximumPeriodInAuditRecords);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final AuditorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalStaticCodeAudits", "totalDynamicCodeAudits", "averageNumberOfAuditRecords", "deviationNumberOfAuditRecords", "maximumNumberOfAuditRecords", "minimumNumberOfAuditRecords", "averagePeriodInAuditRecords",
			"deviationPeriodInAuditRecords", "minimumPeriodInAuditRecords", "maximumPeriodInAuditRecords");

		super.getResponse().addData(dataset);
	}

}

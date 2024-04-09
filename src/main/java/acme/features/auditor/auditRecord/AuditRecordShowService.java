/*
 * AuditRecordShowService.java
 *
 * Copyright (C) 2012-2024 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.codeAudit.AuditRecord;
import acme.roles.Auditor;

@Service
public class AuditRecordShowService extends AbstractService<Auditor, AuditRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditRecordRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;
		AuditRecord ar;

		id = super.getRequest().getData("id", int.class);
		ar = this.repository.findOneAuditRecordById(id);
		status = ar != null && super.getRequest().getPrincipal().hasRole(ar.getCodeAudit().getAuditor());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		AuditRecord object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneAuditRecordById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "codeAudit.correctiveActions", "periodInit", "periodEnd", "mark", "link");

		super.getResponse().addData(dataset);
	}

}

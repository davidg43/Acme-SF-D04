/*
 * AuditorDashboardRepository.java
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.roles.Auditor;

@Repository
public interface AuditorDashboardRepository extends AbstractRepository {

	@Query("select a from Auditor a where a.userAccount.id = :id")
	Auditor findAuditorById(int id);

	@Query("select count(ca) from CodeAudit ca where ca.type = acme.entities.codeAudit.Type.STATIC and ca.auditor.id = :id and ca.draftMode = false")
	Integer totalStaticCodeAudits(int id);

	@Query("select count(ca) from CodeAudit ca where ca.type = acme.entities.codeAudit.Type.DYNAMIC and ca.auditor.id = :id and ca.draftMode = false")
	Integer totalDynamicCodeAudits(int id);

	@Query("select avg((select count(ar) from AuditRecord ar where ar.codeAudit = ca)) from CodeAudit ca where ca.auditor.id = :id and ca.draftMode = false")
	Double averageNumberOfAuditRecords(int id);

	@Query("select stddev((select count(ar) from AuditRecord ar where ar.codeAudit = ca)) from CodeAudit ca where ca.auditor.id = :id and ca.draftMode = false")
	Double deviationNumberOfAuditRecords(int id);

	@Query("select min((select count(ar) from AuditRecord ar where ar.codeAudit = ca)) from CodeAudit ca where ca.auditor.id = :id and ca.draftMode = false")
	Integer minimumNumberOfAuditRecords(int id);

	@Query("select max((select count(ar) from AuditRecord ar where ar.codeAudit = ca)) from CodeAudit ca where ca.auditor.id = :id and ca.draftMode = false")
	Integer maximumNumberOfAuditRecords(int id);

	@Query("select avg(ar.period) from AuditRecord ar where ar.codeAudit.auditor.id = :id and ar.codeAudit.draftMode = false and ar.isDraftMode = false")
	Double averagePeriodInAuditRecords(int id);

	@Query("select stddev(ar.period) from AuditRecord ar where ar.codeAudit.auditor.id = :id and ar.codeAudit.draftMode = false and ar.isDraftMode = false")
	Double deviationPeriodInAuditRecords(int id);

	@Query("select min(ar.period) from AuditRecord ar where ar.codeAudit.auditor.id = :id and ar.codeAudit.draftMode = false and ar.isDraftMode = false")
	Double minimumPeriodInAuditRecords(int id);

	@Query("select max(ar.period) from AuditRecord ar where ar.codeAudit.auditor.id = :id and ar.codeAudit.draftMode = false and ar.isDraftMode = false")
	Double maximumPeriodInAuditRecords(int id);

}

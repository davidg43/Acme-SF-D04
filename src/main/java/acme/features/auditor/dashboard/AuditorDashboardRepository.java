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

@Repository
public interface AuditorDashboardRepository extends AbstractRepository {

	@Query("select count(distinct ca) from CodeAudit ca where ca.type = 'STATIC'")
	Integer totalStaticCodeAudits();

	@Query("select count(distinct ca) from CodeAudit ca where ca.type = 'DYNAMIC'")
	Integer totalDynamicCodeAudits();

	@Query("select avg((select count(ar) from AuditRecord ar where ar.codeAudit = ca)) from CodeAudit ca")
	Double averageNumberOfAuditRecords();

	@Query("select stddev((select count(ar) from AuditRecord ar where ar.codeAudit = ca)) from CodeAudit ca")
	Double deviationNumberOfAuditRecords();

	@Query("select min((select count(ar) from AuditRecord ar where ar.codeAudit = ca)) from CodeAudit ca")
	Integer minimumNumberOfAuditRecords();

	@Query("select max((select count(ar) from AuditRecord ar where ar.codeAudit = ca)) from CodeAudit ca")
	Integer maximumNumberOfAuditRecords();

	@Query("select avg(ar.period) from AuditRecord ar")
	Double averagePeriodInAuditRecords();

	@Query("select stddev(ar.period) from AuditRecord ar")
	Double deviationPeriodInAuditRecords();

	@Query("select min(ar.period) from AuditRecord ar")
	Double minimumPeriodInAuditRecords();

	@Query("select max(ar.period) from AuditRecord ar")
	Double maximumPeriodInAuditRecords();

}


package acme.features.authenticated.sponsorship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorShip.SponsorShip;
import acme.roles.Sponsor;

@Service
public class SponsorShipShowService extends AbstractService<Sponsor, SponsorShip> {

	@Autowired
	private SponsorShipRepository repo;


	@Override
	public void authorise() {
		boolean status;
		int id;
		SponsorShip sp;
		
		id = super.getRequest().getData("id", int.class);
		sp = this.repo.findOneSponsorShipById(id);
		status = sp != null && super.getRequest().getPrincipal().hasRole(sp)
		
	}

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

}

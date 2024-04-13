
package acme.features.authenticated.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.sponsorShip.SponsorShip;
import acme.roles.Sponsor;

@Service
public class SponsorShipListAllService extends AbstractService<Sponsor, SponsorShip> {

	@Autowired
	private SponsorShipRepository repo;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<SponsorShip> objects;

		objects = this.repo.findAllSponsorShip();
		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final SponsorShip object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "moment", "duration", "amount", "type", "contact", "link", "invoice", "project");

		super.getResponse().addData(dataset);
	}

}

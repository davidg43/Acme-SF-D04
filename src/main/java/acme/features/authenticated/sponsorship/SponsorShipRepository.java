
package acme.features.authenticated.sponsorship;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorShip.SponsorShip;

@Repository
public interface SponsorShipRepository extends AbstractRepository {

	@Query("select s from SponsorShip where s.id = :id")
	SponsorShip findOneSponsorShipById(int id);

	@Query("select s from SponsorShip s")
	Collection<SponsorShip> findAllSponsorShip();

	@Query("select s from SponsorShip s where s.invoice.id = :invoiceId")
	Collection<SponsorShip> findManySponsorShipByInvoiceId(int invoiceId);

}

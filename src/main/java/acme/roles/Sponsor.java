
package acme.roles;

import javax.validation.constraints.NotBlank;

import acme.client.data.AbstractRole;

public class Sponsor extends AbstractRole {

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				sponsorShips;

	@NotBlank
	private String				invoices;

}

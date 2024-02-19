
package acme.roles;

import javax.validation.constraints.NotBlank;

import acme.client.data.AbstractRole;

public class Developer extends AbstractRole {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				trainingModule;
	@NotBlank
	private String				trainingSessions;

}

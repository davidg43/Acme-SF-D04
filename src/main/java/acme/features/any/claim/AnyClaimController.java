/*
 * ClaimController.java
 *
 * Copyright (C) 2012-2024 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.any.claim;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Any;
import acme.entities.Claim;

@Controller
public class AnyClaimController extends AbstractController<Any, Claim> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyClaimListService	listService;

	@Autowired
	private AnyClaimShowService	showService;

	@Autowired
	private AnyClaimCreateService	createService;

	@Autowired
	private AnyClaimPublishService	publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("show", this.showService);

		super.addCustomCommand("publish", "update", this.publishService);
	}

}


package acme.features.client.contract;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.contract.Contract;
import acme.roles.Client;

@Controller
public class ClientContractController extends AbstractController<Client, Contract> {

	@Autowired
	private ClientContractDeleteService		deleteService;

	@Autowired
	private ClientContractCreateService		createService;

	@Autowired
	private ClientContractUpdateService		updateService;

	@Autowired
	private ClientContractShowService		showService;

	@Autowired
	private ClientContractListAllService	listService;

	@Autowired
	private ClientContractPublishService	publishService;


	@PostConstruct
	protected void initialise() {

		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("delete", this.deleteService);
		super.addCustomCommand("publish", "update", this.publishService);
	}
}

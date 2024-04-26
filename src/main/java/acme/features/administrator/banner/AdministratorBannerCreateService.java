/*
 * AdministratorBannerCreateService.java
 *
 * Copyright (C) 2012-2024 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.banner;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.Banner;

@Service
public class AdministratorBannerCreateService extends AbstractService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBannerRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Banner object;

		object = new Banner();

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Banner object) {
		assert object != null;

		Date currentMoment = MomentHelper.getCurrentMoment();
		Date creationMoment = new Date(currentMoment.getTime() - 6000);

		super.bind(object, "instantiationOrUpdateDate", "periodInit", "periodEnd", "picture", "slogan", "link");
		object.setInstantiationOrUpdateDate(creationMoment);
	}

	@Override
	public void validate(final Banner object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("instantiationOrUpdateDate") && !super.getBuffer().getErrors().hasErrors("periodInit") && !super.getBuffer().getErrors().hasErrors("periodEnd")) {
			Date minimumPeriod;

			minimumPeriod = MomentHelper.deltaFromMoment(object.getPeriodInit(), 7, ChronoUnit.DAYS);

			if (!MomentHelper.isBefore(object.getInstantiationOrUpdateDate(), object.getPeriodInit()))
				super.state(false, "instantiationOrUpdateDate", "administrator.banner.form.error.instantiation-after-display");
			else if (!MomentHelper.isBefore(object.getPeriodInit(), object.getPeriodEnd()))
				super.state(false, "periodInit", "administrator.banner.form.error.init-after-end");
			else if (!MomentHelper.isBeforeOrEqual(minimumPeriod, object.getPeriodEnd()))
				super.state(false, "periodEnd", "administrador.banner.form.error.too-close");
		}

	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "instantiationOrUpdateDate", "periodInit", "periodEnd", "picture", "slogan", "link");

		super.getResponse().addData(dataset);
	}

}

package org.ligoj.app.model;

import java.util.Date;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;
import org.ligoj.app.api.Activity;
import org.ligoj.app.api.SubscriptionMode;
import org.ligoj.app.iam.model.CacheCompany;
import org.ligoj.app.iam.model.CacheGroup;
import org.ligoj.app.iam.model.CacheUser;
import org.ligoj.app.iam.model.DelegateType;
import org.ligoj.app.iam.model.ReceiverType;

/**
 * Simple test of API beans.
 */
public class BeanTest {

	@Test
	public void testCacheCompany() {
		Assert.assertTrue(new CacheCompany().isNew());
		
	}

	@Test
	public void testCacheGroup() {
		Assert.assertTrue(new CacheGroup().isNew());
	}

	@Test
	public void testActivity() {
		check(new Activity(), Activity::setLastConnection, Activity::getLastConnection, new Date());
	}
	
	

	@Test
	public void testEnum() {
		ParameterType.valueOf(ParameterType.values()[0].name());
		ReceiverType.valueOf(ReceiverType.values()[0].name());
		DelegateType.valueOf(DelegateType.values()[0].name());
		SubscriptionMode.valueOf(SubscriptionMode.values()[0].name());
		EventType.valueOf(EventType.values()[0].name());
		ContainerType.valueOf(ContainerType.values()[0].name()).getDelegateType();
	}


	@Test
	public void testCacheUser() {
		final CacheUser user = new CacheUser();

		// Simple user attributes
		check(user, CacheUser::setCompany, CacheUser::getCompany, new CacheCompany());
		check(user, CacheUser::setFirstName, CacheUser::getFirstName, "first");
		check(user, CacheUser::setLastName, CacheUser::getLastName, "last");
		check(user, CacheUser::setMails, CacheUser::getMails, "singlemail");
	}

	private <T, X> void check(X bean, BiConsumer<X, T> setter, Function<X, T> getter, T value) {
		setter.accept(bean, value);
		Assert.assertEquals(value, getter.apply(bean));
	}
}
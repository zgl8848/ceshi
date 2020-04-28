package com.campus.warning.service.impl;


public interface SubscribeService {

	String eventSubscriptions(String schoolCodeId);

	String UnsubscriptionEvent(String schoolCodeId);

	String viewSubscriptionEvents(String schoolCodeId);

}

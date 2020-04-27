package com.hcdz.hk.impl;


public interface SubscribeService {

	String eventSubscriptions(String schoolCodeId);

	String UnsubscriptionEvent(String schoolCodeId);

	String viewSubscriptionEvents(String schoolCodeId);
	
	String tryR(String schoolCodeId);

}

package com.hoangdh.doctor_app.common.service;

import java.util.List;
import java.util.Map;

public interface JwtAuthenticationManager {
	Boolean isAuthenticated();

	Map<String, Object> getClaims();

	String getUserId();

	String getIdentityProviderUserId();

	<T> T getClaim(String key);

	List<String> getUserPermissions();
}

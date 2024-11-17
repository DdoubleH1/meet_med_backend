package com.hoangdh.doctor_app.modules.identity_providers.interfaces;

public abstract class IdentityProviderStrategy implements IdentityProviderUserManagementStrategy {
	abstract public String getUserIdKey();
}

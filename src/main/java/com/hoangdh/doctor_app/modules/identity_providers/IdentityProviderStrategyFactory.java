package com.hoangdh.doctor_app.modules.identity_providers;


import com.hoangdh.doctor_app.common.interfaces.StrategyFactory;
import com.hoangdh.doctor_app.modules.identity_providers.interfaces.IdentityProviderStrategy;
import com.hoangdh.doctor_app.modules.identity_providers.provider.auth0.strategy.Auth0Strategy;
import com.hoangdh.doctor_app.modules.identity_providers.provider.mock.strategy.MockStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdentityProviderStrategyFactory extends StrategyFactory<IdentityProviderStrategy> {
	private final Auth0Strategy auth0Strategy;
	private final MockStrategy mockStrategy;

	public static final String DEFAULT_STRATEGY = "auth0";

	@Override
	public IdentityProviderStrategy getStrategy(String strategy) {
		return switch (strategy.toLowerCase()) {
			case "auth0" -> auth0Strategy;
			case "mock" -> mockStrategy;
			default -> throw new IllegalArgumentException("Invalid strategy: " + strategy);
		};
	}
}

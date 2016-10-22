package com.leqcar.instalmentbilling.domain.model.charges;

import java.util.List;

public interface ChargesRuleRepository {

	List<ChargesRule> findEffectiveRulesByProduct();
}

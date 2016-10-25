package com.leqcar.instalmentbilling.infrastructure.charges;

import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevelCodeMatcher;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.product.Premium;

public class ProductLevelCodeMatcher implements AttachmentLevelCodeMatcher {

	@Override
	public boolean matches(ChargesRule chargesRule, Premium premium) {
		return true;
	}

}

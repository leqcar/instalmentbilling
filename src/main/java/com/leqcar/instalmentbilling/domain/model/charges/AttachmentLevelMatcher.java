package com.leqcar.instalmentbilling.domain.model.charges;

import java.util.function.BiPredicate;

import com.leqcar.instalmentbilling.domain.model.product.Premium;

public interface AttachmentLevelMatcher extends BiPredicate<ChargesRule, Premium>{

}

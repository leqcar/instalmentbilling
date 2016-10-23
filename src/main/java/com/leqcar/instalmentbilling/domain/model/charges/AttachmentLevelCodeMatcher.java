package com.leqcar.instalmentbilling.domain.model.charges;

import com.leqcar.instalmentbilling.domain.model.product.Premium;


/**
 * Created by jongtenerife on 24/10/2016.
 */
public interface AttachmentLevelCodeMatcher {
    boolean matches(ChargesRule chargesRule, Premium premium);
}

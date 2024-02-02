package fr.bgili.bestyoutube.utils.validators

import android.webkit.URLUtil
import fr.bgili.bestyoutube.R
import fr.bgili.bestyoutube.utils.validators.base.BaseValidator
import fr.bgili.bestyoutube.utils.validators.base.ValidateResult

class URLValidator(private val input: String) : BaseValidator() {

    override fun validate(): ValidateResult {
        // check if input is a valid url (regex)

        val isValid = URLUtil.isValidUrl(input)

        return ValidateResult(
            isValid,
            if (isValid) R.string.text_validation_success else R.string.text_validation_error_empty_field
        )
    }

}
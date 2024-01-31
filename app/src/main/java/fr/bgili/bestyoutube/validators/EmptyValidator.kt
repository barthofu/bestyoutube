package fr.bgili.bestyoutube.validators

import fr.bgili.bestyoutube.R
import fr.bgili.bestyoutube.validators.base.BaseValidator
import fr.bgili.bestyoutube.validators.base.ValidateResult

class EmptyValidator(private val input: String) : BaseValidator() {

    override fun validate(): ValidateResult {
        val isValid = input.isNotEmpty()
        return ValidateResult(
            isValid,
            if (isValid) R.string.text_validation_success else R.string.text_validation_error_empty_field
        )
    }
}
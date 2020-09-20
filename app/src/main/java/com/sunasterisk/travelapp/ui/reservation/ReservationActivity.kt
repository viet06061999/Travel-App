package com.sunasterisk.travelapp.ui.reservation

import android.content.Context
import android.content.Intent
import com.google.android.material.datepicker.MaterialDatePicker
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.di.Injector
import com.sunasterisk.travelapp.ui.base.BaseMVPActivity
import com.sunasterisk.travelapp.ui.main.MainActivity
import com.sunasterisk.travelapp.utils.isValidEmail
import kotlinx.android.synthetic.main.activity_reservation.*

class ReservationActivity :
    BaseMVPActivity<ReservationContract.View, ReservationContract.Presenter>(),
    ReservationContract.View {

    override val layoutResource get() = R.layout.activity_reservation
    override val presenter by lazy {
        ReservationPresenter(Injector.getReservationRepository(this))
    }

    val restaurant: Restaurant by lazy {
        intent.getParcelableExtra<Restaurant>(EXTRA_RESTAURANT)
    }

    val reservation: Reservation? by lazy {
        intent.getParcelableExtra<Reservation>(EXTRA_RESERVATION)
    }

    override fun initComponents() {
        setContentView()
        setReservationText()
    }

    override fun navigationMain() {
        startActivity(MainActivity.getIntent(this))
    }

    private fun setContentView() {
        toolbarReservation.setNavigationOnClickListener {
            onBackPressed()
        }
        editTextDate.setOnClickListener { setupDatePickerBuilder() }
        val id = reservation?.id ?: -1
        materialButtonSave.setOnClickListener {
            if (isValidInput()) presenter.saveReservation(
                Reservation(
                    id,
                    restaurant,
                    editTextEmail.text.toString(),
                    editTextPhone.text.toString(),
                    editextName.text.toString(),
                    editTextDate.text.toString(),
                    editTextPerson.text.toString().toInt(),
                    0
                )
            )
            else {
                showToastMessage(ERROR)
            }
        }
    }

    private fun setupDatePickerBuilder() {
        val builder =
            MaterialDatePicker.Builder.datePicker()
        builder.setTheme(R.style.ThemeOverlay_Catalog_MaterialCalendar_Custom)
        val picker = builder.build()
        picker.show(supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener {
            editTextDate.setText(picker.headerText)
        }
    }

    private fun setReservationText() {
        reservation?.apply {
            editextName.setText(name)
            editTextDate.setText(arrivalTime)
            editTextEmail.setText(email)
            editTextPerson.setText(numAdult.toString())
            editTextPhone.setText(phone)
        }
    }

    private fun isValidInput(): Boolean {
        var result = true
        result = result and if (editextName.text.isNullOrBlank()) {
            editextName.error = NAME_BLANK
            false
        } else {
            editextName.error = null
            true
        }
        result = result and if (editTextDate.text.isNullOrBlank()) {
            editTextDate.error = DATE_BLANK
            false
        } else {
            editTextDate.error = null
            true
        }
        result = result and if (editTextEmail.text.isNullOrBlank()) {
            editTextEmail.error = EMAIl_BLANK
            false
        } else if (!editTextEmail.text.toString().isValidEmail()) {
            editTextEmail.error = EMAIL_NOT_VALID
            false
        } else {
            editTextEmail.error = null
            true
        }
        result = result and if (editTextPerson.text.isNullOrBlank()) {
            editTextPerson.error = NUMBER_PERSON_BLANK
            false
        } else {
            editTextPerson.error = null
            true
        }
        result = result and if (editTextPhone.text.isNullOrBlank()) {
            editTextPhone.error = PHONE_BLANK
            false
        } else {
            editTextPhone.error = null
            true
        }
        return result
    }

    companion object {
        private const val EXTRA_RESTAURANT = "EXTRA_RESTAURANT"
        private const val EXTRA_RESERVATION = "EXTRA_RESERVATION"
        private const val NAME_BLANK = "name must not blank"
        private const val EMAIl_BLANK = "email must not blank"
        private const val EMAIL_NOT_VALID = "email not valid"
        private const val PHONE_BLANK = "phone must not blank"
        private const val DATE_BLANK = "date must not blank"
        private const val NUMBER_PERSON_BLANK = "number person must not blank"
        private const val ERROR = "Insert reservation fail check input again!"

        fun getIntent(
            context: Context,
            restaurant: Restaurant,
            reservation: Reservation? = null
        ) =
            Intent(context, ReservationActivity::class.java)
                .putExtra(EXTRA_RESTAURANT, restaurant)
                .putExtra(EXTRA_RESERVATION, reservation)

    }
}

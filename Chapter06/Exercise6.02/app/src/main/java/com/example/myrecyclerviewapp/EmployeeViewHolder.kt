package com.example.myrecyclerviewapp

import android.view.View
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myrecyclerviewapp.model.EmployeeRole
import com.example.myrecyclerviewapp.model.EmployeeUiModel
import com.example.myrecyclerviewapp.model.Gender
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_employee.item_employee_biography as employeeBioView
import kotlinx.android.synthetic.main.item_employee.item_employee_role as employeeRoleView
import kotlinx.android.synthetic.main.item_employee.item_employee_gender as employeeGenderView
import kotlinx.android.synthetic.main.item_employee.item_employee_name as employeeNameView
import kotlinx.android.synthetic.main.item_employee.item_employee_photo as employeePhotoView

private val FEMALE_SYMBOL by lazy {
    HtmlCompat.fromHtml("&#9793;", HtmlCompat.FROM_HTML_MODE_LEGACY)
}
private val MALE_SYMBOL by lazy {
    HtmlCompat.fromHtml("&#9794;", HtmlCompat.FROM_HTML_MODE_LEGACY)
}
private const val UNKNOWN_SYMBOL = "?"

class EmployeeViewHolder(
    override val containerView: View,
    private val imageLoader: ImageLoader
) : ViewHolder(containerView), LayoutContainer {
    fun bindData(employeeData: EmployeeUiModel) {
        imageLoader.loadImage(employeeData.imageUrl, employeePhotoView)
        employeeNameView.text = employeeData.name
        employeeRoleView.text = when (employeeData.role) {
            EmployeeRole.HumanResources -> "Human Resources"
            EmployeeRole.Management -> "Management"
            EmployeeRole.Technology -> "Technology"
        }
        employeeBioView.text = employeeData.biography
        employeeGenderView.text = when (employeeData.gender) {
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            else -> UNKNOWN_SYMBOL
        }
    }
}

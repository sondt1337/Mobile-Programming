package vn.edu.hust.studentman

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentModel(
    val studentName: String,
    val studentId: String
) : Parcelable {
    override fun toString(): String {
        return "$studentName - $studentId"
    }
}
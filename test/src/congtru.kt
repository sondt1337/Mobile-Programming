fun main() {
    // Nhập số nguyên từ người dùng
    print("Nhập số nguyên thứ nhất: ")
    val num1 = readLine()?.toIntOrNull() ?: 0

    print("Nhập số nguyên thứ hai: ")
    val num2 = readLine()?.toIntOrNull() ?: 0

    // Thực hiện phép cộng và trừ
    val sum = num1 + num2
    val difference = num1 - num2

    // Hiển thị kết quả
    println("Kết quả của phép cộng: $sum")
    println("Kết quả của phép trừ: $difference")
}

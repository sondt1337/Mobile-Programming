import kotlin.math.sqrt

fun main() {
    val a = 1.0
    val b = -3.0
    val c = 2.0
    if (a == 0.0)
        if (b ==0.0)
            if (c ==0.0)
                println("Phuong trinh vo so nghiem")
            else
                println("Phuong trinh vo nghiem")
        else
            println("Phuong trinh co nghiem x = ${-c/b}")
    else{
        val delta = b*b - 4*a*c
        if (delta < 0.0)
            println("Phuong trinh vo nghiem")
        else if (delta == 0.0)
            println("Phuong trinh co nghiem kep x = ${-b/2*a}")
        else
            println("Phuong trinh co 2 nghiem x1 = ${(-b- sqrt(delta))/2*a} va x2 = ${(-b + sqrt(delta))/2*a}")
    }

}
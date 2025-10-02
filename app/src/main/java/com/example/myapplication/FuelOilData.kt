package com.example.fuelcalculator

data class FuelOilData(
    val H: Double = 0.0,
    val C: Double = 0.0,
    val S: Double = 0.0,
    val O: Double = 0.0,
    val V: Double = 0.0,
    val W: Double = 0.0,
    val A: Double = 0.0,
    val Q: Double = 0.0
) {
    fun calculateResults(): FuelOilResults {
        val Hp = H * (100 - W - A) / 100
        val Cp = C * (100 - W - A) / 100
        val Sp = S * (100 - W - A) / 100
        val Op = O * (100 - W - A) / 100
        val Ap = A * (100 - W) / 100
        val Vp = V * (100 - W) / 100
        val Qp = Q * (100 - W - A) / 100 - 0.025 * W

        return FuelOilResults(
            Hp = Hp,
            Cp = Cp,
            Sp = Sp,
            Op = Op,
            Ap = Ap,
            Vp = Vp,
            Wp = W,
            Qp = Qp
        )
    }
}

data class FuelOilResults(
    val Hp: Double,
    val Cp: Double,
    val Sp: Double,
    val Op: Double,
    val Ap: Double,
    val Vp: Double,
    val Wp: Double,
    val Qp: Double
)
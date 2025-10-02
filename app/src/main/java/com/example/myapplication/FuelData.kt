package com.example.fuelcalculator

data class FuelData(
    val H: Double = 0.0,
    val C: Double = 0.0,
    val S: Double = 0.0,
    val N: Double = 0.0,
    val O: Double = 0.0,
    val W: Double = 0.0,
    val A: Double = 0.0
) {
    fun calculateResults(): FuelResults {
        val Kpc = 100 / (100 - W)
        val Kpg = 100 / (100 - W - A)

        val Hc = H * Kpc
        val Cc = C * Kpc
        val Sc = S * Kpc
        val Nc = N * Kpc
        val Oc = O * Kpc
        val Ac = A * Kpc

        val Hg = H * Kpg
        val Cg = C * Kpg
        val Sg = S * Kpg
        val Ng = N * Kpg
        val Og = O * Kpg

        val Qph = (339 * C + 1030 * H - 108.8 * (O - S) - 25 * W)
        val Qch = (Qph / 1000 + 0.025 * W) * Kpc
        val Qgh = (Qph / 1000 + 0.025 * W) * Kpg

        return FuelResults(
            Kpc = Kpc,
            Kpg = Kpg,
            Hc = Hc,
            Cc = Cc,
            Sc = Sc,
            Nc = Nc,
            Oc = Oc,
            Ac = Ac,
            Hg = Hg,
            Cg = Cg,
            Sg = Sg,
            Ng = Ng,
            Og = Og,
            Qph = Qph,
            Qch = Qch,
            Qgh = Qgh
        )
    }
}

data class FuelResults(
    val Kpc: Double,
    val Kpg: Double,
    val Hc: Double,
    val Cc: Double,
    val Sc: Double,
    val Nc: Double,
    val Oc: Double,
    val Ac: Double,
    val Hg: Double,
    val Cg: Double,
    val Sg: Double,
    val Ng: Double,
    val Og: Double,
    val Qph: Double,
    val Qch: Double,
    val Qgh: Double
)
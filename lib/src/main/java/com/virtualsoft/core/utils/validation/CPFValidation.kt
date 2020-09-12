package com.virtualsoft.core.utils.validation

object CPFValidation {

    private val invalidCPFs = setOf(
        "00000000000",
        "11111111111",
        "22222222222",
        "33333333333",
        "44444444444",
        "55555555555",
        "66666666666",
        "77777777777",
        "88888888888",
        "99999999999"
    )

    fun isCPFValid(cpf: String): Boolean {

        val cpfClean = cpf.replace(".", "")
            .replace("-","")
        if (invalidCPFs.contains(cpfClean) || cpfClean.length != 11)
            return false

        try {
            cpfClean.toLong()
        }catch (e : Exception){
            return false
        }

        val dvCurrent10 = cpfClean.substring(9,10).toInt()
        val dvCurrent11= cpfClean.substring(10,11).toInt()

        val cpfNineFirst = IntArray(9)
        var i = 9
        while (i > 0 ) {
            cpfNineFirst[i-1] = cpfClean.substring(i-1, i).toInt()
            i--
        }

        val sumProductNine = IntArray(9)
        var weight = 10
        var position = 0
        while (weight >= 2){
            sumProductNine[position] = weight * cpfNineFirst[position]
            weight--
            position++
        }

        var dvForTenthDigit = sumProductNine.sum() % 11
        dvForTenthDigit = 11 - dvForTenthDigit
        if(dvForTenthDigit > 9)
            dvForTenthDigit = 0
        if (dvForTenthDigit != dvCurrent10)
            return false

        val cpfTenFirst = cpfNineFirst.copyOf(10)
        cpfTenFirst[9] = dvCurrent10

        val sumProductTen = IntArray(10)
        var w = 11
        var p = 0
        while (w >= 2){
            sumProductTen[p] = w * cpfTenFirst[p]
            w--
            p++
        }

        var dvForEleventhDigit = sumProductTen.sum() % 11
        dvForEleventhDigit = 11 - dvForEleventhDigit
        if(dvForEleventhDigit > 9)
            dvForEleventhDigit = 0
        if (dvForEleventhDigit != dvCurrent11)
            return false

        return true
    }
}